package com.ivy.tool;

import com.ivy.crawler.PoemTypeCrawler;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

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
        try {
            // byte[]
//            byte[] bytes = downloadByte(url);
//            String result = new String(bytes);
//            System.out.println(result);


            //保存到本地
            downloadPicture(url,"yuanzheng.jpg");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static byte[] downloadByte(String imgUrl) throws IOException {
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(imgUrl);

        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity entity = closeableHttpResponse.getEntity();
        if(entity != null){
            InputStream in= entity.getContent();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int len = -1;
            while ((len = in.read(buff, 0, 1024)) != -1) {
                byteArrayOutputStream.write(buff, 0, len);
            }
            byte[] bytes = byteArrayOutputStream.toByteArray();
            return bytes;
        }
        return null;
    }

    /**
     * 传入要下载的图片的url列表，将url所对应的图片下载到本地
     *
     * @param urlString
     * @param pictureFileName
     */
    public static String downloadPicture(String urlString, String pictureFileName) {
        URL url = null;
        String imageName = null;
        try {
            url = new URL(urlString);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            imageName = PoemTypeCrawler.poetPic + pictureFileName + ".jpg";
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
        return imageName;
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
