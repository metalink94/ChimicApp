package diplom.itis.chemistrydrawer.activities;

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
import diplom.itis.chemistrydrawer.adapters.BaseAdapter;
import diplom.itis.chemistrydrawer.models.ExperimentsModel;
import diplom.itis.chemistrydrawer.models.TaskModel;
import diplom.itis.chemistrydrawer.utils.BaseActivity;

/**
 * Created by denis_000 on 06.11.2016.
 */
public class ExperimentsActivity extends BaseActivity implements View.OnClickListener{

    private RecyclerView mExperimentsList;
    private FloatingActionButton mFab;

    public static final String KEY_EXPERIMENTS = "experiments";

    private void getStatusBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(((TaskModel) getIntent().getParcelableExtra(KEY_EXPERIMENTS)).title);
    }
    
    
    private void setViews() {
        mExperimentsList = (RecyclerView) findViewById(R.id.recycler_view);
        mExperimentsList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mExperimentsList.setLayoutManager(llm);
        mAdapter = new BaseAdapter(this, setExperiments(), this);
        mExperimentsList.setAdapter(mAdapter);
        getStatusBar();
        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);
    }

    private List<ExperimentsModel> setExperiments() {
        List<ExperimentsModel> taskModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String status = ExperimentsModel.STATUS_OK;
            if (i%2 == 0) {
                status = ExperimentsModel.STATUS_ERROR;
            }
            ExperimentsModel model = new ExperimentsModel(i, "Эксперимент №" + i, "Описание данной задачи под номером " + i, status);
            taskModels.add(model);
        }
        return taskModels;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(new Intent(ExperimentsActivity.this, WebViewActivity.class));
                break;
            case R.id.taplayout:
                startActivity(new Intent(ExperimentsActivity.this, GraphActivity.class));
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
}
