package SeleniumTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchEmpleoTest {
    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://new.yapo.cl");

        // Crear el test en el reporte
        test = TestSuite.createTest("Prueba de busqueda de trabajos", "Prueba para examinar si el campo de busqueda busca trabajos");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testSearchBoxJobs() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/app-option-select[2]/button/span")).click();
        driver.findElement(By.xpath("//*[@id=\"mat-menu-panel-1\"]/div/div/div[1]/div/input")).sendKeys("v valparaíso");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
        driver.findElement(By.xpath("//*[@id=\"mat-menu-panel-1\"]/div/div/div[2]/button/app-highlight-text")).click();
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/app-search-autocomplete/div[1]/input")).sendKeys("empleo");
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        String expectedMessage = "Resultados en Busco empleo.";
        String actualMessage = driver.findElement(By.xpath("/html/body/app-root/listing-index/listing-main/div[2]/div/div[2]/listing-result-list/div/div/h1")).getText();
        if (actualMessage.equals(expectedMessage)) {
            test.log(Status.PASS, "Se mostraron los resultados esperados");
        } else {
            test.log(Status.FAIL, "No se mostraron los resultados esperados");
        }
        assertEquals(expectedMessage, actualMessage);

    }

    @After
    public void tearDown() {

        driver.quit();
    }
}
