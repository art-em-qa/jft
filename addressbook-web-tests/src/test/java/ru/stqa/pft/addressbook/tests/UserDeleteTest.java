package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;


public class UserDeleteTest extends TestBase{

  @Test
  public void testUserDelete() throws Exception {
    if(! app.getContactHelper().isThereAContact()){
      app.getContactHelper().initCreateNewUser();
      if(! app.getContactHelper().isGroupCreated(1)){
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().createGroup(new GroupData("peopleTest", "humanTest", "testGroup"));
        app.getNavigationHelper().returnHomePage();
      }
      app.getContactHelper().createContact(new UserData("Antonio", "Fagundes",
              "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
              "peopleTest"));
    }
    app.getNavigationHelper().selectRandomCheckBox();
    app.getContactHelper().deleteSelectedUsers();
    app.getNavigationHelper().acceptAllert();
    app.getNavigationHelper().returnHomePage();
  }
}
