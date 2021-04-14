package com.example.androidtv3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;

import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;

public class CarPresenter extends Presenter {
    private static final String TAG = "TAG1";
    private static final int C_WIDTH = 300;
    private static final int C_HEIGHT = 200;
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        Log.d(TAG, "*** onCreateViewHolder - wywolano ***");
        mContext = parent.getContext();

        ImageCardView cardView = new ImageCardView(new ContextThemeWrapper(mContext, R.style.Theme_Leanback));
        cardView.setFocusable(true);
        cardView.setFocusableInTouchMode(true);
        cardView.setBackgroundColor(mContext.getResources().getColor(R.color.header_background));
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Log.d(TAG, "onBindViewHolder");
        Car car = (Car) item;

        ImageCardView cardView = (ImageCardView) viewHolder.view;
        
        cardView.setTitleText(car.brand);
        cardView.setContentText(car.model);
        cardView.setMainImage(car.image);
        cardView.setMainImageDimensions(C_WIDTH, C_HEIGHT);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        Log.d(TAG, "*** onUnbindViewHolder - wywolano ***");
        ImageCardView cardView = (ImageCardView) viewHolder.view;
        cardView.setMainImage(null);
    }
}

