package SeleniumTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PublicarAvisoTest {

    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www2.yapo.cl/login");

        test = TestSuite.createTest("Prueba Publicar Aviso", "Probar la publicación de avisos");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testPublicar() throws InterruptedException {
        driver.findElement(By.id("email_input")).sendKeys("rsol777ddd@gmail.com");
        driver.findElement(By.id("password_input")).sendKeys("hs9sASjj");
        driver.findElement(By.id("submit_button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        Thread.sleep(2000);

        //una vez loguado, dirigirse a la publicación
        driver.get("https://new.yapo.cl/legacy/ad-insert.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        Thread.sleep(2000);

        driver.findElement(By.id("category_group")).click();
        driver.findElement(By.id("cat5160")).click();
        driver.findElement(By.id("subject")).sendKeys("Florero");
        driver.findElement(By.id("body")).sendKeys("Florero en excelente estado");
        driver.findElement(By.id("price")).sendKeys("5000");
        Thread.sleep(2000);

        //upload photo
        driver.findElement(By.xpath("//*[@id=\"image_upload_button\"]/input")).sendKeys("C:\\Users\\Aranzu\\Mi unidad\\ACL\\Plan de Estudio\\15 Selenium\\florero.jpg");
        driver.findElement(By.xpath("//*[@id=\"aiform\"]/div[1]/div/ins")).click();
        driver.findElement(By.xpath("//*[@id=\"submit_create_now\"]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        Thread.sleep(2000);

        driver.switchTo().alert().accept();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        Thread.sleep(2000);

        WebElement message = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[2]/div/div/h1"));
        String value = message.getText();
        if (value.equals("¡Aviso en Revisión!")) {
            test.log(Status.PASS, "Se publicó el aviso'");
        } else {
            test.log(Status.FAIL, "No se publicó el aviso");
        }
        assertEquals("¡Aviso en Revisión!", value);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
