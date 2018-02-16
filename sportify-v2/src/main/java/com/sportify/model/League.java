package com.sportify.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vendor.database.DB;
import com.vendor.utility.ModelUtil;

/**
 * @author Crispin A.
 *
 */
public class League {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private int id;
	private String division;
	private String name;
	private Date season_start;
	private Date season_end;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	public League() {

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

	public Date getSeason_start() {
		return season_start;
	}

	public Date getSeason_end() {
		return season_end;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Returns a list of strings of teams that are in this League.
	 * 
	 * @return list of strings of teams that are in this League
	 * @throws SQLException
	 */
	public List<String> getTeamNames() throws SQLException {
		List<String> list = new ArrayList<String>();
		Team[] teams = ModelUtil.toModels((String) DB.table("Teams").where("division", this.division).get(),
				Team[].class);
		for (Team team : teams)
			list.add(team.getName());
		return list;
	}

	/**
	 * 
	 * Returns a list of Team objects of teams that are in this League.
	 * 
	 * @return list of Team objects of teams that are in this League
	 * @throws SQLException
	 */
	public List<Team> getTeams() throws SQLException {
		List<Team> list = new ArrayList<Team>();
		Team[] teams = ModelUtil.toModels((String) DB.table("Teams").where("division", this.division).get(),
				Team[].class);
		for (Team team : teams)
			list.add(team);
		return list;
	}

	/**
	 * 
	 * Returns a list of Standing objects of teams that are in this League.
	 * 
	 * @return list of Standing objects of teams that are in this League
	 * @throws SQLException
	 */
	public List<Standing> getStandings() throws SQLException {
		List<Standing> list = new ArrayList<Standing>();
		Standing[] standings = ModelUtil.toModels((String) DB.table("Standings").where("division", this.division).get(),
				Standing[].class);
		for (Standing standing : standings)
			list.add(standing);
		return list;
	}

	/**************************************************************************
	 * 
	 * Overridden Methods
	 * 
	 **************************************************************************/

	@Override
	public String toString() {
		return "League [id=" + id + ", division=" + division + ", name=" + name + ", season_start=" + season_start
				+ ", season_end=" + season_end + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((division == null) ? 0 : division.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((season_end == null) ? 0 : season_end.hashCode());
		result = prime * result + ((season_start == null) ? 0 : season_start.hashCode());
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
		League other = (League) obj;
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
		if (season_end == null) {
			if (other.season_end != null)
				return false;
		} else if (!season_end.equals(other.season_end))
			return false;
		if (season_start == null) {
			if (other.season_start != null)
				return false;
		} else if (!season_start.equals(other.season_start))
			return false;
		return true;
	}

}
