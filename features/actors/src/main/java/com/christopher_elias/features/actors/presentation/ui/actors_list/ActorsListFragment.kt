package com.christopher_elias.features.actors.presentation.ui.actors_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.christopher_elias.features.actors.R
import com.christopher_elias.features.actors.databinding.FragmentActorsListBinding
import com.christopher_elias.features.actors.presentation.model.ActorUi
import com.christopher_elias.features.actors.presentation.ui.actors_detail.ActorDetailFragment
import com.christopher_elias.features.actors.presentation.ui.actors_list.adapter.ActorListAdapter
import com.christopher_elias.features.actors.presentation.ui.actors_list.state.ActorsListUiState
import com.christopher_elias.navigation.extensions.replaceFragmentExt
import com.christopher_elias.navigation.transactions.TransactionAnimations
import com.christopher_elias.utils.consumeOnce
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

class ActorsListFragment : Fragment(R.layout.fragment_actors_list) {

    private var _binding: FragmentActorsListBinding? = null
    private val binding: FragmentActorsListBinding
        get() = _binding!!

    private val actorListViewModel: ActorsListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActorsListBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectUiState()
    }

    private fun initView() {
        binding.rvActors.adapter = ActorListAdapter(::navigateToActorDetail)
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            actorListViewModel.uiState.collect { state ->
                renderUiState(state)
            }
        }
    }

    private fun navigateToActorDetail(actor: ActorUi) {
        replaceFragmentExt(
            newFragment = ActorDetailFragment().apply {
                arguments = Bundle().apply { putParcelable("actor", actor) }
            },
            addToBackStack = true,
            transactionAnimations = TransactionAnimations.RIGHT_TO_LEFT
        )
    }

    fun renderUiState(state: ActorsListUiState) {
        with(state) {
            // Progress
            binding.progressBarActors.isVisible = isLoading

            // Bind actors.
            (binding.rvActors.adapter as ActorListAdapter)
                .submitList(actors)

            // Empty view
            binding.tvActorsEmpty.isVisible = !isLoading && actors.isEmpty()
            binding.rvActors.isVisible = actors.isNotEmpty()

            // Display error if any. Only once.
            error?.let {
                it.consumeOnce { failure ->
                    Toast.makeText(
                        requireContext(),
                        "$failure",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}