package diplom.itis.chemistrydrawer.screens.experiments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.activities.WebViewActivity;
import diplom.itis.chemistrydrawer.adapters.BaseAdapter;
import diplom.itis.chemistrydrawer.models.ExperimentsModel;
import diplom.itis.chemistrydrawer.models.TaskModel;
import diplom.itis.chemistrydrawer.screens.addExperements.AddExperementsActivity;
import diplom.itis.chemistrydrawer.screens.addExperements.AddExperimentsView;
import diplom.itis.chemistrydrawer.screens.graphic.GraphActivity;
import diplom.itis.chemistrydrawer.utils.BaseActivity;

import static diplom.itis.chemistrydrawer.screens.graphic.GraphActivity.KEY_MODEL;

/**
 * Created by denis_000 on 06.11.2016.
 */
public class ExperimentsActivity extends BaseActivity implements View.OnClickListener, ExperimentsView{

    final int REQUEST_CODE_DATA = 1;

    private RecyclerView mExperimentsList;
    private FloatingActionButton mFab;
    private ExperimentsPresenter mPresenter;

    public static final String KEY_EXPERIMENTS = "experiments";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new ExperimentsPresenter(this);
        mPresenter.setViews();
        mPresenter.getList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(ExperimentsActivity.this, AddExperementsActivity.class), REQUEST_CODE_DATA);
                break;
            case R.id.taplayout:
                startActivityForResult(new Intent(ExperimentsActivity.this, GraphActivity.class), REQUEST_CODE_DATA);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                onBackPressed();
                break;

        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(ExperimentsActivity.class.getName(), "requestCode = " + requestCode + " resultColde = " + resultCode);
    }

    @Override
    public void showList(List<ExperimentsModel> experimentsModels) {
        mAdapter.addItems(experimentsModels);
    }

    @Override
    public void showViews() {
        mExperimentsList = (RecyclerView) findViewById(R.id.recycler_view);
        mExperimentsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mExperimentsList.setLayoutManager(llm);
        mAdapter = new BaseAdapter(this, this);
        mExperimentsList.setAdapter(mAdapter);
        mPresenter.setActionBar();
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
    }

    @Override
    public void showActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(((TaskModel) getIntent().getParcelableExtra(KEY_EXPERIMENTS)).title);
    }
}
