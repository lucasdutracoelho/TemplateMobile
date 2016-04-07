package lucasdutracoelho.templatemobile.Utils.Interfaces;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lucas.coelho.dutra on 09/03/2016.
 */
public interface HttpConnection {

    String getJson(String url) throws IOException;
    InputStream getInputStream(String url) throws IOException;
}
