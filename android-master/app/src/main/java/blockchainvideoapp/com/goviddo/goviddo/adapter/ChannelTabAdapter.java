package blockchainvideoapp.com.goviddo.goviddo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import blockchainvideoapp.com.goviddo.goviddo.Fragments.ChHomeFragment;

public class ChannelTabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public ChannelTabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ChHomeFragment homeFragment = new ChHomeFragment();
                return homeFragment;
            case 1:
                ChHomeFragment homeFragment1 = new ChHomeFragment();
                return homeFragment1;
            case 2:
                ChHomeFragment homeFragment2 = new ChHomeFragment();
                return homeFragment2;
            case 3:
                ChHomeFragment homeFragment3 = new ChHomeFragment();
                return homeFragment3;
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }

}
