package com.epam.AmazonPageTask;



import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.pageObjectClasses.HomePage;

import com.epam.pageObjectClasses.VideoPage;





/**
 * @author Pavan_Kotha
 *
 */
public class TestFunction {
    static VideoPage videoPage;
    static HomePage homePage;
    static ChromeDriver driver;
   

//   
   @Test(priority = 1)
    public void Scenario1Test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sravanthi_rudhru\\Desktop\\chromedriver.exe");
        ChromeOptions chromeOptions= new ChromeOptions();
        chromeOptions.setBinary("C:\\Users\\sravanthi_rudhru\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
         homePage = new HomePage(driver);
         homePage.open("https://www.youtube.com/results?search_query=EPAM");
         jse.executeScript("window.scrollBy(0, 1000)");
         jse.executeScript("window.scrollBy(0,1000)");
         jse.executeScript("window.scrollBy(0,1000)");
         
         Assert.assertEquals(17000.0, homePage.get2ndHighViews()); 

    }
    @Test(priority = 2)
    public void Scenario2Test() throws InterruptedException {
       
       Assert.assertEquals("0:38", homePage.getShortestDuration()); 


    }
    @Test(priority = 3)
    public void Scenario4Test() throws InterruptedException {
    	
          
    	videoPage =  homePage.clickOnHighViews();
    	int exepected[] = {88,8};
        Assert.assertEquals(88, videoPage.getLikesAndDislikes()[0]); 
        Assert.assertEquals(8, videoPage.getLikesAndDislikes()[1]); 
        Assert.assertEquals("2.12K subscribers", videoPage.getSubscribers());



    }
//    @Test(priority = 3)
//    public void dloginTest() throws InterruptedException {
//    	
//          
//    	homePage.clickUploadDate();
//    	String title=homePage.getfirstVideoTitle();
//    	String views=homePage.getfirstVideoViews();
//    	System.out.println("Latest vedio title: "+title);
//    	
//    	System.out.println("Latest vedio views: "+views);
//    }

//    @AfterClass
//    public static void end() {
//        driver.quit();
//    }
}
