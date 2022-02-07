package com.alvin.to_doappwithtesting.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.filters.LargeTest
import com.alvin.to_doappwithtesting.R
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@LargeTest
class MainActivityTest {

    @get:Rule(order = 0)
    var hiltAndroidRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @get:Rule(order = 2)
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        activityScenarioRule.scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun addQuote() {
        onView(withId(R.id.addQuoteFloatingButton)).perform(click())

        onView(withId(R.id.editTextQuoteText)).perform(
            ViewActions.typeText("hlo"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.editTextQuoteAuthor)).perform(
            ViewActions.typeText("alvin"),
            ViewActions.closeSoftKeyboard()
        )
        onView(withId(R.id.buttonSaveQuote)).perform(click())

        onView(withText("hlo")).check(matches(isDisplayed()))

        onView(withText("alvin")).check(matches(isDisplayed()))

    }

}