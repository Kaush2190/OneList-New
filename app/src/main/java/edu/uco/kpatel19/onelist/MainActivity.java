package edu.uco.kpatel19.onelist;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.buddy.sdk.Buddy;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int RETURN = 1;
    private TextView register;
    private TextView yourLists;
    private EditText addList;
    private ArrayAdapter theAdapter;
    private ArrayList <String> listArray;
    private android.widget.ListView theLists;
    private static final String TAG = "HERE";
    private static final String DEFAULT = "-1";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Context myContext = getApplicationContext(); // If there is no context, set myContext to null
        Buddy.init(getApplicationContext(), "bbbbbc.FDwkLFntvPmdc", "ccfe27f2-4eee-9bea-8000-0f4b92ec4243");

        SharedPreferences sharedPreferences = getSharedPreferences("OneListData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("userName", DEFAULT);

        register = (TextView) findViewById(R.id.textVRegister);
        register.setTextColor(getResources().getColor(R.color.link));

        yourLists = (TextView) findViewById(R.id.textVYourLists);

        addList = (EditText)  findViewById(R.id.editTAddList);

        theLists = (ListView) findViewById(R.id.listVYourLists);
        listArray = new ArrayList<String>();
        theAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listArray)
        {//all this code just to change the text color
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.parseColor("#7f7166"));
                return super.getView(position, convertView, parent);
            }
        };
        theLists.setAdapter(theAdapter);

        if(username.equals(DEFAULT)) {
            register.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent register = new Intent(MainActivity.this, RegisterSignIn.class);
                    startActivityForResult(register, RETURN);
                }
            });
        }//end if username null
        else
        {
            yourLists.setText(username + "'s Lists");
            register.setText("Sign Out");
            register.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent register = new Intent(MainActivity.this, RegisterSignIn.class);
                    SharedPreferences sharedPreferences = getSharedPreferences("OneListData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userName", DEFAULT);
                    editor.commit();
                    startActivityForResult(register, RETURN);
                }
            });

        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listArray.add(addList.getText().toString());
                theAdapter.notifyDataSetChanged();
            }
        });


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

        return super.onOptionsItemSelected(item);
    }
}
