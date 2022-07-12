package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class
TestThreeActivity extends AppCompatActivity {

    private EditText edt_len1;
    private EditText edt_len2;
    private Spinner spin_len1;
    private Spinner spin_len2;


    private EditText edt_vol1;
    private EditText edt_vol2;
    private Spinner spin_vol1;
    private Spinner spin_vol2;

    private EditText edt_wht1;
    private EditText edt_wht2;
    private Spinner spin_wht1;
    private Spinner spin_wht2;

    private EditText edt_hb1;
    private EditText edt_hb2;
    private Spinner spin_hb1;
    private Spinner spin_hb2;

    private Spinner skipto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_three);

        edt_len1=findViewById(R.id.edt_len1);
        edt_len2=findViewById(R.id.edt_len2);
        spin_len1=findViewById(R.id.spin_len1);
        spin_len2=findViewById(R.id.spin_len2);

        edt_vol1=findViewById(R.id.edt_vol1);
        edt_vol2=findViewById(R.id.edt_vol2);
        spin_vol1=findViewById(R.id.spin_vol1);
        spin_vol2=findViewById(R.id.spin_vol2);

        edt_wht1=findViewById(R.id.edt_wht1);
        edt_wht2=findViewById(R.id.edt_wht2);
        spin_wht1=findViewById(R.id.spin_wht1);
        spin_wht2=findViewById(R.id.spin_wht2);

        edt_hb1=findViewById(R.id.edt_hb1);
        edt_hb2=findViewById(R.id.edt_hb2);
        spin_hb1=findViewById(R.id.spin_hb1);
        spin_hb2=findViewById(R.id.spin_hb2);


        edt_len1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(spin_len1.getSelectedItem().equals("毫米")&&spin_len2.getSelectedItem().equals("毫米")){
                    edt_len2.setText(edt_len1.getText()+"");

                }else if(spin_len1.getSelectedItem().equals("毫米")&&spin_len2.getSelectedItem().equals("厘米")){
                    Double d1=Double.valueOf(edt_len1.getText()+"")/10;
                    edt_len2.setText(d1+"");
                }else if(spin_len1.getSelectedItem().equals("毫米")&&spin_len2.getSelectedItem().equals("分米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 100;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("毫米")&&spin_len2.getSelectedItem().equals("米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 1000;
                    edt_len2.setText(d1 + "");
                } else if(spin_len1.getSelectedItem().equals("毫米")&&spin_len2.getSelectedItem().equals("千米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 1000000;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("厘米")&&spin_len2.getSelectedItem().equals("毫米")){
                    Double d1=Double.valueOf(edt_len1.getText()+"")*10;
                    edt_len2.setText(d1+"");
                }else if(spin_len1.getSelectedItem().equals("厘米")&&spin_len2.getSelectedItem().equals("厘米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") ;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("厘米")&&spin_len2.getSelectedItem().equals("分米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 10;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("厘米")&&spin_len2.getSelectedItem().equals("米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 100;
                    edt_len2.setText(d1 + "");
                } else if(spin_len1.getSelectedItem().equals("厘米")&&spin_len2.getSelectedItem().equals("千米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 100000;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("分米")&&spin_len2.getSelectedItem().equals("毫米")){
                    Double d1=Double.valueOf(edt_len1.getText()+"")*100;
                    edt_len2.setText(d1+"");
                }else if(spin_len1.getSelectedItem().equals("分米")&&spin_len2.getSelectedItem().equals("厘米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") *10;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("分米")&&spin_len2.getSelectedItem().equals("分米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") ;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("分米")&&spin_len2.getSelectedItem().equals("米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 10;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("分米")&&spin_len2.getSelectedItem().equals("千米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 10000;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("米")&&spin_len2.getSelectedItem().equals("毫米")){
                    Double d1=Double.valueOf(edt_len1.getText()+"")*1000;
                    edt_len2.setText(d1+"");
                }else if(spin_len1.getSelectedItem().equals("米")&&spin_len2.getSelectedItem().equals("厘米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") *100;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("米")&&spin_len2.getSelectedItem().equals("分米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") *10;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("米")&&spin_len2.getSelectedItem().equals("米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") ;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("米")&&spin_len2.getSelectedItem().equals("千米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") / 1000;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("千米")&&spin_len2.getSelectedItem().equals("毫米")){
                    Double d1=Double.valueOf(edt_len1.getText()+"")*1000000;
                    edt_len2.setText(d1+"");
                }else if(spin_len1.getSelectedItem().equals("千米")&&spin_len2.getSelectedItem().equals("厘米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") *100000;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("千米")&&spin_len2.getSelectedItem().equals("分米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") * 10000;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("千米")&&spin_len2.getSelectedItem().equals("米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") * 1000;
                    edt_len2.setText(d1 + "");
                }else if(spin_len1.getSelectedItem().equals("千米")&&spin_len2.getSelectedItem().equals("千米")) {
                    Double d1 = Double.valueOf(edt_len1.getText() + "") ;
                    edt_len2.setText(d1 + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edt_vol1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(spin_vol1.getSelectedItem().equals("毫升")&&spin_vol2.getSelectedItem().equals("毫升")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"");
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("毫升")&&spin_vol2.getSelectedItem().equals("立方厘米")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"");
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("毫升")&&spin_vol2.getSelectedItem().equals("升")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "") / 1000;
                    edt_vol2.setText(d1 + "");
                }else if(spin_vol1.getSelectedItem().equals("毫升")&&spin_vol2.getSelectedItem().equals("立方米")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "") / 1000000;
                    edt_vol2.setText(d1 + "");
                }else if(spin_vol1.getSelectedItem().equals("立方厘米")&&spin_vol2.getSelectedItem().equals("毫升")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"");
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("立方厘米")&&spin_vol2.getSelectedItem().equals("立方厘米")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"");
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("立方厘米")&&spin_vol2.getSelectedItem().equals("升")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "") / 1000;
                    edt_vol2.setText(d1 + "");
                }else if(spin_vol1.getSelectedItem().equals("立方厘米")&&spin_vol2.getSelectedItem().equals("立方米")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "") / 1000000;
                    edt_vol2.setText(d1 + "");
                }else if(spin_vol1.getSelectedItem().equals("升")&&spin_vol2.getSelectedItem().equals("毫升")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"")*1000;
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("升")&&spin_vol2.getSelectedItem().equals("立方厘米")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"")*1000;
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("升")&&spin_vol2.getSelectedItem().equals("升")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "");
                    edt_vol2.setText(d1 + "");
                }else if(spin_vol1.getSelectedItem().equals("升")&&spin_vol2.getSelectedItem().equals("立方米")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "") / 1000;
                    edt_vol2.setText(d1 + "");
                }else if(spin_vol1.getSelectedItem().equals("立方米")&&spin_vol2.getSelectedItem().equals("毫升")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"")*1000000;
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("立方米")&&spin_vol2.getSelectedItem().equals("立方厘米")){
                    Double d1=Double.valueOf(edt_vol1.getText()+"")/1000000;
                    edt_vol2.setText(d1+"");
                }else if(spin_vol1.getSelectedItem().equals("立方米")&&spin_vol2.getSelectedItem().equals("升")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "") / 1000;
                    edt_vol2.setText(d1 + "");
                }else if(spin_vol1.getSelectedItem().equals("立方米")&&spin_vol2.getSelectedItem().equals("立方米")) {
                    Double d1 = Double.valueOf(edt_vol1.getText() + "");
                    edt_vol2.setText(d1 + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edt_wht1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(spin_wht1.getSelectedItem().equals("毫克")&&spin_wht2.getSelectedItem().equals("毫克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"");
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("毫克")&&spin_wht2.getSelectedItem().equals("克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"")/1000;
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("毫克")&&spin_wht2.getSelectedItem().equals("千克")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") / 1000000;
                    edt_wht2.setText(d1 + "");
                }else if(spin_wht1.getSelectedItem().equals("毫克")&&spin_wht2.getSelectedItem().equals("吨")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") / 1000000000;
                    edt_wht2.setText(d1 + "");
                }else if(spin_wht1.getSelectedItem().equals("克")&&spin_wht2.getSelectedItem().equals("毫克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"")*1000;
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("克")&&spin_wht2.getSelectedItem().equals("克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"");
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("克")&&spin_wht2.getSelectedItem().equals("千克")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") / 1000;
                    edt_wht2.setText(d1 + "");
                }else if(spin_wht1.getSelectedItem().equals("克")&&spin_wht2.getSelectedItem().equals("吨")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") / 1000000;
                    edt_wht2.setText(d1 + "");
                }else if(spin_wht1.getSelectedItem().equals("千克")&&spin_wht2.getSelectedItem().equals("毫克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"")*1000000;
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("千克")&&spin_wht2.getSelectedItem().equals("克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"")*1000;
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("千克")&&spin_wht2.getSelectedItem().equals("千克")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") ;
                    edt_wht2.setText(d1 + "");
                }else if(spin_wht1.getSelectedItem().equals("千克")&&spin_wht2.getSelectedItem().equals("吨")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") / 1000;
                    edt_wht2.setText(d1 + "");
                }else if(spin_wht1.getSelectedItem().equals("吨")&&spin_wht2.getSelectedItem().equals("毫克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"")*1000000000;
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("吨")&&spin_wht2.getSelectedItem().equals("克")){
                    Double d1=Double.valueOf(edt_wht1.getText()+"")*1000000;
                    edt_wht2.setText(d1+"");
                }else if(spin_wht1.getSelectedItem().equals("吨")&&spin_wht2.getSelectedItem().equals("千克")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") * 1000;
                    edt_wht2.setText(d1 + "");
                }else if(spin_wht1.getSelectedItem().equals("吨")&&spin_wht2.getSelectedItem().equals("吨")) {
                    Double d1 = Double.valueOf(edt_wht1.getText() + "") ;
                    edt_wht2.setText(d1 + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        edt_hb1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"");
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*1.2076;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.1552;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.1315;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.1124;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *17.01433;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *181.653;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.2118;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("人民币")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.1968;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.8281;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"");
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.1286;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.1089;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.0931;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *14.0897;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *150.4283;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.1754;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("港元")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.163;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*6.4416;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*7.7787;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"");
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.8472;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.7242;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *109.5995;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1170.1362;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.3646;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("美元")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.2676;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*7.6037;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*9.182;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*1.1804;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") ;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.8549;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *129.3712;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1381.2289;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.6108;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("欧元")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.4963;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*8.8946;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*10.7408;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*1.3808;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.1698;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") ;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *151.335;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1615.7241;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.8843;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("英镑")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.7503;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.05877;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.07097;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.009124;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.00773;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.006608;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") ;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *10.6765;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.01245;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("日元")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.01157;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.005505;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.006648;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.0008546;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.000724;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.0006189;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.09366;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") ;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.001166;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("韩元")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.001083;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*4.7204;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*5.7002;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.7328;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.6208;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.5307;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *80.3145;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *857.4757;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") ;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("澳元")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.9289;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("人民币")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*5.08173;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("港元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*6.1366;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("美元")){
                    Double d1=Double.valueOf(edt_hb1.getText()+"")*0.7889;
                    edt_hb2.setText(d1+"");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("欧元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.6683;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("英镑")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *0.5713;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("日元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *86.4622;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("韩元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *923.1115;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("澳元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") *1.07655;
                    edt_hb2.setText(d1 + "");
                }else if(spin_hb1.getSelectedItem().equals("加元")&&spin_hb2.getSelectedItem().equals("加元")) {
                    Double d1 = Double.valueOf(edt_hb1.getText() + "") ;
                    edt_hb2.setText(d1 + "");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        skipto = findViewById(R.id.skipto);
        skipto.setSelection(3);

        skipto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sp_content = getResources().getStringArray(R.array.CaculatorType);
                System.out.println(sp_content[position]);
                //  Toast.makeText(MainActivity.this,sp_content[position],Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                if ("标准".equals(sp_content[position])) {
                    i.setClass(TestThreeActivity.this, MainActivity.class);
                    startActivity(i);
                } else if ("科学".equals(sp_content[position])) {
                    i.setClass(TestThreeActivity.this, TestOneActivity.class);
                    startActivity(i);
                } else if ("进制转换".equals(sp_content[position])) {
                    i.setClass(TestThreeActivity.this, TestTwoActivity.class);
                    startActivity(i);
                }else if("日期计算".equals(sp_content[position])){
                    i.setClass(TestThreeActivity.this, TestFourActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

}