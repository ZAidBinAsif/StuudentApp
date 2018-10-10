package com.andrinotech.studentapp.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.andrinotech.studentapp.R;


/**
 * Created by ZaidAs on 08/03/2018.
 */

public class FragManager {
    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, boolean addToStack, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment mFragment = fragmentManager.findFragmentByTag(tag);
//        if(mFragment!=null){
//            transaction.replace(R.id.flContainerFragment, mFragment, tag);
//
//        }else {
//        transaction.replace(R.id.flContainerFragment, fragment, tag);

//        }
        if (addToStack) {
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    public static void clearBackStack(FragmentManager fragmentManager) {

        for (int i = 0; i < fragmentManager.getBackStackEntryCount(); i++) {
            fragmentManager.popBackStack();
        }
    }

}
