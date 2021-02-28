package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class AddContactTests extends TestBase {


  @Test
  public void testAddContact() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("NameХ").withLastname("LastNameХ").withAddress("address").withPhone("22-22-22").withEmail("test@mail.ru").withGroup("1");
    app.contact().create(contact, true);
    app.goTo().HomePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> (c.getId())).max().getAsInt());
    before.add(contact);

    Assert.assertEquals(before, after);
  }

}
