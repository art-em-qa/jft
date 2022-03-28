package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationsTests extends TestBase{

    @Test
    public void testUserModifications() {
    app.getContactHelper().initUserModifications();
    app.getContactHelper().fillFormNewUser(new UserData("Antonio-Maria", "Fagundes",
            "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
            null), false);
    app.getContactHelper().submitUserModifications();
    app.getNavigationHelper().returnHomePage();
    }
}
