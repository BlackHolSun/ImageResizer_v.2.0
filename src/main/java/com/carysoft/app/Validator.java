package com.carysoft.app;

import org.apache.commons.validator.routines.UrlValidator;


import java.io.File;
import java.util.ArrayList;


public class Validator {

    static void urlValidator(String URL) throws InterruptedException {

        if (!checkURL(URL)) {
            System.out.println("Invalid URL, closing the app");
            Thread.sleep(2000);
            System.exit(1);
        }

    }

    private static boolean checkURL(String url) {


        UrlValidator defaultValidator = new UrlValidator();
        return defaultValidator.isValid(url);
    }


    static void pathValidator(String pathname) throws InterruptedException {

        File file = new File(pathname);
        if (!file.exists()) {
            System.out.println("Invalid path, closing the app");
            Thread.sleep(2000);
            System.exit(1);
        }


    }

    static void ifFolderEmpty(ArrayList files) throws InterruptedException{

        if (files.isEmpty()) {
            System.out.println("No images downloaded, try to run the app again");
            Thread.sleep(2000);
            System.out.println("Exiting");
            Thread.sleep(1000);
            System.exit(1);
        }



    }

}
