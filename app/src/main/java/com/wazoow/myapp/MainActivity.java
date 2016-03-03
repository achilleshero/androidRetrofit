package com.wazoow.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import android.accounts.Account;
import android.accounts.AccountAuthenticatorActivity;
import android.accounts.AccountManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button mainBtn = (Button) findViewById(R.id.connectBtn);
        final EditText phoneTxt = (EditText) findViewById(R.id.phone);
        final EditText codeTxt = (EditText) findViewById(R.id.code);


        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Use Rest API function defined locally */
                RestAdapter adapter = new RestAdapter.Builder().setEndpoint("http://bebetrack.com/api").build();
                RestApi service = adapter.create(RestApi.class);
                String phone = phoneTxt.getText().toString();
                String code = codeTxt.getText().toString();
                User user = new User(phone, code);

                service.createUser(user, new Callback<ReceiveData>() {
                    @Override
                    public void success(ReceiveData receiveData, Response response) {
                        /* Save token to AccountManager */
                        final Intent res = new Intent();
                        res.putExtra(AccountManager.KEY_AUTHTOKEN, receiveData.token);

                        /* Save PIN code to Next Activity and go to Next Show page */
                        final Intent intent = new Intent(getBaseContext(), ViewPinActivity.class);
                        intent.putExtra("PIN", receiveData.pin);
                        startActivity(intent);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Toast.makeText(getBaseContext(), "Connection Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
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
