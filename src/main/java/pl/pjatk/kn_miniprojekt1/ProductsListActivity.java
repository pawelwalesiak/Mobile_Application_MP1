package pl.pjatk.kn_miniprojekt1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import pl.pjatk.kn_miniprojekt1.data.ProductsListDbHelper;

public class ProductsListActivity extends DefaultActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

       this.bind_list();
    }

    public void navigateToMain(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }

    public void navigateToAdd(View v) {
        Intent addIntent = new Intent(this, AddActivity.class);
        startActivity(addIntent);
    }

    private void bind_list() {

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new ProductsCursorAdapter(this, new ProductsListDbHelper(this).getAllProducts()));
    }
}
