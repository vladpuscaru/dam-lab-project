package eu.ase.ro.damapp.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.damapp.R;
import eu.ase.ro.damapp.util.Player;
import eu.ase.ro.damapp.util.PlayerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    public static final String PLAYERS_KEY = "playersKey";
    ListView lvPlayers;
    List<Player> players = new ArrayList<>();

    public Home() {
        // Required empty public constructor
    }

    public void notifyInternal() {
        // notificare Adapter ListView sursa de date(players) s-a modificat
        PlayerAdapter playerAdapter = (PlayerAdapter)lvPlayers.getAdapter();
        playerAdapter.notifyDataSetChanged();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);

        return view;
    }

    private void initComponents(View view) {
        lvPlayers = view.findViewById(R.id.home_lv_players);

        // preluare lista jucatori
        if (getArguments() != null) {
            players = getArguments().getParcelableArrayList(PLAYERS_KEY);
        }

        // creare adapter
        if (getContext() != null) {
//            ArrayAdapter<Player> adapter = new ArrayAdapter<>(getContext().getApplicationContext(), android.R.layout.simple_list_item_1, players);
//            lvPlayers.setAdapter(adapter);
            PlayerAdapter playerAdapter = new PlayerAdapter(getContext(),
                    R.layout.lv_row_view, players, getLayoutInflater());
            lvPlayers.setAdapter(playerAdapter);
        }
    }

}
