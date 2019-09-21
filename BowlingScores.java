import java.io.*;
import java.util.*; 
import java.lang.Math.*;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.parser.*; 

public class BowlingScores 
{
		
	public static void main(String[] args) throws Exception 
	{
		//Request filepath for JSON file and await response
		Scanner scnFilePath = new Scanner(System.in);
		System.out.println("Please enter the filepath of the scores.");
		String strFilepath = scnFilePath.next();
		
		//Test filepath - comment out for live
		strFilepath = "C:\\Test\\games.json";
		
		//Check if file exists, if not exit program
		File fileScores = new File(strFilepath);
		
		if (!fileScores.exists())
		{
			System.out.println("File not found");
			System.exit(0);
		}
		
		//Create an array of games to store the details from json
		ArrayList<BowlingGame> lstGames = new ArrayList<BowlingGame>();
		
		//Parse JSON file.
		JSONParser jsonParser = new JSONParser();
		
		try (FileReader frScores = new FileReader(fileScores))
		{
			Object objJSON = jsonParser.parse(frScores);
			
			JSONArray jaGames = (JSONArray) objJSON;			
			
			for (Object objGame : jaGames)
			{
				JSONObject jsonGame = (JSONObject) objGame;

				//Read Details from JSON
				String strGameID = (String) jsonGame.get("gameId");
				JSONArray jaGameThrows = (JSONArray) jsonGame.get("throws");
				
				//Create Game class and add to array
				BowlingGame bgGame = new BowlingGame();
				bgGame.strGameID = strGameID;
				bgGame.intFrames = (int) Math.ceil(jaGameThrows.size()/2);
				
				
				Iterator itrThrows = jaGameThrows.iterator(); 
          
				while (itrThrows.hasNext())  
				{ 
					String strThrow = (String) itrThrows.next();
					bgGame.lstThrows.add(strThrow);
				} 
				
				bgGame.CalculateScore();
				lstGames.add(bgGame);
			}
		}
		
		System.out.println("------------");
		System.out.println("Please enter a game ID to see the details or All for all games.");
		String strResponse = scnFilePath.next();
		
		switch (strResponse)
		{
			case "All":
			case "all":
				ShowAllScores(lstGames);
				break;
			default:
				ShowGameScores(lstGames, strResponse);
				break;
		}
	}
	
	public static void ShowAllScores(ArrayList<BowlingGame> lstGames)
	{
		//Iterates through all games and outputs the score
		for(int i = 0; i < lstGames.size(); i++) 
		{
			BowlingGame bgGame = new BowlingGame();
			bgGame = lstGames.get(i);
			
			System.out.println("------------");
			System.out.println("GameID: " + bgGame.strGameID);
			System.out.println("Frames Played: " + bgGame.intFrames);
			System.out.println("Score: " + bgGame.intScore);
		}
	}
	
	public static void ShowGameScores(ArrayList<BowlingGame> lstGames, String strID)
	{
		//Locates one specific game and outputs the score
		for(int i = 0; i < lstGames.size(); i++) 
		{
			BowlingGame bgGame = new BowlingGame();
			bgGame = lstGames.get(i);
			
			if (bgGame.strGameID.equals(strID))
			{
				System.out.println("------------");
				System.out.println("GameID: " + bgGame.strGameID);
				System.out.println("Frames Played: " + bgGame.intFrames);
				System.out.println("Score: " + bgGame.intScore);
			}
		}
	}
}

