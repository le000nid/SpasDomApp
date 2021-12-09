package com.example.spasdomuserapp.ui.chat.singlechat

import android.os.Bundle
import android.text.style.TtsSpan.TYPE_TEXT
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.spasdomuserapp.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_single_chat.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.ui.chat.Chat
import com.example.spasdomuserapp.ui.chat.ChatFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*


class SingleChatFragment: Fragment(R.layout.fragment_single_chat) {
    private val args by navArgs<SingleChatFragmentArgs>()

    private lateinit var mListenerInfoToolbar: ChatFragment.AppValueEventListener
    private lateinit var mRecevingChat : Chat
    private lateinit var mToolbarInfo: View
    private lateinit var mRefChat: DatabaseReference
    private val userUid = Firebase.auth.currentUser!!.uid

    private lateinit var mRefMesseges: DatabaseReference
    private lateinit var mAdapter: SingleChatAdapter
    private lateinit var messageRecView: RecyclerView
    private lateinit var messageListener: AppValueEventListener
    private var mListMessage = emptyList<Message>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRefChat = Firebase.database.reference.child("chats").child(args.currentChat.name)
        initRecView()
        chat_btn_send_message.setOnClickListener {
            val message = chat_input_message.text.toString()
            if (message.isEmpty()){
                val toast = Toast.makeText(
                    context,
                    "Field is empty", Toast.LENGTH_SHORT
                )
                toast.show()
            } else sendMessage(message, userUid, TYPE_TEXT){
                chat_input_message.setText("")
            }
        }
    }
    private fun initRecView(){
        messageRecView = chat_recycle_view
        mAdapter = SingleChatAdapter()
        mRefMesseges = Firebase.database.reference.child("chats/${args.currentChat.name}/messages")
        messageRecView.adapter = mAdapter
        messageListener = AppValueEventListener { dataSnapshot ->
            mListMessage = dataSnapshot.children.map{ it.getMessage() }
            mAdapter.setList(mListMessage)
            messageRecView.smoothScrollToPosition(mAdapter.itemCount)
        }
        mRefMesseges.addValueEventListener(messageListener)
    }

    private fun sendMessage(message: String, id: String, typeText: String, function: () -> Unit) {
        val userUid = Firebase.auth.currentUser!!.uid.toString()
        val messageKey = Firebase.database.reference.child(userUid).push().key
        val mapMessage = hashMapOf<String, Any>()
        mapMessage["from"] = Firebase.auth.currentUser!!.uid.toString()
        mapMessage["fromName"] = "Сосед"// нужно будет заменить на имя из базы данных
        mapMessage["type"] = typeText
        mapMessage["text"] = message
        mapMessage["timeStamp"] = ServerValue.TIMESTAMP

        val mapDialog = hashMapOf<String, Any>()
        mapDialog["chats/${args.currentChat.name}/messages/$messageKey"] = mapMessage
        Firebase.database.reference.updateChildren(mapDialog)
            .addOnSuccessListener { function() }
            .addOnFailureListener{
                val toast = Toast.makeText(
                    context,
                    it.message.toString(),
                    Toast.LENGTH_SHORT)
                toast.show()}
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()
        mRefMesseges.removeEventListener(messageListener)
    }
    fun DataSnapshot.getMessage(): Message = this.getValue(Message::class.java) ?: Message()



}
class AppValueEventListener (val onSuccess:(DataSnapshot) -> Unit) : ValueEventListener {
    override fun onCancelled(p0: DatabaseError) {

    }

    override fun onDataChange(p0: DataSnapshot) {
        onSuccess(p0)
    }

}

