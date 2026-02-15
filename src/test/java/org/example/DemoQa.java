package org.example;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Playwright;
import org.example.data.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.testng.Assert.assertTrue;

public class DemoQa extends BaseTest{
    @Test


    public void test() {
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
        Locator state = page.locator("#state");
        Locator submitButton = page.locator("#submit");
        //Locator success = page.locator(".modal-title h4");
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
        //dropdownidan amogeba mosafiqrebelia
        submitButton.click();

        assertThat(successMessage).isVisible();

        Assert.assertTrue(successMessage.isVisible(),
                "Success message is not displayed!");
    }
}
