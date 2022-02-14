package pages;

import elements.Button;
import elements.Input;
import elements.Label;
import framework.base.BaseForm;
import framework.utils.LoggerUtility;
import org.openqa.selenium.By;

public class SignInPage extends BaseForm {
    private final static By pageLocator = By.xpath("//div[@class='login_mobile_header']");
    private final Input loginInput = new Input(By.id("index_email"), "loginInput" );
    private final Input passwordInput = new Input(By.id("index_pass"), "passwordInput" );
    private final Button loginButton = new Button(By.id("index_login_button"), "loginButton");

    public SignInPage(){
        super(new Label(pageLocator, "uniqueElement from SignInPage"), "SingInPage");
    }

    public void writeLogin(String login){
        LoggerUtility.info("Ввод логина");
        loginInput.sendStringKey(login);
    }

    public void writePassword(String password){
        LoggerUtility.info("Ввод пароля");
        passwordInput.sendStringKey(password);
    }

    public void clickLoginButton(){
        loginButton.click();
    }
}
