package com.example.alarm.diet_ver1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.alarm.diet_ver1.Activities.Fragment1_home;
import com.example.alarm.diet_ver1.Activities.Fragment2_test;
import com.example.alarm.diet_ver1.Activities.Fragment3_program;
import com.example.alarm.diet_ver1.Activities.Fragment4_mypage;
import com.example.alarm.diet_ver1.R;
import com.example.alarm.diet_ver1.UI.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity{

    public static ViewPager viewPager;

    public static Button item1_home;
    public static Button item2_test;
    public static Button item3_program;
    public static Button item4_mypage;

    private int pageCount = 4;
    private int pageNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment Setting
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        viewPager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(0);

        item1_home = (Button) findViewById(R.id.tab_home);
        item2_test = (Button) findViewById(R.id.tab_test);
        item3_program = (Button) findViewById(R.id.tab_program);
        item4_mypage = (Button) findViewById(R.id.tab_mypage);

        item1_home.setOnClickListener(movePageListener);
        item2_test.setOnClickListener(movePageListener);
        item3_program.setOnClickListener(movePageListener);
        item4_mypage.setOnClickListener(movePageListener);
        item1_home.setTag(0);
        item2_test.setTag(1);
        item3_program.setTag(2);
        item4_mypage.setTag(3);

        byPassActivity();



    }


    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            viewPager.setCurrentItem(tag);

            resetBackground(tag);
        }
    };

    public static void resetBackground(int tag){

        //reset
        item1_home.setBackgroundResource(R.drawable.outline_home_black_24dp);
        item2_test.setBackgroundResource(R.drawable.outline_home_black_24dp);
        item3_program.setBackgroundResource(R.drawable.outline_home_black_24dp);
        item4_mypage.setBackgroundResource(R.drawable.outline_home_black_24dp);

        //change selected tag
        switch (tag){
            case 0:
                item1_home.setBackgroundResource(R.drawable.round_home_black_24dp);
                return ;
            case 1:
                item2_test.setBackgroundResource(R.drawable.round_home_black_24dp);
                return ;
            case 2:
                item3_program.setBackgroundResource(R.drawable.round_home_black_24dp);
                return ;
            case 3:
                item4_mypage.setBackgroundResource(R.drawable.round_home_black_24dp);
                return ;
        }

    }

    public class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new Fragment1_home();
                case 1:
                    return new Fragment2_test();
                case 2:
                    return new Fragment3_program();
                case 3:
                    return new Fragment4_mypage();
                default:
                    return null;
            }
        }

        public android.support.v4.app.Fragment returnItem(Fragment fragment){
            return fragment;
        }
        @Override
        public int getCount()
        {
            return pageCount;
        }
    }

    public void byPassActivity(){

        //db에 종료시 tag를 저장해놨다가 그에 따라 tag 자동 전환되도록 (카톡처럼)

        resetBackground(0);
    }


    //ActionBar - setting, search etc...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_setting:
                //TODO: process the click event
                return true;


        }

        return false;
    }
}
