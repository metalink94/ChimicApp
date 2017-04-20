package diplom.itis.drawer;

import java.util.List;

/**
 * Created by Денис on 13.03.2017.
 */

public interface DrawerDataSender {

    void getListElements(List<DrawerModel> mPointList);

    void getLastModel(DrawerModel drawerModel);
}
