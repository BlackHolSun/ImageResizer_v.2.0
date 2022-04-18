package com.carysoft.app;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;

class ImageDownloader {

    private String imgDestinationFolder;
    private String pageURL;

    public ImageDownloader(String imgDestinationFolder, String pageURL) {
        this.imgDestinationFolder = imgDestinationFolder;
        this.pageURL = pageURL;
    }

    public void grabImages() throws IOException, InterruptedException {
        System.out.println();
        System.out.println("Opening Chrome browser.");
        Thread.sleep(2000);
        //getting a document
        Document document = Jsoup
                .connect(pageURL)
                .userAgent("")
                .timeout(5000) //3000 throws timeout exception 2 out 3 times, may be specific for the website
                .get();


        //if authorization required
        //this part is specific to Trek
        String title = document.select("title").text();
        System.out.println(title);

        if (title.equals("Sign In with Auth0")) {

            Thread.sleep(2000);
            System.out.println();
            System.out.println("Please login into Trek and switch back to your command prompt");
            Thread.sleep(2000);
            //Selenium opens Chrome browser
            List<WebElement> images = new Authenticator().getImageElements(pageURL);
            images.forEach(img -> {
                String strImgUrl = img.getAttribute("src");

                downloadImage(strImgUrl, imgDestinationFolder);

            });
        }

        //if no authorisation required
        else {

            Elements imageElements = document.select("img");
            imageElements.forEach(img -> {
                String strImgURL = img.attr("abs:src");
                downloadImage(strImgURL, imgDestinationFolder);
            });

        }


    }

    private void downloadImage(String imgURL, String imgDestinationFolder) {

        String strImgName = imgURL.substring(imgURL.lastIndexOf("/") + 1);
        System.out.println("Saving: " + strImgName);


        try {
            URL urlImage = new URL(imgURL);

            InputStream inputStream = urlImage.openStream();
            byte[] buffer = new byte[4096];
            int n = -1;


            OutputStream outputStream = new FileOutputStream(imgDestinationFolder + "/" + strImgName);

            while ((n = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, n);
            }

            outputStream.close();
            System.out.println("Image saved!" + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
