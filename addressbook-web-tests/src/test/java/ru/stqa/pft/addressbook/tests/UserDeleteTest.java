package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;


public class UserDeleteTest extends TestBase{

  @Test(enabled = false)
  public void testUserDelete() throws Exception {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContactAndGroupIfGroupNotExist(new UserData("Antonio", "Fagundes",
              "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
              "actor"));
    }
    List<UserData> before = app.getContactHelper().getContactList();
    app.goTo().selectCheckBox(before.size() -1);
    app.getContactHelper().deleteSelectedUsers();
    app.goTo().acceptAllert();
    app.goTo().returnHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() -1);
    Assert.assertEquals(before, after);
  }
}
