package diplom.itis.chemistrydrawer.utils;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

import diplom.itis.chemistrydrawer.adapters.BaseAdapter;
import diplom.itis.chemistrydrawer.network.NetworkWorker;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by denis_000 on 05.11.2016.
 */
public class BaseActivity extends AppCompatActivity implements Callback {

    protected BaseAdapter mAdapter;
    protected NetworkWorker mNetworkWorker;
    protected ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNetworkWorker = new NetworkWorker();
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }

    public void showProgressDialog() {
        showProgressDialog("Подождите пожалуйста...");
    }

    public void showProgressDialog(String message) {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(message);
        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
