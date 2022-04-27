package com.company.nadavhalevy.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            buttonSquareRoot, buttonRoot, buttonSquared, buttonInPower,
            buttonAC, buttonDel, buttonDot, buttonDiv, buttonMulti,
            buttonMinus, buttonPlus, buttonEqual;

    private TextView textViewResult, textViewExercise;

    private String number = null, status = null;

    boolean operator = false;

    double firstNum = 0, lastNum = 0;

    boolean checkDot = true;

    int countNumClick = 0;

    DecimalFormat mf = new DecimalFormat("#####.#####");

    String exercise, currentResult;

    boolean btnACCheck = true, btnEqualsCheck = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text view:
        textViewResult = findViewById(R.id.textViewResult);
        textViewExercise = findViewById(R.id.textViewExercise);

        // Actions button
        buttonEqual = findViewById(R.id.buttonEqual);
        buttonAC = findViewById(R.id.buttonAC);
        buttonDel = findViewById(R.id.buttonDel);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonSquareRoot = findViewById(R.id.buttonSquareRoot);
        buttonRoot = findViewById(R.id.buttonRoot);
        buttonDot = findViewById(R.id.buttonPoint);
        buttonSquared = findViewById(R.id.buttonSquared);
        buttonInPower = findViewById(R.id.buttonInPower);

        // Numbers button
        btn0 = findViewById(R.id.button0);
        btn1 = findViewById(R.id.button1);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);
        btn5 = findViewById(R.id.button5);
        btn6 = findViewById(R.id.button6);
        btn7 = findViewById(R.id.button7);
        btn8 = findViewById(R.id.button8);
        btn9 = findViewById(R.id.button9);

        buttonEqual.setClickable(false);

        btn0.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("0");
                countNumClick++;
            }
        });

        btn1.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("1");
                countNumClick++;
            }

        });

        btn2.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("2");
                countNumClick++;
            }
        });

        btn3.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("3");
                countNumClick++;
            }

        });

        btn4.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("4");
                countNumClick++;
            }

        });

        btn5.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("5");
                countNumClick++;
            }

        });

        btn6.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("6");
                countNumClick++;
            }

        });

        btn7.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("7");
                countNumClick++;
            }

        });

        btn8.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("8");
                countNumClick++;
            }

        });

        btn9.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                numberClick("9");
                countNumClick++;
            }

            } );

        buttonEqual.setOnClickListener(view -> {
            if(countNumClick > 1)
            {
                if (!btnEqualsCheck) {
                    exercise = textViewExercise.getText().toString();
                    currentResult = textViewResult.getText().toString();
                    textViewExercise.setText(exercise + currentResult);
                }
                if (operator) {

                    checkOperator();

                }

                operator = false;
                btnEqualsCheck = true;
                countNumClick = 1;
                buttonEqual.setClickable(false);
            }
            else
            {
                buttonAC.post(() -> buttonAC.performClick());
                Toast.makeText(getApplicationContext(), "You clicked equal before the time." +   "\n"
                        + "Please start from the beginning", Toast.LENGTH_LONG).show();
            }
        });


        buttonAC.setOnClickListener(view -> {

            number = null;
            status = null;

            textViewResult.setText("0");
            textViewExercise.setText("");
            firstNum = 0;
            lastNum = 0;
            countNumClick = 0;
            checkDot = true;
            btnACCheck = true;
            btnEqualsCheck = false;


        });

        buttonDel.setOnClickListener(view -> {

            if (btnACCheck){

                textViewResult.setText("0");

            }else {

                number = number.substring(0, number.length() - 1);

                if (number.length() == 0) {

                    buttonDel.setClickable(false);

               }else if(number.contains(".")){

                    checkDot = false;

                }else{

                    checkDot = true;


                }
            }
            textViewResult.setText(number);

        });

        buttonDot.setOnClickListener(view -> {

            if (!btnEqualsCheck)
            {
                if (countNumClick > 0 )
                {
                    if (!number.contains(".") || number.isEmpty()) {
                        if (number == null) {
                            number = "0.";
                        } else {
                            number += ".";
                        }
                        textViewResult.setText(number);
                        checkDot = false;
                    }
                }
                else
                {
                    number = "0.";
                    textViewResult.setText(number);
                    checkDot = false;

                }
            }
            else
            {
                buttonAC.post(() -> buttonAC.performClick());
                Toast.makeText(getApplicationContext(), "You can not click on a dot after an equal." +   "\n"
                        + "Please start from the beginning", Toast.LENGTH_LONG).show();
            }
        });

        buttonPlus.setOnClickListener(view -> {

            statusEqual("+");

            if (operator) {

                status = "sum";
                checkOperator();

            }

            status = "sum";
            operator = false;
            number = null;

        });

        buttonMinus.setOnClickListener(view -> {

            statusEqual("-");

            if (operator) {

                status = "sub";
                checkOperator();

            }

            status = "sub";
            operator = false;
            number = null;

        });

        buttonMulti.setOnClickListener(view -> {

            statusEqual("*");

            if (operator) {

                status = "multi";
                checkOperator();

            }

            status = "multi";
            operator = false;
            number = null;

        });

        buttonDiv.setOnClickListener(view -> {

            statusEqual("/");

            if (operator) {

                status = "div";
                checkOperator();

            }

            status = "div";
            operator = false;
            number = null;

        });

        buttonSquared.setOnClickListener(view -> {
            if (!btnEqualsCheck) {

                statusEqual("^2");

                if (operator) {

                    status = "square";
                    checkOperator();

                }

                status = "square";
                operator = false;
                number = null;

            }else
            {
                Toast.makeText(getApplicationContext(), "You can not click on a square after an equal", Toast.LENGTH_LONG).show();

            }

        });

        buttonInPower.setOnClickListener(view -> {

            statusEqual("^");

            if (operator) {

                status = "power";
                checkOperator();

            }

            status = "power";
            operator = false;
            number = null;
        });

        buttonSquareRoot.setOnClickListener(view -> {
            if (!btnEqualsCheck) {

                statusEqual("2√");


                if (operator) {

                    status = "squareRoot";
                    checkOperator();

                }

                status = "squareRoot";
                operator = false;
                number = null;

            }else
            {
                Toast.makeText(getApplicationContext(), "You can not click on a square root after an equal", Toast.LENGTH_LONG).show();

            }

        });

        buttonRoot.setOnClickListener(view -> {

            statusEqual("√");

            if (operator) {

                status = "root";
                checkOperator();

            }

            status = "root";
            operator = false;
            number = null;
        });

    }

    public void numberClick(String view) {

        if (number == null) {

            number = view;

        }else if(btnEqualsCheck){

            firstNum = 0;
            lastNum = 0;
            number = view;

        }else {

            number = number + view;

        }

        textViewResult.setText(number);
        operator = true;
        btnACCheck = false;
        buttonDel.setClickable(true);
        btnEqualsCheck = false;

    }

    public void plus(){

        lastNum = Double.parseDouble(textViewResult.getText().toString());
        firstNum = firstNum + lastNum;
        showResult();


    }

    public void minus(){

       if (firstNum == 0){
           firstNum = Double.parseDouble(textViewResult.getText().toString());
       }else {
           lastNum = Double.parseDouble(textViewResult.getText().toString());
           firstNum = firstNum - lastNum;
       }
        showResult();

    }

    public void multi(){

        if ( firstNum == 0){
            firstNum = 1;
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = firstNum * lastNum;
        }else {
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = firstNum * lastNum;
        }
        showResult();

    }

    public void div(){

        if (firstNum == 0){
            firstNum = 1;
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = lastNum / firstNum;
        }else {
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            firstNum = firstNum / lastNum;
        }
        if(lastNum == 0.0){
            buttonAC.post(new Runnable(){
                @Override
                public void run() {
                    buttonAC.performClick();
                }
            });
            Toast.makeText(getApplicationContext(), "It is not possible to divide a number by 0", Toast.LENGTH_LONG).show();
        }else{
            showResult();
        }
    }

    public void square(){

        lastNum = Double.parseDouble(textViewResult.getText().toString());
        firstNum = Math.pow(lastNum, 2);
        showResult();

    }

    public void power(){

        if (firstNum == 0 ){
            firstNum = Double.parseDouble(textViewResult.getText().toString());
        } else {
        lastNum = Double.parseDouble(textViewResult.getText().toString());
        firstNum = Math.pow(firstNum, lastNum);
    }
        showResult();
    }

    public void squareRoot(){

        lastNum = Double.parseDouble(textViewResult.getText().toString());
        firstNum = Math.pow(lastNum, 0.5);
        showResult();

    }

    public void root(){


        if (firstNum == 0 ){
            firstNum = Double.parseDouble(textViewResult.getText().toString());
        }else {
            lastNum = Double.parseDouble(textViewResult.getText().toString());
            lastNum = (1 / lastNum);
            firstNum = Math.pow(firstNum, lastNum);

        }

        showResult();

    }

    public void checkOperator(){

        switch (status){
            case "multi":
                multi();
                break;
            case "div":
                div();
                break;
            case "sub":
                minus();
                break;
            case "sum":
                plus();
                break;
            case "square":
                square();
                break;
            case "power":
                power();
                break;
            case "squareRoot":
                squareRoot();
                break;
            case "root":
                root();
                break;
            default:
                firstNum = Double.parseDouble(textViewResult.getText().toString());
                break;
        }

    }

    public void showResult() {

        textViewResult.setText(mf.format(firstNum));
        lastNum = firstNum;
        checkDot = true;

    }

    public void statusEqual(String ope){

        buttonEqual.setClickable(true);
        if(!btnEqualsCheck)
            beforeEqual(ope);
        else
            afterEqual(ope);
    }

    public void beforeEqual(String ope){

            exercise = textViewExercise.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewExercise.setText(exercise + currentResult + ope);

    }

    public void afterEqual(String ope){

        btnEqualsCheck = false;
        exercise = textViewExercise.getText().toString();
        currentResult = textViewResult.getText().toString();
        textViewExercise.setText(exercise + ope);

    }
}