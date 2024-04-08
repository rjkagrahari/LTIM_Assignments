package Assigement_03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assignment3 {
    WebDriver driver;
    @BeforeMethod
    public void invokebrowser()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://money.rediff.com/losers/bse/daily/groupall");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    public void closedriver()

    {
        driver.close();
    }
    @Test
    public void ValidateData() throws IOException, InterruptedException {
        HashMap<String, String> webTable = new HashMap<String, String>();
        List<WebElement> allcomRows = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));

        for (int i = 0; i < allcomRows.size(); i++) {
            List<WebElement> cells = allcomRows.get(i).findElements(By.tagName("td"));
            String key = cells.get(0).getText();
            String value = cells.get(3).getText();
            webTable.put(key, value);

        }
        String actualOutput = null;
        for (Map.Entry<String, String> entry : webTable.entrySet()) {
            actualOutput = entry.getKey() + " " + entry.getValue();
            System.out.println(actualOutput);
        }
        System.out.println("--------------excel value----------------------");
        FileInputStream fis = new FileInputStream("src/test/Resources/testData.xlsx");
        XSSFWorkbook wbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = wbook.getSheetAt(0);
        String excelCellValue = null;
        for (int j = 0; j <= sheet.getLastRowNum(); j++) {
            XSSFRow row = sheet.getRow(j);
            for (int k = 0; k < row.getLastCellNum(); k++) {
                Cell cellvalue = row.getCell(k, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                excelCellValue = cellvalue.toString();
                System.out.print(excelCellValue+" ");
            }
            System.out.println();
        }
        wbook.close();
        Assert.assertThat(actualOutput, Matchers.containsString(excelCellValue));



    }
}

