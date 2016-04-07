package lucasdutracoelho.templatemobile.Adapter.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
public class ViewWrapper <V extends View> extends RecyclerView.ViewHolder {

    private V view;

    public ViewWrapper(V itemView) {
        super(itemView);
        view = itemView;
    }

    public V getView() {
        return view;
    }
}
