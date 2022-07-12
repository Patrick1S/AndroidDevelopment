package com.example.test0_debug.tools.httpHelper.xmlParse;

import com.example.test0_debug.tools.db.DBHelper;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

public class PullParse {
    private static final String TAG = "myTAG";

    public static String parseXmlWithPull(String data,boolean showTrans){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser pullParser=factory.newPullParser();
            pullParser.setInput(new StringReader(data));
            int eventType=pullParser.getEventType();
            String orig="";
            String trans="";
            while (eventType!=XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG: {
                        if (pullParser.getName().equals("orig")) {
                            orig = pullParser.nextText();
                        } else if (pullParser.getName().equals("trans")) {
                            trans = pullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if (pullParser.getName().equals("sent")){
                            stringBuilder.append(orig);
                            if (showTrans)
                                stringBuilder.append(trans);
                            stringBuilder.append("\n");
                        }
                    }
                    default:
                        break;
                }

                eventType=pullParser.next();
            }
        }catch (Exception e){
            e.printStackTrace();
            stringBuilder.append(e.getMessage());
        }
        return DBHelper.sqliteEscape(stringBuilder.toString());
    }
}
