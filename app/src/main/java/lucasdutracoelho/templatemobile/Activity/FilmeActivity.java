package lucasdutracoelho.templatemobile.Activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import lucasdutracoelho.templatemobile.Enum.ImageLoaderType;
import lucasdutracoelho.templatemobile.Exception.ConexaoException;
import lucasdutracoelho.templatemobile.Manager.FilmeManager;
import lucasdutracoelho.templatemobile.Manager.FilmeManagerImpl;
import lucasdutracoelho.templatemobile.Model.Filme;
import lucasdutracoelho.templatemobile.R;
import lucasdutracoelho.templatemobile.Rest.FactoryOmdbApi;
import lucasdutracoelho.templatemobile.Rest.OmdbApi;
import lucasdutracoelho.templatemobile.Utils.Impl.FactoryImageLoader;
import lucasdutracoelho.templatemobile.Utils.Interfaces.ImageLoader;

@EActivity(R.layout.activity_filme)
public class FilmeActivity extends AppCompatActivity {

    private final String TAG = "FilmeActivity";

    @ViewById(R.id.image_poster_filme)
    SimpleDraweeView imageView;

    @ViewById(R.id.edtTitulo)
    EditText edtTitulo;

    @ViewById(R.id.edtAno)
    EditText edtAno;

    @ViewById(R.id.edtGenero)
    EditText edtGenero;

    @ViewById(R.id.edtDuracao)
    EditText edtDuracao;

    @ViewById(R.id.edtDescricao)
    EditText edtDescricao;

    @ViewById(R.id.input_layout_titulo)
    TextInputLayout validacaoTitulo;

    @ViewById(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @Bean(value = FilmeManagerImpl.class)
    FilmeManager filmeManager;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    Filme filme;

    ImageLoader imageLoader = FactoryImageLoader.getImageLoader(ImageLoaderType.FRESCO);

    @AfterViews
    public void setBackActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Filme");
        filme =(Filme) getIntent().getSerializableExtra("FILME");
        updateTela(filme);
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

    @Click(R.id.fab)
    protected void onClickPesquisar(View view){
        filmeManager.inserir(filme);
        Snackbar.make(coordinatorLayout, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        finish();

    }

    @UiThread
    protected void updateTela(Filme filme){
        if(filme!=null){
            imageLoader.showImage(imageView, filme.getUrlPoster());
            edtTitulo.setText(filme.getTitulo());
            edtAno.setText(filme.getAno());
            edtDuracao.setText(filme.getDuracao());
            edtGenero.setText(filme.getGenero());
            edtDescricao.setText(filme.getDescricao());
        }
    }


}
