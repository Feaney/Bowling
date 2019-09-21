import java.io.*;
import java.util.Scanner;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;

public class BowlingScores 
{
	public static void main(String[] args) throws Exception 
	{
		Scanner scnFilePath = new Scanner(System.in);
		System.out.println("Please enter the filepath of the scores.");
		
		String strFilepath = scnFilePath.next();
		
		//System.out.println(String.format("Filepath is %s", strFilepath));
		
		File fileScores = new File(strFilepath);
		
		if (!fileScores.exists())
		{
			System.out.println("File not found");
			System.exit(0);
		}
		
		BufferedReader brScoresFile = new BufferedReader(new FileReader(fileScores));
		
		String strFileContent;
		while ((strFileContent = brScoresFile.readLine()) != null)
			System.out.println(strFileContent);
		
		
	}
}