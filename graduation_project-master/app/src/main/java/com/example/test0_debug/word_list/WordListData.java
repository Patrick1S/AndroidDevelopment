package com.example.test0_debug.word_list;

public class WordListData {
    private String msg;
    private String name;
    private String desc;
    private String symbol;
    private String course_num;
    private String class_id;
    private String sound;
    private String class_title;
    private String example_sentence;
    private int memory;
    private int wordId;
    private int favorites;

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

    public int getFavorites() {
        return favorites;
    }

    public String getExampleSentence() {
        return example_sentence;
    }

    public void setExampleSentence(String example_sentence) {
        this.example_sentence = example_sentence;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getWordId() {
        return wordId;
    }

    public int getMemory() {
        return memory;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setClass_title(String class_title) {
        this.class_title = class_title;
    }

    public String getSound() {
        return sound;
    }

    public String getClass_title() {
        return class_title;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSymbol() {
        return symbol;
    }
}
