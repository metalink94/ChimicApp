package diplom.itis.chemistrydrawer.screens.graphic;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.utils.BaseActivity;
import diplom.itis.graphlibrary.MyView;


/**
 * Created by Денис on 06.01.2017.
 */

public class GraphActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, GraphView {

    private MyView mView;
    private GraphPresenter mPresenter;

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
        mView = (MyView) findViewById(R.id.view);
        setNavigation();
        mPresenter = new GraphPresenter(this);
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
        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
    public void setAngles(int i) {
        mView.setNumberOfPoint(i);
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
}
