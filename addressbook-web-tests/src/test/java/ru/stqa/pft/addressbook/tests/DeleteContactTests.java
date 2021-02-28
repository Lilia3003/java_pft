package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class DeleteContactTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Name").withLastname("LastName").withAddress("address").withPhone("22-22-22").withEmail("test@mail.ru").withGroup("1"), true);
    }
  }

  @Test
  public void testDeleteContact() throws InterruptedException {
    app.goTo().HomePage();
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);

    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);
    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
