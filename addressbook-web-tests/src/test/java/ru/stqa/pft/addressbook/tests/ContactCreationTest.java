package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactCreationTest extends TestBase {

    @Test
    public void testUserCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withName("Antonio").withLastname("Fagundes").
                withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withHomephone("112").
                withMobile("+79111120202").withEmail("a.fagundes@stbarbara.com").withGroup("actor");
        app.contact().createContactAndGroupIfGroupNotExist(contact);
        assertEquals(app.contact().count(), before.size() + 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.withAdded(
                contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    @Test
    public void testUserBadCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withName("Antonio'").withLastname("Fagundes").
                withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withHomephone("112").withMobile("+79111120202").withEmail("a.fagundes@stbarbara.com").
                withGroup("actor"); // name with '
        app.contact().createContactAndGroupIfGroupNotExist(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
