package de.brainsizzle.sudokusolver.solver;

/**
 * possible globalSolution
 */
public class Guess
{
	/**
	 * checks whether all child branches are closed
	 */
	public void recalcState()
	{
		if (State.OPEN == state && childBranch != null)
		{
			switch (childBranch.branchState)
			{
				case COMLETELY_CHECKED:

					int complete = 0;

					for (Guess childGuess : childBranch.guesses)
					{

						switch (childGuess.getState())
						{
							case OPEN:
								break;
							case COMLETELY_CHECKED:
								complete++;
								break;
						}
					}
					if ( complete == childBranch.guesses.size())
					{
						state = State.COMLETELY_CHECKED;
					}
					break;
				case OPEN:
					break;
			}
		}
	}

	public enum State
	{
		OPEN,
		COMLETELY_CHECKED
	}

	private Branch childBranch = null;
	private State state = State.OPEN;
	private int value;

	Guess(int value)
	{
		this.value = value;
	}

	public Branch getChildBranch()
	{
		return childBranch;
	}

	public void setChildBranch(Branch childBranch)
	{
		this.childBranch = childBranch;
	}

	public State getState()
	{
		return state;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return "Guess{" +
				"state=" + state +
				", value=" + value +
				", childBranch=" + childBranch  +
				'}';
	}
}
