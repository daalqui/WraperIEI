package principal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;


public class helpers {

	private static WebDriver driver= null;

	static void waitForPageLoad(WebDriver wdriver) {
		WebDriverWait wait = new WebDriverWait(wdriver, 60);
		Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {
			@Override
			public boolean apply(WebDriver input) {
				return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
			}
		};
		wait.until(pageLoaded);
		}
	
	
	
	
	public static void waitForPageLoaded() {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete"); 
			}
		};		
		try {
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete.");
		}
	}
	
	public static void WaitForAjax() throws InterruptedException {
		while (true) { 
			Boolean ajaxIsComplete = (Boolean)((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
			if (ajaxIsComplete){
				break;
			}
			Thread.sleep(100);
		}
	}

}
