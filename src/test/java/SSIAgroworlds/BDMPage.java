package SSIAgroworlds;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import Base.BDMBaseClass;
import POMpage.BDM;
import utility.Utility;

public class BDMPage extends BDMBaseClass{

	BDM bdm = new BDM(BDMdriver);
	Utility utility = new Utility();
	TraderPage traderPage  = new TraderPage();
	public static String tradeIDNumber;
    public static String tradeID ;
    public static String readTrade;
    public static String SELLERid ;

	@Test(priority = 1)
	public void bdmlogin() throws InterruptedException, EncryptedDocumentException, IOException {

		Thread.sleep(3000);

		bdm = new BDM(BDMdriver);

		bdm.getEmail().sendKeys((utility.readExcelFile("SSICredential", 2, 0)));

		utility.Dropdown(bdm.getCountry(), "+91");

		bdm.getPhone().sendKeys((utility.readExcelFile("SSICredential", 2, 1)));

		bdm.getVerifyLogin().click();

		bdm.getOTP().sendKeys("1234");

		bdm.getOTPverify().click();


	}

	@Test(priority = 2)
	public void bdmApprove() throws InterruptedException {


		bdm.getTradeManagementDropdown().click();

		//bdm.getTrade().click();
		
		bdm.getTrade().sendKeys("sdfs");

		tradeID = bdm.getTradeId().getText();

		System.out.println(tradeID + "trade bdm is ");
 
		String[] parts = tradeID.split("/");
		tradeIDNumber = parts[parts.length - 1]; // This gets the last element of the array
		System.out.println(tradeIDNumber); 
		
		
		readTrade = traderPage.readTradeID();

		System.out.println(readTrade);

		if(tradeID.equalsIgnoreCase(readTrade)) {

			Thread.sleep(2000);

			bdm.getTradeId().click();

			Thread.sleep(2000);
		}

		//			
		//			String Expectstatus = createTrade.getUnderBuyerReview().getText();
		//			
		//	        String ActualStaus = "Under Buyer Review";
		//	        
		//	        Assert.assertEquals(ActualStaus, Expectstatus, "BDM Approve request");

		Actions action = new Actions(BDMdriver);

		action.moveToElement(bdm.getBDMtradeApproveButton())
		.click()
		.perform();

		log.info("approved from bdm");

	}


	@Test(priority = 3)
	public void sellerApprove() throws InterruptedException {

		BDMdriver.switchTo().newWindow(WindowType.WINDOW);

		utility.getYopmail(BDMdriver, "shubham6655@yopmail.com");

	}

	@Test(priority = 4)
	public void buyerApprove() throws InterruptedException {

		Thread.sleep(3000);
		
      //  driver.manage().deleteAllCookies();

		utility.getYopmail(BDMdriver,"kulkarni4488@yopmail.com");

	}

	@Test(priority = 2)
	public void Addendum() throws InterruptedException {
		
		bdm.getTradeManagementDropdown().click();

		bdm.getTrade().click();

		bdm.getSearchInTradeList().sendKeys(tradeID);
		
		if(tradeID.equalsIgnoreCase(readTrade)) {

		Thread.sleep(2000);
		
		bdm.getActionButtonINTradeList().click();
		
		WebElement addendumButton = BDMdriver.findElement(By.xpath("//a[@data-trade=\""+tradeIDNumber+"\" and @data-target=\"#modal-addendum\"]"));
		
		addendumButton.click();
		
		log.info("addendum button clicked");
		
		bdm.getAddendumRaiseReason().sendKeys("please raise adddendumn");
		
		bdm.getYesButtonAddendum().click();
		
		log.info("addendum approved");
				
		
		}
	}
	
	@Test(priority = 3)
	public void addendumTradeApprove() throws InterruptedException {
		
//		SELLERid = bdm.getSellerID().getText();
//		
//		bdm.getNotificationButton().click();
//		
//		BDMdriver.findElement(By.xpath("//p[contains(text(),'Anshita Trader requested  to review Trade "+tradeID+", "+SELLERid+"')]")).click();
//		
			bdm.getTradeManagementDropdown().click();

			bdm.getTrade().click();

			tradeID = bdm.getTradeId().getText();

			System.out.println(tradeID + "trade bdm is ");
	 
			String[] parts = tradeID.split("/");
			tradeIDNumber = parts[parts.length - 1]; // This gets the last element of the array
			System.out.println(tradeIDNumber); 
			
			
			readTrade = traderPage.readTradeID();

			System.out.println(readTrade);

			if(tradeID.equalsIgnoreCase(readTrade)) {

				Thread.sleep(2000);

				bdm.getTradeId().click();

				Thread.sleep(2000);
			}

			Actions action = new Actions(BDMdriver);

			action.moveToElement(bdm.getBDMtradeApproveButton())
			.click()
			.perform();

			log.info("approved from bdm");

		}


}
