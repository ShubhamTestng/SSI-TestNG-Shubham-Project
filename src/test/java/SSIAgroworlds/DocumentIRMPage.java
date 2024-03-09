package SSIAgroworlds;

import static org.testng.Assert.expectThrows;

import java.awt.RenderingHints.Key;
import java.io.IOException;

import org.apache.batik.apps.rasterizer.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import Base.DocIRMBaseClass;
import POMpage.DocumentIRM;
import utility.Utility;

public class DocumentIRMPage extends DocIRMBaseClass {
	public Logger log = LogManager.getLogger(DocumentIRMPage.class);
	TraderPage traderPage  = new TraderPage();
	DocumentIRM documentIRM = new DocumentIRM(DocIRMdriver);
	ProcurementIRMPage procurementIRMPage = new ProcurementIRMPage();
	Utility utility = new Utility();
	public static String currentWindowHandle ;
	public static String tradeID;
	public static String filePath;

	public static String Lot1;



	public void ReusableContractMethod() {

		documentIRM.getContractManagement().click();

		documentIRM.getContract().click();

	}

	@Test(priority = 1)
	public void DocIRMlogin() throws InterruptedException, EncryptedDocumentException, IOException {

		Thread.sleep(3000);

		log.info("sucessfully logged in");

		documentIRM = new DocumentIRM(DocIRMdriver);

		documentIRM.getEmail().sendKeys((utility.readExcelFile("SSICredential", 3, 0)));

		documentIRM.getCountry().click();

		documentIRM.getCountrycode().click();

		//utility.Dropdown(wait.until(ExpectedConditions.elementToBeClickable((procurementIRM.getCountry()))), "+91");

		documentIRM.getPhone().sendKeys((utility.readExcelFile("SSICredential", 3, 1)));

		documentIRM.getVerifyLogin().click();

		documentIRM.getOTP().sendKeys("1234");

		documentIRM.getOTPverify().click();


	}


	@Test(priority = 2)
	public void createContract() throws InterruptedException {

		log.info("sucessfully in contratc page");

		documentIRM.getContractManagementModule().click();

		Thread.sleep(2000);

		documentIRM.getContractManagement().click();

		documentIRM.getContract().click();
	}


	@Test(priority = 3)
	public void generateContract() throws InterruptedException {
		tradeID = procurementIRMPage.tradeID;

		//documentIRM.getContractSearch().sendKeys(tradeID);

		documentIRM.getContractSearch().sendKeys(tradeID);

		utility.Dropdown(documentIRM.getTradeStatus(), "Assign To Team");

		documentIRM.getAssignToTeam().click();

		Select dropdown = new Select(wait.until(ExpectedConditions.visibilityOf(documentIRM.getSelectOpeartiveExecutiveDropdown())));

		dropdown.selectByValue("629");

		documentIRM.getContractAssignButton().click();

		Thread.sleep(2000);

		documentIRM.getClearFilter().click();

		//documentIRM.getClearFilter().click();

		String tradeIDinContract = documentIRM.getTradeIDinContract().getText();

		System.out.println(tradeIDinContract + "trade id in contract");

		if(tradeIDinContract.contains(tradeID)) {

			documentIRM.getGenerateContract().click();

		}

		//		Thread.sleep(2000);
		//		DocIRMdriver.findElement(By.xpath("/html/body/div[3]/nav/ul[2]/li[3]/a")).click();
		//
		//		DocIRMdriver.findElement(By.xpath("/html/body/div[3]/nav/ul[2]/li[3]/ul[1]/li/a")).click();
		//		Thread.sleep(2000);
		//
		//		currentWindowHandle = DocIRMdriver.getWindowHandle();
		//		
		//		System.out.println(currentWindowHandle);
		//
		//		log.info("sucessfully logged out");


	}

