package ru.iteco.fmhandroid;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.R.id.login_text_input_layout;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AndroidJUnit4.class)
public class Authorization {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule = new ActivityTestRule<>(AppActivity.class);

    String textLogin = "login2";

    public ViewInteraction getLoginField() {
        ViewInteraction loginField = onView(withId(login_text_input_layout));
        return loginField;
    }

    @Test
        public void autoTest() throws InterruptedException {
        Thread.sleep(5000);
        getLoginField().perform(typeText(textLogin), closeSoftKeyboard());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void autoTest2() throws InterruptedException {
        ViewInteraction loginField = onView(withId(login_text_input_layout));
        Thread.sleep(40000);
        loginField.perform(typeText("login2"), closeSoftKeyboard());
    }

    @Test
        public void clickBtn() throws InterruptedException {
        ViewInteraction btn = onView(withId(R.id.enter_button));
        Thread.sleep(40000);
        btn.perform(click()).check(matches(isDisplayed()));
    }
}

