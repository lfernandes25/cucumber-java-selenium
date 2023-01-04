package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/",
        glue = "steps",
        plugin = {"pretty", "html:target/relatorio/report-html.html"},
        tags = "@inserirContaVersaoUm" ,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        dryRun = false // valida se os cenarios foram construidos corretamente, sem executar os testes
)
public class RunnerContaVersaoUm {

    @BeforeClass
    public static void reset(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
        driver.findElement(By.id("email")).sendKeys("leanstorm@gmail.com");
        driver.findElement(By.id("senha")).sendKeys("qsc258");
        driver.findElement(By.xpath("//button[contains(text(),'Entrar')]")).click();
        driver.findElement(By.linkText("reset")).click();
        driver.quit();
    }

}
