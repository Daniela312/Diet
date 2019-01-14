package com.example.alarm.diet_ver1.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.alarm.diet_ver1.Activities.Fragment2.DietTestActivity;
import com.example.alarm.diet_ver1.R;

public class Fragment2_test extends Fragment implements View.OnClickListener{

    private Button btn1_diet_free;
    private Button btn2_drink_free;
    private Button btn3_love_free;
    private Button btn4_diet_charge;

    private String TEST_ID;

    public Fragment2_test(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment2_test, container, false);

        btn1_diet_free = layout.findViewById(R.id.test_diet_free);
        btn2_drink_free = layout.findViewById(R.id.test_drink_free);
        btn3_love_free = layout.findViewById(R.id.test_love_free);
        btn4_diet_charge = layout.findViewById(R.id.test_diet_charge);

        /*btn1_diet_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DietTestActivity.class);
                startActivity(intent);
            }
        });
*/

        btn1_diet_free.setOnClickListener(this);
        btn2_drink_free.setOnClickListener(this);
        btn3_love_free.setOnClickListener(this);
        btn4_diet_charge.setOnClickListener(this);

        return layout;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), DietTestActivity.class);

        switch (v.getId()){
            case R.id.test_diet_free:
                TEST_ID = "TestQuest";
                break;
            case R.id.test_drink_free:

                break;
            case R.id.test_love_free:

                break;
            case R.id.test_diet_charge:

                break;
        }

        intent.putExtra("TEST_ID",TEST_ID);


       startActivity(intent);





    }
}
