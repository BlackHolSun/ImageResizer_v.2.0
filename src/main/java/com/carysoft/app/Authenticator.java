package com.carysoft.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Authenticator {


    public List<WebElement> getImageElements(String url) throws InterruptedException, IOException {


        /**Open Chrome browser*/
        WebDriver driver = createChromeDriver();

        driver.get(url);

        Thread.sleep(3000);


        /** Switch to an active screen */
        String winHandleBefore = driver.getWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }


        System.out.println("Press Enter to continue \n");

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<WebElement> images = driver.findElements(By.cssSelector("img"));

        return images;

    }


    private WebDriver createChromeDriver() throws IOException {
        //creating a Chrome driver to open Chrome browser
        Path tempDir = Files.createTempDirectory("");
        String os = System.getProperty("os.name").toLowerCase();
        String fileName = (os.contains("mac")) ? "chromedriver" : "chromedriver.exe";

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream(fileName);
        File chromeDriver = new File(tempDir + File.separator + fileName);


        chromeDriver.createNewFile();
        org.apache.commons.io.FileUtils.copyInputStreamToFile(is, chromeDriver);

        //give permissions to be executable - for UNIX
        chromeDriver.setExecutable(true);
        chromeDriver.setReadable(true);
        //chromeDriver.setWritable(true);

        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());

        return new ChromeDriver();
    }
}
