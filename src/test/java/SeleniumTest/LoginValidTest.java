package SeleniumTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginValidTest {

    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www2.yapo.cl/login");

        test = TestSuite.createTest("Prueba Login Válido", "Probar el login con credenciales válidas");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }
    @Test
    public void testLogin() throws InterruptedException {
        driver.findElement(By.id("email_input")).sendKeys("rsol777ddd@gmail.com");
        driver.findElement(By.id("password_input")).sendKeys("hs9sASjj");
        driver.findElement(By.id("submit_button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        String url = driver.getCurrentUrl();
        if (url.equals("https://www2.yapo.cl/dashboard")) {
            test.log(Status.PASS, "El login se realizó correctamente");
        } else if (url.equals("https://www2.yapo.cl/login")) {
            test.log(Status.WARNING, "El login no redireccionó correctamente");
        } else {
            test.log(Status.FAIL, "No se pudo hacer el login");
        }
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
