package ru.stqa.pft.mantis.models;

public class EMailMessage {
    public String to;
    public String text;

    public EMailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}