import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

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
    

}
