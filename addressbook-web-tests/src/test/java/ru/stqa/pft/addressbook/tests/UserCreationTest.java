package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.UserData;

public class UserCreationTest extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    app.getContactHelper().createContactAndGroupIfGroupNotExist(new UserData("Antonio", "Fagundes",
            "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
            "actor"), "actor");
  }
}
