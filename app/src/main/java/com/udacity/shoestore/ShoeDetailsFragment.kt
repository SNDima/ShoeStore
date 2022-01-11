package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Bindable
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe


class ShoeDetailsFragment : Fragment() {

    private val viewModel: ApplicationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentShoeDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoe_details, container, false
        )

        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        binding.shoe = Shoe()

        viewModel.eventShoeSave.observe(viewLifecycleOwner, { shoeSaved ->
            if(shoeSaved){
                val action = ShoeDetailsFragmentDirections
                    .actionShoeDetailsFragmentToShoeListFragment()
                findNavController().navigate(action)
                viewModel.onShoeSaveComplete()
            }
        })

        viewModel.eventShoeCancel.observe(viewLifecycleOwner, { canceled ->
            if(canceled){
                val action = ShoeDetailsFragmentDirections
                    .actionShoeDetailsFragmentToShoeListFragment()
                findNavController().navigate(action)
                viewModel.onShoeCancelComplete()
            }
        })

        return binding.root
    }
}