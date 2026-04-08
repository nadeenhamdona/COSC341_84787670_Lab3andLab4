package com.example.lab2_part1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._-]+@[A-Za-z0-9-]+(?:\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,63}$"
    );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$"
    );

    private EditText etName;      // @+id/editTextText
    private EditText etEmail;     // @+id/editTextText2
    private RadioGroup rgGender;  // @+id/radioGroup
    private EditText etBirthday;  // @+id/birthday
    private EditText etPassword;  // @+id/password
    private Button btnRegister;   // @+id/button

    private final Calendar calendar = Calendar.getInstance();
    private final SimpleDateFormat dobFormatter =
            new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View root = findViewById(R.id.main);
        if (root != null) {
            ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
                Insets sb = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(sb.left, sb.top, sb.right, sb.bottom);
                return insets;
            });
        }

        etName     = findViewById(R.id.editTextText);
        etEmail    = findViewById(R.id.editTextText2);
        rgGender   = findViewById(R.id.radioGroup);
        etBirthday = findViewById(R.id.birthday);
        etPassword = findViewById(R.id.password);
        btnRegister= findViewById(R.id.button);

        etBirthday.setFocusable(false);
        etBirthday.setFocusableInTouchMode(false);
        etBirthday.setClickable(true);
        etBirthday.setInputType(InputType.TYPE_NULL);
        calendar.set(2000, Calendar.JANUARY, 1);
        etBirthday.setOnClickListener(v -> showDatePicker());

        btnRegister.setOnClickListener(v -> onRegisterClicked());
    }

    private void onRegisterClicked() {
        String name     = safeText(etName).trim();
        String email    = safeText(etEmail).trim();
        String birthday = safeText(etBirthday).trim();
        String password = safeText(etPassword);


        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            etName.requestFocus();
            return;
        }


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter your email address", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
            return;
        }

        int checkedId = rgGender.getCheckedRadioButtonId();
        if (checkedId == -1) {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            rgGender.requestFocus();
            return;
        }
        String gender = ((RadioButton) findViewById(checkedId)).getText().toString();

        if (TextUtils.isEmpty(birthday)) {
            Toast.makeText(this, "Please enter a date of birth", Toast.LENGTH_SHORT).show();
            etBirthday.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password) || password.length() < 8) {
            Toast.makeText(this, "Password should be at least 8 characters long", Toast.LENGTH_SHORT).show();
            etPassword.requestFocus();
            return;
        }
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            Toast.makeText(this, "Password should contain 1 numeric digit, 1 uppercase letter, and 1 special character", Toast.LENGTH_LONG).show();
            etPassword.requestFocus();
            return;
        }

        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("gender", gender);
        intent.putExtra("birthday", birthday);
        startActivity(intent);
    }

    private void showDatePicker() {
        int y = calendar.get(Calendar.YEAR);
        int m = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (DatePicker view, int year, int month, int dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    etBirthday.setText(dobFormatter.format(calendar.getTime()));
                },
                y, m, d
        );
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis()); // No fechas futuras

        Calendar min = Calendar.getInstance();
        min.set(1900, Calendar.JANUARY, 1);
        dialog.getDatePicker().setMinDate(min.getTimeInMillis());

        dialog.show();
    }

    private String safeText(EditText et) {
        CharSequence cs = et.getText();
        return cs == null ? "" : cs.toString();
    }
}













