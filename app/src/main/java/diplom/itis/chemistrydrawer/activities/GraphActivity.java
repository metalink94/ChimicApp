package diplom.itis.chemistrydrawer.activities;

import android.content.DialogInterface;
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
import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.utils.BaseActivity;
import diplom.itis.chemistrydrawer.utils.MyView;


/**
 * Created by Денис on 06.01.2017.
 */

public class GraphActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MyView mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        mView = (MyView) findViewById(R.id.view);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_triangle) {
            mView.setNumberOfPoint(3);
        } else if (id == R.id.nav_pentagone) {
            mView.setNumberOfPoint(5);
        } else if (id == R.id.nav_hexsagone) {
            mView.setNumberOfPoint(6);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Make your selection");
            builder.setItems(readFile(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    // Do something with the selection
                    Toast.makeText(GraphActivity.this, readFile()[item], Toast.LENGTH_LONG).show();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private String[] readFile() {
        BufferedReader reader = null;
        List<String> temps = new ArrayList<>();
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("elements.txt"), "UTF-8"));
            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                //process line
                temps.add(mLine);
            }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        String[] tempsArray = temps.toArray(new String[0]);
        return tempsArray;
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
}
