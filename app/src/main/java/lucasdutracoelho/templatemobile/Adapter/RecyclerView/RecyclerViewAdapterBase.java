package lucasdutracoelho.templatemobile.Adapter.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;
import java.util.List;

import lucasdutracoelho.templatemobile.Adapter.ItemClick.OnDoubleClickListener;
import lucasdutracoelho.templatemobile.Adapter.ItemClick.OnItemClickListener;
import lucasdutracoelho.templatemobile.R;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
public  class RecyclerViewAdapterBase<T, V extends View> extends RecyclerSwipeAdapter<ViewWrapper<V>> {

    private final String TAG = "RecyclerViewAdapterBase";
    protected List<T> items = new ArrayList<T>();
    private RecyclerViewInflater<T, V> m_viewInflater;
    private OnItemClickListener<T> m_OnItemClickListener;

    private OnDoubleClickListener<T> m_OnDoubleClickListener;

    public RecyclerViewAdapterBase(RecyclerViewInflater<T, V> viewInflater, List<T> items)
    {
        this.items.addAll(items);
        m_viewInflater = viewInflater;
    }

    public RecyclerViewAdapterBase(RecyclerViewInflater<T, V> viewInflater, List<T> items, OnItemClickListener<T> onItemClickListener)
    {
        this.items.addAll(items);
        m_viewInflater = viewInflater;
        m_OnItemClickListener = onItemClickListener;
    }

    public RecyclerViewAdapterBase(RecyclerViewInflater<T, V> viewInflater, List<T> items, OnDoubleClickListener<T> onDoubleClickListener)
    {
        this.items.addAll(items);
        m_viewInflater = viewInflater;
        m_OnDoubleClickListener = onDoubleClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.m_OnItemClickListener = onItemClickListener;
    }

    public void setOnDoubleClickListener(OnDoubleClickListener<T> onDoubleClickListener) {
        this.m_OnDoubleClickListener = onDoubleClickListener;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(T item, boolean notifyChange) {
        items.add(item);

        if (notifyChange)
            notifyDataSetChanged();
    }

    public void addItems(List<T> items, boolean notifyChange) {
        this.items.clear();
        this.items.addAll(items);

        if (notifyChange)
            this.notifyDataSetChanged();
    }

    public void clear(boolean notifyChange) {
        items.clear();

        if (notifyChange)
            notifyDataSetChanged();
    }

    public void remove(int position, boolean notifyChange){
        this.items.remove(position);
        if (notifyChange)
            notifyDataSetChanged();
    }

    public T getItem(int position){
        return items.get(position);
    }

    @Override
    public final ViewWrapper<V> onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewWrapper<V>(onCreateItemView(parent, viewType));
    }

    protected V onCreateItemView(ViewGroup parent, int viewType){
        return m_viewInflater != null ? m_viewInflater.onCreateItemView(parent, viewType) : null;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<V> viewHolder, int position){
        try{
            if(m_viewInflater != null ){
                ViewGroup view = m_viewInflater.onBindViewHolder(viewHolder, this, position, mItemManger);
                final T item = items.get(position);
                if(m_OnItemClickListener!=null){
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override public void onClick(View v) {
                            m_OnItemClickListener.onItemClick(item);
                        }
                    });
                }
                if(m_OnDoubleClickListener!=null && view instanceof SwipeLayout){
                    ((SwipeLayout)view).setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
                        @Override
                        public void onDoubleClick(SwipeLayout layout, boolean surface) {
                            m_OnDoubleClickListener.onItemDoubleClick(item);
                        }
                    });
                }
            }
        }catch (Exception e){
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        //// TODO: 04/04/2016 Mudar para a interface
        return R.id.swipe;
    }
}

