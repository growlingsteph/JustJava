package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button order = (Button) findViewById(R.id.order);

        // Set a click listener on that View
        order.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent summaryIntent = new Intent(MainActivity.this, SummaryActivity.class);

                EditText nameView = (EditText) findViewById(R.id.name);
                String name = String.valueOf(nameView.getText());

                CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
                boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
                Log.v("Main Activity", "Has whipped cream? " + hasWhippedCream);

                CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
                boolean hasChocolate = chocolateCheckBox.isChecked();
                Log.v("Main Activity", "Has chocolate? " + hasChocolate);

                int price = calculatePrice(hasWhippedCream, hasChocolate);
                String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

                summaryIntent.putExtra("EXTRA_ID", priceMessage);
                // Start the new activity
                startActivity(summaryIntent);
            }
        });
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if( quantity <= 100 ) {
            quantity++;
        } else {
            Toast.makeText(this, "Cannot buy more than 100 cups of coffee", Toast.LENGTH_LONG).show();
        }        displayQuantity(quantity);
        Log.v("Increment View", "Quantity: " + quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if( quantity > 0 ) {
            quantity--;
        } else {
            Toast.makeText(this, "Cannot go below 0 cups of coffee", Toast.LENGTH_LONG).show();
        }
        displayQuantity(quantity);
        Log.v("Decremnet View", "Quantity: " + quantity);
    }


        /**
         * This method displays the given quantity value on the screen.
         */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }



    /**
     * This method is called when the order button is clicked.
     */
//   public void submitOrder(View view) {
//        EditText nameView = (EditText) findViewById(R.id.name);
//        String name = String.valueOf(nameView.getText());
//
//        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
//        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
//        Log.v("Main Activity", "Has whipped cream? " + hasWhippedCream);
//
//        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
//        boolean hasChocolate = chocolateCheckBox.isChecked();
//        Log.v("Main Activity", "Has chocolate? " + hasChocolate);
//
//        int price = calculatePrice(hasWhippedCream, hasChocolate);
//        String priceMessage = createOrderSummary(name,price,hasWhippedCream,hasChocolate);
//
//        displayMessage(priceMessage);
//
//     }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream is true when the checkbox is marked
     * @param hasChocolate is true when the checkbox is marked
     *
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int price = quantity * 5;
        if(hasWhippedCream){
            price += 1;
        }
        if(hasChocolate) {
            price += 2;
        }
        return price;
    }

    private String createOrderSummary(String name, int price, boolean hasWhippedCream, boolean hasChocolate){
        String orderMessage;
        orderMessage = "Name: " + name +
                "\nAdd Whipped Cream? " + hasWhippedCream +
                "\nAdd Chocolate? " + hasChocolate +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price +
                "\nThank you!";
        return orderMessage;
    }





}