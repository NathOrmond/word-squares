package com.nathan.run;

public class ValidInputChecker {

	public boolean isArgumentValid(String[] args) {
		if ((args.length == 2) && 
			(isInteger(args[0])) && 
			(args[1].length() == ((Integer.parseInt(args[0])) * (Integer.parseInt(args[0]))) )){
			return true;
		}
		
		System.err.println("Invalid arguments given: \n");
		for(String arg : args) { 
			System.err.println(arg);
		}
		System.out.print("\n");
		return false;
	}

	private boolean isInteger(String str) {
		if (str == null) {
			return false;
		}
		int length = str.length();
		if (length == 0) {
			return false;
		}
		int i = 0;
		if (str.charAt(0) == '-') {
			if (length == 1) {
				return false;
			}
			i = 1;
		}
		for (; i < length; i++) {
			char c = str.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

}
