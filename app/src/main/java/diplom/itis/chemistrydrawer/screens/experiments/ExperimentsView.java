package diplom.itis.chemistrydrawer.screens.experiments;

import java.util.List;

import diplom.itis.chemistrydrawer.models.ExperimentsModel;

/**
 * Created by Денис on 15.01.2017.
 */

public interface ExperimentsView {
    void showList(List<ExperimentsModel> experimentsModels);

    void showViews();

    void showActionBar();
}
