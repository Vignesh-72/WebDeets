package WDCore;
/**
 *
 * @author Vicky
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JFileChooser;

import WDFrame.ResultFrame;
import org.apache.commons.net.whois.*;

public class Core 
{
    ResultFrame result_Screen = new ResultFrame();
    String cuurentCommand , result = " ", target , Path="null";
    Process process;
    BufferedReader buff;
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> Results = new ArrayList<>();    
   
    public void WD_ExcuteCommand()  //this fuction is used for excuting commands in terminal 
    {    
        try 
        {   
            //Excuting Command in termial 
            process = Runtime.getRuntime().exec(cuurentCommand);
        }
        catch (IOException e) 
        {
            e.printStackTrace();
            result_Screen.setResultFrame_Error();
        }

        buff = new BufferedReader(new InputStreamReader(process.getInputStream()));
        PrintResult();

    }
    
    public void FileChooser()
    {   
        //Creating FileChooser Frame And Saving The Path To Save Result
        JFileChooser jFileChooser1 = new JFileChooser();
        int response = jFileChooser1.showOpenDialog(null);
    
            if(response  == JFileChooser.APPROVE_OPTION){
                Path = jFileChooser1.getSelectedFile().getAbsolutePath();
            }
    }
   
    public void PrintResult()
    {
        String line;
        try 
        {
           while ((line = buff.readLine()) != null) 
           {
            //Printing the result in core and ResultFrame 
                this.Results.add(line);
                this.result += line + "\n";
                System.out.println(line);
            //Adding result to ResultSrceen
                result_Screen.jTextArea1.setText(this.result);
            }
            process.waitFor();
       } catch (IOException | InterruptedException e) 
       { 
            e.printStackTrace();
            result_Screen.setResultFrame_Error();
       }
         
    }
    
    public void WD_FileWriter()
    {  

    if(this.Path.equals("null"))
        return;
    
        //Writing The Result to File
        try (FileWriter writer = new FileWriter(this.Path)) 
        {
            for(String str: Results) 
            {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            result_Screen.setResultFrame_Error();
        }
        
    }

    public void WD_AdvanceFileWriter()  
    {

        if(this.Path.equals("null")) return;
    //Writing the Result to File  
        try (FileWriter writer = new FileWriter(this.Path)) 
        {
            writer.write(this.result + System.lineSeparator());
            writer.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            result_Screen.setResultFrame_Error();
        }

    }

    public void WD_setResultFrame()
    {   
        //Setting Result Frame Visible And Showing Result
        result_Screen.jTextArea1.setText(result);
    }

    public void WD_SimpleProc()
    {
        //this function is used by WD_Pingign and WD_Pcinfo  
        result_Screen.setResultFrame_Loading();

        WD_ExcuteCommand();
        WD_setResultFrame();

        result_Screen.setResultFrame_Blank();
    }

    public void WD_AdvanceProc()
    {
        //this function is used by All AdvanceToolsFrame Functions
        result_Screen.setResultFrame_Loading();
        WD_setResultFrame();
        result_Screen.setVisible(true);
        WD_AdvanceFileWriter();

        result_Screen.setResultFrame_Blank();
    }

    public void WD_Pinging(int index , String link)
    {   
        //ArrayList With Some Commands Used For Pinging
        result_Screen.jLabel3.setText("Loading..."); 
        ArrayList<String> pingingCommand = new ArrayList<>();
        pingingCommand.add("ping ");
        pingingCommand.add("ping -n 10 ");
        pingingCommand.add("ping -l 64 && ping -l 36 ");
        pingingCommand.add("ping -s 4 ");
        pingingCommand.add("ping -r 9 ");
        pingingCommand.add("ping ");
        pingingCommand.add("ping -i 15 ");
        cuurentCommand = pingingCommand.get(index) + link;
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        result_Screen.setVisible(true);
        WD_SimpleProc();
        WD_FileWriter();   
    }

    public void WD_PcInfo(int index)
    {   
        //ArrayList With Netstat Commands
        ArrayList<String> netStatCommand = new ArrayList<>();
        netStatCommand.add("netstat -a");
        netStatCommand.add("netstat -b");
        netStatCommand.add("netstat -e");
        netStatCommand.add("netstat -n");
        netStatCommand.add("netstat -o");
        netStatCommand.add("netstat -r");
        netStatCommand.add("netstat -v");
        
            cuurentCommand = netStatCommand.get(index);
            result_Screen.setVisible(true);
            WD_SimpleProc();
            WD_FileWriter();
    }

	public void WD_HostAll_IP(String target)//this function is used to grap all the ip of the target 
    {   
        try
        {
        result_Screen.setResultFrame_Loading();

        if(target.contains("https://"))
        {   
        //removing https:// form target 
            target = target.substring(8);
        }

            InetAddress[] myHost = InetAddress.getAllByName(target);
            //using for-each storing all ip one by one into inerAddress  
            for (InetAddress inetAddress : myHost) 
            {
                this.result += inetAddress.getHostAddress() + '\n';
                System.out.println(result);
                WD_setResultFrame();
            }

        }catch(Exception e)
        {
            e.fillInStackTrace();
            result_Screen.setResultFrame_Error();
            return ;
        }

        WD_AdvanceProc();
        return ;
    }

    public String WD_UrlCheck(String target) //check if it contains https or not. if not then concating(joining) https with the string and returning the String 
    {
        //target contins the url which is checked!
        if(!target.contains("https://"))
        {
            target = "https://" + target;
            return target;
        }
        return target;
    }

    /*WHOIS is a query and response protocol that is widely used for querying databases that store the registered users or assignees of an Internet resource, such as a domain name, an IP address block, or an autonomous system, but is also used for a wider range of other information.
    This is an example on how to use the Java “Apache Commons Net” library to get the WHOIS data of a domain.
    The Apache Commons Net API does not come bundled with the JDK. You can download it from here: http://commons.apache.org/proper/commons-net/download_net.cgi  */
    public void WD_Whois(String target){
        result_Screen.setResultFrame_Loading();
        StringBuilder sb = new StringBuilder("");
        WhoisClient wic = new WhoisClient();

        try 
        {
           wic.connect(WhoisClient.DEFAULT_HOST);
           String whoisData1 = wic.query("=" + target);
           sb.append(whoisData1);
           wic.disconnect();
        } catch (Exception e) 
        {
           e.printStackTrace();
           result_Screen.setResultFrame_Error();
        }
        System.out.println(sb.toString());
        result = sb.toString();
        result_Screen.jTextArea1.setText(result);
        result_Screen.setResultFrame_Blank();
    }

    public void WD_WebPageContent(String URL) // graps the html and css of the webpage 
    {   

        String line;
        URL url;
        InputStream urlStream;
        DataInputStream html;

        try
        {
        result_Screen.setResultFrame_Loading();

        URL = WD_UrlCheck(URL);

            url = new URL(URL);
            urlStream = url.openStream();
            html = new DataInputStream(urlStream);
            //html.readLine() convert byte to character and stores it in String line
            while ((line = html.readLine()) != null) 
            {
                this.result += line + "\n" ;
                result_Screen.jTextArea1.setText(result);
                System.out.println(line);
            }

        }catch(Exception e)
        {
            e.fillInStackTrace();
            result_Screen.setResultFrame_Error();
            return;
        }
        WD_AdvanceProc();
    }

    /*A port scanner is a software application designed to probe a server or host for open ports. This is often used by administrators to verify security policies of their networks and by attackers to identify running services on a host with the view to compromise it.  */
    public void WD_PortScan(String target) throws UnknownHostException//finds the ports which are open by the target 
    {
        result_Screen.setResultFrame_Loading();
        InetAddress[] addresses = InetAddress.getAllByName(target);
        for (InetAddress inetAddress : addresses) {
            target = inetAddress.toString();
        } 
    
    final String main_URL = target.substring(target.indexOf("/")+1);
    final String url_Head = target;

    ConcurrentLinkedQueue openPorts = new ConcurrentLinkedQueue<>();
    ExecutorService executorService = Executors.newFixedThreadPool(50);
    AtomicInteger port = new AtomicInteger(0);
    while (port.get() < 65000) {
        final int currentPort = port.getAndIncrement();
        executorService.submit(() -> {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(main_URL, currentPort), 200);
                socket.close();
                openPorts.add(currentPort);
                System.out.println(url_Head + " ,port open: " + currentPort);
                this.result += url_Head + " ,port open: " + currentPort + "\n";
                result_Screen.jTextArea1.setText(result);
            }
            catch (IOException e) {}
        });
    }
    executorService.shutdown();
    try {
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }

    List openPortList = new ArrayList<>();
    System.out.println("openPortsQueue: " + openPorts.size());
    while (!openPorts.isEmpty()) {
        openPortList.add(openPorts.poll());
    }
        result_Screen.setResultFrame_Blank();
        this.WD_AdvanceProc();
    }
    
    public void WD_HttpHeader(String target) 
    {
        result_Screen.setResultFrame_Loading();
        try
        {

        String urlString = WD_UrlCheck(target);
        String username = "myname";
        String password = "mypassword";

        target = WD_UrlCheck(target);

        Authenticator.setDefault(new MyAuthenticator(username, password));
     
        URL url = null;
        
            url = new URL(urlString);

        InputStream content = null;

            content = (InputStream) url.getContent();

        InputStreamReader isr = new InputStreamReader(content);
        BufferedReader in = new BufferedReader(isr);
     
        String line;
        
            while ((line = in.readLine()) != null) 
            {
              System.out.println(line);
              this.result += line.toString() + "\n";
              result_Screen.jTextArea1.setText(result);
            }
            
    }catch(Exception e)
    {
        e.fillInStackTrace();
        result_Screen.setResultFrame_Error();
    }

        WD_setResultFrame();
        WD_FileWriter();
        result_Screen.setResultFrame_Blank();
    }

    public void WD_ConvertHost(String urlString) {
        result_Screen.setResultFrame_Loading();
        InetAddress address = null;
        
        try {
            address = InetAddress.getByName(new URL(urlString).getHost());
        } catch (UnknownHostException | MalformedURLException e) {
           result_Screen.setResultFrame_Blank();
            e.printStackTrace();
        }

        String ip = address.getHostAddress();
        this.result = ip;

        result_Screen.jTextArea1.setText(result);
        result_Screen.setResultFrame_Blank();
        this.WD_AdvanceProc();
    }

    public void WD_ConvertIP(String urlString) {

        result_Screen.setResultFrame_Loading();
        InetAddress addr = null;
        try {
            addr = InetAddress.getByName(urlString);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            result_Screen.setResultFrame_Error();
        }
        this.result =  addr.getHostName();

        result_Screen.jTextArea1.setText(result);
        result_Screen.setResultFrame_Blank();
        this.WD_AdvanceProc();
    }

    
