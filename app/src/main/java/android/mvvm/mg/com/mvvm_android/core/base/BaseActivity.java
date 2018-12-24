package android.mvvm.mg.com.mvvm_android.core.base;

import android.annotation.SuppressLint;
import android.mvvm.mg.com.mvvm_android.BuildConfig;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import dmutils.com.dmutils.general.DMMemory;

@SuppressLint("Registered")
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DMMemory.freeMemory();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final BaseApplicationConfigs applicationConfigs = ((BaseApplication) getApplication()).getApplicationConfigs();

        if (BuildConfig.DEBUG) {
            Log.d(applicationConfigs.getTag(), "-----------------------------------------------------------------------------------------------------> " + this.getClass().getSimpleName());
        }
    }

    protected void hideActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public void setTitle(final String title) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void setTitle(final int title) {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }

    public void showBackButton() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void hideBackButton() {
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }
}