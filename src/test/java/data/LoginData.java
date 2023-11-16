package data;

import org.testng.annotations.DataProvider;

public class LoginData {

    @DataProvider(name = "invalidLoginData")
    public static Object[][] getInvalidLoginData() {
        return new Object[][]{
                {"invalid_username", "invalid_password", "Epic sadface: Username and password do not match any user in this service"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "", "Epic sadface: Username is required"},
                {"valid_username", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."}
        };
    }
}
