package br.edu.iff.pooa20172.darksoulsiiiplanner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CharEditActivity extends AppCompatActivity {
    private ViewPager pager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char_edit);

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new CharEditPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tlTabs);
        tabLayout.setupWithViewPager(pager);
    }

    private class CharEditPagerAdapter extends FragmentStatePagerAdapter {
        public CharEditPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new CharEditStatsFragment();
                case 1:
                    return new CharEditEquipFragment();
                case 2:
                    return new CharEditOverviewFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Stats";
                case 1:
                    return "Equipment";
                case 2:
                    return "Overview";
            }
            return null;
        }
    }

}
