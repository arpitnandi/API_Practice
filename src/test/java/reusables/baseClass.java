package reusables ;


import org.testng.annotations.* ;
import org.testng.* ;
import org.testng.xml.* ;

import com.aventstack.extentreports.* ;
import com.aventstack.extentreports.reporter.* ;
import com.aventstack.extentreports.reporter.configuration.* ;


public class baseClass extends Utils {
    
	String frameworkDirectory = System.getProperty( "user.dir" ) ;
	public String testDataLocation = frameworkDirectory + "\\src\\test\\java\\testData" ;
	String testName = "Req_Res_Test" ;
	String reportFileName = testName + ".html" ;
	static ExtentSparkReporter sparkReporter ;
	static ExtentReports extentReports ;
	static ExtentTest extentTest ;
	public String baseURI = "https://reqres.in" ;
	public String employeeName = this.getString( "random" , 12 ) ;

    
	@BeforeSuite
	public void configureReporterName() {
		extentReports = new ExtentReports() ;
		sparkReporter = new ExtentSparkReporter( frameworkDirectory + "\\target\\ExtentReport\\" + reportFileName ) ;
		
		extentReports.attachReporter( sparkReporter ) ;
		extentReports.setSystemInfo( "Operating System", "Windows 10" ) ;
		extentReports.setSystemInfo( "Application", "Req_Res.in" ) ;
		extentReports.setSystemInfo( "Functionality", "CRUD operations" ) ;
		extentReports.setSystemInfo( "Test Features", "Get, Post, Put, Patch and Delete" ) ;
	}

	@BeforeTest
	public void configureReportDocument( ITestContext context ) {
		sparkReporter.config().setDocumentTitle( reportFileName ) ;
		sparkReporter.config().setReportName( context.getSuite().getName() ) ;
        sparkReporter.config().setEncoding( "utf-8" ) ;
        sparkReporter.config().setTimeStampFormat( "EEEE, dd/MMMM/yyyy \n hh:mm:ss a '('zzz')'" ) ;
		sparkReporter.config().setTheme( Theme.STANDARD ) ;
		sparkReporter.config().setProtocol( Protocol.HTTPS ) ;
	}

	@BeforeClass
	public void configureTest() {
		extentTest = extentReports.createTest( this.getClass().getName() ) ;
	}

	@AfterMethod
	public void logTestCaseResult( ITestResult result ) {
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
	public void terminateReporter() {
		extentReports.flush() ;
	}
	
}