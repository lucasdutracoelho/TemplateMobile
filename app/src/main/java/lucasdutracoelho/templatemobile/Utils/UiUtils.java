package lucasdutracoelho.templatemobile.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;

/**
 * Created by lucas.coelho.dutra on 01/04/2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class UiUtils {

    @RootContext
    Context appCtx;

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showToast(CharSequence text) {
        Toast.makeText(appCtx, text, Toast.LENGTH_LONG).show();
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showToast(int textResId) {
        Toast.makeText(appCtx, textResId, Toast.LENGTH_LONG).show();
    }

    public ProgressDialog showProgressDialog(CharSequence title, CharSequence menssage, boolean indeterminate) {
        return ProgressDialog.show(appCtx, title, menssage, indeterminate);
    }

    public ProgressDialog showProgressDialog(int titleResId, int menssageResId, boolean indeterminate) {
        return ProgressDialog.show(appCtx, appCtx.getString(titleResId), appCtx.getString(menssageResId), indeterminate);
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void dismissDialog(ProgressDialog progressDialog){
        progressDialog.dismiss();
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showSnackBar(CoordinatorLayout coordinatorLayout, CharSequence text, int duration){
        Snackbar.make(coordinatorLayout, text, duration).show();
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showSnackBar(CoordinatorLayout coordinatorLayout, int textResId, int duration){
        Snackbar.make(coordinatorLayout, appCtx.getString(textResId), duration).show();
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showSnackBar(CoordinatorLayout coordinatorLayout, CharSequence text, int duration, CharSequence action, View.OnClickListener onClickListener){
        Snackbar.make(coordinatorLayout, text, duration)
                .setAction(action, onClickListener).show();
    }
    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void showSnackBar(CoordinatorLayout coordinatorLayout, CharSequence text, int duration, int actionResId, View.OnClickListener onClickListener){
        Snackbar.make(coordinatorLayout, text, duration)
                .setAction(appCtx.getString(actionResId), onClickListener).show();
    }
}
