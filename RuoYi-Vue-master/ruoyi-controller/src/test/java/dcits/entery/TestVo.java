package dcits.entery;

import com.ruoyi.common.utils.poi.ExcelUtil;
import dcits.entery.CusHouse;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;


@SpringBootTest
public class TestVo {

    @Test
    public void test01() throws Exception {
        InputStream is = new FileInputStream(new File("C:\\Users\\12141\\Desktop\\s.xlsx"));
        ExcelUtil<CusHouse> util = new ExcelUtil<CusHouse>(CusHouse.class);
        List<CusHouse> userList = util.importExcel(is);
    }
}
