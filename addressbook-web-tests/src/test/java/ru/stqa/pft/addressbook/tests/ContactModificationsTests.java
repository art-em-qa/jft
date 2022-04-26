package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class ContactModificationsTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData().withPhoto(new File("src/test/resources/photo.jpeg")).withName("Antonio").withLastname("Fagundes").
                    withAddress("Portugal, St.Barbara").withEmail("a.fagundes@stbarbara.com").withWorkphone("+0123456789").
                    withHomephone("+112").withMobile("+79110220303"));
        }
    }

    @Test
    public void testContactModifications() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData modContact = new ContactData().withId(modifiedContact.getId()).withPhoto(new File("src/test/resources/photo.jpeg")).withName("Antonio-Maria").
                withLastname("Fagundes").withAddress("Portugal, St.Barbara").
                withHomephone("+112").withMobile("+79110220303").withEmail("a.fagundes@stbarbara.com");
        app.contact().modify(modContact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(modContact)));
    }

    @Test(enabled = false)
    public void testContactBadModifications() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData modContact = new ContactData().withId(modifiedContact.getId()).withName("Antonio-Maria'").
                withLastname("Fagundes").withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").
                withHomephone("+112").withMobile("+79110220303").withEmail("a.fagundes@stbarbara.com"); //name with '
        app.contact().modify(modContact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }

}