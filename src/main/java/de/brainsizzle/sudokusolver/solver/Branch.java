package de.brainsizzle.sudokusolver.solver;

import de.brainsizzle.sudokusolver.model.Puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * branch for solving
 */
public class Branch
{
	Puzzle puzzle;
	GuessLocation guessLocation;
	List<Guess> guesses = new ArrayList<>();
	BranchState branchState = BranchState.OPEN;

	public enum BranchState
	{
		OPEN,
		COMLETELY_CHECKED
	}


	@Override
	public String toString()
	{
		return "Branch{" +
				" branchState=" + branchState +
				", field=" + puzzle +
				", guessLocation=" + guessLocation +
				", guesses=" + guesses +
				'}';
	}

	/**
	 * Overall state is stored in global solution
	 */
	public BranchState calculateBranchState()
	{
		branchState = BranchState.OPEN;
		for (Guess guess : guesses)
		{
			switch (guess.getState())
			{
				case OPEN:
					// any is open -> all is open
					return branchState;
				case COMLETELY_CHECKED:
					break;
			}
		}
		branchState = BranchState.COMLETELY_CHECKED;
		return branchState;
	}


	Branch(Puzzle puzzle)
	{
		this.puzzle = puzzle;
	}

	Guess getFirstOpenGuess()
	{
		for (Guess guess : guesses)
		{
			switch (guess.getState())
			{
				case OPEN:
					// recursive
					return guess;
				case COMLETELY_CHECKED:
					break;
			}
		}
		return null;
	}

}
