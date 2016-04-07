package lucasdutracoelho.templatemobile;

import android.content.Context;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import lucasdutracoelho.templatemobile.Activity.FilmeActivity_;
import lucasdutracoelho.templatemobile.DAO.FilmeDAO_;
import lucasdutracoelho.templatemobile.Model.Filme;

/**
 * Created by lucas.coelho.dutra on 31/03/2016.
 */
@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(RobolectricGradleTestRunner.class)
public class FilmeDAOTest extends BaseTest{

    private Context context;
    FilmeDAO_ dao;

    @Before
    public void setup() {
        context = Robolectric.setupActivity(FilmeActivity_.class).getApplicationContext();
        dao = (FilmeDAO_)configureDao(FilmeDAO_.class, context);
        resetSingleton(FilmeDAO_.class, INSTANCE);
    }

    @Test
    public void validaContexto_NotNull(){
        Assert.assertNotNull(context);
    }

    @Test
    public void validaFilmeDAO_NotNull(){
        Assert.assertNotNull(dao);
    }

    @Test
    public void insereUmFilme(){
        Filme filme = new Filme();
        filme.setAno("2016");
        filme.setDescricao("Filme de Ação");
        filme.setDuracao("126 min");
        filme.setGenero("Ação");
        filme.setTitulo("Batman");
        filme.setUrlPoster("https://kleberivo.files.wordpress.com/2011/07/poster_batman3.jpg");
        Filme filmeSalvo = dao.save(filme);
        Assert.assertNotNull(filme);
    }

    @Test
    public void listaFilme() {
        Filme filme = new Filme();
        filme.setAno("2016");
        filme.setDescricao("Filme de Ação");
        filme.setDuracao("126 min");
        filme.setGenero("Ação");
        filme.setTitulo("Batman");
        filme.setUrlPoster("https://kleberivo.files.wordpress.com/2011/07/poster_batman3.jpg");
        Filme filmeSalvo = dao.save(filme);

        Assert.assertTrue(dao.findAll().size() > 0);

    }

    @Test
    public void consultaFilme() {
        Filme filme = new Filme();
        filme.setAno("2016");
        filme.setDescricao("Filme de Ação");
        filme.setDuracao("126 min");
        filme.setGenero("Ação");
        filme.setTitulo("Batman");
        filme.setUrlPoster("https://kleberivo.files.wordpress.com/2011/07/poster_batman3.jpg");
        Filme filmeSalvo = dao.save(filme);

        Filme filmeConsulta = dao.findById(filmeSalvo.getId());

        Assert.assertTrue(filmeSalvo.getId().equals(filmeConsulta.getId()));

    }
}
