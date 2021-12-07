package model;

public class Server {
    //atributos 
    private String cantCacheMemory;
    private String numberProcessors;
    private String processorBrand;
    private String ram;
    private String cantDiscos;
    private String capacityDiscos;
    
    public Server(String cantCacheMemory, String numberProcessors, String processorBrand, String ram, String cantDiscos, String capacityDiscos) {
        this.cantCacheMemory = cantCacheMemory;
        this.numberProcessors = numberProcessors;
        this.processorBrand = processorBrand;
        this.ram = ram;
        this.cantDiscos = cantDiscos;
        this.capacityDiscos = capacityDiscos;
    }
    public String getCantCacheMemory() {
        return cantCacheMemory;
    }
    public void setCantCacheMemory(String cantCacheMemory) {
        this.cantCacheMemory = cantCacheMemory;
    }

    public String getNumberProcessors() {
        return numberProcessors;
    }
    public void setNumberProcessors(String numberProcessors) {
        this.numberProcessors = numberProcessors;
    }

    public String getProcessorBrand() {
        return processorBrand;
    }
    public void setProcessorBrand(String processorBrand) {
        this.processorBrand = processorBrand;
    }
    
    public String getRam() {
        return ram;
    }
    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getCantDiscos() {
        return cantDiscos;
    }
    public void setCantDiscos(String cantDiscos) {
        this.cantDiscos = cantDiscos;
    }

    public String getCapacityDiscos() {
        return capacityDiscos;
    }
    public void setCapacityDiscos(String capacityDiscos) {
        this.capacityDiscos = capacityDiscos;
    }


    /** 
     * Show the general information of a server
     * @return message information (String)
     */
    public String toString()
    {
        String message = "";

        message += "Disk capacity: " + capacityDiscos + "(" + cantDiscos + " units) || ";
        message += "RAM: " + ram;

        return message;
    }
}
