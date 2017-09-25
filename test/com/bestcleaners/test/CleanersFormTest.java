package com.bestcleaners.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CleanersFormTest {

	private WebClient webClient;
	private HtmlPage page;
	private HtmlForm form;
	private String urlConfirmationForm = "http://localhost:8080/cleaner_register_confirmation";
	private String validFirstName = "ValidFirstName";
	private String validLasttName = "ValidLastName";
	private String validPhoneNumber = "+640123456789";
	private String validHourlyRate = "23.50";

	@Before
	public void initBrowser() throws Exception {
		webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
		page = webClient.getPage("http://localhost:8080/cleaner_register");
		form = page.getHtmlElementById("fContact");
	}
	
	@Test
	public void cleanerRegisterTitle() {
        Assert.assertEquals("Cleaner Register", page.getTitleText());
	}
	
	@Test
	public void validatePageTitle() throws Exception {
		HtmlHeading1 h1 = (HtmlHeading1) page.getByXPath("//h1[@class='title']").get(0);
        Assert.assertEquals("Cleaner Register", h1.getTextContent());
	}
	
	private void fillAllFieldsProperly()
	{
		form.getInputByName("nFirstName").setValueAttribute(validFirstName);
		form.getInputByName("nLastName").setValueAttribute(validLasttName);
		form.getInputByName("nPhone").setValueAttribute(validPhoneNumber);
		form.getInputByName("nGender").setChecked(true);
		form.getInputByName("nBasic").setChecked(true);
		form.getInputByName("nArchHill").setChecked(true);
		form.getInputByName("nRate").setValueAttribute(validHourlyRate);
	}
	
	@Test
	public void submitFormOK() throws Exception {
		fillAllFieldsProperly();
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
        Assert.assertEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_FirstName_MoreThan15Char() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nFirstName").setValueAttribute("NameWithMoreThanFifteen");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_FirstName_InvalidChar_Number() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nFirstName").setValueAttribute("NameWith15Char");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_FirstName_InvalidChar_Synmbol() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nFirstName").setValueAttribute("NameWith+Fiftee");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_FirstName_InvalidChar_Empty() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nFirstName").setValueAttribute("");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_LastName_MoreThan20Char() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nLastName").setValueAttribute("FamilyNameWithMoreThanTweenty");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_LasttName_InvalidChar_Number() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nLastName").setValueAttribute("FamilyNameWith20");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_LastName_InvalidChar_Synmbol() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nLastName").setValueAttribute("FamilyNameWith+Tween");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_LasttName_InvalidChar_Empty() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nLastName").setValueAttribute("");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_PhoneNumber_InvalidChar_Letter() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nPhone").setValueAttribute("InvalidPhoneNumber5456");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_PhoneNumber_InvalidChar_Symbol() throws Exception {
		fillAllFieldsProperly();

		form.getInputByName("nPhone").setValueAttribute("+64-*23562");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_PhoneNumber_InvalidChar_MoreThan10Numbers() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nPhone").setValueAttribute("+6401234567891");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_PhoneNumber_InvalidChar_NoNZNumber() throws Exception {
		fillAllFieldsProperly();

		form.getInputByName("nPhone").setValueAttribute("+5511967816385");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_Gender_NoneSelected() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nGender").setChecked(false);
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_TypeOfCleaning_NoneSelected() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nBasic").setChecked(false);
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void typeOfCleaning_SelectAllIsSelectingAll() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nTpAll").setChecked(true);
		
    	Assert.assertTrue(form.getInputByName("nDeep").isChecked());
	}
	
	@Test
	public void submitFormNOK_Suburban_NoneSelected() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nArchHill").setChecked(false);
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
    	Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void suburban_SelectAllIsSelectingAll() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nAllSub").setChecked(true);
		
    	Assert.assertTrue(form.getInputByName("nBalmoral").isChecked());
	}
	
	@Test
	public void submitFormNOK_HourlyRate_NegativeNumber() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nRate").setValueAttribute("-10.50");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
        Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_HourlyRate_Zero() throws Exception {
		fillAllFieldsProperly();

		form.getInputByName("nRate").setValueAttribute("0.0");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
        Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_HourlyRate_MoreThan999() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nRate").setValueAttribute("1000.00");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
        Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
	
	@Test
	public void submitFormNOK_HourlyRate_More2DecimalNumbers() throws Exception {
		fillAllFieldsProperly();
		
		form.getInputByName("nRate").setValueAttribute("23.456");
		
    	HtmlPage page2 = form.getInputByName("submit").click();
		
        Assert.assertNotEquals(urlConfirmationForm, page2.getBaseURI());
	}
}