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
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.RequestObject as RequestObject
import com.kms.katalon.core.testobject.TestObjectProperty as TestObjectProperty
import com.kms.katalon.core.testobject.HttpBodyContent as HttpBodyContent
import java.util.Arrays;

//First! Edit this string value
String customname = 'alviasindi'

//edit the name, set name = "alviasindi"
def stringBody = ('{"id":1307,"email":"alvia1614552533@mailinator.com",' + customname) + ',"status":"Active","gender":"Female"}'

//send PUT Request
def response = WS.sendRequest(findTestObject('C. PutRequest/PutRequest', [('userfullname') : customname]))

//Get Response Body
JsonSlurper slurper = new JsonSlurper()

Map parsedJson = slurper.parseText(response.getResponseBodyContent())

//Print to console
println(response.getResponseBodyContent())

//Extract value edited name at node data
String editedname = parsedJson.data.name

//print the name of editedname to console
System.out.println('expectedname: ' + editedname)

//print the name of editedname to console
System.out.println('name: ' + customname)

//Verify the response content => customname should be same with editedname that display in response
Assertions.assertThat(editedname).isEqualTo(customname)
