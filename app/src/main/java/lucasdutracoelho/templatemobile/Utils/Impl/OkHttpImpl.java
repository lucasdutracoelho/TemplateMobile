package lucasdutracoelho.templatemobile.Utils.Impl;

import java.io.IOException;
import java.io.InputStream;

import lucasdutracoelho.templatemobile.Utils.Interfaces.HttpConnection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lucas.coelho.dutra on 09/03/2016.
 */
public class OkHttpImpl implements HttpConnection {

    OkHttpClient client = new OkHttpClient();

    @Override
    public String getJson(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    @Override
    public InputStream getInputStream(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().byteStream();
    }
}
