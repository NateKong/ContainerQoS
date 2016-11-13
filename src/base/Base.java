package base;

import java.util.ArrayList;

/**************************************
 * Simulation of a containers running cloudlets
 * in a Cloud environment
 * 
 * BASELINE
 * 
 * @author Nathan Kong
 *
 **************************************/
public class Base {
	private static int time;
	private static Host host;
	private static ArrayList<VM> VMs;
	private static ArrayList<Container> containers;
	private static ArrayList<Request> completedRequests;

	public static void main(String[] args) {
		time = 1;
		int numOfHosts = 1;
		int hostBW = 1000000; //Rounded number from CloudSim
		int numOfVMs = 1;
		int vmBW = 3000; //Rounded number from CloudSim
		int numOfContainers = 3;
		int numOfRequests = 75;
		
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
		System.out.println("\n*****  Simulation Complete  *****");
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
		
		//the first one won't count
		int[] priority = {0, 1, 2, 3, 3 };
		
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
		/*int[] requestTime = {29, 10, 57, 34, 30, 23, 12, 33, 56, 60, 43, 32,
				27, 30, 11, 57, 20, 16, 30, 47, 49, 9, 48, 54, 38, 23, 56,
				52, 4, 23, 37, 35, 32, 39, 20, 2, 46, 19, 31, 32, 39, 57, 51,
				12, 11, 23, 30, 8, 3, 37, 49, 17, 32, 24, 54, 46, 7, 9, 23,
				28, 20, 29, 59, 19, 7, 50, 2, 22, 52, 42, 2, 53, 34, 38, 25,
				10, 54, 42, 40, 45, 27, 56, 1, 51, 40, 22, 41, 50, 37, 48, 59,
				11, 36, 11, 19, 42, 49, 53, 51, 50, 39};
				*/
		int[] containerID = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
				2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
				3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,
				4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4};

		//create container
		for (int i = 1; i <= num; i++){
			requests.add( new Request(i, bw[i], 4, containerID[i]) );
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
		do {
			//run request from containers: highest priority first
			runContainers(p1);
			runContainers(p2);
			runContainers(p3);
			
			run = checkRequests(numOfRequests);
			
			increment();
			
		}while(run);
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
					
					if(r.getTime() == 0 && r.getStatus() != Status.completed){
						r.setFinishTime(time);
						completedRequests.add( c.RemoveFirstRequest() );
						r.setStatus(Status.completed);
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
