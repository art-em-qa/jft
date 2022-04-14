package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class UserDeleteTest extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    if(app.contact().all().size() == 0){
      app.contact().createContactAndGroupIfGroupNotExist(new UserData().withName("Antonio").withLastname("Fagundes").
              withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com").
              withGroup("actor"));
    }
  }

  @Test
  public void testUserDelete() throws Exception {
    Contacts before = app.contact().all();
    UserData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() -1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }

}
