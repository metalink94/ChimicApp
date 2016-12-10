package diplom.itis.chemistrydrawer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import diplom.itis.chemistrydrawer.utils.BaseActivity;

/**
 * Created by denis_000 on 05.11.2016.
 */
public class SplashScreenActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
