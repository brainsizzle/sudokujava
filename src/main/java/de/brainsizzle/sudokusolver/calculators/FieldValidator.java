package de.brainsizzle.sudokusolver.calculators;

import de.brainsizzle.sudokusolver.model.Puzzle;
import de.brainsizzle.sudokusolver.model.ResolutionState;
import de.brainsizzle.sudokusolver.model.SingleField;

public class FieldValidator
{

	public FieldValidator()
	{
	}

	public ResolutionState.Resolution validate(Puzzle puzzle)
	{
		puzzle.resetErrorStates();
		boolean ok = validateRows(puzzle);
		ok &= validateCols(puzzle);
		ok &= validateSquares(puzzle);

		if (ok)
		{
			if (allComplete(puzzle))
			{
				return ResolutionState.Resolution.COMPLETE;
			}
			return ResolutionState.Resolution.VALID;
		}
		return ResolutionState.Resolution.INVALID;
	}

	private boolean allComplete(Puzzle puzzle)
	{
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				if (puzzle.getField(col, row).isEmpty())
				{
					return false;
				}
			}
		}
		return true;
	}

	private boolean validateRows(Puzzle puzzle)
	{
		boolean allok = true;
		for (int col = 0; col < 9; col++)
		{
			int[] count = new int[10];
			for (int row = 0; row < 9; row++)
			{
				SingleField sf = puzzle.getField(col, row);
				if (sf.getValue() != null)
				{
					count[sf.getValue()]++;
				}
			}
			for (int i = 1; i <= 9;  i++)
			{
				if (count[i] > 1)
				{
					allok = false;
					for (int row = 0; row < 9; row++)
					{
						SingleField sf = puzzle.getField(col, row);
						if (sf.getValue() != null && sf.getValue() == i)
						{
							sf.setErrorState(SingleField.ErrorState.WARNING);
						}
					}
				}
			}
		}
		return allok;
	}

	private boolean validateCols(Puzzle puzzle)
	{
		boolean allok = true;
		for (int row = 0; row < 9; row++)
		{
			int[] count = new int[10];
			for (int col = 0; col < 9; col++)
			{
				SingleField sf = puzzle.getField(col, row);
				if (sf.getValue() != null)
				{
					count[sf.getValue()]++;
				}
			}
			for (int i = 1; i <= 9;  i++)
			{
				if (count[i] > 1)
				{
					allok = false;
					for (int col = 0; col < 9; col++)
					{
						SingleField sf = puzzle.getField(col, row);
						if (sf.getValue() != null && sf.getValue() == i)
						{
							sf.setErrorState(SingleField.ErrorState.WARNING);
						}
					}
				}
			}
		}
		return allok;
	}

	private boolean validateSquares(Puzzle puzzle)
	{
		boolean allOk = true;
		for (int sx = 0; sx < 3; sx++)
		{
			for (int sy = 0; sy < 3; sy++)
			{
				int[] count = new int[10];
				for (int col = 0; col < 3; col++)
				{
					for (int row = 0; row < 3; row++)
					{
						SingleField sf = puzzle.getField(sx * 3 + col, sy * 3 + row);
						if (sf.getValue() != null)
						{
							count[sf.getValue()]++;
						}
					}
				}
				for (int i = 1; i <= 9;  i++)
				{
					if (count[i] > 1)
					{
						allOk = false;
						for (int col = 0; col < 3; col++)
						{
							for (int row = 0; row < 3; row++)
							{
								SingleField sf = puzzle.getField(sx * 3 + col, sy * 3 + row);
								if (sf.getValue() != null && sf.getValue() == i)
								{
									sf.setErrorState(SingleField.ErrorState.WARNING);
								}
							}
						}
					}
				}
			}
		}
		return allOk;
	}
}
