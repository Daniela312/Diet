package com.example.alarm.diet_ver1.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.alarm.diet_ver1.Activities.Fragment2.DietTestActivity;
import com.example.alarm.diet_ver1.R;
import com.example.alarm.diet_ver1.UI.ViewPagerAdapter;

public class Fragment1_home extends Fragment implements View.OnClickListener{

    private  RelativeLayout layout;

    private Button btn1_mainPage;
    private Button btn2_mainPage;
    private Button btn3_mainPage;
    private Button btn4_mainPage;

    private ViewPagerAdapter adapterViewPager;
    private ViewPager viewPager_banner;
    private Handler handler_banner;
    private Thread thread_banner;
    private int pageNum = 0;

    @SuppressLint("ResourceType")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bannerSetting();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layout = (RelativeLayout) inflater.inflate(R.layout.fragment1_home, container, false);
        viewPager_banner = (ViewPager) layout.findViewById(R.id.viewPager_banner);
        adapterViewPager = new ViewPagerAdapter(getChildFragmentManager());
        viewPager_banner.setAdapter(adapterViewPager);

        //button setting
        btn1_mainPage = (Button) layout.findViewById(R.id.btn1_mainPage);
        btn2_mainPage = (Button) layout.findViewById(R.id.btn2_mainPage);
        btn3_mainPage = (Button) layout.findViewById(R.id.btn3_mainPage);
        btn4_mainPage = (Button) layout.findViewById(R.id.btn4_mainPage);

        btn1_mainPage.setOnClickListener(this);
        btn2_mainPage.setOnClickListener(this);
        btn3_mainPage.setOnClickListener(this);
        btn4_mainPage.setOnClickListener(this);

        return layout;
    }

    private void bannerSetting(){

        handler_banner = new Handler(){

            public void handleMessage(android.os.Message msg) {
                if(pageNum==(adapterViewPager.getCount()-1))
                    viewPager_banner.setCurrentItem(pageNum=0);
                else
                    viewPager_banner.setCurrentItem(++pageNum);
            }
        };
        thread_banner = new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try {
                        Thread.sleep(4000);
                        handler_banner.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread_banner.start();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn1_mainPage:
                MainActivity.viewPager.setCurrentItem(1);
                MainActivity.resetBackground(1);
                break;

            case R.id.btn2_mainPage:
                MainActivity.viewPager.setCurrentItem(2);
                MainActivity.resetBackground(2);
                break;

            case R.id.btn3_mainPage:
                break;

            case R.id.btn4_mainPage:
                MainActivity.viewPager.setCurrentItem(3);
                MainActivity.resetBackground(3);
                break;
        }



    }

}
