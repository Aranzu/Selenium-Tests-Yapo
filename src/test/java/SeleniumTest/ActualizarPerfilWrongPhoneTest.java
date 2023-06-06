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

public class ActualizarPerfilWrongPhoneTest {
    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www2.yapo.cl/login");

        test = TestSuite.createTest("Actualizar pefil", "Probar si se permite actualizar perfil ingresando un número inferior a 8 digitos");
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
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("phone")).sendKeys("8321897");
        driver.findElement(By.id("edit_profile_btn")).click();

        WebElement message = driver.findElement(By.id("phone-error"));
        String value = message.getText();
        if (value.equals("Teléfono muy corto, código de área + número completo")) {
            test.log(Status.PASS, "Se encontró el mensaje 'Teléfono muy corto, código de área + número completo'");
        } else if (value.equals("Número de teléfono inválido")) {
            test.log(Status.WARNING, "El mensaje 'Número de teléfono inválido' fue encontrado en lugar del mensaje esperado.");
        } else {
            test.log(Status.FAIL, "El mensaje esperado no fue encontrado.");
        }
        assertEquals("Teléfono muy corto, código de área + número completo", value);

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
