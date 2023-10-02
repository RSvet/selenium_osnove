package p02_10_2023;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

public class Helper {

     // za Zadatak4 i 6
    public static void takeAScreenshot(WebDriver driver, String path) throws IOException {
        File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Files.copy(f,new File(path));

        //2.nacin
        //FileUtils.copyFile(f, new File(path).getAbsoluteFile());
    }

    // za Zadatak5
    public static int getHTTPResponseStatusCode(String u) throws IOException {
        URL url = new URL(u);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        return http.getResponseCode();
        }
}
