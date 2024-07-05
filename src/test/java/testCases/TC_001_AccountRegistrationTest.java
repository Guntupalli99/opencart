package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistrationTest extends BaseClass {

		
//	public WebDriver driver;
	 
		
		@Test(groups={"Regression","Master"})
		public void verify_account_registration()
		{
			logger.info("*** starting TC_001_AccountRegistrationTest *** ");
			HomePage hp=new HomePage(driver);
			
			hp.clickMyAccount();
			logger.info("click on my account link");
			
			hp.clickRegister();
			logger.info("click on my register link");
			
			try
			{
			logger.info("entering customer details");
			AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
			regpage.setFirstName(randomeString().toUpperCase());
			regpage.setLastName(randomeString().toUpperCase());
			regpage.setEmail(randomeString()+"@gmail.com");
			regpage.setTelephone(randomNumber());
			
			String password=randomAlphaNumeric();
			
			regpage.setPassword(password);
			regpage.setConfirmPassword(password);
			
			regpage.setPrivacyPolicy();
			
			
			regpage.clickContinue();
			logger.info("clicked on continue........");
			
			String confmsg=regpage.getConfirmationMsg();
			logger.info("validating expected message....");
			
//			Assert.assertEquals(confmsg, "Your Account Has Been Created..!");
			
			
			if(confmsg.equals("Your Account Has Been Created!"))
			{
				Assert.assertTrue(true);
			}
			else
			{
			logger.error("test is failed");
			Assert.fail();
			}
			
			}
			
			catch(Exception e)
			{
				logger.error("test failed");
				Assert.fail();
			}
			
			logger.info("**** finished TC_001_AccountRegistrationTest ******");
		}
		
		
		
		
	}


