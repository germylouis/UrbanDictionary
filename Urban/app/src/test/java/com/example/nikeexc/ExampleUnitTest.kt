package com.example.nikeexc

import com.example.nikeexc.network.repository.UrbanRepository
import com.example.nikeexc.viewmodel.UrbanViewModel
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var mvm: UrbanViewModel
    private lateinit var repo: UrbanRepository

    @Test
    fun searchForDog_getDog() {
        givenAFeedOfData()
        whenSearchForDog()
        thenResultContainsListogDogDefinitions()
    }

    private fun givenAFeedOfData() {
        repo = UrbanRepository()
        mvm = UrbanViewModel(repo)
    }

    private fun whenSearchForDog() {
        mvm.getWords("Dog")
    }

    private fun thenResultContainsListogDogDefinitions() {
        mvm.definitions.forEach {
            assertNotNull(it)
            assertTrue(it.definition.isNotEmpty())

        }
    }
}
