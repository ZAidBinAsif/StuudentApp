package com.andrinotech.studentapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

/**
 * Created by Zaid on 16/10/2017.
 */
public class GlideHelper {
    public static void loadImage(Context context, String filePath, ImageView view, @DrawableRes int placeHolder) {
        Glide.with(context)
                .load(filePath).dontAnimate()
                .fitCenter()
                .error(placeHolder)
                .placeholder(placeHolder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    public static void loadImage(Context context, Bitmap bitmap, ImageView view, @DrawableRes int placeHolder) {
        Glide.with(context)
                .load(bitmap).dontAnimate()
                .fitCenter()
                .error(placeHolder)
                .placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    public static void loadImage(Context context, ImageView view, @DrawableRes int placeHolder) {
        Glide.with(context)
                .load(placeHolder)
                .placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

    public static void loadRoundedImage(final Context context, String filePath, ImageView view, @DrawableRes int placeHolder) {
        Glide.with(context)
                .load(filePath)
                .asBitmap()
                .thumbnail(0.1f)
                .centerCrop()
                .placeholder(placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new ImageViewTarget<Bitmap>(view) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        view.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }
}
