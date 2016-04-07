package lucasdutracoelho.templatemobile.Utils.Impl;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import org.androidannotations.annotations.EBean;
import org.apache.commons.lang3.StringUtils;

import lucasdutracoelho.templatemobile.Utils.Interfaces.ImageLoader;

/**
 * Created by lucas.coelho.dutra on 09/03/2016.
 */
@EBean
public class FrescoImpl implements ImageLoader {

    protected FrescoImpl(){};

    @Override
    public void showImage(SimpleDraweeView imageView, String url) {
        if(StringUtils.isNotBlank(url)){
            Uri uri = Uri.parse(url);
            imageView.setImageURI(uri);
        }
    }
}
