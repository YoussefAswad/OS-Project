package com.company;

import java.io.*;
import java.util.Hashtable;
import java.util.Scanner;

public class OperatingSystem {
	Hashtable<String, String> memory = new Hashtable<String, String>();

	public String readFile(String var) throws IOException {
		String filename = (String) memory.get(var);
		String s = "";
		BufferedReader f = new BufferedReader(new FileReader("src/com/company/" + filename));
		String current = f.readLine();
		while (current != null) {
			s += current + "\n";
			current = f.readLine();
		}
		f.close();
		return s;
	}

	public void writeFile(String src, String dest) throws IOException {
		String filename = memory.get(src);
		String data = memory.get(dest);
		BufferedWriter w = new BufferedWriter(new FileWriter("src/com/company/" + filename));
		w.write(data);
		w.flush();
		w.close();
	}

	public static void print(String o) {
		System.out.println(o);
	}

	public void add(String var1, String var2) {
		String val1 = memory.get(var1);
		String val2 = memory.get(var2);
		Double x = Double.parseDouble(val1);
		Double y = Double.parseDouble(val2);
		Double d = x + y;
		if ((val1 + val2).contains(".")) {
			assign(var1, d.toString());
		} else {
			int o = d.toString().indexOf(".");
			String s = d.toString().substring(0, o);
			assign(var1, s);
		}

	}

	public void assign(String var, String val) {
		if (!memory.contains(var))
			memory.put(var, val);
		else
			memory.replace(var, val);
	}

	public void interpreter(String programName) throws IOException {
		BufferedReader br;
		br = new BufferedReader(new FileReader("src/com/company/" + programName));
		String current = br.readLine();
		while (current != null) {
			String[] line = current.split(" ");
			int i = 0;
			switch (line[i]) {
			case "assign":
				if (line[i + 2].equals("input")) {
					String s = input();
					assign(line[i + 1], s);
				} else if (line[i + 2].equals("readFile")) {
					assign(line[i + 1], readFile(line[i + 3]));
				} else {
					assign(line[i + 1], memory.get(line[i + 2]));
				}
				break;
			case "print":
				if (memory.containsKey(line[i + 1]))
					print(memory.get(line[i + 1]));
				else
					print(line[i + 1]);
				break;
			case "add":
				add(line[i + 1], line[i + 2]);
				break;
			case "readFile":
				readFile(line[i + 1]);
				break;
			case "writeFile":
				writeFile(line[i + 1], line[i + 2]);

				break;
			}
			current = br.readLine();
		}
		br.close();
	}

	public static String input() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String s = null;
		s = sc.nextLine();
		return s;
	}

	public static void main(String[] args) throws IOException {
		OperatingSystem o = new OperatingSystem();
		o.interpreter("Program 2.txt");
		o.interpreter("Program 1.txt");
		o.interpreter("Program 3.txt");
	}
}
