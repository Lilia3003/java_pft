package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase {


  @Test
  public void testAddContact() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("NameХ").withLastname("LastNameХ").withHomePhone("homePhone").withMobilePhone("mobilePhone").withWorkPhone("workPhone").withAddress("address").withEmail("test@mail.ru").withGroup("1");
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> (c.getId())).max().getAsInt()))));

  }

  @Test
  public void testBadAddContact() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("NameХ'").withLastname("LastNameХ").withAddress("address").withEmail("test@mail.ru").withGroup("1");
    app.contact().create(contact, true);
    app.goTo().HomePage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before));

  }
}
