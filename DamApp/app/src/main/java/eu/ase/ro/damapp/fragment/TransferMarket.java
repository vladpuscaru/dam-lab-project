package eu.ase.ro.damapp.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import eu.ase.ro.damapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransferMarket extends Fragment {


    public TransferMarket() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer_market, container, false);
    }

}
