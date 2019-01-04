package Tests.Groups;

import Model.GroupData;
import Model.Groups;
import Tests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CreateGroupTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().groupPage();
    }

    @Test
    public void testCreateGroup() {
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test 1").withHeader("test 2").withFooter("test 3");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

}