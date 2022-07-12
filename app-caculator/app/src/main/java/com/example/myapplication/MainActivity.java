
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    boolean clear_flag;

    //运算窗口
    private EditText edt;

    //数字
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;

    //运算符
    private Button btnClear;
    private Button btnDiv;
    private Button btnMul;
    private Button btndel;
    private Button btnPlus;
    private Button btnSub;
    private Button btnPoint;
    private Button btnEqual;

    private Spinner skipto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);


        btnClear = findViewById(R.id.btnClear);
        btnDiv = findViewById(R.id.btnDiv);
        btndel = findViewById(R.id.btnDel);
        btnPlus = findViewById(R.id.btnPlus);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);

        btnPoint = findViewById(R.id.btnPoint);
        btnEqual = findViewById(R.id.btnEqual);
        edt = findViewById(R.id.tv_edt);

        skipto=findViewById(R.id.skipto);
        skipto.setSelection(0);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn0.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn1.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn2.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn3.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn4.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn5.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn6.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn7.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn8.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btn9.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btnPoint.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btnPlus.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btnSub.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btnMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btnMul.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btnDiv.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });



        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  keyCode = KeyEvent.KEYCODE_DEL;
                KeyEvent keyEventDown = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
                KeyEvent keyEventUp = new KeyEvent(KeyEvent.ACTION_UP, keyCode);
                edt.onKeyDown(keyCode, keyEventDown);
                edt.onKeyUp(keyCode, keyEventUp);
            }
        });




        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText("");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = edt.getText()+"";
                if(!isExpression(str1)){
                    edt.setText("输入错误");
                    return;
                }else {
                    if(str1.contains("^")){
                        String[] spl=str1.split("\\^");

                        Log.e("zjx", spl.length+"");

                        double d1=Double.valueOf(spl[0]);
                        double d2=dealString(spl[1]);
                        edt.setText(Math.pow(d1,d2)+"");
                        return;
                    }
                    edt.setText(dealString(str1) + "");
                }
            }
        });



        skipto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sp_content = getResources().getStringArray(R.array.CaculatorType);
                System.out.println(sp_content[position]);
                Intent i = new Intent();
                //Toast.makeText(MainActivity.this,sp_content[position],Toast.LENGTH_SHORT).show();
                if ("科学".equals(sp_content[position])) {
                    i.setClass(MainActivity.this, TestOneActivity.class);
                    startActivity(i);
                }
                else if("进制转换".equals(sp_content[position])){
                    i.setClass(MainActivity.this, TestTwoActivity.class);
                    startActivity(i);
                }
                else if("单位转换".equals(sp_content[position])){
                    i.setClass(MainActivity.this, TestThreeActivity.class);
                    startActivity(i);
                }else if("日期计算".equals(sp_content[position])){
                    i.setClass(MainActivity.this, TestFourActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    /**
     * 是否为算术表达式
     *
     * @param str
     * @return
     */
    private static boolean isExpression(String str) {
        int flag = 0;
        for (int i = 0; i < str.length() - 1; i++) {
            char ch = str.charAt(i);
            char chb = str.charAt(i + 1);
            if ((!isNum(ch) && i == 0) && ch != '(' || !isNum(chb)
                    && (i == str.length() - 2) && chb != ')') {
                System.out.println("首尾不是数字---->" + ch + chb);
                return false;
            }
            if ((ch == '.' && !isNum(chb)) || (!isNum(ch) && chb == '.')) {
                System.out.println("小数点前后不是数字--->" + ch + chb);
                return false;
            }
            if (isOperator(ch) && !isNum(chb) && chb != '(') {
                System.out.println("运算符不是数字--->" + ch + chb);
                return false;
            }
            if (isNum(ch) && !isOperator(chb) && chb != '.' && chb != ')'
                    && !isNum(chb)&&chb!='^') {
                System.out.println("数字后不是运算符--->" + ch + chb);
                return false;
            }
            if (ch == '(') {
                flag++;
            }
            if (chb == ')') {
                flag--;
            }
        }
        if (flag != 0) {
            System.out.println("括号不匹配--->");
            return false;
        }
        return true;
    }


    /**
     * 分解表达式
     *
     * @param str
     * @return
     */
    private static List<String> resolveString(String str) {
        List<String> list = new ArrayList<String>();
        String temp1 = "",temp2="";
        for (int i = 0; i < str.length(); i++) {
            final char ch = str.charAt(i);
            if (isNum(ch) || ch == '.') {
                char c = str.charAt(i);
                temp1 += c;
            } else if (isOperator(ch) || ch == ')') {
                if (!temp1.equals("")) {
                    list.add(temp1);
                }
                list.add("" + ch);
                temp1 = "";

            } else if (ch == '(') {
                list.add(temp2);
                list.add("" + ch);
                temp2="";

            }else if(isLetter(ch)){
                char c = str.charAt(i);
                temp2 += c;
            }
            if (i == str.length() - 1) {
                list.add(temp1);
            }
        }
        return list;
    }


    /**
     * 中缀表达式转换后缀
     *
     * @param list
     * @return
     */
    private static List<String> nifix_to_post(List<String> list) {
        Stack<String> stack = new Stack<String>();
        List<String> plist = new ArrayList<String>();
        for (String str : list) {
            if (isDouble(str)) {
                plist.add(str);
            }
            if (isStrOperator(str) && stack.isEmpty()) {
                stack.push(str);
            } else if (isStrOperator(str) && !stack.isEmpty()) {
                String last = stack.lastElement();
                if (heightOperator(str, last) || str.equals("(")) {
                    stack.push(str);
                } else if (!heightOperator(str, last) && !str.equals(")")) {
                    while (!stack.isEmpty() && !stack.lastElement().equals("(")) {
                        plist.add(stack.pop());
                    }
                    stack.push(str);
                } else if (str.equals(")")) {
                    while (!stack.isEmpty()) {
                        String pop = stack.pop();
                        if (!pop.equals("(")) {
                            plist.add(pop);
                        }
                        if (pop.equals("(")) {
                            break;
                        }
                    }
                }
            }
        }
        while (!stack.isEmpty()) {
            plist.add(stack.pop());
        }

        return plist;
    }


    /**
     * 字符是否为数字
     *
     * @param ch
     * @return
     */
    private static boolean isNum(char ch) {
        if (ch <= '9' && ch >= '0') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串是否为Double类型
     *
     * @param s
     * @return
     */
    private static boolean isDouble(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 字符是否为运输符
     *
     * @param ch
     * @return
     */
    private static boolean isOperator(char ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '=') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 字符串是否为运算符
     *
     * @param s
     * @return
     */
    private static boolean isStrOperator(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")||s.equals("sin")
                || s.equals("(") || s.equals(")")) {
            return true;
        } else {
            return false;
        }
    }


    private static boolean isLetter(char ch) {
        if (ch >='a'&& ch<='z') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较运算符优先级
     *
     * @param o1
     * @param o2
     * @return
     */
    private static boolean heightOperator(String o1, String o2) {
        if ((o1.equals("*") || o1.equals("/"))
                && (o2.equals("+") || o2.equals("-")) || o2.equals("(")) {
            return true;
        } else if ((o1.equals("+") || o1.equals("-"))
                && (o2.equals("*") || o2.equals("/"))) {
            return false;
        } else if ((o1.equals("*") || o1.equals("/"))
                && ((o2.equals("*") || o2.equals("/")))) {
            return true;
        } else if ((o1.equals("+") || o1.equals("-"))
                && (o2.equals("+") || o2.equals("-"))) {
            return true;

        }
        else {
            return false;
        }
    }

    /**
     * 计算后缀表达式
     *
     * @param list
     * @return
     */
    private static double get_postfis_result(List<String> list) {
        Stack<String> stack = new Stack<String>();
        for (String str : list) {
            if (isDouble(str)) {
                stack.push(str);
            } else if (isStrOperator(str)) {/*
                if(str.equals("sin")){
                double nn1=Double.valueOf(stack.pop());
                    Log.e("zjx",getCountResult2(str, nn1)+"" );
                stack.push("" + getCountResult2(str, nn1));


            } else{*/ String s2=stack.pop();
                String s1=stack.pop();
                double n2 = Double.valueOf(s2);
                double n1 = Double.valueOf(s1);
                Log.e("zjx",getCountResult(str, n1, n2)+"" );
                Log.e("zjx",Math.sin(getCountResult(str, n1, n2))+"" );
                stack.push("" + getCountResult(str, n1, n2));
            }

        }
        return Double.valueOf(stack.pop());
    }



    /*
两数算术运算
*/
    static double getCountResult(String oper, double num1, double num2) {

        if ("+".equals(oper))
        {
            return num1 + num2;
        } else if ("-".equals(oper))
        {
            return num1 - num2;
        } else if ("*".equals(oper))
        {
            return num1 * num2;
        } else if ("/".equals(oper))
        {
            return num1 / num2;
        }
        else
        {
            return 0;
        }

    }

    private Double dealString(String str){

        List<String> list = resolveString(str);
        System.out.println(list);
        list = nifix_to_post(list);
        System.out.println(list);
        double result = get_postfis_result(list);
        return result;
    }



}


