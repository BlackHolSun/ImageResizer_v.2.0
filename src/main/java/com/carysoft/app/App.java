package com.carysoft.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.carysoft.app.Logo.printLogo;


public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        /** Help*/
        if(args == null) Help.showHelp();
        else if(args[0].equalsIgnoreCase("--help")) Help.showHelp();

        /** Get an object containing parameters */
        Arguments programArgs = new ArgsParser().parse(args);

        final String pathname = programArgs.getPath();
        final String url = programArgs.getURL();
        final int width = programArgs.getWidth();
        final double ratio = programArgs.getRatio();

        /** Checking parameters*/

        Validator.pathValidator(pathname);
        Validator.urlValidator(url);


        /** Print logo */
        printLogo();


        /** CONNECT AND DOWNLOAD */
        new ImageDownloader(pathname, url).grabImages();


        File folder = new File(pathname);
        ArrayList<String> allFiles = new ArrayList<>(Arrays.asList(folder.list()));

        new Resizer().resize(allFiles, pathname, url, width, ratio);
    }
}
