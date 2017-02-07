package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);


        /**
         * This method displays the given text on the screen.
         */
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String datas = extras.getString("EXTRA_ID");
            if (datas != null) {

            displayMessage(datas);


            }

        }


    }


    private void displayMessage(String message) {
        TextView orderSummaryView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryView.setText(message);
    }

}

