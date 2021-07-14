package com.example.myshop.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;
import com.example.myshop.model.Product;
import java.io.ByteArrayOutputStream;

public class DataBaseHandlerProduct extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private Context context;
    private static final String NAME = "seller.db";
    private static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    private static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    private static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    private static final String COLUMN_PRODUCT_SELLER_ID = "PRODUCT_SELLER_ID";
    private static final String COLUMN_PRODUCT_CATEGORY = "PRODUCT_CATEGORY";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    private static final String COLUMN_PRODUCT_IS_PIN = "PRODUCT_PIN";
    private static final String COLUMN_PRODUCT_RELEASE_DATE = "PRODUCT_RELEASE_DATE";
    private static final String COLUMN_PRODUCT_IMAGE = "PRODUCT_IMAGE";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;


    private static final String COLUMN_ID = "ID";

    public DataBaseHandlerProduct(@Nullable Context context) {
        super(context, NAME, null, VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + PRODUCT_TABLE + " (" + COLUMN_ID + " PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_PRICE + " INT, " + COLUMN_PRODUCT_DESCRIPTION + " TEXT, " +
                COLUMN_PRODUCT_SELLER_ID + " INT, " + COLUMN_PRODUCT_CATEGORY + " TEXT, " + COLUMN_PRODUCT_IS_PIN + " INT, " + COLUMN_PRODUCT_RELEASE_DATE + " TEXT, " + COLUMN_PRODUCT_IMAGE + " BLOB);";

        db.execSQL(createTableStatement);
    }

    public boolean addProduct(Product product) {
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
        cv.put(COLUMN_PRODUCT_SELLER_ID, product.getSeller().getId());
        cv.put(COLUMN_PRODUCT_IMAGE, imageInBytes);

        long insert = db.insert(PRODUCT_TABLE, null, cv);

        if (insert==-1) {
            return false;
        }

        return true;

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
