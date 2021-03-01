import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.testng.asserts.Assertion as Assertion
import org.assertj.core.api.Assertions as Assertions
import groovy.json.JsonSlurper as JsonSlurper
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.HttpBodyContent as HttpBodyContent

//Generate unique Number
long uniqueNumber = System.currentTimeMillis() / 1000

//we need to combine unique Number with string, It will generate unique email
String uniqueEmail = ('alvia' + uniqueNumber) + '@mailinator.com'

//inject emailrandom to email property
def stringBody = '{"email":' + uniqueEmail + ',"name":"alvia","status":"Active","gender":"Female"}'

//send POST Resquest
//value uniqueEmail akan di mapping ke variable emailunique
def response = WS.sendRequest(findTestObject('A. PostRequest/PostRequest-DynamicData', [('emailunique') : uniqueEmail]))

//Print email random to ensure the generated email is works
System.out.println('email yang terbentuk: ' + uniqueEmail)

//Verify response status
WS.verifyResponseStatusCode(response, 200)

//Get Response Body

JsonSlurper slurper = new JsonSlurper()

Map parsedJson = slurper.parseText(response.getResponseBodyContent())

//Print to console
println(response.getResponseBodyContent())

//Extract value email in node data
String actualEmail = parsedJson.data.email 

//Print hasil ekstraksi
System.out.println('actualEmail: ' + actualEmail)


//Verify the response content => uniqueEmail (generated email) should be same with actualemail that display in response 
Assertions.assertThat(actualEmail).isEqualTo(uniqueEmail)

