package de.brainsizzle.sudokusolver.calculators;

import de.brainsizzle.sudokusolver.model.Puzzle;
import de.brainsizzle.sudokusolver.model.SingleField;

/**
 *  potential values need be calculated first
 */
public class SimpleHintCalculator
{

	/**
	 * @return true when any field was set
	 */
	public boolean calculate(Puzzle puzzle)
	{
		puzzle.resetHighlightStates();
		boolean fieldWasSet = false;
		fieldWasSet |= highlightOnlyValueInRow(puzzle);
		fieldWasSet |= highlightOnlyValueInCol(puzzle);
		fieldWasSet |= highlightOnlyValueInSquare(puzzle);
		fieldWasSet |= highlightOnlyPotentialValue(puzzle);
		return fieldWasSet;
	}

	/**
	 * @return true when field was set
	 */
	private boolean highlightOnlyPotentialValue(Puzzle puzzle)
	{
		boolean result = false;
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				SingleField singleField = puzzle.getField(col, row);
				if (singleField.isEmpty())
				{
					boolean[] b = singleField.getPotentialValues();
					int onlyPotNum = 0;
					int potnumCount = 0;
					for (int potnum = 1; potnum <= 9; potnum++)
					{
						if (b[potnum])
						{
							potnumCount++;
							onlyPotNum = potnum;
						}
					}
					if (potnumCount == 1)
					{
						singleField.setValue(onlyPotNum);
						singleField.setHighlightState(SingleField.HighlightState.HIGHLIGHT);
						result = true;
					}
				}
			}
		}
		return result;
	}

	private boolean highlightOnlyValueInRow(Puzzle puzzle)
	{
		boolean result = false;
		for (int row = 0; row < 9; row++)
		{
			int[] num = new int[10];
			for (int col = 0; col < 9; col++)
			{
				SingleField singleField = puzzle.getField(col, row);
				if (singleField.isEmpty())
				{
					boolean[] b = singleField.getPotentialValues();
					for (int potnum = 1; potnum <= 9; potnum++)
					{
						if (b[potnum])
						{
							num[potnum]++;
						}
					}
				}
				else
				{
					// not a possible solution
					num[singleField.getValue()] += 100;
				}
			}
			// any num containing 1 is a possible hit
			for (int number = 1; number <= 9; number++)
			{
				if (num[number] == 1)
				{
					for (int col = 0; col < 9; col++)
					{
						SingleField singleField = puzzle.getField(col, row);
						if (singleField.isEmpty() && singleField.hasPotentialValue(number))
						{
							singleField.setValue(number);
							singleField.setHighlightState(SingleField.HighlightState.HIGHLIGHT);
							result = true;
							break;
						}
					}
				}
			}
		}
		return result;
	}

	private boolean highlightOnlyValueInCol(Puzzle puzzle)
	{
		boolean result = false;
		for (int col = 0; col < 9; col++)
		{
			int[] num = new int[10];
			for (int row = 0; row < 9; row++)
			{
				SingleField singleField = puzzle.getField(col, row);
				if (singleField.isEmpty())
				{
					boolean[] b = singleField.getPotentialValues();
					for (int potnum = 1; potnum <= 9; potnum++)
					{
						if (b[potnum])
						{
							num[potnum]++;
						}
					}
				}
				else
				{
					// not a possible solution
					num[singleField.getValue()] += 100;
				}
			}
			// any num containing 1 is a possible hit
			for (int number = 1; number <= 9; number++)
			{
				if (num[number] == 1)
				{
					for (int row = 0; row < 9; row++)
					{
						SingleField singleField = puzzle.getField(col, row);
						if (singleField.isEmpty() && singleField.hasPotentialValue(number))
						{
							singleField.setValue(number);
							singleField.setHighlightState(SingleField.HighlightState.HIGHLIGHT);
							result = true;
							break;
						}
					}
				}
			}
		}
		return result;
	}

	private boolean highlightOnlyValueInSquare(Puzzle puzzle)
	{
		boolean result = false;
		for (int sx = 0; sx < 3; sx ++)
		{
			for (int sy = 0; sy < 3; sy ++)
			{
				int[] num = new int[10];
				for (int col = 0; col < 3; col++)
				{
					for (int row = 0; row < 3; row++)
					{
						SingleField singleField = puzzle.getField(col + sx*3, row+sy*3);
						if (singleField.isEmpty())
						{
							boolean[] b = singleField.getPotentialValues();
							for (int potnum = 1; potnum <= 9; potnum++)
							{
								if (b[potnum])
								{
									num[potnum]++;
								}
							}
						}
						else
						{
							// not a possible solution
							num[singleField.getValue()] += 100;
						}
					}
				}
				// any num containing 1 is a possible hit
				for (int number = 1; number <= 9; number++)
				{
					if (num[number] == 1)
					{
						for (int col = 0; col < 3; col++)
						{
							for (int row = 0; row < 3; row++)
							{
								SingleField singleField = puzzle.getField(col+sx*3, row+sy*3);
								if (singleField.isEmpty() && singleField.hasPotentialValue(number))
								{
									singleField.setValue(number);
									singleField.setHighlightState(SingleField.HighlightState.HIGHLIGHT);
									result = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		return result;
	}
}
