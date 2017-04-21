package diplom.itis.chemistrydrawer.screens.tasks;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.models.TaskModel;
import diplom.itis.chemistrydrawer.utils.Presenter;

/**
 * Created by Денис on 15.01.2017.
 */

public class TaskListPresenter extends Presenter<TaskListView> {

    TaskListRepository mRepo;

    TaskListPresenter(TaskListView view) {
        setView(view);
        mRepo = new TaskListRepository();
    }

    private void setTasks() {
        List<TaskModel> taskModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Log.d(getClass().getName(),"getListSize " + i);
            TaskModel model = new TaskModel(i, "Задача " + i, "Описание данной задачи под номером " + i);
            taskModels.add(model);
        }
        Log.d(getClass().getName(), "Get List size() = "+ taskModels.size());
        getView().showTasks(taskModels);
    }


    public void getList() {
        //mRepo.getList();
        setTasks();
    }

    public void setViews() {
        getView().showView();
    }
}
