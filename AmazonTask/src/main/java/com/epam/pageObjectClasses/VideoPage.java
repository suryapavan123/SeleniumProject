package com.epam.pageObjectClasses;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class VideoPage extends Page{
    /**
     * login button web element
     */
  
    @FindBy(id = "identifierId")
    private WebElement emailID;
    
    @FindBy(id = "identifierNext")
    private WebElement nextButton;
    private String password = "//*[@id = 'password']//input[@aria-label= 'Enter your password']"; 
    private String pwdNext = "passwordNext";
    /**
     * @param driver contains driver element
     */
    public VideoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
     public int[] getLikesAndDislikes() {
    	globalWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id = 'text' and contains(@aria-label, 'likes')]")));
    	List<WebElement> elements = driver.findElements(By.xpath("//*[@id = 'text' and contains(@aria-label, 'likes')]"));
    	 int[] array = new int[3];
    	 array[0] = Integer.parseInt(elements.get(0).getText());
    	 array[1] = Integer.parseInt(elements.get(1).getText());
    	 return array;

     }
     public String getSubscribers() {
    	 WebElement element = driver.findElement(By.xpath(" //*[@id = 'owner-sub-count']"));
    	 return element.getText();
     }
   
      
}
