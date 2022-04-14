package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.UserData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class UserCreationTest extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    Contacts before = app.contact().all();
    UserData contact = new UserData().withName("Antonio").withLastname("Fagundes").
            withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com").
            withGroup("actor");
    app.contact().createContactAndGroupIfGroupNotExist(contact);
    Contacts after = app.contact().all();
    assertEquals(after.size(), before.size() +1);
    assertThat(after, equalTo(before.withAdded(
            contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }
}
