package com.example.myshop.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.myshop.model.Admin;
import com.example.myshop.model.Category;
import com.example.myshop.model.Customer;
import com.example.myshop.model.Product;
import com.example.myshop.model.Seller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String NAME = "database.db";
    private Context context;

    //Seller
    private static final String SELLER_TABLE = "SELLER_TABLE";
    private static final String COLUMN_SELLER_NAME = "SELLER_NAME";
    private static final String COLUMN_SELLER_EMAIL = "SELLER_EMAIL";
    private static final String COLUMN_SELLER_PASSWORD = "SELLER_PASSWORD";
    private static final String COLUMN_SELLER_NUMBER = "SELLER_NUMBER";
    private static final String COLUMN_SELLER_LOGIN_COUNT = "SELLER_LOG_COUNT";
    private static final String SELLER_POSTS = "SELLER_POSTS";
    private static final String COLUMN_ID = "ID";

    //Product
    private static final String PRODUCT_TABLE = "PRODUCT_TABLE";
    private static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    private static final String COLUMN_PRODUCT_PRICE = "PRODUCT_PRICE";
    private static final String COLUMN_PRODUCT_CATEGORY = "PRODUCT_CATEGORY";
    private static final String COLUMN_PRODUCT_DESCRIPTION = "PRODUCT_DESCRIPTION";
    private static final String COLUMN_PRODUCT_IS_PIN = "PRODUCT_PIN";
    private static final String COLUMN_PRODUCT_IMAGE = "PRODUCT_IMAGE";
    private static final String PRODUCT_SELLER_ID = "SELLER_ID";
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageInBytes;

    //Customer
    private static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    private static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    private static final String CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    private static final String CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    private static final String CUSTOMER_LOGIN_COUNT = "CUSTOMER_LOG_COUNT";


    // admin
    public static final String ADMIN_USERNAME = "ADMIN_USERNAME";
    public static final String ADMIN_PASSWORD = "ADMIN_PASSWORD";
    public static final String ADMIN_PIN_PRODUCTS = "ADMIN_PIN_PRODUCTS";
    public static final String ADMIN_TABLE = "ADMIN_TABLE";


    public DataBaseHandler(@Nullable Context context) {
        super(context,NAME,null,VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String onCreateTableString_Seller = "CREATE TABLE " + SELLER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_SELLER_NAME + " TEXT, " + COLUMN_SELLER_EMAIL + " TEXT, " + COLUMN_SELLER_PASSWORD + " TEXT, " + COLUMN_SELLER_NUMBER + " TEXT, "+ COLUMN_SELLER_LOGIN_COUNT + " INT, " + SELLER_POSTS + " INT)";
        String createCustomer = "CREATE TABLE " + CUSTOMER_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CUSTOMER_NAME + " TEXT, " + CUSTOMER_EMAIL + " TEXT, " + CUSTOMER_PASSWORD + " TEXT, " + CUSTOMER_LOGIN_COUNT + " INT)";
        String createTableStatement_Product = "CREATE TABLE " + PRODUCT_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_PRODUCT_NAME + " TEXT, " + COLUMN_PRODUCT_PRICE + " INT, " + COLUMN_PRODUCT_DESCRIPTION + " TEXT, "+ PRODUCT_SELLER_ID + " INT, " + COLUMN_PRODUCT_CATEGORY + " TEXT, " + COLUMN_PRODUCT_IS_PIN + " TEXT, " + COLUMN_PRODUCT_IMAGE + " BLOB)";
        String createTableAdmin = "CREATE TABLE " + ADMIN_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ADMIN_USERNAME + " TEXT, " + ADMIN_PASSWORD + " TEXT, " + ADMIN_PIN_PRODUCTS + " TEXT)";

        db.execSQL(createTableAdmin);
        db.execSQL(createTableStatement_Product);
        db.execSQL(onCreateTableString_Seller);
        db.execSQL(createCustomer);
    }

    public void addAdmin() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ADMIN_USERNAME,"admin");
        cv.put(ADMIN_PASSWORD,"admin");
        cv.put(ADMIN_PIN_PRODUCTS,0);

        db.insert(ADMIN_TABLE,null,cv);
    }

    public Admin getAdmin() {
        List<Admin> allAdmins = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + ADMIN_TABLE + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStatement,null);

        if (cursor.moveToFirst()) {
            do {
                Admin admin = new Admin();
                allAdmins.add(admin);
            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
        return allAdmins.get(0);
    }

    public void addProductToPromoted(Admin admin, Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ADMIN_PIN_PRODUCTS, String.valueOf(product.getId())+",");

        db.insert(ADMIN_TABLE,null,cv);
    }

    public List<Product> getPinedProducts() {
        List<Product> pinedProducts = new ArrayList<>();
        for (Product product : this.getAllProducts()) {
            if (product.isPin().equals("true")){
                pinedProducts.add(product);
            }
        }
        return pinedProducts;
    }

    public List<Product> getSortedProducts() {
        List<Product> sorted = this.getPinedProducts();
        for (Product product : this.getAllProducts()) {
            if (product.isPin().equals("false"))
                sorted.add(product);
        }
        return sorted;
    }

    public boolean addSeller(Seller seller) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SELLER_NAME, seller.getFullName().toString());
        cv.put(COLUMN_SELLER_EMAIL, seller.getEmail().toString());
        cv.put(COLUMN_SELLER_NUMBER, seller.getPhoneNumber());
        cv.put(COLUMN_SELLER_PASSWORD, seller.getPassword().toString());
        cv.put(COLUMN_SELLER_LOGIN_COUNT, 1);
        cv.put(SELLER_POSTS,0);

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

    public boolean deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryStatement = "DELETE FROM " + PRODUCT_TABLE + " WHERE " + COLUMN_ID + " = " + product.getId();

        Cursor cursor = db.rawQuery(queryStatement,null);

        if (cursor.moveToFirst()) {
            return true;
        }

        return false;
    }


    public void deleteAllSellers() {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryStatement = "DELETE FROM " + SELLER_TABLE + ";";

        db.execSQL(queryStatement);
    }

    public void deleteAllCustomers() {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryStatement = "DELETE FROM " + CUSTOMER_TABLE + ";";

        db.execSQL(queryStatement);
    }

    public void deleteAllProducts() {

        SQLiteDatabase db = this.getWritableDatabase();
        String queryStatement = "DELETE FROM " + PRODUCT_TABLE + ";";

        db.execSQL(queryStatement);
    }

    public List<Seller> getAllSellers() {
        List<Seller> allSellers = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + SELLER_TABLE + ";";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStatement,null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String phoneNumber = cursor.getString(4);
                String password = cursor.getString(3);
                int logCount = Integer.parseInt(cursor.getString(5));
                int posts = cursor.getInt(6);
                Seller seller = new Seller(name,email,password,phoneNumber);
                seller.setLoginCount(logCount);
                seller.setId(id);
                seller.setPosts(posts);
                allSellers.add(seller);
            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
        return allSellers;
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
        cv.put(COLUMN_PRODUCT_CATEGORY, product.getCategoryString());
        cv.put(COLUMN_PRODUCT_DESCRIPTION, product.getDescription());
        cv.put(COLUMN_PRODUCT_IS_PIN, product.isPin());
        cv.put(COLUMN_PRODUCT_IMAGE, imageInBytes);
        cv.put(PRODUCT_SELLER_ID,Seller.getActiveSeller().getId());

        long insert = db.insert(PRODUCT_TABLE, null, cv);
        if (insert==-1) {
            return false;
        }

        return true;
    }


    public void addSellerPostCount(Seller seller,int newNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SELLER_POSTS, newNum);
        long update = db.update(SELLER_TABLE,cv,COLUMN_ID + " = ?", new String[] {String.valueOf(seller.getId())});
    }

    public List<Product> getAllProductsOfSeller(Seller seller)
    {
        try{
            List<Product> AllProducts = this.getAllProducts();
            List<Product> AllProductsOfSeller = new ArrayList<>();
            for (Product product : AllProducts){
                if(product.getSeller().getId()==seller.getId())
                    AllProductsOfSeller.add(product);
            }
            return AllProductsOfSeller;
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public List<Product> getAllProducts()
    {
        try {
            List<Product> products = new LinkedList<>();
            String query = "SELECT * FROM " + PRODUCT_TABLE;
            SQLiteDatabase DB = this.getReadableDatabase();
            Cursor cursor = DB.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                //loop through the table of products
                do {
                    int Id = cursor.getInt(0);
                    String Name = cursor.getString(1);
                    int Price = cursor.getInt(2);
                    Seller seller = this.getSellerById(cursor.getInt(4));
                    String Description = cursor.getString(3);
                    String Category = cursor.getString(5);
                    String isPin = cursor.getString(6);
                    byte[] imageBytes = cursor.getBlob(7);

                    Bitmap imageProduct = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                    boolean isPinBool = isPin.equals("true");
                    Product product = new Product(Name,Id, Price, imageProduct, seller, Description, isPinBool , Category);

                    product.setPin(isPinBool);
                    products.add(product);
                } while (cursor.moveToNext());
            }
            cursor.close();
            DB.close();
            return products;
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    public void pinProducts(Admin admin) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryCommand = "SELECT * FROM " + ADMIN_TABLE + " WHERE " + COLUMN_ID + " = 1";
        Cursor cursor = db.rawQuery(queryCommand,null);
        if (cursor.moveToFirst() && !cursor.getString(4).equals("")) {
            do {
                String pinedProductsId = cursor.getString(4);
                String[] ids = pinedProductsId.split(",");
                for (Product product : this.getAllProducts()) {
                    for (String s:ids) {
                        if (product.getId()==Integer.parseInt(s))
                            this.addProductToPromoted(admin,product);
                    }
                }
            }while (cursor.moveToFirst());
        }
    }

    public boolean addCustomer(Customer customer) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_NAME, customer.getFullName());
        cv.put(CUSTOMER_EMAIL, customer.getEmail());
        cv.put(CUSTOMER_PASSWORD, customer.getPassword());
        cv.put(CUSTOMER_LOGIN_COUNT, 1);

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if (insert==-1) {
            return false;
        }
        return true;
    }

    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryStatement,null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String email = cursor.getString(2);
                String password = cursor.getString(3);
                int logCount = cursor.getInt(4);
                Customer customer = new Customer(name,email,password);
                customer.setLoginCount(logCount);
                customer.setId(id);
                allCustomers.add(customer);
            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
        return allCustomers;
    }

    public boolean updateCustomerLogCount(Customer customer, int newNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_LOGIN_COUNT, newNum);
        long update = db.update(CUSTOMER_TABLE,cv,CUSTOMER_EMAIL + " = ?", new String[] {customer.getEmail()});

        return update !=-1;
    }

    public boolean updateSellerLogCount(Seller seller, int newNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SELLER_LOGIN_COUNT, newNum);
        long update = db.update(SELLER_TABLE,cv,COLUMN_SELLER_EMAIL + " = ?", new String[] {seller.getEmail()});

        return update !=-1;
    }

    public boolean updateSeller(Seller seller, String name, String email, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SELLER_NAME,name);
        cv.put(COLUMN_SELLER_EMAIL,email);
        cv.put(COLUMN_SELLER_NUMBER,phone);

        long update = db.update(SELLER_TABLE,cv,COLUMN_ID + " = ?" , new String[] {String.valueOf(seller.getId())});

        return update!=-1;
    }

    public boolean updateCustomer(Customer customer, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_NAME,name);
        cv.put(CUSTOMER_EMAIL, email);

        long update = db.update(CUSTOMER_TABLE,cv,COLUMN_ID + " = ?",new String[] {String.valueOf(customer.getId())});

        return update!=-1;
    }

    public boolean updateProduct(Product product, String name, int price, Bitmap newImage, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        Bitmap imageToStoreBitmap = newImage;
        byteArrayOutputStream = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        imageInBytes = byteArrayOutputStream.toByteArray();


        cv.put(COLUMN_PRODUCT_NAME,name);
        cv.put(COLUMN_PRODUCT_PRICE,price);
        cv.put(COLUMN_PRODUCT_IMAGE,imageInBytes);
        cv.put(COLUMN_PRODUCT_DESCRIPTION,description);

        long update = db.update(PRODUCT_TABLE,cv, COLUMN_ID + " = ?",new String[] {String.valueOf(product.getId())});

        return update!=-1;
    }

    public Seller getSellerById(int id) {
        List<Seller> sellers = this.getAllSellers();
        for (Seller seller : sellers) {
            if (seller.getId()==id) {
                return seller;
            }
        }
        return null;
    }

    public int getSellerId(String email) {
        List<Seller> sellers = this.getAllSellers();
        for (Seller seller : sellers) {
            if (seller.getEmail().equals(email)) {
                return seller.getId();
            }
        }
        return 0;
    }

    public boolean updatePassSeller(Seller seller,String newPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SELLER_PASSWORD,newPass);

        long update = db.update(SELLER_TABLE,cv,COLUMN_ID + " = ?",new String[] {String.valueOf(seller.getId())});
        return update!=-1;
    }

    public Customer getCustomerById(int id) {
        List<Customer> customers = this.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getId()==id) {
                return customer;
            }
        }
        return null;
    }

    public int getCustomerId(String email) {
        List<Customer> customers = this.getAllCustomers();
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer.getId();
            }
        }
        return 0;
    }

    public boolean updatePassCustomer(Customer customer,String newPass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_PASSWORD,newPass);

        long update = db.update(CUSTOMER_TABLE,cv,COLUMN_ID + " = ?",new String[] {String.valueOf(customer.getId())});
        return update!=-1;
    }

    public List<Product> getProductsByCategory(Category category) {
        List<Product> products = new ArrayList<>();
        for (Product product : this.getAllProducts()) {
            if (product.getCategory()==category) {
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> getProductsByName(String name) {
        List<Product> products = new ArrayList<>();
        for (Product product : this.getAllProducts()) {
            if (product.getName().contains(name)) {
                products.add(product);
            }
        }
        return products;
    }

    public List<Product> searchProductByNameInCategory(String name,Category category) {
        List<Product> products = new ArrayList<>();
        for (Product product : this.getAllProducts()) {
            if (product.getName().contains(name) && product.getCategory()==category) {
                products.add(product);
            }
        }
        return products;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
