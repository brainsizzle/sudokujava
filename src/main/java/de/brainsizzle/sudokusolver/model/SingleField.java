package de.brainsizzle.sudokusolver.model;

import java.util.ArrayList;
import java.util.List;

public class SingleField
{
	public void initWithCharRepresentation(char c)
	{
		value = null;
		errorState = ErrorState.NORMAL;
		if (c >= '1' && c <= '9')
		{
			value = Integer.parseInt("" + c);
		}
	}

	public boolean containsNumber(int number)
	{
		return value != null && value == number;
	}

	public boolean isEmpty()
	{
		return value == null;
	}

	public void resetPotentialValues()
	{
		potentialValues = new boolean[10];
	}

	public void setPotentialValuesToMax()
	{
		for (int i = 1; i <= 9; i++)
		{
			potentialValues[i] = true;
		}
	}

	public void removePotentialValue(Integer value)
	{
		if (value != null) potentialValues[value] = false;
	}

	public boolean hasPotentialValue(int number)
	{
		return potentialValues[number];
	}

	public void reset()
	{
		value = null;
		errorState = ErrorState.NORMAL;
		highlightState = HighlightState.NORMAL;
		resetPotentialValues();
	}

	public enum ErrorState
	{
		NORMAL,
		WARNING
	}

	public enum HighlightState
	{
		NORMAL,
		BACKGROUND,
		HIGHLIGHT
	}

	int col;
	int row;
	Integer value;
	ErrorState errorState = ErrorState.NORMAL;
	HighlightState highlightState = HighlightState.NORMAL;
	boolean[] potentialValues = new boolean[10];

	public SingleField(int col, int row)
	{
		this.col = col;
		this.row = row;
	}

	public Integer getValue()
	{
		return value;
	}

	public void setValue(Integer value)
	{
		this.value = value;
	}


	public void incrementValue()
	{
		if (value == null)
		{
			value = 1;
		}
		else
		{
			value++;
		}
		if (value > 9) value = null;

	}

	public ErrorState getErrorState()
	{
		return errorState;
	}

	public void setErrorState(ErrorState errorState)
	{
		this.errorState = errorState;
	}

	public String getStringRepresentation()
	{
		if (value == null) return "-";
		return Integer.toString(value);
	}

	public HighlightState getHighlightState()
	{
		return highlightState;
	}

	public void setHighlightState(HighlightState highlightState)
	{
		this.highlightState = highlightState;
	}

	public boolean[] getPotentialValues()
	{
		return potentialValues;
	}

	public List<Integer> getPotentialValuesList()
	{
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i < 10; i++)
		{
			if (potentialValues[i])
			{
				result.add(i);
			}
		}
		return result;
	}
}
