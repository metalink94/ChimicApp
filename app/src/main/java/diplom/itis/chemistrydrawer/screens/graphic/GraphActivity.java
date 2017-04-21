package diplom.itis.chemistrydrawer.screens.graphic;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.parceler.Parcels;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.activities.LoginActivity;
import diplom.itis.chemistrydrawer.models.CreateExperimentsModel;
import diplom.itis.drawer.DrawerDataSender;
import diplom.itis.drawer.DrawerModel;
import diplom.itis.drawer.DrawerView;


/**
 * Created by Денис on 06.01.2017.
 */

public class GraphActivity extends FragmentActivity implements GraphView,
        NavigationView.OnNavigationItemSelectedListener,
        DrawerDataSender {

    public static final String KEY_MODEL = "key_model";
    private DrawerView mView;
    private GraphPresenter mPresenter;
    private CreateExperimentsModel mModel;

    private void setNavigation() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        mView = (DrawerView) findViewById(R.id.view);
        mView.setCallback(this);
        setNavigation();
        mPresenter = new GraphPresenter(this);
        if (getIntent().hasExtra(KEY_MODEL)) {
            mModel = Parcels.unwrap(getIntent().getParcelableExtra(KEY_MODEL));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_triangle) {
            mPresenter.setAngles(3);
        } else if (id == R.id.nav_pentagone) {
            mPresenter.setAngles(5);
        } else if (id == R.id.nav_hexsagone) {
            mPresenter.setAngles(6);
        } else if (id == R.id.nav_manage) {
            mPresenter.setAlertDialog(new String[] {"Red color", "Green color", "Blue color"});
        } else if (id == R.id.nav_share) {
            try {
                mPresenter.setAlertDialog(new BufferedReader(
                        new InputStreamReader(getAssets().open("elements.txt"), "UTF-8")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setAngles(int i) {
        mView.setmNumberOfPoints(i);
    }

    @Override
    public void showAlertDialog(String[] arrayList) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выбрать химический элемент");
        final String [] array = arrayList;
        builder.setItems(array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                Toast.makeText(GraphActivity.this, array[item], Toast.LENGTH_LONG).show();
                mView.setText(array[item].substring(0,array[item].indexOf(" ")));
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void showAlertDialogColor(String[] colors) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Выбрать химический элемент");
        final String [] array = colors;
        builder.setItems(array, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                // Do something with the selection
                Toast.makeText(GraphActivity.this, array[item], Toast.LENGTH_LONG).show();
                switch (item) {
                    case 0:
                        mView.setColor(Color.RED);
                        break;
                    case 1:
                        mView.setColor(Color.GREEN);
                        break;
                    case 2:
                        mView.setColor(Color.BLUE);
                        break;
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_accept:

                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void getListElements(List<DrawerModel> mPointList) {
        Log.d("ListCallback","ListSize " + mPointList.size());
        mModel.addElements(mPointList);
    }

    @Override
    public void getLastModel(DrawerModel drawerModel) {

    }
}
