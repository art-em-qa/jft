package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;


public class UserDeleteTest extends TestBase{

  @Test
  public void testUserDelete() throws Exception {
    String groupName = "actors";
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().initCreateNewUser();
      if(! app.getContactHelper().isGroupCreatedByName(groupName)){
        app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
      }
      app.getContactHelper().createContact(new UserData("Antonio", "Fagundes",
              "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
              groupName));
    }
    app.getNavigationHelper().selectCheckBox(0);
    app.getContactHelper().deleteSelectedUsers();
    app.getNavigationHelper().acceptAllert();
    app.getNavigationHelper().returnHomePage();
  }
}
