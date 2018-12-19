package Tests.Groups;

import Model.GroupData;
import Model.Groups;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class DeleteGroupTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test 1"));
        }
    }

    @Test
    public void testGroupDelete() {
        Groups before = app.group().all();
        //Беру первую попавшуюся группу целиком
        GroupData deletedGroup = before.iterator().next();
        //Именно эту группу и удаляю
        app.group().delete(deletedGroup);
        Groups after = app.group().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        // А тут из набора групп удаляю удаленную группу
        before.remove(deletedGroup);
        assertThat(after, equalTo(before.without(deletedGroup)));

    }


}