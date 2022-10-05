package com.example.masterrepo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test

class MainActivityTest{

    @Test
    fun initialTest(){

        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(withId(R.id.recyclerViewMainActivity)).check(
            ViewAssertions.matches(
                isDisplayed()
            )
        )
    }
}