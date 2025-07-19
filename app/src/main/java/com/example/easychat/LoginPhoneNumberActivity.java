package com.example.easychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.hbb20.CountryCodePicker;

public class LoginPhoneNumberActivity extends AppCompatActivity {

    // Declare UI components
    CountryCodePicker countryCodePicker;
    EditText phoneInput;
    Button sendOtpBtn;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_phone_number); // Set the layout for this activity

        // Initialize UI components using their IDs from the layout XML
        countryCodePicker = findViewById(R.id.login_countrycode); // Country code picker
        phoneInput = findViewById(R.id.login_mobile_number);      // Phone number input field
        sendOtpBtn = findViewById(R.id.send_otp_btn);             // Button to send OTP
        progressBar = findViewById(R.id.login_progress_bar);      // Progress bar

        // Hide the progress bar initially
        progressBar.setVisibility(View.GONE);

        // Register the EditText with CountryCodePicker to enable phone validation
        countryCodePicker.registerCarrierNumberEditText(phoneInput);

        // Set onClick listener for the OTP button
        sendOtpBtn.setOnClickListener((v) -> {
            // Validate the phone number using the CountryCodePicker method
            if (!countryCodePicker.isValidFullNumber()) {
                phoneInput.setError("Phone Number is Not valid"); // Show error if invalid
                return;
            }

            // If valid, create an intent to navigate to LoginOtpActivity
            Intent intent = new Intent(LoginPhoneNumberActivity.this, LoginOtpActivity.class);

            // Pass the full phone number (with country code) to the next activity
            intent.putExtra("phone", countryCodePicker.getFullNumberWithPlus());

            // Start the OTP verification activity
            startActivity(intent);
        });
    }
}
