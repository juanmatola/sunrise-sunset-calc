package sunriseAndSunset.main.java.lib;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * SunCalc class for calculating sunrise and sunset times.
 */
public class SunCalc {

	// Desired zenith angle (with correction for refraction and solar disc size)
	private static final Double RISE_SET_ZENITH_ANGLE = 90.833 * Math.PI / 180.0;
	
	// Solar hour angle in radians (1 hour = 15 degrees)
	private Double gamma;
	private Double declination;
	private Double eqtime;

	/**
	 * Constructor for calculating sunrise and sunset times for the current day.
	 */
	public SunCalc() {
		LocalDate yearDay = LocalDate.now();
		gamma = calculateDailyAngle(yearDay);
		declination = calculateDeclination(gamma);
		eqtime = calculateEquationOfTime(gamma);
	}

	/**
	 * Constructor for calculating sunrise and sunset times for any given day of the
	 * year.
	 *
	 * @param yearDay date for which sunrise and sunset times will be calculated
	 */
	public SunCalc(LocalDate yearDay) {
		gamma = calculateDailyAngle(yearDay);
		declination = calculateDeclination(gamma);
		eqtime = calculateEquationOfTime(gamma);
	}

	/**
	 * Calculates sunrise time for a given location.
	 *
	 * @param location Location object containing latitude, longitude, and UTC
	 *                 offset
	 * @return LocalTime object representing sunrise time for the given location
	 */
	public LocalTime calculateSunriseHour(Location location) {
		Long segsSunrise = sunriseSeconds(location);
		LocalTime timeUtcZero = TimeUtils.segsToLocalTime(segsSunrise);
//		return TimeUtils.applyUTC(timeUtcZero, location.getUtc());
		return TimeUtils.applyUTC(timeUtcZero, location.getUtc());
	}

	/**
	 * Calculates sunset time for a given location.
	 *
	 * @param location Location object containing latitude, longitude, and UTC
	 *                 offset
	 * @return LocalTime object representing sunset time for the given location
	 */
	public LocalTime calculateSunsetHour(Location location) {
		Long segsSunset = sunsetSeconds(location);
		LocalTime timeUtcZero = TimeUtils.segsToLocalTime(segsSunset);
		return TimeUtils.applyUTC(timeUtcZero, location.getUtc());
	}

	/**
	 * Calculates the sunrise time in seconds (UTC-0) for a given location.
	 *
	 * @param location Location object containing latitude, longitude, and UTC
	 *                 offset
	 * @return Long value representing sunrise time in seconds for the given
	 *         location
	 */
	private Long sunriseSeconds(Location location) {

		Double salida_sol_min = this.sunriseMinutes(location);

		return TimeUtils.minsToSegs(salida_sol_min);

	}

	/**
	 * Calculates the sunset time in seconds (UTC-0) for a given location.
	 *
	 * @param location Location object containing latitude, longitude, and UTC
	 *                 offset
	 * @return Long value representing sunset time in seconds for the given location
	 */
	private Long sunsetSeconds(Location location) {

		Double puesta_sol_min = this.sunsetMinutes(location);

		return TimeUtils.minsToSegs(puesta_sol_min);

	}

	/**
	 * Calculates the sunrise time in minutes (UTC-0) for a given location.
	 *
	 * @param location Location object containing latitude, longitude, and UTC
	 *                 offset
	 * @return Double value representing sunrise time in minutes for the given
	 *         location
	 */
	private double sunriseMinutes(Location location) {

		Double latitud_rad = location.getLatitude() * Math.PI / 180.0;

		Double ha_grad = calculateSolarAngle(latitud_rad);

		return 720.0 - 4 * (location.getLongitude() + ha_grad) - eqtime;
	}

	/**
	 * Calculates the sunset time in minutes (UTC-0) for a given location.
	 *
	 * @param location Location object containing latitude, longitude, and UTC
	 *                 offset
	 * @return Double value representing sunset time in minutes for the given
	 *         location
	 */
	private double sunsetMinutes(Location location) {

		Double latitud_rad = location.getLatitude() * Math.PI / 180.0;

		Double ha_grad = this.calculateSolarAngle(latitud_rad);

		return 720.0 - 4 * (location.getLongitude() - ha_grad) - eqtime;
	}

	/**
	 * Calculates the solar angle for a given latitude in radians.
	 *
	 * @param latitude_rad Latitude in radians
	 * @return Double value representing the solar angle for the given latitude
	 */
	private Double calculateSolarAngle(Double latitude_rad) {

		Double ha_rad = Math.acos(Math.cos(RISE_SET_ZENITH_ANGLE) / (Math.cos(latitude_rad) * Math.cos(declination))
				- Math.tan(latitude_rad) * Math.tan(declination));

		return ha_rad * 180.0 / Math.PI;

	}

	/**
	 * Calculates the daily solar angle (gamma) for a given date.
	 *
	 * @param day LocalDate object representing the date for which the daily solar
	 *            angle will be calculated
	 * @return Double value representing the daily solar angle (gamma) for the given
	 *         date
	 */
	private Double calculateDailyAngle(LocalDate day) {

		Integer yearDay = day.getDayOfYear();

		Integer leapYearCorrection = 0;
		if (day.isLeapYear())
			leapYearCorrection = 1;

		// Daily angle in radians
		return Math.PI * 2.0 / (365.0 + leapYearCorrection) * (yearDay - 1);

	}

	/**
	 * Calculates the solar declination angle based on the daily solar angle
	 * (gamma).
	 *
	 * @param gamma Double value representing the daily solar angle
	 * @return Double value representing the solar declination angle
	 */
	private Double calculateDeclination(Double gamma) {
		return 0.006918 - 0.399912 * Math.cos(gamma) + 0.070257 * Math.sin(gamma) - 0.006758 * Math.cos(2 * gamma)
				+ 0.000907 * Math.sin(2 * gamma) - 0.002697 * Math.cos(3 * gamma) + 0.00148 * Math.sin(3 * gamma);
	}

	/**
	 * Calculates the equation of time (difference in minutes between mean solar
	 * time and apparent solar time).
	 *
	 * @param gamma Double value representing the daily solar angle
	 * @return Double value representing the equation of time
	 */
	private Double calculateEquationOfTime(Double gamma) {
		return 229.18 * (0.000075 + 0.001868 * Math.cos(gamma) - 0.032077 * Math.sin(gamma)
				- 0.014615 * Math.cos(2 * gamma) - 0.040849 * Math.sin(2 * gamma));

	}

}
