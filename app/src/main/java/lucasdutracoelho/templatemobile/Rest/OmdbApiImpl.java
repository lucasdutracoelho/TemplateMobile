package lucasdutracoelho.templatemobile.Rest;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import lucasdutracoelho.templatemobile.Exception.ConexaoException;
import lucasdutracoelho.templatemobile.Exception.OmdbApiException;
import lucasdutracoelho.templatemobile.Model.Filme;
import lucasdutracoelho.templatemobile.Utils.Interfaces.HttpConnection;

/**
 * Created by lucas.coelho.dutra on 09/03/2016.
 */
public class OmdbApiImpl implements OmdbApi {

    private final String PARAM_NOME = "@Nome";
    private final String PARAM_ANO = "@Ano";
    private final String PARAM_PLOT = "@Plot";
    private final String EMPTY = "";
    private final String URL = "http://www.omdbapi.com/?t=".concat(PARAM_NOME).concat("&y=").concat(PARAM_ANO).concat("&plot=").concat(PARAM_PLOT).concat("&r=json");

    private HttpConnection httpConnection;
    Gson gson;

    protected OmdbApiImpl(HttpConnection httpConnection, Gson gson){
        this.httpConnection = httpConnection;
        this.gson = gson;
    }

    @Override
    public Filme getFilme(String name) {
        try {
            String searchUrl = createUrl(name, null, null);
            return Parse(searchUrl);
        } catch (IOException e) {
            Log.e(OmdbApiImpl.class.getName(), e.getMessage());
            e.printStackTrace();
            throw new ConexaoException("Sem conexão com a internet.");
        }catch (OmdbApiException omdbE){
            Log.e(OmdbApiImpl.class.getName(), omdbE.getMessage());
            omdbE.printStackTrace();
        }
        return null;
    }

    @Override
    public Filme getFilme(String name, String ano) {
        try {
            String searchUrl = createUrl(name, ano, null);
            return Parse(searchUrl);
        } catch (IOException e) {
            Log.e(OmdbApiImpl.class.getName(), e.getMessage());
            e.printStackTrace();
            throw new ConexaoException("Sem conexão com a internet.");
        }catch (OmdbApiException omdbE){
            Log.e(OmdbApiImpl.class.getName(), omdbE.getMessage());
            omdbE.printStackTrace();
        }
        return null;
    }

    @Override
    public Filme getFilme(String name, String ano, String plot) {
        try {
            String searchUrl = createUrl(name, ano, plot);
            return Parse(searchUrl);
        } catch (IOException e) {
            Log.e(OmdbApiImpl.class.getName(), e.getMessage());
            e.printStackTrace();
            throw new ConexaoException("Sem conexão com a internet.");
        }catch (OmdbApiException omdbE){
            Log.e(OmdbApiImpl.class.getName(), omdbE.getMessage());
            omdbE.printStackTrace();
        }
        return null;
    }



    private String createUrl(String name, String ano, String plot){
        if(StringUtils.isBlank(name)){
            throw new OmdbApiException("Nome é obrigatório");
        }
        String url = URL.replace(PARAM_NOME, name);

        if(StringUtils.isNotBlank(ano)){
            url = url.replace(PARAM_ANO, ano);
        }else {
            url = url.replace(PARAM_ANO, EMPTY);
        }

        if(StringUtils.isNotBlank(plot)){
            url = url.replace(PARAM_PLOT, plot);
        }else {
            url = url.replace(PARAM_PLOT, EMPTY);
        }

        return url;
    }

    private Filme Parse(String searchUrl) throws IOException {
        String json = httpConnection.getJson(searchUrl);
        Filme filme = gson.fromJson(json, Filme.class);
        if(Boolean.parseBoolean(filme.getResponse())){
            return filme;
        }
        return  null;
    }

}
