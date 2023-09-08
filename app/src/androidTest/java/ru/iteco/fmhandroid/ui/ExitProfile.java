package ru.iteco.fmhandroid.ui;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.pom.AuthPage;

@RunWith(AllureAndroidJUnit4.class)
public class ExitProfile extends AuthPage {

    @Test
    public void exitProfileTest() {
        loginPassword("login2", "password2");
        btnEnterClick();
        waitMainTitle();
        exitProfile();
        checkAuthFragment();
    }
}
