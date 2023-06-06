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

public class SearchMarketPlaceTest {
    private WebDriver driver;
    private ExtentTest test;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://new.yapo.cl");

        // Crear el test en el reporte
        test = TestSuite.createTest("Search MarketPlace Test", "Test to search marketplaces");
        test.log(Status.INFO, "Comenzando caso de prueba");
    }

    @Test
    public void testSearchMarket() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/app-root/home-index/home-search/div/a[3]/img")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));

        WebElement message = driver.findElement(By.xpath("/html/body/app-root/listing-index/listing-main/div[2]/div/div[2]/listing-result-list/div/div/h1"));
        String value = message.getText();
        if (value.equals("Resultados en Marketplace.")) {
            test.log(Status.PASS, "Se encontró el mensaje 'Resultados en Marketplace.'");
        } else if (value.equals("No se encontraron resultados")) {
            test.log(Status.WARNING, "El mensaje 'No se encontraron resultados' fue encontrado en lugar del mensaje esperado.");
        } else {
            test.log(Status.FAIL, "El mensaje esperado no fue encontrado.");
        }
        assertEquals("Resultados en Marketplace.", value);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
