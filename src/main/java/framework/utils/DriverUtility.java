package framework.utils;

import enums.Resources;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class DriverUtility {
    private static WebDriver driver = null;
    private static ChromeOptions chromeOptions = new ChromeOptions();
    private static FirefoxOptions firefoxOptions = new FirefoxOptions();

    public static WebDriver getDriver(){
        return driver;
    }

    public static void setDriver() {
        if (driver == null)  {
            String browserName = PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"browserName");
            switch (browserName){
                case "Chrome":
                    try {
                        chromeOptions.addArguments(PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"windowSizeChrome"));
                        chromeOptions.addArguments(PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"browserLanguageChrome"));
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(chromeOptions);
                        break;
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        throw new RuntimeException();
                    }

                case "Firefox":
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setPreference(PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"profileLanguageFirefox"),
                            PropertiesUtility.getStringValue(Resources.CONFIG.toString(),"browserLanguageFirefox"));
                    firefoxOptions.setProfile(profile);
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver(firefoxOptions);
                    driver.manage().window().maximize();
                    break;
                default:
                    LoggerUtility.error("Wrong browser name!");
                    throw new RuntimeException();
            }
            driver.manage().timeouts().implicitlyWait(PropertiesUtility.getIntValue(Resources.CONFIG.toString(),"pauseTime"),
                    TimeUnit.MILLISECONDS);
        }
    }

    public static void scrolling(int x, int y ){
        try {
            LoggerUtility.info("Scroll form: ");
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("window.scrollBy("+ x + "," + y +")", "");
        }
        catch (Exception e){
            e.printStackTrace();
            LoggerUtility.error("Form scrolling failed");
            throw new RuntimeException();

        }

    }

    public static WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public static List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public static void quit(){
        LoggerUtility.info("Closing the browser.\n");
        driver.quit();
    }

    public static void get(String url){
        LoggerUtility.info("Opening a browser. Following a link");
        driver.get(url);
    }

    public static String getWindowHandle(){
        LoggerUtility.info("Getting a page handle");
        return driver.getWindowHandle();
    }

    public static Set<String> getWindowHandles(){
        LoggerUtility.info("Getting page descriptors");
        return driver.getWindowHandles();
    }

    public static String getCurrentUrl(){
        LoggerUtility.info("Get page URL");
        return driver.getCurrentUrl();
    }

    public static void close(){
        LoggerUtility.info("Closing the window.");
        driver.close();
    }

    public static void refresh(){
        LoggerUtility.info("Resetting the driver.");
        driver = null;
    }

    public static WebDriver.TargetLocator switchTo(){
        LoggerUtility.info("Switching");
        return driver.switchTo();
    }

    public static void switchToFrame(By locator){
        LoggerUtility.info("Switch to frame");
        driver.switchTo().frame(findElement(locator));
    }

    public static void switchToDefault(){
        switchTo().defaultContent();
    }
}
