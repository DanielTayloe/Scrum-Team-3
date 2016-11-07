
import java.text.DecimalFormat;

/***
 * Scrum Group 3
 * @author jacob psimos
 *
 */

//Reference: http://www.coolrunning.com/engine/4/4_1/96.shtml

public class PaceCalculations {
	
	/*
	 * The following defined bytes are the values for the first
	 * argument when converting a unit of measurement to a different one
	 * 
	 * See ConvertUnit(byte oneOfThese, double value)
	 */
	public static final byte CONV_MILES_TO_KILOMETERS = 0x01;
	public static final byte CONV_MILES_TO_METERS = 0x02;
	public static final byte CONV_MILES_TO_YARDS = 0x03;
	public static final byte CONV_MILES_TO_FEET = 0x04;
	public static final byte CONV_KILOMETERS_TO_MILES = 0x05;
	public static final byte CONV_KILOMETERS_TO_METERS = 0x06;
	public static final byte CONV_KILOMETERS_TO_YARDS = 0x07;
	public static final byte CONV_KILOMETERS_TO_FEET = 0x08;
	public static final byte CONV_METERS_TO_MILES = 0x09;
	public static final byte CONV_METERS_TO_KILOMETERS = 0x10;
	public static final byte CONV_METERS_TO_YARDS = 0x11;
	public static final byte CONV_METERS_TO_FEET = 0x12;
	public static final byte CONV_YARDS_TO_MILES = 0x13;
	public static final byte CONV_YARDS_TO_KILOMETERS = 0x14;
	public static final byte CONV_YARDS_TO_METERS = 0x15;
	public static final byte CONV_YARDS_TO_FEET = 0x16;
	public static final byte CONV_FEET_TO_MILES = 0x17;
	public static final byte CONV_FEET_TO_KILOMETERS = 0x18;
	public static final byte CONV_FEET_TO_METERS = 0x19;
	public static final byte CONV_FEET_TO_YARDS = 0x1A;

	public PaceCalculations(){
		
	}
	
	/***
	 * Calculate time, pace, and factor using strings
	 * 
	 * @param time
	 * @param pace
	 * @param factor
	 * @return
	 */
	public static double Distance(String time, String pace, String factor){
		try{
			double temp = 0.0;
			double dblTime = 0.0;
			double dblPace = 0.0;
			double dblFactor = 0.0;
			
                        // Parse the numbers and ensure they are above zero
			temp = Double.parseDouble(time);
			dblTime = Math.max(0.0, temp);
			
			temp = Double.parseDouble(pace);
			dblPace = Math.max(0.0, temp);
			
			temp = Double.parseDouble(factor);
			dblFactor = Math.max(0.0, temp);
			
			return Distance(dblTime, dblPace, dblFactor);
		}catch(Exception e){
			System.err.println("Distance() - Exception: " + e.getMessage());
		}
		return -1.0;
	}
	
	
	public static double Distance(double time, double pace, double factor){
		//Dist = Time / (Pace / factor)
		if(pace != 0){
			return time / (pace / factor);
		}else{
			return 0;
		}
	}
	
	public static double Pace(String time, String distance, String factor){
		try{
			double temp = 0.0;
			double dblTime = 0.0;
			double dblDistance = 0.0;
			double dblFactor = 0.0;
			
                        // Parse the numbers and ensure they are above zero
			temp = Double.parseDouble(time);
			dblTime = Math.max(0.0, temp);
			
			temp = Double.parseDouble(distance);
			dblDistance = Math.max(0.0, temp);
			
			temp = Double.parseDouble(factor);
			dblFactor = Math.max(0.0, temp);
			
			return Pace(dblTime, dblDistance, dblFactor);
		}catch(Exception e){
			System.err.println("Pace() - Exception: " + e.getMessage());
		}
		return -1.0;
	}
	
	public static double Pace(double time, double distance, double factor){
		//Pace = (Time / distance) / factor
		return (time / distance) / factor;
	}
	
