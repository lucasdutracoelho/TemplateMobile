package lucasdutracoelho.templatemobile.Rest;

import com.google.gson.Gson;

import lucasdutracoelho.templatemobile.Rest.OmdbApi;
import lucasdutracoelho.templatemobile.Rest.OmdbApiImpl;
import lucasdutracoelho.templatemobile.Utils.Impl.OkHttpImpl;

/**
 * Created by lucas.coelho.dutra on 10/03/2016.
 */
public class FactoryOmdbApi {

    private static OmdbApi omdbApi;

    public static OmdbApi getInstance(){

        if(omdbApi == null){
            omdbApi = new OmdbApiImpl(new OkHttpImpl(), new Gson());
        }
        return omdbApi;
    }
}
