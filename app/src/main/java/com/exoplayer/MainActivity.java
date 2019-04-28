package com.exoplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.exoplayer.custom.VerticalSpacingItemDecorator;
import com.exoplayer.ui.MediaObject;
import com.exoplayer.ui.VideoPlayerRecyclerAdapter;
import com.exoplayer.ui.VideoPlayerRecyclerView;
import com.exoplayer.util.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VideoPlayerRecyclerView mRecyclerView;
    private VideoPlayerRecyclerAdapter mVideoPlayerRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);
        mRecyclerView.setMediaObjects(getMediaObjects());
        setAdapter();
    }

    public List<MediaObject> getMediaObjects() {
        return new ArrayList<>(Arrays.asList(Resources.MEDIA_OBJECTS));
    }

    public void setAdapter() {
        if (mVideoPlayerRecyclerAdapter == null) {
            mVideoPlayerRecyclerAdapter = new VideoPlayerRecyclerAdapter(getMediaObjects(), initGlide());
            mRecyclerView.setAdapter(mVideoPlayerRecyclerAdapter);
        } else if (mRecyclerView.getAdapter() == null) {
            mRecyclerView.setAdapter(mVideoPlayerRecyclerAdapter);
        } else {
            mVideoPlayerRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private RequestManager initGlide() {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(this)
                .setDefaultRequestOptions(options);
    }

    @Override
    protected void onDestroy() {
        if (mRecyclerView != null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }
}
