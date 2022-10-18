import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal();


        while(true){
            System.out.print("$ ");
            String command = scanner.nextLine();
            
            terminal.CommandSet(command);
            terminal.process();
            
        }

    }
}
