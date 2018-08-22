package caa.sportify.utility;

/**
 * @author Crispin A
 *
 */
public class NumberUtil {

	/**
	 * 
	 * Utility method that adds ordinal characters to a number.
	 * 
	 * @param value
	 *            - positive integer
	 * @return number with its correct ordinal characters
	 */
	public static String getOrdinalFor(int value) {
		int remainder = value % 10;
		switch (remainder) {
		case 1:
			return value + "st";
		case 2:
			return value + "nd";
		case 3:
			return value + "rd";
		default:
			return value + "th";
		}
	}

}
