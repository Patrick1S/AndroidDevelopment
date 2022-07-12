package com.example.test0_debug.tools.httpHelper.jsonParse;

import java.util.ArrayList;

public class BookListData {
    private String course_num;
    private String word_num;
    private String title;
    private ArrayList<BookListData_childList> child_list;

    public ArrayList<BookListData_childList> getChild_list() {
        return child_list;
    }

    public String getWord_num() {
        return word_num;
    }

    public String getTitle() {
        return title;
    }

    public String getCourse_num() {
        return course_num;
    }
}
