package com.example.alarm.diet_ver1.Activities.Fragment2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.alarm.diet_ver1.DB.DatabaseHandler;
import com.example.alarm.diet_ver1.Model.TestAns;
import com.example.alarm.diet_ver1.Model.TestQuest;
import com.example.alarm.diet_ver1.R;
import com.example.alarm.diet_ver1.UI.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DietTestActivity extends AppCompatActivity {

    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<TestQuest> testQuestList;
    private DatabaseHandler db;

    public static String[] sumOfTestAnsList;
    private Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_test);


        recyclerView = (RecyclerView) findViewById(R.id.test_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        testQuestList = new ArrayList<TestQuest>();


        ////////////Get items from database///////////////
        db = new DatabaseHandler(this);
        db.DO_TEMPERARY();
        testQuestList = db.getAllTestQuests();


        ////////Fill RecyclerView with the Data///////////
        recyclerViewAdapter = new RecyclerViewAdapter(this, testQuestList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();


        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                boolean isNotEmpty = true;
                for(String i : sumOfTestAnsList)
                    if(i == null) isNotEmpty = false;

                if(isNotEmpty){

                    String checkedAnswers = sumOfTestAnsList.toString();

                    //insert to DB
                    TestAns testAns = new TestAns();
                    testAns.setCustomerID("312joo");
                    testAns.setTestID("TestDietFree");
                    testAns.setCheckedAns(checkedAnswers);
                    testAns.setTestResult("약간 심각");
                    db.addTestAns(testAns);

                    //start Activity
                    startActivity(new Intent(getApplicationContext(), TestResultActivity.class));
                    finish();

                }
                else{
                    Toast.makeText(recyclerView.getContext(), "모든 칸을 채워주세요", Toast.LENGTH_LONG).show();
                }


            }
        });
    }

}
