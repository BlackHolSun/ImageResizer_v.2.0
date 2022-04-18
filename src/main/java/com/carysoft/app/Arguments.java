package com.carysoft.app;

public class Arguments {

    private String path;
    private String URL;
    private int width;
    private double ratio;

    public Arguments(String path, String URL, int width, double ratio) {
        this.path = path;
        this.URL = URL;
        this.width = width;
        this.ratio = ratio;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }
}
