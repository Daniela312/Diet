package com.example.alarm.diet_ver1.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alarm.diet_ver1.Model.TestAns;
import com.example.alarm.diet_ver1.Model.TestQuest;
import com.example.alarm.diet_ver1.Util.Constants;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context ctx;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null,  Constants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create Table of TEST Paper
        String CREATE_TEST_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_TEST_PAPER + "("
                + Constants.KEY_TEST_ID + " TEXT PRIMARY KEY, " + Constants.KEY_TEST_NAME + " TEXT);";
        db.execSQL(CREATE_TEST_TABLE);

        //Create Table of Test IDs
        CREATE_TEST_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_DIET_FREE + "("
                + Constants.KEY_QUEST_ID + " INTEGER PRIMARY KEY, " + Constants.KEY_QUEST_CONTENT + " TEXT);";
        db.execSQL(CREATE_TEST_TABLE);

        CREATE_TEST_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_DRINK_FREE + "("
                + Constants.KEY_QUEST_ID + " INTEGER PRIMARY KEY, " + Constants.KEY_QUEST_CONTENT + " TEXT);";
        db.execSQL(CREATE_TEST_TABLE);

        CREATE_TEST_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_LOVE_FREE + "("
                + Constants.KEY_QUEST_ID + " INTEGER PRIMARY KEY, " + Constants.KEY_QUEST_CONTENT + " TEXT);";
        db.execSQL(CREATE_TEST_TABLE);

        CREATE_TEST_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_DIET_CHARGE + "("
                + Constants.KEY_QUEST_ID + " INTEGER PRIMARY KEY, " + Constants.KEY_QUEST_CONTENT + " TEXT);";
        db.execSQL(CREATE_TEST_TABLE);

        //Create Table of TestAns
        CREATE_TEST_TABLE = "CREATE TABLE " + Constants.TABLE_NAME_TEST_ANS + "("
                + Constants.KEY_NUM_ID + " INTEGER PRIMARY KEY, " + Constants.KEY_CUSTOMER_ID + " TEXT, "
                + Constants.KEY_TEST_ID + " TEXT, " + Constants.KEY_TEST_RESULT + " TEXT, "
                + Constants.KEY_TEST_DATE + " LONG, " + Constants.KEY_CHECKED_ANS + " TEXT);";
        db.execSQL(CREATE_TEST_TABLE);

    }

    public void DO_TEMPERARY() {

        // Table does not exist
        /*TestQuest testQuest = new TestQuest();
        testQuest.setQuestContent("내가 힘들 때 편하게 이야기 할 수 있는 사람이 없다");
        Temp(testQuest);
        testQuest.setQuestContent("나를 이해해주는 사람이 없다");
        Temp(testQuest);
        testQuest.setQuestContent("친구들과 안정적으로 오래 관계를 지속하는 편이다");
        Temp(testQuest);
        testQuest.setQuestContent("다른 사람들의 눈치를 본다");
        Temp(testQuest);
        testQuest.setQuestContent("나를 믿고 응원해주는 사람들이 있다");
        Temp(testQuest);
        testQuest.setQuestContent("나는 사람들과 갈등이 생기면 해결이 어렵다");
        Temp(testQuest);*/

    }

    public void Temp(TestQuest testQuest) {//임시로 검사문항을 넣어둠(본래 서버에서 불러와야함?)

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_QUEST_CONTENT, testQuest.getQuestContent());

        db.insert(Constants.TABLE_NAME_DIET_FREE, null, values);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_TEST_PAPER);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_DIET_FREE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_DRINK_FREE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_LOVE_FREE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_DIET_CHARGE);
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME_TEST_ANS);

        onCreate(db);
    }

    /*
        TEST ANSWER - CRUD OPERATIONS: Create, Read, Update, Delete Methods
*/
    //Add TestAns
    public void addTestAns(TestAns testAns){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_CUSTOMER_ID, testAns.getCustomerID());
        values.put(Constants.KEY_TEST_ID, testAns.getTestID());
        values.put(Constants.KEY_CHECKED_ANS, testAns.getCheckedAns());
        values.put(Constants.KEY_TEST_DATE, System.currentTimeMillis());
        values.put(Constants.KEY_TEST_RESULT, testAns.getTestResult());

        //Insert the row
        db.insert(Constants.TABLE_NAME_TEST_ANS, null, values);

        Log.d("Saved: !", "Saved to DB");
    }

    //Get a TestAns
    public TestAns getTestAns(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(Constants.TABLE_NAME_TEST_ANS,
                new String[] {Constants.KEY_NUM_ID, Constants.KEY_CUSTOMER_ID, Constants.KEY_TEST_ID, Constants.KEY_TEST_DATE,
                        Constants.KEY_CHECKED_ANS, Constants.KEY_TEST_RESULT},
                Constants.KEY_NUM_ID + "=?",
                new String[] {String.valueOf(id)},
                null,null,null,null);

        if(cursor != null){
            cursor.moveToFirst();

            TestAns testAns = new TestAns();
            testAns.setNumID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_NUM_ID))));
            testAns.setCustomerID(cursor.getString(cursor.getColumnIndex(Constants.KEY_CUSTOMER_ID)));
            testAns.setTestID(cursor.getString(cursor.getColumnIndex(Constants.KEY_TEST_ID)));
            testAns.setCheckedAns(cursor.getString(cursor.getColumnIndex(Constants.KEY_CHECKED_ANS)));
            testAns.setTestResult(cursor.getString(cursor.getColumnIndex(Constants.KEY_TEST_RESULT)));

            //convert timestamp to something readable
            java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
            String testDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_TEST_DATE))).getTime());
            testAns.setTestDate(testDate);

            return testAns;
        }
        return null;
    }

    //Get all TestAns
    public List<TestAns> getAllTestAnswers(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<TestAns> testAnsList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME_TEST_ANS,
                new String[] {Constants.KEY_NUM_ID, Constants.KEY_CUSTOMER_ID, Constants.KEY_TEST_ID, Constants.KEY_TEST_DATE,
                        Constants.KEY_CHECKED_ANS, Constants.KEY_TEST_RESULT},
                null, null, null, null, Constants.KEY_TEST_DATE + " DESC");

        if(cursor.moveToFirst()){
            do{
                TestAns testAns = new TestAns();
                testAns.setNumID(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_NUM_ID))));
                testAns.setCustomerID(cursor.getString(cursor.getColumnIndex(Constants.KEY_CUSTOMER_ID)));
                testAns.setTestID(cursor.getString(cursor.getColumnIndex(Constants.KEY_TEST_ID)));
                testAns.setCheckedAns(cursor.getString(cursor.getColumnIndex(Constants.KEY_CHECKED_ANS)));
                testAns.setTestResult(cursor.getString(cursor.getColumnIndex(Constants.KEY_TEST_RESULT)));

                //convert timestamp to something readable
                java.text.DateFormat dateFormat = java.text.DateFormat.getDateInstance();
                String testDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_TEST_DATE))).getTime());
                testAns.setTestDate(testDate);

                //Add to the groceryList
                testAnsList.add(testAns);

            }while(cursor.moveToNext());
        }

        return testAnsList;

    }

    //Update TestAns
    public int UpdateTestAns(TestAns testAns){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_CUSTOMER_ID, testAns.getCustomerID());
        values.put(Constants.KEY_TEST_ID, testAns.getTestID());
        values.put(Constants.KEY_CHECKED_ANS, testAns.getCheckedAns());
        values.put(Constants.KEY_TEST_DATE, System.currentTimeMillis());

        //update row
        return db.update(Constants.TABLE_NAME_TEST_ANS, values, Constants.KEY_CUSTOMER_ID + "=?",
                new String[] {String.valueOf(testAns.getNumID())});
    }

    //Delete TestAns
    public void DeleteTestAns(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Constants.TABLE_NAME_TEST_ANS, Constants.KEY_NUM_ID + "=?",
                new String[] {String.valueOf(id)});

        db.close();

    }

    //Get TestAns count
    public int getTestAnswerCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME_TEST_ANS;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }



/*
        TEST QUESTION - CRUD OPERATIONS: Create, Read, Update, Delete Methods
*/

    //Get all TestQuests
    public List<TestQuest> getAllTestQuests(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<TestQuest> testQuestList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME_DIET_FREE,
                new String[] {Constants.KEY_QUEST_ID, Constants.KEY_QUEST_CONTENT},
                null, null, null, null, Constants.KEY_QUEST_ID + " ASC");

        if(cursor.moveToFirst()){
            do{
                TestQuest testQuest = new TestQuest();
                testQuest.setQuestId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_QUEST_ID))));
                testQuest.setQuestContent(cursor.getString(cursor.getColumnIndex(Constants.KEY_QUEST_CONTENT)));

                //Add to the groceryList
                testQuestList.add(testQuest);

            }while(cursor.moveToNext());
        }

        return testQuestList;

    }

    //Get TestAns count
    public int getTestQuestsCount(){
        SQLiteDatabase db = this.getReadableDatabase();

        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME_DIET_FREE;
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }

}