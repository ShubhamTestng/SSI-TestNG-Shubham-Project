package SSIAgroworlds;

import java.io.IOException;
import java.util.UUID;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Base.DocExecutiveBaseClass;
import POMpage.DocumentExecutive;
import utility.Utility;

public class DocumentExecutivePage extends DocExecutiveBaseClass{
	public Logger log = LogManager.getLogger(DocumentExecutivePage.class);
	DocumentExecutive documentExecutive = new DocumentExecutive(DocExecutivedriver);
	Utility utility = new Utility();
	ProcurementIRMPage procurementIRMPage = new ProcurementIRMPage();
	public static String filePath;
	public static String url;	
	public static String Contractid =null;


	@Test(priority = 1)
	public void DocExecutivelogin() throws InterruptedException, EncryptedDocumentException, IOException {

		Thread.sleep(3000);

		documentExecutive = new DocumentExecutive(DocExecutivedriver);

		documentExecutive.getEmail().sendKeys((utility.readExcelFile("SSICredential", 4, 0)));

		documentExecutive.getCountry().click();

		documentExecutive.getCountrycode().click();

		//utility.Dropdown(wait.until(ExpectedConditions.elementToBeClickable((procurementIRM.getCountry()))), "+91");

		documentExecutive.getPhone().sendKeys((utility.readExcelFile("SSICredential", 4, 1)));

		documentExecutive.getVerifyLogin().click();

		documentExecutive.getOTP().sendKeys("1234");

		documentExecutive.getOTPverify().click();


	}

	@Test(priority = 2)
	public void generateContract() throws InterruptedException {

		Thread.sleep(2000);

		documentExecutive.getContractManagement().click();

		documentExecutive.getContract().click();

		String tradeID = procurementIRMPage.tradeID;

		String tradeIDinContract = documentExecutive.getTradeIDinContract().getText();

		System.out.println(tradeIDinContract + "trade id in contract");

		if(tradeIDinContract.contains(tradeID)) {

			documentExecutive.getYetToCome().click();

		}


	}

	@Test(priority = 3)
	public void defineStageApprove() throws InterruptedException {

		Thread.sleep(2000);

		documentExecutive.getDefineStage().click();

		Actions actions = new Actions(DocExecutivedriver);

		actions.moveToElement(documentExecutive.getBllingDraftCheckbox()).click().build().perform();

		documentExecutive.getSelectStartEvent().click();

		documentExecutive.getETDdate().click();

		documentExecutive.getExtensionRequestTAT().click();

		documentExecutive.getTATinDAYS().click();

		documentExecutive.getPreshipmentNextButton().click();

		Thread.sleep(2000);

		//		for(int i=1 ; i<27 ;i++) {
		//		WebElement draftcheckbox = driver.findElement(By.xpath("//input[@name='contractSubStages["+(i)+"][]']"));
		//				
		//		draftcheckbox.click();
		//		
		//		}

		Actions action = new Actions(DocExecutivedriver);

		action.moveToElement(documentExecutive.getDraftCheckbox()).click().build().perform();

		List<WebElement> draftDocuments = documentExecutive.getDraftDocumentstartShipments();
		for (WebElement draftDocument : draftDocuments) {

			System.out.println(draftDocument + "and draftDocument are");

			draftDocument.click();
		}

		Thread.sleep(2000);

		for(int i=28; i<52; i++) {

			WebElement selecttat = DocExecutivedriver.findElement(By.xpath("//select[@name='contractSubStageTats["+(i)+"][]']"));

			Select tatdropdown = new Select(selecttat);

			tatdropdown.selectByValue("1");

		}

		for(int i=82; i<85; i++) {

			WebElement selecttat = DocExecutivedriver.findElement(By.xpath("//select[@name='contractSubStageTats["+(i)+"][]']"));

			Select tatdropdown = new Select(selecttat);

			tatdropdown.selectByValue("1");

		}

		Select billingofLading = new Select(documentExecutive.getBillofLadingstartEvent());

		billingofLading.selectByVisibleText("Vessel - Actual Time of Departure (ATD)");

		documentExecutive.getShipmentNextButton().click();

		documentExecutive.getPostshipmentNextButton().click();

	}


