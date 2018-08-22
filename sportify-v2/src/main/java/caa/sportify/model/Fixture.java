package caa.sportify.model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

/**
 * @author Crispin A.
 *
 */
public class Fixture {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/
	private int id;
	private String division;
	private String date;
	private String HomeTeam;
	private String AwayTeam;
	private float B365H;
	private float B365D;
	private float B365A;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	public Fixture() {

	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public int getId() {
		return id;
	}

	public String getDivision() {
		return division;
	}

	public String getDate() {
		return date;
	}

	public String getHomeTeam() {
		return HomeTeam;
	}

	public String getAwayTeam() {
		return AwayTeam;
	}

	public float getB365H() {
		return B365H;
	}

	public float getB365D() {
		return B365D;
	}

	public float getB365A() {
		return B365A;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Returns true if the date field of the Fixture object is today's date (NOW).
	 * 
	 * @return true if the date of the fixture is today; false otherwise
	 * @throws ParseException
	 * 
	 */
	public boolean isToday() throws ParseException {
		Date date = DateUtils.parseDate(this.date, "dd/MM/yy");
		return DateUtils.isSameDay(date, Calendar.getInstance().getTime());
	}

	/**************************************************************************
	 * 
	 * Overridden Methods
	 * 
	 **************************************************************************/

	@Override
	public String toString() {
		return "Fixture [id=" + id + ", division=" + division + ", date=" + date + ", HomeTeam=" + HomeTeam
				+ ", AwayTeam=" + AwayTeam + ", B365H=" + B365H + ", B365D=" + B365D + ", B365A=" + B365A + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AwayTeam == null) ? 0 : AwayTeam.hashCode());
		result = prime * result + Float.floatToIntBits(B365A);
		result = prime * result + Float.floatToIntBits(B365D);
		result = prime * result + Float.floatToIntBits(B365H);
		result = prime * result + ((HomeTeam == null) ? 0 : HomeTeam.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fixture other = (Fixture) obj;
		if (AwayTeam == null) {
			if (other.AwayTeam != null)
				return false;
		} else if (!AwayTeam.equals(other.AwayTeam))
			return false;
		if (Float.floatToIntBits(B365A) != Float.floatToIntBits(other.B365A))
			return false;
		if (Float.floatToIntBits(B365D) != Float.floatToIntBits(other.B365D))
			return false;
		if (Float.floatToIntBits(B365H) != Float.floatToIntBits(other.B365H))
			return false;
		if (HomeTeam == null) {
			if (other.HomeTeam != null)
				return false;
		} else if (!HomeTeam.equals(other.HomeTeam))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
