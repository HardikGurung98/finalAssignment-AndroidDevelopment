package com.hardik.ekakshya

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.hardik.ekakshya.ui.auth.student.StudentLoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class LoginTest {

    @get:Rule
    val testRule = ActivityScenarioRule(StudentLoginActivity::class.java)

    @Test
    fun testLoginUI()
    {
        onView(withId(R.id.etStudentUsername))
                .perform(typeText("stajay"))


        Thread.sleep(2000)
        onView(withId(R.id.etStudentPassword))
                .perform(typeText("1530ajay"))

        Thread.sleep(2000)
        closeSoftKeyboard()

        onView(withId(R.id.btnStudentLogin))
                .perform(click())


        onView(withId(R.id.txtStudentCreateOne))
                .check(matches(isDisplayed()))
    }
}
