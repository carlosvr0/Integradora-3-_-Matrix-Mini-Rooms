package model;

public class Company {
    //atributos
    private String name;
    private String nit;
    private String projectRegistrationNumber;
    
    public Company(String name, String nit) {
        this.name = name;
        this.nit = nit;
    }

    public Company(String name, String nit, String projectRegistrationNumber) {
        this.name = "ICESI University";
        this.nit = "890.316.745-5";
        this.projectRegistrationNumber = projectRegistrationNumber;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNit() {
        return nit;
    }
    public void setNit(String nit) {
        this.nit = nit;
    }
    
    public String getProjectRegistrationNumber() {
        return projectRegistrationNumber;
    }
    public void setProjectRegistrationNumber(String projectRegistrationNumber) {
        this.projectRegistrationNumber = projectRegistrationNumber;
    }
}
