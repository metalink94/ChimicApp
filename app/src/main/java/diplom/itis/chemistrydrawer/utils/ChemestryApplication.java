package diplom.itis.chemistrydrawer.utils;

import android.app.Application;

import diplom.itis.chemistrydrawer.network.IDataProvider;

/**
 * Created by Денис on 05.02.2017.
 */

public class ChemestryApplication extends Application {

    IDataProvider mDataProvider;

    protected IDataProvider getDataProvider() {
        return mDataProvider;
    }
}
