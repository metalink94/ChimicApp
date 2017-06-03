package diplom.itis.chemistrydrawer.screens.tasks;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.activities.ProfileActivity;
import diplom.itis.chemistrydrawer.adapters.BaseAdapter;
import diplom.itis.chemistrydrawer.models.TaskModel;
import diplom.itis.chemistrydrawer.screens.experiments.ExperimentsActivity;
import diplom.itis.chemistrydrawer.utils.BaseActivity;

/**
 * Created by denis_000 on 06.11.2016.
 */
public class TasksListActivity extends BaseActivity implements View.OnClickListener, TaskListView{

    @BindView(R.id.tasks_list)
    private RecyclerView mTasksList;
    private TaskListPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        ButterKnife.bind(this);
        mPresenter = new TaskListPresenter(this, mNetworkWorker);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.taplayout:
                startActivity(new Intent(TasksListActivity.this, ExperimentsActivity.class)
                        .putExtra(ExperimentsActivity.KEY_EXPERIMENTS, (TaskModel)v.getTag()));
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_settings:
                return true;

            case R.id.action_profile:
                startActivity(new Intent(TasksListActivity.this, ProfileActivity.class));
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void showTasks(List<TaskModel> taskModels) {
        mAdapter.addItems(taskModels);
    }

    @Override
    public void showView() {
        mTasksList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mTasksList.setLayoutManager(llm);
        mAdapter = new BaseAdapter(this, this);
        mTasksList.setAdapter(mAdapter);
    }
}
