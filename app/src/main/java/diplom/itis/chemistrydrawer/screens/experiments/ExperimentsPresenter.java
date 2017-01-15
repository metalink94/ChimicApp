package diplom.itis.chemistrydrawer.screens.experiments;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.models.ExperimentsModel;

/**
 * Created by Денис on 15.01.2017.
 */

public class ExperimentsPresenter {

    ExperimentsView mView;

    ExperimentsPresenter(ExperimentsView view) {
        mView = view;
    }

    void getList() {
        mView.showList(setExperiments());
    }

    void setActionBar() {
        mView.showActionBar();
    }

    void setViews() {
        mView.showViews();
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
}
