package lucasdutracoelho.templatemobile.Rest;

import org.androidannotations.annotations.rest.Get;

import lucasdutracoelho.templatemobile.Model.Filme;

/**
 * Created by lucas.coelho.dutra on 09/03/2016.
 */
public interface OmdbApi {

    public static final String PLOT_FULL = "full";
    public static final String SHORT_FULL = "short";

    Filme getFilme(String name);
    Filme getFilme(String name, String ano);
    Filme getFilme(String name, String ano, String plot);
}
