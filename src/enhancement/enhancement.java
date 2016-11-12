package enhancement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import base.*;

/**************************************
 * Simulation of a containers running cloudlets
 * in a Cloud environment
 * 
 * ENHANCEMENT
 * 
 * @author Nathan Kong
 *
 **************************************/

public class enhancement {
	private static int time;
	private static Host host;
	private static ArrayList<VM> VMs;
	private static ArrayList<Container> containers;
	private static ArrayList<Request> completedRequests;

	public static void main(String[] args) {
		time = 0;
		int numOfHosts = 1;
		int hostBW = 1000000; //Rounded number from CloudSim
		int numOfVMs = 1;
		int vmBW = 10000; //Rounded number from CloudSim
		int numOfContainers = 10;
		int numOfRequests = 100;
		
		/**initialize architecture*/
		//create host
		host = new Host(numOfHosts, hostBW);
		System.out.println("Host " + Host.getId() + " created");
		//create VMs
		createVMs(numOfVMs, vmBW);
		//create Containers
		createContainers(numOfContainers);
		//Setup - Connection of Objects
		setup();
		
		/**Simulate project*/
		//create requests and adds them to containers
		schedule(numOfRequests);
		
		//run simulation
		run(numOfRequests);
		
		//print
		printOutput();
		System.out.println("\n***** Enhancement Simulation Complete  *****");
	}

	private static void createVMs(int num, int bw){
		VMs = new ArrayList<VM>();
		printBreak();
		for (int i = 1; i <= num; i++){
			VMs.add(new VM(i, bw));
		}
		
		for (VM m: VMs){
			System.out.println("VM " + m.getId() + " created ");
		}
	}

	private static void createContainers(int num) {
		containers = new ArrayList<Container>();
		printBreak();
		
		//random generated data
		int[] priority = {0, 2, 3, 1, 2, 3, 3, 2, 3, 1, 3};
		
		for (int i = 1; i <= num; i++){
			containers.add( new Container(i, priority[i]) );
		}
		
		for (Container c: containers){
			System.out.println("Container " + c.getId() + " has a priority of " + c.getPriority() );
		}
	}

	private static void setup() {
		//VMs look to attach to hosts
		printBreak();
		for (VM vm: VMs){
			vm.setHost(host);
			host.addVm(vm);
		}
		
		//containers look to attach to VMs
		printBreak();
		int v = 0;
		int numOfVms = VMs.size();
		for (Container con: containers){
			if (v == numOfVms){ v = 0;}
			VM vm = VMs.get(v++);
			//System.out.println(vm.getId());
			vm.addContainer(con);
			con.setVm(vm);
		}
		
	}
	
	public static void printBreak(){
		System.out.println("\n********************");
	}
	
	private static void schedule(int numOfRequests){
		//create request
		ArrayList<Request>requests = createRequests(numOfRequests);

		for (Request r: requests){
			containers.get( r.getContainerId()-1 ).addRequest(r);
		}	
	}
	
