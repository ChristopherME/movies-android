package com.christopher_elias.features.actors

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.christopher_elias.features.actors.presentation.model.ActorUi
import com.christopher_elias.features.actors.presentation.ui.actors_list.ActorsListFragment
import com.christopher_elias.features.actors.presentation.ui.actors_list.ActorsListViewModel
import com.christopher_elias.features.actors.presentation.ui.actors_list.state.ActorsListUiState
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Lima, Peru.
 */

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ActorListFragmentInstrumentedTest : AutoCloseKoinTest() {

    private lateinit var scenario: FragmentScenario<ActorsListFragment>
    private val actorsViewModel: ActorsListViewModel = mockk(relaxed = true)

    @Before
    fun setUp() {
        startKoin {
            modules(
                module { viewModel { actorsViewModel } }
            )
        }
        scenario = launchFragmentInContainer(themeResId = R.style.Movies_DayNight)
        scenario.moveToState(newState = Lifecycle.State.STARTED)
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Loading_State() {
        scenario.onFragment { fragment ->
            // Loading State
            fragment.renderUiState(
                state = ActorsListUiState(
                    isLoading = true,
                    actors = emptyList(),
                    error = null
                )
            )
        }
        onView(withId(R.id.tvActorsFeatureTitle)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.progressBarActors)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.tvActorsEmpty)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.rvActors)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Empty_Actors_List_State() {
        scenario.onFragment { fragment ->
            // Empty List
            fragment.renderUiState(
                state = ActorsListUiState(
                    isLoading = false,
                    actors = emptyList(),
                    error = null
                )
            )
        }
        onView(withId(R.id.tvActorsFeatureTitle)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.progressBarActors)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tvActorsEmpty)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.rvActors)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun assert_Actors_List_Fragment_Render_The_UI_According_The_Actors_List_State() {
        scenario.onFragment { fragment ->
            // List with 'items'
            fragment.renderUiState(
                state = ActorsListUiState(
                    isLoading = false,
                    actors = listOf(
                        ActorUi(
                            id = 1,
                            "Christopher Elias",
                            popularity = 10.0,
                            profilePath = null,
                            moviesNames = "...",
                            knownFor = emptyList()
                        )
                    )
                )
            )
        }
        onView(withId(R.id.tvActorsFeatureTitle)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.progressBarActors)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tvActorsEmpty)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.rvActors)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }
}