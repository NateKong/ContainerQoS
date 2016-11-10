package base;

import java.util.ArrayList;
import java.util.Arrays;

/**************************************
 * Simulation of a containers running cloudlets
 * in a Cloud environment
 * 
 * one cloudlet is ran in a 
 * 
 * @author Nathan Kong
 *
 **************************************/
public class Base {

	public static void main(String[] args) {
		ArrayList<Integer> priority = new ArrayList<Integer>(Arrays.asList
				(10, 4, 6, 7, 2, 7, 1, 9, 2, 3, 5, 2, 4, 4, 2, 5, 3, 5, 3,
				2, 4, 7, 10, 9, 3, 2, 6, 2, 10, 2, 2, 3, 7, 8, 6, 3, 8, 7,
				3, 10, 5, 10, 9, 7, 1, 7, 2, 7, 9, 9, 10, 9, 1, 9, 9, 7, 4,
				6, 4, 3, 10, 7, 1, 9, 4, 5, 3, 8, 4, 3, 10, 5, 10, 5, 9, 6,
				6, 8, 10, 6, 4, 1, 1, 4, 5, 5, 8, 6, 5, 6, 8, 6, 8, 8, 1,
				1, 8, 1, 1, 8 ));

		for(Integer i : priority){
			System.out.println(i);
		}
	}

	
	
}
