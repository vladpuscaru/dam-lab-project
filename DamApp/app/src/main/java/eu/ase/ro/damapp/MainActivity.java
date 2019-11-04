package eu.ase.ro.damapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import eu.ase.ro.damapp.fragment.About;
import eu.ase.ro.damapp.fragment.Home;
import eu.ase.ro.damapp.fragment.TransferMarket;
import eu.ase.ro.damapp.util.Player;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_PLAYER = 200;

    private DrawerLayout drawerLayout;
    private FloatingActionButton fabAddPlayer;
    private NavigationView navigationView;
    private Fragment currentFragment;

    private ArrayList<Player> players = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configNavigation();
        initComponents();
        openDefaultFragment(savedInstanceState);

    }

    private void openDefaultFragment(Bundle savedInstaceState) {
        if (savedInstaceState == null) { // prima data in activitate
            currentFragment = createHomeFragment();
            openFragment();
            navigationView.setCheckedItem(R.id.main_nav_home);
        }
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

        // atasare meniu lateral: selectie item
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(itemSelect());
    }

    private NavigationView.OnNavigationItemSelectedListener itemSelect() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                if (menuItem.getItemId() == R.id.main_nav_home) {
                    currentFragment = createHomeFragment();
                } else if (menuItem.getItemId() == R.id.main_nav_transfer_market) {
                    currentFragment = new TransferMarket();
                } else if (menuItem.getItemId() == R.id.main_nav_about) {
                    currentFragment = new About();
                }

                // deschidere frament
                openFragment();

                // inchidere meniu lateral
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        };
    }

    private void openFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frame_container, currentFragment)
                .commit();
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
                        players.add(player);
                        // notificare ListView din HomeFragment, daca este cazul
                        if (currentFragment instanceof Home) {
                            ((Home) currentFragment).notifyInternal();
                        }
                    }
                }
            }
        }
    }


    private Fragment createHomeFragment() {
        Fragment fragment = new Home();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Home.PLAYERS_KEY, players);
        fragment.setArguments(bundle);

        return fragment;
    }
}