	@Test(priority = 4)
	public void tCdOCUMNET() throws InterruptedException{

		Thread.sleep(3000);

		DocExecutivedriver.navigate().back();

		documentExecutive.getYetToCome().click();

		documentExecutive.getTandC().click();

		documentExecutive.getOroginal().click();

		filePath = "C:\\Users\\wbox62\\Downloads\\amazonfiretab.png";

		documentExecutive.getUploadfileTanDc().sendKeys(filePath);

		documentExecutive.getOriginalUploadBUtton().click();
		
		log.info("T and c document uploaded");

		Thread.sleep(2000);


	}


	@Test(priority = 5)
	public void contractRegistration() throws InterruptedException {

		DocExecutivedriver.navigate().back();

		documentExecutive.getYetToCome().click();

		documentExecutive.getContractRegistration().click();
		
        url = DocExecutivedriver.getCurrentUrl();
		
		System.out.println("And current url is ...." + url );
		
		String[] parts = url.split("/");
	      
		Contractid = parts[parts.length - 1];

		String randomString = "Test Automation flow " + UUID.randomUUID().toString();
		documentExecutive.getContractNumber().sendKeys(randomString);
		
		//String randomMail = "shubham55@yopmail.com " + UUID.randomUUID().toString();
		documentExecutive.getBuyermailContract().sendKeys("shubham55@yopmail.com");
		
		documentExecutive.getContractstepNextButton1().click();
		
		documentExecutive.getSellermailContract().sendKeys("kulkarni55@yopmail.com");
		
		documentExecutive.getContractstepNextButton2().click();
		
		documentExecutive.getContractstepNextButton3().click();
		
		documentExecutive.getPercentage().sendKeys("50");
		
		documentExecutive.getAdvanceAmount().sendKeys("2000");
		
		documentExecutive.getContractstepNextButton4().click();
		
		documentExecutive.getQualityAssurance().sendKeys("50");

		documentExecutive.getInsurance().sendKeys("20");
		
		documentExecutive.getFreedays().sendKeys("30");
		
		utility.Dropdown(documentExecutive.getSelectPacking(), "25 KG PP BAGS");
		
		filePath = "C:\\Users\\wbox62\\Downloads\\activitylogscancellation.png";
		
		documentExecutive.getUploadPackingfile().sendKeys(filePath);
		
		documentExecutive.getPackingUploadButton().click();
		
		Thread.sleep(1000);
		
		documentExecutive.getContractstepNextButton5().click();
		
		documentExecutive.getDocumentRequiredShipment().click();
		
		documentExecutive.getSendToIRMButton().click();
		
		documentExecutive.getContractRegistrationApproveRequestRemarks().sendKeys("please approve the request");
		
		documentExecutive.getRemarksSendButton().click();
		
		log.info("contract registration approve request send");
	}


	//		for(int i=19; i<=44;i++) {
	////		WebElement select1 = driver.findElement(By.xpath("(//span[text()='Select'])["+(i+2)+"]"));
	////		select1.click();
	////		Thread.sleep(500);
	////		WebElement possibleEvent = driver.findElement(By.xpath("(//div[@class='stage-list bg-white br-10 px-3 py-2'])["+i+"]//span[text()='DRAFT BILL OF LADING (Draft)']"));
	////		possibleEvent.click();
	////		
	//		WebElement select2 = driver.findElement(By.xpath("(//span[text()='Select TAT'])["+(i+2)+"]"));
	//		Thread.sleep(500);
	//		WebElement extensionRequest = driver.findElement(By.xpath("(//span[text()='Select TAT'])["+(i-1)+"]"));
	//		extensionRequest.click();
	//		}
	//		




	//
	//		Thread.sleep(2000);
	//		//utility.Dropdown(documentExecutive.getBillingDraftText(),"Vessel - Actual Time of Departure (ATD)");
	//       
	//		List<WebElement> TAT = documentExecutive.getTATShipment();


	//		System.out.println(TAT + "AND TAT ARE ");
	//		
	//		for (WebElement tat : TAT) {
	//			
	//			tat.click();

	//			documentExecutive.getSelectTat().click();




}



