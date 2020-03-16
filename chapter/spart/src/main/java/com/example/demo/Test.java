package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by JF on 2019/11/28.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        post(null,null,3);
    }
    public static Map<String, Object> post(String urlStr, String xmlInfo, int roomNumber) throws IOException {

        Map<String, Object> map = new HashMap<String, Object>();
//        try {
            long startTime=System.currentTimeMillis();  //获取开始时间
//
//            URL url= new URL(null, urlStr, new sun.net.www.protocol.https.Handler());
//            HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setDoInput(true);
//            connection.setDoOutput(true);
//            connection.setUseCaches(false);
//            connection.setRequestProperty("Content-Type", "UTF-8");
//            connection.setRequestProperty("Content-Type", "application/soap+xml;");
//            connection.setRequestProperty("Accept-Charset", "UTF-8");
//
//            if (xmlInfo!=null && !"".equals(xmlInfo)) {
//                OutputStream outwritestream = connection.getOutputStream();
//                outwritestream.write(xmlInfo.getBytes("UTF-8"));
//                outwritestream.flush();
//                outwritestream.close();
//            }

//            if (connection.getResponseCode() == 200) {

                long endTime=System.currentTimeMillis(); //获取结束时间
                System.out.println("发送响应时间： "+(endTime-startTime)+"ms");

//                InputStream in = connection.getInputStream();
                InputStream in = new FileInputStream(new File("D:\\chapter\\spart\\src\\main\\java\\test.txt"));
                    int count = 0;
                    while (count == 0) {
                        count = in.available();
                    }
                    byte[] data1 = new byte[count];
                    int c =  in.read(data1);
                    while (c!=-1) {
                        in.read(data1);
                    }

//                }
//            }
//        }
return null;
    }
}
