package model;

import java.util.ArrayList;

public class DataCenter {
    //atributos
    public final static int NUM_FILAS = 8;
    public final static int NUM_COLUMNAS = 50;
    public final static double RENTAL_VALUE = 100000;

    //relaciones
    private MiniRoom[][] room;

    //metodos
    public DataCenter() {
        this.room = new MiniRoom[NUM_FILAS][NUM_COLUMNAS];
        createMatrixRoom(); // load the mini rooms 
    }

    
    /** 
     * Load and create the matrix (the floor) of the mini rooms
     * @return message matrix (String)
     */
    public String createMatrixRoom(){
        String message = ""; //matrix of mini rooms 
        int count = 1; // will be the mini room id 
        double price = RENTAL_VALUE;

        for (int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                
                if(m == 0 || m == NUM_FILAS-1 || n == 0 || n == NUM_COLUMNAS-1)//for the window
                    room[m][n] = new MiniRoom(count, true, RENTAL_VALUE,m+1,n+1,true,"06-12");
                else
                    room[m][n] = new MiniRoom(count, true, RENTAL_VALUE,m+1,n+1,false,"06-12"); //create mini room 
                
                count++;
            }
        }

        for (int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                price = calculateMonthlyRentValue(room[m][n].getId(), 5); //price to pay monthly
                room[m][n].setRentalValue(price);
            }
        }

        message = printMatrixRoom();
        return message;
    }

    
    /** 
     * Print the matrix of the mini rooms, with the ids, to confirm the actions that are done
     * if it shows *** its because its rented
     * @return pisoRoom matrix (String)
     */
    public String printMatrixRoom() {
        String pisoRoom = ""; // matrix       

        for (int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9 && room[m][n].getAvailable())//to add ceros before the number id, so is a perect rectangle
                {
                    pisoRoom += "[00" + room[m][n].getId() + "]";
                }else if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9 && !(room[m][n].getAvailable())){
                    pisoRoom += "[***]";
                }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99 && room[m][n].getAvailable()){
                    pisoRoom += "[0" + room[m][n].getId() + "]";
                }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99 && !(room[m][n].getAvailable())){
                    pisoRoom += "[***]";
                }else if(room[m][n].getId() >= 100 && room[m][n].getAvailable()){
                    pisoRoom += "[" + room[m][n].getId() + "]";
                }else if(room[m][n].getId() >= 100 && !(room[m][n].getAvailable())){
                    pisoRoom += "[***]";
                } 
            }
            pisoRoom += "\n";
        }

        return pisoRoom;
    }

    /** 
     * Shows the general information of each available mini room
     * <br>pre: room[m][n] != null</br>
	 * <br>post: </br> 
     * @return ArrayList<String>
     */
    public ArrayList<String> showInfoAvailableRoom()
    {
        ArrayList<String> infoArray = new ArrayList<String>();

        for (int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                if(room[m][n].getAvailable())
                {
                    infoArray.add(room[m][n].toString());
                }
            }
        }

        return infoArray;
    }

    /**
     * Find the row and column position of a mini room
     * @param idNumberRoom id of the mini room (int)
     * @return position (int[])
     */
    public int[] findPosition(int idNumberRoom){
        int[] position = new int[2];

        for (int m = 0; m < room.length; m++){
            for (int n = 0; n < room[0].length; n++) {
                if(room[m][n].getId() == idNumberRoom)
                {
                    position[0] = m;
                    position[1] = n;
                }
            }
        }

        return position;
    }


    /** 
     * Divide the string that contains information about each server to assign to the corresponding attribute
     * <br>pre: room[m][n] != null</br>
	 * <br>post: </br>      
     * * @param numServers number of servers that the mini room will have (int)
     * @param information the information of each server separated by "-"
     */
    public void splitInfoServer(int idNumber,int numServers, String information){
        
        String[] arrayInfo =  information.split(",");//divide por servidores
        
        int[] place = findPosition(idNumber); // posicion de la fila y columna del room 
    
        for (int m = 0; m < arrayInfo.length; m++) { //divide la info de cada servidor 
            String[] parts = arrayInfo[m].split("-");
            for(int i = 0; i < numServers; i++){ //
                room[place[0]][place[1]].getServidores().get(i).setCantCacheMemory(parts[0]);
                room[place[0]][place[1]].getServidores().get(i).setNumberProcessors(parts[1]);
                room[place[0]][place[1]].getServidores().get(i).setProcessorBrand(parts[2]);
                room[place[0]][place[1]].getServidores().get(i).setRam(parts[3]);
                room[place[0]][place[1]].getServidores().get(i).setCantDiscos(parts[4]);
                room[place[0]][place[1]].getServidores().get(i).setCapacityDiscos(parts[5]);
            }
        }
        
    }

    /** 
     * Shows the information of the servers of the mini room
     * <br>pre: </br>
	 * <br>post: </br>
     * @param fila row number in which the mini room is located (int)
     * @param column column number in which the mini room is located (int)
     * @return info information of the servers (String)
     */
    public String showServerInfo(int id){
        String info = "";
        int[] place = findPosition(id);

        info = room[place[0]][place[1]].getServidores().toString();

        return info;
    }
    
    /** 
     * Rent to a research project
     * <br>pre: company name: ICESI University
     * @param id mini room number
     * @param rentalValue price to pay monthly 
     * @param numProject project registration number
     * @param date date it is being rented
     * @return void
     */
    public void alquilar(int id, double rentalValue, String numProject, String date)//for a ICESI project
    {
        int[] place = findPosition(id);
        //rewrite the data
        room[place[0]][place[1]].setAvailable(false);
        room[place[0]][place[1]].setRentalValue(rentalValue);
        room[place[0]][place[1]].getCompany().setProjectRegistrationNumber(numProject);
        room[place[0]][place[1]].setRow(place[0]);
        room[place[0]][place[1]].setColumn(place[1]);
        room[place[0]][place[1]].setRentedDate(date);

        if(place[0] == 0 || place[0] == NUM_FILAS-1 || place[1] == 0 || place[1] == NUM_COLUMNAS-1){//for the window
            room[place[0]][place[1]].setWithWindow(true);
        }

    }
    
    /** 
     * Rent to a company (different to ICESI)
     * <br>pre: </br>
	 * <br>post: </br>   
     * @param id mini room number
     * @param rentalValue price to pay monthly 
     * @param nameComapy the name of the company it will be rented
     * @param nit the NIT of the company it will be rented
     * @param date date it is being rented
     * @return void
     */
    public void alquilar(int id, double rentalValue, String nameComapy, String nit, String date)//for a company 
    {
        int[] place = findPosition(id);

        room[place[0]][place[1]].setAvailable(false);
        room[place[0]][place[1]].setRentalValue(rentalValue);
        room[place[0]][place[1]].getCompany().setName(nameComapy);
        room[place[0]][place[1]].getCompany().setNit(nit);
        room[place[0]][place[1]].setRow(place[0]);
        room[place[0]][place[1]].setColumn(place[1]);
        room[place[0]][place[1]].setRentedDate(date);

        if(place[0] == 0 || place[0] == NUM_FILAS-1 || place[1] == 0 || place[1] == NUM_COLUMNAS-1){//for the window
            room[place[0]][place[1]].setWithWindow(true);
        }
    }

    
    /** 
     * Tells if the mini room is available or not
     * <br>pre: numRoom != -1 </br>
	 * <br>post: </br>   
     * @return boolean
     */
    public boolean isAvailable(int numRoom){//to know if is not rented 
        int[] place = findPosition(numRoom);

        if(room[place[0]][place[1]].getAvailable())
        {
            return true;
        }

        return false;
    }
    
    /** 
     * Cancel the rental of a mini room
     * <br>pre: numberRoom != -1 </br>
	 * <br>post: </br>  
     * @param numberRoom mini room number(int)
     * @return boolean
     */
    public boolean cancelRent(int numberRoom){ // For just one room
        int[] place = findPosition(numberRoom);

        if(room[place[0]][place[1]].getAvailable() == false){
            room[place[0]][place[1]].setAvailable(true);
            return true;
        }

        return false;
    }

    
    /** 
     * Cancel all rooms that are rented to a company
     * <br>pre: companyName != "" </br>
	 * <br>post: find all the rooms that are rented with that company</br>  
     * @param companyName mini room number(String)
     * @return boolean
     */
    public boolean cancelRentForACompany(String companyName) //for many rooms 
    {
        boolean changed = false;

        for(int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                if(room[m][n].getCompany().getName().equalsIgnoreCase(companyName))
                {
                   if(room[m][n].getAvailable() == false){
                        room[m][n].setAvailable(true);
                        changed = true;
                   }
                }
            }
        }

        return changed;
    }


    
    /** 
     * cCalculates the amount to be paid monthly
     * <br>pre: </br>
	 * <br>post: </br>
     * @param numberRoom mini room number (int)
     * @param numServers number of servers (int)
     * @return toPay price (double)
     */
    public double calculateMonthlyRentValue(int numberRoom, int numServers){
        double toPay = RENTAL_VALUE;
        double descuento = 0;
        double aumento = 0;

        for(int m = 0; m < room.length; m++)
        {
            if(m == 0 || m == NUM_FILAS-1) //if its located in a window
            {
                for(int n = 0; n < room[0].length; n++)
                {
                    if(n == 0 || n == NUM_COLUMNAS-1)
                    {
                        if(room[m][n].getId() == numberRoom)
                        {
                            descuento = toPay * 0.1;
                            toPay -= descuento;
                        }
                    }                   
                }
            }
        }

        for(int m = 0; m < room.length; m++)
        {
            if(m == 7) //if its located in the seventh row
            {
                for(int n = 0; n < room[0].length; n++)
                {
                    if(room[m][n].getId() == numberRoom)
                    {
                        descuento = toPay * 0.15;
                        toPay -= descuento;
                    }
                }
            }
        }

        for(int m = 0; m < room.length; m++)
        {
            if(!(m == 1) && !(m == 8))//if its located between the second and sixth corridor
            {
                for(int n = 0; n < room[0].length; n++)
                {
                    if(room[m][n].getId() == numberRoom)
                    {
                        descuento = toPay * 0.25;
                        toPay -= descuento;
                    }
                }
            }
        }

        if(numServers < 4) //if it has less than servers 
        {
            aumento = RENTAL_VALUE * 0.15;
            toPay += aumento;
        }

        return toPay;
    }

    
    /** 
     * count how many mini rooms are available
     * <br>pre: </br>
	 * <br>post: </br>
     * @return numAvailableRoom number (int)
     */
    public int contadorAvailable(){//to know how many rooms are not rented 
        int numAvailableRoom = 0;

        for (int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                if(room[m][n].getAvailable())
                {
                    numAvailableRoom++;
                }
            }
        }

        return numAvailableRoom;
    }

    
    /** 
     * Show matrix with the mini rooms that are rented and not
     * <br>pre: </br>
	 * <br>post: </br>
     * @return pisoRoom matrix (String)
     */
    public String printOnOff(){ //show those that are rented and not
        String pisoRoom = "";

        for (int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                if(room[m][n].getAvailable()) //If its available, then its not rented, therefore, must be OFF
                {
                    pisoRoom += "[OFF]";
                }else{
                    pisoRoom += "[O N]";//If it isnt available, then its rented, therefore, must be ON
                }
            }
            pisoRoom += "\n";
        }

        return pisoRoom;
    }

    
    /** 
     * Simulate turning on of all mini-rooms (regardless of status) to test power-on protocols
     * <br>pre: </br>
	 * <br>post: </br>     
     * @return piso matrix (String)
     */
    public String simulateTurnON(){ //it doesnt matter if its available or not
        String piso = "";
        
        for (int m = 0; m < room.length; m++) {
            for (int n = 0; n < room[0].length; n++) {
                piso += "[***]";

            }
            piso += "\n"; 
        }

        return piso;
    }
  

    
    /** 
     * Simulate turning off the mini rooms according to the letter entered
     * @param letter letter entered (String)
     * @param secondLetter position of a row or column (int) for the case of the letter M and P
     * @return piso matrx (String)
     */
    public String simulateTurnOff(String letter, int secondLetter){//all the mini roms will be rented, and depending on the letter the number is shown, that is, they become available
        String piso = "";

        switch (letter) {
            case "L":
                for (int m = 0; m < room.length; m++) {
                    for (int n = 0; n < room[0].length; n++) {
                        if(m == 0){
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 100 ){
                                piso += "[" + room[m][n].getId() + "]";
                                
                            }
                        }else if(n == 0){
                    
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                               
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 100 ){
                                piso += "[" + room[m][n].getId() + "]";
                                
                            }                
                        }else{
                            piso += "[***]";
                        }
                    }
                    piso += "\n";
                }
            break;
            case "Z":
                
                //int diagonal = (NUM_COLUMNAS * 2) - 1;  // 99
                //int bloque = (NUM_COLUMNAS - 2) / (NUM_FILAS - 2); // 8
                
                for (int m = 0; m < room.length; m++) {
                    for (int n = 0; n < room[0].length; n++) {
                        if(m == 0 || m == NUM_FILAS-1){ // first and last row
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 100){
                                piso += "[" + room[m][n].getId() + "]";
                               
                            }
                        }else if(room[m][n].getId() >= 92 && room[m][n].getId() <= 99){  //diagonal
                            if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                               
                            }else if(room[m][n].getId() >= 100){
                                piso += "[" + room[m][n].getId() + "]";
                                
                            }
                            //diagonal = (diagonal - bloque) + NUM_COLUMNAS; intente  hacerlo con algunos calculos, pero solo me imprimia un número                   
                        }else if(room[m][n].getId() >= 134 && room[m][n].getId() <= 141){ //diagonal
                            piso += "[" + room[m][n].getId() + "]";

                        }else if(room[m][n].getId() >= 176 && room[m][n].getId() <= 183){ //diagonal
                            piso += "[" + room[m][n].getId() + "]";
                            
                        }else if(room[m][n].getId() >= 218 && room[m][n].getId() <= 225){ //diagonal
                            piso += "[" + room[m][n].getId() + "]";
                            
                        }else if(room[m][n].getId() >= 260 && room[m][n].getId() <= 267){ //diagonal
                            piso += "[" + room[m][n].getId() + "]";
                            
                        }else if(room[m][n].getId() >= 302 && room[m][n].getId() <= 309){ //diagonal
                            piso += "[" + room[m][n].getId() + "]";
                            
                        }else{
                            piso += "[***]";
                        }
                    }
                    piso += "\n";
                }
            break;
            case "H":
                for (int m = 0; m < room.length; m++) {
                    for (int n = 0; n < room[0].length; n++) {
                        if((m+1) % 2 != 0){
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 100){
                                piso += "[" + room[m][n].getId() + "]";
                                
                            }
                        }else{
                            piso += "[***]";
                        }                        
                    }
                    piso += "\n";
                }
            break;
            case "O":
                for (int m = 0; m < room.length; m++) {
                    for (int n = 0; n < room[0].length; n++) {
                        if(m == 0 || m == NUM_FILAS-1){
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                               
                            }else if(room[m][n].getId() >= 100){
                                piso += "[" + room[m][n].getId() + "]";
                                
                            }   
                        }else if(n == 0 || n == NUM_COLUMNAS-1){
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                             
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 100){
                                piso += "[" + room[m][n].getId() + "]";
                               
                            }
                            
                        }else{
                            piso += "[***]";
                        }                        
                    }
                    piso += "\n";
                }
            break;
            case "M":
                for (int m = 0; m < room.length; m++) {
                    for (int n = 0; n < room[0].length; n++) {
                        if((n+1) == secondLetter){
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 100){
                                piso += "[" + room[m][n].getId() + "]";
                                
                            }
                        }else{  
                            piso += "[***]";
                        }                        
                    }
                    piso += "\n";
                }   
            break;
            case "P":
                for (int m = 0; m < room.length; m++) {
                    for (int n = 0; n < room[0].length; n++) {
                        if((m+1) == secondLetter){
                            if(room[m][n].getId() >= 1 && room[m][n].getId() <= 9)
                            {
                                piso += "[00" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 10 && room[m][n].getId() <= 99){
                                piso += "[0" + room[m][n].getId() + "]";
                                
                            }else if(room[m][n].getId() >= 100){
                                piso += "[" + room[m][n].getId() + "]";
                               
                            }
                        }else{
                            piso += "[***]";
                        }                        
                    }
                    piso += "\n";
                }
            break;
        }
        return piso;
    }

    
    /** 
     * @return MiniRoom[][]
     */
    public MiniRoom[][] getRoom() {
        return room;
    }
    
    /** 
     * @param room
     */
    public void setRoom(MiniRoom[][] room) {
        this.room = room;
    }

}
