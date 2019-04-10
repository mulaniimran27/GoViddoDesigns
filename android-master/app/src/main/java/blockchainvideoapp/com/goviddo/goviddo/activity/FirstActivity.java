package blockchainvideoapp.com.goviddo.goviddo.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import blockchainvideoapp.com.goviddo.goviddo.Fragments.AddMovieFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.HomeFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.MoreDetailsFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.SearchFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.ShareFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.SubscriptionFragment;
import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.Utils.BottomNavigationViewHelper;

public class FirstActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(new HomeFragment(), 0);
                    return true;
                case R.id.navigation_subscription:
                    loadFragment(new SubscriptionFragment(), 0);
                    return true;
                case R.id.navigation_recent:
                    loadFragment(new HomeFragment(), 0);
                    return true;
                case R.id.navigation_more:
                    loadFragment(new MoreDetailsFragment(FirstActivity.this), 0);
                    return true;
            }
            return false;
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
      /*  MenuItem itemSearch = menu.findItem(R.id.action_search);
        MenuItem itemAddVideo = menu.findItem(R.id.action_add_movie);
        MenuItem itemNotification = menu.findItem(R.id.action_notification1);

        MenuItemCompat.setActionView(itemSearch, R.layout.layout_search);
        MenuItemCompat.setActionView(itemAddVideo, R.layout.layout_add_video);
        MenuItemCompat.setActionView(itemNotification, R.layout.layout_nitification);*/
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("Test", "menu item id " + item.getItemId() + " item " + item.getTitle());

        switch (item.getItemId()) {
            case R.id.action_search:
                loadFragment(new SearchFragment(), 0);
                return true;
            case R.id.action_add_movie:
                loadFragment(new AddMovieFragment(), 0);
                return true;
            case R.id.action_notification1:
                loadFragment(new ShareFragment(), 0);
                // Toast.makeText(this, "Notification", Toast.LENGTH_LONG).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public boolean loadFragment(Fragment fragment, int i) {
        //switching fragment
        if (fragment != null) {
            if (i == 1) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(fragment.getTag())
                        .commit();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }
            return true;
        }
        return false;
    }

    protected Toolbar toolbar;
    private AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        loadFragment(new HomeFragment(), 1);
        appBarLayout = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(navigation);
    }

}
