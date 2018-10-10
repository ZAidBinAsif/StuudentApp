package com.andrinotech.studentapp.ui.Post;


import com.andrinotech.studentapp.StudentApp;
import com.andrinotech.studentapp.ui.base.BaseViewModel;
import com.andrinotech.studentapp.utils.NetworkUtils;
import com.andrinotech.studentapp.utils.StringUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PostViewModel extends BaseViewModel<PostCallback> {
    public enum validationEnum {
        title, descrtion, category
    }

    public boolean areFieldsValid(String title, String descriptoon, String category) {
        boolean isValid = true;
        if (StringUtils.isNullOrEmpty(title)) {
            getmCallback().ValidationError(validationEnum.title);
            isValid = false;
        }
        if (StringUtils.isNullOrEmpty(descriptoon)) {
            getmCallback().ValidationError(validationEnum.descrtion);
            isValid = false;
        }
        if (StringUtils.isNullOrEmpty(category)) {
            getmCallback().ValidationError(validationEnum.category);
            isValid = false;
        }

        return isValid;
    }


    public void likepost(int id, final int pos) {
        getmCompositeDisposable().add(getmRequestHandler()
                .addlike(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LikePostResponseModel>() {
                    @Override
                    public void accept(LikePostResponseModel addPostResponseModel) throws Exception {
                        if (addPostResponseModel == null) {
                            getmCallback().ErrorOnAddPost("Not Available");
                            return;
                        } else if (addPostResponseModel.getError().getCode() == 302) {
                            getmCallback().ErrorOnAddPost(addPostResponseModel.getError().getMessage());
                            return;
                        }
                        getmCallback().likeAdded(pos, addPostResponseModel);

                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                            getmCallback().ErrorOnAddPost("Check your internet connection");

                        } else {
                            getmCallback().ErrorOnAddPost("No Internet Connection");

                        }

                    }
                }));

    }

    public void unlikepost(int id, final int pos) {
        getmCompositeDisposable().add(getmRequestHandler()
                .posUnlike(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<LikePostResponseModel>() {
                    @Override
                    public void accept(LikePostResponseModel addPostResponseModel) throws Exception {
                        if (addPostResponseModel == null) {
                            getmCallback().ErrorOnAddPost("Not Available");
                            return;
                        } else if (addPostResponseModel.getError().getCode() == 302) {
                            getmCallback().ErrorOnAddPost(addPostResponseModel.getError().getMessage());
                            return;
                        }
                        getmCallback().likeAdded(pos, addPostResponseModel);

                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                            getmCallback().ErrorOnAddPost("Check your internet connection");
                        } else {
                            getmCallback().ErrorOnAddPost("No Internet Connection");

                        }

                    }
                }));

    }

    public void getPosts() {
        getmCompositeDisposable().add(getmRequestHandler()
                .getAllPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<AllPostResponseModel>() {
                    @Override
                    public void accept(AllPostResponseModel getPostResponseModel) throws Exception {
                        if (getPostResponseModel == null) {
                            getmCallback().ErrorOnAddPost("Not Available");
                            return;
                        } else if (getPostResponseModel.getError().getCode() == 302) {
                            getmCallback().ErrorOnAddPost(getPostResponseModel.getError().getMessage());
                            return;
                        }
                        getmCallback().allposts(getPostResponseModel.getPostModel());

                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                            getmCallback().ErrorOnAddPost("Check your internet connection");

                        } else {
                            getmCallback().ErrorOnAddPost("No Internet Connection");

                        }

                    }
                }));
    }

    public void getPostsofUstad(int id) {
        getmCompositeDisposable().add(getmRequestHandler()
                .getPostOfUstad(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<AllPostResponseModel>() {
                    @Override
                    public void accept(AllPostResponseModel getPostResponseModel) throws Exception {
                        if (getPostResponseModel == null) {
                            getmCallback().ErrorOnAddPost("Not Available");
                            return;
                        } else if (getPostResponseModel.getError().getCode() == 302) {
                            getmCallback().ErrorOnAddPost(getPostResponseModel.getError().getMessage());
                            return;
                        }
                        getmCallback().allposts(getPostResponseModel.getPostModel());

                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                            getmCallback().ErrorOnAddPost("Check your internet connection");

                        } else {
                            getmCallback().ErrorOnAddPost("No Internet Connection");

                        }

                    }
                }));
    }

    public void getPostComment(int id) {
        getmCompositeDisposable().add(getmRequestHandler()
                .getAllPostComment(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CommentResponseModel>() {
                    @Override
                    public void accept(CommentResponseModel getPostResponseModel) throws Exception {
                        if (getPostResponseModel == null) {
                            getmCallback().ErrorOnAddPost("Not Available");
                            return;
                        } else if (getPostResponseModel.getError().getCode() == 302) {
                            getmCallback().ErrorOnAddPost(getPostResponseModel.getError().getMessage());
                            return;
                        }
                        getmCallback().allcomments(getPostResponseModel.getComments());

                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                            getmCallback().ErrorOnAddPost("Check your internet connection");

                        } else {
                            getmCallback().ErrorOnAddPost("No Internet Connection");

                        }

                    }
                }));
    }

    public void addComment(String text, int id) {
        getmCompositeDisposable().add(getmRequestHandler()
                .addComment(id, text)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<CommentResponseModel>() {
                    @Override
                    public void accept(CommentResponseModel addPostResponseModel) throws Exception {
                        if (addPostResponseModel == null) {
                            getmCallback().ErrorOnAddPost("Not Available");
                            return;
                        } else if (addPostResponseModel.getError().getCode() == 302) {
                            getmCallback().ErrorOnAddPost(addPostResponseModel.getError().getMessage());
                            return;
                        }
                        getmCallback().allcomments(addPostResponseModel.getComments());

                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (NetworkUtils.isNetworkConnected(StudentApp.getInstance().getApplicationContext())) {
                            getmCallback().ErrorOnAddPost("Check your internet connection");

                        } else {
                            getmCallback().ErrorOnAddPost("No Internet Connection");

                        }

                    }
                }));


    }
}
