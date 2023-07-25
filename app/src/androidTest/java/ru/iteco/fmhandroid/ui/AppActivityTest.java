package ru.iteco.fmhandroid.ui;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.LayoutAssertions.noOverlaps;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import ru.iteco.fmhandroid.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;

import junit.framework.AssertionFailedError;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppActivityTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void appActivityTest() throws InterruptedException {
        Thread.sleep(5000);
        ViewInteraction editText = onView(withId(R.id.login_text_input_layout)).check(matches(isCompletelyDisplayed()));
        editText.perform(replaceText("login2"), closeSoftKeyboard());
        ViewInteraction textInputEditText2 = onView(withId(R.id.password_text_input_layout)).perform(scrollTo()).check(matches(isCompletelyDisplayed()));
        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());
        pressBack();

        ViewInteraction materialButton = onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
        materialButton.perform(click());

        ViewInteraction appCompatImageButton = onView(withId(R.id.main_menu_image_button)).check(matches(isDisplayed()));

        appCompatImageButton.perform(click());
        }
}

