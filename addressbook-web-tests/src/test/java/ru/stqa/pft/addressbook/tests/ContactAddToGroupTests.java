package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.devtools.v95.webaudio.model.ContextState;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactAddToGroupTests extends TestBase {

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
    public void testContactAddToGroup() {
        Contacts contacts = app.db().contacts();
        Contacts contactToAdd = new Contacts();
        for (ContactData contactData : contacts){
            if(contactData.getGroups().size() == 0) {
                ContactData contactWitoutGroup = contactData;
                contactToAdd.add(contactWitoutGroup);
                break;
            } else {
                ContactData contact = contactData;
                int contactId = contact.getId();
                ContactData contactRemove = app.db().getContactInGroup(contactId);
                app.contact().contactRemoveFromGroup(contactRemove);
                app.contact().openHomePage();
                contactToAdd.add(contactRemove);
                break;
            }
        }
        GroupData group = app.db().groups().iterator().next();
        System.out.println(contactToAdd);
        ContactData contact = contactToAdd.iterator().next();
        app.contact().addInGroup(contact, group);
        assertTrue(contact.getGroups().contains(group));
    }

}
