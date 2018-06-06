package de.craplezz.datapacker.client.gui;

import com.google.common.io.MoreFiles;
import de.craplezz.datapacker.client.datapack.DataPack;
import de.craplezz.datapacker.client.datapack.DataPackDirectory;
import de.craplezz.datapacker.client.datapack.DataPackIO;
import de.craplezz.datapacker.client.gui.model.DataPackListModel;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import java.io.File;
import java.io.IOException;

/**
 * @author Overload
 * @version 1.0
 */
public class DataPackerForm extends JFrame {

    private JPanel contentPane;
    private JList<DataPack> workspaceList;
    private JList<DataPack> worldList;
    private JButton loadWorkspaceButton;
    private JButton loadWorldButton;
    private JButton refreshButton;
    private JButton installButton;
    private JButton uninstallButton;
    private JButton databaseButton;
    private JScrollPane workspacePanel;
    private JScrollPane worldPanel;

    private DataPackListModel workspaceListModel, worldListModel;

    public DataPackerForm() {
        setTitle("DatePacker");
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setContentPane(contentPane);

        loadWorkspaceButton.addActionListener(event -> {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(this);

            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                workspaceList.setModel(workspaceListModel = new DataPackListModel(new DataPackDirectory(selectedFile)));

                ((TitledBorder) workspacePanel.getBorder()).setTitle("Workspace (" + selectedFile.getName() + ")");
                repaint();
            }

        });

        loadWorldButton.addActionListener(event -> {

            JFileChooser fileChooser = new JFileChooser(new File(System.getenv("APPDATA"), ".minecraft"));
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.showOpenDialog(this);

            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                worldList.setModel(worldListModel = new DataPackListModel(new DataPackDirectory(new File(selectedFile, "datapacks"))));

                ((TitledBorder) worldPanel.getBorder()).setTitle("World (" + selectedFile.getName() + ")");
                repaint();
            }

        });

        refreshButton.addActionListener(event -> {

            if (workspaceListModel != null) {
                workspaceListModel.refresh();
            }

            if (worldListModel != null) {
                worldListModel.refresh();
            }

        });

        installButton.addActionListener(event -> {

            if (worldListModel != null) {
                for (DataPack dataPack : workspaceList.getSelectedValuesList()) {
                    try {
                        DataPackIO.save(dataPack, new File(worldListModel.getDirectory().getFile(), dataPack.getFile().getName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                worldListModel.refresh();
            }

        });

        uninstallButton.addActionListener(event -> {

            if (worldListModel != null) {
                for (DataPack dataPack : worldList.getSelectedValuesList()) {
                    try {
                        MoreFiles.deleteRecursively(dataPack.getFile().toPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                worldListModel.refresh();
            }

        });

        pack();
        setVisible(true);
    }

}