	public static double Time(String distance, String pace, String factor){
		try{
			double temp = 0.0;
			double dblDistance = 0.0;
			double dblPace = 0.0;
			double dblFactor = 0.0;
			
                        // Parse the numbers and ensure they are above zero
			temp = Double.parseDouble(distance);
			dblDistance = Math.max(0.0, temp);
                        
			temp = Double.parseDouble(pace);
			dblPace = Math.max(0.0, temp);
			
			temp = Double.parseDouble(factor);
			dblFactor = Math.max(0.0, temp);
			
//			return Time(dblDistance, dblPace, dblFactor);
		}catch(Exception e){
			System.err.println("Time() - Exception: " + e.getMessage());
		}
		return -1.0;
	}
	
	public static double Time(double distance, double pace, double factor){
		//Time = Dist x Pace x factor
		return distance * pace * factor;
	}
	
	
	/***
	 * Calculate the total number of seconds from a time formatted string
	 * hh:mm:ss
	 * 
	 * Negative numbers are accepted, however they are set to zero for the field
	 * where a negative number is found.
	 * 
	 * Exceptions:
	 * Invalid input will be printed to standard error stream along with available details
	 * and -1 is returned.
	 * 
	 * @param input hh:mm:ss string
	 * @return total number of seconds as integer
	 */
	public static double GetTotalSeconds(String input){
		
		String[] elements = input.split(":");
		if(elements.length != 3){
			
			System.err.println("GetTotalSeconds() - Invalid input string format");
			System.err.println("> " + input);
			
		}else{
		
			try{
				
				double temp = 0;
				double hours = 0;
				double minutes = 0;
				double seconds = 0;
				
                                // Parse the numbers and ensure they are above zero
				temp = Double.parseDouble(elements[0]);
				hours = Math.max(0.0, temp);
				
				temp = Double.parseDouble(elements[1]);
				minutes = Math.max(0.0, temp);
				
				temp = Double.parseDouble(elements[2]);
				seconds = Math.max(0.0, temp);
				
				//total = (hours * 3600) + (minutes * 60) + seconds;
				
				return GetTotalSeconds(hours, minutes, seconds);
				
			}catch(Exception e){
				System.err.println("GetTotalSeconds() Exception message: " + e.getMessage());
				e.printStackTrace();
			}
			
		} //end if (string elements array length)
		
		return -1;
	}
	
	/***
	 * Returns the total number of seconds given hours minutes and seconds
	 * 
	 * @param hours Number of hours
	 * @param minutes Number of minutes
	 * @param seconds Number of seconds
	 * @return seconds as integer
	 */
	public static double GetTotalSeconds(double hours, double minutes, double seconds){
		if(hours < 0) { hours = 0; }
		if(minutes < 0) { minutes = 0; }
		if(seconds < 0) { seconds = 0; }
		
		return (hours * 3600.0) + (minutes * 60.0) + seconds;
	}
	
	/***
	 * Gets the total number of seconds from three separate strings representing hours, minutes, and seconds
	 * If any of the strings represent negative numbers, they are truncated to zero.
	 * 
	 * Exceptions
	 * If any of the strings are not able to be parsed to integers, the function returns -1
	 * @param strHours
	 * @param strMinutes
	 * @param strSeconds
	 * @return
	 */
	public static double GetTotalSeconds(String strHours, String strMinutes, String strSeconds){
		try{
			
			double temp = 0;
			double hours = 0;
			double minutes = 0;
			double seconds = 0;
			
			temp = Double.parseDouble(strHours);
			hours = Math.max(0.0, temp);
			
			temp = Double.parseDouble(strMinutes);
			minutes = Math.max(0.0, temp);
			
			temp = Double.parseDouble(strSeconds);
			seconds = Math.max(0.0, temp);
			
			return GetTotalSeconds(hours, minutes, seconds);
			
		}catch(Exception e){
			System.err.println("GetTotalSeconds() - Exception: " + e.getMessage());
		}
		return -1;
	}
	
