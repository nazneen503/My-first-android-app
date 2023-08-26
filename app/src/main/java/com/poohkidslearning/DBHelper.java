package com.poohkidslearning;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    static String dbName = "Pooh Kids Learning.sqlite";
    String dbPath = "";
    SQLiteDatabase dbMain;

    public DBHelper(Context context) {
        super(context, dbName, null, 1);
        dbPath = "/data/data/" + context.getPackageName() + "/databases";
    }

    public static synchronized DBHelper getDB(Context context) {
        return new DBHelper(context);
    }

    public boolean checkDb() {
        SQLiteDatabase db;
        try {
            db = SQLiteDatabase.openDatabase(dbPath + "/" + dbName, null, SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            db = null;
        }
        return db != null;
    }

    public void createDB(Context context) {
        this.getReadableDatabase();
        this.close();
        try {

            InputStream inputStream = context.getAssets().open(dbName);
            FileOutputStream fos = new FileOutputStream(dbPath + "/" + dbName);
            int len;
            byte[] bytes = new byte[1024];

            while ((len = inputStream.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
            fos.close();
            fos.flush();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB() {
        dbMain = SQLiteDatabase.openDatabase(dbPath + "/" + dbName, null, SQLiteDatabase.OPEN_READONLY);

    }

    public ArrayList<suitcaseNumberLearning> getImage() {
        ArrayList<suitcaseNumberLearning> numberLearningArrayList = new ArrayList<>();
        Cursor cursor = dbMain.rawQuery("select * from 'Numbers'", null);
        while (cursor.moveToNext()) {
            suitcaseNumberLearning suitcaseNumberLearning = new suitcaseNumberLearning();
            suitcaseNumberLearning.imgv = cursor.getBlob(1);
            suitcaseNumberLearning.image_name_text = cursor.getString(3);
            suitcaseNumberLearning.count_text = cursor.getString(2);
            suitcaseNumberLearning.count_number=""+cursor.getInt(4);
            numberLearningArrayList.add(suitcaseNumberLearning);

        }
        return numberLearningArrayList;
    }
    public ArrayList<table_recycler_structure> gettable(int flag){
        ArrayList<table_recycler_structure>table_recycler_structureArrayList=new ArrayList<>();
        Cursor cursor=null;
        if(flag==2) {
            int i = 2;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==3){
            int i = 3;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==4){
            int i = 4;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==5){
            int i = 5;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==6){
            int i = 6;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==7){
            int i = 7;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==8){
            int i = 8;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==9){
            int i = 9;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        else if(flag==10){
            int i = 10;
            cursor = dbMain.rawQuery("select * from 'table' where tableno='" + i + "'", null);
        }
        while(cursor.moveToNext()){
            table_recycler_structure t1=new table_recycler_structure();
            t1.num1=cursor.getString(2);
            table_recycler_structureArrayList.add(t1);
        }
        return table_recycler_structureArrayList;
    }
    public ArrayList<suitcase_counting> getCounting(){
        ArrayList<suitcase_counting> countingArrayList=new ArrayList<>();
        Cursor cursor=dbMain.rawQuery("select * from 'countings'",null);
        while (cursor.moveToNext()){
            suitcase_counting suitcaseCounting=new suitcase_counting();
            suitcaseCounting.image=cursor.getBlob(1);
            suitcaseCounting.opt1= cursor.getInt(2);
            suitcaseCounting.opt2=cursor.getInt(3);
            suitcaseCounting.opt3=cursor.getInt(4);
            suitcaseCounting.correct_ans=cursor.getInt(5);
            countingArrayList.add(suitcaseCounting);
        }
        return countingArrayList;
    }
    public ArrayList<suitcase_match_realimages> getMatch(){
        ArrayList<suitcase_match_realimages> realimagesArrayList=new ArrayList<>();
        Cursor cursor=dbMain.rawQuery("select * from 'match_real_image'",null);
        while (cursor.moveToNext()){
            suitcase_match_realimages suitcaseMatchRealimages=new suitcase_match_realimages();
            suitcaseMatchRealimages.pattern1=""+cursor.getInt(1);
            suitcaseMatchRealimages.pattern2= ""+cursor.getInt(2);
            suitcaseMatchRealimages.pattern3= ""+cursor.getInt(3);
            realimagesArrayList.add(suitcaseMatchRealimages);
        }
        return realimagesArrayList;
    }

public void userInsertImg(profile_suitcase profileSuitcase){
        this.getWritableDatabase();
        Cursor cursor=dbMain.rawQuery("select * from 'profile'",null);
        ContentValues cv=new ContentValues();
        cv.put("name",profileSuitcase.name);
        cv.put("image",profileSuitcase.img);
        if (cursor.getCount()==0){
            dbMain.insert("profile",null,cv);

        }
        else
        {
            dbMain.update("profile",cv,"id='"+1+"",null);
        }
}
 public ArrayList<profile_suitcase> getProfile(){
        ArrayList<profile_suitcase> arruser=new ArrayList<>();
        Cursor cursor=dbMain.rawQuery("select * from 'profile'",null);
        while(cursor.moveToNext()){
            profile_suitcase suitcase=new profile_suitcase();
            suitcase.name=cursor.getString(1);
            suitcase.img=cursor.getBlob(2);
            arruser.add(suitcase);
        }
        return arruser;
 }
 public ArrayList<byte[]> getFlip(ArrayList<Integer> arrValues){
        //ArrayList<dash_quiz_recycler_structure> arrFlip=new ArrayList<>();
        ArrayList<byte[]> arrImages=new ArrayList<>();
       for(int i=0;i<arrValues.size();i++) {
         /*  Log.d("arrIndex")*/
           Cursor cursor = dbMain.rawQuery("select * from 'match_the_numbers'where sno='" + arrValues.get(i) + "'", null);

           while (cursor.moveToNext()) {
               arrImages.add(cursor.getBlob(1));
           }
       }
     return arrImages;

 }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public ArrayList<missing_asc_recycler_structure> getNumber(int i) {
        ArrayList<missing_asc_recycler_structure> arrayList=new ArrayList<>();

        Cursor cursor=dbMain.rawQuery("select * from 'missing_number' where asc_desc='"+i+"'",null);
        while (cursor.moveToNext()){
            missing_asc_recycler_structure s1=new missing_asc_recycler_structure();
            s1.text=cursor.getString(2);
            arrayList.add(s1);
        }
        return  arrayList;
    }
}
