package lucasdutracoelho.templatemobile.Adapter.Base;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
public interface AdapterViewInflater<T> {
    public View inflate(BaseInflaterAdapter<T> adapter, int pos, View convertView, ViewGroup parent);
}
