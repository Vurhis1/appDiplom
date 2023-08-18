package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.endsWith;
import static ru.iteco.fmhandroid.R.id.nav_host_fragment;
import static ru.iteco.fmhandroid.ViewAction.waitDisplayed;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@RunWith(AndroidJUnit4.class)
public class Authorization {

    @Rule
    public ActivityTestRule<AppActivity> activityTestRule = new ActivityTestRule<>(AppActivity.class);


    String textLogin = "login2";
    String textPassword = "password2";
    String errorText = "Логин и пароль не могут быть пустыми";
    private final int loginFieldId = R.id.login_text_input_layout;
    private final int passwordFieldId = R.id.password_text_input_layout;
    private final int btnEnter = R.id.enter_button;
    private final int errMesId = R.string.empty_login_or_password;


    public void waitAuthorizationPage() {
        onView(isRoot()).perform(waitDisplayed(loginFieldId, 10000));
        onView(isRoot()).perform(waitDisplayed(passwordFieldId, 10000));
    }

    public void waitErrorMessange() {
        onView(isRoot()).perform(waitDisplayed(errMesId, 10000));
    }

    public void waitNavHostFragment() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_custom_app_bar_include_on_fragment_main, 5000));
    }

    private final ViewInteraction loginField = onView(withHint("Логин"));
    private final ViewInteraction passwordField = onView(withHint("Пароль"));
    @Test
        public void validAuthTest() throws InterruptedException {
        waitAuthorizationPage();
        //Thread.sleep(5000);
        loginField.perform(replaceText(textLogin));
        //Thread.sleep(5000);
        passwordField.perform(replaceText(textPassword));
        onView(withId(btnEnter)).perform(click());
        waitNavHostFragment();
        Thread.sleep(5000);
        onView(withId(R.id.container_custom_app_bar_include_on_fragment_main)).check(matches(isDisplayed()));
    }

    @Test
        public void loginFieldIsEmptyTest() throws InterruptedException {
        waitAuthorizationPage();
        Thread.sleep(5000);
        loginField.perform(replaceText(textLogin));
        onView(withId(btnEnter)).perform(click());
        waitErrorMessange();
        onView(withId(errMesId)).check(matches(isDisplayed()));
    }
}

