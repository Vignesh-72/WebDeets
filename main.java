import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        Terminal terminal = new Terminal();

        System.out.print("WebDope [Version 1.0] ");
        System.out.println("Creator : Vicky");
        while(true){

            System.out.print("$ Enter The Link : ");
            String Link = scanner.nextLine();
            System.out.print("Save In A File  Y/N : ");
            String temp = scanner.nextLine();

            if(temp.contains("y")){
                System.out.print("File Name : ");
                String Path = scanner.nextLine();
                terminal.ScanDeets(Link, Path);
            }else{
                terminal.ScanDeets(Link , "null");
            }
        }

    }
}
