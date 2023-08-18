package ru.iteco.fmhandroid.pom;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class EspressoBaseTest {
    public static void clickEnterButton(Integer resourceId) {
        onView(withId(resourceId)).perform(click()).check(matches(isDisplayed()));
    }
}
