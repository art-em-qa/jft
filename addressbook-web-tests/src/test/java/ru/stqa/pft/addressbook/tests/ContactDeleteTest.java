package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;


public class ContactDeleteTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.contact().createContactAndGroupIfGroupNotExist(new ContactData().withName("Antonio").withLastname("Fagundes").
                    withAddress("Portugal, St.Barbara").withWorkphone("+0123456789").withEmail("a.fagundes@stbarbara.com").
                    withGroup("actor"));
        }
    }

    @Test
    public void testUserDelete() throws Exception {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        assertEquals(app.contact().count(), before.size() - 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
