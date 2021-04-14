package com.example.androidtv3;

import androidx.leanback.widget.AbstractDetailsDescriptionPresenter;

public class DetailsDescriptionPresenter extends AbstractDetailsDescriptionPresenter {
    @Override
    protected void onBindDescription(ViewHolder viewHolder, Object item) {
        Car details = (Car) item;
        viewHolder.getTitle().setText(details.brand);
        viewHolder.getSubtitle().setText(details.model);
        viewHolder.getBody().setText(details.description);
    }
}
