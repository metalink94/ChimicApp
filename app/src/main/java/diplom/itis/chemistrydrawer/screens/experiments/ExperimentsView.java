package diplom.itis.chemistrydrawer.screens.experiments;

import java.util.List;

import diplom.itis.chemistrydrawer.models.ExperimentsModel;
import diplom.itis.chemistrydrawer.utils.BaseView;

/**
 * Created by Денис on 15.01.2017.
 */

public interface ExperimentsView extends BaseView {
    void showList(List<ExperimentsModel> experimentsModels);

    void showViews();

    void showActionBar();
}
