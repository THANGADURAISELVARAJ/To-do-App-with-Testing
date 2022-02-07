package com.alvin.to_doappwithtesting.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddAndEditViewModelTest {

    private lateinit var addAndEditViewModel: AddAndEditViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        addAndEditViewModel = AddAndEditViewModel()
    }

    @Test
    fun `quote text is empty return false`() {
        val result = addAndEditViewModel.validation("", "")
        assertThat(result).isEqualTo("Pls Enter quote text")
    }

    @Test
    fun `quote text is empty return true`() {
        val result = addAndEditViewModel.validation("alvin", "alvin")
        assertThat(result).isEqualTo("")
    }

    @Test
    fun `quote text is empty more than 30 char`() {
        val result = addAndEditViewModel.validation("hiihihihihihihihihihihhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", "")
        assertThat(result).isEqualTo("Pls Enter quote text")
    }

}