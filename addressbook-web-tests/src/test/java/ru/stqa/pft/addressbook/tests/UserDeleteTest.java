package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.UserData;


public class UserDeleteTest extends TestBase{

  @Test
  public void testUserDelete() throws Exception {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContactAndGroupIfGroupNotExist(new UserData("Antonio", "Fagundes",
              "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
              "actor"), "actor");
    }
    app.getNavigationHelper().selectCheckBox(0);
    app.getContactHelper().deleteSelectedUsers();
    app.getNavigationHelper().acceptAllert();
    app.getNavigationHelper().returnHomePage();
  }
}
