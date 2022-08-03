package com.maker;

import com.maker.vo.Dept;

public class TestClass {
    public static void main(String[] args){
        System.out.println("中文测试");
        Dept dept=new Dept();
        dept.setDeptno(10001L);
        dept.setDeptname("技术部");
        dept.setLoc("武汉");
        System.out.println(dept.toString());
    }
}
