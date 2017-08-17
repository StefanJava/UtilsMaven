package com.github.flyinghe.test;

import com.github.flyinghe.tools.CommonUtils;
import com.github.flyinghe.tools.WriteExcelUtils;
import com.github.flyinghe.domain.Pet;
import com.github.flyinghe.domain.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * Created by Flying on 2016/5/28.
 */
public class TestBeanToMap {

    @Test
    public void test1() throws Exception {
        User user = new User("小红", "女", new Date());
        //        user.setPet(new Pet("小猫", new Date()));
        user.setAge(90);
        user.setAddress("uiijji");
        Map<String, Object> map = CommonUtils.toMap(user);
        for (String s : map.keySet()) {
            System.out.println(s + ":" + map.get(s));
        }
    }

    @Test
    public void test2() throws IOException {
        User user = new User("小红", "女", new Date());
        user.setPet(new Pet("小猫", new Date()));
        user.setAge(90);
        user.setAddress("uiijji");
        List<User> list = new ArrayList<User>();
        list.add(user);
        Workbook workbook = new HSSFWorkbook();
        //        workbook
        File file = new File("C:\\Users\\h_kx1\\Desktop\\test.xls");
        OutputStream os = new FileOutputStream(file);

        WriteExcelUtils.writeWorkBook(workbook, list);
        WriteExcelUtils.writeWorkBookToExcel(workbook, os);
        CommonUtils.closeIOStream(null, os);
    }

}
