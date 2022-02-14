package framework.base;

import enums.Resources;
import framework.utils.PropertiesUtility;
import framework.utils.DriverUtility;
import framework.utils.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BaseElement {
    protected By locator;
    protected String name;

    public BaseElement(By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public boolean isDisplayed() {
        LoggerUtility.info("Checking element interactivity:  " + name);
        try {
            this.waitForPresent();
        }
        catch (Exception e){
            return false;
        }
        return DriverUtility.findElements(this.locator).size() >0;
    }

    public void click() {
        this.waitForPresent();
        DriverUtility.findElement(this.locator).click();
    }

    public String getText() {
        LoggerUtility.info("Getting the text of an element: " + name);
        this.waitForPresent();
        return DriverUtility.findElement(this.locator).getText();
    }

    public String getAttribute(String name) {
        LoggerUtility.info("Displaying the text of an element: " + name);
        return DriverUtility.findElement(this.locator).getAttribute(name);
    }

    public void waitForPresent() {
        WebDriverWait wait = new WebDriverWait(DriverUtility.getDriver(), PropertiesUtility.getIntValue(Resources.CONFIG.toString(),"explicitTime"));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
