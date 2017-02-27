package com.fruit.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * 这是压缩图片的对象
 *
 * @author Administrator
 */
public class CompressPicUtils {
    private static final Logger logger = LoggerFactory.getLogger(CompressPicUtils.class);
    private File inputFile; // 文件对象
    private File outputFile; // 输出图路径
    private int outputWidth; // 默认输出图片宽
    private int outputHeight; // 默认输出图片高
    private boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

    public CompressPicUtils() {

    }


    /**
     * 进行图片压缩
     *
     * @return
     */
    public boolean compressPic() {
        try {
// 获得源文件
            if (!inputFile.exists()) {
                return false;
            }
            Image img = ImageIO.read(inputFile);
// 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                return false;
            } else {
                int newWidth;
                int newHeight;
// 判断是否是等比缩放
                if (this.proportion == true) {
// 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
// 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) (img.getWidth(null) / rate);
                    newHeight = (int) (img.getHeight(null) / rate);
                } else {
                    newWidth = outputWidth; // 输出的图片宽度
                    newHeight = outputHeight; // 输出的图片高度
                }
                BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                FileOutputStream out = new FileOutputStream(outputFile);
// JPEGImageEncoder可适用于其他图片类型的转换
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
                out.close();
            }
        } catch (IOException ex) {
            logger.error("compressPic error", ex);
            return false;
        }
        return true;
    }

    public boolean compressPic(File inputFile, File outputFile, int width, int height, boolean gp) {
// 输入图路径
        this.inputFile = inputFile;
// 输出图路径
        this.outputFile = outputFile;
// 设置图片长宽
        this.outputWidth = width;
        this.outputHeight = height;
// 是否是等比缩放 标记
        this.proportion = gp;
        return compressPic();
    }

    /**
     * 按最大边来压缩
     *
     * @param inputFile
     * @param outputFile
     * @param maxWidth
     * @return
     */
    public boolean compressPic(File inputFile, File outputFile, int maxWidth) {
        // 输入图路径
        this.inputFile = inputFile;
        // 输出图路径
        this.outputFile = outputFile;
        FileOutputStream out = null;
        try {
            // 获得源文件
            if (!inputFile.exists()) {
                return false;
            }
            Image img = ImageIO.read(inputFile);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                return false;
            } else {
                int newWidth;
                int newHeight;
                double rate = 1;

                int oldWidth = img.getWidth(null);
                int oldHeight = img.getHeight(null);
                if (oldWidth > oldHeight) {
                    if (oldWidth > maxWidth) {
                        rate = maxWidth * 1.0 / oldWidth;
                        newWidth = maxWidth;
                        newHeight = new Double(oldHeight * rate).intValue();
                    } else {
                        newWidth = oldWidth;
                        newHeight = oldHeight;
                    }
                } else {
                    if (oldHeight > maxWidth) {
                        rate = maxWidth * 1.0 / oldHeight;
                        newHeight = maxWidth;
                        newWidth = new Double(oldWidth * rate).intValue();
                    } else {
                        newWidth = oldWidth;
                        newHeight = oldHeight;
                    }

                }

                BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                out = new FileOutputStream(outputFile);
                // JPEGImageEncoder可适用于其他图片类型的转换
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
                out.close();

            }
        } catch (IOException ex) {
            logger.error("compressPic error", ex);
            try {
                //如果图片压缩失败,就复制原图
                copyFile(inputFile, outputFile);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public void copyFile(File f1, File f2) throws Exception {


        int length = 2097152;
        FileInputStream in = new FileInputStream(f1);
        FileOutputStream out = new FileOutputStream(f2);
        byte[] buffer = new byte[length];
        while (true) {
            int ins = in.read(buffer);
            if (ins == -1) {
                in.close();
                out.flush();
                out.close();
            } else
                out.write(buffer, 0, ins);
        }
    }

}