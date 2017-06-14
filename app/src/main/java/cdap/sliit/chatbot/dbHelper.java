package cdap.sliit.chatbot;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
public class dbHelper extends SQLiteOpenHelper {


    public dbHelper(Context context) {
        super(context, "sahanaya.db", null, 8);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE friend(id INTEGER PRIMARY KEY, name TEXT, bgimage INTEGER, first INTEGER);");

        ContentValues contentValues=new ContentValues();
        contentValues.put("id",101);
        contentValues.put("name","none");
        contentValues.put("bgimage",0);
        contentValues.put("first",1);

        db.insert("friend",null,contentValues);

        db.execSQL("CREATE TABLE messageNoah(mid INTEGER PRIMARY KEY AUTOINCREMENT,type INTEGER, msg TEXT);");

        contentValues.clear();
        contentValues.put("mid",1000);
        contentValues.put("type",1);
        contentValues.put("msg","Hi friend!\nI'm Noah. It's very nice to meet you :)");

        db.insert("messageNoah",null,contentValues);


        db.execSQL("CREATE TABLE messageEmma(mid INTEGER PRIMARY KEY AUTOINCREMENT,type INTEGER, msg TEXT);");
        contentValues.clear();
        contentValues.put("mid",1000);
        contentValues.put("type",1);
        contentValues.put("msg","Hi friend!\nI'm Emma. It's very nice to meet you :)");

        db.insert("messageEmma",null,contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS friend");
        db.execSQL("DROP IF TABLE EXISTS messageNoah");
        db.execSQL("DROP IF TABLE EXISTS messageEmma");

    onCreate(db);

    }




    public Cursor getFriend() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor data=db.rawQuery("SELECT * FROM friend WHERE id=101",null);
        return data;
    }


    public Cursor getMessageNoah() {
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor data1=db1.rawQuery("SELECT * FROM messageNoah ORDER BY mid DESC LIMIT 20",null);
        return data1;
    }

    public Cursor getMessageEmma() {
        SQLiteDatabase db1=this.getWritableDatabase();
        Cursor data1=db1.rawQuery("SELECT * FROM messageEmma ORDER BY mid DESC LIMIT 20",null);
        return data1;
    }



    public boolean addMessage(int t,String name,String msg) {
        SQLiteDatabase db=this.getWritableDatabase();
        long res;

        ContentValues data = new ContentValues();
        data.put("type", t);
        data.put("msg", msg);

        if(name.equalsIgnoreCase("Emma")) {
            res = db.insert("messageEmma", null, data);
        }
        else {
            res = db.insert("messageNoah", null, data);
        }

        if(res==-1){
            return false;
        }
        else {
            return true;
        }

    }



    public boolean UpdateName(String name) {
        SQLiteDatabase db=this.getWritableDatabase();
        long res;

        ContentValues data = new ContentValues();
        data.put("name", name);

        res = db.update("friend", data, "id=" + 101, null);
        if(res==-1){
            return false;
        }
        else {
            return true;
        }

    }



    public boolean UpdateBG(int bg) {
        SQLiteDatabase db = this.getWritableDatabase();
        long res;

        ContentValues data = new ContentValues();
        data.put("bgimage", bg);

        res = db.update("friend", data, "id=" + 101, null);
        if(res==-1){
            return false;
        }
        else{
            return true;
        }
    }


    public boolean UpdateFirst() {
        SQLiteDatabase db = this.getWritableDatabase();
        long res;

        ContentValues data = new ContentValues();
        data.put("first", 2);

        res = db.update("friend", data, "id=" + 101, null);
        if(res==-1){
            return false;
        }
        else{
            return true;
        }
    }


public boolean deleteAll(char c){

    SQLiteDatabase db = this.getWritableDatabase();
    long res=1;

    if(c=='e'){
        res = db.delete("messageEmma",null,null);
    }
    else{
        res = db.delete("messageNoah",null,null);
    }

    if(res==-1){
        return false;
    }
    else{
        return true;
    }
}

}