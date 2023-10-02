package p02_10_2023.zadatak3;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class Filedownloader {

    public static void downloadImg(String imgUrl, String imgLocation) throws IOException {
        URLConnection openConnection = new URL(imgUrl).openConnection();
        openConnection.addRequestProperty("User-Agent", "YOUR USER AGENT");
        InputStream is = openConnection.getInputStream();
        BufferedImage saveImage = ImageIO.read(is);
        ImageIO.write(saveImage, "jpg", new File(imgLocation));
    }


    /*
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

    public static void downloadUsingNIO(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
     */
}
