package com.ivy.tool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by admin on 2017/11/3.
 */
public class DownLoadPicture {
    /**
     * 根据图片的url，下载图片到本地，并返回图片的base64字符串
     * http://img.gushiwen.org/authorImg/yuanzhen.jpg
     */


    public static void main(String[] args) {
        String url = "http://img.gushiwen.org/authorImg/yuanzhen.jpg";
        ArrayList<String> urlList = new ArrayList<String>();
        urlList.add(url);
        try {
            String s = encodeBase64File(url,"唐代_yuanzhen");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 将base64字符解码保存文件
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void decoderBase64File(String base64Code, String targetPath)
            throws Exception {
        byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();

    }

    /**
     * 将base64字符保存文本文件
     * @param base64Code
     * @param targetPath
     * @throws Exception
     */

    public static void toFile(String base64Code, String targetPath)
            throws Exception {

        byte[] buffer = base64Code.getBytes();
        FileOutputStream out = new FileOutputStream(targetPath);
        out.write(buffer);
        out.close();
    }

    /**
     * 将文件转成base64 字符串
     * @param urlString 文件网络地址
     * @Param pictureFileName 文件保存本地的名称
     * @return
     * @throws Exception
     */

    public static String encodeBase64File(String urlString,String pictureFileName) throws Exception {
        URL url = new URL(urlString);
        System.out.println("filename="+url.getFile());

        //下载
        DataInputStream dataInputStream = new DataInputStream(url.openStream());
        String imageName = "D://poetPicture//" + pictureFileName + ".jpg";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));
        byte[] buffer = new byte[1024];
        int length;
        while ((length = dataInputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, length);
        }


        //转base64
        FileInputStream inputFile = new FileInputStream(new File(imageName));
        inputFile.read(buffer);
        inputFile.close();
        return new BASE64Encoder().encode(buffer);
    }

    /**
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地
     *
     * @param urlString
     * @param pictureFileName
     */
    public static void downloadPicture(String urlString, String pictureFileName) {
        URL url = null;
        try {
            url = new URL(urlString);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            String imageName = "D://poetPicture//" + pictureFileName + ".jpg";
            FileOutputStream fileOutputStream = new FileOutputStream(new File(imageName));

            byte[] buffer = new byte[1024];
            int length;

            while ((length = dataInputStream.read(buffer)) > 0) {
                fileOutputStream.write(buffer, 0, length);
            }

            dataInputStream.close();
            fileOutputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地
     *
     * @param urlList
     */
    public static void downloadPicture(ArrayList<String> urlList) {
        URL url = null;
        int imageNumber = 0;

        for (String urlString : urlList) {
            try {
                downloadPicture(urlString,urlString);
            }  catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
