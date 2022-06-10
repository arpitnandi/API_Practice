package reusables ;


import org.testng.annotations.* ;
import org.testng.* ;

import com.aventstack.extentreports.* ;
import com.aventstack.extentreports.reporter.* ;


public class baseClass
{
    
	String frameworkDirectory = System.getProperty( "user.dir" ) ;
	public String testDataLocation = frameworkDirectory + "\\src\\test\\java\\testData" ;
	String testName = "Req_Res_Test" ;
	String reportFileName = testName + ".html" ;
	static ExtentHtmlReporter htmlReporter ;
	static ExtentReports extentReports ;
	static ExtentTest extentTest ;

    
	@BeforeSuite
	public void configureReporterName()
	{
		extentReports = new ExtentReports() ;
		htmlReporter = new ExtentHtmlReporter( frameworkDirectory + "\\target\\ExtentReport\\" + reportFileName ) ;
	}

	@BeforeTest
	public void configureReportDocument( ITestContext context )
	{
		htmlReporter.config().setDocumentTitle( context.getName() ) ;
		htmlReporter.config().setReportName( context.getName() ) ;
        htmlReporter.config().setEncoding( "utf-8" ) ;
        htmlReporter.config().setTimeStampFormat( "EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'" ) ;
		
		extentReports.attachReporter( htmlReporter ) ;
		extentReports.setSystemInfo( "Operating System", "Windows 10" ) ;
		extentReports.setSystemInfo( "Functionality", "Req_Res CRUD operations" ) ;
		extentReports.setSystemInfo( "Test Feature", "Get methods testing" ) ;
	}

	@BeforeClass
	public void configureTest( ITestContext context )
	{
		extentTest = extentReports.createTest( context.getCurrentXmlTest().getClasses().stream().findFirst().get().getName() , "Test Detail" ) ;
	}

	@AfterMethod
	public void logTestCaseResult( ITestResult result )
	{
		if( result.getStatus() == ITestResult.SUCCESS )
		{
			extentTest.pass( result.getMethod().toString() ) ;
		}
		if( result.getStatus() == ITestResult.FAILURE )
		{
			extentTest.log( Status.FAIL , result.getMethod().toString() ) ;
		}
		if( result.getStatus() == ITestResult.SKIP )
		{
			extentTest.skip( result.getMethod().toString() ) ;
		}
	}
	
	@AfterClass
	public void terminateReporter()
	{
		extentReports.flush() ;
	}
	
}