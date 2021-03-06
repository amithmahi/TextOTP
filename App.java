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
