package com.minimumpath.ui;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.minimumpath.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void enterTestData() {
        onView(allOf(withId(R.id.data_input_edit_text), withText("5"), isDisplayed())).perform(replaceText("6 7 8 9 10 11 3 5"), closeSoftKeyboard());
        onView(allOf(withId(R.id.find_button), withText("Find Minimum Path"), isDisplayed())).perform(click());
    }
    @Test
    public void enterSampleTestData() {
        onView(allOf(withId(R.id.data_input_edit_text), isDisplayed())).perform(replaceText("5"), closeSoftKeyboard());
        onView(allOf(withId(R.id.example_matrix_1), withText("Example 1"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.data_input_edit_text), withText("5"), isDisplayed())).perform(replaceText("5 2 3 4 5 1 2 3 "), closeSoftKeyboard());
        onView(allOf(withId(R.id.find_button), withText("Find Minimum Path"), isDisplayed())).perform(click());
    }
    @Test
    public void clickOnSampleExample() {
        onView(allOf(withId(R.id.example_matrix_1), withText("Example 1"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.find_button), withText("Find Minimum Path"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.clear_data), withText("Clear Data"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.example_matrix_2), withText("Example 2"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.find_button), withText("Find Minimum Path"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.clear_data), withText("Clear Data"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.example_matrix_3), withText("Example 3"), isDisplayed())).perform(click());
        onView(allOf(withId(R.id.find_button), withText("Find Minimum Path"), isDisplayed())).perform(click());
    }


}
