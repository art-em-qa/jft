package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if(app.contact().all().size() == 0){
            app.contact().createContactAndGroupIfGroupNotExist(new ContactData().withName("Antonio").withLastname("Fagundes").
                    withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com").
                    withGroup("actor"));
        }
    }

    @Test
    public void testUserModifications() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData modContact = new ContactData().withId(modifiedContact.getId()).withName("Antonio-Maria").withLastname("Fagundes").
                withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com");
        app.contact().modify(modContact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modContact)));
    }

    @Test
    public void testUserBadModifications() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData modContact = new ContactData().withId(modifiedContact.getId()).withName("Antonio-Maria'").withLastname("Fagundes").
                withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com");
        app.contact().modify(modContact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}