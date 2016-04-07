package lucasdutracoelho.templatemobile.Adapter.RecyclerView.ViewHolder;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import lucasdutracoelho.templatemobile.Model.Filme;
import lucasdutracoelho.templatemobile.R;

/**
 * Created by lucas.coelho.dutra on 28/03/2016.
 */
@EViewGroup(R.layout.list_item_filme)
public class FilmeViewHolder extends LinearLayout {

    @ViewById(R.id.position)
    TextView titulo;

    @ViewById(R.id.text_data)
    TextView ano;

    @ViewById(R.id.delete)
    Button btnDelete;

    @ViewById(R.id.swipe)
    SwipeLayout swipeLayout;

    public FilmeViewHolder(Context context) {
        super(context);
    }

    public void bind(Filme filme) {
        titulo.setText(filme.getTitulo());
        ano.setText(filme.getAno());
    }

    public SwipeLayout getSwipeLayout() {
        return swipeLayout;
    }
    public Button getBtnDelete() {
        return btnDelete;
    }
}
