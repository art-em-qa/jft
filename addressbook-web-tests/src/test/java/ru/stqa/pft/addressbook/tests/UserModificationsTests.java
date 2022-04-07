package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationsTests extends TestBase {

    @Test
    public void testUserModifications() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContactAndGroupIfGroupNotExist(new UserData("Antonio", "Fagundes",
                    "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
                    "artist"));
        }
        app.getContactHelper().modificateContact(new UserData("Antonio-Maria", "Fagundes",
                "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
                null));
        app.getNavigationHelper().returnHomePage();
    }
}