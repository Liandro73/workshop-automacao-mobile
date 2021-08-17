package br.com.liandro.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmeStepsTest {

    private AppiumDriver<MobileElement> driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    //udid = 0044581080
    //udid = emulator-5554

    public void iniciar() throws MalformedURLException {
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Moto G5s");
        capabilities.setCapability(MobileCapabilityType.UDID, "0044581080");
        capabilities.setCapability("appPackage", "com.google.android.deskclock");
        capabilities.setCapability("appActivity", "com.android.deskclock.DeskClock");

        driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void tirarPrintTela(String nomeEtapa) throws IOException {
        File evidencia = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String nomeEtapaFormatado = nomeEtapa
                .toLowerCase()
                .replace("ã", "a")
                .replace("ç", "c")
                .replace(" ", "_");
        FileUtils.moveFile(evidencia, new File("target/screenshots/evidencia_" + nomeEtapaFormatado + ".jpg"));
    }

    public void tirarPrintEImprimirNoLog(String passo) throws IOException {
        System.out.println(passo);
        tirarPrintTela(passo);
    }

    public void verificarAlarmeEHorario(String passoNome) throws IOException {
        Assert.assertTrue(driver.findElementByAccessibilityId("10:00").isDisplayed());
        Assert.assertEquals("10:00", driver.findElementByAccessibilityId("10:00").getText());
        tirarPrintEImprimirNoLog("Confirmei que a hora cadastrada esta de acordo com os valores enviados " + passoNome);
    }

    public void clicarNoBotaoOkEEvidenciar(String passoAtual) throws IOException {
        driver.findElement(By.xpath("//android.widget.Button[@text='OK']")).click();
        tirarPrintEImprimirNoLog("Cliquei no botao OK " + passoAtual);
    }

    public void clicarNoBotaoAlarmeEValidarTelaDeAlarme(String passoAtual) throws IOException {
        driver.findElementByAccessibilityId("Alarme").click();
        tirarPrintEImprimirNoLog("Clicou no botao Alarme " + passoAtual);

        Assert.assertTrue(driver.findElementById("com.google.android.deskclock:id/action_bar_title").isDisplayed());
        Assert.assertEquals("Alarme", driver.findElementById("com.google.android.deskclock:id/action_bar_title").getText());
        tirarPrintEImprimirNoLog("Validamos que a tela de Alarme foi exibida " + passoAtual);
    }

    @Test
    public void adicionarUmAlarme() throws IOException {
        iniciar();

        clicarNoBotaoAlarmeEValidarTelaDeAlarme("adicionar um alarme");

        driver.findElementByAccessibilityId("Adicionar alarme").click();
        tirarPrintEImprimirNoLog("Clicou no botao Adicionar alarme");

        driver.findElementByAccessibilityId("Alterne para o modo de entrada de texto para informar o horário.").click();
        tirarPrintEImprimirNoLog("Clicou no botao Adicionar mudança de entrada de texto");

        Assert.assertTrue(driver.findElementById("android:id/input_header").isDisplayed());
        Assert.assertEquals("Definir horário", driver.findElementById("android:id/input_header").getText());
        tirarPrintEImprimirNoLog("Confirmei que a dialog foi exibida");

        driver.findElementById("android:id/input_hour").clear();
        driver.findElementById("android:id/input_hour").sendKeys("10");
        tirarPrintEImprimirNoLog("Lancei o valor 10 no campo hora");

        driver.findElementById("android:id/input_minute").clear();
        driver.findElementById("android:id/input_minute").sendKeys("00");
        tirarPrintEImprimirNoLog("Lancei o valor 00 no campo minuto");

        clicarNoBotaoOkEEvidenciar("ao completar envio de valores ao alarme");

        verificarAlarmeEHorario("ao completar alarme");

        driver.findElementById("com.google.android.deskclock:id/repeat_onoff").click();
        tirarPrintEImprimirNoLog("Cliquei no botao para repetir o alarme");

        driver.findElement(By.xpath("//android.widget.TextView[@text='Marcador']")).click();
        tirarPrintEImprimirNoLog("Clicar no botão Marcador");

        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).isDisplayed());
        Assert.assertEquals("Marcador", driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).getText());
        tirarPrintEImprimirNoLog("Confirmei que a dialog do marcador foi exibida com sucesso");

        driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).clear();
        driver.findElement(By.xpath("//android.widget.EditText[@text='Marcador']")).sendKeys("Alarme Workshop");
        tirarPrintEImprimirNoLog("Lancei o valor Alarme Workshop no campo Marcador");

        clicarNoBotaoOkEEvidenciar("ao finalizar cadastro de alarme");

        driver.findElementByAccessibilityId("Recolher alarme").click();
        tirarPrintEImprimirNoLog("Cliquei no botao Recolher alarme");

        verificarAlarmeEHorario(" ao finalizar inclusão do alarme");

        Assert.assertTrue(driver.findElementByAccessibilityId("Marcador Alarme Workshop").isDisplayed());
        Assert.assertEquals("Alarme Workshop", driver.findElementByAccessibilityId("Marcador Alarme Workshop").getText());
        tirarPrintEImprimirNoLog("Confirmei que a marcador recebeu o nome de Alarme Workshop");

    }

    @Test
    public void excluirAlarme() throws IOException {
        iniciar();

        clicarNoBotaoAlarmeEValidarTelaDeAlarme("excluir alarme");

        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=' Alarme'][2]")).click();
        tirarPrintEImprimirNoLog("Clicar no Alarme Workshop");

        driver.findElementById("com.google.android.deskclock:id/delete").click();
        tirarPrintEImprimirNoLog("Clicar no botão para excluir Alarme Workshop");

        Assert.assertTrue(driver.findElementById("com.google.android.deskclock:id/snackbar_text").isDisplayed());
        Assert.assertEquals("Alarme excluído", driver.findElementById("com.google.android.deskclock:id/snackbar_text").getText());
        tirarPrintEImprimirNoLog("Validamos que o alarme foi excluído com sucesso");

    }

}
