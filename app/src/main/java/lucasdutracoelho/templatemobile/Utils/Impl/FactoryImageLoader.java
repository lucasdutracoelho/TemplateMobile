package lucasdutracoelho.templatemobile.Utils.Impl;

import lucasdutracoelho.templatemobile.Enum.ImageLoaderType;
import lucasdutracoelho.templatemobile.Exception.FactoryImageLoaderException;
import lucasdutracoelho.templatemobile.Utils.Interfaces.ImageLoader;

/**
 * Created by lucas.coelho.dutra on 10/03/2016.
 */
public class FactoryImageLoader {

    private static ImageLoader imageLoader;

    public static ImageLoader getImageLoader(ImageLoaderType type){
        switch (type){
            case FRESCO:
                return new FrescoImpl();
        }
        throw new FactoryImageLoaderException("Instância não configurada corretamente");
    }
}
