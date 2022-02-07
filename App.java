package com.ms.qa.totp;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;

/**
 * 
 * @author Amith Kumar M G
 *
 */
public class App {

	public static final String ACCOUNT_SID = "AC1729bdb59d249310ff76491b3fc883f9";
	public static final String AUTH_TOKEN = "74112b571eeb8d7838015b51cc48ac4e";

	public static void main(String[] args) {


		// get the OTP using Twilio APIs:
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		String smsBody = getMessage();
		

		System.out.println(" SMS Read from Twilio Account: "+ACCOUNT_SID);
		System.out.println("");
		System.out.println("#############################################################################");
		System.out.println(smsBody);
		System.out.println("#############################################################################");
		
		String OTPNumber = smsBody.replaceAll("[^-?0-9]+", " ");
		System.out.println("");
		System.out.println("");
		System.out.println("#############################################################################");
		System.out.println("OTP Retrieved by Twilio and to be Entered in MSO:"+OTPNumber);
		System.out.println("#############################################################################");
		
		System.exit(1);
		
		
		
		
		
		
		
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\amith\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");



		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.cssSelector("a#nav-link-accountList>span>span")).click();

		driver.findElement(By.linkText("Start here.")).click();


		driver.findElement(By.id("ap_customer_name")).sendKeys("NaveenTestOTP");
		driver.findElement(By.id("auth-country-picker-container")).click();

		driver.findElement(By.xpath("//ul[@role='application']//li/a[contains(text(),'United States +1')]")).click();
		driver.findElement(By.id("ap_phone_number")).sendKeys("12349013246");
		driver.findElement(By.id("ap_password")).sendKeys("TestAutomation@123");
		driver.findElement(By.id("continue")).click();



		driver.findElement(By.id("auth-pv-enter-code")).sendKeys(OTPNumber);

	}

	public static String getMessage() {


		return getMessages().toString();
	}

	private static String getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		//	return StreamSupport.stream(messages.spliterator(), false);
		String messageBody="";
		for(Message messg:messages)
		{
			messageBody= messg.getBody();
			//System.out.println(messageBody);
			break;
		}
		
		return messageBody;
	}

}