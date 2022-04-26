package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.checkerframework.checker.units.qual.C;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contact = generateContact(count);
        if (format.equals("xml")) {
            saveAsXml(contact, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contact, new File((file)));
        } else {
            System.out.println("Неизвестный формат" + format);
        }

    }

    private void saveAsJson(List<ContactData> contact, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contact);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contact, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(ContactData.class);
        String xml = xStream.toXML(contact);
        try ( Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private static List<ContactData> generateContact(int count) {
        List<ContactData> groups = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            groups.add(new ContactData().withPhoto(new File(String.format("src/test/resources/photo.jpeg"))).
                    withName(String.format("FirstnameTest %s", i)).withLastname(String.format("LastnameTest %s", i)).
                    withAddress(String.format("AddressTest %s", i)).withHomephone(String.format("0%s", i)).
                    withWorkphone(String.format("0111%s", i)).
                    withHomephone2(String.format("3129837", i)).withMobile(String.format("+79999", i)).
                    withEmail(String.format("fagundes@ail.", i)));
        }
        return groups;
    }

}
