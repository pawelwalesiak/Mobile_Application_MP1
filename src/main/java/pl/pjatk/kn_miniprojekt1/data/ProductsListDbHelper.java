package pl.pjatk.kn_miniprojekt1.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ProductsListDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ProductsList.db";
    private Context context;

    public ProductsListDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
         this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + ProductsContract.ProductColumns.PRODUCTS + "("
                + ProductsContract.ProductColumns.ID + " INTEGER PRIMARY KEY, "
                + ProductsContract.ProductColumns.NAME + " TEXT, "
                + ProductsContract.ProductColumns.PRICE + " TEXT, "
                + ProductsContract.ProductColumns.AMOUNT + " TEXT, "
                + ProductsContract.ProductColumns.BOUGHT + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ProductsContract.ProductColumns.PRODUCTS);
        onCreate(db);
    }

    public Cursor getAllProducts() {
        return context.getContentResolver().query(ProductsContract.ProductColumns.CONTENT_URI,
                null,
                null,
                null,
                ProductsContract.ProductColumns.ID + " DESC");
    }

    public Cursor getProductById(int productId) {
        return context.getContentResolver().query(ProductsContract.ProductColumns.CONTENT_URI,
                null,
                ProductsContract.ProductColumns.ID + "=" + productId,
                null,
                null
        );
    }

    public void updateProductById(ContentValues values, int productId) {
        context.getContentResolver().update(
                ProductsContract.ProductColumns.CONTENT_URI,
                values,
                ProductsContract.ProductColumns.ID + "=" + productId,
                null);
    }
}
