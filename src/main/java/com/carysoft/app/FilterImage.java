package com.carysoft.app;

import java.util.ArrayList;
import java.util.List;


public class FilterImage {

    static ArrayList<String> filter(List<String> allFiles) throws InterruptedException{


        ArrayList<String> imagesInFolder = new ArrayList<>();
        for (String file : allFiles) {

            String extension = file.substring(file.lastIndexOf(".") + 1);


            if ((extension.equals("png")
                    || extension.equalsIgnoreCase("jpg")
                    || extension.equalsIgnoreCase("jpeg")
                    || extension.equalsIgnoreCase("jpe"))
                    && !file.contains("avatar")
                    && !file.contains("level")
                    && !file.contains("logo")
            ) {

                imagesInFolder.add(file);
            }
        }

        if(imagesInFolder.isEmpty()) {

            System.out.println("No images downloaded, try to running an app again");
            Thread.sleep(2000);
            System.out.println("Exiting");
            Thread.sleep(1000);
            System.exit(0);
        }

        System.out.println();
        System.out.println("Images to resize: " + imagesInFolder.size());
        
        return imagesInFolder;

    }

}
