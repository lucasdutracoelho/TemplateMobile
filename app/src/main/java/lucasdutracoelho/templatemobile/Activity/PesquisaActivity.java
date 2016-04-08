package lucasdutracoelho.templatemobile.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.apache.commons.lang3.StringUtils;

import lucasdutracoelho.templatemobile.Manager.FilmeManager;
import lucasdutracoelho.templatemobile.Manager.FilmeManagerImpl;
import lucasdutracoelho.templatemobile.Model.Filme;
import lucasdutracoelho.templatemobile.R;
import lucasdutracoelho.templatemobile.Utils.Constantes;
import lucasdutracoelho.templatemobile.Utils.UiUtils;

@EActivity(R.layout.activity_pesquisa)
public class PesquisaActivity extends AppCompatActivity {

    @ViewById(R.id.edtTitulo)
    EditText edtTitulo;

    @ViewById(R.id.edtAno)
    EditText edtAno;

    @ViewById(R.id.input_layout_titulo)
    TextInputLayout inputLayoutTitulo;

    @ViewById(R.id.input_layout_ano)
    TextInputLayout inputLayoutAno;

    @ViewById(R.id.btnPesquisar)
    Button btnPesquisar;

    @ViewById(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Bean(value = FilmeManagerImpl.class)
    FilmeManager filmeManager;

    ProgressDialog progressDialog;
    Filme filme;

    @Bean
    UiUtils uiUtils;


    @AfterViews
    public void setBackActionBar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pesquisa");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed in the action bar.
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validate(){
        if(StringUtils.isBlank(edtTitulo.getText())){
            inputLayoutTitulo.setErrorEnabled(true);
            inputLayoutTitulo.setError("Título é obrigatório.");
            return false;
        }
        return true;
    }

    @Click(R.id.btnPesquisar)
    protected void onClickPesquisar(){
        if(validate()){
            progressDialog = ProgressDialog.show(this, "Aguarde", "Pesquisando...", true);
            pesquisar();
        }
    }

    @Background
    protected void pesquisar(){
        try{
            filme = filmeManager.pesquisar(edtTitulo.getText().toString(), edtAno.getText().toString(), null);
            if(filme!=null){
                exibeFilme(filme);
            }else {
                uiUtils.showSnackBar(coordinatorLayout, "Filme não encontrado!", Snackbar.LENGTH_SHORT);
            }
        }catch (Exception e){
            uiUtils.showToast(e.getMessage());
        }finally {
            uiUtils.dismissDialog(progressDialog);
        }
    }

    @UiThread
    protected void exibeFilme(Filme filme){
        try{
            progressDialog.dismiss();
            Intent intent = new Intent(this, FilmeActivity_.class);
            intent.putExtra(Constantes.FILME_SERIALIZE, filme);
            startActivity(intent);
        }catch (Exception e){
            uiUtils.showToast(e.getMessage());
        }
    }
}
