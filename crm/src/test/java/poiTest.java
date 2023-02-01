import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * Author: zr
 * Date: 2023/1/9 10:46
 * Description:
 */
public class poiTest {
    public static void main(String[] args) {
//        HSSFWorkbook exl = new HSSFWorkbook();
//        HSSFSheet sheet = exl.getSheet("");
//        HSSFRow row = sheet.getRow();
        String a = "afaeg4eageag";
        String b = "afaeg4eageag";
        System.out.println(a==b);
        System.out.println(a.equals(b));
    }

}
