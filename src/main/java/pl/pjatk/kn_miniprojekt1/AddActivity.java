package pl.pjatk.kn_miniprojekt1;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pl.pjatk.kn_miniprojekt1.data.ProductsContract;

public class AddActivity extends DefaultActivity {

    private AddActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.bindSave();
        activity = this;
    }

    private void insertData() {
        ContentValues[] valuesAttr = new ContentValues[1];
        valuesAttr[0] = new ContentValues();

        EditText name = (EditText)findViewById(R.id.name);
        EditText price = (EditText)findViewById(R.id.price);
        EditText amount = (EditText)findViewById(R.id.amount);

        valuesAttr[0].put(ProductsContract.ProductColumns.NAME, name.getText().toString());
        valuesAttr[0].put(ProductsContract.ProductColumns.PRICE, price.getText().toString());
        valuesAttr[0].put(ProductsContract.ProductColumns.AMOUNT, amount.getText().toString());

        getContentResolver().bulkInsert(ProductsContract.ProductColumns.CONTENT_URI, valuesAttr);
    }

    private void bindSave() {
        final Button button = (Button)findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertData();
                Intent addIntent = new Intent(AddActivity.this, ProductsListActivity.class);
                startActivity(addIntent);
            }
        });
    }
}
