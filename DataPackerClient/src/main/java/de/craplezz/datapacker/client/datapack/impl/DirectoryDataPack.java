package de.craplezz.datapacker.client.datapack.impl;

import de.craplezz.datapacker.client.datapack.DataPack;
import de.craplezz.datapacker.client.datapack.DataPackMeta;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Overload
 * @version 1.0
 */
public class DirectoryDataPack extends DataPack {

    public DirectoryDataPack(File file) throws IOException {
        super(file);
    }

    @Override
    protected DataPackMeta loadMeta(File file) throws IOException {
        return GSON.fromJson(new InputStreamReader(new FileInputStream(new File(file, "pack.mcmeta"))), DataPackMeta.class);
    }

}
