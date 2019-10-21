package eu.ase.ro.damapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import eu.ase.ro.damapp.util.Player;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_PLAYER = 200;

    private DrawerLayout drawerLayout;
    private FloatingActionButton fabAddPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        initComponents();
    }

    private void configNavigation() {
        //initializare toolbar - bara de actiune
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //initializare drawer layout - panou meniu lateral
        drawerLayout = findViewById(R.id.drawer_layout);
        //legare meniu lateral cu bara actiune
        // + eveniment de deschidere
        //creare instanta utilitara
        ActionBarDrawerToggle actionBar =
                new ActionBarDrawerToggle(
                        this,
                        drawerLayout,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        //atasare eveniment
        drawerLayout.addDrawerListener(actionBar);
        //sincronizare actionBartoggle
        actionBar.syncState();
    }

    private void initComponents() {
        // instantiare de buton
        fabAddPlayer = findViewById(R.id.main_fab_add_player);
        // atasare eveniment
        fabAddPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddPlayerActivity.class);
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_ADD_PLAYER);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD_PLAYER) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Player player = data.getParcelableExtra(AddPlayerActivity.ADD_PLAYER_KEY);
                    if (player != null) {
                        Toast.makeText(getApplicationContext(), player.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}



