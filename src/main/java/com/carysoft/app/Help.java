package com.carysoft.app;

public class Help {

    static void showHelp(){
        System.out.println("Usage: " + "\n"
               + "for resizing to the certain width \n"

                + "\t imageResizer.jar "
                + "--p <path to destination folder > "
                + "--u <link to the webpage> "
                + "--w <target width>");

        System.out.println("to scale by certain ratio \n"
                + "\t imageResizer.jar "
                + "--p <path to destination folder > "
                + "--u <link to the webpage "
                + "--r <ratio  - from 0.1 to 0.9 >");

        System.exit(1);
    }
}
