package diplom.itis.chemistrydrawer.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import diplom.itis.chemistrydrawer.adapters.BaseAdapter;
import diplom.itis.chemistrydrawer.network.IDataProvider;

/**
 * Created by denis_000 on 05.11.2016.
 */
public class BaseActivity extends AppCompatActivity implements Handler.Callback {

    protected BaseAdapter mAdapter;
    protected IDataProvider mDataProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
