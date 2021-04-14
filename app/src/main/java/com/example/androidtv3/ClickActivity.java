package com.example.androidtv3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.FragmentActivity;

public class ClickActivity extends FragmentActivity {
    private static final String TAG = ClickActivity.class.getSimpleName();
    private static final String POLAND = "POL";
    private static final String GERMAN = "GER";
    private static final String ITALY = "IT";
    private static final String JAPAN = "JPN";
    private static final String BRITISH = "UK";
    private static final String FRANCE = "FR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String country = intent.getStringExtra("COUNTRY");
        Log.v(TAG, country);
        int firstId;
        int secondId;
        int thirdId;
        int firstMessage;
        int secondMessage;
        int thirdMessage;
        switch (country) {
            case POLAND:
                firstId = R.drawable.fiat125p;
                firstMessage = R.string.fiat_text;
                secondId = R.drawable.fso;
                secondMessage = R.string.polonez_text;
                thirdId = R.drawable.syrena;
                thirdMessage = R.string.syrena_text;
                break;
            case GERMAN:
                firstId = R.drawable.wartburg;
                firstMessage = R.string.wartburg_text;
                secondId = R.drawable.bmwe21;
                secondMessage = R.string.bmw_text;
                thirdId = R.drawable.garbus;
                thirdMessage = R.string.garbus_text;
                break;
            case ITALY:
                firstId = R.drawable.alfa_romeo_8c;
                firstMessage = R.string.alfa_romeo_text;
                secondId = R.drawable.maserati;
                secondMessage = R.string.maserati_text;
                thirdId = R.drawable.alfa_romeo_montreal;
                thirdMessage = R.string.alfa_romeo_text_montreal;
                break;
            case JAPAN:
                firstId = R.drawable.toyota_ae86;
                firstMessage = R.string.toyota_ae86_text;
                secondId = R.drawable.toyota_chaser;
                secondMessage = R.string.toyota_chaser_text;
                thirdId = R.drawable.subaru;
                thirdMessage = R.string.subaru_text;
                break;
            case BRITISH:
                firstId = R.drawable.bentley;
                firstMessage = R.string.bentley_text;
                secondId = R.drawable.jaguar;
                secondMessage = R.string.jaguar_text;
                thirdId = R.drawable.aston_martin;
                thirdMessage = R.string.aston_martin_text;
                break;
            case FRANCE:
                firstId = R.drawable.bugatti;
                firstMessage = R.string.bugatti_text;
                secondId = R.drawable.alpine;
                secondMessage = R.string.alpine_text;
                thirdId = R.drawable.reno;
                thirdMessage = R.string.renault_text;
                break;
            default:
                firstId = R.drawable.lb_ic_sad_cloud;
                firstMessage = R.string.error;
                secondId = firstId;
                secondMessage = firstMessage;
                thirdId = secondId;
                thirdMessage = secondMessage;
                break;
        }
        showCar(intent.getStringExtra("AUTO"), firstId, secondId, thirdId, firstMessage, secondMessage, thirdMessage);
    }

    private void showCar(String chosenCar, int firstCarId, int secondCarId, int thirdCarId,
                         int firstCarText, int secondCarText, int thirdCarText) {
        ClickFragment mCarFragment = new ClickFragment(chosenCar, firstCarId, secondCarId, thirdCarId,
                firstCarText, secondCarText, thirdCarText);
        getSupportFragmentManager().beginTransaction().add(R.id.main_browse_fragment, mCarFragment).commit();
    }
}
