import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.HashMap;

import org.junit.Test;

public class Tester {

	@Test
	public void testDistances() {

		//(String paceMinutes, String paceSeconds, String hours, String minutes, String seconds){
		DistanceTest test1 = new DistanceTest("45", "12", "1", "1", "1");
		DistanceTest test2 = new DistanceTest("22", "9", "0", "4", "59");
		DistanceTest test3 = new DistanceTest("20", "0", "33", "33", "0");
		
		/*
		DistanceTest test4 = new DistanceTest("-1z", "12hh", "393", "992", "0");
		System.out.println(String.valueOf(test4.GetAnswer()));
		*/
		
		//The following comparative results are from the online calculator
		//given in the assignment description using the same input values
		//used in the program
		assertEquals(test1.GetAnswer(), 1.3499262536873156, 0.001);
		assertEquals(test2.GetAnswer(), 0.22498118886380739, 0.001);
		assertEquals(test3.GetAnswer(), 100.65, 0.01);
	}
	
	@Test
	public void testPaces(){
		
		//(String distance, String hours, String minutes, String seconds)
		PaceTest test1 = new PaceTest("100", "0", "20", "00");
		PaceTest test2 = new PaceTest("4", "0", "56", "12");
		
		assertEquals(test1.GetAnswerInSeconds(), 12.0, 0.001);
		assertEquals(test2.GetAnswerInSeconds(), 843.0, 0.001);	//843 seconds = 14 minutes 3 seconds
	}
	
	@Test
	public void testTimes(){
		//(String distance, String minutes, String seconds)
		TimeTest test1 = new TimeTest("12", "59", "12");
		TimeTest test2 = new TimeTest("2", "20", "0");
		
		assertEquals(test1.GetAnswer(), 42624.0, 0.001);
		assertEquals(test2.GetAnswer(), 2400.0, 0.001);
	}

}

class TimeTest{
	private String distance;
	private String txtMinutes;
	private String txtSeconds;
	
	public TimeTest(String distance, String minutes, String seconds){
		this.distance = distance;
		this.txtMinutes = minutes;
		this.txtSeconds = seconds;
	}
	
	public double GetAnswer(){
		double _distance = PaceCalculations.SafeParseDouble(distance);
		double pace = PaceCalculations.GetTotalSeconds("0", txtMinutes, txtSeconds);
		return PaceCalculations.Time(_distance, pace, 1);
	}
}

class PaceTest{
	private String distance;
	private String txtHours;
	private String txtMinutes;
	private String txtSeconds;
	
	public PaceTest(String distance, String hours, String minutes, String seconds){
		this.distance = distance;
		this.txtHours = hours;
		this.txtSeconds = seconds;
		this.txtMinutes = minutes;
	}
	
	public double GetAnswerInSeconds(){
		double dbldistance = PaceCalculations.SafeParseDouble(distance);
		double time = PaceCalculations.GetTotalSeconds(txtHours, txtMinutes, txtSeconds);
		return PaceCalculations.Pace(time,  dbldistance, 1);
	}
}

class DistanceTest{
	private String paceMinutes;
	private String paceSeconds;
	private String txtHours;
	private String txtMinutes;
	private String txtSeconds;
	
	public DistanceTest(String paceMinutes, String paceSeconds, String hours, String minutes, String seconds){
		this.paceMinutes = paceMinutes;
		this.paceSeconds = paceSeconds;
		this.txtHours = hours;
		this.txtMinutes = minutes;
		this.txtSeconds = seconds;
	}
	
	public double GetAnswer(){
		double pace = PaceCalculations.GetTotalSeconds("0", paceMinutes, paceSeconds);
		double time = PaceCalculations.GetTotalSeconds(txtHours, txtMinutes, txtSeconds);
		return PaceCalculations.Distance(time, pace, 1.0);
	}
}
