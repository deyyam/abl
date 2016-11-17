package com.hitherejoe.androidboilerplate.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ProgressBar;

import com.hitherejoe.androidboilerplate.R;
import com.hitherejoe.androidboilerplate.data.model.Character;
import com.hitherejoe.androidboilerplate.data.model.Config;
import com.hitherejoe.androidboilerplate.ui.activity.BaseActivity;
import com.hitherejoe.androidboilerplate.data.DataManager;
import com.hitherejoe.androidboilerplate.ui.activity.MainActivity;
import com.hitherejoe.androidboilerplate.util.DataUtils;
import com.hitherejoe.androidboilerplate.util.DialogFactory;

import java.util.List;

import butterknife.Bind;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Srihari S Reddy on 17/11/16.
 */


public class SplashActivity extends BaseActivity {

    @Bind(R.id.splash_progress)
    ProgressBar mProgressBar;


    @Inject DataManager mDataManager;
    private CompositeSubscription mSubscriptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mSubscriptions = new CompositeSubscription();
        loadConfig();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    private void loadConfig() {
        if (DataUtils.isNetworkAvailable(this)) {
            mSubscriptions.add(mDataManager.getConfig()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new Subscriber<Config>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable error) {
                            Timber.e("There was an error retrieving the characters " + error);
                            mProgressBar.setVisibility(View.GONE);
                            DialogFactory.createSimpleErrorDialog(SplashActivity.this).show();
                        }

                        @Override
                        public void onNext(Config config) {
                            Timber.e("Config sucessfully retrived" + config.toString());
                            mProgressBar.setVisibility(View.GONE);
                        }
                    }));
        } else {
            mProgressBar.setVisibility(View.GONE);
            DialogFactory.createSimpleOkErrorDialog(
                    this,
                    getString(R.string.dialog_error_title),
                    getString(R.string.dialog_error_no_connection)
            ).show();
        }

    }
}
