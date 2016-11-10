package base;

import java.util.ArrayList;
import java.util.List;

/**
 * A Host for Cloud computing that contains VMs
 * 
 * Created by Nathan Kong 11/2016
 * This is a variant of ContainerHost from CloudSim
 */
public class Host {


    /** The id.*/
    private static int id;

    /**The total bandwidth for a host.*/
    private static long bw;
    
    /**A list of the VMs on this host */
    private static ArrayList<VM> VMs;
  
    /**
     * Instantiates a new host.
     */
    public Host(int id, long bw){
    	this.id = id;
    	this.bw = bw;//1000000 -> this number was taken from CloudSim "ConstantsExamples.java"
    	this.VMs = new ArrayList<VM>();

    }
    
    public static int getId(){
    	return id;
    }

    /**
     * Requests updating of processing of cloudlets in the VMs running in this host.
     *
     * @param currentTime the current time
     * @return expected time of completion of the next cloudlet in all VMs in this host.
     * Double.MAX_VALUE if there is no future events expected in this host
     * @pre currentTime >= 0.0
     * @post $none
     */
    //public double updateContainerVmsProcessing(double currentTime) {    }

    /**
     * Adds the migrating in vm.
     *
     * @param containerVm the vm
     */
    public void addVm(VM containerVm) {
        System.out.println("Adding VM " + containerVm.getId() + " to Host "+ id );
        VMs.add(containerVm);
    }

    /**
     * Destroys all VMs running in the host.
     *
     * @pre $none
     * @post $none
     */
    public void removeAllVms() {
    	System.out.println("Removing all VMs on host: " + id);
    	VMs.clear();
    }

    /**
     * Returns a VM object.
     *
     * @param vmId   the vm id
     * @param userId ID of VM's owner
     * @return the virtual machine object, $null if not found
     * @pre $none
     * @post $none
     */
    public VM getVM(int vmId) {
        for (VM vm : VMs) {
            if (vm.getId() == vmId) {
                return vm;
            }
        }
        return null;
    }
}


