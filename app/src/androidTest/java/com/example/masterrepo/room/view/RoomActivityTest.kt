package com.example.masterrepo.room.view

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.masterrepo.R
import com.example.masterrepo.room.adapter.RoomRecyclerViewAdapter
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RoomActivityTest{

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(RoomActivity::class.java)

    @Test//(expected = PerformException::class)
    fun insertionAndDeletion(){

        // Launching the room database Activity
        val scenario = ActivityScenario.launch(RoomActivity::class.java)
        // Performing click on the Add Entry Button
        Espresso.onView(withId(R.id.roomActivity_button_add)).perform(click())
        // Verifying if the dialog box to take the entry information is displayed
        Espresso.onView(withId(R.id.activityRoom_dialogBox)).check(matches(isDisplayed()))
        // Input a new entry in the database using the dialog box
        Espresso.onView(withId(R.id.activityRoom_edittext_fname)).perform(typeText("Linkin"))
        Espresso.onView(withId(R.id.activityRoom_edittext_lname)).perform(typeText("Park"))
        Espresso.onView(withId(R.id.activityRoom_edittext_age)).perform(typeText("66"))
        // Keeping the gender default to Male - M
        // Performing click on the Save button in the dialog box
        Espresso.onView(withId(R.id.roomActivity_button_dialog_save)).perform(click())

        // Verifying if the main recyclerview is displayed
        Espresso.onView(withId(R.id.roomActivity_recyclerView)).check(matches(isDisplayed()))

        // Verifying if the entry has been added to the database and is shown in the recyclerview
        Espresso.onView(withId(R.id.roomActivity_recyclerView))
            .perform(RecyclerViewActions.actionOnItem<RoomRecyclerViewAdapter.RoomViewHolder>(
                hasDescendant( withText("Linkin Park") ), click()))

        // Deleting the entry that was saved while running the test so that it is not available already when the tests is run again
        Espresso.onView(withId(R.id.roomActivity_recyclerView))
            .perform(RecyclerViewActions.actionOnItem<RoomRecyclerViewAdapter.RoomViewHolder>(
                hasDescendant(withText("Linkin Park")), clickChildViewWithId(R.id.roomActivity_recyclerView_row_delete_button))
            )

        // Confirm deletion by clicking on the 'Yes' button in the confirmation deletion box
        Espresso.onView(withText("Yes"))
            .check(matches(isDisplayed()))
            .perform(click());

        }

    // This function is used to perform action on a child view of a RecyclerView row
    private fun clickChildViewWithId(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }
            override fun getDescription(): String {
                return "Click on a child view with specific id."
            }
            override fun perform(
                uiController: UiController,
                view: View
            ) {
                val v = view.findViewById<View>(id)
                v.performClick()
            }
        }
    }
}