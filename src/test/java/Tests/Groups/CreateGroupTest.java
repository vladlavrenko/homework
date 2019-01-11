package Tests.Groups;

import Model.GroupData;
import Model.Groups;
import Tests.TestBase;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class CreateGroupTest extends TestBase {

    @DataProvider
    public Iterator<Object[]> validGroupsFromXml() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/Resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
           xml += line;
            line = reader.readLine();
        }
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/Resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
        }.getType());
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void checkPreconditions() {
        app.goTo().groupPage();
    }

    @Test(dataProvider = "validGroupsFromJson")
    public void testCreateGroup(GroupData group) {
        Groups before = app.group().all();
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
    }

}