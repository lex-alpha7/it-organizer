package ru.akhitev.organizer.web.applet;

import java.applet.Applet;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FileOrganizer extends Applet {

    public void paint(Graphics g){
        g.drawString("welcome to applet",150,150);
    }

    public void createFile() throws IOException {
        File file = new File("/home/aleksei_khitev/apl.txt");
        file.createNewFile();
    }
}
