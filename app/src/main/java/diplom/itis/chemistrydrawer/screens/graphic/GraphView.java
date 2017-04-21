package diplom.itis.chemistrydrawer.screens.graphic;

import diplom.itis.chemistrydrawer.utils.BaseView;

/**
 * Created by Денис on 15.01.2017.
 */

public interface GraphView extends BaseView {
    void setAngles(int i);

    void showAlertDialog(String[] arrayList);

    void showAlertDialogColor(String[] colors);
}
