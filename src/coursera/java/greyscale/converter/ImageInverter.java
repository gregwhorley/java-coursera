package coursera.java.greyscale.converter;
/**
 * Write a description of ImageInverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;

import java.io.File;

public class ImageInverter {
    //select images
    public void selectAndInvert() {
        DirectoryResource dirResource = new DirectoryResource();
        for (File file: dirResource.selectedFiles()) {
            ImageResource inImage = new ImageResource(file);
            ImageResource invertedImage = makeInverted(inImage);
            String fileName = inImage.getFileName();
            invertedImage.setFileName("inverted-"+fileName);
            invertedImage.save();
            invertedImage.draw();
        }
        
    }
    //invert the image by subtracting red, green, and blue values by 255
    public ImageResource makeInverted(ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return outImage;
    }
}
