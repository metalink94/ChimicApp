package diplom.itis.chemistrydrawer.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import diplom.itis.chemistrydrawer.adapters.BaseAdapter;
import diplom.itis.chemistrydrawer.network.IDataProvider;
import timber.log.Timber;

/**
 * Created by denis_000 on 05.11.2016.
 */
public class BaseActivity extends AppCompatActivity {

    protected BaseAdapter mAdapter;
    protected IDataProvider mDataProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.d("Now in %s", getBaseContext().getClass());
    }
}
