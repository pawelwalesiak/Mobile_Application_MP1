package pl.pjatk.kn_miniprojekt1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import pl.pjatk.kn_miniprojekt1.data.ProductsListDbHelper;
import pl.pjatk.kn_miniprojekt1.data.ProductsContract;

public class EditActivity extends DefaultActivity {

    private EditText productName;
    private EditText productPrice;
    private EditText productAmount;
    private ProductsListDbHelper procuctListDbHelper;
    private int productId;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.acitivity_edit);

        procuctListDbHelper = new ProductsListDbHelper(this);

        productName = (EditText)findViewById(R.id.name);
        productPrice = (EditText)findViewById(R.id.price);
        productAmount = (EditText)findViewById(R.id.amount);

        Intent i = getIntent();
        productId = i.getIntExtra(getString(R.string.product), 0);

        Cursor cursor = procuctListDbHelper.getProductById(productId);
        cursor.moveToFirst();

        productName.setText(cursor.getString(cursor.getColumnIndex(ProductsContract.ProductColumns.NAME)));
        productPrice.setText(cursor.getString(cursor.getColumnIndex(ProductsContract.ProductColumns.PRICE)));
        productAmount.setText(cursor.getString(cursor.getColumnIndex(ProductsContract.ProductColumns.AMOUNT)));
    }

    public void update(View v) {
        ContentValues values = new ContentValues(1);

        values.put(ProductsContract.ProductColumns.NAME, productName.getText().toString());
        values.put(ProductsContract.ProductColumns.PRICE, productPrice.getText().toString());
        values.put(ProductsContract.ProductColumns.AMOUNT, productAmount.getText().toString());

        procuctListDbHelper.updateProductById(values, productId);

        Intent productListIntent = new Intent(this, ProductsListActivity.class);
        startActivity(productListIntent);
    }
}