class MyAuthenticator extends Authenticator 
{
    private String username, password;
 
    public MyAuthenticator(String username, String password) 
    {
      this.username = username;
      this.password = password;
    }
 
    protected PasswordAuthentication getPasswordAuthentication() {
      System.out.println("Requesting Host  : " + getRequestingHost());
      System.out.println("Requesting Port  : " + getRequestingPort());
      System.out.println("Requesting Prompt : " + getRequestingPrompt());
      System.out.println("Requesting Protocol: " + getRequestingProtocol());
      System.out.println("Requesting Scheme : " + getRequestingScheme());
      System.out.println("Requesting Site  : " + getRequestingSite());
      return new PasswordAuthentication(username, password.toCharArray());

    }
}   
    /*Cookies are data, stored in small text files, on your computer. When a web server has sent a web page to a browser, the connection is shut down, and the server forgets everything about the user. 
    Cookies were invented to solve the problem "how to remember information about the user". For example, when a user visits a web page,
     his name can be stored in a cookie and the next time the user visits the page, the cookie "remembers" his name.  */

    public void WD_Cokkie (String target)     
    {   
        result_Screen.setResultFrame_Loading();
        try{
        if(!target.contains("http")){
            target = "https://" + target;
        }

        final String IP = target;
        CookieManager cm = new CookieManager();
        CookieHandler.setDefault(cm);
        System.out.println("IP O :"+IP);
        URL url = new URL(IP);

    
        java.net.URLConnection con = url.openConnection();
        con.getContent();
   
        CookieStore cs = cm.getCookieStore();
   
        List<URI> uriList = cs.getURIs();
        for (URI uri : uriList) 
        {
           this.result += uri.getHost() + "\n";
           result_Screen.jTextArea1.setText(result);
           WD_setResultFrame();
        }
        
        List<java.net.HttpCookie> hcList = cs.getCookies();
        for (java.net.HttpCookie hc : hcList) 
        {
           System.out.println("Cookie: " + hc.getName());
           System.out.println("Value: " + hc.getValue());
           this.result += "Cookie: " + hc.getName() + " \n"+ "Value: " + hc.getValue() + "\n";
           result_Screen.jTextArea1.setText(result);
           WD_setResultFrame();
        }
        
        result_Screen.jLabel3.setText("");
        System.out.println("Cookies Funtion Finished File Path : "+this.Path);
    }catch(Exception e)
    {
        e.fillInStackTrace();
        result_Screen.setResultFrame_Error();
    }
        WD_AdvanceFileWriter();
     
    }

     public void W_R_Socket(int Port) 
     {
        new Server(6666).start();
        new Client(6666).start();
        WD_FileWriter();
     }

    }
  
