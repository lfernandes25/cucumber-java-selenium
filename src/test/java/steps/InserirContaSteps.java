package steps;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class InserirContaSteps {

    private WebDriver driver;

    @Dado("que estou acessando a aplicacao")
    public void queEstouAcessandoAAplicacao() {
        driver = new ChromeDriver();
        driver.get("https://seubarriga.wcaquino.me/");
    }
    @Quando("inform o usuario {string}")
    public void informOUsuario(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }
    @Quando("a senha {string}")
    public void aSenha(String senha) {
        driver.findElement(By.id("senha")).sendKeys(senha);
    }
    @Quando("seleciono entrar")
    public void selecionoEntrar() {
        driver.findElement(By.xpath("//button[contains(text(),'Entrar')]")).click();
    }
    @Entao("visualizo a pagina inicial")
    public void visualizoAPaginaInicial() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals(texto,"Bem vindo, leandro!");
    }
    @Quando("seleciono Contas")
    public void selecionoContas() {
        driver.findElement(By.linkText("Contas")).click();
    }
    @Quando("seleciono Adicionar")
    public void selecionoAdicionar() {
        driver.findElement(By.linkText("Adicionar")).click();
    }
    @Quando("informo a conta {string}")
    public void informoAConta(String string) {
        driver.findElement(By.id("nome")).sendKeys(string);
    }
    @Quando("seleciono Salvar")
    public void selecionoSalvar() {
        driver.findElement(By.xpath("//button[contains(text(),'Salvar')]")).click();
    }
    @Entao("a conta e inserida com sucesso")
    public void aContaEInseridaComSucesso() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
        Assert.assertEquals(texto,"Conta adicionada com sucesso!");
    }

    @Entao("sou notificar que o nome da conta e obrigatorio")
    public void souNotificarQueONomeDaContaEObrigatorio() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals(texto,"Informe o nome da conta");
    }

    @Entao("sou notificado que ja existe uma conta com esse nome")
    public void souNotificadoQueJaExisteUmaContaComEsseNome() {
        String texto = driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
        Assert.assertEquals(texto,"Já existe uma conta com esse nome!");
    }

    @Entao("recebo a mensagem {string}")
    public void receboAMensagemContaAdicionadaComSucesso(String mensagem) {
        String texto = driver.findElement(By.xpath("//div[starts-with(@class,'alert alert-')]")).getText();
        Assert.assertEquals(texto,mensagem);
    }

    @After(order = 1, value = "@selenium")
    public void screenshot(Scenario cenario){
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("target/screenshot/"+cenario.getId().concat(".jpg")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @After(order = 0)
    public void fecharBrowser(){
        driver.quit();
    }

}
