package de.craplezz.datapacker.client.datapack;

import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Overload
 * @version 1.0
 */
@Getter
public class DataPackDirectory {

    private final File file;
    private final List<DataPack> dataPacks;

    public DataPackDirectory(File file) {
        this.file = file;

        dataPacks = new ArrayList<>();

        refresh();
    }

    public void refresh() {
        if (!dataPacks.isEmpty()) {
            dataPacks.clear();
        }

        File[] dataPackFiles = file.listFiles(testFile -> testFile.isDirectory() || testFile.getName().endsWith(".zip"));
        if (dataPackFiles != null) {
            for (File dataPackFile : dataPackFiles) {
                try {
                    dataPacks.add(DataPackIO.load(dataPackFile));
                } catch (IOException e) {
                    System.out.println("Found non data pack file in data pack directory: " + dataPackFile.getName());
                }
            }
        }
    }

}
