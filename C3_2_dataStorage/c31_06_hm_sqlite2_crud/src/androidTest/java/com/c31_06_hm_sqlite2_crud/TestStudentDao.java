package com.c31_06_hm_sqlite2_crud;

import android.test.AndroidTestCase;
import android.util.Log;

import dbhelper.Student;
import dbhelper.StudentDao;

/**
 * Created by vero on 2016/5/20.
 */
public class TestStudentDao extends AndroidTestCase {

    public void testAdd(){
        Student st=new Student("1","vv","hahah");
        StudentDao studentDao=new StudentDao(getContext());
        studentDao.add(st);
        Log.i("ssssssssssssssssssssst",st.toString());
    }
    public void testDelete(){
        StudentDao studentDao=new StudentDao(getContext());
        studentDao.delete("1");
    }
    public void testUpdate(){
        Student st=new Student("1","vero","man");
        StudentDao studentDao=new StudentDao(getContext());
        studentDao.update(st);
    }
    public void testQuery(){
        StudentDao studentDao=new StudentDao(getContext());
        Student st=studentDao.query("1");
        Log.i("ssssssssssssssssssssst",st.toString());
    }
}
