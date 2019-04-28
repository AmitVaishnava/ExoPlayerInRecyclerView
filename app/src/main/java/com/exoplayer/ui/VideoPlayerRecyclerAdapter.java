package com.exoplayer.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.exoplayer.R;

import java.util.List;

public class VideoPlayerRecyclerAdapter extends RecyclerView.Adapter<VideoPlayerRecyclerAdapter.VideoPlayerViewHolder> {

    private List<MediaObject> mMediaObjects;
    private RequestManager mRequestManager;

    public VideoPlayerRecyclerAdapter(List<MediaObject> mediaObjects, RequestManager requestManager) {
        mMediaObjects = mediaObjects;
        mRequestManager = requestManager;
    }

    @NonNull
    @Override
    public VideoPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new VideoPlayerViewHolder(
                LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_video_list_item, viewGroup, false));

    }

    @Override
    public void onBindViewHolder(@NonNull VideoPlayerViewHolder viewHolder, int i) {
        ((VideoPlayerViewHolder) viewHolder).onBind(mMediaObjects.get(i), mRequestManager);
    }

    @Override
    public int getItemCount() {
        return mMediaObjects == null ? 0 : mMediaObjects.size();
    }

    public class VideoPlayerViewHolder extends RecyclerView.ViewHolder {
        private FrameLayout media_container;
        private TextView title;
        protected ImageView thumbnail, volumeControl;
        protected ProgressBar progressBar;
        private View parent;
        protected RequestManager requestManager;

        public VideoPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
            media_container = itemView.findViewById(R.id.media_container);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            progressBar = itemView.findViewById(R.id.progressBar);
            volumeControl = itemView.findViewById(R.id.volume_control);
        }

        public void onBind(MediaObject mediaObject, RequestManager requestManager) {
            this.requestManager = requestManager;
            parent.setTag(this);
            title.setText(mediaObject.title);
            this.requestManager
                    .load(mediaObject.thumbnail)
                    .into(thumbnail);
        }
    }
}
