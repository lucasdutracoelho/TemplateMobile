package lucasdutracoelho.templatemobile.Adapter.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.implments.SwipeItemMangerImpl;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
public interface RecyclerViewInflater<T, V extends View> {
    V onCreateItemView(ViewGroup parent, int viewType);
    ViewGroup onBindViewHolder(ViewWrapper<V> viewHolder, RecyclerViewAdapterBase<T, V> adapter, int position, SwipeItemMangerImpl mItemManger);
}
