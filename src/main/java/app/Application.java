package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import model.barem;

public class Application {

	public static void main(String[] args) {
		try {
			Application app = new Application();
			String inputFile = app.getFileWithUtil("Data.txt");
			List<barem> baremList = readTextFile(inputFile);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static List<barem> readTextFile(String path) {
		List<barem> baremList = new ArrayList<>();
		barem baremObj = new barem();
		try {
			BufferedReader br = null;
			FileReader fr = null;
			try {
				fr = new FileReader(path);
				br = new BufferedReader(fr);

				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					if(sCurrentLine.contains("|")){
						System.out.println(sCurrentLine);
						baremObj = parseBarem(sCurrentLine);	
						
						baremList.add(baremObj);
					}
				}

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					if (br != null)
						br.close();
					if (fr != null)
						fr.close();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return baremList;
	}

	private String getFileWithUtil(String fileName) {
		String path = "";
		ClassLoader classLoader = getClass().getClassLoader();
		try {
			File file = new File(classLoader.getResource(fileName).getFile());
			path = file.getPath();
		} catch (Exception e) {
			System.out.println(e);
		}
		return path;
	}
	
	private static barem parseBarem(String inpString) {
		barem baremObj = new barem();
		String h = "";
		String v = "";
		String vLe = "";
		try {
			String[] arrInput = inpString.split("\\|");
			for (int i = 0; i < arrInput.length; i++) {
				if (i == 0) {
					h = arrInput[0];
					baremObj.setH(h);
				} else if (i == 1) {
					v = arrInput[1];
					baremObj.setV(v);
				} else if (i == 2) {
					vLe = arrInput[2];
					baremObj.setvLe(vLe);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return baremObj;
	}
}
