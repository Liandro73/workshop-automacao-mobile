package br.com.liandro.steps;

import br.com.liandro.page.AlarmePageObject;
import br.com.liandro.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.net.MalformedURLException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlarmeStepsTest {

    private Utils utils = new Utils();

    @Before
    public void iniciar() throws MalformedURLException {
        utils.iniciar();
    }

    @After
    public void finalizar() {
        utils.driver.quit();
    }

    @Test
    public void adicionarUmAlarme() throws IOException {

        AlarmePageObject alarmePageObject = new AlarmePageObject(utils.driver);

        alarmePageObject.clicarNoBotaoAlarmeEValidarTelaDeAlarme("adicionar um alarme");

        alarmePageObject.clicarEmAdicionarAlarme();

        alarmePageObject.clicarEmAlternarModoDeEntrada();

        alarmePageObject.verificarQueDialogFoiExibida();

        alarmePageObject.preencherCampoHoras("10");
        alarmePageObject.preencherCampoMinutos("00");

        alarmePageObject.clicarNoBotaoOkEEvidenciar("ao completar envio de valores ao alarme");

        alarmePageObject.verificarAlarmeEHorario("ao completar alarme");

        alarmePageObject.clicarNoBotaoRepetirLigadoOuDesligado();

        alarmePageObject.clicarNoBotaoAdicionarMarcador();

        alarmePageObject.verificarSeMarcadorFoiExibido();

        alarmePageObject.preencherCampoMarcador("Alarme Workshop");

        alarmePageObject.clicarNoBotaoOkEEvidenciar("ao finalizar cadastro de alarme");

        alarmePageObject.clicarNoBotaoRecolherAlarme();

        alarmePageObject.verificarAlarmeEHorario(" ao finalizar inclusão do alarme");

        alarmePageObject.verificarSeMarcadorRecebeuNomeDefinido();

    }

    //@Test
//    public void excluirAlarme() throws IOException {
//        clicarNoBotaoAlarmeEValidarTelaDeAlarme("excluir alarme");
//
//        driver.findElementByAccessibilityId("Marcador Alarme Workshop").click();
//        tirarPrintEImprimirNoLog("Clicar no Alarme Workshop");
//
//        driver.findElementById("com.google.android.deskclock:id/delete").click();
//        tirarPrintEImprimirNoLog("Clicar no botão para excluir Alarme Workshop");
//
//        Assert.assertTrue(driver.findElementById("com.google.android.deskclock:id/snackbar_text").isDisplayed());
//        Assert.assertEquals("Alarme excluído", driver.findElementById("com.google.android.deskclock:id/snackbar_text").getText());
//        tirarPrintEImprimirNoLog("Validamos que o alarme foi excluído com sucesso");
//
//    }

}
