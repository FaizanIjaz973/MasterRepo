package com.example.masterrepo.retrofit.view

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example.masterrepo.R
import com.example.masterrepo.util.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class RetrofitActivityTest{

    @Before
    fun registerIdlingResource(){
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
    }

    @After
    fun unregisterIdlingResource(){
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }
    @Test
    fun isButtonAndTextViewDisplayedOnStartup(){
        ActivityScenario.launch(RetrofitActivity::class.java)

        onView(withId(R.id.retrofitActivity_ButtonGetCatFact)).check(matches(isDisplayed()))
        onView(withId(R.id.retrofitActivity_textViewCatFact)).check(matches(isDisplayed()))
        // Verify the textview contains empty string
        onView(withId(R.id.retrofitActivity_textViewCatFact)).check(matches(withText("")))
        // Loading bar should not be visible on the startup
        onView(withId(R.id.retrofitActivity_loadingBar)).check(matches(not(isDisplayed())))
    }

    @Test
    fun doesApiCallReturnSuccessful(){
        ActivityScenario.launch(RetrofitActivity::class.java)

        onView(withId(R.id.retrofitActivity_textViewCatFact)).check(matches(withText("")))
        // Make the network call
        onView(withId(R.id.retrofitActivity_ButtonGetCatFact)).perform(click())
        // Since we don't know exactly what string will be fetched from the API, we will verify that the textview is not empty anymore.
        onView(withId(R.id.retrofitActivity_textViewCatFact)).check(matches(not(withText(""))))
    }

}