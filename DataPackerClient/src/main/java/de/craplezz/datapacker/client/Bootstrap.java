package de.craplezz.datapacker.client;

import de.craplezz.datapacker.client.gui.DataPackerForm;

import javax.swing.UIManager;

/**
 * @author Overload
 * @version 1.0
 */
public class Bootstrap {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }

        DataPackerForm form = new DataPackerForm();
    }

}