	/***
	 * Returns the number of whole hours from a given number of total seconds
	 * Negative numbers or zero always return zero
	 * 
	 * @param totalSeconds number of seconds to retrieve hours from
	 * @return hours as integer
	 */
	public static double GetHoursFromSeconds(double totalSeconds){
		if(totalSeconds <= 0) { return 0.0; }
		
		return totalSeconds / 3600.0;
	}
	
	/**
	 * Splits a number of seconds into seconds, minutes and hours strings
	 * 
	 * @param seconds total number of seconds
	 * @return string array, containing the hours, minutes and then seconds in order
	 */
	public static String[] secondsToParts(double seconds) {
		int wholeSeconds = (int)Math.floor(seconds);
		double fraction = seconds - (double)wholeSeconds;
		
		int secondsPart = wholeSeconds  % 60;
		int minutesPart = (wholeSeconds / 60) % 60;
		int hoursPart = wholeSeconds / 3600;
		
		DecimalFormat d = new DecimalFormat("#.#");

		String out[] = new String[] {""+hoursPart, ""+minutesPart, d.format(secondsPart+fraction)};
		return out;
	}
	
	/***
	 * Returns the number of whole minutes from a given number of total seconds
	 * Negative numbers or zero always return zero
	 * 
	 * @param totalSeconds number of seconds to retrieve minutes from
	 * @return minutes as integer
	 */
	public static double GetMinutesFromSeconds(double totalSeconds){
		if(totalSeconds <= 0) { return 0.0; }
		
		return totalSeconds / 60.0;
	}
	
	/***
	 * Converts `value` of value X to the same value in unit Y
	 * 
	 * 
	 * Example:
	 * 		To convert miles to yards, input CONV_MILES_TO_YARDS as
	 * 		the `conversion` and the number of miles for `value`.  The total number
	 * 		of yards would be returned
	 * @param conversion CONV_(UNIT)_TO_(UNIT) See declarations for these values
	 * @param value the input value that will be converted
	 * @return the converted value
	 */
	public static double ConvertUnit(final byte conversion, final double value){
		double returnValue = 0.0;
		switch(conversion){
			case  CONV_MILES_TO_METERS:
				returnValue = value * 1609.33999997549;
			break;
			case  CONV_MILES_TO_YARDS:
				returnValue = value * 1760.0;
			break;
			case  CONV_MILES_TO_FEET:
				returnValue = value * 5280.0;
			break;
			case  CONV_KILOMETERS_TO_MILES:
				returnValue = value * 0.621371;
			break;
			case  CONV_KILOMETERS_TO_METERS:
				returnValue = value * 1000.0;
			break;
			case  CONV_KILOMETERS_TO_YARDS:
				returnValue = value * 1093.61;
			break;
			case  CONV_KILOMETERS_TO_FEET:
				returnValue = value * 3280.84;
			break;
			case  CONV_METERS_TO_MILES:
				returnValue = value * 0.000621371;
			break;
			case  CONV_METERS_TO_KILOMETERS:
				returnValue = value * 0.001;
			break;
			case  CONV_METERS_TO_YARDS:
				returnValue = value * 1.09361;
			break;
			case  CONV_METERS_TO_FEET:
				returnValue = value * 3.28084;
			break;
			case  CONV_YARDS_TO_MILES:
				returnValue = value * 0.000568182;
			break;
			case  CONV_YARDS_TO_KILOMETERS:
				returnValue = value * 0.0009144;
			break;
			case  CONV_YARDS_TO_METERS:
				returnValue = value * 0.9144;
			break;
			case  CONV_YARDS_TO_FEET:
				returnValue = value * 3.0;
			break;
			case  CONV_FEET_TO_MILES:
				returnValue = value * 0.000189394;
			break;
			case  CONV_FEET_TO_KILOMETERS:
				returnValue = value * 0.0003048;
			break;
			case  CONV_FEET_TO_METERS:
				returnValue = value * 0.3048;
			break;
			case  CONV_FEET_TO_YARDS:
				returnValue = value / 3.0;
			break;
		}
		return returnValue;
	}
	
}
