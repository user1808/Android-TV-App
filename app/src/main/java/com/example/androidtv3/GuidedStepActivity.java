package com.example.androidtv3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.leanback.app.GuidedStepSupportFragment;
import androidx.leanback.widget.GuidanceStylist;
import androidx.leanback.widget.GuidedAction;

import java.util.List;

public class GuidedStepActivity extends FragmentActivity {

    private static final String TAG = GuidedStepActivity.class.getSimpleName();

    private static final int ACTION_CONTINUE_CLASSIC = 0;
    private static final int ACTION_CONTINUE_MODERN = 1;
    private static final int ACTION_BACK = 2;

    private static final String POLAND = "POL";
    private static final String POLAND_NAME = "POLSKA";
    private static final int POLAND_ID = 3;
    private static final String GERMAN = "GER";
    private static final String GERMAN_NAME = "NIEMCY";
    private static final int GERMAN_ID = 4;
    private static final String ITALY = "IT";
    private static final String ITALY_NAME = "WŁOCHY";
    private static final int ITALY_ID = 5;
    private static final String JAPAN = "JPN";
    private static final String JAPAN_NAME = "JAPONIA";
    private static final int JAPAN_ID = 6;
    private static final String BRITISH = "UK";
    private static final String BRITISH_NAME = "WLK. BRYTANIA";
    private static final int BRITISH_ID = 7;
    private static final String FRANCE = "FR";
    private static final String FRANCE_NAME = "FRANCJA";
    private static final int FRANCE_ID = 8;

    private static String country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        country = getIntent().getStringExtra("COUNTRY");
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState) {
            GuidedStepSupportFragment.add(getSupportFragmentManager(), new FirstStepFragment());
        }
    }

    private static void addAction(List<GuidedAction> actions, long id, String title, String desc) {
        actions.add(new GuidedAction.Builder()
                .id(id)
                .title(title)
                .description(desc)
                .build());
    }

    public static class FirstStepFragment extends GuidedStepSupportFragment {
        @Override
        public int onProvideTheme() {
            return R.style.Theme_Leanback_GuidedStep;
        }

        @NonNull
        @Override
        public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState) {
            String title = "Opcje";
            String breadcrumb = "Guided Steps: 1";
            String description = "Pierwszy poziom";
            @SuppressLint("UseCompatLoadingForDrawables") Drawable icon = getActivity().getDrawable(R.drawable.ic_main_icon);

            return new GuidanceStylist.Guidance(title, description, breadcrumb, icon);
        }

        @Override
        public void onCreateActions(@NonNull List<GuidedAction> actions, Bundle savedInstanceState) {
            addAction(actions, ACTION_CONTINUE_CLASSIC, "Klasyczne Auta", "Wybór klasyków");
            addAction(actions, ACTION_CONTINUE_MODERN, "Auta współczesne", "Wybór aut współczesnych");
            addAction(actions, ACTION_BACK, "Powrót", "Zamknij opcje");
        }

        @Override
        public void onGuidedActionClicked(GuidedAction action) {
            FragmentManager fm;
            switch ((int) action.getId()){
                case ACTION_CONTINUE_CLASSIC:
                    fm = getFragmentManager();
                    GuidedStepSupportFragment.add(fm, new SecondStepFragment(ACTION_CONTINUE_CLASSIC));
                    break;
                case ACTION_CONTINUE_MODERN:
                    fm = getFragmentManager();
                    GuidedStepSupportFragment.add(fm, new SecondStepFragment(ACTION_CONTINUE_MODERN));
                    break;
                case ACTION_BACK:
                    getActivity().finish();
                    break;
                default:
                    Log.w(TAG, "Action is not defined");
                    break;
            }
        }
    }

    public static class SecondStepFragment extends GuidedStepSupportFragment {
        private static int modeOn;
        public SecondStepFragment(int mode){
            modeOn = mode;
        }
        @Override
        public int onProvideTheme() {
            return R.style.Theme_Leanback_GuidedStep;
        }
        @NonNull
        @Override
        public GuidanceStylist.Guidance onCreateGuidance(Bundle savedInstanceState) {
            String title = "Następny poziom";
            String breadcrumb = "Guided Steps: 2";
            String description ="Wybierz państwo";
            @SuppressLint("UseCompatLoadingForDrawables") Drawable icon = getActivity().getDrawable(R.drawable.ic_main_icon);
            return new GuidanceStylist.Guidance(title, description, breadcrumb, icon);
        }

        @Override
        public void onCreateActions(List<GuidedAction> actions, Bundle savedInstanceState) {
            String title = "OPIS";
            String desc = "Wybierz z poniższych państw te które cię interesuje\n" +
                    "Jeżeli nie chcesz nic wybierać wciśnij przycisk Powrót";

            actions.add(new GuidedAction.Builder(getContext())
                    .title(title)
                    .description(desc)
                    .multilineDescription(true)
                    .infoOnly(true)
                    .enabled(false)
                    .build());
            if(modeOn == ACTION_CONTINUE_CLASSIC){
                addAction(actions, POLAND_ID, POLAND_NAME, "Nasze, polskie marki");
                addAction(actions, GERMAN_ID, GERMAN_NAME, "Auta z NRD i RFN");
                addAction(actions, ITALY_ID, ITALY_NAME, "Włoskie klasyki");
            } else if(modeOn == ACTION_CONTINUE_MODERN){
                addAction(actions, JAPAN_ID, JAPAN_NAME, "JDM Fanclub");
                addAction(actions, BRITISH_ID, BRITISH_NAME, "Clarkson lubi to (chyba)");
                addAction(actions, FRANCE_ID, FRANCE_NAME, "Auta znad Loary");
            } else {
                Log.w(TAG, "Action is not defined");
            }
            addAction(actions, ACTION_BACK, "Powrót", "Zamknij opcje");
        }

        @Override
        public void onGuidedActionClicked(GuidedAction action) {
            Intent returnIntent = getActivity().getIntent();
            switch ((int) action.getId()){
                case POLAND_ID:
                    country = POLAND;
                    break;
                case GERMAN_ID:
                    country = GERMAN;
                    break;
                case ITALY_ID:
                    country = ITALY;
                    break;
                case JAPAN_ID:
                    country = JAPAN;
                    break;
                case BRITISH_ID:
                    country = BRITISH;
                    break;
                case FRANCE_ID:
                    country = FRANCE;
                    break;
            }
            returnIntent.putExtra("RESULT", country);
            getActivity().setResult(Activity.RESULT_OK, returnIntent);
            getActivity().finish();
        }
    }
}
