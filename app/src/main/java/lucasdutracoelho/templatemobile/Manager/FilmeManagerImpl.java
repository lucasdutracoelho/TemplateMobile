package lucasdutracoelho.templatemobile.Manager;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import lucasdutracoelho.templatemobile.DAO.FilmeDAO;
import lucasdutracoelho.templatemobile.Enum.ImageLoaderType;
import lucasdutracoelho.templatemobile.Exception.ConexaoException;
import lucasdutracoelho.templatemobile.Model.Filme;
import lucasdutracoelho.templatemobile.Rest.FactoryOmdbApi;
import lucasdutracoelho.templatemobile.Rest.OmdbApi;
import lucasdutracoelho.templatemobile.Utils.ConexaoUtils;
import lucasdutracoelho.templatemobile.Utils.Impl.FactoryImageLoader;
import lucasdutracoelho.templatemobile.Utils.Interfaces.ImageLoader;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
@EBean(scope = EBean.Scope.Singleton)
public class FilmeManagerImpl implements FilmeManager{

    /**
     * Log
     */
    private final String TAG = "FilmeManagerImpl";

    OmdbApi omdbApi = FactoryOmdbApi.getInstance();

    ImageLoader imageLoader = FactoryImageLoader.getImageLoader(ImageLoaderType.FRESCO);

    @Bean
    FilmeDAO filmeDAO;

    @Override
    public Filme pesquisar(String nome, String ano, String plot) {
        if(ConexaoUtils.verificaConexao()){
            return omdbApi.getFilme(nome, ano, plot);
        }
        throw new ConexaoException("Sem conexão com a internet.");
    }

    @Override
    public void inserir(Filme filme) {
        filmeDAO.save(filme);
    }

    @Override
    public void deletar(Filme filme) {
        filmeDAO.delete(filme);
    }

    @Override
    public List<Filme> listarFilmes() {
        return filmeDAO.findAll();
    }
}