class Server extends Thread 
{
    Socket socket = null ;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    int Port;
    Core core = new Core();

    Server(int port)
    {
        this.Port  =  port;
    }
    public void run() 
    {
       try 
       {
          ServerSocket server = new ServerSocket(Port);
          while(true) 
          {
             socket = server.accept();
             ois = new ObjectInputStream(socket.getInputStream());
             String message = (String) ois.readObject();

             core.result = "Server Received: " + message + " \n";
             core.WD_setResultFrame();

             oos = new ObjectOutputStream(socket.getOutputStream());
             oos.writeObject("Server Reply");

             ois.close();
             oos.close();
             socket.close();
             server.close();
          }
       }catch (Exception e) {e.fillInStackTrace();}
    }
 }
  
 class Client extends Thread  
 {
    InetAddress host = null;
    Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    Core core = new Core();
    int Port;

    Client(int port)
    {
        this.Port = port;
    }
    public void run() 
    {
       try 
       {
          for(int x=0; x<5; x++)
           {
             host = InetAddress.getLocalHost();
             socket = new Socket(host.getHostName(), Port);

             oos = new ObjectOutputStream(socket.getOutputStream());
             oos.writeObject("Client Message " + x);
             ois = new ObjectInputStream(socket.getInputStream());

             String message = (String) ois.readObject();
             core.result = "Client Message " + message + " \n";
             core.WD_setResultFrame();

             ois.close();
             oos.close();
             socket.close();
          }
       } catch (Exception e) {e.fillInStackTrace();}
    }
 }