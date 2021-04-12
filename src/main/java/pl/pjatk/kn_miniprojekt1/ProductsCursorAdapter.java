package pl.pjatk.kn_miniprojekt1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CursorAdapter;
import android.widget.Switch;
import android.widget.TextView;

import pl.pjatk.kn_miniprojekt1.data.ProductsListDbHelper;
import pl.pjatk.kn_miniprojekt1.data.ProductsContract;

public class ProductsCursorAdapter extends CursorAdapter {
    private Context context;
    private ProductsListDbHelper productsListDbHelper;

    public ProductsCursorAdapter(Context ctx, Cursor cursor) {
        super(ctx, cursor, 0);
        context = ctx;
        productsListDbHelper = new ProductsListDbHelper(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_element, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final int productId = cursor.getInt(cursor.getColumnIndex(ProductsContract.ProductColumns.ID));

        Switch isBoughtSwitch = (Switch)view.findViewById(R.id.switch1);
        isBoughtSwitch.setChecked(cursor.getInt(cursor.getColumnIndex(ProductsContract.ProductColumns.BOUGHT)) == 1);

        TextView productName = (TextView)view.findViewById(R.id.name);
        TextView productPrice = (TextView)view.findViewById(R.id.price);
        TextView productAmount = (TextView)view.findViewById(R.id.amount);

        productName.setText("Nazwa: " + cursor.getString(cursor.getColumnIndex(ProductsContract.ProductColumns.NAME)));
        productPrice.setText("Cena: " + cursor.getString(cursor.getColumnIndex(ProductsContract.ProductColumns.PRICE)));
        productAmount.setText("Ilość: " + cursor.getString(cursor.getColumnIndex(ProductsContract.ProductColumns.AMOUNT)));

        Button deleteButton = (Button)view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductsCursorAdapter.this.context.getContentResolver().delete(ProductsContract.ProductColumns.CONTENT_URI, ProductsContract.ProductColumns.ID + "=" + productId, null);
                swapCursor(productsListDbHelper.getAllProducts());
            }
        });

        Button editButton = (Button)view.findViewById(R.id.edit);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(ProductsCursorAdapter.this.context, EditActivity.class);
                editIntent.putExtra(ProductsCursorAdapter.this.context.getString(R.string.product), productId);
                ProductsCursorAdapter.this.context.startActivity(editIntent);
            }
        });

        isBoughtSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContentValues values = new ContentValues();
                values.put(ProductsContract.ProductColumns.BOUGHT, isChecked);
                productsListDbHelper.updateProductById(values, productId);
            }
        });
    }
}