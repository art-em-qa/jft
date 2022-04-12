package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;

import java.util.ArrayList;
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


    public void initUserModifications() {
        click(By.xpath("//img[@alt='Edit']"));
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
            new GroupHelper(wd).createGroup(new GroupData(contact.getGroup(), null, null));
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
        initUserModifications();
        fillFormNewUser(new UserData("Antonio-Maria", "Fagundes",
                "Portugal, St.Barbara", "+0123456789", "a.fagundes@stbarbara.com",
                null), false);
        submitUserModifications();
    }

    public List<UserData> getContactList() {
        List<UserData> contacts = new ArrayList<UserData>();
        List<WebElement> elements = wd.findElements(By.xpath("//*[@id='maintable']/tbody/tr[@name = 'entry']"));
        for (WebElement element : elements) {
            String firstname = element.findElement(By.xpath("td[3]")).getText();
            String lastname = element.findElement(By.xpath("td[2]")).getText();
            String address = element.findElement(By.xpath("td[4]")).getText();
            String emails = element.findElement(By.xpath("td[5]")).getText();
            String phones = element.findElement(By.xpath("td[6]")).getText();
            int id = Integer.parseInt(element.findElement(By.xpath("//td/input")).getAttribute("value"));
            UserData contact = new UserData(id,firstname, lastname, address, phones, emails);
            contacts.add(contact);
        }
        return contacts;
    }
}
