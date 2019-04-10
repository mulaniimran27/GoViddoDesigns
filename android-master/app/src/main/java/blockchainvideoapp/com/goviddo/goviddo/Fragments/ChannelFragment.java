package blockchainvideoapp.com.goviddo.goviddo.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import blockchainvideoapp.com.goviddo.goviddo.R;
import blockchainvideoapp.com.goviddo.goviddo.adapter.ChannelTabAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View view;

    public ChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_channel, container, false);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Movies"));
        tabLayout.addTab(tabLayout.newTab().setText("Playlists"));
        tabLayout.addTab(tabLayout.newTab().setText("About"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ChannelTabAdapter adapter = new ChannelTabAdapter(getContext(), getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

}
