package pl.pjatk.kn_miniprojekt1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends DefaultActivity {
    private TextView productsListName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productsListName = (TextView) findViewById(R.id.productsListName);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String headerText = sharedPreferences.getString(getString(R.string.productsListName), "");
        productsListName.setText(headerText);
    }

    public void navigateToProductsList(View view) {
        Intent productsListIntent = new Intent(this, ProductsListActivity.class);
        startActivity(productsListIntent);
    }

    public void navigateToAppSettings(View view) {
        Intent optionsIntent = new Intent(this, OptionsActivity.class);
        startActivity(optionsIntent);
    }
}
