package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.models.EMailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase{

    //@BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.ld", now);
        app.james().createUser(user, password);
        app.registration().start(user, email);
        //List<EMailMessage> eMailMessages = app.mail().waitForMail(2, 10000);
        List<EMailMessage> eMailMessages = app.james().waitForMail(user, password, 60000)
        String confiramationLink = findConfiramationLink(eMailMessages, email);
        app.registration().finish(confiramationLink, password);
        assertTrue(app.newSession().login(user, password));
    }

    private String findConfiramationLink(List<EMailMessage> eMailMessages, String email) {
        EMailMessage eMailMessage = eMailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(eMailMessage.text);
    }

    //@AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
