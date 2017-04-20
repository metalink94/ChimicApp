package diplom.itis.chemistrydrawer.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.models.LogInModel;
import diplom.itis.chemistrydrawer.network.GetRequest;
import diplom.itis.chemistrydrawer.screens.tasks.TasksListActivity;
import diplom.itis.chemistrydrawer.utils.BaseActivity;

/**
 * Created by denis_000 on 05.11.2016.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final String SAVE_LOGIN = "login";

    private EditText mLogin;
    private EditText mPassword;
    private TextView mText;
    private SharedPreferences mShared;

    private void setViews() {
        mLogin = (EditText) findViewById(R.id.et_login);
        mPassword = (EditText) findViewById(R.id.et_password);
        mText = (TextView) findViewById(R.id.tv_check_login);
        mShared = getPreferences(MODE_PRIVATE);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setViews();
        if (!mShared.getString(SAVE_LOGIN, "").equals("")) {
            loadText();
            mText.setVisibility(View.VISIBLE);
            mLogin.setVisibility(View.GONE);
            findViewById(R.id.tv_change_user).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_change_user).setOnClickListener(this);
        } else {
            findViewById(R.id.tv_change_user).setVisibility(View.GONE);
            mText.setVisibility(View.GONE);
            mLogin.setVisibility(View.VISIBLE);
        }
    }

    private void saveText() {
        SharedPreferences.Editor ed = mShared.edit();
        ed.putString(SAVE_LOGIN, mLogin.getText().toString());
        ed.commit();
    }

    private String loadText() {
        String savedText = mShared.getString(SAVE_LOGIN, "");
        mText.setText(String.format(getString(R.string.check_user), savedText));
        return savedText;
    }

    private void removeText() {
        SharedPreferences.Editor ed = mShared.edit();
        ed.putString(SAVE_LOGIN, "");
        ed.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }

    private void sendRequest(LogInModel model) {
        GetRequest getRequest = new GetRequest("auth", model);
        getRequest.execute();
        try {
            boolean request = (boolean) getRequest.get();
            if (request) {
                saveText();
                startActivity(new Intent(LoginActivity.this, TasksListActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, R.string.error_auth, Toast.LENGTH_LONG).show();
            }
            Log.d("REQUEST", "getRequest " + getRequest.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void openNewActivity() {
        LogInModel logInModel = new LogInModel();

        if (loadText().length() > 0) {
            logInModel.user = loadText();
            logInModel.password = mPassword.getText().toString();
        } else {
            logInModel.user = mLogin.getText().toString();
            logInModel.password = mPassword.getText().toString();
        }
        sendRequest(logInModel);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_accept:
                if (mShared.getString(SAVE_LOGIN, "").equals("")) {
                    if (mLogin.getText().toString().length() > 0 && mPassword.getText().toString().length() > 0) {
                        openNewActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.need_more_info, Toast.LENGTH_LONG).show();
                    }
                } else {
                    if (mPassword.getText().toString().length() > 0) {
                        openNewActivity();
                    } else {
                        Toast.makeText(LoginActivity.this, R.string.need_more_info, Toast.LENGTH_LONG).show();
                    }
                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_change_user:
                removeText();
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }
}
