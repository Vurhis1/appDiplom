package ru.iteco.fmhandroid.pom;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.internal.util.Checks.checkNotNull;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ViewAction.waitDisplayed;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

public class NavigationPage extends AuthPage {
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
    private final ViewInteraction titleField = onView(withHint("Тема"));
    private final ViewInteraction descriptionField = onView(withHint("Описание"));
    private final ViewInteraction implementedFieldClaim = onView(withHint("Исполнитель"));
    private final ViewInteraction saveClaimBtn = onView(withHint("Сохранить"));
    private final ViewInteraction mainMenuBtn = onView(
            allOf(withId(R.id.main_menu_image_button), withContentDescription("Главное меню"),
                    isDisplayed()));
    private final int mainMenuBtnId = R.id.main_menu_image_button;
    private final int addNewClaimBtnId = R.id.add_new_claim_material_button;
    private final int titleFieldId = R.id.title_edit_text;
    private final int descriptionFieldId = R.id.description_edit_text;
    private final int saveClaimBtnId = R.id.save_button;
    private final int dateFieldClaimId = R.id.date_in_plan_text_input_edit_text;
    private final int timeFieldClaimId = R.id.time_in_plan_text_input_edit_text;

    public final int claimsTitleId = R.id.claim_list_swipe_refresh;
    public void selectClaimPage() {
        Allure.step("Открытие страницы с заявками");
        onView(isRoot()).perform(waitDisplayed(mainMenuBtnId, 10000));
        mainMenuBtn.perform(click());
        onView(withText("Заявки"))
                .inRoot(withDecorView(Matchers.not(decorView)))
                .perform(click());
    }

    public void clickAddNewClaimBtn() {
        onView(withId(addNewClaimBtnId)).perform(click());
    }

    public void waitClaimsTitle() {
        onView(isRoot()).perform(waitDisplayed(claimsTitleId, 2000));
    }

    public void clickSaveClaimBtn() {
        onView(isRoot()).perform(waitDisplayed(saveClaimBtnId, 10000));
        onView(withId(saveClaimBtnId)).check(matches(isCompletelyDisplayed()));
        onView(withId(saveClaimBtnId)).perform(click());
    }

    public void clickSave() {
        onView(withId(saveClaimBtnId)).perform(click());
    }

    public void titleDescription(String title, String description) {
        Allure.step("Зполнение полей тема и описание с id: " + titleFieldId + ", " + descriptionFieldId);
        onView(isRoot()).perform(waitDisplayed(titleFieldId, 10000));
        titleField.perform(replaceText(title));
        onView(isRoot()).perform(waitDisplayed(descriptionFieldId, 10000));
        descriptionField.perform(replaceText(description));
    }
    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    public void getNowDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy");
        onView(withId(dateFieldClaimId)).perform(replaceText(formatForDateNow.format(dateNow)));
    }

    public void getNowTime() {
        onView(withId(timeFieldClaimId)).perform(replaceText(getCurrentTimeStamp()));
    }

    public void getImplemented(String name) {
        implementedFieldClaim.perform(replaceText(name));
    }
}




