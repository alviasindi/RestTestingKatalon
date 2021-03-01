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
import org.assertj.core.api.Assertions as Assertions
import groovy.json.JsonSlurper as JsonSlurper

//change url at Object repository GetRequest
def responseGet = WS.sendRequest(findTestObject('B. GetRequest/GetRequest'))

//samakan url di object GetRequest dengan DeleteRequest
//condition => if get request success, we can use the data (memastikan data yang mau dihapus, masih ada di sistem)
if (WS.verifyResponseStatusCode(responseGet, 200, FailureHandling.STOP_ON_FAILURE)) {
	//send Delete request
    def responseDelete = WS.sendRequest(findTestObject('D. DeleteRequest/DeleteRequest'))

	//Get Response Body
	JsonSlurper slurper = new JsonSlurper()
	Map parsedJson = slurper.parseText(responseDelete.getResponseBodyContent())
	
	//Print to console
	println(responseDelete.getResponseBodyContent())
	//verify the Response
	WS.verifyResponseStatusCodeInRange(responseDelete, 200, 204)
}


