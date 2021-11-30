package com.example.spasdomworkerapp.ui.home

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spasdomworkerapp.R
import com.example.spasdomworkerapp.databinding.FragmentHomeBinding
import com.example.spasdomworkerapp.domain.Order
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    /**
     * One way to delay creation of the viewModel until an appropriate lifecycle method is to use
     * lazy. This requires that viewModel not be referenced before onViewCreated(), which we
     * do in this Fragment.
     */
    private val viewModel: HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, HomeViewModel.Factory(activity.application))[HomeViewModel::class.java]
    }

    /**
     * RecyclerView Adapter for converting a list of Video to cards.
     */

    private var viewModelOrdersAdapter: OrderItemsAdapter? = null

    /**
     * Called immediately after onCreateView() has returned, and fragment's
     * view hierarchy has been created.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateOrders()
    }

    fun updateOrders(){
        viewModel.orderItems.observe(viewLifecycleOwner, { orders ->
            orders?.apply {
                viewModelOrdersAdapter?.orderItems = orders
            }
        })
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return Return the View for the fragment's UI.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container,false)

        // Set the lifecycleOwner so DataBinding can observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        viewModelOrdersAdapter = OrderItemsAdapter(OrderItemClick {
            // context is not around, we can safely discard this click since the Fragment is no
            // longer on the screen
            this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToOrderDetailedFragment(it))
        })

        binding.root.findViewById<TextView>(R.id.date).setText(viewModel.OrderGetFormat)

        val butNext = binding.root.findViewById<ImageView>(R.id.nextDate)
        val butPrevious = binding.root.findViewById<ImageView>(R.id.previousDate)

        butNext.setOnClickListener {
            viewModel.NextDay()
            binding.root.findViewById<TextView>(R.id.date).setText(viewModel.OrderGetFormat)
            updateOrders()
        }

        butPrevious.setOnClickListener {
            viewModel.PreviousDay()
            binding.root.findViewById<TextView>(R.id.date).setText(viewModel.OrderGetFormat)
            updateOrders()
        }

        binding.root.findViewById<RecyclerView>(R.id.orders_rv).apply {
            layoutManager =
                object : LinearLayoutManager(context){}
            adapter = viewModelOrdersAdapter
        }

        binding.apply {
            swipeRefreshHome.setOnRefreshListener {
                viewModel?.swipeToRefresh()
                swipeRefreshHome.isRefreshing = false
            }
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.calendar -> {
                var cal = Calendar.getInstance()
                val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                           dayOfMonth: Int) {
                        cal.set(Calendar.YEAR, year)
                        cal.set(Calendar.MONTH, monthOfYear)
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                        viewModel.SomeDay(cal)
                        binding.root.findViewById<TextView>(R.id.date).setText(viewModel.OrderGetFormat)
                        updateOrders()
                    }
                }
                context?.let {
                    DatePickerDialog(
                        it,
                        dateSetListener,
                        // set DatePickerDialog to point to today's date when it loads up
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

/**
 * Click listener for Orders. By giving the block a name it helps a reader understand what it does.
 */
class OrderItemClick(val block: (Order) -> Unit) {
    fun onClick(order: Order) = block(order)
}


