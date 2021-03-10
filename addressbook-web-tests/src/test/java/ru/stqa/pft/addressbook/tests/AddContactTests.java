package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase {


  @Test
  public void testAddContact() throws Exception {
    Contacts before = app.contact().all();
    File photo  = new File("src/test/resources/test.png");
    ContactData contact = new ContactData().withFirstname("NameХ").withLastname("LastNameХ")
            .withAddress("address").withPhoto(photo).withEmail("test@mail.ru").withGroup("1");
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
