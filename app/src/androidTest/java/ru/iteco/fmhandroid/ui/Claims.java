package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;

import static ru.iteco.fmhandroid.ViewAction.waitDisplayed;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Map;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.pom.NavigationPage;

public class Claims extends NavigationPage {
    @Before
    public void authorization() {
        loginPassword("login2", "password2");
        btnEnterClick();
    }

    @Test
    public void addClaim() throws InterruptedException {
        waitMainTitle();
        selectClaimPage();
        clickAddNewClaimBtn();
        titleDescription("Тестовая версия Агафонов Илья", "Димпломная работа");
        getImplemented("Ivanov Ivan Ivanovich");
        getNowDate();
        getNowTime();
        clickSaveClaimBtn();
        waitClaimsTitle();
        ViewInteraction vi = onView(withContentDescription("Тестовая версия Агафонов Илья"));
        vi.check(matches(isDisplayed()));
    }

    @Test
    public void addClaim2() {
        waitMainTitle();
        selectClaimPage();
        waitClaimsTitle();
        onView(isRoot()).perform(waitDisplayed(R.id.description_material_text_view, 10000));
        onView(withId(R.id.description_material_text_view))
                .check(matches(textViewHasValue()));
    }
}
