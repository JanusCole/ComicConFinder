package com.example.janus.confinder;

import static androidx.test.espresso.Espresso.onView;

import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

@RunWith(AndroidJUnit4.class)
public class ConventionFinderActivityTest {

    @Rule
    public ActivityTestRule<ConventionMapperActivity> mActivityRule = new ActivityTestRule<>(
            ConventionMapperActivity.class);

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testMainActivityExists() throws Exception {
        Assert.assertNotNull(mActivityRule.getActivity());

    }

/*    @Test
    public void testFirstMessage() throws Exception {

        onView(withText("Press START to Begin Con Search"))
                .inRoot(isDialog())
                .check(matches(isDisplayed()))
                .perform(click());

        onView(withId(R.id.searchButton_AlertDialog)).perform(click());

    }*/

    @After
    public void tearDown() throws Exception {
    }
}