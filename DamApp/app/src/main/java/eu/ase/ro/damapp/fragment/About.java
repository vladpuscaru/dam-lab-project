package eu.ase.ro.damapp.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import eu.ase.ro.damapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class About extends Fragment {
    private final static String PREF_SHARED_NAME = "PrefSharedName";
    private final static String RATING_BAR_SHARED_PREF = "ratingBarSharedPref";

    private RatingBar rbFeedback;
    private SharedPreferences preferences;

    public About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view) {
        rbFeedback = view.findViewById(R.id.about_rb_feedback);
        if (getActivity() != null) {
            // initializare shared preferences
            preferences = getActivity().getSharedPreferences(PREF_SHARED_NAME, Context.MODE_PRIVATE);

            // citire din fisier
            float value = preferences.getFloat(RATING_BAR_SHARED_PREF, -1);
            if (value != -1) {
                rbFeedback.setRating(value);
            }

            rbFeedback.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    // salvare in fisier
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putFloat(RATING_BAR_SHARED_PREF, rating);
                    editor.apply();
                }
            });
        }
    }

}
