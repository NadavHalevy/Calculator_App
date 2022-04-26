package com.company.nadavhalevy.calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

// TODO: 4/18/2022 fix: 1. order of operations in exercise 2. dot after equal 3. equal with first number only

public class MainActivity extends AppCompatActivity {

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
            buttonSquareRoot, buttonRoot, buttonSquared, buttonInPower,
            buttonAC, buttonDel, buttonDot, buttonDiv, buttonMulti,
            buttonMinus, buttonPlus, buttonEqual;

    private TextView textViewResult, textViewExercise;

    private String number = null, status = null;

    boolean operator = false;

    double firstNum = 0, lastNum = 0;

    int countDot = 0;

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


        btn0.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                numberClick("0");

        });

        btn1.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                numberClick("1");

        });

        btn2.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                numberClick("2");

        });

        btn3.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                numberClick("3");

        });

        btn4.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                numberClick("4");

        });

        btn5.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                numberClick("5");

        });

        btn6.setOnClickListener(view -> {

            if(!btnEqualsCheck)

                numberClick("6");

        });

        btn7.setOnClickListener(view -> {

            if(!btnEqualsCheck)

                numberClick("7");

        });

        btn8.setOnClickListener(view -> {

            if(!btnEqualsCheck)

               numberClick("8");

        });

        btn9.setOnClickListener(view -> {
        
            if(!btnEqualsCheck)

                numberClick("9");

            } );

        buttonEqual.setOnClickListener(view -> {

            if(!btnEqualsCheck) {
                exercise = textViewExercise.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewExercise.setText(exercise + currentResult);
            }

            if (operator) {

                checkOperator();

            }
            operator = false;
            btnEqualsCheck = true;
        });

        buttonAC.setOnClickListener(view -> {

            number = null;
            status = null;

            textViewResult.setText("0");
            textViewExercise.setText("");
            firstNum = 0;
            lastNum = 0;
            countDot = 0;
            btnACCheck = true;
            btnEqualsCheck = false;


        });

        buttonDel.setOnClickListener(view -> {

            if (btnACCheck){

                textViewResult.setText("0");

            }else{

                number = number.substring(0, number.length()-1);

                if(number.length() == 0){

                    buttonDel.setClickable(false);

                }else if(number.contains(".")){

                    countDot = 1;

                }else{

                    countDot = 0;

                }
            }

            textViewResult.setText(number);

        });

        buttonDot.setOnClickListener(view -> {


            if(countDot == 0) {
                if (number == null) {
                    number = "0.";
                } else {
                    number += ".";
                }
                countDot++;
            }
            textViewResult.setText(number);
        });

        buttonPlus.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("+");
            else
                afterEqual("+");

            if (operator) {

                status = "sum";
                checkOperator();

            }

            operator = false;
            number = null;

        });

        buttonMinus.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("-");
            else
                afterEqual("-");

            if (operator) {

                status = "sub";
                checkOperator();

            }

            operator = false;
            number = null;

        });

        buttonMulti.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("*");
            else
                afterEqual("*");

            if (operator) {

                status = "multi";
                checkOperator();

            }

            operator = false;
            number = null;

        });

        buttonDiv.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("/");
            else
                afterEqual("/");


            if (operator) {

                status = "div";
                checkOperator();

            }

            operator = false;
            number = null;

        });

        buttonSquared.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("^2");
            else
                afterEqual("^2");

            if (operator) {

                status = "square";
                checkOperator();

            }

            operator = false;
            number = null;

        });

        buttonInPower.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("^");
            else
                afterEqual("^");

            if (operator) {

                status = "power";
                checkOperator();

            }

            operator = false;
            number = null;
        });

        buttonSquareRoot.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("2√");
            else
                afterEqual("2√");


            if (operator) {

                status = "squareRoot";
                checkOperator();

            }

            operator = false;
            number = null;
        });

        buttonRoot.setOnClickListener(view -> {

            if(!btnEqualsCheck)
                beforeEqual("√");
            else
                afterEqual("√");

            if (operator) {

                status = "root";
                checkOperator();

            }

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
        showResult();

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
        countDot = 0;

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