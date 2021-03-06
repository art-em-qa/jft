package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.testng.Assert.assertFalse;

public class ContactRemoveFromGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("Test1").withHeader("Test2").withFooter("Test3"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().openHomePage();
            app.contact().create(new ContactData().withPhoto(new File("src/test/resources/photo.jpeg")).withName("Antonio").withLastname("Fagundes").
                    withAddress("Portugal, St.Barbara").withEmail("a.fagundes@stbarbara.com").withWorkphone("+0123456789").
                    withHomephone("+112").withMobile("+79110220303"));
        }
    }

    @Test
    public void testDeleteContactFromToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        int contactId = contact.getId();
        if (contact.getGroups().size() == 0) {
            GroupData group = app.db().groups().iterator().next();
            app.contact().addInGroup(contact, group);
            app.contact().openHomePage();
        }
        ContactData contactRemove = app.db().getContactInGroup(contactId);
        Groups group = contactRemove.getGroups();
        app.contact().contactRemoveFromGroup(contactRemove);
        assertFalse(contact.getGroups().contains(group));
    }
}
