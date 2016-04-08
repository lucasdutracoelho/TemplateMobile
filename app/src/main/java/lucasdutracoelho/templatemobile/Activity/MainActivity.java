package lucasdutracoelho.templatemobile.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import lucasdutracoelho.templatemobile.Adapter.ItemClick.OnDoubleClickListener;
import lucasdutracoelho.templatemobile.Adapter.RecyclerView.Inflater.FilmeInflater;
import lucasdutracoelho.templatemobile.Adapter.ItemClick.OnItemClickListener;
import lucasdutracoelho.templatemobile.Adapter.RecyclerView.RecyclerViewAdapterBase;
import lucasdutracoelho.templatemobile.Adapter.RecyclerView.Util.DividerItemDecoration;
import lucasdutracoelho.templatemobile.Adapter.RecyclerView.ViewHolder.FilmeViewHolder;
import lucasdutracoelho.templatemobile.Manager.FilmeManager;
import lucasdutracoelho.templatemobile.Manager.FilmeManagerImpl;
import lucasdutracoelho.templatemobile.Model.Filme;
import lucasdutracoelho.templatemobile.R;
import lucasdutracoelho.templatemobile.Utils.Constantes;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnDoubleClickListener<Filme>, FilmeInflater.OnClickDelete {
    /**
     * Logging
     */
    private static final String TAG = MainActivity.class.getSimpleName();

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @ViewById(R.id.fab)
    FloatingActionButton fab;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @Bean(value = FilmeManagerImpl.class)
    FilmeManager filmeManager;

    @ViewById(R.id.my_recycler_view)
    RecyclerView myRecyclerView;

    RecyclerViewAdapterBase adapterBase;

    @Bean
    FilmeInflater filmeInflater;


    @AfterViews
    protected void init() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerView.setLayoutManager(layoutManager);
        myRecyclerView.addItemDecoration(new DividerItemDecoration(ResourcesCompat.getDrawable(getResources(), R.drawable.divider, null)));

        filmeInflater.setOnClickDelete(this);
        adapterBase = new RecyclerViewAdapterBase<Filme, FilmeViewHolder>(filmeInflater, filmeManager.listarFilmes(), this);
        adapterBase.setMode(Attributes.Mode.Single);

        myRecyclerView.setAdapter(adapterBase);

    }

    @Override
    protected void onResume(){
        super.onResume();
        updateList();
    }

    @Click(R.id.fab)
    public void onClickFab(View view){
        startActivity(new Intent(this, PesquisaActivity_.class));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()){
            case R.id.nav_camera:
                Toast.makeText(this, "Menu 1", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.nav_gallery:
                Toast.makeText(this, "Menu 2", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_slideshow:
                Toast.makeText(this, "Menu 3", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.nav_manage:
                Toast.makeText(this, "Menu 4", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Menu 5", Toast.LENGTH_SHORT).show();
                break;

            case  R.id.nav_send:
                Toast.makeText(this, "Menu 6", Toast.LENGTH_SHORT).show();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean DeleteFilme(Filme filme) {
        filmeManager.deletar(filme);
        return true;
    }

    @Override
    public void onItemDoubleClick(Filme item) {
        Intent intent = new Intent(this, FilmeActivity_.class);
        intent.putExtra(Constantes.FILME_SERIALIZE, item);
        startActivity(intent);
    }
    @UiThread
    public void updateList(){
        adapterBase.addItems(filmeManager.listarFilmes(), true);
    }

    public FilmeManager getFilmeManager() {
        return filmeManager;
    }

    public RecyclerViewAdapterBase getAdapterBase() {
        return adapterBase;
    }
}