	@Test(priority = 3)
	public void approvalAccept() throws InterruptedException, EncryptedDocumentException, IOException {

		String tradeID = procurementIRMPage.tradeID;

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		documentIRM.getContractSearch().sendKeys(tradeID);

		String tradeIDinContract = documentIRM.getTradeIDinContract().getText();

		String[] words = tradeIDinContract.split("/");

		String numberTradeiNaCTION = words[words.length - 1];

		System.out.println(numberTradeiNaCTION);

		log.info("trade id found in contract while define stage");


		System.out.println(tradeIDinContract + "trade id in contract while define stage approval");

		if(tradeIDinContract.contains(tradeID)) {

			documentIRM.getYetToCome().click();

			System.out.println(tradeID + "and trade is ");
			log.info("get clicked ");

		}

		documentIRM.getDefineStage().click();

		Thread.sleep(3000);

		documentIRM.getPreshipmentNextApprove().click();

		documentIRM.getShipmentNextApprove().click();

		documentIRM.getApproveButton().click();

		documentIRM.getDefineStageApprovalRemarks().sendKeys("Please Approve Define stage");

		documentIRM.getYesButton().click();

		Thread.sleep(1000);

		ReusableContractMethod();

		Thread.sleep(1000);

		System.out.println(tradeID +"and trade is in contract is ......");

		documentIRM.getContractSearch().clear();

		documentIRM.getContractSearch().sendKeys(tradeID);
		Thread.sleep(1000);

		documentIRM.getDropDownContractButton().click();

		Thread.sleep(2000);

		documentIRM.getViewContractRegistration().click();
		
		Thread.sleep(2000);

		documentIRM.getContractstepNextButton1().click();

		documentIRM.getContractstepNextButton2().click();

		documentIRM.getContractstepNextButton3().click();

		documentIRM.getContractstepNextButton4().click();

		documentIRM.getContractstepNextButton5().click();

		documentIRM.getContractRegistrationApproved().click();

		Thread.sleep(2000);

		ReusableContractMethod();

	}

	@Test(priority = 4)
	public void manageLots() throws InterruptedException {

		Thread.sleep(2000);

		Actions act = new Actions(DocIRMdriver);
		act.moveToElement(documentIRM.getDropDownContractButton(),0, 17)
		.click().perform();

		System.out.println("dropdwon are clickable ");
		//	     act.moveByOffset(documentIRM.getDropDownContractButton(),0, 17);

		//Thread.sleep(1000);

		//WebElement manageLots = wait.until(ExpectedConditions.elementToBeClickable(documentIRM.getManageLots()));

		//manageLots.click();
		documentIRM.getManageLots().click();

		System.out.println("manage lots are clickable ");

		documentIRM.getAddLotButton().click();

		String balanceQuantity = documentIRM.getBalanceQuanity().getText();
		double balance = Double.parseDouble(balanceQuantity);

		Double balanceLot1 = (balance)/2;

		Lot1 = String.valueOf(balanceLot1);

		Actions acts = new Actions(DocIRMdriver);
		acts.moveToElement(documentIRM.getQuanity());

		documentIRM.getQuanity().sendKeys("" + (balance/2));

		documentIRM.getBillNo().sendKeys("" + (balance/2));

		documentIRM.getShippingLine().sendKeys("https://www");

		utility.Dropdown(documentIRM.getSelectOriginLots(), "SUDAN");

		Thread.sleep(2000);

		documentIRM.getSaveButton().click();
		Thread.sleep(8000);

		ReusableContractMethod();

	}

