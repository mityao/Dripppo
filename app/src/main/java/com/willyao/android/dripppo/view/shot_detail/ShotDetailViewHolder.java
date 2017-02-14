package com.willyao.android.dripppo.view.shot_detail;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.willyao.android.dripppo.R;
import com.willyao.android.dripppo.view.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by mitya on 2/1/2017.
 */

class ShotDetailViewHolder extends BaseViewHolder {

   @BindView(R.id.shot_title) TextView title;
   @BindView(R.id.shot_description) TextView description;
   @BindView(R.id.shot_author_picture)
   ImageView authorPicture;
   @BindView(R.id.shot_author_name) TextView authorName;
   @BindView(R.id.shot_like_count) TextView likeCount;
   @BindView(R.id.shot_view_count) TextView viewCount;
   @BindView(R.id.shot_bucket_count) TextView bucketCount;
   @BindView(R.id.shot_action_like) ImageButton likeButton;
   @BindView(R.id.shot_action_bucket) ImageButton bucketButton;
   @BindView(R.id.shot_action_share) TextView shareButton;

   public ShotDetailViewHolder(View itemView) {
      super(itemView);
   }
}

