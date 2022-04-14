package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitCreateNewUser() {
        click(By.name("submit"));
    }

    public void fillFormNewUser(UserData userData, boolean creation) {
        type(By.name("firstname"), userData.getFirstname());
        type(By.name("lastname"), userData.getLastname());
        type(By.name("address"), userData.getAddress());
        type(By.name("work"), userData.getWorkphone());
        type(By.name("email"), userData.getEmail());
        if (creation) {
            isGroupCreatedByName(userData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public boolean isGroupCreatedByName(String groupName) {
        try{
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupName);
            return true;
        } catch (NoSuchElementException e){
            return false;
        }
    }

    public void initCreateNewUser() {
        click(By.linkText("add new"));
    }

    public void deleteSelectedUsers() {
        click(By.xpath("//input[@value='Delete']"));
    }


    public void initUserModificationsById(int id) {
        click(By.cssSelector("a[href='edit.php?id="+ id + "']"));
    }

    public void submitUserModifications() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void createContactAndGroupIfGroupNotExist(UserData contact) {
        initCreateNewUser();
        if(! isGroupCreatedByName(contact.getGroup())){
            new GroupHelper(wd).create(new GroupData().withName(contact.getGroup()));
            initCreateNewUser();
        }
        fillFormNewUser(contact, true);
        submitCreateNewUser();
        stopCreateAndOpenHomePage();
    }

    public void createContact(UserData contact) {
        initCreateNewUser();
        fillFormNewUser(contact, true);
        submitCreateNewUser();
        stopCreateAndOpenHomePage();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" +id + "']")).click();
    }

    public void delete(UserData contact) {
        selectContactById(contact.getId());
        deleteSelectedUsers();
        acceptAllert();
        openHomePage();
    }

    private void stopCreateAndOpenHomePage() {
        click(By.linkText("home page"));
    }

    public boolean isGroupCreatedByIndex(int index) {
        try {
            new Select (wd.findElement(By.name("new_group"))).selectByIndex(index);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void modificateContact(UserData contact) {
        initUserModificationsById(contact.getId());
        fillFormNewUser(contact, false);
        submitUserModifications();
    }

    public void modify(UserData modContact) {
        modificateContact(modContact);
        openHomePage();
    }

    public void acceptAllert() {
        wd.switchTo().alert().accept();
    }

    public void openHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']/tbody/tr[@name = 'entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String emails = element.findElement(By.xpath("td[5]")).getText();
            String phones = element.findElement(By.xpath("td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("//td/input")).getAttribute("value"));
            UserData contact = new UserData().withId(id).withName(firstname).withLastname(lastname).
                    withAddress(address).withWorkphone(phones).withEmail(emails);
            contacts.add(contact);
        }
        return contacts;
    }
}
