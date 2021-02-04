package com.leo.upload;


import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
public class Main {

    public static void main(String[] args) {
        String uploadFilePath = "D:\\lxnfile\\milk_2021_01_13_20_29_02.wav";
        uploadFile(uploadFilePath);
    }
    private static void uploadFile(String uploadFilePath) {
        String end = "/r/n";
        String Hyphens = "--";
        String boundary = "*****";
        String fileName = uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1);
//        String actionUrl = "http://localhost:8080/file";
        String actionUrl = "http://101.200.61.68:10086/file";
        StringBuilder sb = new StringBuilder(actionUrl);
        sb.append("?filename=" + fileName);
        String newURL = sb.toString();
        try {
            URL url = new URL(newURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            /* 允许Input、Output，不使用Cache */
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);

            /* 设定传送的method=POST */
            con.setRequestMethod("POST");

            /* setRequestProperty */
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            con.setRequestProperty("Content-Type",
                    "multipart/form-data;boundary=" + boundary);

            /* 设定DataOutputStream */
            DataOutputStream ds = new DataOutputStream(con.getOutputStream());
            ds.writeBytes(Hyphens + boundary + end);
            ds.writeBytes("Content-Disposition: form-data; name=\"uploadfile\"; filename=\""
                    + uploadFilePath.substring(uploadFilePath.lastIndexOf(File.separator) + 1) + "\"" + end);
            ds.writeBytes(end);

            /* 取得文件的FileInputStream */
            FileInputStream fStream = new FileInputStream(uploadFilePath);

            /* 设定每次写入1024bytes */
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length = -1;

            /* 从文件读取数据到缓冲区 */
            while ((length = fStream.read(buffer)) != -1) {
                /* 将数据写入DataOutputStream中 */
                ds.write(buffer, 0, length);
            }

            ds.writeBytes(end);
            ds.writeBytes(Hyphens + boundary + Hyphens + end);
            fStream.close();
            ds.flush();
            ds.close();
            /* 取得Response内容 */
            InputStream is = con.getInputStream();
            int ch;
            StringBuffer b = new StringBuffer();

            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }
            System.out.println(b.toString());

        } catch (Exception e) {
            System.out.println("上传失败" + e.getMessage());
        }
    }
}
