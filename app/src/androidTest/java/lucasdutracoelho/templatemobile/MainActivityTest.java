package lucasdutracoelho.templatemobile;

import android.app.KeyguardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import lucasdutracoelho.templatemobile.Activity.MainActivity_;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by lucas.coelho.dutra on 31/03/2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    private PowerManager.WakeLock mWakeLock;

    @Rule
    public ActivityTestRule<MainActivity_> mActivityRule = new ActivityTestRule<>(
            MainActivity_.class);

    @Before
    public void initialize(){
        // Unlock the screen
        KeyguardManager keyguard = (KeyguardManager) mActivityRule.getActivity().getSystemService(Context.KEYGUARD_SERVICE);
        keyguard.newKeyguardLock(getClass().getSimpleName()).disableKeyguard();

        // Start a wake lock
        PowerManager power = (PowerManager) mActivityRule.getActivity().getSystemService(Context.POWER_SERVICE);
        mWakeLock = power.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, getClass().getSimpleName());
        mWakeLock.acquire();
    }

    @Test
    public void abreTelaPesquisa(){
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.btnPesquisar)).check(matches(isDisplayed()));
    }

    @Test
    public void PesquisaFilme() {
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.edtTitulo)).perform(typeText("Spider-Man")).perform(closeSoftKeyboard());
        onView(withId(R.id.btnPesquisar)).perform(click());
        //Se tiver internet, abre a tela de filmes
        if(isOnline()){
            onView(withId(R.id.edtDescricao)).check(matches(isDisplayed()));
        }else{
            //continua na mesma tela e exibe snackbar
            onView(withId(R.id.btnPesquisar)).check(matches(isDisplayed()));
        }
    }

    @Test
    public void IncluirFilme() throws InterruptedException {
        //int quantidadeAtual = mActivityRule.getActivity().getAdapterBase().getItemCount();
        int quantidadeAtual = mActivityRule.getActivity().getFilmeManager().listarFilmes().size();
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.edtTitulo)).perform(typeText("Matrix")).perform(closeSoftKeyboard());
        onView(withId(R.id.btnPesquisar)).perform(click());
        //Se tiver internet, abre a tela de filmes
        if(isOnline()){
            onView(withId(R.id.fab)).perform(click());
            mActivityRule.getActivity().updateList();
            //Thread.sleep(1000);
            //Assert.assertTrue(mActivityRule.getActivity().getAdapterBase().getItemCount() > quantidadeAtual);
            Assert.assertTrue(mActivityRule.getActivity().getFilmeManager().listarFilmes().size() > quantidadeAtual);
        }else{
            //continua na mesma tela e exibe snackbar
            onView(withId(R.id.btnPesquisar)).check(matches(isDisplayed()));
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mActivityRule.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
