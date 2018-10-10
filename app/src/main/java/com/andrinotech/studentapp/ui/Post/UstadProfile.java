package com.andrinotech.studentapp.ui.Post;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.databinding.ProfilePageOfustadBinding;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.ui.profile.UstadProfileDetail;
import com.andrinotech.studentapp.utils.CommonUtils;
import com.andrinotech.studentapp.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class UstadProfile extends BaseActivity<PostViewModel> implements PostCallback, View.OnClickListener {
    private AVProgressDialog mLoadingDialog;
    private ProfilePageOfustadBinding binding;
    private PostModelResponse ustad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        binding = DataBindingUtil.setContentView(this, R.layout.profile_page_ofustad);
        initViews();

    }

    @Override
    public int getLayout() {
        return R.layout.profile_page_ofustad;

    }


    @Override
    public PostViewModel initViewModel() {
        return new PostViewModel();
    }

    public void initViews() {
        mLoadingDialog = new AVProgressDialog(this);

        getPosts();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }
    }


    private void dismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing())
            mLoadingDialog.dismiss();
    }

    public void getPosts() {

        if (getIntent() != null && getIntent().getStringExtra("ustad") != null) {
            String ustadjson = getIntent().getStringExtra("ustad");
            ustad = StringUtils.getGson().fromJson(ustadjson, PostModelResponse.class);
            if (ustad == null) {
                finish();
            } else {
                getViewModel().getPostsofUstad(Integer.parseInt(ustad.getUstad().getId()));

                binding.name.setText(ustad.getUstad().getName());
                binding.price.setText(ustad.getUstad().getPrice());
                binding.categoryustad.setText(ustad.getCategory());
                binding.skilltext.setText(ustad.getUstad().getSkils());
                binding.personaltext.setText(ustad.getUstad().getInfo());
                binding.personalskill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UstadProfile.this, UstadProfileDetail.class);
                        intent.putExtra("ustad", StringUtils.getGson().toJson(ustad));
                        startActivity(intent);
                    }
                });
                binding.readskill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(UstadProfile.this, UstadProfileDetail.class);
                        intent.putExtra("ustad", StringUtils.getGson().toJson(ustad));
                        startActivity(intent);
                    }
                });
            }
        } else {
            finish();
        }
        mLoadingDialog.show();

    }

    @Override
    public void ValidationError(PostViewModel.validationEnum validationEnum) {


    }

    @Override
    public void SuccessFullyAdded() {
        dismissDialog();
    }

    @Override
    public void likeAdded(int post, LikePostResponseModel likePostResponseModel) {

    }

    @Override
    public void ErrorOnAddPost(String str) {
        dismissDialog();
        CommonUtils.showToast(str);

    }

    @Override
    public void InternetError(String str) {
        dismissDialog();
        CommonUtils.showToast(str);

    }

    @Override
    public void allposts(ArrayList<PostModelResponse> postModelResponses) {
        dismissDialog();
        if (postModelResponses.size() > 0) {
            final PostModelResponse model = postModelResponses.get(0);
            Glide.with(this)
                    .load(postModelResponses.get(0).getUstad().getLogo()).dontAnimate()
                    .fitCenter()
                    .error(R.drawable.ic_profile_plc)
                    .placeholder(R.drawable.ic_profile_plc)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.imageViewLogo);
            binding.category.setText(model.getCategory());
            binding.decription.setText(model.getText());
            binding.postname.setText(model.getUstad().getName());
            binding.title.setText(model.getTitle());
            binding.status.setText(model.getUstad().getStatus());
            binding.view9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(UstadProfile.this, UstadProfileDetail.class);
                    intent.putExtra("ustad", StringUtils.getGson().toJson(ustad));
                    startActivity(intent);
                }
            });
        } else {
            binding.newfeedlayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void allcomments(ArrayList<Comment> postModelResponses) {

    }

    @Override
    public void onBackPressed() {
        finish();
    }


}
