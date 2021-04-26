package de.brainsizzle.sudokusolver.model;

/**
 * whole field
 */
public class Puzzle
{
	SingleField[][] singleFields = new SingleField[9][9];

	public Puzzle()
	{
	}

	/**
	 * builds a new field from the given one
	 */
	public Puzzle(Puzzle puzzle)
	{
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				SingleField singleField = new SingleField(col, row);
				singleFields[col][row] = singleField;
				singleField.setValue(puzzle.getField(col, row).getValue());
			}
		}
	}

	public void init()
	{
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				SingleField singleField = new SingleField(col, row);
				singleFields[col][row] = singleField;
				singleField.setValue(null);
			}
		}
	}

	public SingleField getField(int col, int row)
	{
		return singleFields[col][row];
	}

	public void resetErrorStates()
	{
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				singleFields[col][row].setErrorState(SingleField.ErrorState.NORMAL);
			}
		}
	}

	public void setPotentialValuesToMax()
	{
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				singleFields[col][row].setPotentialValuesToMax();
			}
		}
	}

	public String getStringRepresentation()
	{
		StringBuilder result = new StringBuilder(82);
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				result.append(singleFields[col][row].getStringRepresentation());
			}
			result.append("#");
		}
		return result.toString();
	}

	public String initWithStringRepresentation(String in)
	{
		if (in == null) return "Empty clipboard";
		StringBuilder clean = new StringBuilder(81);
		for (char c : in.toCharArray())
		{
			if (c >= '1' && c <= '9')
			{
				clean.append(c);
			}
			else if (c == '.' || c == '-')
			{
				clean.append('-');
			}
		}
		if (clean.length() != 81) return "Invalid clipboard content - use export to get correct format!";

		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				singleFields[col][row].initWithCharRepresentation(clean.charAt(row * 9 + col));
			}
		}
		return null;

	}

	public void resetHighlightStates()
	{
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				singleFields[col][row].setHighlightState(SingleField.HighlightState.NORMAL);
			}
		}
	}

	public boolean containsNumberInCol(int number, int col)
	{
		for (int row = 0; row < 9; row++)
		{
			if (singleFields[col][row].containsNumber(number)) return true;
		}
		return false;
	}

	public boolean isEmpty(int col, int row)
	{
		return singleFields[col][row].isEmpty();
	}

	public boolean containsNumberInRow(int number, int row)
	{
		for (int col = 0; col < 9; col++)
		{
			if (singleFields[col][row].containsNumber(number)) return true;
		}
		return false;
	}

	public void removePotentialValueFromCol(Integer value, int col)
	{
		if (value == null) return;
		for (int row = 0; row < 9; row++)
		{
			singleFields[col][row].removePotentialValue(value);
		}
	}

	public void removePotentialValueFromRow(Integer value, int row)
	{
		if (value == null) return;
		for (int col = 0; col < 9; col++)
		{
			singleFields[col][row].removePotentialValue(value);
		}
	}

	public void removePotentialValueFromSquare(Integer value, int sx, int sy)
	{
		if (value == null) return;
		for (int col = 0; col < 3; col ++)
		{
			for (int row = 0; row < 3; row++)
			{
				singleFields[col + sx * 3][row + sy * 3].removePotentialValue(value);
			}
		}
	}

	public void reset()
	{
		for (int col = 0; col < 9; col ++)
		{
			for (int row = 0; row < 9; row++)
			{
				singleFields[col][row].reset();
			}
		}
	}

	@Override
	public String toString()
	{
		return "Field{" +
				getStringRepresentation() +
				'}';
	}
}
