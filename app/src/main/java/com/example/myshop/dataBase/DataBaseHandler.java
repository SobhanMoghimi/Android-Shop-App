package com.example.myshop.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.example.myshop.model.Customer;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private Context context;
    private static final String NAME = "database.db";

    //Seller
    private static final String SELLER_TABLE = "SELLER_TABLE";
    private static final String COLUMN_SELLER_NAME = "SELLER_NAME";
    private static final String COLUMN_SELLER_EMAIL = "SELLER_EMAIL";
    private static final String COLUMN_SELLER_PASSWORD = "SELLER_PASSWORD";
    private static final String COLUMN_SELLER_NUMBER = "SELLER_NUMBER";
    private static final String COLUMN_SELLER_LOGIN_COUNT = "SELLER_LOG_COUNT";
    private static final String COLUMN_ID = "ID";

    //Product
    private static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    private static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    private static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    private static final String COLUMN_PRODUCT_SELLER_ID = "PRODUCT_SELLER_ID";
    private static final String COLUMN_PRODUCT_CATEGORY = "PRODUCT_CATEGORY";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    private static final String COLUMN_PRODUCT_IS_PIN = "PRODUCT_PIN";
    private static final String COLUMN_PRODUCT_RELEASE_DATE = "PRODUCT_RELEASE_DATE";
    private static final String COLUMN_PRODUCT_IMAGE = "PRODUCT_IMAGE";
    private static final String COLUMN_PRODUCT_CUSTOMERS_BOOKMARKED = "BOOKMARKED";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    //Customer
    private static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    private static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    private static final String CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    private static final String CUSTOMER_LOGIN_COUNT = "SELLER_LOG_COUNT";


    public DataBaseHandler(@Nullable Context context) {
        super(context,NAME,null,VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String onCreateTableString_Seller = "CREATE TABLE " + SELLER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SELLER_NAME + " TEXT, " + COLUMN_SELLER_EMAIL + " TEXT, " + COLUMN_SELLER_PASSWORD + " TEXT, " + COLUMN_SELLER_NUMBER + " TEXT, "+ COLUMN_SELLER_LOGIN_COUNT + " INT)";
        String createTableStatement_Product = "CREATE TABLE " + PRODUCT_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_PRICE + " INT, " + COLUMN_PRODUCT_DESCRIPTION + " TEXT, " + COLUMN_PRODUCT_SELLER_ID + " INT, " + COLUMN_PRODUCT_CATEGORY + " TEXT, " + COLUMN_PRODUCT_IS_PIN + " TEXT, " + COLUMN_PRODUCT_RELEASE_DATE + " TEXT, " + COLUMN_PRODUCT_IMAGE + " BLOB)";
        String createCustomer = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_EMAIL + " TEXT, " + COLUMN_PRODUCT_CUSTOMERS_BOOKMARKED + " TEXT, "+ CUSTOMER_PASSWORD + " TEXT)";

        db.execSQL(createTableStatement_Product);
        db.execSQL(onCreateTableString_Seller);
        db.execSQL(createCustomer);
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
        String queryStatement = "DELETE FROM " + SELLER_TABLE + " WHERE " + COLUMN_SELLER_EMAIL + " = " + seller.getEmail();

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

    public List<Seller> getAllSellers() {
        List<Seller> allSellers = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + SELLER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStatement,null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String phoneNumber = cursor.getString(3);
                String password = cursor.getString(4);
                int logCount = cursor.getInt(5);
                Seller seller = new Seller(name,email,password,phoneNumber);
                seller.setLogCount(logCount);
                allSellers.add(seller);
            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
        return allSellers;
    }

    public Seller getSeller(String email) {
        Seller seller;
        List<Seller> sellers = this.getAllSellers();
        for (Seller seller1 : sellers) {
            if (seller1.getEmail().equalsIgnoreCase(email)) {
                seller = new Seller(seller1.getFullName(),seller1.getEmail(),seller1.getPassword(),seller1.getPhoneNumber());
                return seller;
            }
        }
        return null;
    }


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

    public boolean addProduct(Product product)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Bitmap imageToStoreBitmap = product.getImage();
        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();

        cv.put(COLUMN_PRODUCT_NAME , product.getName());
        cv.put(COLUMN_PRODUCT_PRICE, product.getPrice());
        cv.put(COLUMN_PRODUCT_CATEGORY, product.getCategory().toString());
        cv.put(COLUMN_PRODUCT_RELEASE_DATE, product.getReleaseDate().toString());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        cv.put(COLUMN_PRODUCT_IS_PIN, product.isPin());
        cv.put(COLUMN_PRODUCT_IMAGE, imageInBytes);
        cv.put(COLUMN_PRODUCT_CUSTOMERS_BOOKMARKED, "");

        long insert = db.insert(PRODUCT_TABLE, null, cv);

        if (insert==-1) {
            return false;
        }

        return true;

    }

    public boolean addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_NAME, customer.getFullName());
        cv.put(CUSTOMER_EMAIL, customer.getEmail());
        cv.put(CUSTOMER_PASSWORD, customer.getPassword());
        cv.put(CUSTOMER_LOGIN_COUNT, 1);

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if (insert==-1)
            return false;
        return true;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStatement,null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                int logCount = cursor.getInt(5);
                Customer customer = new Customer(name,email,password);
                customer.setLogCount(logCount);
                allCustomers.add(customer);
            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
        return allCustomers;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
