package com.example.selection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn_submit;

    private EditText eTxt_completeName;
    private EditText eTxt_monthlySalary;
    private EditText eTxt_planCode;

    private double salary;
    private String errorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_submit = findViewById(R.id.submit);

        eTxt_completeName = findViewById(R.id.completeName);
        eTxt_monthlySalary = findViewById(R.id.monthlySalary);
        eTxt_planCode = findViewById(R.id.planCode);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeName = eTxt_completeName.getText().toString();
                String monthlySalary = eTxt_monthlySalary.getText().toString();
                String planCode = eTxt_planCode.getText().toString();
                if(textValidation()) {
                    Toast.makeText(getApplicationContext(),
                            "Please fill out all fields.",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    if(validateInput(monthlySalary)) {
                        Toast.makeText(getApplicationContext(),
                                calculateTotalNet(planCode),
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),
                                "Salary input must be 1 to 100,000 only.",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    private boolean textValidation() {
        boolean invalid = false;
        if(eTxt_completeName.getText().toString().equals("")) {
            invalid = true;
        }
        if(eTxt_monthlySalary.getText().toString().equals("")) {
            invalid = true;
        }
        if(eTxt_planCode.getText().toString().equals("")) {
            invalid = true;
        }
        return invalid;
    }
    private boolean validateInput(String monthlySalary) {
        boolean valid = false;
        if(monthlySalary != "") {
            try {
                DecimalFormat formatter = new DecimalFormat("#.##");
                double convertedSalary = Double.parseDouble(formatter.format(Double.parseDouble(monthlySalary)));
                if(convertedSalary <= 100000 && convertedSalary > 0) {
                    valid = true;
                    salary = convertedSalary;
                }
            }
            catch(Exception e) {

            }
        }
        return valid;
    }
    private String calculateTotalNet(String planCode) {
        DecimalFormat formatter = new DecimalFormat("#,###,##0.00");
        double totalNet = 0;
        String msg = "";
        switch(planCode) {
            case "F":
                totalNet = salary;
                msg = "Your Total Net is : ₱" + formatter.format(totalNet);
                break;
            case "B":
                totalNet = salary - 150.65;
                msg = "Your Total Net is : ₱" + formatter.format(totalNet);
                break;
            case "K":
                totalNet = salary - 357.85;
                msg = "Your Total Net is : ₱" + formatter.format(totalNet);
                break;
            case "E":
                totalNet = salary - 500.50;
                msg = "Your Total Net is : ₱" + formatter.format(totalNet);
                break;
            default:
                msg = "Invalid Plan Code. Choose only F, B, K and E.";
        }
        return msg;
    }

}