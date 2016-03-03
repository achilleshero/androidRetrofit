package com.wazoow.myapp;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by swordmaster on 3/3/2016.
 */
public class ViewPinActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pin);

        final TextView pinTxt = (TextView) findViewById(R.id.pinText);
        /* Get Extra value saved by MainActivity and show it */
        Bundle bundle = getIntent().getExtras();
        pinTxt.setText(bundle.getString("PIN"));
    }
}
