/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.methods;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Peter
 */
public class ImageTool {
    
    public static ImageIcon createImageByPath(String path) {
        return new ImageIcon(path);
    }

    public static ImageIcon scaleImage(ImageIcon image, int w, int h) {
        return new ImageIcon(image.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
    }
}
