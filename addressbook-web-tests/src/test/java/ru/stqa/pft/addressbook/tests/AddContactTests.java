package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts(){
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[]{new ContactData().withFirstname("Firstname1").withLastname("Lastname 1").withAddress("Address 1").withEmail("Email1@mail.ru").withGroup("Group1")});
    list.add(new Object[]{new ContactData().withFirstname("Firstname2").withLastname("Lastname 2").withAddress("Address 2").withEmail("Email2@mail.ru").withGroup("Group2")});
    list.add(new Object[]{new ContactData().withFirstname("Firstname3").withLastname("Lastname 3").withAddress("Address 3").withEmail("Email3@mail.ru").withGroup("Group3")});


    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void testAddContact(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    File photo  = new File("src/test/resources/test.png");
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> (c.getId())).max().getAsInt()))));

  }

  @Test (enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo  = new File("src/test/resources/test.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());

  }

  @Test (enabled = false)
  public void testBadAddContact() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("NameХ'").withLastname("LastNameХ").withAddress("address").withPhone("22-22-22").withEmail("test@mail.ru").withGroup("1");
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }
}
