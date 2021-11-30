package ru.mail.besfian;

import com.github.javafaker.Faker;

import java.util.Locale;

public class StudentData {
    Faker faker = new Faker(new Locale("en"));
    public String firstName = faker.name().firstName();
    public String lastName = faker.name().lastName();
    public String userEmail = faker.internet().emailAddress("test");
    public String userNumber = "8904775511";
    public String address = faker.address().streetAddress();
    public String yearOfBirthInput = "1990";
    public String monthOfBirthInput = "February";
    public String dayOfBirthInput = "7";
    public String hobby = "Reading";
    public String sports = "Sports";
    public String music = "Music";
    public String gender = "Other";
}

