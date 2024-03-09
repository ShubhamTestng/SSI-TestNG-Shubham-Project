package SSIAgroworlds;


import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import Base.TraderBaseClass;
import POMpage.CreateTrade;
import utility.Utility;

public class TraderPage extends TraderBaseClass {


	Utility utility = new Utility();
	CreateTrade createTrade;

	public static String readtrade = null;
	public static String SELLERid ;


	public Logger log = LogManager.getLogger(TraderPage.class);

	@Test(groups = {"sanity","Regression"}, priority = 1)
	public void traderlogin() throws EncryptedDocumentException, IOException {

		createTrade = new CreateTrade(Traderdriver);

		createTrade.getEmail().sendKeys((utility.readExcelFile("SSICredential", 1, 0)));

		createTrade.getCountry().click();

		createTrade.getCountrycode().click();
		//	utility.Dropdown(wait.until(ExpectedConditions.elementToBeClickable((createTrade.getCountry()))), "+91");

		createTrade.getPhone().sendKeys((utility.readExcelFile("SSICredential", 1, 1)));

		createTrade.getVerifyLogin().click();

		createTrade.getOTP().sendKeys("1234");

		createTrade.getOTPverify().click();

		log.info("login is clicked");


	}

	@Test(groups = {"sanity","Regression"}, priority = 2)
	public void homepage() {

		createTrade = new CreateTrade(Traderdriver);

		createTrade.getTradeDropdown().click();

		createTrade.getCreateTrade().click();
	}

	@Test(groups = { "sanity", "Regression" },priority = 3)
	public void TradeDetails() throws InterruptedException {

		createTrade = new CreateTrade(Traderdriver);

		utility.Dropdown(createTrade.getTradetype(),"BROKERAGE");

		createTrade.getTradeDate().sendKeys("13/09/2023");

		utility.Dropdown(createTrade.getCountrySelect(),"Iran");

		utility.Dropdown(createTrade.getPayterm(), "15% ADV AND 85% DP AT SIGHT");

		utility.Dropdown(createTrade.getContainerLoad(), "LCL");

		createTrade.getTradeNextButton().click();

		log.info("trade details filled");

	}


	@Test(priority = 4)
	public void shipmentDetails() {


		createTrade = new CreateTrade(Traderdriver);

		createTrade.getFromDate().sendKeys("10/12/2023");

		createTrade.getToDate().sendKeys("01/01/2024");

		utility.Dropdown(createTrade.getIncotern(), "EXW (Ex Works)");

		utility.Dropdown(createTrade.getPortLoading(), "QASIM, PAKISTAN");

		utility.Dropdown(createTrade.getPortDischarge(), "TIANJIN/XINGANG, CHINA");

		utility.Dropdown(createTrade.getContractissuer(), "Buyer");

		createTrade.getShipmentNextButton().click();

		log.info("shipment details filled");


	}

	@Test(priority = 5)
	public void productDetails() throws InterruptedException {

		createTrade = new CreateTrade(Traderdriver);

		utility.Dropdown(createTrade.getCategory(), "OIL AND OILSEEDS");

		Thread.sleep(3000);

		utility.Dropdown(wait.until(ExpectedConditions.elementToBeClickable((createTrade.getProduct()))), "SOYABEAN");

		createTrade.getBookedQuantity().sendKeys("1000");

		createTrade.getProductRate().sendKeys("350");

		createTrade.getOrigin().click();

		createTrade.getCountryName().click();

		Thread.sleep(3000);

		utility.Dropdown(createTrade.getContainerType(), "Open side storage container");

		utility.Dropdown(createTrade.getContainerSize(), "40'");

		createTrade.getNoOfContainers().sendKeys("200");

		createTrade.getAddSpecialisation().click();

		utility.Dropdown(wait.until(ExpectedConditions.elementToBeClickable((createTrade.getSpecilisation()))), "GREEN SEEDS");

		utility.Dropdown(createTrade.getUnit(), "CM");

		createTrade.getUnivalue().sendKeys("25");

		createTrade.getAddbutton().click();

		createTrade.getProductnextButton().click();

		log.info("product details filled");

	}


