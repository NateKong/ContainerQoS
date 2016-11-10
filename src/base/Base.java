package base;

import java.util.ArrayList;
import java.util.Arrays;

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
	private static ArrayList<Request> requests;

	public static void main(String[] args) {
		time = 0;
		int numOfHosts = 1;
		int hostBW = 10000; //Rounded number from CloudSim
		int numOfVMs = 5;
		int vmBW = 1000; //Rounded number from CloudSim
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
		//create request
		createRequests(numOfRequests);
		
		/**Setup - Connection of Objects*/
		setup();
		
		//run
		
		//print
		
		//close everything out
		
	}

	public static void createVMs(int num, int bw){
		VMs = new ArrayList<VM>();
		System.out.println("\n********************");
		for (int i = 1; i <= num; i++){
			VM vm = new VM(i, bw);
			VMs.add(vm);
			System.out.println("VM " + vm.getId() + " created");
		}
	}


	private static void createContainers(int num) {
		containers = new ArrayList<Container>();
		System.out.println("\n********************");
		
		//random generated data
		int[] priority = {3, 2, 3, 1, 2, 3, 3, 2, 2, 1, 3};
		
		for (int i = 1; i <= num; i++){
			Container c = new Container(i, priority[i]);
			containers.add( c );
			System.out.println("Container " + c.getId() + " has a priority of " + c.getPriority() );
		}
	}

	private static void createRequests(int num) {
		requests = new ArrayList<Request>();
		System.out.println("\n********************");
		
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
			Request r = new Request(i, bw[i], requestTime[i], containerID[i]);
			requests.add( r );
			System.out.println("Requests: " + r.getId() + " requires " + r.getBw() + " bw and will take " + r.getTime() + " seconds");
		}		
	}
	
	private static void setup() {
		// TODO Auto-generated method stub
		
	}
	
}