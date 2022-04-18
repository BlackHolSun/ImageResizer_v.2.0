# Image Resizer


is a command line image downloader and resizer. It can grab images from a webpage, resize them, and save on storage.

You can specify the output format using one of the following resize modes:
* fixed width (proportional height)
* scale by a decimal fraction

This picture resizer maintains the picture aspect ratio.

## Prerequisites
* Windows 10 or Mac 10.13+
* Chrome Browser v 83.x
* Java 11+ 



## Usage

To scale down to a specific width
```bash
java -jar [path to the .jar file] --p [folder for images storage] --u [link to a webpage] --w [target width]
```

To scale down by a decimal fraction
```bash
java -jar [path to the .jar file] --p [folder for images storage] --u [link to a webpage] --r [ratio]
```

### Examples

Resize to width 250px

```bash
java -jar C:\Users\user\\image-resizer-1.0-Chrome_v83.jar --p C:\Users\user\Documents\source --u https://trek.mycompany.org/directory/coreteam --w 250
```

Scale image by 0.5

```bash
java -jar C:\Users\user\\image-resizer-1.0-Chrome_v83.jar --p C:\Users\user\Documents\source --u https://trek.mycompany.org/directory/coreteam --r 0.5
```

If a website requires authorization the app will open a Chrome browser so user can authorize and go back to the command line in order to let app know it can continue.
### Output
Resized images will be saved into an automatically created **“Resized”** folder in a directory specified as --p attribute

## Build

```bash
maven install
```
This command is creating a jar file in **/target** folder.

### Chrome driver issues

If Chrome driver version doesn't support your Chrome version:

1. Download another Chrome driver here: https://chromedriver.chromium.org/downloads
2. Go to `\src\main\resources` and replace and old chromedriver.exe (Windows) or chromedriver (Mac OS) with the one that's been downloaded.
3. Restart your computer

### Future enhacements

 #### Images format
The app saves resized images only to **png** format since the `Resizer.resize()` method doesn't save images to jpeg (might be issues with codecs).
Format of a saved image can be changed in a `write` method

 ```  ImageIO.write(outputImage, "png", output);```

 #### Capturing images from any website
Currently the app can grab only images that enclosed in `<img>` tag by taking _src_ attribute's value.
The app should be able to get images from any website and with any type of link to an image.  
Some examples:

* image links have parameters after file extension like:

  ```java
  https://www.iprospect.com:443/-/media/local/us/our-team/name-last-name.jpg?		h=944&w=675&la=en&hash=580C9D6231E77AC59B2B732B145CA72E
  ```

* image links inside style attribute `background-image: url(https://url.jpg);`

* images can be parsed from HTML code:  
  ```<div data-v-b03adf64="" data-v-a0b2fad8="" class="hover__background figure__hover hover__background--fade" image="[object Object]" images="[object Object]" aspectratio="1_1" shape="square" imageborderwidth="1px" placeholdericon="physical" placeholdersize="40" secondaryimage="[object Object]"><!----> <!----></div>```

Possible solution: make user download images using Chrome extension and automate as much as possible with Selenium.  

#### Authorisation
The app determines if authorisation is required by condition:  

```java
  if (title.equals("Sign In with Auth0"))
```
from `ImageDownloader.grabImages()` method.  
There should be an additional condition if a website uses a different authorization service or if the method returns another document title.

#### Images to filter
The app doesn't resize image which name contains any of these words;

* "avatar"
* "level"
* "logo"

To customize it go to the `FilterImage` class.

#### Supported file extensions

* .jpeg
* .png
* .jpe

To customize it go to the `FilterImage` class.


### Suggested test scenarios and issues to fix

1. User inputs an invalid ratio.
2. If invalid input an error message should contain the corresponding reason (if an exception was thrown because wrong parameters there shouldn't be an error message about wrong URL format).

---
Diana Liubimova,
2020