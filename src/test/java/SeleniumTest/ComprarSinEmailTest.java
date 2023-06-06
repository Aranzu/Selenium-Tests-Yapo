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

public class ComprarSinEmailTest {

    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www2.yapo.cl/login");

        test = TestSuite.createTest("Prueba Comprar", "Probar si es puede comprar productos");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testPublicar() throws InterruptedException {
        driver.findElement(By.id("email_input")).sendKeys("rsol777ddd@gmail.com");
        driver.findElement(By.id("password_input")).sendKeys("hs9sASjj");
        driver.findElement(By.id("submit_button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        //Buscar producto
        driver.get("https://new.yapo.cl/");
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/app-search-autocomplete/div[1]/input")).sendKeys("Smartwatch soymomo space lite rosado openbox");
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/button")).click();
        driver.get("https://new.yapo.cl/marketplace/smartwatch-soymomo-space-lite-rosado-openbox_86959824");
        driver.findElement(By.xpath("/html/body/app-root/adview-index/div/div[2]/div/div[2]/div/adview-publisher/div/div[2]/a")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(" ");
        driver.findElement(By.id("delivery_strategies-PICK_UP")).click();
        driver.findElement(By.xpath("//*[@id=\"Form1\"]/div[1]/div/div[2]/div[2]/div[1]/button")).submit();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        //Comprobar resultado
        WebElement message = driver.findElement(By.id("error-for-email"));
        String value = message.getText();
        if (value.equals("Introduce un correo electrónico")) {
            test.log(Status.PASS, "Paso a 'Pago'");
        } else if (value.equals("Contacto")) {
            test.log(Status.WARNING, "No se avanzo a Pago, sigue en contacto.");
        } else {
            test.log(Status.FAIL, "El sistema no indica que falta el correo");
        }
        assertEquals("Introduce un correo electrónico", value);

    }

    @After
    public void tearDown(){
        driver.quit();
    }


}
