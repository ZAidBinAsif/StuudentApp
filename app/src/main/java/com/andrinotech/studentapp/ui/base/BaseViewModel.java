package com.andrinotech.studentapp.ui.base;


import android.arch.lifecycle.ViewModel;

import com.andrinotech.studentapp.network.RequestHandler;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by ZaidAs on 2/28/2018.
 */

public abstract class BaseViewModel<C extends BaseCallBack> extends ViewModel {
    private C mCallback;
    private CompositeDisposable mCompositeDisposable;
    private RequestHandler mRequestHandler;

    public BaseViewModel() {
        mRequestHandler = new RequestHandler();
        mCompositeDisposable = new CompositeDisposable();
    }

    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void setmCompositeDisposable(CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    public RequestHandler getmRequestHandler() {
        return mRequestHandler;
    }

    protected C getmCallback() {
        return mCallback;
    }

    public void setCallBack(C callBack) {
        this.mCallback = callBack;
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
