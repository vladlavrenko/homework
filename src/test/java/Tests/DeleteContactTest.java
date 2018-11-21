package Tests;

import org.testng.annotations.Test;

public class DeleteContactTest extends  TestBase{
    @Test
    public void testDeleteContact() {
        app.getContactHelper().initContactEdition();
        app.getContactHelper().submitContactDeletion();
     }
}
