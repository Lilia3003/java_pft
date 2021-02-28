package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ModificationContactTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName").withAddress("address").withPhone("22-22-22").withEmail("test@mail.ru").withGroup("1"), true);
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {

    app.goTo().HomePage();

    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId()).withFirstname("modfirstname").withLastname("modlastname").withAddress("modadress").withPhone("223322").withEmail("modemail").withGroup(null);
    app.contact().modify(contact);

    app.goTo().HomePage();

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
