package ru.mail.besfian;

import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationsPage {
    private final String URL = "https://demoqa.com/automation-practice-form";
    private final String FORM_TEXT = "Student Registration Form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            formFirstNameInput = $("#firstName"),
            formLastNameInput = $("#lastName"),
            formEmailInput = $("#userEmail"),
            formUserNumberInput = $("#userNumber"),
            formDateOfBirthInput = $("#dateOfBirthInput"),
            formSubmit = $("#submit"),
            formSubjectsInput = $("#subjectsInput"),
            formUploadPictureInput = $("#uploadPicture"),
            formCurrentAddressInput = $("#currentAddress");

    public RegistrationsPage openPage() {
        open(URL);
        return this;
    }

    public RegistrationsPage validateFormTitle() {
        formTitle.shouldHave(text(FORM_TEXT));
        return this;
    }

    public RegistrationsPage typeFirstName(String firstName) {
        formFirstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationsPage typeLastName(String lastName) {
        formLastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationsPage typeEmail(String email) {
        formEmailInput.setValue(email);
        return this;
    }

    public RegistrationsPage typeUserNumber(String userNumber) {
        formUserNumberInput.setValue(userNumber);
        return this;
    }

    public RegistrationsPage typeDateOfBirth(String year, String month, String day) {
        formDateOfBirthInput.click();
        $(".react-datepicker__year-select").click();
        $(byText(year)).click();
        $(".react-datepicker__month-select").click();
        $(byText(month)).click();
        $(byText(day)).click();
        return this;
    }

    public RegistrationsPage typeHobbies(List hobbies) {
        for (int index = 0; index < hobbies.size(); index++) {
            $(byText(String.valueOf((hobbies.get(index))))).click();
        }
        return this;
    }

    public RegistrationsPage chooseGender(String gender) {
        switch (gender) {
            case "Male":
                $("[for='gender-radio-1']").click();
                break;
            case "Female":
                $("[for='gender-radio-2']").click();
                break;
            case "Other":
                $("[for='gender-radio-3']").click();
                break;
        }
        return this;
    }

    public RegistrationsPage typeSubjects(String subjects, String fullSubjects) {
        formSubjectsInput.sendKeys(subjects);
        $(byText(fullSubjects)).click();
        return this;
    }

    public RegistrationsPage loadPicture(String picture) {
        formUploadPictureInput.uploadFromClasspath(picture);
        return this;
    }

    public RegistrationsPage typeAddress(String address) {
        formCurrentAddressInput.setValue(address);
        return this;
    }

    public RegistrationsPage typeState(String state) {
        $(byText("Select State")).scrollTo().click();
        $(byText(state)).click();
        return this;
    }
    public RegistrationsPage typeCity(String city) {
        $(byText("Select City")).click();
        $(byText(city)).click();
        return this;
    }

    public RegistrationsPage submitClick() {
        formSubmit.scrollTo().click();
        return this;
    }

    RegistrationsPage checkResults(String key, String meaning) {
        $(".table-responsive").$(byText(key)).parent().shouldHave(text(meaning));
        return this;
    }


    RegistrationsPage checkResults(String key, List hobbies) {
        String stringHobbies;
        {
            switch (hobbies.size()) {
                case 1:
                    stringHobbies = "" + hobbies.get(0);
                    $(".table-responsive").$(byText(key)).parent().shouldHave(text(stringHobbies));
                    break;
                case 2:
                    stringHobbies = hobbies.get(0) + ", " + hobbies.get(1);
                    $(".table-responsive").$(byText(key)).parent().shouldHave(text(stringHobbies));
                    break;
                case 3:
                    stringHobbies = hobbies.get(0) + ", " + hobbies.get(1) + ", " + hobbies.get(2);
                    $(".table-responsive").$(byText(key)).parent().shouldHave(text(stringHobbies));
                    break;
            }
        }
        return this;
    }
}
