package model;
import java.util.ArrayList;

public class MiniRoom {
    //atributos
    private int id;
    private boolean available;
    private double rentalValue;
    private int row; //corredor
    private int column; // columna 
    private boolean withWindow;
    private String rentedDate;

    //relacion
    private Company company;
    private ArrayList<Server> servidores;

    //metodos
    public MiniRoom(int id, boolean available, double rentalValue, int row, int column, boolean withWindow, String rentedDate){
        this.id = id;
        this.available = available;
        this.rentalValue = rentalValue;
        this.row = row;
        this.column = column;
        this.withWindow = withWindow;
        this.rentedDate = rentedDate;

        company = new Company("a", "c");
        company = new Company("Icesi", "aaaa", "A001");     
        servidores =  new ArrayList<Server>();
    }
    

    
    //ArrayList<Server> servers)
     //this.servers = servers;
     //metodos

    public ArrayList<Server> getServidores() {
        return servidores;
    }


    public void setServidores(ArrayList<Server> servidores) {
        this.servidores = servidores;
    }


    public String getRentedDate() {
        return rentedDate;
    }
    public void setRentedDate(String rentedDate) {
        this.rentedDate = rentedDate;
    }

    public boolean isWithWindow() {
        return withWindow;
    }
    public void setWithWindow(boolean withWindow) {
        this.withWindow = withWindow;
    }

    public int getColumn() {
        return column;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean getAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    public double getRentalValue() {
        return rentalValue;
    }
    public void setRentalValue(double rentalValue) {
        this.rentalValue = rentalValue;
    }
    
    /** 
     * Show the general information of a mini room
     * @return message information (String)
     */
    public String toString()
    {
        String message = "";

        message += "Row: " + row + " || ";
        message += "Column: " + column + " || ";
        
        if(withWindow)
            message += "Window: TRUE || ";
        else
            message += "Window: FALSE || ";
    
        message += "Rental value: " + rentalValue + "\n";
        
        return message;
    }

}
