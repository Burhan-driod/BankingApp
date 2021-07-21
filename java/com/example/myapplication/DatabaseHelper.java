package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL INT,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(1234567890,'Baig',9472.00,'1','129XXXX7892','Spark0001')");
        db.execSQL("insert into user_table values(2345678901,'Alex',582.67,'2','41873XXX2341','kkbk00690')");
        db.execSQL("insert into user_table values(3456789012,'KBR',11359.56,'3','5389XXX412','Spark003')");
        db.execSQL("insert into user_table values(4567890123,'BRL',1500.01,'4','09301XXXX412','Spark004')");
        db.execSQL("insert into user_table values(5678901234,'Amey',21603.48,'5','10101XXX2345','Spark005')");
        db.execSQL("insert into user_table values(6789012345,'Ashish',9145.16,'6','9482XXXXX3452','Spark006')");
        db.execSQL("insert into user_table values(7890123456,'Ananth',5936.00,'7','382XXXX4523','Spark007')");
        db.execSQL("insert into user_table values(8901234567,'Mango',1857.22,'8','4820XXXXXX5234','Spark001')");
        db.execSQL("insert into user_table values(9012345678,'Nikola',41398.46,'9','48291XXX3456','Spark003')");
        db.execSQL("insert into user_table values(1234567809,'Dsu',10273.90,'10','472XXX4563','cnrb0061')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
