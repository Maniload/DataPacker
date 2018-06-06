package de.craplezz.datapacker.client.datapack.impl;

import de.craplezz.datapacker.client.datapack.DataPack;
import de.craplezz.datapacker.client.datapack.DataPackMeta;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * @author Overload
 * @version 1.0
 */
public class ZipDataPack extends DataPack {

    public ZipDataPack(File file) throws IOException {
        super(file);
    }

    @Override
    protected DataPackMeta loadMeta(File file) throws IOException {
        ZipFile zipFile = new ZipFile(file);
        ZipEntry entry = zipFile.getEntry("pack.mcmeta");
        if (entry != null) {
            return GSON.fromJson(new InputStreamReader(new ZipInputStream(zipFile.getInputStream(entry))), DataPackMeta.class);
        } else {
            throw new FileNotFoundException("No pack.mcmeta found.");
        }
    }

    @Override
    public String getName() {
        return super.getName().replace(".zip", "");
    }

}
