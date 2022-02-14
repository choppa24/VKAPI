package pages;

import elements.Button;
import elements.Label;
import enums.Resources;
import framework.base.BaseForm;
import framework.utils.PropertiesUtility;
import framework.utils.DriverUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class NewsPage extends BaseForm {
    private final static By pageLocator = By.id("post_field");

    private final Button myPageButton = new Button(By.xpath("//li[@id='l_pr']//span[contains(@class,'left_label')]"), "myPageButton");

    public NewsPage(){
        super(new Label(pageLocator, "uniqueElement from NewsPage"),"NewsPage");
    }

    public void clickMyPageButton(){
        Actions actions =new Actions(DriverUtility.getDriver());
        actions.pause(PropertiesUtility.getIntValue(Resources.CONFIG.toString(),"pauseTime"));
        actions.perform();
        myPageButton.click();
    }
}
