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

public class ActualizarPerfilWrongRutTest {
    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www2.yapo.cl/login");

        test = TestSuite.createTest("Actualizar pefil", "Probar si se permite actualizar perfil si se ingresa un rut inválido");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testPublicar() throws InterruptedException {
        //Login
        driver.findElement(By.id("email_input")).sendKeys("rsol777ddd@gmail.com");
        driver.findElement(By.id("password_input")).sendKeys("hs9sASjj");
        driver.findElement(By.id("submit_button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));

        //Actualizar
        driver.findElement(By.xpath("//*[@id=\"link_profile\"]/i")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        driver.findElement(By.id("account_rut")).clear();
        driver.findElement(By.id("account_rut")).sendKeys("183548746");
        driver.findElement(By.id("edit_profile_btn")).click();

        WebElement message = driver.findElement(By.id("err_rut"));
        String value = message.getText();
        if (value.equals("El RUT es incorrecto")) {
            test.log(Status.PASS, "Se encontró el mensaje 'El RUT es incorrecto'");
        } else if (value.equals("Debes ingresar tu RUT")) {
            test.log(Status.WARNING, "El mensaje 'Debes ingresar tu RUT' fue encontrado en lugar del mensaje esperado.");
        } else {
            test.log(Status.FAIL, "El mensaje esperado no fue encontrado.");
        }
        assertEquals("El RUT es incorrecto", value);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
