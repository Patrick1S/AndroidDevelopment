package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TestTwoActivity extends AppCompatActivity {


    //运算窗口
    private EditText edt_hex;
    private EditText edt_dec;
    private EditText edt_oct;
    private EditText edt_bin;



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

    //字母
    private Button btn_A;
    private Button btn_B;
    private Button btn_C;
    private Button btn_D;
    private Button btn_E;
    private Button btn_F;





    //运算符
    private Button btnClear;
    private Button btnDiv;
    private Button btnMul;
    private Button btnPlus;
    private Button btnSub;
    private Button btndel;
    private Button btnPoint;
    private Button btnEqual;
    private Button btnLeftKh;
    private Button btnRightKh;


    private boolean flag=false;

    private Spinner skipto;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_two);


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


        btn_A =findViewById(R.id.btn_A);
        btn_B =findViewById(R.id.btn_B);
        btn_C =findViewById(R.id.btn_C);
        btn_D =findViewById(R.id.btn_D);
        btn_E =findViewById(R.id.btn_E);
        btn_F =findViewById(R.id.btn_F);

        btnClear = findViewById(R.id.btnClear);
        btnDiv = findViewById(R.id.btnDiv);
        btnPlus = findViewById(R.id.btnPlus);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btndel = findViewById(R.id.btndel);
        btnPoint = findViewById(R.id.btnPoint);
        btnEqual = findViewById(R.id.btnEqual);
        btnLeftKh = findViewById(R.id.btnLeftKh);
        btnRightKh = findViewById(R.id.btnRightKh);

        edt_bin=findViewById(R.id.edt_bin);
        edt_oct=findViewById(R.id.edt_oct);
        edt_dec=findViewById(R.id.edt_dec);
        edt_hex=findViewById(R.id.edt_hex);


        btn0.setEnabled(false);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
        btn5.setEnabled(false);
        btn6.setEnabled(false);
        btn7.setEnabled(false);
        btn8.setEnabled(false);
        btn9.setEnabled(false);
        btn_A.setEnabled(false);
        btn_B.setEnabled(false);
        btn_C.setEnabled(false);
        btn_D.setEnabled(false);
        btn_E.setEnabled(false);
        btn_F.setEnabled(false);


        edt_bin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn0.setEnabled(true);
                btn1.setEnabled(true);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                btn5.setEnabled(false);
                btn6.setEnabled(false);
                btn7.setEnabled(false);
                btn8.setEnabled(false);
                btn9.setEnabled(false);
                btn_A.setEnabled(false);
                btn_B.setEnabled(false);
                btn_C.setEnabled(false);
                btn_D.setEnabled(false);
                btn_E.setEnabled(false);
                btn_F.setEnabled(false);
                return false;
            }
        });

        edt_oct.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn0.setEnabled(true);
                btn0.setEnabled(true);
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(false);
                btn9.setEnabled(false);
                btn_A.setEnabled(false);
                btn_B.setEnabled(false);
                btn_C.setEnabled(false);
                btn_D.setEnabled(false);
                btn_E.setEnabled(false);
                btn_F.setEnabled(false);
                return false;
            }
        });

        edt_dec.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn0.setEnabled(true);
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                btn_A.setEnabled(false);
                btn_B.setEnabled(false);
                btn_C.setEnabled(false);
                btn_D.setEnabled(false);
                btn_E.setEnabled(false);
                btn_F.setEnabled(false);
                return false;
            }
        });

        edt_hex.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                btn0.setEnabled(true);
                btn1.setEnabled(true);
                btn2.setEnabled(true);
                btn3.setEnabled(true);
                btn4.setEnabled(true);
                btn5.setEnabled(true);
                btn6.setEnabled(true);
                btn7.setEnabled(true);
                btn8.setEnabled(true);
                btn9.setEnabled(true);
                btn_A.setEnabled(true);
                btn_B.setEnabled(true);
                btn_C.setEnabled(true);
                btn_D.setEnabled(true);
                btn_E.setEnabled(true);
                btn_F.setEnabled(true);
                return false;
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if (str1==edt_bin.getId()) {
                    edt_bin.setText(edt_bin.getText() + "" + btn0.getText());
                    edt_bin.setSelection(edt_bin.getText().toString().length());
                }else if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn0.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn0.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn0.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if (str1==edt_bin.getId()) {
                    edt_bin.setText(edt_bin.getText() + "" + btn1.getText());
                    edt_bin.setSelection(edt_bin.getText().toString().length());
                }else if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn1.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn1.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn1.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn2.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn2.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn2.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn3.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn3.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn3.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn4.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn4.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn4.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn5.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn5.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn5.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn6.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn6.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn6.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btn7.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btn7.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btn7.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_dec.getId()) {
                    edt_dec.setText(edt_dec.getText() + "" + btn8.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                } else if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn8.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_dec.getId()) {
                    edt_dec.setText(edt_dec.getText() + "" + btn9.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                } else if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn9.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn_A.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn_B.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });

        btn_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn_C.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });


        btn_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn_D.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });



        btn_E.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn_E.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });


        btn_F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1 = getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");
                if (str1 == edt_hex.getId()) {
                    edt_hex.setText(edt_hex.getText() + "" + btn_F.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });


        btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if (str1==edt_bin.getId()) {
                    edt_bin.setText(edt_bin.getText() + "" + btnPoint.getText());
                    edt_bin.setSelection(edt_bin.getText().toString().length());
                }else if(str1==edt_oct.getId()){
                    edt_oct.setText(edt_oct.getText() + "" + btnPoint.getText());
                    edt_oct.setSelection(edt_oct.getText().toString().length());
                }else if(str1==edt_dec.getId()){
                    edt_dec.setText(edt_dec.getText() + "" + btnPoint.getText());
                    edt_dec.setSelection(edt_dec.getText().toString().length());
                }else if(str1==edt_hex.getId()){
                    edt_hex.setText(edt_hex.getText() + "" + btnPoint.getText());
                    edt_hex.setSelection(edt_hex.getText().toString().length());
                }
            }
        });
