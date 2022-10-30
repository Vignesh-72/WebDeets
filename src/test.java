import java.io.DataInputStream;
import java.io.InputStream;
import java.net.URL;

public class test {
    public static void main(String[] args) {
        String line;
        URL url;
        InputStream urlStream;
        DataInputStream html;
        try {
            url = new URL("http://www.google.com");
            urlStream = url.openStream();
            html = new DataInputStream(urlStream);
            while ((line = html.readLine()) != null) {
                System.out.println(line);
                
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}
