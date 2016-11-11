package base;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Vm represents a Virtual Machine: it runs inside a Host, sharing hostList with other VMs. It processes
 * containers. 
 *
 * Created by Nathan Kong 11/2016
 * A variant of ContainerVM.java in CloudSim
 * 
 */
public class VM {

    /**The vm id. */
    private int id;

    /**The List of Containers on this VM. */
    private static ArrayList<Container> containers;
    
    /**The host. */
    private Host host;

    private int bw;
    
    private static int resetBW;
    
    /**
     * Creates a new VM
     * @param id
     */

    public VM( int id, int bw ) {
    	this.id = id;
    	this.bw = bw;
    	this.resetBW = bw;
    	this.containers = new ArrayList<Container>();
    }

    /**
     * gets the id of the VM
     */
    public int getId(){
    	return id;
    }
    
    public void subBW(int band){
    	bw -= band;
    }
    
    public void addBW(int band){
    	bw += band;
    }
    
    public int getBW(){
    	return bw;
    }
    
    public void setHost(Host host){
    	this.host = host;
    }
    
    public void addContainer(Container con){
    	System.out.println("Adding container " + con.getId() + " to VM "+ id );
    	containers.add(con);
    }
    
    public void resetBW(){
    	bw = resetBW;
    }
    
}



