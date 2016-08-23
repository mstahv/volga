package org.vaadin.it;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vaadin.addonhelpers.automated.VaadinConditions;
import org.vaadin.BasicUsageUI;

/**
 * A simple example that uses Selenium to do a browser level test for a
 * BasicJavaSCriptComponentUsageUI. For more complex tests, consider using page
 * object pattern.
 */
public class BasicIT extends AbstractWDIT {

    @Test
    public void testJavaScriptComponentWithBrowser() throws InterruptedException {

        startBrowser();

        driver.navigate().to(
                BASEURL + BasicUsageUI.class.getName());

        // Consider using Vaadin TestBench to make stuff easier
        new WebDriverWait(driver, 30).until(VaadinConditions.ajaxCallsCompleted());

        String pageSource = driver.getPageSource();

        verifyTwitterStuffOnPage(pageSource);
        
    }

    protected void verifyTwitterStuffOnPage(String pageSource) {
        Assert.assertTrue(pageSource.contains("name=\"twitter:card\""));
        Assert.assertTrue(pageSource.contains("name=\"twitter:image\""));
        Assert.assertTrue(pageSource.contains("name=\"twitter:title\""));
        Assert.assertTrue(pageSource.contains("name=\"twitter:description\""));
    }

}
