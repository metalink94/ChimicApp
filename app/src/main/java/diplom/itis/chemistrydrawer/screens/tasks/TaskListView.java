package diplom.itis.chemistrydrawer.screens.tasks;

import java.util.List;

import diplom.itis.chemistrydrawer.models.TaskModel;

/**
 * Created by Денис on 15.01.2017.
 */

public interface TaskListView {

    void showTasks(List<TaskModel> taskModels);

    void showView();
}
