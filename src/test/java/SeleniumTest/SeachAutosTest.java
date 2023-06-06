package SeleniumTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeachAutosTest {

    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://new.yapo.cl");

        test = TestSuite.createTest("Buscar autos search", "Probar la busqueda de vehiculos");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testSearchbox() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/app-search-autocomplete/div[1]/input")).sendKeys("auto");
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement message = driver.findElement(By.xpath("/html/body/app-root/listing-index/listing-main/div[2]/div/div[2]/listing-result-list/div/div/h1"));
        String value = message.getText();
        if(value.equals("Resultados en Autos, camionetas y 4x4.")) {
            test.log(Status.PASS, "La búsqueda de autos funcionó correctamente");
        } else {
            test.log(Status.FAIL, "La búsqueda de autos falló");
        }
        assertEquals("Resultados en Autos, camionetas y 4x4.", value);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
