package blockchainvideoapp.com.goviddo.goviddo.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.List;

import blockchainvideoapp.com.goviddo.goviddo.Fragments.HomeFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.AccountFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.MoreDetailsFragment;
import blockchainvideoapp.com.goviddo.goviddo.Fragments.SubscriptionFragment;
import blockchainvideoapp.com.goviddo.goviddo.R;

public class HomeActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private int[] mTabIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabIcons = new int[]{
                R.mipmap.ic_account_balance_24px,
                R.mipmap.video_camera,
                R.mipmap.ic_view_headline_24px
        };


        mViewPager =  findViewById(R.id.container);
        setupViewPager(mViewPager);

        mTabLayout =  findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "Home");
        adapter.addFragment(new SubscriptionFragment(), "Subscription");
        adapter.addFragment(new MoreDetailsFragment(), "Recent");
        adapter.addFragment(new AccountFragment(), "Other");
        viewPager.setAdapter(adapter);
    }


    private void setupTabIcons() {

       /* View view1 = getLayoutInflater().inflate(R.layout.custom_tablayout_image, null);
        view1.findViewById(R.id.icon).setBackgroundResource(mTabIcons[0]);
        mTabLayout.getTabAt(0).setCustomView(view1);*/

        mTabLayout.getTabAt(0).setIcon(mTabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(mTabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(mTabIcons[1]);
        mTabLayout.getTabAt(3).setIcon(mTabIcons[2]);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }


    }


}



