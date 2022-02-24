package net.kicchi;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class TestRunner {
    private AppiumDriver<MobileElement> driver;

    @Test
    public void test(){
        try {
            //DesiredCapabilities will be used for identifying our target system
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            //we are testing an android device
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
            //our target android virtual device os version is 5.1 (it should match, be careful)
            desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "5.1");
            //the application under test is etsy.apk which we added to our project as a file
            desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+"\\etsy.apk");
            //the name of the target device (it should match, be careful)
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2API22");
            //defining command timeout
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);

            //define the appium server address - appium server is working on our local machine
            //add desiredCapabilities in order to communicate with the target mobile system over appium server
            driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
            Thread.sleep(3000);
            //locating an element from the etsy.apk
            WebElement getStarted = driver.findElement(By.xpath("//*[@text='Get Started']"));
            getStarted.click();
            Thread.sleep(5000);
            driver.closeApp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void calculator4x5() throws InterruptedException, MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, Platform.ANDROID);
        desiredCapabilities.setCapability(MobileCapabilityType.VERSION, "5.1");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel2API22");
        desiredCapabilities.setCapability("appPackage", "com.android.calculator2");
        desiredCapabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 20000);
        driver = new AppiumDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        Thread.sleep(3000);

        MobileElement digit4 = driver.findElementById("com.android.calculator2:id/digit_4");
        MobileElement times = driver.findElementByAccessibilityId("times");
        MobileElement digit5 = driver.findElementById("com.android.calculator2:id/digit_5");
        MobileElement equals = driver.findElementByAccessibilityId("equals");
        MobileElement result = driver.findElement(By.id("com.android.calculator2:id/result"));

        digit4.click(); times.click(); digit5.click(); equals.click();

        Assert.assertEquals("20", result.getText());

        driver.closeApp();
    }
}
