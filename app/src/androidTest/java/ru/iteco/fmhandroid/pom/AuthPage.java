package ru.iteco.fmhandroid.pom;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ViewAction.waitDisplayed;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Matchers;
import org.junit.Before;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.ActivityTestRule;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class AuthPage extends ActivityTestRule {

    private View decorView;
    @Before
    public void setUp() {
        activityTestRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }
    private final ViewInteraction loginField = onView(withHint("Логин"));
    private final ViewInteraction passwordField = onView(withHint("Пароль"));
    private final ViewInteraction exitBtn = onView(
            allOf(withId(R.id.authorization_image_button), withContentDescription("Авторизация"),
                    isDisplayed()));
    private final int loginFieldId = R.id.login_text_input_layout;
    private final int passwordFieldId = R.id.password_text_input_layout;
    private final int btnEnterId = R.id.enter_button;
    private final int exitProfileBtnId = R.id.authorization_image_button;
    private final int mainTitleId = R.id.container_custom_app_bar_include_on_fragment_main;
    private final int authTitleId = R.id.nav_host_fragment;

    public void loginPassword(String login, String password) {
        Allure.step("Зполнение полей логин и пароль с id: " + loginFieldId + ", " + passwordFieldId);
        onView(isRoot()).perform(waitDisplayed(loginFieldId, 10000));
        loginField.perform(replaceText(login));
        onView(isRoot()).perform(waitDisplayed(passwordFieldId, 10000));
        passwordField.perform(replaceText(password));
    }

    public void toastErrMess(String errMess) {
        Allure.step("Ошибка" + errMess);
        onView(withText(errMess))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .check(matches(isDisplayed()));
    }

    public void checkMainTitle() {
        Allure.step("Проверка отображения главного меню с id: " + mainTitleId);
        onView(withId(mainTitleId)).check(matches(isDisplayed()));
    }


    public void btnEnterClick() {
        Allure.step("Клик по кнопке с id: " + btnEnterId);
        onView(withId(btnEnterId)).perform(click());
    }

    public void waitMainTitle() {
        Allure.step("Ожидание прогрузки главного меню с id: " + mainTitleId);
        onView(isRoot()).perform(waitDisplayed(mainTitleId, 2000));
    }
    public void exitProfile() {
        Allure.step("Выход из профиля");
        onView(isRoot()).perform(waitDisplayed(exitProfileBtnId, 10000));
        exitBtn.perform(click());
        onView(withText("Выйти"))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .perform(click());
    }

    public void checkAuthFragment() {
        Allure.step("Проверка страницы авторизации");
        onView(isRoot()).perform(waitDisplayed(authTitleId, 10000));
        onView(withId(btnEnterId)).check(matches(isDisplayed()));
    }
}
