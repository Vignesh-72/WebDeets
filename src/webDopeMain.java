import java.util.ArrayList;
import java.util.Scanner;

public class webDopeMain{

    public static void main(String[] args) throws Exception {

            String Path = "null";
            Scanner scanner = new Scanner(System.in);

            System.out.print("Save In A File  Y/N : ");
                String fileOp = scanner.nextLine();
                    if(fileOp.contains("y") || fileOp.contains("Y") ){
                        System.out.print("File Name : ");
                        Path = scanner.nextLine();

                    }
            terminal terminal = new terminal();

            System.out.println("\n\n\n\n\n\n\n\n");
            System.out.print("\t\t\tWebDeets [Version 1.0]  Creator : Vicky \n\n");

                while(true){
                System.out.println("\n$ [1 - PcInfo | 2 - WebDeets | 3 - AdvanceTools]\n");
                System.out.print("$ ");
                int option = scanner.nextInt();

                
                    switch (option) {
                        case 1:
                            System.out.println("\n[1 - Display All Connection And Ports]");
                            System.out.println("[2 - The Executable Involved In Each Connection Or Hearing Port]");
                            System.out.println("[3 - Display The Address And The Port Number In The Form Of Numerical]");
                            System.out.println("[4 - Display The ID Of Each Connection For The Ownership Process]");
                            System.out.println("[5 - Display The Routing Table]");
                            System.out.println("[6 - Ink Or Hearing Port Sequence For Every Executable is Shown]");
                            System.out.println("[7 - Hearing Port Sequence For Every Executable]");
                            System.out.println("[8 - Do All And Display]");
                            System.out.print("\n$ ");
                            option = scanner.nextInt();
                            terminal.pc_Info_Section(option,Path);


                            break;
                        case 2:
        
                            System.out.print("\n$ Enter The Link : ");
                            String link = scanner.next(); 

                            System.out.println("\n1 - Normal-Pinging | 2 - Full-Pinging");
                            System.out.print("\n$ ");
                            int pinging_Option = scanner.nextInt();

                            if(pinging_Option == 1){
                                System.out.println("\n[1 - Ping The IP Address]");
                                System.out.println("[2 - Send ICMP Echo Requests]");
                                System.out.println("[3 - Set The Size, In Bytes, Of The Echo-Request Packet And Ping]");
                                System.out.println("[4 - Time In The Internet Timestamp Format Of Echo Request And Echo reply]");
                                System.out.println("[5 - Set Time To Live TTL And Ping]");
                                System.out.println("[6 - Ping a Hyper-V Network Virtualization Provider Address]");
                                System.out.println("[7 - Full-Pinging , Do All And Display]");
                                System.out.print("\n$ ");
                                pinging_Option = scanner.nextInt();
                                terminal.ping_Section(pinging_Option,link, Path);
                            
                            }
                            if(pinging_Option == 2){
                                System.out.println("\n$ This Option Will Provide Full Details About The Target");
                            
                            }    

                            break;
                        default:
                            break;
                    }

                
                }
            
            
        }
    }


