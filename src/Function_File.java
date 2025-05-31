import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Function_File
{
    GUI gui;
    String fileName;
    String filePath;

    public Function_File(GUI gui)
    {
        this.gui = gui;
    }

    public void newFile()
    {
        gui.textArea.setText("");
        gui.window.setTitle("New JavaPad");
        fileName = null;
        filePath = null;
    }

    public void open()
    {
        FileDialog fd = new FileDialog(gui.window, "Open File", FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFile() != null)
        {
            fileName = fd.getFile();
            filePath = fd.getDirectory();
            gui.window.setTitle(fileName);
        }
        System.out.println("File address and file name: " + filePath + fileName); // Added for debugging purposes to check if the method is reading the file address and name

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath + fileName)); // You need the address to read the file

            gui.textArea.setText("");

            String Line = null;

            while((Line = br.readLine()) != null)
            {
                gui.textArea.append(Line + "\n");
            }
            br.close();

        } catch(Exception e) {
            System.out.println("File not found");
        }
    }

    public void save()
    {
        if(fileName == null)
        {
            saveAs();
        }
        else
        {
            try {
                FileWriter fw = new FileWriter(filePath + fileName);
                fw.write(gui.textArea.getText());
                gui.window.setTitle(fileName);
                fw.close();

            } catch(Exception e) {
                System.out.println("Error saving file");
            }
        }
    }

    public void saveAs()
    {
        FileDialog fd = new FileDialog(gui.window, "Save File", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile() != null)
        {
            fileName = fd.getFile();
            filePath = fd.getDirectory();
            gui.window.setTitle(fileName);
        }

        try {
            FileWriter fw = new FileWriter(filePath + fileName);
            fw.write(gui.textArea.getText());
            fw.close();

        } catch(Exception e) {
            System.out.println("Error saving file");
        }
    }

    public void exit()
    {
        System.exit(0);
    }
}
