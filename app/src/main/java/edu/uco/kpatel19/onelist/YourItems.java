package edu.uco.kpatel19.onelist;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class YourItems extends Activity {

    private TextView listPassed;
    private String whichList;
    private static final String DEFAULT = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_items);

        SharedPreferences sharedPreferences = getSharedPreferences("OneListData", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("userName", DEFAULT);

        TextView listPassed = (TextView) findViewById(R.id.tVListClicked);
        whichList = getIntent().getStringExtra("listClicked");
        listPassed.setText(username + "'s " + whichList + " List");
    }

}
