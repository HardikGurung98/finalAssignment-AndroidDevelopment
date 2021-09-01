package com.hardik.ekakshya

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.hardik.ekakshya.ui.auth.student.StudentRegisterActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class RegisterTest {

    @get:Rule
    val testRule = ActivityScenarioRule(StudentRegisterActivity::class.java)

    @Test
    fun testRegisterUI()
    {
        Espresso.onView(ViewMatchers.withId(R.id.etStudentSetUsername))
                .perform(ViewActions.typeText("stajay"))


        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.etStudentSetEmail))
                .perform(ViewActions.typeText("ajay@gmail.com"))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.etStudentSetPassword))
                .perform(ViewActions.typeText("1530ajay"))

        Thread.sleep(2000)
        Espresso.onView(ViewMatchers.withId(R.id.etStudentConfirmSetPassword))
                .perform(ViewActions.typeText("1530ajay"))

        Thread.sleep(2000)
        Espresso.closeSoftKeyboard()

        Espresso.onView(ViewMatchers.withId(R.id.btnStudentRegister))
                .perform(ViewActions.click())


        Espresso.onView(ViewMatchers.withId(R.id.txtStudentLogin))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}