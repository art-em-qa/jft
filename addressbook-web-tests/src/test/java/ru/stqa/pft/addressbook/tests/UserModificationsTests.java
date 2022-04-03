package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

public class UserModificationsTests extends TestBase {

    @Test
    public void testUserModifications() {
        String groupName = "artist";
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().initCreateNewUser();
            if (!app.getContactHelper().isGroupCreatedByName(groupName)) {
                app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
            }
            app.getContactHelper().createContact(new UserData("Antonio", "Fagundes",
                    "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
                    groupName));
        }
        app.getContactHelper().modificateContact(new UserData("Antonio-Maria", "Fagundes",
                "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
                null));
        app.getNavigationHelper().returnHomePage();
    }
}