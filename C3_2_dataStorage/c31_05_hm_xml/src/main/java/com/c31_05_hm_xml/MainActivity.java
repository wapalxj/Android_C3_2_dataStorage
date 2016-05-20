package com.c31_05_hm_xml;
/**
 * serializer生成xml和pull解析
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import domain.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name;
    private EditText et_no;
    private RadioGroup r_sex;
    private Button save;
    private Button query;
    private TextView tv_display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        et_name= (EditText) findViewById(R.id.et_name);
        et_no= (EditText) findViewById(R.id.et_no);
        r_sex= (RadioGroup) findViewById(R.id.r_group);
        save= (Button) findViewById(R.id.save);
        query= (Button) findViewById(R.id.query);
        tv_display= (TextView) findViewById(R.id.tv_display);
        save.setOnClickListener(this);
        query.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        File file=new File(getFilesDir(),"vero.xml");
        switch (v.getId()){
            case R.id.save:
                try {
                    if (TextUtils.isEmpty(et_name.getText())){
                        et_name.setError("不能为空！");
                        return;
                    }
                    if (TextUtils.isEmpty(et_no.getText())){
                        et_no.setError("不能为空！");
                        return;
                    }
                    int id=r_sex.getCheckedRadioButtonId();
                    String sex="男";
                    if (id==R.id.male){
                        sex="男";
                    }else if(id==R.id.female){
                        sex="女";
                    }
                    OutputStream os=new FileOutputStream(file);
                    //生成XML的序列化器
                    XmlSerializer serializer=Xml.newSerializer();
                    serializer.setOutput(os,"UTF-8");
                    //<?xml version="1.0" encoding="utf-8" standalone?>
                    serializer.startDocument("UTF-8",true);//参数对应encoding和standalone
                    serializer.startTag("null","student");//namespace为null

                    serializer.startTag("null","name");
                    serializer.text(et_name.getText().toString());
                    serializer.endTag("null","name");

                    serializer.startTag("null","number");
                    serializer.text(et_no.getText().toString());
                    serializer.endTag("null","number");

                    serializer.startTag("null","sex");
                    serializer.text(sex);
                    serializer.endTag("null","sex");

                    serializer.endTag("null","student");
                    serializer.endDocument();
                    os.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.query:
                XmlPullParser pullParser=Xml.newPullParser();
                try {
                    InputStream is=new FileInputStream(file);
                    pullParser.setInput(is,"UTF-8");
                    int eventType=pullParser.getEventType();
                    Student s=null;
                    while (eventType!=XmlPullParser.END_DOCUMENT){
                        if (eventType==XmlPullParser.START_TAG){
                            //获得当前元素的名称
                            if ("student".equals(pullParser.getName())){
                                //准备Student类的实例，封装数据
                                s=new Student();
                            }else if("name".equals(pullParser.getName())){
                                String name=pullParser.nextText();
                                s.setName(name);
                            }else if("number".equals(pullParser.getName())){
                                String number=pullParser.nextText();
                                s.setNo(number);
                            }else if("sex".equals(pullParser.getName())){
                                String sex=pullParser.nextText();
                                s.setSex(sex);
                            }
                        }
                        //“指针”移动
                        eventType=pullParser.next();
                    }
                    tv_display.setText("student-->name=="+s.getName()+"-----number=="+s.getNo()+"---sex=="+s.getSex());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}
