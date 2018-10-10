package com.andrinotech.studentapp.ui.Post;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseFragment;
import com.andrinotech.studentapp.utils.CommonUtils;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ShowPostFragment extends BaseFragment<PostViewModel> implements PostCallback, PostAdapter.ClickListener {
    private AVProgressDialog mLoadingDialog;

    PostAdapter adapter;
    TextView emptyview;
    private RecyclerView recyclerView;
    private ArrayList<PostModelResponse> postModelResponses = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        getViewModel().setCallBack(this);
        mLoadingDialog = new AVProgressDialog(getContext());
        initViews();
        return mRootView;
    }

    private void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_news_feed;
    }

    @Override
    public PostViewModel initViewHolder() {
        return new PostViewModel();
    }

    @Override
    public void ValidationError(PostViewModel.validationEnum validationEnum) {

    }

    @Override
    public void SuccessFullyAdded() {
        emptyview.setVisibility(View.VISIBLE);
        dismissDialog();
    }

    @Override
    public void likeAdded(int pos, LikePostResponseModel likePostResponseModel) {
        postModelResponses.set(pos, likePostResponseModel.getPostModel());
        adapter = new PostAdapter(getContext(), postModelResponses, this);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();
        dismissDialog();
    }

    @Override
    public void ErrorOnAddPost(String str) {
        CommonUtils.showToast(str);
        dismissDialog();
        emptyview.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void InternetError(String str) {
        CommonUtils.showToast(str);
        dismissDialog();
        emptyview.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void allposts(ArrayList<PostModelResponse> postModelResponses) {
        this.postModelResponses = postModelResponses;
        adapter = new PostAdapter(getContext(), postModelResponses, this);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        dismissDialog();
    }

    @Override
    public void allcomments(ArrayList<Comment> postModelResponses) {

    }

    @Override
    public void initViews() {

        emptyview = mRootView.findViewById(R.id.emptyview);
        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PostAdapter(getContext(), new ArrayList<PostModelResponse>(), this);
        recyclerView.setAdapter(adapter);
        getPosts();
    }

    public void getPosts() {
        mLoadingDialog.show();
        getViewModel().getPosts();
    }

    @Override
    public void onFavClick(int pos, PostModelResponse model) {
        mLoadingDialog.show();
        getViewModel().likepost(Integer.parseInt(model.getId()), pos);
    }

    @Override
    public void onFavDeleteClick(int pos, PostModelResponse model) {
        mLoadingDialog.show();
        getViewModel().unlikepost(Integer.parseInt(model.getId()), pos);

    }

    @Override
    public void onResume() {
        super.onResume();
        getPosts();
    }
}