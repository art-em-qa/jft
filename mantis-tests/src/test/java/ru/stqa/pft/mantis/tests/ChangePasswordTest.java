package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.models.EMailMessage;
import ru.stqa.pft.mantis.models.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ChangePasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void passwordResetTest() throws MessagingException, IOException {
        app.registration().loginAdmin();
        UserData user = app.db().users()
                .stream().filter((u) -> (!u.getLogin().equals("administrator")))
                .collect(Collectors.toList()).iterator().next();
        app.registration().pressResetByAdmin(user);
        List<EMailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, user.getEmail());
        String newPassword = "password2";
        app.registration().finish(confirmationLink, newPassword);
        app.newSession().login(user.getLogin(), newPassword);
    }

    public String findConfirmationLink(List<EMailMessage> mailMessages, String email) {
        EMailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}