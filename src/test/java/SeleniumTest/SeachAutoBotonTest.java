package SeleniumTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeachAutoBotonTest {

    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://new.yapo.cl");

        test = TestSuite.createTest("Buscar autos botón", "Probar la busqueda de vehiculos por botón");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testBotonSeach() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://new.yapo.cl");
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/div/a[2]/img")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement message = driver.findElement(By.xpath("/html/body/app-root/home-index/div/h1[2]"));
        String value = message.getText();
        assertEquals("Buscar por presupuesto", value);

        if (value.equals("Buscar por presupuesto")) {
            test.log(Status.PASS, "El texto en el encabezado es correcto");
        } else {
            test.log(Status.FAIL, "El texto en el encabezado no es correcto");
        }
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
