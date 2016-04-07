package lucasdutracoelho.templatemobile;

import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import lucasdutracoelho.templatemobile.Activity.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by lucas.coelho.dutra on 31/03/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule = new ActivityTestRule<>(
            MainActivity_.class);

    @Test
    public void abreTelaConsulta(){
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.btnPesquisar)).check(matches(isDisplayed()));
    }
}
