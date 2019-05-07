package com.babykumari.expressolearning;

import android.content.Intent;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.intent.Intents;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @BeforeClass
    public static void setup() {
        Intents.init();
    }

    @Test
    public void checkIfUserNameIsEmpty(){
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.edt_username))
                .check(matches(hasErrorText("Invalid User Name")));

    }

    @Test
    public void checkIfUserNameIsInvalid(){
        onView(withId(R.id.edt_username)).perform(typeText("baby"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.edt_username))
                .check(matches(hasErrorText("Invalid User Name")));
    }

    @Test
    public void invalidPassword(){
        onView(withId(R.id.edt_username)).perform(typeText("admin"));
        onView(withId(R.id.edt_password)).perform(typeText("1234"));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.edt_password)).check(matches(hasErrorText("Invalid Password")));
    }

    @Test
    public void emptyPassword(){
        onView(withId(R.id.edt_username)).perform(typeText("admin"));
        onView(withId(R.id.edt_password)).perform(typeText(""));
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.edt_password)).check(matches(hasErrorText("Invalid Password")));
    }

    @Test
    public void openHomeActivity() {
        onView(withId(R.id.edt_username)).perform(typeText("admin"));
        onView(withId(R.id.edt_password)).perform(typeText("admin@123"));
        onView(withId(R.id.btn_login)).perform(click());

        intended(hasComponent(HomeActivity.class.getName()));
        assertTrue(loginActivityActivityTestRule.getActivity().isFinishing());
    }
}
