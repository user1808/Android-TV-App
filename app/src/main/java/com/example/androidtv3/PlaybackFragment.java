package com.example.androidtv3;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.leanback.app.VideoSupportFragment;

import java.util.Objects;

public class PlaybackFragment extends VideoSupportFragment {
    VideoView videoView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoView = Objects.requireNonNull(getActivity()).findViewById(R.id.video_view);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Objects.requireNonNull(getActivity()).finish();
            }
        });

        initializePlayer();
    }

    private void initializePlayer(){
        String videoUrl = "android.resource://" + Objects.requireNonNull(getActivity()).getPackageName() + "/" + R.raw.drift;
        MediaController mediacontroller = new MediaController(getActivity());
        mediacontroller.setAnchorView(videoView);
        Uri video = Uri.parse(videoUrl);
        videoView.setMediaController(mediacontroller);
        videoView.setVideoURI(video);
    }
}