/*
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

        btnLeftKh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btnLeftKh.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

        btnRightKh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt.setText(edt.getText() + "" + btnRightKh.getText());
                edt.setSelection(edt.getText().toString().length());
            }
        });

*/

        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int keyCode = KeyEvent.KEYCODE_DEL;
                KeyEvent keyEventDown = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
                KeyEvent keyEventUp = new KeyEvent(KeyEvent.ACTION_UP, keyCode);

                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if (str1==edt_bin.getId()) {
                    edt_bin.onKeyDown(keyCode, keyEventDown);
                    edt_bin.onKeyUp(keyCode, keyEventUp);
                }else if(str1==edt_oct.getId()){
                    edt_oct.onKeyDown(keyCode, keyEventDown);
                    edt_oct.onKeyUp(keyCode, keyEventUp);
                }else if(str1==edt_dec.getId()){
                    edt_dec.onKeyDown(keyCode, keyEventDown);
                    edt_dec.onKeyUp(keyCode, keyEventUp);
                }else if(str1==edt_hex.getId()){
                    edt_hex.onKeyDown(keyCode, keyEventDown);
                    edt_hex.onKeyUp(keyCode, keyEventUp);
                }

            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");


                    edt_bin.setText("");
                    edt_oct.setText("");
                    edt_dec.setText("");
                    edt_hex.setText("");


            }
        });


        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int str1=getWindow().getDecorView().findFocus().getId();
                //Log.e("zjx", str);
                //Log.e("zjx", edt_bin.getId()+"");

                if (str1==edt_bin.getId()) {
                    int i10=Integer.valueOf(edt_bin.getText()+"",2);
                    Log.e("zjx", i10+"" );
                    edt_dec.setText(i10+"");

                    edt_oct.setText(Integer.toOctalString(i10)+"");
                    edt_hex.setText(Integer.toHexString(i10)+"");

                }else if(str1==edt_oct.getId()){
                    int i10=Integer.valueOf(edt_oct.getText()+"",8);
                    Log.e("zjx", i10+"" );
                    edt_dec.setText(i10+"");

                    edt_bin.setText(Integer.toBinaryString(i10)+"");
                    edt_hex.setText(Integer.toHexString(i10)+"");

                }else if(str1==edt_dec.getId()){
                    int i10=Integer.valueOf(edt_dec.getText()+"");
                    edt_bin.setText(Integer.toBinaryString(i10)+"");
                    edt_oct.setText(Integer.toOctalString(i10)+"");
                    edt_hex.setText(Integer.toHexString(i10)+"");
                }else if(str1==edt_hex.getId()){
                    int i10=Integer.valueOf(edt_hex.getText()+"",16);
                    Log.e("zjx", i10+"" );
                    edt_dec.setText(i10+"");
                    edt_bin.setText(Integer.toBinaryString(i10)+"");
                    edt_oct.setText(Integer.toOctalString(i10)+"");

                }
            }
        });


        /*btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = edt.getText() + "";
                if (!isExpression(str1)) {
                    edt.setText("输入错误");
                    return;
                } else {
                    if (str1.contains("^")) {
                        String[] spl = str1.split("\\^");

                        Log.e("zjx", spl.length + "");

                        double d1 = Double.valueOf(spl[0]);
                        double d2 = dealString(spl[1]);
                        edt.setText(Math.pow(d1, d2) + "");
                        return;
                    }
                    edt.setText(dealString(str1) + "");
                }
            }
        });

         */

        skipto = findViewById(R.id.skipto);
        skipto.setSelection(2);


        skipto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sp_content = getResources().getStringArray(R.array.CaculatorType);
                System.out.println(sp_content[position]);
                //  Toast.makeText(MainActivity.this,sp_content[position],Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                if ("标准".equals(sp_content[position])) {
                    i.setClass(TestTwoActivity.this, MainActivity.class);
                    startActivity(i);
                } else if ("科学".equals(sp_content[position])) {
                    i.setClass(TestTwoActivity.this, TestOneActivity.class);
                    startActivity(i);
                } else if ("单位转换".equals(sp_content[position])) {
                    i.setClass(TestTwoActivity.this, TestThreeActivity.class);
                    startActivity(i);
                }else if("日期计算".equals(sp_content[position])){
                    i.setClass(TestTwoActivity.this, TestFourActivity.class);
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

            } else if((o1.equals("sin"))
                    &&(o2.equals("*")||o2.equals("/"))){
                return true;}
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



    /*static double getCountResult2(String oper, double nnum1) {
        if (oper.equals("sin")) {
            Log.e("zjx",Math.sin(nnum1)+"" );
            return Math.sin(nnum1);
        } else {
            return 0;
        }
    }

     */
    }




