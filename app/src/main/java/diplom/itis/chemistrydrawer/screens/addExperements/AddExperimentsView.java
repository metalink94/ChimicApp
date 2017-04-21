package diplom.itis.chemistrydrawer.screens.addExperements;

import diplom.itis.chemistrydrawer.models.CreateExperimentsModel;
import diplom.itis.chemistrydrawer.utils.BaseView;

/**
 * Created by Денис on 21.04.2017.
 */

public interface AddExperimentsView extends BaseView {
    void sendModel(CreateExperimentsModel aCreateExperimentsModel);

    void showError();
}
