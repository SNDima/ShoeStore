package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding


class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoeListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_list, container, false
        )

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)

        viewModel.shoes.observe(viewLifecycleOwner, { shoes ->
            val layout = binding.layoutShoeList
            layout.removeAllViews()
            shoes.forEach { shoe ->
                val shoeItemLayout = ShoeItemBinding.inflate(inflater, null, false)
                shoeItemLayout.shoe = shoe
                layout.addView(shoeItemLayout.root)
            }
        })

        return binding.root
    }

}