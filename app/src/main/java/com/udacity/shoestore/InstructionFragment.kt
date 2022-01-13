package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionBinding


class InstructionFragment : Fragment() {

    private val viewModel: ApplicationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentInstructionBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_instruction, container, false
        )

        binding.viewModel = viewModel

        viewModel.eventOnboardingFinish.observe(viewLifecycleOwner, { isFinished ->
            if (isFinished && findNavController().currentDestination?.id == R.id.instruction_destination) {
                findNavController().navigate(InstructionFragmentDirections.actionInstructionToShoeList())
            }
        })

        return binding.root
    }

}