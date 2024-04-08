import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Assignment_flipkart extends baseClass{


    @Test
    public void forEachLoop()
    {
        alllinks = driver.findElements(By.tagName("a"));
        int size=alllinks.size();
        System.out.println(size);
        for (WebElement link : alllinks) {
            System.out.println(link.getText() + " : " + link.getAttribute("href"));
        }
    }
    @Test
    public void lemdaDemo()
    {
        alllinks = driver.findElements(By.tagName("a"));
        alllinks.forEach(s->System.out.println(s.getText()));
    }
    @Test
    public void streamDemo()
    {
        alllinks = driver.findElements(By.tagName("a"));
        alllinks.stream().forEach(s->System.out.println(s.getText()));
        System.out.println("-----------2nd senarion-print which links contains only text------------");
        List<String> linkContainsText=alllinks.stream().filter(s->!s.getText().equals("")).map(s->s.getText()).collect(Collectors.toList());
        linkContainsText.forEach(s->System.out.println(s));
    }
    @Test
    public void parallelStreamDemo()
    {
        alllinks = driver.findElements(By.tagName("a"));
        alllinks.parallelStream().forEach(s->System.out.println(s.getText()));
    }

}
