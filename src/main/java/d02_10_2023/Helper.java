package d02_10_2023;

import org.openqa.selenium.WebElement;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Helper {

    //3. zadatak
    public static int getHTTPResponseStatusCode(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        return http.getResponseCode();
    }

    public static void printStatusMessage(int status){
            if(status>=200 && status<400)
                System.out.println("Status je "+status+" veci/jednak od 200 i manji od 400");
            else if (status<200)
                System.out.println("Status je "+status+" manji od 200");
            else System.out.println("Status je "+status+" veci/jednak od 400");
    }

    //4.zadatak
    public static void downloadUsingStream(String urlStr, String file) throws IOException, IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }


}
