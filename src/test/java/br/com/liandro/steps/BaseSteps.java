package br.com.liandro.steps;

import br.com.liandro.page.AlarmePageObject;
import br.com.liandro.page.BasePage;
import br.com.liandro.utils.Utils;

import java.net.MalformedURLException;

public class BaseSteps {

    private Utils utils = new Utils();
    BasePage basePage;
    AlarmePageObject alarmePageObject;

    public BaseSteps() throws MalformedURLException {
        utils.iniciar();
        this.basePage = new BasePage(utils.driver);
        this.alarmePageObject = basePage.getAlarmePageObject();
    }
}
