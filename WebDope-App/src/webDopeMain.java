import java.util.Scanner;

public class webDopeMain{

    public static void main(String[] args) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            terminal terminal = new terminal();

            System.out.print("WebDope [Version 1.0] \nCreator : Vicky \n");
            while(true){

                System.out.print("$ Enter The Link : ");
                String Link = scanner.nextLine();
                System.out.print("Save In A File  Y/N : ");
                String temp = scanner.nextLine();

                if(temp.contains("y") || temp.contains("Y") ){
                    System.out.print("File Name : ");
                    String Path = scanner.nextLine();
                    terminal.ScanDeets(Link, Path);
                }else{
                    terminal.ScanDeets(Link , "null");
                }
            }
        }

    }
}
