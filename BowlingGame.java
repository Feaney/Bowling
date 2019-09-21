import java.util.*; 
import java.lang.reflect.Array;

public class BowlingGame
{
	String strGameID = "";
	Integer intFrames = 0;
	ArrayList<String> lstThrows = new ArrayList<String>();
	Integer intScore = 0;
	
	public static void main(String[] args)
	{
	}
	
	public void CalculateScore()
	{
		for(int i = 0; i < lstThrows.size(); i++) 
		{
			//If score is numeric simply add it
			if (isNumeric((String)lstThrows.get(i)))
			{
				intScore = intScore + Integer.parseInt(lstThrows.get(i));
			}
			else
			{
				switch(lstThrows.get(i))
				{
					case "x":
					case "X":					
						//If score is a strike add 10 for the strike, plus double the next two throws (1 throw for another strike)
						intScore = intScore + 10;
						
						if (lstThrows.size()-1 >= i+2)
						{
							if (lstThrows.get(i+1) == "X" || lstThrows.get(i+2) == "/")
							{
								intScore = intScore + 10;
							}
							
							if (isNumeric((String)lstThrows.get(i+1)))
							{
								intScore = intScore + Integer.parseInt(lstThrows.get(i+1));
							}
						}
						
						if (lstThrows.size()-1 >= i+2)
						{
							if (isNumeric((String)lstThrows.get(i+2)))
							{
								intScore = intScore + Integer.parseInt(lstThrows.get(i+2));
							}
						}
						
						break;
					case "/":
						//If throw was a spare double next next score
						intScore = intScore + (10 - Integer.parseInt(lstThrows.get(i-1)));
						
						if (lstThrows.size()-1 >= i+1)
						{
							if (lstThrows.get(i+1) == "X")
							{
								intScore = intScore + 10;
							}
							
							if (isNumeric((String)lstThrows.get(i+1)))
							{
								intScore = intScore + Integer.parseInt(lstThrows.get(i+1));
							}
						}
					
						break;
					default:
						break;
				}
			}
		}
	}
	
	public static boolean isNumeric(String strNum) 
	{
		//Simple function to check if a value is numeric
		try 
		{
			double dblTest = Double.parseDouble(strNum);
		} 
		catch (NumberFormatException | NullPointerException nfe) 
		{
			return false;
		}
		
		return true;
	}
} 