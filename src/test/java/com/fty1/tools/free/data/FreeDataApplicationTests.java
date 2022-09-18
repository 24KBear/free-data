package com.fty1.tools.free.data;

import com.alibaba.fastjson.JSON;
import com.fty1.tools.free.data.kernel.NavicatSQL;
import com.fty1.tools.free.data.kernel.RowData;
import com.fty1.tools.free.data.utils.MySQLHelper;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.List;

@SpringBootTest
class FreeDataApplicationTests {


    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() {


        String sql = "select s.TABLE_NAME from information_schema.TABLES s where s.TABLE_SCHEMA = 'mysql'";
        List<List<RowData>> data = MySQLHelper.navicatSQL(dataSource, new NavicatSQL(sql));
        System.out.printf(JSON.toJSONString(data));
    }

}
