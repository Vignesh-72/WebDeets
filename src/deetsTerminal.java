import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
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
        System.out.println("Path");
        System.out.println("Creating");
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
                this.outText += inetAddress.getHostAddress() + '\n';
                System.out.println(outText);
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
            while ((line = html.readLine()) != null) {
                this.outText += line + "\n" ;
                System.out.println(line);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        try {
            createFile();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        outputScreen();
        try {
            System.out.println("Createing file start" + this.Path);
            createFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void port_scan(String URL)   {
        this.outputScreen();
       
        getport_scan(URL);
    }

    public void getport_scan(String ip) {

        ConcurrentLinkedQueue openPorts = new ConcurrentLinkedQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        AtomicInteger port = new AtomicInteger(0);
        while (port.get() < 1000) {
            final int currentPort = port.getAndIncrement();
            executorService.submit(() -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(ip, currentPort), 200);
                    socket.close();
                    openPorts.add(currentPort);
                    System.out.println(ip + " ,port open: " + currentPort);
                    this.outText += ip + " ,port open: " + currentPort + "\n";     
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
        try {
            createFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(this.outText);
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
  
    


