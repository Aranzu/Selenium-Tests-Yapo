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

public class SearchDepartmentoTest {
    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://new.yapo.cl");

        test = TestSuite.createTest("Buscar departamentos", "Probar la busqueda de departamentos");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testSearchDepartment() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/app-option-select[2]/button/span")).click();
        driver.findElement(By.xpath("//*[@id=\"mat-menu-panel-1\"]/div/div/div[2]/button[1]/app-highlight-text")).click();
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/app-search-autocomplete/div[1]/input")).sendKeys("departamento");
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/app-search-form/div/button")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement message = driver.findElement(By.xpath("/html/body/app-root/listing-index/listing-main/div[2]/div/div[1]/div/listing-filters-loader/listing-filters/div[2]/listing-filter-item[2]/div[1]/listing-filter-item-price-currency/div/p"));
        String value = message.getText();
        if (value.equals("¿En qué divisa quieres buscar?")) {
            test.log(Status.PASS, "Se encontró el mensaje '¿En qué divisa quieres buscar?'");
        } else if (value.equals("Selecciona una divisa")) {
            test.log(Status.WARNING, "El mensaje 'Selecciona una divisa' fue encontrado en lugar del mensaje esperado.");
        } else {
            test.log(Status.FAIL, "El mensaje esperado no fue encontrado.");
        }
        assertEquals("¿En qué divisa quieres buscar?", value);

    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
