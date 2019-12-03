package com.epam.pageObjectClasses;


import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * @author Pavan_Kotha
 *
 */
public class HomePage extends Page {
	
	 
    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    @FindBy(xpath = "//div[@class = 'text-wrapper style-scope ytd-video-renderer']//div[@id = 'metadata-line']//span")
    private WebElement views[];
    /**
     * opens the webpage.
     */

    public void open(String URL) {
    	getDriver().get(URL);
    	
    }
//    public LoginPage clickLoginButton () {
//    	loginButton.click();
//        return new LoginPage(driver);
//    };
    public double get2ndHighViews() {
    	List<WebElement> views = driver.findElements(By.xpath("//div[@class = 'text-wrapper style-scope ytd-video-renderer']//div[@id = 'metadata-line']//span"));
    	TreeSet<Double> set = new TreeSet<Double>();
    	int counter =0;
    	double returnValue = 0;
    	for (WebElement element : views) {
    		 if (counter  > 25) {
    			 break;
    		 }
    		if(counter%2 == 0  ) {
    			String[] s = element.getText().split("\\s");
    			if (s[0].contains("K")) {
    				String[] s1 = s[0].split("K");
    				double value  = Double.parseDouble(s1[0]);
    				set.add(value*1000);
    			} else set.add(Double.parseDouble(s[0]));
    		}
    		 counter++;
    		
    	}
    	int counter1 = 0;
    	for (double d : set) {
    		counter1++;
    		if (counter1 == set.size()-1) {
    			returnValue = d;
    		}
    	}
        return returnValue;

    }
    public VideoPage clickOnHighViews() {
    	List<WebElement> views = driver.findElements(By.xpath("//div[@class = 'text-wrapper style-scope ytd-video-renderer']//div[@id = 'metadata-line']//span"));
    	TreeMap<Double, WebElement> map = new TreeMap<Double, WebElement>();
    	int counter =0;
    	for (WebElement element : views) {
    		 if (counter  > 25) {
    			 break;
    		 }
    		if(counter%2 == 0  ) {
    			String[] s = element.getText().split("\\s");
    			if (s[0].contains("K")) {
    				String[] s1 = s[0].split("K");
    				double value  = Double.parseDouble(s1[0]);
    				map.put(value*1000, element);
    			} else map.put(Double.parseDouble(s[0]), element);
    		}
    		 counter++;
    		
    	}
    	globalWait().until(ExpectedConditions.elementToBeClickable(map.get(map.lastKey())));

    	getActions().moveToElement(map.get(map.lastKey())).click().build().perform();

//    	map.get(map.lastKey()).click();
    	
    	
        return new VideoPage(driver);

    }
     public String getShortestDuration() throws InterruptedException {
    	Thread.sleep(10000);
    	List<WebElement> videos = driver.findElements(By.xpath("//span[@class = 'style-scope ytd-thumbnail-overlay-time-status-renderer']"));
    	TreeSet<Double> set = new TreeSet<Double>();
     	int max = Integer.MAX_VALUE, time;
     	String returnValue = "";
     	int counter = 0;
     	for(WebElement element : videos) {
     		counter++;
     		if(counter > 25) {
     			break;
     		}
			String[] s = element.getText().split(":");
			if (s.length == 2) {
				
				time = Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
				if(time < max) {
					max = time;
					returnValue = element.getText();
				}
			} else if(s.length == 3) {
				time = Integer.parseInt(s[0])*3600 + Integer.parseInt(s[1])*60 + Integer.parseInt(s[2]);
				System.out.println(time);
				if(time < max) {
					max = time;
					returnValue = element.getText();
				}
			}
     	}
     	return returnValue;
     }
     public String getLatestUploadedVideo() {
         List<WebElement> video = driver.findElements(By.xpath("//ytm-compact-video-renderer//h4//span"));
         int counter = 0;
         TreeMap<Double, WebElement> set = new TreeMap<Double, WebElement>();
         double y=0,m=0,d=0;
         for(WebElement element :video){
                counter++;
                if(counter > 25) break;
               String[] s = element.getAttribute("aria-label").split("\\s");
             for(int i=0; i< s.length;i++) {
                    if(s[i].equals("years") || s[i].equals("year")) {
                          System.out.println( Double.parseDouble(s[i-1]));
                          y = Double.parseDouble(s[i-1]);
                    }
                    else if(s[i].equals("months") || s[i].equals("month")) {
                          System.out.println(Double.parseDouble(s[i-1]));
                          m = Double.parseDouble(s[i-1]);
                    }
                    else if(s[i].equals("days") || s[i].equals("day")) {
                          System.out.println(Double.parseDouble(s[i-1]));
                          d = Double.parseDouble(s[i-1]);
                    }
                    System.out.println(y*365 + m*30+d);
                    set.put(y*365 + m*30+d,element);
             }
             
         }
         return set.get(set.lastKey()).getAttribute("aria-label");
     }
     //############################################
     
     @FindBy(xpath = "//*[@id= 'button' and contains(@aria-label, 'Search filters')]")
     private WebElement filterButton;
     @FindBy(xpath = "//*[@title= 'Search for Today' and @id='label']")
     private WebElement today;
     @FindBy(xpath = "//*[@title= 'Search for Today' and @id='label']//yt-icon")
     private WebElement todayClose;

     @FindBy(xpath = "//*[@title= 'Sort by upload date' and @id='label']")
     private WebElement uploadDate;

     @FindBy(xpath = "//div[@id='contents']//h3//a")
     private WebElement firstVideoTitle;

     @FindBy(xpath = "//div[@id='contents']//ytd-video-meta-block//span")
     private WebElement firstVideoViews;

/**
  * @param driver contains driver element
  */


public void clickUploadDate(){
	globalWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id= 'button' and contains(@aria-label, 'Search filters')]")));

     filterButton.click();
     globalWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title= 'Search for Today' and @id='label']")));

     uploadDate.click();
}

public String getfirstVideoTitle(){
     String title=firstVideoTitle.getAttribute("title");
     return title;
}

public String getfirstVideoViews(){
     String views=firstVideoTitle.getAttribute("text");
     return views;
}


public void closeToday(){
     filterButton.click();
     globalWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@title= 'Search for Today' and @id='label']//yt-icon")));
     todayClose.click();
}


}
