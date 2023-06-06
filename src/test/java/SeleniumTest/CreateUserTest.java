package SeleniumTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUserTest {
    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://www2.yapo.cl/login");

        test = TestSuite.createTest("Prueba crear usuario", "Probar la creación de usuarios con datos válidos");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testCreateUser() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"account_create_form\"]/fieldset/div[1]/div[1]/div/div[1]/div/ins")).click();
        driver.findElement(By.id("account_name")).sendKeys("raul gonzalez");
        driver.findElement(By.xpath("//*[@id=\"account_create_form\"]/fieldset/div[1]/div[5]/div/div[1]/div/ins")).click();
        driver.findElement(By.id("account_region")).click();
        driver.findElement(By.xpath("//*[@id=\"account_region\"]/option[2]")).click();
        driver.findElement(By.id("account_commune")).click();
        driver.findElement(By.xpath("//*[@id=\"account_commune\"]/option[5]")).click();
        driver.findElement(By.name("phone")).sendKeys("45859535");
        driver.findElement(By.id("account_email")).sendKeys("asdasdasd@gmail.com");
        driver.findElement(By.id("account_password")).sendKeys("F58sgg1112");
        driver.findElement(By.id("account_password_verify")).sendKeys("F58sgg1112");
        driver.findElement(By.xpath("//*[@id=\"label_accept_conditions\"]/div/ins")).click();
        driver.findElement(By.id("edit_profile_btn")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement message = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[1]/h2"));
        String value = message.getText();
        if (value.equals("Felicidades!")) {
            test.log(Status.PASS, "Se encontró el mensaje 'Felicidades!'");
        } else {
            test.log(Status.FAIL, "El usuario no pudo ser creado.");
        }
        assertEquals("Felicidades!", value);

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
