package com.company.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnDot
            ,btnMulti ,btnDivide, btnSub, btnAdd, btnEqual, btnAC, btnDel;
    private TextView textViewResult, textViewHistory;
    private String number = null;

    double firstNum = 0, lastNum =0;

    String status = null;
    boolean operator = false;

    DecimalFormat myFormatter = new DecimalFormat("#######.######");

    String history, currentResult;

    boolean dot = true;

    boolean btnACcontrol = true;

    boolean btnEqualsControl = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnDivide = findViewById(R.id.btnDivide);
        btnMulti = findViewById(R.id.btnMulti);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);

        btnDel = findViewById(R.id.btnDEL);
        btnAC = findViewById(R.id.btnAC);
        btnDot = findViewById(R.id.btnDot);
        btnEqual = findViewById(R.id.btnEqual);

        textViewHistory = findViewById(R.id.textViewHistory);
        textViewResult = findViewById(R.id.textViewResult);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("0");
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("1");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("2");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("3");
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("4");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("5");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("6");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("7");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("8");
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberClick("9");
            }
        });

        btnAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = null;
                status = null;
                textViewResult.setText("0");
                textViewHistory.setText("");
                firstNum = 0;
                lastNum = 0;
                btnACcontrol = true;
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnACcontrol){
                    textViewResult.setText("0");
                }
                else{
                    number = number.substring(0,number.length()-1);
                    if(number.length() == 0){
                        btnDel.setClickable(false);
                    }
                    else if (number.contains(".")){
                        dot = false;
                    }
                    else{
                        dot = true;
                    }
                    textViewResult.setText(number);
                }

            }
        });

        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dot){
                    if(number == null){
                        number = "0.";
                    }
                    else{
                        number = number+".";
                    }
                }
                textViewResult.setText(number);
                dot = false;
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(operator){
                    if(status == "addition"){
                        Add();
                    }else if(status == "subtraction"){
                        Sub();
                    }else if(status == "multiplication"){
                        Multiply();
                    }else if(status == "division"){
                        Divide();
                    }
                    else {
                        firstNum = Double.parseDouble(textViewResult.getText().toString());
                    }
                    operator = false;
                    btnEqualsControl = true;
                }
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"/");

                if(operator == true){
                    if(status == "multiplication"){
                        Multiply();
                    }
                    else if(status == "subtraction"){
                        Sub();
                    }
                    else if(status == "addition"){
                        Add();
                    }
                    else {
                        Divide();
                    }
                }
                status = "division";
                operator = false;
                number = null;
            }
        });

        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"x");

                if(operator == true){
                    if(status == "subtraction"){
                        Sub();
                    }
                    else if(status == "division"){
                        Divide();
                    }
                    else if(status == "addition"){
                        Add();
                    }
                    else {
                        Multiply();
                    }
                }
                status = "multiplication";
                operator = false;
                number = null;
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"-");

                if(operator == true){
                    if(status == "multiplication"){
                        Multiply();
                    }
                    else if(status == "division"){
                        Divide();
                    }
                    else if(status == "addition"){
                        Add();
                    }
                    else {
                        Sub();
                    }
                }
                status = "subtraction";
                operator = false;
                number = null;
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history+currentResult+"+");

                if(operator == true){
                    if(status == "multiplication"){
                        Multiply();
                    }
                    else if(status == "division"){
                        Divide();
                    }
                    else if(status == "subtraction"){
                        Sub();
                    }
                    else {
                        Add();
                    }
                }

                status = "addition";
                operator = false;
                number = null;
            }
        });
    }

    public void numberClick(String view){
        if(number == null){
            number = view;
        }
        else if(btnEqualsControl){
            firstNum = 0;
            lastNum = 0;
            number = view;
        }
        else {
            number = number + view;
        }

        textViewResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDel.setClickable(true);
        btnEqualsControl = false;
    }

    public void Add(){
        lastNum = Double.parseDouble(textViewResult.getText().toString());
        firstNum = firstNum + lastNum;

        textViewResult.setText(myFormatter.format(firstNum));
        dot = true;
    }

    public void Sub(){
        if(firstNum == 0){
            firstNum = Double.parseDouble(textViewResult.getText().toString());
        }
        else {
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = firstNum - lastNum;
        }

        textViewResult.setText(myFormatter.format(firstNum));
        dot = true;
    }

    public void Multiply(){
        if(firstNum == 0){
            firstNum = 1;
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = firstNum * lastNum;
        }
        else{
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = firstNum * lastNum;
        }

        textViewResult.setText(myFormatter.format(firstNum));
        dot = true;
    }

    public void Divide() {
        if (firstNum == 0) {
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = lastNum / 1;
        } else {
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = firstNum / lastNum;
        }

        textViewResult.setText(myFormatter.format(firstNum));
        dot = true;
    }
}