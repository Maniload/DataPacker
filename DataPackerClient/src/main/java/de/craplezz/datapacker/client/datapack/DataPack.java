package de.craplezz.datapacker.client.datapack;

import com.google.gson.Gson;
import lombok.Getter;

import java.io.File;
import java.io.IOException;

/**
 * @author Overload
 * @version 1.0
 */
@Getter
public abstract class DataPack {

    protected static final Gson GSON = new Gson();

    protected final File file;

    protected DataPackMeta meta;

    protected DataPack(File file) throws IOException {
        this.file = file;

        meta = loadMeta(file);
    }

    protected abstract DataPackMeta loadMeta(File file) throws IOException;

    public String getName() {
        return file.getName();
    }

    @Override
    public String toString() {
        return getName() + " (" + getMeta().getPack().getDescription() + ")";
    }

}
