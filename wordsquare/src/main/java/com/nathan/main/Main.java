package com.nathan.main;

import java.util.Scanner;

import com.nathan.run.CommandLineArgumentRunner;
import com.nathan.run.IWordSquareRunner;
import com.nathan.run.InteractiveRunner;

public class Main {
	
	static Scanner sc;
	
	public static void main(String[] args) {
		System.out.println("\nWORD SQUARE PRG:\n");
		IWordSquareRunner runner;
		int count = 0;
		do {
			if ((args.length > 0) && (count == 0)) {
				runner = new CommandLineArgumentRunner(args);
			} else { 
				runner = new InteractiveRunner();
			}
			runner.run();
			count++;
		}while(continueRunning());
		sc.close();
		System.exit(0);
	}
	
	public static boolean continueRunning() { 
		System.out.println("Run programme again in interactive mode [y/n]? ");
		sc = new Scanner(System.in);
		String input = sc.next().toLowerCase();
		while(true) { 
			if(input.equals("y") ) { 
				System.out.println("Running again ...");	
				return true;
			} else if(input.equals("n")){
				 System.out.println("Terminating Programme...");
				 return false;
			} else {
				System.out.println("Invalid input, ensure character entered is y or n :");
				input = sc.next().toLowerCase();
			}
		}
	}

}
