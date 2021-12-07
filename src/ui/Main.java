package ui;
import java.util.Scanner;

import model.DataCenter;

public class Main {

    public final static Scanner LECTOR = new Scanner(System.in);
    private static DataCenter objPrincipal;

    /**
     * Main menu
     * @param args
     */
    public static void main(String[] args) {

        objPrincipal = new DataCenter();

        System.out.println("********************************");
        System.out.println("*         Integradora 3        *");
        System.out.println("*                              *");
        System.out.println("*          MINICUARTOS         *");
        System.out.println("********************************");
        System.out.println("");

        int chosenOptionMenu = 0;
        do {
            System.out.println("------------------------------");
            System.out.println("-  Chose one of the options  -");
            System.out.println("------------------------------");
            System.out.println("1) Show available mini rooms"); //list
            System.out.println("2) Rent a mini room"); 
            System.out.println("3) Cancel the rental");
            System.out.println("4) Show map with available mini rooms");
            System.out.println("5) Simulate turning on all mini rooms");
            System.out.println("6) Simulate turning off some mini rooms"); //según la letra 
            System.out.println("7) EXIT");
            chosenOptionMenu = LECTOR.nextInt();
            LECTOR.nextLine();

            switch (chosenOptionMenu) {
                case 1:
                    showListAvailableRooms();
                break;
                case 2:
                    rentRoom();
                break;
                case 3:
                    cancelRental();
                break;
                case 4:
                    showMap();
                break;
                case 5:
                    turnOnRoom();
                break;
                case 6:
                    turnOffRoom();
                break;
            }
        } while(chosenOptionMenu != 7);
    }


    /**
     * Call the method showInfoAvailableRoom
     * @return void
     */
    public static void showListAvailableRooms() {
        System.out.print(objPrincipal.printMatrixRoom()); //mapa
        
        if(objPrincipal.contadorAvailable() != 0){
            System.out.println(objPrincipal.showInfoAvailableRoom()); //lista 
        }else{
            System.out.println("***All the rooms are rented***");
        }
        
        
    }

     /**
     * Call the method printMatrixRoom in the controller
     * Call the method calculateMonthlyRentValue in the controller
     * Call the method isAvailable in the controller
     * @return void
     */
    public static void rentRoom() {
         
        //SE MUESTRA EL MAPA    
        System.out.println(objPrincipal.printMatrixRoom());


        System.out.println("Choose the number of the mini room");
        int numberRoom = LECTOR.nextInt();
        LECTOR.nextLine();
        
        if(objPrincipal.isAvailable(numberRoom))
        {
            double precio = objPrincipal.calculateMonthlyRentValue(numberRoom, 5);
            System.out.println("Monthly payment: " + precio);

            System.out.println("Write the date: ");
            String rentalDate = LECTOR.nextLine();

            System.out.println("Choose the tenant");
            System.out.println("    1) Is an investigation project");
            System.out.println("    2) An external company");
            String inquilino = LECTOR.nextLine();

            String infoAboutServers = "";
            int numServers = 0;

            switch (inquilino) {
                case "1":
                    System.out.println("Write the project registration number");
                    String projectNumber = LECTOR.nextLine();

                    objPrincipal.alquilar(numberRoom, precio, projectNumber, rentalDate);

                    System.out.println("Write the number of servers to be used");
                    numServers = LECTOR.nextInt();
                    LECTOR.nextLine();          
                    
                    for(int m = 0; m < numServers; m++){
                        System.out.println("");
                        System.out.println("SERVER #" + (m+1));
            
                        System.out.println("Cache memory amount (in GB)");
                        infoAboutServers =  LECTOR.nextLine() + "-";
            
                        System.out.println("Number of processors");
                        infoAboutServers += LECTOR.nextLine() + "-";
            
                        System.out.println("Processor brand");
                        System.out.println("    1) INTEL");
                        System.out.println("    2) AMD");
                        infoAboutServers += LECTOR.nextLine()+ "-";
            
                        System.out.println("RAM memory amount (in GB)");
                        infoAboutServers += LECTOR.nextLine()+ "-";
            
                        System.out.println("Number of disks");
                        infoAboutServers += LECTOR.nextLine()+ "-";
            
                        System.out.println("Disk capacity (in tasks)");
                        infoAboutServers += LECTOR.nextLine();

                        infoAboutServers += ",";
                    }

                    //objPrincipal.splitInfoServer(numberRoom, numServers, infoAboutServers);
                break;
                case "2":
                    System.out.println("Write the company name");
                    String companyName = LECTOR.nextLine();

                    System.out.println("Wrtite the NIT of the company");
                    String nitCompany = LECTOR.nextLine();
                    objPrincipal.alquilar(numberRoom, precio, companyName, nitCompany, rentalDate);

                    System.out.println("Write the number of servers to be used");
                    numServers = LECTOR.nextInt();
                    LECTOR.nextLine();                           
                    
                    for(int m = 0; m < numServers; m++){
                        System.out.println("");
                        System.err.println("SERVER #" + (m+1));
            
                        System.out.println("Cache memory amount (in GB)");
                        infoAboutServers =  LECTOR.nextLine() + "-";
            
                        System.out.println("Number of processors");
                        infoAboutServers += LECTOR.nextLine() + "-";
            
                        System.out.println("Processor brand");
                        System.out.println("    1) INTEL");
                        System.out.println("    2) AMD");
                        infoAboutServers += LECTOR.nextLine()+ "-";
            
                        System.out.println("RAM memory amount (in GB)");
                        infoAboutServers += LECTOR.nextLine()+ "-";
            
                        System.out.println("Number of disks");
                        infoAboutServers += LECTOR.nextLine()+ "-";
            
                        System.out.println("Disk capacity (in tasks)");
                        infoAboutServers += LECTOR.nextLine();
                    }

                    //objPrincipal.splitInfoServer(numberRoom, numServers, infoAboutServers);
                break;
            }
        }else{
            System.out.println("**Mini room not available for rent**");
        }
    }

