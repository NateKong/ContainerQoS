/*
 * Title:        CloudSim Toolkit
 * Description:  CloudSim (Cloud Simulation) Toolkit for Modeling and Simulation of Clouds
 * Licence:      GPL - http://www.gnu.org/copyleft/gpl.html
 *
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */
package base;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Request made by some user to access a application
 * 
 * Created By Nathan Kong 11/2016
 */
public class Request {

    /**
     * The request ID.
     */
    private final int id;

    /**
     * The execution status of this Cloudlet.
     * 0 = not started
     * 1 = running
     * 2 = completed
     */
    private int status;

    /**
     * The time when the application for this request starts.
     */
    private long startTime;

    /**
     * The time when the application for this request completes.
     */
    private long finishTime;
    
    //The bandwidth required
    private int bw;
    
    //The container that will run this request
    private Container container;
    
    //the id of the container
    private int containerId;
    
    //The time it will take to run this request
    private static int time;
    
     /**
     * Allocates a Request being made to a container
     */
    public Request( int id, int bw, int time, int containerId){
    	this.id = id;
    	this.bw = bw;
    	this.time = time;
    	this.containerId = containerId;
    	this.status = 0;
    	this.startTime = 0;
    	this.finishTime = 0;
    }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public int getBw() {
		return bw;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setFinishTime(long finishTime) {
		this.finishTime = finishTime;
	}
    
    public void printResults(){
    	System.out.println(id + "\t" + startTime + "\t" + finishTime);
    }
    
    public static int getTime(){
    	return time;
    }

}