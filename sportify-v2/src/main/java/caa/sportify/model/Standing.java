package caa.sportify.model;

import java.util.Comparator;

/**
 * @author Crispin A.
 *
 */
public class Standing {

	/**************************************************************************
	 * 
	 * Private Fields
	 * 
	 **************************************************************************/

	private int id;
	private String name;
	private int position;
	private int P;
	private int W;
	private int D;
	private int L;
	private int GF;
	private int GA;
	private int GD;
	private int points;
	private int pastPoints;
	private int movement;

	/**************************************************************************
	 * 
	 * Constructor
	 * 
	 **************************************************************************/

	public Standing() {

	}

	/***************************************************************************
	 * 
	 * Getter and Setter Methods
	 * 
	 **************************************************************************/

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}

	public int getP() {
		return P;
	}

	public int getW() {
		return W;
	}

	public int getD() {
		return D;
	}

	public int getL() {
		return L;
	}

	public int getGF() {
		return GF;
	}

	public int getGA() {
		return GA;
	}

	public int getGD() {
		return GD;
	}

	public int getPoints() {
		return points;
	}

	public int getPastPoints() {
		return pastPoints;
	}

	public int getMovement() {
		return movement;
	}

	/***************************************************************************
	 * 
	 * Other Methods
	 * 
	 **************************************************************************/

	/**
	 * 
	 * Returns a comparator function that orders Standing objects based on the
	 * points field as first priority and GD field as second priority. In descending
	 * order.
	 * 
	 * @return a comparator function that orders Standing objects based on the
	 *         points field and GD field.
	 */
	public static Comparator<Standing> compareP() {
		return Comparator.comparing((Standing standing) -> standing.getPoints())
				.thenComparingInt(standing -> standing.getGD()).reversed();
	}

	/**
	 * 
	 * Returns a comparator function that orders Standing objects based on the
	 * pastPoints field as first priority and GD field as second priority. In
	 * descending order.
	 * 
	 * @return a comparator function that orders Standing objects based on the
	 *         points field and GD field.
	 */
	public static Comparator<Standing> comparePP() {
		return Comparator.comparing((Standing standing) -> standing.getPastPoints())
				.thenComparingInt(standing -> standing.getGD()).reversed();
	}

	/**************************************************************************
	 * 
	 * Overridden Methods
	 * 
	 **************************************************************************/

	@Override
	public String toString() {
		return "Standing [id=" + id + ", name=" + name + ", position=" + position + ", P=" + P + ", W=" + W + ", D=" + D
				+ ", L=" + L + ", GF=" + GF + ", GA=" + GA + ", GD=" + GD + ", points=" + points + ", pastPoints="
				+ pastPoints + ", movement=" + movement + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + D;
		result = prime * result + GA;
		result = prime * result + GD;
		result = prime * result + GF;
		result = prime * result + L;
		result = prime * result + P;
		result = prime * result + W;
		result = prime * result + id;
		result = prime * result + movement;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + pastPoints;
		result = prime * result + points;
		result = prime * result + position;
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
		Standing other = (Standing) obj;
		if (D != other.D)
			return false;
		if (GA != other.GA)
			return false;
		if (GD != other.GD)
			return false;
		if (GF != other.GF)
			return false;
		if (L != other.L)
			return false;
		if (P != other.P)
			return false;
		if (W != other.W)
			return false;
		if (id != other.id)
			return false;
		if (movement != other.movement)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pastPoints != other.pastPoints)
			return false;
		if (points != other.points)
			return false;
		if (position != other.position)
			return false;
		return true;
	}

}
