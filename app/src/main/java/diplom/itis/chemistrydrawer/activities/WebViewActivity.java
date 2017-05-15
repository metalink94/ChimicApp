package diplom.itis.chemistrydrawer.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import org.parceler.Parcels;

import diplom.itis.chemistrydrawer.R;
import diplom.itis.chemistrydrawer.models.CreateExperimentsModel;
import diplom.itis.chemistrydrawer.utils.BaseActivity;
import ru.diplom.itis.chimicdrawer.MarvinView;

/**
 * Created by denis_000 on 06.12.2016.
 */
public class WebViewActivity extends BaseActivity{

    private MarvinView mWebView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        mWebView = (MarvinView) findViewById(R.id.marvin);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_login, menu);
        return true;
    }

    private void createDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(WebViewActivity.this);
        alertDialog.setTitle("Сохранение");
        alertDialog.setMessage("Название эксперимента");

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        alertDialog.setView(input);

        alertDialog.setPositiveButton("Сохранить",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        saveExperiment(input.getText().toString());
                    }
                });

        alertDialog.setNegativeButton("Отмена",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialog.show();
    }

    public static final String KEY_MODEL = "key_model";

    private void saveExperiment(String name) {
        Intent intent = new Intent();
        CreateExperimentsModel model = new CreateExperimentsModel();
        model.name = name;
        model.description = "New model";
        intent.putExtra(KEY_MODEL, Parcels.wrap(model));
        setResult(100, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_accept:
                createDialog();
                Log.d(this.getClass().getName(), "Click");
                mWebView.getData();
                Log.d(this.getClass().getName(), "Click");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