	private static ArrayList<Request> createRequests(int num) {
		ArrayList<Request> requests = new ArrayList<Request>();
		printBreak();
		
		//random generated data
		int[] bw = {1343, 2426, 2207, 947, 2201, 1222, 1085, 1547, 1098,
				1112, 2187, 1391, 1260, 1687, 1233, 560, 1334, 1329, 2007,
				1947, 1394, 1737, 1761, 762, 655, 1509, 1380, 1432, 1035,
				921, 1595, 1456, 1265, 1174, 1922, 1470, 1418, 1957, 1409,
				2067, 1116, 2275, 727, 1511, 2120, 1266, 832, 1860, 1175,
				1635, 1139, 1507, 1191, 1408, 835, 1782, 741, 2083, 1943, 
				2378, 1836, 1798, 2262, 2418, 908, 770, 2192, 1384, 1297,
				1020, 2108, 1524, 2229, 1462, 2301, 2240, 2206, 1098, 1470,
				1837, 930, 2031, 2210, 1738, 1096, 920, 1666, 2194, 1208, 869,
				1138, 1024, 2401, 1052, 700, 2405, 1864, 501, 1906, 686, 1513};
		int[] requestTime = {29, 10, 57, 34, 30, 23, 12, 33, 56, 60, 43, 32,
				27, 30, 11, 57, 20, 16, 30, 47, 49, 9, 48, 54, 38, 23, 56,
				52, 4, 23, 37, 35, 32, 39, 20, 2, 46, 19, 31, 32, 39, 57, 51,
				12, 11, 23, 30, 8, 3, 37, 49, 17, 32, 24, 54, 46, 7, 9, 23,
				28, 20, 29, 59, 19, 7, 50, 2, 22, 52, 42, 2, 53, 34, 38, 25,
				10, 54, 42, 40, 45, 27, 56, 1, 51, 40, 22, 41, 50, 37, 48, 59,
				11, 36, 11, 19, 42, 49, 53, 51, 50, 39};
		int[] containerID = {2, 9, 10, 6, 8, 3, 5, 1, 10, 4, 8, 10, 8, 8,
				10, 5, 8, 4, 9, 8, 5, 5, 10, 3, 3, 1, 3, 6, 7, 3, 4, 2,
				2, 9, 3, 7, 2, 3, 3, 5, 2, 10, 4, 7, 8, 5, 10, 7, 6, 5,
				1, 7, 4, 2, 9, 5, 3, 2, 2, 8, 3, 3, 5, 7, 5, 6, 4, 1, 8,
				6, 2, 9, 4, 2, 8, 6, 10, 3, 7, 2, 3, 1, 8, 7, 4, 7, 10,
				2, 3, 10, 9, 6, 10, 4, 7, 2, 6, 5, 10, 4, 6};

		//create container
		for (int i = 1; i <= num; i++){
			requests.add( new Request(i, bw[i], requestTime[i], containerID[i]) );
		}
		for (Request r: requests){
			System.out.println("Requests: " + r.getId() + " requires " + r.getBw() + " bw and will take " + r.getTime() + " seconds on container " + r.getContainerId() );
		}
		return requests;
	}
	
	private static void run(int numOfRequests){
		printBreak();
		completedRequests = new ArrayList<Request>();
		ArrayList<Container> p1 = new ArrayList<Container>();
		ArrayList<Container> p2 = new ArrayList<Container>();
		ArrayList<Container> p3 = new ArrayList<Container>();
		
		for (Container c: containers){
			int i = c.getPriority();
			if (i == 1){
				p1.add(c);
			}else if (i == 2){
				p2.add(c);
			}else {
				p3.add(c);
			}
		}
		
		boolean run = true;
		int cnt = 0;
		do {
			switch (cnt++ % 6) {
			case 0:	rr(p1,p2,p3);
			break;
			case 1:	rr(p1,p2,p3);
			break;
			case 2:	rr(p2,p1,p3);
			break;
			case 3:	rr(p1,p2,p3);
			break;
			case 4:	rr(p2,p1,p3);
			break;
			default:rr(p3,p1,p2);
			}

			run = checkRequests(numOfRequests);
			
			increment();
			
		}while(run);
	}
	
	private static void rr(ArrayList<Container> a, ArrayList<Container> b, ArrayList<Container> c){
		runContainers(a);
		runContainers(b);
		runContainers(c);
	}
	
	private static void runContainers(ArrayList<Container> con){
		for (Container c: con){
			if (c.getRequestSize() > 0){
				VM vm = c.getVm();
				Request r = c.getRequest(0);
				
				if (vm.getBW() >= r.getBw()){
					//subtract time from VM bandwidth
					vm.subBW(r.getBw());
					//if the request has not been started
					if(r.getStatus() == Status.waiting){
						r.setStartTime(time);
						r.setStatus(Status.running);
					}
					
					if(r.getTime() == 0){
						r.setFinishTime(time);
						completedRequests.add( c.RemoveFirstRequest() );
					}else{
						r.subTime(1);	
					}
				}			
			}
		}
	}
	
	
	//checks to make sure all 100 requests have been completed
	private static boolean checkRequests(int numOfRequests){
		int size = completedRequests.size();
		if(size == numOfRequests){
			return false;
		}
		/*else{
			System.out.println(size);
		}
		*/
		return true;
	}
		
	private static void increment(){
		//increment global time
		time++;
		//reset the bandwidth for each VM
		for (VM vm: VMs){
			vm.resetBW();
		}
	}
	
	private static void printOutput(){
		System.out.println("Request ID\tContainer ID\tStart Time\tFinish Time\tBandwidth");
		for (Request r: completedRequests){
			r.printResults();
		}
	}
	
	
}