    /**
     * Call the method cancelRent in the controller
     * Call the method cancelRentForACompany in the controller
     * @return void
     */
    public static void cancelRental() {
        System.out.println("Cancel the rental for:");
        System.out.println("    1) A mini room");
        System.out.println("    2) All the mini rooms of a company");
        String cancelRentOption = LECTOR.nextLine();

        switch (cancelRentOption) {
            case "1":
                System.out.println("Write the mini room number");
                int idRoom = LECTOR.nextInt();
                LECTOR.nextLine();
        
                if(objPrincipal.cancelRent(idRoom))
                {
                    System.out.println(objPrincipal.showServerInfo(idRoom));
                    System.out.println("**Successful cancellation**");
                }else{
                    System.out.println("**There has been a problem with the cancellation*");
                }
            break;
            case "2":
                System.out.println("Write the company name");
                String companyName = LECTOR.nextLine();

                // si es ICESI, se debe escribir ICESI University 

                if(objPrincipal.cancelRentForACompany(companyName))
                    System.out.println("**Successful cancellation**");
                else
                    System.out.println("**There has been a problem with the cancellation*");
            break;
        }
    }

    /**
     * Call the method printOnOff in the controller
     * Call the method contadorAvailable in the controller
     * @return void
     */
    public static void showMap() {
        String matrixRoom = objPrincipal.printOnOff();
        System.out.println(matrixRoom);
        System.out.println("AVAILABLE: " + objPrincipal.contadorAvailable());
    }

    /**
     * Call the method simulateTurnON in the controller
     * @return void
     */
    public static void turnOnRoom() {
        String pisoRoom = objPrincipal.simulateTurnON();
        System.out.println(pisoRoom);
    }

    /**
     * Call the method simulateTurnOff in the controller
     * @return void
     */
    public static void turnOffRoom() {
        System.out.println("**Choose a letter**");
        System.out.println("    L) turns off the first mini room of all corridors, along with the mini rooms of the first corridor");
        System.out.println("    Z) turns off the mini-rooms in the first and last corridor, along with the mini-rooms on the reverse diagonal");
        System.out.println("    H) turns off mini-rooms located in odd-numbered corridors");
        System.out.println("    O) turns off the mini-rooms located in the windows");
        System.out.println("    M) turns off the rooms of a certain column");
        System.out.println("    P) turns off the rooms of a certain corridor");
        String turnOffLetterChosen = LECTOR.nextLine();
        turnOffLetterChosen = turnOffLetterChosen.toUpperCase();

        String map = "";

        if(turnOffLetterChosen.equals("M") || turnOffLetterChosen.equals("P")){
            if(turnOffLetterChosen.equals("M"))
            {
                System.out.println("Which column do you want to turn off?");
                int columnToTurnOff = LECTOR.nextInt();
                LECTOR.nextLine();
                map = objPrincipal.simulateTurnOff(turnOffLetterChosen, columnToTurnOff);
            }else{
                System.out.println("Which corridor do you want to turn off?");
                int filaToTurnOff = LECTOR.nextInt();
                LECTOR.nextLine();
                map = objPrincipal.simulateTurnOff(turnOffLetterChosen, filaToTurnOff);
            }
        }else{
            map = objPrincipal.simulateTurnOff(turnOffLetterChosen, 0);
        }
        
        System.out.println(map);
    }
}  


