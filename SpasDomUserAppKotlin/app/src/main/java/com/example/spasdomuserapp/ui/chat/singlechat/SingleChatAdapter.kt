package com.example.spasdomuserapp.ui.chat.singlechat

import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomuserapp.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_message.view.*
import java.text.SimpleDateFormat
import java.util.*

class SingleChatAdapter: RecyclerView.Adapter<SingleChatAdapter.SingeChatHolder>(){

    private var listMessageCache = emptyList<Message>()

    class SingeChatHolder(view: View): RecyclerView.ViewHolder(view){
        val blocUserMessage: ConstraintLayout = view.block_user_massege
        val chatUSerMessage: TextView = view.chat_user_message
        val chatUSerMessageTime: TextView = view.chat_user_message_time

        val blocRecievedMessage: ConstraintLayout = view.bloc_received_message
        val chatRecievedMessage: TextView = view.chat_recived_message
        val chatRecievedMessageTime: TextView = view.chat_recived_message_time
        val chatRecievedMessageNameSender : TextView = view.chat_name_sender
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingeChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return SingeChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingeChatHolder, position: Int) {
        if (listMessageCache[position].from == Firebase.auth.currentUser!!.uid){
            holder.blocUserMessage.visibility = View.VISIBLE
            holder.blocRecievedMessage.visibility=View.GONE
            holder.chatUSerMessage.text=listMessageCache[position].text

            holder.chatUSerMessageTime.text = listMessageCache[position].timeSTAMP.toString().asTime()
        }else{
            holder.blocUserMessage.visibility = View.GONE
            holder.blocRecievedMessage.visibility=View.VISIBLE
            holder.chatRecievedMessage.text=listMessageCache[position].text
            holder.chatRecievedMessageTime.text = listMessageCache[position].timeSTAMP.toString().asTime()
            holder.chatRecievedMessageNameSender.text = listMessageCache[position].fromName
        }
    }

    override fun getItemCount(): Int = listMessageCache.size


    fun setList(list: List<Message>){
        listMessageCache = list
        notifyDataSetChanged()
    }
}
private fun String.asTime(): String{
    Log.d("AAA", this)
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}