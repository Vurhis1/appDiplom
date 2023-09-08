package ru.iteco.fmhandroid.ui;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ActivityTestRule;
import ru.iteco.fmhandroid.pom.AuthPage;

@RunWith(AllureAndroidJUnit4.class)
public class Authorization extends AuthPage {
    @Test
        public void oneOfFieldIsEmptyTest() {
        loginPassword("login2", "");
        btnEnterClick();
        toastErrMess("Логин и пароль не могут быть пустыми");
    }

    @Test
        public void wrongLoginOrPasswordTest() {
        loginPassword("login", "password");
        btnEnterClick();
        toastErrMess("Неверный логин или пароль");
    }

    @Test
    public void validAuthTest() {
        loginPassword("login2", "password2");
        btnEnterClick();
        waitMainTitle();
        checkMainTitle();
    }
}

