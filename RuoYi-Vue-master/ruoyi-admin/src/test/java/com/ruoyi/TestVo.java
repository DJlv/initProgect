package com.ruoyi;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import dcits.entery.CusHouse;
import dcits.interfaces.ICusHouseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


@SpringBootTest
public class TestVo {

    @Autowired
    private ICusHouseService cusHouseService;
    @Test
    public void test01() throws Exception {
        InputStream is = new FileInputStream(new File("C:\\Users\\12141\\Desktop\\s.xlsx"));
        ExcelUtil<CusHouse> util = new ExcelUtil<CusHouse>(CusHouse.class);
        List<CusHouse> userList = util.importExcel(is);
        List<CusHouse> collect = userList.stream().filter(vo -> StringUtils.isNotEmpty(vo.getStorageLocation())).collect(Collectors.toList());
        for (CusHouse cusHouse : collect) {
            int i = cusHouseService.insertCusHouse(cusHouse);
        }
        System.out.println(userList);
        System.out.println(userList.size());

    }
}
