package com.example.androidtv3;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.leanback.app.DetailsSupportFragment;
import androidx.leanback.widget.Action;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnActionClickedListener;

public class CarDetailsFragment extends DetailsSupportFragment {
    private static final String TAG = "CarDetailsFragment";
    private static final int BUY_ACTION = 1;
    private static final int RENT_ACTION = 2;
    private static final int BACK_ACTION = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        buildDetails();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void buildDetails() {
        ClassPresenterSelector selector = new ClassPresenterSelector();

        FullWidthDetailsOverviewRowPresenter rowPresenter =
                new FullWidthDetailsOverviewRowPresenter(
                        new DetailsDescriptionPresenter());
        rowPresenter.setOnActionClickedListener(new OnActionClickedListener() {
            @Override
            public void onActionClicked(Action action) {
                if (action.getId() == BACK_ACTION) {
                    getActivity().finish();
                } else if(action.getId() == RENT_ACTION){
                    Toast.makeText(getContext(), "Wynajęcie", Toast.LENGTH_LONG).show();
                } else if(action.getId() == BUY_ACTION){
                    Toast.makeText(getContext(), "Kupno", Toast.LENGTH_LONG).show();
                }
            }
        });

        selector.addClassPresenter(DetailsOverviewRow.class, rowPresenter);
        selector.addClassPresenter(ListRow.class, new ListRowPresenter());
        ArrayObjectAdapter rowsAdapter = new ArrayObjectAdapter(selector);

        DetailsOverviewRow detailsOverview = new DetailsOverviewRow(
                getActivity().getIntent().getParcelableExtra("CAR"));

        Car car = getActivity().getIntent().getParcelableExtra("CAR");
        detailsOverview.addAction(new Action(BUY_ACTION, "Kup $" + car.price));
        detailsOverview.addAction(new Action(RENT_ACTION, "Wypożycz $" + car.price * 0.5));
        detailsOverview.addAction(new Action(BACK_ACTION, "Powrót"));
        detailsOverview.setImageDrawable(getResources().getDrawable(car.image_id));
        rowsAdapter.add(detailsOverview);

        setAdapter(rowsAdapter);
    }
}
