package com.example.myshop.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myshop.model.Seller;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandlerSeller extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private Context context;
    private static final String NAME = "seller.db";
    private static final String SELLER_TABLE = "SELLER_TABLE";
    private static final String COLUMN_SELLER_NAME = "SELLER_NAME";
    private static final String COLUMN_SELLER_EMAIL = "SELLER_EMAIL";
    private static final String COLUMN_SELLER_PASSWORD = "SELLER_PASSWORD";
    private static final String COLUMN_SELLER_NUMBER = "SELLER_NUMBER";
    private static final String COLUMN_SELLER_LOGIN_COUNT = "SELLER_LOG_COUNT";
    private static final String COLUMN_ID = "ID";


    public DataBaseHandlerSeller(@Nullable Context context) {
        super(context,NAME,null,VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + SELLER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SELLER_NAME + " TEXT, " + COLUMN_SELLER_EMAIL + " TEXT, " + COLUMN_SELLER_PASSWORD + " TEXT, " + COLUMN_SELLER_NUMBER + " TEXT, "+ COLUMN_SELLER_LOGIN_COUNT + " INT)";

        db.execSQL(createTableStatement);


    }

    public boolean addSeller(Seller seller) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SELLER_NAME, seller.getFullName());
        cv.put(COLUMN_SELLER_EMAIL, seller.getEmail());
        cv.put(COLUMN_SELLER_NUMBER, seller.getPhoneNumber());
        cv.put(COLUMN_SELLER_PASSWORD, seller.getPassword());
        cv.put(COLUMN_SELLER_LOGIN_COUNT, 1);

        long insert = db.insert(SELLER_TABLE,null, cv);

        if (insert==-1) {
            return false;
        }
        return true;

    }

    public boolean deleteSeller(Seller seller) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryStatement = "DELETE FROM " + SELLER_TABLE + " WHERE " + COLUMN_ID + " = " + seller.getId();

        Cursor cursor = db.rawQuery(queryStatement,null);

        if (cursor.moveToFirst()) {
            return true;
        }

        return false;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryStatement = "DELETE FROM " + SELLER_TABLE + ";";

        db.execSQL(queryStatement);
    }

//    public Seller logInSeller(String email, String password) {
//
//    }

//    public boolean deleteOne(Person person) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String queryString = "DELETE FROM " + CUSTOMER_TABLE +" WHERE " + COLUMN_ID + " = " + person.getId();
//
//        Cursor cursor = sqLiteDatabase.rawQuery(queryString,null);
//        if (cursor.moveToFirst()) {
//            return true;
//        }
//        return false;
//
//    }

//    public List<Person> getEveryone() {
//        List<Person> returnList = new ArrayList<>();
//
//        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.rawQuery(queryString,null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                int id = cursor.getInt(0);
//                String name = cursor.getString(1);
//                int age = cursor.getInt(2);
//
//                Person person = new Person(name,age);
//                returnList.add(person);
//            }while (cursor.moveToNext());
//        } else {
//
//        }
//
//        cursor.close();
//        db.close();
//        return returnList;
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
