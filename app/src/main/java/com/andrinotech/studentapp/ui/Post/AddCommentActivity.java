package com.andrinotech.studentapp.ui.Post;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.helper.AVProgressDialog;
import com.andrinotech.studentapp.ui.base.BaseActivity;
import com.andrinotech.studentapp.utils.CommonUtils;
import com.andrinotech.studentapp.utils.StringUtils;

import java.util.ArrayList;

public class AddCommentActivity extends BaseActivity<PostViewModel> implements PostCallback, View.OnClickListener {
    private EditText inputAddComment;
    Button addCommentBtn;
    TextView textNotComnt;
    ProgressBar progressComments;
    private AVProgressDialog mLoadingDialog;
    private int postid;
    private ListView listComments;
    private CustomCommentsAdapter adapter;
    private ArrayList<Comment> postModelResponses;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getViewModel().setCallBack(this);
        initViews();

    }

    @Override
    public int getLayout() {
        return R.layout.write_cmt_activity;

    }


    @Override
    public PostViewModel initViewModel() {
        return new PostViewModel();
    }

    public void initViews() {
        if (getIntent() != null && getIntent().getStringExtra("id") != null) {
            postid = Integer.parseInt(getIntent().getStringExtra("id"));
            getViewModel().getPostComment(postid);
        } else {

            finish();
        }

        listComments = (ListView) findViewById(R.id.listComments);

        addCommentBtn = findViewById(R.id.addCommentBtn);
        progressComments = findViewById(R.id.progressComments);
        inputAddComment = findViewById(R.id.inputAddComment);
        textNotComnt = findViewById(R.id.textNotComnt);
        addCommentBtn.setOnClickListener(this);
        mLoadingDialog = new AVProgressDialog(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.addCommentBtn:
                addpost();
                break;

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


    private void addpost() {
        mLoadingDialog.show();
        String comment = inputAddComment.getText().toString();
        if (StringUtils.isNullOrEmpty(comment)) {
            CommonUtils.showToast("Please add some text");
        } else {
            getViewModel().addComment(comment, postid);

        }

    }


    @Override
    public void ValidationError(PostViewModel.validationEnum validationEnum) {
        if (validationEnum.equals(validationEnum.title)) {
            CommonUtils.showToast("title required");
        }
        if (validationEnum.equals(validationEnum.descrtion)) {
            CommonUtils.showToast("Description required");
        }
        if (validationEnum.equals(validationEnum.category)) {
            CommonUtils.showToast("category required");
        }
        dismissDialog();

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
        listComments.setVisibility(View.GONE);
        textNotComnt.setVisibility(View.VISIBLE);
        progressComments.setVisibility(View.GONE);
        textNotComnt.setText(str);

    }

    @Override
    public void InternetError(String str) {
        dismissDialog();
        CommonUtils.showToast(str);
        listComments.setVisibility(View.GONE);
        textNotComnt.setVisibility(View.VISIBLE);
        progressComments.setVisibility(View.GONE);
        textNotComnt.setText(str);
    }

    @Override
    public void allposts(ArrayList<PostModelResponse> postModelResponses) {

    }

    @Override
    public void allcomments(ArrayList<Comment> postModelResponses) {
        if (postModelResponses.size() > 0) {
            progressComments.setVisibility(View.GONE);
            listComments.setVisibility(View.VISIBLE);
            adapter = new CustomCommentsAdapter();
            textNotComnt.setVisibility(View.GONE);
            this.postModelResponses = postModelResponses;
            listComments.setAdapter(adapter);

            listComments.setSelection(postModelResponses.size() - 1);
        } else {
            listComments.setVisibility(View.GONE);
            textNotComnt.setVisibility(View.VISIBLE);
            progressComments.setVisibility(View.GONE);
        }
        dismissDialog();

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    class CustomCommentsAdapter extends BaseAdapter {

        public int getCount() {
            // TODO Auto-generated method stub
            return postModelResponses.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        public View getView(final int position, View view1, ViewGroup arg2) {
            final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.custom_comments, null);

            ImageView imageComments = (ImageView) view
                    .findViewById(R.id.imageComments);


            TextView textCmntsUsername = (TextView) view
                    .findViewById(R.id.textCmntsUsername);
            final TextView textCmntsData = (TextView) view
                    .findViewById(R.id.textCmntsData);
            TextView textCmntsDate = (TextView) view
                    .findViewById(R.id.textCmntsDate);
            TextView textCmntsEdit = (TextView) view
                    .findViewById(R.id.textCmntsEdit);


            return view;
        }

    }

}
