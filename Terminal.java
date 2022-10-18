import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileWriter;

public class Terminal{
    
    public
    String Command;
    Process process;
    BufferedReader buff;
    String Path = "Results.txt";

    ArrayList<String> Results = new ArrayList<>();
        Terminal(){};
        Terminal(String Command) throws IOException, InterruptedException{
            this.Command = Command;
            process();
            createFile();
        }
        Terminal(String Command , String FileToSave) throws IOException, InterruptedException{
            this.Command = Command;
            this.Path = FileToSave;
            process();
            createFile();
        }
    
    public void process() throws IOException, InterruptedException {
         process = Runtime.getRuntime().exec(Command);
         buff = new BufferedReader(new InputStreamReader(process.getInputStream()));
         
         String line;
         while ((line = buff.readLine()) != null) {
             Results.add(line);
             System.out.println(line);
         }
         process.waitFor(); 
    }
    public void createFile() throws IOException{
        FileWriter writer = new FileWriter(Path);
        for(String str: Results) {
          writer.write(str + System.lineSeparator());
        }
        writer.close();
    }
    public void CommandSet(String command){
        
        if(command.contains("scan")){
            String SubCommmand = command.substring(5);
            String MainCommand = "ping "+SubCommmand+" & "+"help";
            this.Command = MainCommand;
            System.out.println(Command);
            return;
        }
            this.Command = command;
            return;       
    }

    
}
