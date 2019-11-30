package com.boot.lea.mybot.wan;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("公众号");
        list.add("why技术");
        Integer loopTime =1;
        Iterator var3 = list.iterator();
        while(var3.hasNext()) {
            System.out.println("loopTime = " + loopTime);
            loopTime = loopTime + 1;
            var3.next();
        }
    }
}
