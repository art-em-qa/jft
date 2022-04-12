package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationsTests extends TestBase {

    @Test
    public void testUserModifications() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContactAndGroupIfGroupNotExist(new UserData("Antonio", "Fagundes",
                    "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
                    "artist"));
        }
        List<UserData> before = app.getContactHelper().getContactList();
        UserData modContact = new UserData(before.get(before.size()-1).getId(), "Antonio-Maria", "Fagundes",
                "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com");
        app.getContactHelper().modificateContact(modContact);
        app.getNavigationHelper().returnHomePage();
        List<UserData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(modContact);
        Comparator<? super UserData> byId = (n1, n2) -> Integer.compare(n1.getId(), n2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(after, before);
    }
}