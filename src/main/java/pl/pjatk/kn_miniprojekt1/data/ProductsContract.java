package pl.pjatk.kn_miniprojekt1.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class ProductsContract {
    public static final String AUTHORITY = "pl.pjatk.kn_miniprojekt1.productlist";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    public static final class ProductColumns implements BaseColumns {
        public static final String PRODUCTS = "products";
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String PRICE = "price";
        public static final String AMOUNT = "amount";
        public static final String BOUGHT = "bought";
        public static final Uri CONTENT_URI = ProductsContract.CONTENT_URI.buildUpon().appendPath(PRODUCTS).build();

        public static final String CONTENT_DIR =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + AUTHORITY + "/" + PRODUCTS;

        public static Uri buildUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }
}
