package com.example.aaa.chatapp.activites;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.aaa.chatapp.MainActivity;
import com.example.aaa.chatapp.R;
import com.rilixtech.Country;
import com.rilixtech.CountryCodePicker;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "HTAG";
    private Toolbar toolbar;
    private Button btnLogin;
    private EditText editNumber;
    private CardView cardViewLogin;
    private CardView btnDoneCardView;
    private EditText editCode;
    private Button btnDone;
    private TextInputLayout textInputEditTextNumber;
    private TextInputLayout textInputEditTextCode;
    private CountryCodePicker ccp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbar_login_activity);
        toolbar.setTitle("Login");
        setSupportActionBar(toolbar);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                Log.d(TAG, "onCountrySelected: + : " + selectedCountry.getName());
            }
        });

        textInputEditTextNumber = findViewById(R.id.textInputEditText_muber_login_activity);
        textInputEditTextCode = findViewById(R.id.textInputEditText_code_login_activity);
        editNumber = findViewById(R.id.edit_phone_number_login_activity);
        editNumber.setSelection(editNumber.getText().length());
        cardViewLogin = findViewById(R.id.btn_login_card_view);
        btnLogin = findViewById(R.id.btn_login_login_activity);
        editCode = findViewById(R.id.edit_code_login_activity);
        btnDone = findViewById(R.id.btn_done_login_activity);
        btnDoneCardView = findViewById(R.id.btn_done_card_view);


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterUserActivity.class));
                finish();
            }
        });

        btnDoneCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: btn DONE");

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNumber.setVisibility(View.GONE);
                cardViewLogin.setVisibility(View.GONE);
                textInputEditTextNumber.setVisibility(View.GONE);
                ccp.setVisibility(View.GONE);

                btnDoneCardView.setVisibility(View.VISIBLE);
                editCode.setVisibility(View.VISIBLE);
                textInputEditTextCode.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        moveTaskToBack(true);
    }
}
