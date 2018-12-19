package Tests.Groups;

import Model.GroupData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class CreateGroupTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().groupPage();
    }
    @Test
    public void testCreateGroup() {
        List<GroupData> before = app.group().list();
        GroupData group = new GroupData().withName("test 1").withHeader("test 2").withFooter("test 3");
        app.group().create(group);
        List<GroupData> after = app.group().list();

        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}