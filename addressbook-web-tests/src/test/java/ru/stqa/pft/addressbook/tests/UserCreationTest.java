package ru.stqa.pft.addressbook.tests;

import com.sun.org.apache.xpath.internal.operations.String;
import com.sun.xml.internal.ws.util.StringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserCreationTest extends TestBase{

  @Test
  public void testUserCreation() throws Exception {
    List<UserData> before = app.getContactHelper().getContactList();
    UserData contact = new UserData("Antonio", "Fagundes",
            "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
            "actor");
    app.getContactHelper().createContactAndGroupIfGroupNotExist(contact);
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() +1);

    before.add(contact);
    Comparator<? super UserData> byId = (n1, n2) -> Integer.compare(n1.getId(), n2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(after, before);
    //System.out.println(before);
    //System.out.println(after);
  }
}
