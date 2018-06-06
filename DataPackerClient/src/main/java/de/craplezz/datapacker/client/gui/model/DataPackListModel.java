package de.craplezz.datapacker.client.gui.model;

import de.craplezz.datapacker.client.datapack.DataPack;
import de.craplezz.datapacker.client.datapack.DataPackDirectory;
import lombok.Getter;

import javax.swing.DefaultListModel;

/**
 * @author Overload
 * @version 1.0
 */
@Getter
public class DataPackListModel extends DefaultListModel<DataPack> {

    private final DataPackDirectory directory;

    public DataPackListModel(DataPackDirectory directory) {
        this.directory = directory;

        refresh();
    }

    public void refresh() {
        if (getSize() > 0) {
            clear();
        }

        directory.refresh();

        for (DataPack dataPack : directory.getDataPacks()) {
            addElement(dataPack);
        }
    }

}
