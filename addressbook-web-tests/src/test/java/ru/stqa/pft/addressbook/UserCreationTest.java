package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class UserCreationTest extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    initCreateNewUser();
    fillFormNewUser(new UserData("Antonio", "Fagundes", "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com"));
    submitCreateNewUser();
    returnHomePage();
  }
}
