package com.example.myapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Date;

public class TestFourActivity extends AppCompatActivity {

    private Spinner skipto;
    private EditText edt_data1;
    private EditText edt_data2;
    private EditText sub_date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_four);

        skipto=findViewById(R.id.skipto);
        skipto.setSelection(4);

        edt_data1=findViewById(R.id.edt_date1);
        edt_data2=findViewById(R.id.edt_date2);
        sub_date=findViewById(R.id.sub_date);



        skipto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String[] sp_content = getResources().getStringArray(R.array.CaculatorType);
                System.out.println(sp_content[position]);
                Intent i = new Intent();
                //Toast.makeText(MainActivity.this,sp_content[position],Toast.LENGTH_SHORT).show();
                if ("科学".equals(sp_content[position])) {
                    i.setClass(TestFourActivity.this, TestOneActivity.class);
                    startActivity(i);
                }
                else if("进制转换".equals(sp_content[position])){
                    i.setClass(TestFourActivity.this, TestTwoActivity.class);
                    startActivity(i);
                }
                else if("单位转换".equals(sp_content[position])){
                    i.setClass(TestFourActivity.this, TestThreeActivity.class);
                    startActivity(i);
                }else if("标准".equals(sp_content[position])){
                    i.setClass(TestFourActivity.this, MainActivity.class);
                    startActivity(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edt_data2.addTextChangedListener(new TextWatcher() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void afterTextChanged(Editable s) {

                if (edt_data1.getText() != null && edt_data2.getText() != null) {
                    try {
                        int days = calculateTime(edt_data1.getText() + "", edt_data2.getText() + "");
                        sub_date.setText(days + "");

                    } catch (ParseException e) {
                        sub_date.setText( "日期格式错误");
                        e.printStackTrace();
                    }
                }
            }



        });
        


    }
    /**
     * 用SimpleDateFormat计算时间差
     * @throws ParseException
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int calculateTime(String str1,String str2) throws ParseException {
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
        /*天数差*/
        Date fromDate1 = simpleFormat.parse(str1);
        Date toDate1 = simpleFormat.parse(str2);
        long from1 = fromDate1.getTime();
        long to1 = toDate1.getTime();
        int days = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));
        System.out.println("两个时间之间的天数差为：" + days);
        return  days;
    }

}