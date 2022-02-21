package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding


class ShoeListFragment : Fragment() {

    private val viewModel: ApplicationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        val fab = binding.fab
        fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListToShoeDetails())
        }

        viewModel.shoes.observe(viewLifecycleOwner) { shoes ->
            val layout = binding.layoutShoeList
            layout.removeAllViews()
            shoes.forEach { shoe ->
                val shoeItemLayout = ShoeItemBinding.inflate(inflater, null, false)
                shoeItemLayout.shoe = shoe
                layout.addView(shoeItemLayout.root)
            }
        }

        viewModel.eventLogin.observe(viewLifecycleOwner) { loggedIn ->
            if (!loggedIn) {
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListToLogin())
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.login_destination) {
            viewModel.onLogout()
            super.onOptionsItemSelected(item)
        } else {
            (NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                    || super.onOptionsItemSelected(item))
        }
    }
}