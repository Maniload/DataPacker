package de.craplezz.datapacker.client.datapack;

import com.google.common.io.Files;
import de.craplezz.datapacker.client.datapack.impl.DirectoryDataPack;
import de.craplezz.datapacker.client.datapack.impl.ZipDataPack;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Overload
 * @version 1.0
 */
@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class DataPackIO {

    public static DataPack load(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                return new DirectoryDataPack(file);
            } else if (file.getName().endsWith(".zip")) {
                return new ZipDataPack(file);
            } else {
                throw new IOException("File is not a data pack.");
            }
        } else {
            throw new FileNotFoundException();
        }
    }

    public static void save(DataPack dataPack, File file) throws IOException {
        copy(dataPack.getFile(), file);
    }

    private static void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            Files.copy(sourceLocation, targetLocation);
        }
    }

    private static void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : source.list()) {
            copy(new File(source, f), new File(target, f));
        }
    }

}
