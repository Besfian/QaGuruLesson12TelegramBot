package ru.mail.besfian;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;


public class MyTextBoxTests extends TestBase{
    @BeforeAll
    static void disclosure() {
        Configuration.startMaximized = true;
    }

    @Owner("sergeev")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Github test with Allure")
    @Test
    void formTest() {
        StudentData faker = new StudentData();
        RegistrationsPage registrationsPage = new RegistrationsPage();
        List<String> hobbies = new ArrayList();
        hobbies.add("Sports");
        hobbies.add("Reading");
        hobbies.add("Music");
        step("Open students registration form", () -> {
            registrationsPage.openPage().validateFormTitle();
        });
        step("Fill students registration form", () -> {
            step("Fill common data", () -> {
                registrationsPage.typeFirstName(faker.firstName)
                        .typeLastName(faker.lastName)
                        .typeEmail(faker.userEmail)
                        .chooseGender(faker.gender)
                        .typeUserNumber(faker.userNumber);
            });
            step("Set date", () -> {
                registrationsPage.typeDateOfBirth(faker.yearOfBirthInput, faker.monthOfBirthInput, faker.dayOfBirthInput);
            });
            step("Set hobbies", () -> {
                registrationsPage.typeHobbies(hobbies);
            });
            registrationsPage.typeSubjects("e", "English");
            step("Upload image", () -> {
                registrationsPage.loadPicture("Test.jpg");
            });
            step("Set address", () -> {
                registrationsPage.typeAddress(faker.address)
                        .typeState("NCR")
                        .typeCity("Delhi");
            });
            step("Submit form", () -> {
                registrationsPage.submitClick();
            });
        });
        step("Verify successful form submit", () -> {
            registrationsPage.checkResults("Student Name", faker.firstName + " " + faker.lastName)
                    .checkResults("Student Email", faker.userEmail)
                    .checkResults("Gender", faker.gender)
                    .checkResults("Mobile", faker.userNumber)
                    .checkResults("Date of Birth", faker.dayOfBirthInput + " " + faker.monthOfBirthInput + "," + faker.yearOfBirthInput)
                    .checkResults("Subjects", "English")
                    .checkResults("Hobbies", hobbies)
                    .checkResults("Picture", "Test.jpg")
                    .checkResults("Address", faker.address)
                    .checkResults("State and City", "NCR Delhi");
        });
    }


}
