package com.carysoft.app;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Resizer {


    void resize(ArrayList<String> allFiles, String path, String URL, int width, double ratio) throws InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        Validator.ifFolderEmpty(allFiles);


        ArrayList<String> imagesInFolder = FilterImage.filter(allFiles);
        File destFolder = new File(String.format("%s/resized/", path));
        destFolder.mkdir();

        /**RESIZE */
        imagesInFolder.forEach(fileName ->
        {

            File input;
            int startIndex = fileName.lastIndexOf(".");
            String extention = fileName.substring(startIndex);


            //check if path ends with "/"
            if (!path.substring(path.length() - 1).equals("\\")) {
                input = new File(path + File.separator + fileName);
            } else {
                input = new File(path + fileName);
            }


            try {
                BufferedImage image = ImageIO.read(input);
                BufferedImage outputImage;

                //if user provides ratio image will be scaled by ratio
                if (width == 0) {
                    outputImage = resizeImage(image, image.getWidth(), ratio);
                    System.out.printf("%s resized!%n", fileName);
                }
                //if user provides width image will be scaled down to that width
                else if (image.getWidth() > width) {
                    outputImage = resizeImage(image, width, ratio);
                    System.out.printf("%s resized!%n", fileName);

                } else {
                    //if image width is less than desirable width images won't be scaled
                    outputImage = image;
                    System.out.printf("%s saved%n", fileName);

                }


                File output = new File(destFolder + "/" + fileName.replace(extention, "") + ".png");
                ImageIO.write(outputImage, "png", output);


            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        System.out.println("All set!\n");
        System.exit(0);

    }

    private BufferedImage resizeImage(BufferedImage img, int width, double ratio) {

        int divisor = (int) (1 / ratio);
        Image tmp = img.getScaledInstance(width / divisor, -1, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(tmp.getWidth(null), tmp.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
