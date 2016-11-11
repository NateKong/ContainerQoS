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
    
    //priority
    private int priority;
    
    //List of requests
    private ArrayList<Request> requests;

    /**
     * Creates a new Container object.
     * @param id
     * @param vm
     * @param request
     */
    public Container(int id, int priority) {
    	this.id = id;
    	this.priority = priority;
    	this.requests = new ArrayList<Request>();
    }

	public VM getVm() {
		return vm;
	}

	public void setVm(VM vm) {
		this.vm = vm;
	}

	public Request getRequest(int i) {
		return requests.get(i);
	}
	
	public Request getFirstRequest(){
		return requests.remove(0);
	}

	public void addRequest(Request request) {
		requests.add(request);
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getId() {
		return id;
	}
}
