package Tests.Groups;

import Model.GroupData;
import Model.Groups;
import Tests.TestBase;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class EditGroupTest extends TestBase {

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test 1"));
        }
    }

    @Test
    public void testEditGroup() {
        Groups before = app.group().all();
        GroupData editedGroup = before.iterator().next();
        GroupData group = new GroupData()
                .withId(editedGroup.getId()).withName("Edit name").withHeader("Edit header").withFooter("Edit footer");
        app.group().edit(group);
        assertThat(app.group().count(), equalTo(before.size()   ));
        Groups after = app.group().all();
        assertEquals(after.size(), before.size());

        assertThat(after, equalTo(before.withEdited(group, editedGroup)));

    }
}
