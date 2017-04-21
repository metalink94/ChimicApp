package diplom.itis.chemistrydrawer.screens.addExperements;

import diplom.itis.chemistrydrawer.models.CreateExperimentsModel;
import diplom.itis.chemistrydrawer.utils.Presenter;

/**
 * Created by Денис on 21.04.2017.
 */

public class AddExperimentsPresenter extends Presenter<AddExperimentsView> {

    AddExperimentsPresenter(AddExperimentsView view) {
        setView(view);
    }

    public void saveData(String name, String description) {
        if (name.isEmpty() || description.isEmpty()) {
            getView().showError();
            return;
        }
        CreateExperimentsModel createExperimentsModel = new CreateExperimentsModel(name, description);
        getView().sendModel(createExperimentsModel);
    }
}