	@Test(priority = 6)
	public void sellerDetails() throws InterruptedException {

		Thread.sleep(2000);

		createTrade = new CreateTrade(Traderdriver);

		utility.Dropdown(createTrade.getSeller(), "AFRI VENTURES FZE");

		createTrade.getSearchMail().clear();

		createTrade.getSearchMail().sendKeys("shubham6655@yopmail.com");

		utility.Dropdown(createTrade.getSellerCommision(), "%");

		createTrade.getSellercommisionInput().sendKeys("65");
		 
		createTrade.getSellercommisionInput().clear();

		createTrade.getSellercommisionInput().sendKeys("65");

		if(createTrade.getSellerccMail().isDisplayed()) {
			createTrade.getSellerccMail().sendKeys("shubham3395@yopmail.com");

			System.out.println("email filled");
		}

		else if(createTrade.getSellerccMail2().isDisplayed()) {
			createTrade.getSellerccMail2().clear();
			createTrade.getSellerccMail2().sendKeys("shubham3395@yopmail.com");

			System.out.println("new email filled");

		}

		else {

			System.out.println("nothing is displayed");
		}
		
		createTrade.getSellerNextButton().click();

		log.info("seller details filled");

	}


	@Test(priority = 7)
	public void buyerDetails() throws InterruptedException {

		createTrade = new CreateTrade(Traderdriver);

		utility.Dropdown(createTrade.getBuyer(), "TRISTAR");

		createTrade.getSearchBuyermail().clear();

		createTrade.getSearchBuyermail().sendKeys("kulkarni4488@yopmail.com");

		createTrade.getBuyerCCMail().sendKeys("kulkarni1133@yopmail.com");

		createTrade.getBuyerCCMail().clear();

		createTrade.getBuyerCCMail().sendKeys("kulkarni1133@yopmail.com");

		Thread.sleep(3000);

		createTrade.getBuyernextButton().click();

		Thread.sleep(4000);


	}
	//	   
	//	   
	@Test(priority = 8)
	public void sendReviewTrade() throws InterruptedException {

		createTrade = new CreateTrade(Traderdriver);

		createTrade.getAddTrade().click();

		utility.Dropdown(wait.until(ExpectedConditions.elementToBeClickable((createTrade.getReviewForBDM()))), "Mayank BDM");

		createTrade.getSendTrade().click();


		//String Expected = "https://stage.agroworlds.com/trade-list";

		//Assert.assertEquals(driver.getCurrentUrl(), Expected, "URL are equal , test is passed");

	}

	@Test(priority = 9)
	public String readTradeID() {

		createTrade = new CreateTrade(Traderdriver);

		readtrade = createTrade.getReadtrade().getText();

		System.out.println(readtrade + "read trade id ");

		return readtrade;
	}

	@Test(priority = 2)
	public void CreateAddendum() throws InterruptedException {
		log.info("inside CreateAddendum method ......");
		createTrade.getTradeDropdown().click();

		createTrade.getTrade().click();

		Thread.sleep(4000);
		SELLERid = createTrade.getSellerID().getText();

		System.out.println("seller id is " + SELLERid);

		createTrade.getNotificationButton().click();

		log.info("notification clicked");

		Traderdriver.findElement(By.xpath("//p[contains(text(),'Mayank BDM has raised the Addendum request for Trade " + readtrade + ", " + SELLERid + "')]")).click();

		log.info("addendum request clicked ....");

		createTrade.getApproveAddendumButton().click();

		createTrade.getAddendumRemarks().sendKeys("approved");

		createTrade.getAddendumApproveAfterRemarksButton().click();

		createTrade.getCreateAddendumTradeButton().click();

		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void AddendumDeatailsFilled() throws InterruptedException {

		createTrade.getTradeNextButton().click();

		utility.Dropdown(createTrade.getIncotern(), "FAS (free of charge besides the ship)");

		utility.Dropdown(createTrade.getPortLoading(), "COTONOU, BENIN");

		utility.Dropdown(createTrade.getPortDischarge(), "KARACHI, PAKISTAN");

		utility.Dropdown(createTrade.getContractissuer(), "Seller");
		
		Thread.sleep(2000);

		createTrade.getShipmentNextButton().click();

		log.info("shipment details filled");

		createTrade.getProductnextButton().click();

		sellerDetails();

		buyerDetails();

		sendReviewTrade();

	}
}

