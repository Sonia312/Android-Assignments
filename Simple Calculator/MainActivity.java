package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;


// Code for infix evaluation taken from geeks for geeks
 class EvaluateString
{
    public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();

        // Stack for numbers: 'values'
        Stack<Integer> values = new
                Stack<Integer>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new
                Stack<Character>();

        for (int i = 0; i < tokens.length; i++)
        {

            // Current token is a
            // whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number,
            // push it to stack for numbers
            if (tokens[i] >= '0' &&
                    tokens[i] <= '9')
            {
                StringBuffer sbuf = new
                        StringBuffer();

                // There may be more than one
                // digits in number
                while (i < tokens.length &&
                        tokens[i] >= '0' &&
                        tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.
                        toString()));

                // right now the i points to
                // the character next to the digit,
                // since the for loop also increases
                // the i, we would skip one
                //  token position; we need to
                // decrease the value of i by 1 to
                // correct the offset.
                i--;
            }

            // Current token is an opening brace,
            // push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

                // Closing brace encountered,
                // solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/')
            {
                // While top of 'ops' has same
                // or greater precedence to current
                // token, which is an operator.
                // Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() &&
                        hasPrecedence(tokens[i],
                                ops.peek()))
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been
        // parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(),
                    values.pop(),
                    values.pop()));

        // Top of 'values' contains
        // result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher
    // or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(
            char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an
    // operator 'op' on operands 'a'
    // and 'b'. Return the result.
    public static int applyOp(char op,
                              int b, int a)
    {
        switch (op)
        {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
        }
        return 0;
    }

    // Driver method to test above methods
}

public class MainActivity extends AppCompatActivity {

    TextView equation, answer;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bp,bs,bm,bd,be,bc,br1,br2,bckspc;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        equation = (TextView) findViewById(R.id.textView);
        answer = (TextView) findViewById(R.id.textView2);
        b0 = (Button) findViewById(R.id.button11);
        b1 = (Button) findViewById(R.id.button2);
        b2 = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);
        b4 = (Button) findViewById(R.id.button6);
        b5 = (Button) findViewById(R.id.button9);
        b6 = (Button) findViewById(R.id.button12);
        b7 = (Button) findViewById(R.id.button7);
        b8 = (Button) findViewById(R.id.button10);
        b9 = (Button) findViewById(R.id.button13);
        bp = (Button) findViewById(R.id.button5);
        bs = (Button) findViewById(R.id.button15);
        bm = (Button) findViewById(R.id.button16);
        bd = (Button) findViewById(R.id.button17);
        be = (Button) findViewById(R.id.button20);
        bc = (Button) findViewById(R.id.button19);
        br1 = (Button) findViewById(R.id.button8);
        br2 = (Button) findViewById(R.id.button14);
        bckspc = (Button) findViewById(R.id.button);

        b0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "0";
                equation.setText(s);
            }
        });

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "1";
                equation.setText(s);
            }
        });

        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "2";
                equation.setText(s);
            }
        });

        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "3";
                equation.setText(s);
            }
        });

        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "4";
                equation.setText(s);
            }
        });

        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "5";
                equation.setText(s);
            }
        });

        b6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "6";
                equation.setText(s);
            }
        });

        b7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "7";
                equation.setText(s);
            }
        });

        b8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "8";
                equation.setText(s);
            }
        });

        b9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "9";
                equation.setText(s);
            }
        });

        bp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "+";
                equation.setText(s);
            }
        });

        bs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "-";
                equation.setText(s);
            }
        });

        bm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "*";
                equation.setText(s);
            }
        });

        bd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "/";
                equation.setText(s);
            }
        });

        br1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += "(";
                equation.setText(s);
            }
        });

        br2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();
                s += ")";
                equation.setText(s);
            }
        });

        be.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();

                try{
                    int ans = EvaluateString.evaluate(s);
                    answer.setText(Integer.toString(ans));
                }catch (Exception e){
                    answer.setText("Error");
                }
            }
        });

        bckspc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String s = equation.getText().toString();

                if(!s.equals("")) s = s.substring(0, s.length() - 1);

                equation.setText(s);
            }
        });

        bc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                equation.setText("");
                answer.setText("");
            }
        });
    }
}