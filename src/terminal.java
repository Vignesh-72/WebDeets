import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class terminal{
    
    public

    String Command;
    Process process;
    BufferedReader buff;
    String Path = "Results.txt", target;
    Scanner scanner = new Scanner(System.in);
    
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
    public void ping_Section(int Option , String Link , String nameOfTheFile) throws IOException{
        
        ArrayList<String> subCommand = new ArrayList<>();
        subCommand.add(" 4");
        subCommand.add(" 4");
        subCommand.add(" 32");
        subCommand.add(" 4");
        subCommand.add(" 4");
        subCommand.add(" 10");
        subCommand.add(" ");
         
        ArrayList<String> deetsCommand = new ArrayList<>();
        this.target = Link;
        deetsCommand.add("ping ");
        deetsCommand.add("ping -n ");
        deetsCommand.add("ping -l ");
        deetsCommand.add("ping -s ");
        deetsCommand.add("ping -r ");
        deetsCommand.add("ping -i ");
        deetsCommand.add("ping -p ");

    
        /*if(Option == 8){
            String temp;

            System.out.println("$ Enter Times To Ping Limit [1] To [4294967295]");
            temp = scanner.next();
            subCommand.set(1, temp);
        
            System.out.print("$ Enter In Bytes, Of The Echo-Request Packet : \n");
            temp = scanner.next();
            subCommand.set(2, temp);
        
            System.out.println("$ The maximum count value is 4, i.e. only the first four hops can be time stamped");
            System.out.print("$ ");
            temp = scanner.next();
            subCommand.set(3, temp);
      
            System.out.print("$ Numbers Of Hops Max Value [9] : ");
            temp = scanner.next();
            subCommand.set(4, temp);

            System.out.print("$ Set Time To Live [TTL] Max Value [255] : ");
            temp = scanner.next();
            subCommand.set(5, temp);
            for (String string : deetsCommand) {
                for (String string2 : subCommand) {
                    System.out.println(string + " " + string2);
                }
            }
        }else{
            this.Command = deetsCommand.get(Option) + subCommand.get(Option) + " " + target;
            process();
        }
       
       */
        System.out.println(Option);
        System.out.println(deetsCommand.get(Option));
        System.out.println(deetsCommand.get(Option) + subCommand.get(Option) + " " + target);
        if(nameOfTheFile != "null"){
            this.Path = nameOfTheFile;
            createFile();}
    }
    
    public void pc_Info_Section(int option , String nameOfTheFile) throws IOException{
        ArrayList<String> netStatCommand = new ArrayList<>();
        netStatCommand.add("netstat -a");
        netStatCommand.add("netstat -b");
        netStatCommand.add("netstat -e");
        netStatCommand.add("netstat -n");
        netStatCommand.add("netstat -o");
        netStatCommand.add("netstat -r");
        netStatCommand.add("netstat -v");

        if(option == 8){
            for (String string : netStatCommand) {
                this.Command = string;
                process();
            }
        }
        else{
            this.Command = netStatCommand.get(option);
            process();
        }
        if(nameOfTheFile != "null"){
            this.Path = nameOfTheFile;
            createFile();
        }
    }
        
    }


