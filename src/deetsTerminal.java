import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import java.net.*;
import java.util.List;

public class deetsTerminal {

    public

    String Command;
    String outText = "";
    Process process;
    BufferedReader buff;
    String Path = "Results.txt", target;
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> Results = new ArrayList<>();
    int temp = 0;
    ouputScreen frame1 = new ouputScreen();

    public void process()  {
        System.out.println("Processing ! :" + Command);
         try {
            process = Runtime.getRuntime().exec(Command);
        } catch (IOException e) {
            e.printStackTrace();
        }
         buff = new BufferedReader(new InputStreamReader(process.getInputStream()));
         print();
    }

    public void print(){
        String line;
         try {
            while ((line = buff.readLine()) != null) {
                 Results.add(line);
                 outText += line + "\n";
                 System.out.println(line);
             }
        } catch (IOException e) {
            e.printStackTrace();
        }
         try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
       
    }
    
    public void createFile() throws IOException {
        FileWriter writer = new FileWriter(Path);
        for(String str: Results) {
          writer.write(str + System.lineSeparator());
        }
        writer.close();
        
    }
    public void outputScreen(){
        frame1.setVisible(true);
        frame1.jTextArea1.setText(outText);
    }
    public void deets_scan(int index){

        System.out.println("deets_scan");
        ArrayList<String> netStatCommand = new ArrayList<>();
        netStatCommand.add("netstat -a");
        netStatCommand.add("netstat -b");
        netStatCommand.add("netstat -e");
        netStatCommand.add("netstat -n");
        netStatCommand.add("netstat -o");
        netStatCommand.add("netstat -r");
        netStatCommand.add("netstat -v");
        
            Command = netStatCommand.get(index);
            System.out.println("inside if | "+ Command);
            process();
    }
    public void all_ipOf_Host(String URL){
        try {
            InetAddress[] myHost = InetAddress.getAllByName(URL);
            for (InetAddress inetAddress : myHost) {
                this.outText = inetAddress.getHostAddress();
                outputScreen();
            } 
        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }
    public void content_Of_WebPage(String URL) {
        String line;
        URL url;
        InputStream urlStream;
        DataInputStream html;
        try {
            url = new URL(URL);
            urlStream = url.openStream();
            html = new DataInputStream(urlStream);
            int i = 0;
            while ((line = html.readLine()) != null) {
                i++;
                if(i == 10){
                    this.outText = "\n";
                }                
                this.outText = line ;
                System.out.println(line);
                outputScreen();
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
    public void port_scan(String URL) {
        System.out.println("Entered port_scan");
        for(int port = 1 ; port <= 65535 ; port++){
            System.out.println("Inside For ");
            Socket socket = new Socket();
            try {
                System.out.println("Socket Connecting");
                socket.connect(new InetSocketAddress(URL, port),1000);
                socket.close();
                this.outText = "Port " + port + " Is Open \n";
                outputScreen();
                System.out.println("Port " + port + " Is Open \n");
                System.out.println("Done \n ------------ \n");
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void Cookie (String str) throws IOException   {
        CookieManager cm = new CookieManager();
        CookieHandler.setDefault(cm);
   
        URL url = new URL(str);
        URLConnection con = url.openConnection();
        con.getContent();
   
        CookieStore cs = cm.getCookieStore();
   
        List<URI> uriList = cs.getURIs();
        for (URI uri : uriList) {
           this.outText = uri.getHost() + "\n";
           this.outputScreen();
        }
   
        List<HttpCookie> hcList = cs.getCookies();
        for (HttpCookie hc : hcList) {
           System.out.println("Cookie: " + hc.getName());
           System.out.println("Value: " + hc.getValue());
           this.outText = "Cookie: " + hc.getName() + " \n"+ "Value: " + hc.getValue() + "\n";
           outputScreen();
        }
     } 
     public void W_R_Socket(int Port) {
        new Server(6666).start();
        new Client(6666).start();
     }
    }
    
 
class Server extends Thread {
    Socket socket = null ;
    ObjectInputStream ois = null;
    ObjectOutputStream oos = null;
    int Port;
    deetsTerminal terminal = new deetsTerminal();
    Server(int port){
        this.Port  =  port;
    }
    public void run() {
       try {
          ServerSocket server = new ServerSocket(Port);
          while(true) {
             socket = server.accept();
             ois = new ObjectInputStream(socket.getInputStream());
             String message = (String) ois.readObject();
             terminal.outText = "Server Received: " + message + " \n";
             terminal.outputScreen();
             oos = new ObjectOutputStream(socket.getOutputStream());
             oos.writeObject("Server Reply");
             ois.close();
             oos.close();
             socket.close();
             server.close();
          }
       } catch (Exception e) {
       }
    }
 }
  
 class Client extends Thread  {
    InetAddress host = null;
    Socket socket = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    deetsTerminal terminal = new deetsTerminal();
    int Port;
    Client(int port){
        this.Port = port;
    }
    public void run() {
       try {
          for(int x=0; x<5; x++) {
             host = InetAddress.getLocalHost();
             socket = new Socket(host.getHostName(), Port);
             oos = new ObjectOutputStream(socket.getOutputStream());
             oos.writeObject("Client Message " + x);
             ois = new ObjectInputStream(socket.getInputStream());
             String message = (String) ois.readObject();
             terminal.outText = "Client Message " + message + " \n";
             terminal.outputScreen();
             ois.close();
             oos.close();
             socket.close();
          }
       } catch (Exception e) {
       }
    }
 }
  
    


