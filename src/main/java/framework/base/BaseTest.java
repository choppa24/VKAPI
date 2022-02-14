package framework.base;

import framework.utils.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {

    @BeforeTest
    public void initialization() {
    }

    @BeforeMethod
    public void setDriver() {
        DriverUtility.setDriver();
    }

    @AfterMethod
    public void quit(){
        LoggerUtility.info("Driver quit");
        DriverUtility.quit();
        DriverUtility.refresh();
    }
}
