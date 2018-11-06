package com.jamjhot.dailylife.jamjhot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tanin on 8/6/2017.
 */
public class DbHelperVab extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "securitylevel";
    // tasks table name
    private static final String TABLE_QUEST = "homedata";
    // tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "name";
    private static final String KEY_DATA1= "phone_number";
    private static final String KEY_DATA2= "";
    private static final String KEY_DATA3= "ageday";
    private static final String KEY_DATA4= "agemonth";
    private static final String KEY_DATA5= "ageweekday";//option a
    private SQLiteDatabase dbase;
    public DbHelperVab(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase=db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE
                + " TEXT, "+ KEY_DATA1
                + " TEXT, "+ KEY_DATA2
                + " TEXT, "+ KEY_DATA3
                + " TEXT, "+ KEY_DATA4
                + " TEXT, " + KEY_DATA5+ " TEXT)";
        db.execSQL(sql);
//        addHOMEDATAs();
        //db.close();
    }
    private void addHOMEDATAs()
    {
//        HOMEDATA q1=new HOMEDATA("What is JP?","Jalur Pesawat", "Jack sParrow", "Jasa Programmer", "Jasa Programmer");
//        this.addHOMEDATA(q1);
//        HOMEDATA q2=new HOMEDATA("where the JP place?", "Monas, Jakarta", "Gelondong, Bangun Tapan, bantul", "Gelondong, Bangun Tapan, bandul", "Gelondong, Bangun Tapan, bantul");
//        this.addHOMEDATA(q2);
//        HOMEDATA q3=new HOMEDATA("who is CEO of the JP?","Usman and Jack", "Jack and Rully","Rully and Usman", "Rully and Usman" );
//        this.addHOMEDATA(q3);
//        HOMEDATA q4=new HOMEDATA("what do you know about JP?", "JP is programmer home", "JP also realigy home", "all answer is true","all answer is true");
//        this.addHOMEDATA(q4);
//        HOMEDATA q5=new HOMEDATA("what do you learn in JP?","Realigy","Programming","all answer is true","all answer is true");
//        this.addHOMEDATA(q5);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }
    // Adding new question
    public void addHOMEDATA(String string1,String string2) {
       if (rowcount()<=11) {
           SQLiteDatabase db = this.getWritableDatabase();
           ContentValues values = new ContentValues();
           values.put(KEY_TITLE, string1.trim());
           values.put(KEY_DATA1, string2.trim());
//           values.put(KEY_DATA2, string3.trim());
//           values.put(KEY_DATA3, string4.trim());
//           values.put(KEY_DATA4, string5.trim());
//           values.put(KEY_DATA5, string6.trim());
           // Inserting Row
           long rowInserted = db.insert(TABLE_QUEST, null, values);
       }
//        }


//        dbase.close();
    }

    public void deleterawDB(int id) {
//        if (rowcount()<=11) {

//        Log.e("hhhhhhh","id: "+string1+"iiii: "+string2);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, ""+id);
        // Inserting Row
        long rowUpdated = db.delete(TABLE_QUEST,  KEY_ID+"="+id,null);
//        }
//        }


//        dbase.close();
    }

    public void updateDB(int id,String string1,String string2) {
//        if (rowcount()<=11) {

        Log.e("hhhhhhh","id: "+string1+"iiii: "+string2);
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
        values.put(KEY_TITLE, string1.trim());
        values.put(KEY_DATA1, string2.trim());
//        values.put(KEY_DATA2, string3.trim());
//        values.put(KEY_DATA3, string4.trim());
//        values.put(KEY_DATA4, string5.trim());
//        values.put(KEY_DATA5, string6.trim());
            // Inserting Row
            long rowUpdated = db.update(TABLE_QUEST,  values,KEY_ID+"="+id,null);
//        }
//        }


//        dbase.close();
    }

    public Cursor getAllHOMEDATAs() {
//        List<HOMEDATA> quesList = new ArrayList<HOMEDATA>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase=this.getReadableDatabase();

//        Log.e("tttttttzzz","balbal");
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
//        if (cursor.moveToFirst()) {
//            do {
//                HOMEDATA quest = new HOMEDATA();
//                quest.setID(cursor.getInt(0));
//
//
//                quest.setTITLE(cursor.getString(1));
//                quest.setDATA(cursor.getString(2));
//                quesList.add(quest);
//            } while (cursor.moveToNext());
//        }
        // return quest list
        return cursor;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }

    public int getCount() {
        Cursor c = null;
        try {
            dbase = this.getReadableDatabase();
            String query = "SELECT  count(*) FROM " + TABLE_QUEST;
            c = dbase.rawQuery(query, null);
            if (c.moveToFirst()) {
                return c.getInt(0);
            }
            return 0;
        }
        finally {
            if (c != null) {
                c.close();
            }
            if (dbase != null) {
                dbase.close();
            }
        }
    }

    void dropTable(){
        dbase = this.getWritableDatabase();
        dbase.execSQL("DROP TABLE IF EXISTS '" + TABLE_QUEST + "'");
        onCreate(dbase);
    }
}