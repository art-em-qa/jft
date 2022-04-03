package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTest extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    String groupName = "gruppa";
    app.getContactHelper().initCreateNewUser();
    if(! app.getContactHelper().isGroupCreatedByName(groupName)){
      app.getGroupHelper().createGroup(new GroupData(groupName, "humanTest", "testGroup"));
    }
    app.getContactHelper().createContact(new UserData("Antonio", "Fagundes",
            "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
            groupName));
  }
}
