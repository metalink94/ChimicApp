package diplom.itis.chemistrydrawer.screens.tasks;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.models.api.AdditivesListFields;
import diplom.itis.chemistrydrawer.models.TaskModel;
import diplom.itis.chemistrydrawer.models.api.ModelListFields;
import diplom.itis.chemistrydrawer.network.NetworkWorker;
import diplom.itis.chemistrydrawer.utils.Presenter;

/**
 * Created by Денис on 15.01.2017.
 */

public class TaskListPresenter extends Presenter<TaskListView> implements TaskListRepository.Callback {

    TaskListRepository mRepo;

    TaskListPresenter(TaskListView view, NetworkWorker mNetworkWorker) {
        setView(view);
        mRepo = new TaskListRepository(mNetworkWorker);
        mRepo.setCallback(this);
        getView().showView();
        getAdditives();
    }

    public void setTasks(String[] titles, String[] messages) {
        List<TaskModel> taskModels = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            Log.d(getClass().getName(),"getListSize " + i);
            TaskModel model = new TaskModel(i, titles[i], messages[i]);
            taskModels.add(model);
        }
        Log.d(getClass().getName(), "Get List size() = "+ taskModels.size());
        getView().showTasks(taskModels);
    }


    public void getAdditives() {
        mRepo.getAdditives();
        mRepo.getMagics();
        mRepo.getModels();
    }

    @Override
    public void saveAdditive(List<AdditivesListFields> additivesListFieldses) {
        Log.d(getClass().getSimpleName(), "size of list " + additivesListFieldses.size());
    }

    @Override
    public void saveModels(List<ModelListFields> modelListFieldses) {
        Log.d(getClass().getSimpleName(), "size of list " + modelListFieldses.size());
    }
}
