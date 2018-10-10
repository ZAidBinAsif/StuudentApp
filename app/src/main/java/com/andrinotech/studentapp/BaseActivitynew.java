package com.andrinotech.studentapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.andrinotech.studentapp.FragNav.FragNavController;
import com.andrinotech.studentapp.ui.base.BaseFragment;

public abstract class BaseActivitynew extends AppCompatActivity implements BaseFragment.FragmentNavigation {

    public FragNavController mNavController;
    FragNavController.TransactionListener mTransactionListener;

    public FragNavController.TransactionListener getmTransactionListener() {
        return mTransactionListener;
    }

    public void setmTransactionListener(FragNavController.TransactionListener mTransactionListener) {
        this.mTransactionListener = mTransactionListener;
    }

    public FragNavController getmNavController() {
        return mNavController;
    }

    public void setmNavController(FragNavController mNavController) {
        this.mNavController = mNavController;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }


    @Override
    public void pushFragment(Fragment fragment) {
        if (mNavController != null) {
            if (mTransactionListener != null)
                mTransactionListener.onFragmentTransaction(mNavController.getCurrentFrag(), FragNavController.TransactionType.PUSH);
            mNavController.pushFragment(fragment);
        }
    }

    @Override
    public void popFragment() {
        if (mNavController != null && mNavController != null) {
            if (mTransactionListener != null)
                mTransactionListener.onFragmentTransaction(mNavController.getCurrentFrag(), FragNavController.TransactionType.POP);
            mNavController.popFragment();
        }
    }

    @Override
    public void onBackPressed() {
        if (mNavController != null && !mNavController.isRootFragment()) {
            if (mTransactionListener != null)
                mTransactionListener.onFragmentTransaction(mNavController.getCurrentFrag(), FragNavController.TransactionType.POP);
            mNavController.popFragment();
        } else if (mNavController != null && mNavController.isRootFragment()) {
            moveTaskToBack(true);
        } else {
            super.onBackPressed();
//            moveTaskToBack(true);
        }
    }

}
