package sunriseAndSunset.main.java;

import java.time.LocalDate;
import java.time.LocalTime;

import sunriseAndSunset.main.java.lib.Location;
import sunriseAndSunset.main.java.lib.SunCalc;

public class SunriseAndSunset {

	// Useful simulator: http://astro.unl.edu/naap/motion3/animations/sunmotions.html
	public static void main(String[] args) {

		Location location = new Location();

		location.setLatitud(-33.2394434);
		location.setLongitud(-60.318236);
		location.setUtc(-3);

		LocalDate day = LocalDate.now();
		SunCalc sunCalculator = new SunCalc(day);

		LocalTime sunrise = sunCalculator.calculateSunriseHour(location);
		LocalTime sunset = sunCalculator.calculateSunsetHour(location);

		System.out.println("Sunrise: " + sunrise);
		System.out.println("Sunset: " + sunset);

	}

}
