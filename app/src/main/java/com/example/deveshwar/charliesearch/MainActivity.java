package com.example.deveshwar.charliesearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deveshwar.charliesearch.data.Query;
import com.example.deveshwar.charliesearch.data.QueryDbHelper;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    public int isInternetPresent;
    public ConnectionDetector connectionDetector;
    QueryDbHelper queryDbHelper = new QueryDbHelper(this);
    public int i=0;int mId=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Nothing To See Here, Go Type Your Search Above", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        editText =(EditText)findViewById(R.id.searchText);
        connectionDetector = new ConnectionDetector(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.clear_history){
            queryDbHelper.deleteAllQuery();
        }

        return super.onOptionsItemSelected(item);
    }

    public void SearchNow(View view) {
        isInternetPresent = connectionDetector.getConnectivityStatus(getApplicationContext());
        if (isInternetPresent == 1 ) {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            String term = editText.getText().toString();
            intent.putExtra(SearchManager.QUERY, term);
            startActivity(intent);
        } else {
            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            String searchText = editText.getText().toString();
            //dbHandler.addQuery(new Query(i,searchText));*/
            Log.d("Insert: ", "Inserting ..");
            queryDbHelper.addQuery(new Query(i, searchText, seconds));
            Context context = getApplicationContext();
            CharSequence text = "Net Nhi Hai Bad Mei Aana";
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();i++;
        }
    }
}

