package com.alvin.to_doappwithtesting.ui.addAndEdit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.alvin.to_doappwithtesting.R
import com.alvin.to_doappwithtesting.ui.viewmodel.AddAndEditViewModel
import com.google.common.truth.Truth
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@LargeTest
class AddEditActivityTest {

    @get:Rule(order = 0)
    var hiltAndroidRule=HiltAndroidRule(this)

    @get:Rule(order = 1)
    var activityScenarioRule = activityScenarioRule<AddEditActivity>()

    @get:Rule(order = 2)
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var addAndEditViewModel: AddAndEditViewModel

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
        addAndEditViewModel = AddAndEditViewModel()
    }

    @Test
    fun addQuote() {
        onView(withId(R.id.editTextQuoteText)).perform(typeText("hlo"), closeSoftKeyboard())
        onView(withId(R.id.editTextQuoteAuthor)).perform(typeText("alvin"), closeSoftKeyboard())
        onView(withId(R.id.buttonSaveQuote)).perform(click())

        val result = addAndEditViewModel.validation(
           "hlo",
           "alvin"
        )
        Truth.assertThat(result).isEqualTo("")
    }
}