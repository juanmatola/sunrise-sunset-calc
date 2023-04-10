package sunriseAndSunset.main.java.lib;

import java.time.LocalTime;

/**
 * An abstract utility class for time-related calculations and conversions.
 */
public abstract class TimeUtils {

	/**
	 * Converts minutes to seconds.
	 *
	 * @param minutos Double value representing the number of minutes
	 * @return Long value representing the equivalent number of seconds
	 */
	public static Long minsToSegs(Double minutos) {

		Double salida_sol_sec = minutos * 60;

		return salida_sol_sec.longValue();

	}

	/**
	 * Converts seconds to a LocalTime object.
	 *
	 * @param horarioEnSegundos Long value representing the number of seconds
	 * @return LocalTime object representing the time equivalent to the given
	 *         seconds
	 */
	public static LocalTime segsToLocalTime(Long horarioEnSegundos) {

		return LocalTime.MIDNIGHT.plusSeconds(horarioEnSegundos);

	}

	/**
	 * Applies UTC offset to a given LocalTime.
	 *
	 * @param time LocalTime object representing the time to which the UTC offset
	 *             will be applied
	 * @param utc  Integer value representing the UTC offset
	 * @return LocalTime object representing the time after applying the UTC offset
	 */
	public static LocalTime applyUTC(LocalTime time, Integer utc) {

		return time.plusHours(utc.longValue());

	}

}