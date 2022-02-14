package elements;

import framework.base.BaseElement;
import framework.utils.DriverUtility;
import framework.utils.LoggerUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Input extends BaseElement {

    public Input(By locator, String name) {
        super(locator, name);
    }

    public void sendStringKey(String string){
        LoggerUtility.info("Entering text in a field: " + name);
        waitForPresent();
        DriverUtility.findElement(this.locator).sendKeys(string);
    }

    public void sendStringKey(Keys keys){
        LoggerUtility.info("Entering text in a field: " + name);
        waitForPresent();
        clear();
        DriverUtility.findElement(this.locator).sendKeys(keys);
    }

    public void clear() {
        LoggerUtility.info("Clearing the field: " + name);
        waitForPresent();
        DriverUtility.findElement(this.locator).clear();
    }


}
