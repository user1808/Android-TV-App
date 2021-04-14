package com.example.androidtv3;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

import java.util.List;

public class BlankFragment extends BrowseSupportFragment {
    private static final int REQUEST_CODE = 200;

    private static final String MainFragmentTag = "NEVER";
    private static final String FIRST = "AUTO 01";
    private static final String SECOND = "AUTO 02";
    private static final String THIRD = "AUTO 03";
    private static final String VIDEO = "Video";

    private String country;

    public BlankFragment() { }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }*/

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        country = "POL";
        Log.i(MainFragmentTag, "* MainF - onActivityCreated");
        setupUIElements();
        loadRows();
        setupEventListeners();
    }

    private void setupUIElements(){
        setTitle("My First TV App");
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);
        setBrandColor(getResources().getColor(R.color.header_background));
        setSearchAffordanceColor(getResources().getColor(R.color.search_color));
    }

    private void loadRows(){
        ArrayObjectAdapter myRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

        HeaderItem gridItemPresenterHeader = new HeaderItem(0, "Auta");
        ItemGridPresenter myGridPresenter = new ItemGridPresenter();
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(myGridPresenter);
        gridRowAdapter.add(FIRST);
        gridRowAdapter.add(SECOND);
        gridRowAdapter.add(THIRD);
        myRowsAdapter.add(new ListRow(gridItemPresenterHeader, gridRowAdapter));

        gridItemPresenterHeader = new HeaderItem(1, "Inne");
        myGridPresenter = new ItemGridPresenter();
        gridRowAdapter = new ArrayObjectAdapter(myGridPresenter);
        gridRowAdapter.add("Opcje");
        gridRowAdapter.add("Toast");
        gridRowAdapter.add("Video");
        myRowsAdapter.add(new ListRow(gridItemPresenterHeader, gridRowAdapter));

        List<Car> carList = CarList.buildListCard(getContext(), 3);
        HeaderItem cardPresenterHeader = new HeaderItem(2, "Prezentacja aut");
        CarPresenter carPresenter = new CarPresenter();
        ArrayObjectAdapter cardRowAdapter = new ArrayObjectAdapter(carPresenter);
        for(int i = 0; i < 3; i++){
            assert carList != null;
            cardRowAdapter.add(carList.get(i));
        }
        myRowsAdapter.add(new ListRow(cardPresenterHeader, cardRowAdapter));
        setAdapter(myRowsAdapter);
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {

        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
            if(item instanceof String){
                Intent intent = new Intent(getActivity(), ClickActivity.class);
                intent.putExtra("COUNTRY", country);
                if(item.equals(FIRST)){
                    intent.putExtra("AUTO", FIRST);
                    startActivityForResult(intent, REQUEST_CODE);
                } else if(item.equals(SECOND)){
                    intent.putExtra("AUTO", SECOND);
                    startActivityForResult(intent, REQUEST_CODE);
                } else if(item.equals(THIRD)) {
                    intent.putExtra("AUTO", THIRD);
                    startActivityForResult(intent, REQUEST_CODE);
                } else if(item.equals("Opcje")) {
                    intent = new Intent(getActivity(), GuidedStepActivity.class);
                    intent.putExtra("COUNTRY", country);
                    startActivityForResult(intent, REQUEST_CODE);
                } else if(item.equals(VIDEO)) {
                    intent = new Intent(getActivity(), PlaybackActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), ((String) item), Toast.LENGTH_LONG).show();
                }
            } else if(item instanceof Car){
                Intent intent = new Intent(getActivity(), DetailsActivity.class);
                intent.putExtra("CAR", (Parcelable) item);
                startActivity(intent);
            }
        }
    }

    private void setupEventListeners(){
        setOnSearchClickedListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Implement own search ", Toast.LENGTH_LONG).show();
            }
        });
        setOnItemViewClickedListener(new ItemViewClickedListener());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null)
            country = data.getStringExtra("RESULT");
    }
}