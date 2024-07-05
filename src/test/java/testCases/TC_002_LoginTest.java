package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_002_LoginTest extends BaseClass
{
	@Test(groups={"sanity","Master"})
	public void verify_login() {
		
		logger.info("****** Starting TC_002_LoginTest ****");
		
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		//MyAccount page
		MyAccountPage myacc=new MyAccountPage(driver);
		boolean	targetpage=myacc.isMyAccountPageExists();
		
		Assert.assertTrue(targetpage);  //Assert.assertEquals(targetPage, true,"Login failed");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Starting TC_002_LoginTest ****");
		
		
	}
}
