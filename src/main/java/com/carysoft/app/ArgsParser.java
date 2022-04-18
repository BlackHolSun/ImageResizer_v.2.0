package com.carysoft.app;


public class ArgsParser {

    private String path;
    private String URL;
    private int width = 0;
    private double ratio = 1.0;

    Arguments parse(String[] args) throws InterruptedException {

        for (int i = 0; i < args.length-1; i++) {
            String element = args[i];
            String next = args[i + 1];
            if (element.equalsIgnoreCase("--p")) {
                path = next;
                i++;
            } else if (element.equalsIgnoreCase("--u")) {
                URL = next;
                i++;
            } else if (element.equalsIgnoreCase("--w")) {
                width = Integer.parseInt(next);
            } else if (element.equalsIgnoreCase("--r")) {
                ratio = Double.parseDouble(next);
            } else{
                System.out.println("Invalid argument, try running the app again");
                Thread.sleep(1000);
                Help.showHelp();

                System.exit(1);
            }
        }
        if (path == null) {
            System.out.println("Invalid argument, try running the app again");
            Thread.sleep(1000);
            Help.showHelp();

            System.exit(1);
        }

        if (URL == null) {
            System.out.println("Invalid argument, try running the app again");
            Thread.sleep(1000);
            Help.showHelp();
            System.exit(1);
        }

        return new Arguments(path, URL, width, ratio);
    }
}
