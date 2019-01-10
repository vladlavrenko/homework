package Generators;

import Model.GroupData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupGenerator {
    @Parameter(names = "-a")
    int count;
    @Parameter(names = "-b")
    String file;

    public static void main(String[] args) throws IOException {
        GroupGenerator generator = new GroupGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch(ParameterException ex) {
            jCommander.usage();
            generator.run();
            return;
        }

    }

    private void run() throws IOException {
        List<GroupData> groups = generateGroups(count);
        save(groups, new File(file));
    }

    private List<GroupData> generateGroups(int count) {
        List<GroupData> groups = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            groups.add(new GroupData().withName(String.format("Name %s", i)).withHeader(String.format("Header %s", i)).withFooter(String.format("Footer %s", i)));
        }
        return groups;
    }

    private void save(List<GroupData> groups, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (GroupData group : groups) {
            writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getGroupHeader(),group.getGroupFooter()));
        }
        writer.close();
    }
}
