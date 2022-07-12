package com.example.test0_debug.tools.httpHelper.jsonParse;

import java.util.ArrayList;

public class BookList {
    private ArrayList<BookListData> datas;
    private String msg;
    private String code;


    public ArrayList<BookListData> getData() {
        return datas;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
