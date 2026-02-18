package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import org.example.data.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DemoQa extends BaseTest{

    @Test

    public  void FullForm() {
        Locator forms = page.locator("a[href='/forms']");
        Locator automationPracticeForm = page.locator("a[href='/automation-practice-form']");
        Locator firstName = page.locator("#firstName");
        Locator lastName = page.locator("#lastName");
        Locator email = page.locator("#userEmail");
        Locator genderFemale = page.locator("label[for=\"gender-radio-2\"]");
        Locator mobileNumber = page.locator("#userNumber");
        Locator subjects = page.locator("#subjectsInput");
        Locator hobbieSport = page.locator("label[for=\"hobbies-checkbox-1\"]");
        Locator hobbieMusic = page.locator("label[for=\"hobbies-checkbox-2\"]");
        Locator address = page.locator("#currentAddress");
        Locator state = page.locator("#react-select-3-input");
        //კონკრეტული ქალაქის დროფდაუნის ამოღება
        Locator submitButton = page.locator("#submit");
        Locator successMessage = page.getByText("Thanks for submitting the form");

        assertTrue(
                Constants.MOBILE_NUMBER.matches("\\d{10}"),
                "Phone number must contain exactly 10 digits"
        );

        forms.click();
        automationPracticeForm.click();
        firstName.fill(Constants.FIRST_NAME);
        lastName.fill(Constants.LAST_NAME);
        email.fill(Constants.EMAIL);
        genderFemale.click();
        mobileNumber.fill(Constants.MOBILE_NUMBER);
        subjects.fill(Constants.SUBJECT_MATHS);
        subjects.press("Enter");
        subjects.fill(Constants.SUBJECT_MUSIC);
        subjects.press("Enter");

        hobbieSport.click();
        hobbieMusic.click();
        address.fill(Constants.ADDRESS);
        state.click();
        //dropdown-ში კონკრეტული შტატის არჩევა დასამატებელია
        //ქალაქის არჩევა
        submitButton.click();


        Assert.assertTrue(successMessage.isVisible(),
                "Success message is not displayed!");
    }
    @Test
    public void RequiredFieldsValidation() {
        Locator forms = page.locator("a[href='/forms']");
        Locator automationPracticeForm = page.locator("a[href='/automation-practice-form']");
        Locator userForm =  page.locator("#userForm");
        Locator email = page.locator("#userEmail");
        Locator subjects = page.locator("#subjectsInput");
        Locator hobbieSport = page.locator("label[for=\"hobbies-checkbox-1\"]");
        Locator hobbieMusic = page.locator("label[for=\"hobbies-checkbox-2\"]");
        Locator address = page.locator("#currentAddress");
        Locator submitButton = page.locator("#submit");

        forms.click();
        automationPracticeForm.click();
        email.fill(Constants.EMAIL);
        subjects.fill(Constants.SUBJECT_MATHS);
        subjects.press("Enter");
        subjects.fill(Constants.SUBJECT_MUSIC);
        subjects.press("Enter");


        hobbieSport.click();
        hobbieMusic.click();
        address.fill(Constants.ADDRESS);
        submitButton.click();

        assertThat(userForm).hasClass(Pattern.compile(".*was-validated*"));
    }

    @Test
    public void OnlyRequiredFields(){
        Locator forms = page.locator("a[href='/forms']");
        Locator automationPracticeForm = page.locator("a[href='/automation-practice-form']");
        Locator firstName = page.locator("#firstName");
        Locator lastName = page.locator("#lastName");
        Locator genderFemale = page.locator("label[for=\"gender-radio-2\"]");
        Locator mobileNumber = page.locator("#userNumber");
        Locator successMessage = page.getByText("Thanks for submitting the form");
        Locator submitButton = page.locator("#submit");

        forms.click();
        automationPracticeForm.click();
        firstName.fill(Constants.FIRST_NAME);
        lastName.fill(Constants.LAST_NAME);
        genderFemale.click();
        mobileNumber.fill(Constants.MOBILE_NUMBER);
        submitButton.click();

        Assert.assertTrue(successMessage.isVisible(),
                "Success message is not displayed!");
    }
    @Test
    public void mobileLenghValidation(){
        Locator  forms = page.locator("a[href='/forms']");
        Locator automationPracticeForm = page.locator("a[href='/automation-practice-form']");
        Locator firstName = page.locator("#firstName");
        Locator lastName = page.locator("#lastName");
        Locator genderFemale = page.locator("#gender-radio-1");
        Locator mobileNumber = page.locator("#userNumber");
        Locator submitButton = page.locator("#submit");

        forms.click();
        automationPracticeForm.click();
        firstName.fill(Constants.FIRST_NAME);
        lastName.fill(Constants.LAST_NAME);
        genderFemale.click();
        mobileNumber.fill(Constants.INVALID_MOBILE_NUMBER);
        submitButton.click();

        assertFalse(
                Constants.INVALID_MOBILE_NUMBER.matches("\\d{10}"),
                "Phone number must contain exactly 10 digits"
        );
    }

}
