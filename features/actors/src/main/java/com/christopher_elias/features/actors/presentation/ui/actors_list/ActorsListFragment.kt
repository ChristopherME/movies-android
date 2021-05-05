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
import com.christopher_elias.features.actors.presentation.ui.actors_list.adapter.ActorListAdapter
import com.christopher_elias.features.actors.presentation.ui.actors_list.state.ActorsListUiState
import com.christopher_elias.utils.consumeOnce
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/*
 * Created by Christopher Elias on 2/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
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
        binding.rvActors.adapter = ActorListAdapter(listener = ::onActorClicked)
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            actorListViewModel.uiState.collect { state ->
                renderUiState(state)
            }
        }
    }

    private fun renderUiState(state: ActorsListUiState) {
        with(state) {
            // Progress
            binding.progressBarActors.isVisible = isLoading

            // Bind actors.
            (binding.rvActors.adapter as ActorListAdapter)
                .submitList(actors)

            // Empty view
            binding.tvActorsEmpty.isVisible = !isLoading && actors.isEmpty()

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

    private fun onActorClicked(actor: ActorUi) {
        Timber.d("actor: $actor")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}