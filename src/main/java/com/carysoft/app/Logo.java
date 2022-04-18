package com.carysoft.app;

import java.awt.*;
import java.awt.image.BufferedImage;

 class Logo {

    static void printLogo() throws InterruptedException {


        /** Printing huge beautiful "Image Resizer 2.0" */
        int width = 200;
        int height = 20;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 15));

        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString("Image Resizer 2.0", 10, 20);


        for (int y = 0; y < height; y++) {
            StringBuilder sb = new StringBuilder();
            for (int x = 0; x < width; x++) {

                sb.append(image.getRGB(x, y) == -16777216 ? " " : "/");

            }

            if (sb.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(sb);

        }


        Thread.sleep(1000);

        for (int i = 0; i < 5; i++) {
            System.out.println("");
        }
        System.out.println("");

    }



}

