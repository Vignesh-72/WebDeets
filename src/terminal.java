import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.FileWriter;

public class terminal{
    
    public
    String Command;
    Process process;
    BufferedReader buff;
    String Path = "Results.txt", target;
    
    ArrayList<String> Results = new ArrayList<>();
        terminal(){};

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
    public void ScanDeets(String Link , String nameOfTheFile){
        String[] dopeScanCommnads = new String[5];
        dopeScanCommnads[0] = "ping -a -n 4 ";
        dopeScanCommnads[1] = "ping -l 4";
        dopeScanCommnads[2] = "ping -n ";
        dopeScanCommnads[3] = "ping -i 10 ";
        dopeScanCommnads[4] = "tracert -d "; 

        if(Path == "null"){
            this.target = Link;
        for (int i = 0; i < dopeScanCommnads.length; i++) {
            this.Command = dopeScanCommnads[i] + Link;
            System.out.println(Command);
            process();
            }
        }
        else{
            Path = nameOfTheFile;
            for (int i = 0; i < dopeScanCommnads.length; i++) {
                this.Command = dopeScanCommnads[i] + Link;
                System.out.println(Command);
                process();
                try {
                    createFile();
                } catch (IOException e) {
                    e.printStackTrace();
            }
        }
        
    }
        
    }
     
}
