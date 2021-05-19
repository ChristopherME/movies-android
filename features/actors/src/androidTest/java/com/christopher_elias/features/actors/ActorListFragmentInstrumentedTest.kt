package com.christopher_elias.features.actors

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.christopher_elias.common.models.mapper.MovieMapper
import com.christopher_elias.common.models.mapper.MovieMapperImpl
import com.christopher_elias.features.actors.data.data_source.ActorsRemoteDataSource
import com.christopher_elias.features.actors.data.repository.ActorsRepositoryImpl
import com.christopher_elias.features.actors.data_source.models.ActorsResponse
import com.christopher_elias.features.actors.data_source.remote.ActorsRemoteDataSourceImpl
import com.christopher_elias.features.actors.data_source.remote.retrofit.ActorsService
import com.christopher_elias.features.actors.domain.repository.ActorsRepository
import com.christopher_elias.features.actors.mapper.ActorsMapper
import com.christopher_elias.features.actors.mapper.ActorsMapperImpl
import com.christopher_elias.features.actors.presentation.ui.actors_list.ActorsListFragment
import com.christopher_elias.features.actors.presentation.ui.actors_list.ActorsListViewModel
import com.christopher_elias.network.middleware.NetworkMiddleware
import com.christopher_elias.network.middleware.provider.MiddlewareProvider
import com.christopher_elias.network.models.base.ResponseItems
import com.christopher_elias.test_shared.middleware.DefaultTestNetworkMiddleware
import com.christopher_elias.test_shared.network.DefaultRemoteConfig
import com.christopher_elias.utils.resource_provider.ResourceProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest

/*
 * Created by Christopher Elias on 14/05/2021
 * christopher.mike.96@gmail.com
 *
 * Loop Ideas
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
    private val testDispatcher = TestCoroutineDispatcher()
    private val remoteErrorAdapter = DefaultRemoteConfig.provideRemoteErrorAdapter()

    @Before
    fun setUp() {
        initDi()
        scenario = launchFragmentInContainer(themeResId = R.style.Movies_DayNight)
    }

    private fun initDi() {
        val appModuleMocked = module {
            single(named("TMDB_KEY")) { "" }
            single<ResourceProvider> { MockResourceProviderImpl() }
        }
        val actorsFeatureModuleMocked = module {
            //TODO: Fix the mockks in the koin modules,
            // with out them we can't have more flexible InstrumentedTests
            // The MiddlewareProvider & ActorsService Should have to be mocked.
            single<MiddlewareProvider> { MockedMiddlewareProvider() }
            single<ActorsService> { MockedActorService() }
            // Remote data source
            factory<ActorsRemoteDataSource> {
                ActorsRemoteDataSourceImpl(
                    middlewareProvider = get(),
                    ioDispatcher = testDispatcher,
                    errorAdapter = remoteErrorAdapter,
                    actorsService = get()
                )
            }

            // Mapper
            factory<MovieMapper> { MovieMapperImpl(defaultDispatcher = testDispatcher) }

            factory<ActorsMapper> {
                ActorsMapperImpl(
                    defaultDispatcher = testDispatcher,
                    movieMapper = get(),
                    resourceProvider = get()
                )
            }

            factory<ActorsRepository> {
                ActorsRepositoryImpl(
                    remoteDataSource = get(),
                    mapper = get()
                )
            }

            viewModel { ActorsListViewModel(actorsRepository = get(), mapper = get()) }
        }
        startKoin {
            modules(
                appModuleMocked,
                actorsFeatureModuleMocked
            )
        }
    }

    // TODO:
    //  1- Delay the execution of the items in order to verify the loading screen
    //  2- Return the items successfully in order to verify the success screen. (With Actors and with no Actors)
    //  3- Return a failure in order to verify the failure screen
    @Test
    fun assert_Actors_List_Fragment_Show_Correct_Views_When_Actors_List_Is_Not_Empty() {
        // For now everything is going to be loaded as is stated in the "mocked" classes from bellow.
        // So this is going to test after the view is started and therefore has return all the items.
        scenario.moveToState(newState = Lifecycle.State.STARTED)
        onView(withId(R.id.progressBarActors)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.tvActorsEmpty)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.rvActors)).check(matches(isDisplayed()))
    }
}

class MockedActorService : ActorsService {
    override suspend fun getActors(language: String, page: Int): ResponseItems<ActorsResponse> {
        return ResponseItems(
            listOf(
                ActorsResponse(
                    id = 1,
                    name = "",
                    popularity = 2.0,
                    profilePath = null,
                    knownFor = emptyList()
                )
            )
        )
    }
}

class MockedMiddlewareProvider : MiddlewareProvider {
    override fun getAll(): List<NetworkMiddleware> {
        return listOf(
            DefaultTestNetworkMiddleware(
                isMiddlewareValid = true
            )
        )
    }

}