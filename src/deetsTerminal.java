import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class deetsTerminal {
    private
    String Command;
    Process process;
    BufferedReader buff;
    String Path = "Results.txt", target;
    Scanner scanner = new Scanner(System.in);
    ArrayList<String> Results = new ArrayList<>();

    ArrayList<String> deetsCommand = new ArrayList<>();
    this.target = Link;
    deetsCommand.add("ping ");
    deetsCommand.add("ping -n ");
    deetsCommand.add("ping -l ");
    deetsCommand.add("ping -s ");
    deetsCommand.add("ping -r ");
    deetsCommand.add("ping -i ");
    deetsCommand.add("ping -p ");

    ArrayList<String> netStatCommand = new ArrayList<>();
    netStatCommand.add("netstat -a");
    netStatCommand.add("netstat -b");
    netStatCommand.add("netstat -e");
    netStatCommand.add("netstat -n");
    netStatCommand.add("netstat -o");
    netStatCommand.add("netstat -r");
    netStatCommand.add("netstat -v");


    deetsTerminal(){
        System.out.println("Process Started ");
    }
    public void set_pro(String link , String path , String command){
        this.target = link;
        this.Path = path;
        this.Command = command;
    }

    public void process()  {
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

}
