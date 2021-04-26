package de.brainsizzle.sudokusolver.calculators;

import de.brainsizzle.sudokusolver.model.Puzzle;
import de.brainsizzle.sudokusolver.model.SingleField;

public class PotentialValuesCalculator
{
	public void calculate(Puzzle puzzle)
	{
		puzzle.setPotentialValuesToMax();

		for(int col = 0; col < 9; col++)
		{
			for (int row = 0; row < 9; row++)
			{
				SingleField singleField = puzzle.getField(col, row);
				if (!singleField.isEmpty())
				{
					singleField.resetPotentialValues();
					puzzle.removePotentialValueFromCol(singleField.getValue(), col);
					puzzle.removePotentialValueFromRow(singleField.getValue(), row);
					puzzle.removePotentialValueFromSquare(singleField.getValue(), col / 3, row / 3);

				}
			}
		}
	}
}
