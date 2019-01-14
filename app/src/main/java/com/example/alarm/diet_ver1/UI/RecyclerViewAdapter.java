package com.example.alarm.diet_ver1.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.alarm.diet_ver1.Activities.Fragment2.DietTestActivity;
import com.example.alarm.diet_ver1.DB.DatabaseHandler;
import com.example.alarm.diet_ver1.Model.TestQuest;
import com.example.alarm.diet_ver1.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<TestQuest> testQuestList;



    public RecyclerViewAdapter(Context context, List<TestQuest> testQuestList) {
        this.context = context;
        this.testQuestList = testQuestList;
        DietTestActivity.sumOfTestAnsList = new String[getItemCount()];
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_list_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        TestQuest testQuest = testQuestList.get(position);
        holder.testQuestContent.setText(testQuest.getQuestContent());
    }

    @Override
    public int getItemCount() { return testQuestList.size(); }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView testQuestContent;
        public RadioGroup radioGroup;

        public ViewHolder(@NonNull final View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            testQuestContent = (TextView) itemView.findViewById(R.id.question);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radioGroup);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    DatabaseHandler db = new DatabaseHandler(context);
                    int position = getAdapterPosition();

                    //find number of checkedButton
                    int radioIndex = group.indexOfChild(itemView.findViewById(checkedId));

                    //save selectedButton
                    DietTestActivity.sumOfTestAnsList[position] = String.valueOf(radioIndex+1);
                }
            });
        }

    }
}
