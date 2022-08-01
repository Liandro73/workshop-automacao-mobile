package br.com.liandro.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class AlarmePageObject {

    @AndroidFindBy( xpath = "//android.widget.Button[@text='OK']" )
    private MobileElement botaoOk;

    @AndroidFindBy( id = "android:id/input_hour" )
    private MobileElement campoHora;

    @AndroidFindBy( id = "android:id/input_minute" )
    private MobileElement campoMinutos;

    LocalDateTime dateTime = LocalDateTime.now();

    AppiumDriver<MobileElement> driver;

    public AlarmePageObject(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void tirarPrintTela(String nomeEtapa) throws IOException {
        File evidencia = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String nomeEtapaFormatado = nomeEtapa
                .toLowerCase()
                .replace("ã", "a")
                .replace("ç", "c")
                .replace(" ", "_");
        FileUtils.moveFile(evidencia, new File("target/screenshots/evidencia_" + nomeEtapaFormatado + "_" + dateTime + ".jpg"));
    }

    public void tirarPrintEImprimirNoLog(String passo) throws IOException {
        System.out.println(passo);
        tirarPrintTela(passo);
    }

    public void verificarAlarmeEHorario(String passoNome) throws IOException {
        driver.findElementByAccessibilityId("10:00").isDisplayed();
        Assert.assertEquals("10:00", driver.findElementByAccessibilityId("10:00").getText());
        tirarPrintEImprimirNoLog("Confirmei que a hora cadastrada esta de acordo com os valores enviados " + passoNome);
    }

    public void clicarNoBotaoOkEEvidenciar(String passoAtual) throws IOException {
        botaoOk.click();
        tirarPrintEImprimirNoLog("Cliquei no botao OK " + passoAtual);
    }

    public void clicarNoBotaoAlarmeEValidarTelaDeAlarme(String passoAtual) throws IOException {
        driver.findElementByAccessibilityId("Alarme").click();
        tirarPrintEImprimirNoLog("Clicou no botao Alarme " + passoAtual);

        driver.findElementById("com.google.android.deskclock:id/action_bar_title").isDisplayed();
        Assert.assertEquals("Alarme", driver.findElementById("com.google.android.deskclock:id/action_bar_title").getText());
        tirarPrintEImprimirNoLog("Validamos que a tela de Alarme foi exibida " + passoAtual);
    }

    public void clicarEmAdicionarAlarme() throws IOException {
        driver.findElementByAccessibilityId("Adicionar alarme").click();
        tirarPrintEImprimirNoLog("Clicou no botao Adicionar alarme");
    }

    public void clicarEmAlternarModoDeEntrada() throws IOException {
        driver.findElementByAccessibilityId("Alterne para o modo de entrada de texto para informar o horário.").click();
        tirarPrintEImprimirNoLog("Clicou no botao Adicionar mudança de entrada de texto");
    }

    public void verificarQueDialogFoiExibida() throws IOException {
        driver.findElementById("android:id/input_header").isDisplayed();
        Assert.assertEquals("Definir horário", driver.findElementById("android:id/input_header").getText());
        tirarPrintEImprimirNoLog("Confirmei que a dialog foi exibida");
    }

    public void preencherCampoHoras(String hora) throws IOException {
        campoHora.clear();
        campoHora.sendKeys(hora);
        tirarPrintEImprimirNoLog("Lancei o valor 10 no campo hora");
    }

    public void preencherCampoMinutos(String minutos) throws IOException {
        campoMinutos.clear();
        campoMinutos.sendKeys(minutos);
        tirarPrintEImprimirNoLog("Lancei o valor 00 no campo minuto");
    }

    public void clicarNoBotaoRepetirLigadoOuDesligado() throws IOException {
        driver.findElementById("com.google.android.deskclock:id/onoff").click();
        tirarPrintEImprimirNoLog("Cliquei no botao para repetir o alarme");
    }

    public void clicarNoBotaoAdicionarMarcador() throws IOException {
        driver.findElement(By.xpath("//android.widget.TextView[@text='Adicionar marcador']")).click();
        tirarPrintEImprimirNoLog("Clicar no botão Marcador");
    }

    public void verificarSeMarcadorFoiExibido() throws IOException {
        driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).isDisplayed();
        Assert.assertEquals("Marcador", driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).getText());
        tirarPrintEImprimirNoLog("Confirmei que a dialog do marcador foi exibida com sucesso");
    }

    public void preencherCampoMarcador(String nomeMarcador) throws IOException {
        driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).clear();
        driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).sendKeys(nomeMarcador);
        tirarPrintEImprimirNoLog("Lancei o valor Alarme Workshop no campo Marcador");
    }

    public void clicarNoBotaoRecolherAlarme() throws IOException {
        driver.findElementByAccessibilityId("Recolher alarme").click();
        tirarPrintEImprimirNoLog("Cliquei no botao Recolher alarme");
    }

    public void verificarSeMarcadorRecebeuNomeDefinido() throws IOException {
        driver.findElementByAccessibilityId("Marcador Alarme Workshop").isDisplayed();
        Assert.assertEquals("Alarme Workshop", driver.findElementByAccessibilityId("Marcador Alarme Workshop").getText());
        tirarPrintEImprimirNoLog("Confirmei que a marcador recebeu o nome de Alarme Workshop");
    }

}
