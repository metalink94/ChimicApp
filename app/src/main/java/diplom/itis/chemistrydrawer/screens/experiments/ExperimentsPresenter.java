package diplom.itis.chemistrydrawer.screens.experiments;

import java.util.ArrayList;
import java.util.List;

import diplom.itis.chemistrydrawer.models.ExperimentsModel;
import diplom.itis.chemistrydrawer.utils.Presenter;

/**
 * Created by Денис on 15.01.2017.
 */

public class ExperimentsPresenter extends Presenter<ExperimentsView> {

    ExperimentsPresenter(ExperimentsView view) {
        setView(view);
    }

    void getList() {
        getView().showList(new ArrayList<ExperimentsModel>());
    }

    void setActionBar() {
        getView().showActionBar();
    }

    void setViews() {
        getView().showViews();
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
