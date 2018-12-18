package Tests.Groups;

import Model.GroupData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

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
        List<GroupData> before = app.group().list();
        int index = before.size()-1;
        GroupData group = new GroupData()
                .withId(before.get(before.size()-1).getId()).withName("Edit name").withHeader("Edit header").withFooter("Edit footer");
        app.group().edit(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
