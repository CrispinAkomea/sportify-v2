package caa.sportify.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

/**
 * @author Crispin A.
 *
 */
public class Team {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private int id;
	private String division;
	private String name;
	private String alias;
	private String stadium;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	public Team() {

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

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getStadium() {
		return stadium;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Returns the Standing object of this Team.
	 * 
	 * @return Standing object of this Team
	 * @throws SQLException
	 */
	public Standing getStanding() throws SQLException {
		return ModelUtil.toModel((String) DB.table("Standings").where("name", this.name).first(), Standing.class);
	}

	/**
	 * 
	 * Returns a list of Statistic objects of this Team.
	 * 
	 * @return list of Statistic objects of this Team
	 * @throws SQLException
	 */
	public List<Statistic> getStatistics() throws SQLException {
		List<Statistic> list = new ArrayList<Statistic>();
		Statistic[] statistics = ModelUtil.toModels(
				(String) DB.table("Statistics").where("HomeTeam", this.alias).or("AwayTeam", this.alias).get(),
				Statistic[].class);
		for (Statistic statistic : statistics)
			list.add(statistic);
		return list;
	}

	/**************************************************************************
	 * 
	 * Overridden Methods
	 * 
	 **************************************************************************/

	@Override
	public String toString() {
		return "Team [id=" + id + ", division=" + division + ", name=" + name + ", alias=" + alias + ", stadium="
				+ stadium + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alias == null) ? 0 : alias.hashCode());
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((stadium == null) ? 0 : stadium.hashCode());
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
		Team other = (Team) obj;
		if (alias == null) {
			if (other.alias != null)
				return false;
		} else if (!alias.equals(other.alias))
			return false;
		if (division == null) {
			if (other.division != null)
				return false;
		} else if (!division.equals(other.division))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stadium == null) {
			if (other.stadium != null)
				return false;
		} else if (!stadium.equals(other.stadium))
			return false;
		return true;
	}

}
