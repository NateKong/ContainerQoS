package base;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A Container for Cloud computing that handles requests
 * This container is assumed to have one cloudlet
 * that will handle a single request
 * 
 * Created by Nathan Kong 11/2016
 * This is a variant of ContainerHost from CloudSim
 */
public class Container {

    /**
     * The id.
     */
    private int id;

    /**
     * The VM.
     */
    private VM vm;
    
    //request
    private Request request;
    
    //priority
    private static int priority;

    /**
     * Creates a new Container object.
     * @param id
     * @param vm
     * @param request
     */
    public Container(int id, int priority) {
    	this.id = id;
    	this.priority = priority;
    }

	public VM getVm() {
		return vm;
	}

	public void setVm(VM vm) {
		this.vm = vm;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public static int getPriority() {
		return priority;
	}

	public static void setPriority(int priority) {
		Container.priority = priority;
	}

	public int getId() {
		return id;
	}

  

}
