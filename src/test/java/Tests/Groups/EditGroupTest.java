package Tests.Groups;

import Model.GroupData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().groupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData().withName("test 1"));
        }
    }

    @Test
    public void testEditGroup() {
        Set<GroupData> before = app.group().all();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(editedGroup.getId()).withName("Edit name").withHeader("Edit header").withFooter("Edit footer");
        app.group().edit(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(editedGroup);
        before.add(group);
        Assert.assertEquals(before, after);

    }
}
