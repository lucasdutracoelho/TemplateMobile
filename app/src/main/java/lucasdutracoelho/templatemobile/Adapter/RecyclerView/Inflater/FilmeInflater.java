package lucasdutracoelho.templatemobile.Adapter.RecyclerView.Inflater;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.implments.SwipeItemMangerImpl;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import lucasdutracoelho.templatemobile.Adapter.RecyclerView.RecyclerViewAdapterBase;
import lucasdutracoelho.templatemobile.Adapter.RecyclerView.RecyclerViewInflater;

import lucasdutracoelho.templatemobile.Adapter.RecyclerView.ViewHolder.FilmeViewHolder_;
import lucasdutracoelho.templatemobile.Adapter.RecyclerView.ViewHolder.FilmeViewHolder;
import lucasdutracoelho.templatemobile.Adapter.RecyclerView.ViewWrapper;
import lucasdutracoelho.templatemobile.Model.Filme;
import lucasdutracoelho.templatemobile.R;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
@EBean
public class FilmeInflater implements RecyclerViewInflater<Filme, FilmeViewHolder> {
    @RootContext
    Context context;
    private OnClickDelete onClickDelete;

    public interface OnClickDelete {
        public boolean DeleteFilme(Filme filme);
    }

    @Override
    public FilmeViewHolder onCreateItemView(ViewGroup parent, int viewType) {
        return FilmeViewHolder_.build(context);
    }

    @Override
    public ViewGroup onBindViewHolder(final ViewWrapper<FilmeViewHolder> viewHolder, final RecyclerViewAdapterBase<Filme, FilmeViewHolder> adapter, final int position, final SwipeItemMangerImpl mItemManger) {
        FilmeViewHolder view = viewHolder.getView();
        view.getSwipeLayout().setShowMode(SwipeLayout.ShowMode.LayDown);
        view.getSwipeLayout().addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
            }
        });
        view.getSwipeLayout().setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(context, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        view.getBtnDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickDelete != null) {
                    //Se for deletado com sucesso
                    if (onClickDelete.DeleteFilme(adapter.getItem(position))) {
                        mItemManger.removeShownLayouts(((FilmeViewHolder) viewHolder.getView()).getSwipeLayout());
                        adapter.remove(position, false);
                        adapter.notifyItemRemoved(position);
                        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
                    }
                    mItemManger.closeAllItems();
                }
            }
        });
        view.bind(adapter.getItem(position));
        mItemManger.bindView(viewHolder.itemView, position);
        return view.getSwipeLayout();
    }

    public void setOnClickDelete(FilmeInflater.OnClickDelete onClickDelete) {
        this.onClickDelete = onClickDelete;
    }
}
