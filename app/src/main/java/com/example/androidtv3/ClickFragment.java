package com.example.androidtv3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.leanback.app.ErrorSupportFragment;

public class ClickFragment extends ErrorSupportFragment {
    private static final String TAG = ClickFragment.class.getSimpleName();
    private static final boolean TRANSLUCENT = true;
    private static final String FIRST = "AUTO 01";
    private static final String SECOND = "AUTO 02";
    private static final String THIRD = "AUTO 03";
    private final String choice;
    private final int firstId;
    private final int secondId;
    private final int thirdId;
    private final int firstText;
    private final int secondText;
    private final int thirdText;

    public ClickFragment(String chosenCar, int firstCarId, int secondCarId, int thirdCarId,
                         int firstCarText, int secondCarText, int thirdCarText){
        choice = chosenCar;
        firstId = firstCarId;
        firstText = firstCarText;
        secondId = secondCarId;
        secondText = secondCarText;
        thirdId = thirdCarId;
        thirdText = thirdCarText;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        setTitle(getResources().getString(R.string.app_name));
        setClickContent();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    void setClickContent() {
        switch (choice) {
            case FIRST:
                setImageDrawable(getActivity().getDrawable(firstId));
                setMessage(getResources().getString(firstText));
                break;
            case SECOND:
                setImageDrawable(getActivity().getDrawable(secondId));
                setMessage(getResources().getString(secondText));
                break;
            case THIRD:
                setImageDrawable(getActivity().getDrawable(thirdId));
                setMessage(getResources().getString(thirdText));
                break;
            default:
                setImageDrawable(getActivity().getDrawable(R.drawable.lb_ic_sad_cloud));
                setMessage(getResources().getString(R.string.error));
                break;
        }
        setDefaultBackground(TRANSLUCENT);

        setButtonText(getResources().getString(R.string.dismiss_error));
        setButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent returnIntent = getActivity().getIntent();
                returnIntent.putExtra("RESULT", getActivity().getIntent().getStringExtra("COUNTRY"));
                getFragmentManager().beginTransaction().remove(ClickFragment.this).commit();
                getActivity().finish();
            }
        });
    }
}
