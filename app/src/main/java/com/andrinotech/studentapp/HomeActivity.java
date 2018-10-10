package com.andrinotech.studentapp;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;

import com.andrinotech.studentapp.FragNav.FragNavController;
import com.andrinotech.studentapp.ui.Post.ShowPostFragment;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.base.tempViewModel;
import com.andrinotech.studentapp.ui.profile.StudentProfileFragment;

import java.lang.reflect.Field;

public class HomeActivity extends BaseActivity<tempViewModel> implements FragNavController.TransactionListener, FragNavController.RootFragmentListener {
    private BottomNavigationView mnav_bottomBar;
    private final int FRAG_FEED = FragNavController.TAB1;
    private final int FRAG_MAP = FragNavController.TAB2;
    private final int FRAG_FRIEND = FragNavController.TAB3;
    private final int FRAG_TRENDING = FragNavController.TAB4;
    private final int FRAG_ME = FragNavController.TAB5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_feeds);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        initViews();
        initData(savedInstanceState);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_local_feeds;
    }

    @Override
    public tempViewModel initViewModel() {
        return new tempViewModel();
    }

    private void initViews() {
        mnav_bottomBar = (BottomNavigationView) findViewById(R.id.nav_bottomBar);
        disableShiftMode(mnav_bottomBar);
    }

    @SuppressLint("RestrictedApi")
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                //noinspection RestrictedApi
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVHelper", "Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVHelper", "Unable to change value of shift mode", e);
        }
    }

    private void initData(Bundle savedInstanceState) {
        setmNavController(FragNavController.newBuilder(savedInstanceState, getSupportFragmentManager(), R.id.main_frame)
                .transactionListener(this)
                .rootFragmentListener(this, 5)
                .build());

        setmTransactionListener(this);
        replaceFragment(FRAG_FEED);

        mnav_bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        replaceFragment(FRAG_FEED);
                        return true;
                    case R.id.nav_friends:
                        replaceFragment(FRAG_FRIEND);
                        return true;
                    case R.id.nav_trending:
                        replaceFragment(FRAG_TRENDING);
                        return true;
                    case R.id.nav_me:
                        replaceFragment(FRAG_ME);
                        return true;
                    case R.id.nav_map:
                        replaceFragment(FRAG_MAP);
                        return true;
                    default:
                        return true;
                }
            }
        });

    }

    // Replace fragment
    public void replaceFragment(final int map) {
        getmNavController().switchTab(map);
    }


    @Override
    public void onTabTransaction(Fragment fragment, int index) {
    }

    @Override
    public void onFragmentTransaction(Fragment fragment, FragNavController.TransactionType transactionType) {

    }

    @Override
    public Fragment getRootFragment(int index) {
        switch (index) {
            case FRAG_FEED:
                return new ShowPostFragment();
            case FRAG_MAP:
                return new StudentProfileFragment();
            case FRAG_FRIEND:
                return new StudentProfileFragment();
            case FRAG_TRENDING:
                return new StudentProfileFragment();
            case FRAG_ME:
                return new StudentProfileFragment();
        }
        throw new IllegalStateException("");
    }
}