	@Test(priority = 5)
	public void contractExecution() throws InterruptedException {
		Thread.sleep(2000);

		documentIRM.getContractSearch().clear();

		log.info(tradeID + "tradeID is n contract execution");
		documentIRM.getContractSearch().sendKeys(tradeID);
		log.info(tradeID + "tradeID is n contract execution after clicking ");

		Thread.sleep(2000);

		documentIRM.getDropDownContractButton().click();

		Thread.sleep(3000);
		log.info("dropdown clicking ");

		documentIRM.getContractExecution().click();
		//	    Actions action = new Actions(DocIRMdriver);
		//	    action.moveToElement(documentIRM.getContractExecution(),110,30)
		//	     .click().perform();
		log.info("getContractExecution is n contract execution after clicking ");

		Thread.sleep(2000);

		documentIRM.getPreshipmentCompletedButton().click();
		Thread.sleep(2000);

		documentIRM.getSelectAllButton().click();

		documentIRM.getPreshipmentSaveButton().click();

		Select lotDropdown = new Select(documentIRM.getSelectLotsFromDropdown());

		//String balanceQunaity = "- BL: 40";

		for (WebElement option : lotDropdown.getOptions()) {
			if (option.getText().contains(Lot1)) {
				option.click();
				break;
			}
		}

		log.info("lot get selected");
		documentIRM.getShipmentTab().click();

		documentIRM.getShipmentDocumentExcutionButton().click();
		documentIRM.getPreshipmentNextButton().click();

		Thread.sleep(1000);
		documentIRM.getShipmentUploadButton().click();

		filePath = "C:\\Users\\wbox62\\Downloads\\activitylogscancellation.png";

		documentIRM.getAddFileButton().sendKeys(filePath);
		Thread.sleep(1000);
		documentIRM.getShipmentfileUploadButton().click();

		documentIRM.getBuyerCheckbOXinUploadDocumnet().click();
		Thread.sleep(1000);
		documentIRM.getSellerCheckbOXinUploadDocumnet().click();
		Thread.sleep(1000);
		documentIRM.getBuyersellerUploadButton().click();
		Thread.sleep(1000);
		documentIRM.getShipmentNextButton().click();
		Thread.sleep(1000);
		documentIRM.getPostshipmentButton().click();
		documentIRM.getPostShipmetSaveButton().click();

		ReusableContractMethod();
		documentIRM.getContractSearch().clear();

		log.info(tradeID + "tradeID is n contract execution is get cleared ............");
		documentIRM.getContractSearch().sendKeys(tradeID);
		log.info(tradeID + "tradeID is n contract execution has been send................ ");

		Thread.sleep(2000);

		documentIRM.getDropDownContractButton().click();

		System.out.println("dropdown clicked ......");

		Thread.sleep(2000);

		//	documentIRM.getContractExecution().click(); ---->>>> This x path is not working becuase it showing single at this time 
		documentIRM.getContractExecution().click();
		//documentIRM.getContractExecutionAfterPostshipmentSaveDocumnet().click();

		System.out.println("execution clicked ......");

		Select lotDropdown2 = new Select(documentIRM.getSelectLotsFromDropdown());

		for (WebElement option : lotDropdown2.getOptions()) {
			if (option.getText().contains(Lot1)) {
				option.click();
				break;
			}
		}

		documentIRM.getShipmentTab().click();

		// Assuming you've set the implicit wait to a low value or not at all.
		// DocIRMdriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Example if using implicit waits minimally



		for (int i = 2; i < 28; i++) {

			Thread.sleep(1000);
			WebElement drafttext = DocIRMdriver.findElement(By.xpath("(//div[@class='col-12 col-sm-6 col-xl-7 mb-3 mb-sm-0 '])["+i+"]"));
			JavascriptExecutor js = (JavascriptExecutor)DocIRMdriver;
			js.executeScript("arguments[0].scrollIntoView(true);", drafttext);
			//    wait.until(ExpectedConditions.elementToBeClickable(drafttext));
			js.executeScript("arguments[0].click();", drafttext);

			Thread.sleep(1000);
			WebElement selectAll = DocIRMdriver.findElement(By.xpath("(//a[@class=\"checklist-select-btn btn btn-sm btn-primary-outline\"])[1]"));
			js.executeScript("arguments[0].scrollIntoView(true);", selectAll);
			// wait.until(ExpectedConditions.elementToBeClickable(selectAll));
			js.executeScript("arguments[0].click();", selectAll);
			log.info("select all : " + i);

			Thread.sleep(2000);
			WebElement saveButton = DocIRMdriver.findElement(By.xpath("(//a[@class=\"checklist-save-btn btn btn-sm btn-primary mr-1\"])[1]"));
			js.executeScript("arguments[0].scrollIntoView(true);", saveButton);
			// wait.until(ExpectedConditions.elementToBeClickable(saveButton));
			js.executeScript("arguments[0].click();", saveButton);
			log.info("save button : " + i);
			Thread.sleep(1000);

			log.info("Draft selected: " + i);
		}

		DocIRMdriver.findElement(By.xpath("(//div[@class='col-12 col-sm-6 col-xl-7 mb-3 mb-sm-0 '])[28]")).click();
		Thread.sleep(1000);
		log.info("Draft selected last .....");

		WebElement selectAll = DocIRMdriver.findElement(By.xpath("(//a[@class=\"checklist-select-btn btn btn-sm btn-primary-outline\"])[1]"));
		JavascriptExecutor js1 = (JavascriptExecutor)DocIRMdriver;

		js1.executeScript("arguments[0].scrollIntoView(true);", selectAll);
		// wait.until(ExpectedConditions.elementToBeClickable(selectAll));
		js1.executeScript("arguments[0].click();", selectAll);
		log.info("select all : " );

		DocIRMdriver.findElement(By.xpath("//a[@class=\"checklist-save-btn btn btn-sm btn-primary mr-1\"]")).click();
		Thread.sleep(1000); 
		DocIRMdriver.findElement(By.xpath("//a[@class=\"checklist-save-btn btn btn-sm btn-primary mr-1\"]")).click();

		documentIRM.getCompleteCheckListButton().click();

		Thread.sleep(2000);

		ReusableContractMethod();
		Thread.sleep(2000);

		documentIRM.getContractSearch().clear();
		Thread.sleep(1000);

		documentIRM.getContractSearch().sendKeys(tradeID);
		Thread.sleep(1000);
		System.out.println("search id final time ......");

		documentIRM.getDropDownContractButton().click();
		System.out.println("dropdown clicked final time ......");	

		Thread.sleep(3000);

		documentIRM.getManagaeLotafterSearchAgain().click();

		System.out.println("manage lots clikced  final time ......");	
		Thread.sleep(2000);

		documentIRM.getVariancedownArrowButton().click();

		System.out.println("variance arrow clikced  final time ......");	

		Thread.sleep(1000);
		documentIRM.getVarianceCheckbox().click();

		System.out.println("checkbox clikced  final time ......");	

		Thread.sleep(1000);	
		documentIRM.getSaveButton().click();
		System.out.println("save clikced  final time ......");	

		Thread.sleep(2000);

		DocIRMdriver.navigate().back();

		//  ReusableContractMethod();
		Thread.sleep(2000);

		documentIRM.getContractSearch().clear();
		Thread.sleep(2000);
		documentIRM.getContractSearch().sendKeys(tradeID);
		Thread.sleep(2000);


		String tradeIDinContract = documentIRM.getTradeIDinContract().getText();

		String[] words = tradeIDinContract.split("/");

		String numberTradeiNaCTION = words[words.length - 1];

		System.out.println(numberTradeiNaCTION);
		Thread.sleep(2000);

		DocIRMdriver.findElement(By.xpath("//a[@href=\"https://stage.agroworlds.com/trades/"+numberTradeiNaCTION+"/contract-actions\"]")).click();

		// Thread.sleep(2000);
		log.info("action button clicked ");
		
		documentIRM.getActionDropdownButton().click();
		
		log.info("action dropdwon clicked ");
		
		String contractID = DocumentExecutivePage.Contractid;
		
		System.out.println("and contract id we get is ... " + contractID);
		
		DocIRMdriver.findElement(By.xpath("//a[@onclick=\"requestContractComplete("+numberTradeiNaCTION+", "+contractID+")\"]")).click();
		
		log.info("request button clicked ");
		
		documentIRM.getContractCompleteRequestRemark().sendKeys("please complete contract");

		documentIRM.getsendBuutonInReuestToCompleteContract().click();

		Thread.sleep(4000);

		DocIRMdriver.navigate().refresh();

		log.info("page refreshed ");
		
		documentIRM.getActionDropdownButton().click();
		
		log.info("action dropdwon button clicked final time ");

		DocIRMdriver.findElement(By.xpath("//a[@onclick=\"approveContractComplete("+numberTradeiNaCTION+", "+contractID+")\"]")).click();

		log.info("complete button clicked ");
		
		documentIRM.getContractApproveRemarks().sendKeys("completed.....!!!");
		
		documentIRM.getCompleterequestApproveButton().click();

		log.info("approved final time>>>>>");

		Thread.sleep(3000);

		DocIRMdriver.navigate().back();


	}






}
