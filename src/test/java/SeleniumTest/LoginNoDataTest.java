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

public class LoginNoDataTest {

    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www2.yapo.cl/login");

        test = TestSuite.createTest("Prueba Login sin datos", "Probar el login sin ingresar credenciales");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }
    @Test
    public void testLogin() throws InterruptedException {
        driver.findElement(By.id("email_input")).sendKeys("");
        driver.findElement(By.id("password_input")).sendKeys("");
        driver.findElement(By.id("submit_button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        WebElement message = driver.findElement(By.id("err_form"));
        String value = message.getText();
        assertEquals("El correo electrónico y/o la contraseña que ingresaste son incorrectos.", value);

        if (value.equals("El correo electrónico y/o la contraseña que ingresaste son incorrectos.")) {
            test.log(Status.PASS, "El mensaje de error es correcto");
        } else {
            test.log(Status.FAIL, "El mensaje de error no es correcto");
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
