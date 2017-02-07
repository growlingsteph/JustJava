package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
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
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameView = (EditText) findViewById(R.id.name);
        String name = String.valueOf(nameView.getText());

        CheckBox whippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        Log.v("Main Activity", "Has whipped cream? " + hasWhippedCream);

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("Main Activity", "Has chocolate? " + hasChocolate);

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name,price,hasWhippedCream,hasChocolate);

//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
//        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }

        displayMessage(priceMessage);

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
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryView.setText(message);
    }

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