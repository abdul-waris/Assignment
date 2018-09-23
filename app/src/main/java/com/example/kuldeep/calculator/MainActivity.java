package com.example.kuldeep.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMain;
    private TextView textViewSecondary;

    private String res;
    private boolean ADD, SUB, MUL, DIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMain = findViewById(R.id.main);
        textViewSecondary = findViewById(R.id.secondary);

        //resets everything
        AC(findViewById(R.id.AC));
    }

    void updateMain(String x) {
        //get the text from MAIN view e.g any given number
        String str = textViewMain.getText().toString();
        if (str.equals("0")) {
            // handling digits
            if (x != "0" && x != "00" && !setOperator(x)) {
                textViewMain.setText(x);
            } else {
                textViewMain.setText("0");
            }
            //handling operators
        } else {
            if ((x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) && (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/"))) {
                textViewMain.setText(removeLastChar(str));
                setOperator(x);
            }
            textViewMain.setText(textViewMain.getText() + x);
        }
    }

    public void AC(View view) {
        textViewSecondary.setText(" ");
        textViewMain.setText("0");
        res = "";
        ADD = false;
        SUB = false;
        MUL = false;
        DIV = false;

    }


    private void resetOperator() {
        ADD = false;
        SUB = false;
        MUL = false;
        DIV = false;
    }

    public boolean setOperator(String x) {
        resetOperator();
        if (x.equals("+"))
            return ADD = true;
        else if (x.equals("-"))
            return SUB = true;
        else if (x.equals("*"))
            return MUL = true;
        else if (x.equals("/"))
            return DIV = true;
        else
            return false;
    }

    public double mainResult(String expression, String delim) {
        expression = expression.replaceAll("\\,", "");
        String[] str = expression.split(delim);
        double res;
        res = Double.parseDouble(str[0]);
        for (int i = 1; i < str.length; i++) {
            if (delim.equals("\\+")) {
                res += Double.parseDouble(str[i]);
            } else if (delim.equals("\\-")) {
                res -= Double.parseDouble(str[i]);
            } else if (delim.equals("\\*")) {
                res *= Double.parseDouble(str[i]);
            } else if (delim.equals("\\/")) {
                res /= Double.parseDouble(str[i]);
            } else {
                return res;
            }
        }
        return res;
    }

    public void onClickEqual(View view) {
        String delim;
        if (ADD) {
            delim = "\\+";
            res = mainResult(textViewMain.getText().toString(), delim) + "";
            ADD = false;
        } else if (SUB) {
            delim = "\\-";
            res = mainResult(textViewMain.getText().toString(), delim) + "";
            SUB = false;
        } else if (MUL) {
            delim = "\\*";
            res = mainResult(textViewMain.getText().toString(), delim) + "";
            MUL = false;
        } else if (DIV) {
            delim = "\\/";
            res = mainResult(textViewMain.getText().toString(), delim) + "";
            DIV = false;
        } else {
            res = textViewMain.getText().toString();
            res = res.replaceAll("\\,", "");
        }

        if (!textViewMain.getText().equals("0")){
            textViewSecondary.setText(textViewMain.getText().toString());
        }


        if (Double.parseDouble(res) == Math.floor(Double.parseDouble(res)))
            textViewMain.setText(res.split("\\.")[0]);
        else
            textViewMain.setText(res);
        res = "";
        resetOperator();
    }

    public void append1(View view) {
        updateMain("1");
    }

    public void append2(View view) {
        updateMain("2");
    }

    public void append3(View view) {
        updateMain("3");
    }

    public void append4(View view) {
        updateMain("4");
    }

    public void append5(View view) {
        updateMain("5");
    }

    public void append6(View view) {
        updateMain("6");
    }

    public void append7(View view) {
        updateMain("7");
    }

    public void append8(View view) {
        updateMain("8");
    }

    public void append9(View view) {
        updateMain("9");
    }

    public void append0(View view) {
        updateMain("0");
    }

    public void append00(View view) {
        updateMain("00");
    }

    public void appendAddition(View view) {
        if (textViewMain.getText().toString().endsWith(","))
            textViewMain.setText(removeLastChar(textViewMain.getText().toString()));
        updateMain("+");
        ADD = true;
    }

    public void appendSubtraction(View view) {
        if (textViewMain.getText().toString().endsWith(","))
            textViewMain.setText(removeLastChar(textViewMain.getText().toString()));
        updateMain("-");
        SUB = true;
    }

    public void appendMultiplication(View view) {
        if (textViewMain.getText().toString().endsWith(","))
            textViewMain.setText(removeLastChar(textViewMain.getText().toString()));
        updateMain("*");
        MUL = true;
    }

    public void appendDivision(View view) {
        if (textViewMain.getText().toString().endsWith(","))
            textViewMain.setText(removeLastChar(textViewMain.getText().toString()));
        updateMain("/");
        DIV = true;
    }

    public void appendPercentage(View view) {

        onClickEqual(findViewById(R.id.result));
        double num = Double.parseDouble(textViewMain.getText().toString());
        double percentage = num / 100;

        String str = textViewMain.getText().toString();

        if(!str.equals("0")) {
            textViewSecondary.setText(textViewMain.getText().toString() + "%");
            textViewMain.setText("" + percentage);
        }
        else {
            textViewSecondary.setText("");

        }
    }

    public void appendComma(View view) {
        String str = textViewMain.getText().toString();
        if (!(str.endsWith(",")) && !(str.endsWith("+")) && !(str.endsWith("-")) && !(str.endsWith("*")) && !(str.endsWith("/")))
            updateMain(",");
    }

    public void removeCharacter(View view) {
        textViewMain.setText(removeLastChar(textViewMain.getText().toString()));
    }

    private String removeLastChar(String str) {
        if (str.length() == 1 || str.equals("0"))
            return "0";
        return str.substring(0, str.length() - 1);
    }
}