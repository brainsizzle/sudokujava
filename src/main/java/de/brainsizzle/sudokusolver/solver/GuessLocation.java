package de.brainsizzle.sudokusolver.solver;

/**
 * location for the guess
 */
public class GuessLocation
{
	int col;
	int row;

	GuessLocation(int col, int row)
	{
		this.col = col;
		this.row = row;
	}

	@Override
	public String toString()
	{
		return "GuessLocation{" +
				"col=" + col +
				", row=" + row +
				'}';
	}
}
