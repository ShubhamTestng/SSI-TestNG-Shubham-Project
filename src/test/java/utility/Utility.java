package utility;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import POMpage.BDM;

public class Utility  {
	public static WebDriver BDMdriver ;
	

	 public String readExcelFile(String sheetName, int rowNumber, int columnNumber) throws IOException, EncryptedDocumentException {
		 
	        FileInputStream fis = new FileInputStream("C:\\Users\\wbox62\\eclipse-workspace\\SSIAgroworlds\\target\\SSITestData.xlsx");
	        
	        Workbook workbook = WorkbookFactory.create(fis);  
	        Sheet sheet = workbook.getSheet(sheetName);
	        Row row = sheet.getRow(rowNumber);
            Cell cell = row.getCell(columnNumber, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

	        // Use DataFormatter to handle both numeric and string values
	        DataFormatter dataFormatter = new DataFormatter();
	        return dataFormatter.formatCellValue(cell);
	    }
	


	public void Dropdown(WebElement dropdownElement, String dropDowntext) {

		Select INCOTERN = new Select(dropdownElement);

		INCOTERN.selectByVisibleText(dropDowntext);

	}


	public void getYopmail(WebDriver BDMdriver, String yopmail) throws InterruptedException {

		BDM bdm = new BDM(BDMdriver);
		
		Thread.sleep(5000);

		BDMdriver.get("https://yopmail.com/en");

		Thread.sleep(2000);

		bdm.getYopmailLogin().sendKeys(yopmail, Keys.ENTER);

		BDMdriver.switchTo().frame(2);

		JavascriptExecutor js = (JavascriptExecutor) bdm.BDMdriver;

		// Scroll to the element
		js.executeScript("arguments[0].scrollIntoView(true);", bdm.getReviewtradeseller());

		String OTP = bdm.getSellerOTP().getText();

		Thread.sleep(3000);
		
		 bdm.getReviewtradeseller().click();

		Set<String> allWindowHandles = BDMdriver.getWindowHandles();

		Iterator<String> windowIterator = allWindowHandles.iterator();

		while (windowIterator.hasNext()) {
			String currentWindow = windowIterator.next();
			BDMdriver.switchTo().window(currentWindow);

			System.out.println("Title of current window: " + BDMdriver.getTitle());
		}


		bdm.getSellerOTPpassword().sendKeys(OTP);

		bdm.getSellerAccessButton().click();

		Thread.sleep(2000);

		Actions action = new Actions(BDMdriver);

		action.moveToElement(bdm.getSellerApproval())
		.click()
		.perform();

		//driver.close();

		//bdm.getHomebutton().click();

		//bdm.getCloseButton().click();
	}

	


}
