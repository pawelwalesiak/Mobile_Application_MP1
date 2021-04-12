package pl.pjatk.kn_miniprojekt1.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class ProductsContentProvider extends ContentProvider {

    private ProductsListDbHelper productsListDbHelper;
    private static final UriMatcher uriMatcher = uriMatcher();
    private static final int PRODUCT_URI_CODE = 1;

    private static UriMatcher uriMatcher(){
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ProductsContract.AUTHORITY;
        uriMatcher.addURI(authority, ProductsContract.ProductColumns.PRODUCTS, PRODUCT_URI_CODE);
        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        productsListDbHelper = new ProductsListDbHelper(getContext());
        return true;
    }

    @Override
    public String getType(Uri uri){
        final int matchedCode = uriMatcher.match(uri);
        switch (matchedCode){
            case PRODUCT_URI_CODE:{
                return ProductsContract.ProductColumns.CONTENT_DIR;
            }
            default:{
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues){
        final int matchedCode = uriMatcher.match(uri);
        final SQLiteDatabase db = productsListDbHelper.getWritableDatabase();
        Uri uriToReturn;
        switch (matchedCode) {
            case PRODUCT_URI_CODE: {
                long id = db.insert(ProductsContract.ProductColumns.PRODUCTS, null, contentValues);
                uriToReturn = ProductsContract.ProductColumns.buildUri(id);
                break;
            }
            default: {
                throw new UnsupportedOperationException("Didn't found any match with this uri: " + uri);
            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return uriToReturn;
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        final int matchedCode = uriMatcher.match(uri);
        Cursor cursor;
        switch(matchedCode){
            case PRODUCT_URI_CODE:{
                cursor = productsListDbHelper.getReadableDatabase().query(
                        ProductsContract.ProductColumns.PRODUCTS,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                return cursor;
            }
            default:{
                throw new UnsupportedOperationException("Didn't found any match with this uri: " + uri);
            }
        }
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs){
        final int matchedCode = uriMatcher.match(uri);
        final SQLiteDatabase db = productsListDbHelper.getWritableDatabase();
        int amountOfUpdated = 0;
        switch(matchedCode){
            case PRODUCT_URI_CODE:{
                amountOfUpdated = db.update(ProductsContract.ProductColumns.PRODUCTS,
                        contentValues,
                        selection,
                        selectionArgs);
                getContext().getContentResolver().notifyChange(uri, null);
                break;
            }
            default:{
                throw new UnsupportedOperationException("Unknown uri: " + uri);
            }
        }

        return amountOfUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        final int matchedCode = uriMatcher.match(uri);
        final SQLiteDatabase db = productsListDbHelper.getWritableDatabase();
        int amountOfDeleted = 0;
        switch(matchedCode){
            case PRODUCT_URI_CODE:
                amountOfDeleted = db.delete(ProductsContract.ProductColumns.PRODUCTS, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return amountOfDeleted;
    }

}
