package General;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWriter {
	
	private String fileName;
	private File file;
	
	public FileWriter(String fileName) {
		this.fileName = fileName;
		file = new File(fileName);
	}
	
	
	public void addNewWeight (Attacks attack, double weight) throws IOException {
		ArrayList<String> newFile = new ArrayList<String>();
		
		Scanner reader = new Scanner(file).useDelimiter(" -- ");
		
		boolean spellFound = false;
		
		while (reader.hasNextLine()) {
			Scanner lineReader = new Scanner(reader.nextLine()).useDelimiter(" -- ");
			String next = lineReader.next();
			if (!next.equals(attack.attackName)) {
				newFile.add(next + " -- " + lineReader.next().trim());
			} else {
				newFile.add(attack.attackName + " -- " + Math.floor(weight * 100) / 100);
				spellFound = true;
			}
			lineReader.close();
		}
		
		if (!spellFound) {
			newFile.add(attack.attackName + " -- " + Math.floor(weight * 100) / 100);
		}
		
		reader.close();
		
		writeToFile(newFile);
	}
	
	public double getSpellWeight(Attacks attack) throws FileNotFoundException {
		Scanner reader = new Scanner(file).useDelimiter(" -- ");
		
		while (reader.hasNextLine()) {
			Scanner lineReader = new Scanner(reader.nextLine()).useDelimiter(" -- ");
			String next = lineReader.next();
			if (next.equals(attack.attackName)) {
				return Double.parseDouble(lineReader.next());
			}
			lineReader.close();
		}
		
		reader.close();
		return -1;
	}
	
	public void writeToFile(ArrayList<String> toWrite) throws IOException {
		PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		for (String s : toWrite) {
			writer.println(s);
		}
		
		writer.close();
	}

}
