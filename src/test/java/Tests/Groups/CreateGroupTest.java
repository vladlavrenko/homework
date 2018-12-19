package Tests.Groups;

import Model.GroupData;
import Tests.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class CreateGroupTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().groupPage();
    }

    @Test
    public void testCreateGroup() {
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test 1").withHeader("test 2").withFooter("test 3");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }

}