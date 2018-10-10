package com.andrinotech.studentapp.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ZaidAs on 2/28/2018.
 */

public abstract class BaseFragment<V extends BaseViewModel> extends Fragment {
    private V mViewModel;
    public View mRootView;
    public FragmentNavigation mFragmentNavigation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewModel = initViewHolder();

        mRootView = inflater.inflate(getLayout(),
                container, false);
        return mRootView;
    }

    public V getViewModel() {
        return mViewModel;
    }

    public abstract int getLayout();

    public abstract V initViewHolder();

    public interface FragmentNavigation {
        public void pushFragment(Fragment fragment);

        public void popFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentNavigation) {
            mFragmentNavigation = (FragmentNavigation) context;
        }
    }
}
