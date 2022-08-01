package br.com.liandro.page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    AppiumDriver<MobileElement> driver;
    private AlarmePageObject alarmePageObject;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public AlarmePageObject getAlarmePageObject() {
        if(this.alarmePageObject == null) {
            this.alarmePageObject = new AlarmePageObject(driver);
        }
        return this.alarmePageObject;
    }

}
