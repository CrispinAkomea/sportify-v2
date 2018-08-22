package caa.sportify.model;

/**
 * @author Crispin A.
 *
 */
public class Statistic {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private int id;
	private String Div;
	private String Date;
	private String HomeTeam;
	private String AwayTeam;
	private int FTHG;
	private int FTAG;
	private char FTR;
	private int HTHG;
	private int HTAG;
	private char HTR;
	private String Referee;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	public Statistic() {

	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public int getId() {
		return id;
	}

	public String getDiv() {
		return Div;
	}

	public String getDate() {
		return Date;
	}

	public String getHomeTeam() {
		return HomeTeam;
	}

	public String getAwayTeam() {
		return AwayTeam;
	}

	public int getFTHG() {
		return FTHG;
	}

	public int getFTAG() {
		return FTAG;
	}

	public char getFTR() {
		return FTR;
	}

	public int getHTHG() {
		return HTHG;
	}

	public int getHTAG() {
		return HTAG;
	}

	public char getHTR() {
		return HTR;
	}

	public String getReferee() {
		return Referee;
	}

	/**************************************************************************
	 * 
	 * Overridden Methods
	 * 
	 **************************************************************************/

	@Override
	public String toString() {
		return "Statistic [id=" + id + ", Div=" + Div + ", Date=" + Date + ", HomeTeam=" + HomeTeam + ", AwayTeam="
				+ AwayTeam + ", FTHG=" + FTHG + ", FTAG=" + FTAG + ", FTR=" + FTR + ", HTHG=" + HTHG + ", HTAG=" + HTAG
				+ ", HTR=" + HTR + ", Referee=" + Referee + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((AwayTeam == null) ? 0 : AwayTeam.hashCode());
		result = prime * result + ((Date == null) ? 0 : Date.hashCode());
		result = prime * result + ((Div == null) ? 0 : Div.hashCode());
		result = prime * result + FTAG;
		result = prime * result + FTHG;
		result = prime * result + FTR;
		result = prime * result + HTAG;
		result = prime * result + HTHG;
		result = prime * result + HTR;
		result = prime * result + ((HomeTeam == null) ? 0 : HomeTeam.hashCode());
		result = prime * result + ((Referee == null) ? 0 : Referee.hashCode());
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
		Statistic other = (Statistic) obj;
		if (AwayTeam == null) {
			if (other.AwayTeam != null)
				return false;
		} else if (!AwayTeam.equals(other.AwayTeam))
			return false;
		if (Date == null) {
			if (other.Date != null)
				return false;
		} else if (!Date.equals(other.Date))
			return false;
		if (Div == null) {
			if (other.Div != null)
				return false;
		} else if (!Div.equals(other.Div))
			return false;
		if (FTAG != other.FTAG)
			return false;
		if (FTHG != other.FTHG)
			return false;
		if (FTR != other.FTR)
			return false;
		if (HTAG != other.HTAG)
			return false;
		if (HTHG != other.HTHG)
			return false;
		if (HTR != other.HTR)
			return false;
		if (HomeTeam == null) {
			if (other.HomeTeam != null)
				return false;
		} else if (!HomeTeam.equals(other.HomeTeam))
			return false;
		if (Referee == null) {
			if (other.Referee != null)
				return false;
		} else if (!Referee.equals(other.Referee))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
