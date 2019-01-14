package com.example.alarm.diet_ver1.Activities.Fragment2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.alarm.diet_ver1.Activities.MainActivity;
import com.example.alarm.diet_ver1.DB.DatabaseHandler;
import com.example.alarm.diet_ver1.Model.TestAns;
import com.example.alarm.diet_ver1.Model.TestQuest;
import com.example.alarm.diet_ver1.R;

public class TestResultActivity extends AppCompatActivity {

    private TextView tv_result;
    private TextView tv_explain_detail;
    private Button btn_see_details;

    private int numID = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        tv_result = (TextView) findViewById(R.id.tv_result);
        tv_explain_detail = (TextView) findViewById(R.id.tv_explain_detail);


        ////////////Get items from database///////////////
        DatabaseHandler db = new DatabaseHandler(this);
        TestAns testAns = db.getTestAns(numID);


        //set texts on TextView
        tv_result.setText(testAns.getTestResult());
        tv_result.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);


    }

    //ActionBar - setting, search etc...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_list:
                //TODO: process the click event
                onBackPressed();
                return true;


        }

        return false;
    }
}
