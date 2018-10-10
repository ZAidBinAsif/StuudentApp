package com.andrinotech.studentapp.ui.Post;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrinotech.studentapp.R;
import com.andrinotech.studentapp.utils.StringUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<PostModelResponse> modellist;
    ArrayList<PostModelResponse> arrayList;

    public interface ClickListener {
        public void onFavClick(int pos, PostModelResponse model);

        public void onFavDeleteClick(int pos, PostModelResponse model);
    }

    ClickListener clickListener;

    public PostAdapter(Context context, List<PostModelResponse> modellist, ClickListener clickListener) {
        mContext = context;
        this.clickListener = clickListener;
        this.modellist = modellist;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<PostModelResponse>();
        this.arrayList.addAll(modellist);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView_logo, unlike, like, comment;
        TextView name, category, title, time, status, decription, totallike, totalunlike;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            category = view.findViewById(R.id.category);
            title = view.findViewById(R.id.title);
            time = view.findViewById(R.id.time);
            decription = view.findViewById(R.id.decription);
            imageView_logo = view.findViewById(R.id.imageView_logo);
            comment = view.findViewById(R.id.comment);
            unlike = view.findViewById(R.id.unlike);
            like = view.findViewById(R.id.like);
            totallike = view.findViewById(R.id.totallike);
            totalunlike = view.findViewById(R.id.totalunlike);
            status = view.findViewById(R.id.status);

        }


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int postition) {
        final PostModelResponse model = arrayList.get(postition);
        Glide.with(mContext)
                .load(arrayList.get(postition).getUstad().getLogo()).dontAnimate()
                .fitCenter()
                .error(R.drawable.ic_profile_plc)
                .placeholder(R.drawable.ic_profile_plc)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView_logo);
        holder.category.setText(model.getCategory());
        holder.decription.setText(model.getText());
        holder.name.setText(model.getUstad().getName());
        holder.title.setText(model.getTitle());
        holder.status.setText(model.getUstad().getStatus());


        if (model.getLike() != null && model.getLike().getType().equalsIgnoreCase("like")) {
            holder.like.setImageResource(R.drawable.ic_thumb_like);
        } else if (model.getLike() != null && model.getLike().getType().equalsIgnoreCase("unlike")) {
            holder.unlike.setImageResource(R.drawable.ic_thumb_unlike);
        } else {
            holder.like.setImageResource(R.drawable.ic_thumb);
            holder.unlike.setImageResource(R.drawable.ic_unlike);
        }
        holder.totallike.setText(model.getTotal_like());
        holder.totalunlike.setText(model.getTotal_unlike());
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onFavClick(postition, model);
            }
        });
        holder.unlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onFavDeleteClick(postition, model);
            }
        });
        holder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddCommentActivity.class);
                intent.putExtra("id", model.getId());
                mContext.startActivity(intent);
            }
        });
        holder.imageView_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UstadProfile.class);
                intent.putExtra("ustad", StringUtils.getGson().toJson(model));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

//    //filter
//    public void filter(String charText) {
//        charText = charText.toLowerCase(Locale.getDefault());
//        modellist.clear();
//        if (charText.length() == 0) {
//            modellist.addAll(arrayList);
//        } else {
//            for (LawModel model : arrayList) {
//                //                    if(modelCategories.get(i).getCategory().contains(keyword)|| modelCategories.get(i).getCategoryname().contains(keyword)|| modelCategories.get(i).getCatdetails().contains(keyword)){
//
//                if (model.getTitle().toLowerCase(Locale.getDefault())
//                        .contains(charText) || model.getDesc().toLowerCase(Locale.getDefault()).contains(charText)) {
//                    modellist.add(model);
//
//                }
//            }
//        }
//        notifyDataSetChanged();
//    }
}