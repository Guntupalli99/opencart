package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //log4j2
import org.apache.logging.log4j.Logger;		//log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

 //log4j2

public class BaseClass {

		public static WebDriver driver;
		public Logger logger;   
		public Properties p;
		
			@BeforeClass(groups= {"Sanity","Regression","Master"})
			@Parameters({"os","browser"})
			public void setup(String os,String br) throws IOException
			{
				//loading properties file
				FileReader file=new FileReader(".//src/test/resources/config.properties");
				p=new Properties();
				p.load(file);
				
				//log4j2
				logger=LogManager.getLogger(this.getClass()); //log4j2
				
			/*	//lunching the broswer  (Local enevironment/system)  we use this intial stage
				switch(br.toLowerCase())
				{
				case "chrome": driver=new ChromeDriver(); break;
				case "edge": driver=new EdgeDriver(); break;
				default: System.out.println("browser mismat");
				return;
				}
			*/	
				//lunching broswer(remote environment(seleninum grid)
				
				
				if(p.getProperty("execution_env").equalsIgnoreCase("remote"))     //we get the all the values from properties files
				{
					DesiredCapabilities capabilities=new DesiredCapabilities();
					
					//os
					if(os.equalsIgnoreCase("windows"))
					{
						capabilities.setPlatform(Platform.WIN10);
					}
					else if(os.equalsIgnoreCase("mac"))
					{
						capabilities.setPlatform(Platform.MAC);
					}
					else if(os.equalsIgnoreCase("linux"))
					{
						capabilities.setPlatform(Platform.LINUX);
					}
					
					else
					{
						System.out.println("no matching os");
					}
					
					
					//browser
					
					if(br.equalsIgnoreCase("chrome"))
					{
						capabilities.setBrowserName("chrome");
						
					}
					else if(br.equalsIgnoreCase("edge"))
					{
						capabilities.setBrowserName("MicrosoftEdge");
					}
					else if(br.equalsIgnoreCase("firefox"))
					{
						capabilities.setBrowserName("firefox");
					}
					else
					{
						System.out.println("no browser matching");
					}
					
					driver=new RemoteWebDriver(new URL("http://192.168.1.39:4444/wd/hub"), capabilities);
				}
				
				if(p.getProperty("execution_env").equalsIgnoreCase("local"))
				{
					//lunching the broswer  (Local enevironment/system)
					switch(br.toLowerCase())
					{
					case "chrome": driver=new ChromeDriver(); break;
					case "edge": driver=new EdgeDriver(); break;
					default: System.out.println("browser mismat");
					return;
					}	
					
				}
				
				
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				
//				driver.get("http://localhost/opencart/upload/index.php"); //hardcode
				driver.get(p.getProperty("appURL"));
				driver.manage().window().maximize();
			}
		
			@AfterClass(groups= {"Sanity","Regression","Master"})
			public void tearDown()
			{
//				driver.close();
			}

			public String randomeString() 
			{
				String generatedString=RandomStringUtils.randomAlphabetic(5);
				return generatedString;
				
			}
			
			public String randomNumber() 
			{
				String generatednumber=RandomStringUtils.randomNumeric(10);
				return generatednumber;
			}
			
			public String randomAlphaNumeric() 
			{
				String str=RandomStringUtils.randomAlphabetic(5);
				String num=RandomStringUtils.randomNumeric(10);
				
				return str+"@"+num;
			}
			
		
			public String captureScreen(String tname) throws IOException {

				String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
						
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
				File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				
				String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
				File targetFile=new File(targetFilePath);
				
				sourceFile.renameTo(targetFile);
					
				return targetFilePath;

			}
		
	}


