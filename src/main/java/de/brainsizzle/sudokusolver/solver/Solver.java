package de.brainsizzle.sudokusolver.solver;

import de.brainsizzle.sudokusolver.calculators.FieldValidator;
import de.brainsizzle.sudokusolver.calculators.PotentialValuesCalculator;
import de.brainsizzle.sudokusolver.calculators.SimpleHintCalculator;
import de.brainsizzle.sudokusolver.model.Puzzle;
import de.brainsizzle.sudokusolver.model.ResolutionState;
import de.brainsizzle.sudokusolver.model.SingleField;

import java.util.List;

/**
 * solving with brute force
 */
public class Solver
{

	public ResolutionState.Resolution followHints(Puzzle puzzle)
	{
		SimpleHintCalculator simpleHintCalculator = new SimpleHintCalculator();
		PotentialValuesCalculator potentialValuesCalculator = new PotentialValuesCalculator();
		boolean changedSomething = false;
		do
		{
			potentialValuesCalculator.calculate(puzzle);
			changedSomething = simpleHintCalculator.calculate(puzzle);
		}
		while (changedSomething);

		FieldValidator validator = new FieldValidator();
		return validator.validate(puzzle);
	}

	GuessLocation getNextEmtpyLocation(Puzzle puzzle)
	{
		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				if (puzzle.isEmpty(col, row))
				{
					return new GuessLocation(col, row);
				}
			}
		}
		return null;
	}

	public GlobalSolution solve(Puzzle puzzle)
	{
		// try with simple hint calculator
		followHints(puzzle);
		FieldValidator validator = new FieldValidator();


		ResolutionState.Resolution resolution = validator.validate(puzzle);
		GlobalSolution globalSolution = new GlobalSolution();
		switch (resolution)
		{
			case INVALID:
			case COMPLETE:
				break;
			case VALID:
				Branch branch = createBranch(puzzle);
				checkBranchSolutionRecursive(branch, globalSolution);
				break;
		}
		return globalSolution;
	}

	/**
	 * @param globalSolution is passed on to any child in order to know, when to quit
	 */
	void checkBranchSolutionRecursive(Branch branch, GlobalSolution globalSolution)
	{
		if (globalSolution.getSolutions().size() > 1) return;
		Branch.BranchState branchState = branch.calculateBranchState();
		while (Branch.BranchState.OPEN == branchState && globalSolution.getSolutions().size() < 2)
		{
			checkBranch(branch, globalSolution);
			Guess guess = branch.getFirstOpenGuess();

			if (guess != null)
			{
				// follow the branch

				if (guess.getChildBranch() != null)
				{
					System.out.println(guess.getChildBranch().puzzle);
					// guess state must be reflected
					checkBranchSolutionRecursive(guess.getChildBranch(), globalSolution);
				}
				else
				{
					checkGuess(branch, guess, globalSolution);
				}
				guess.recalcState();
				branchState = branch.calculateBranchState();

			}
			else
			{
				// branch.calculateBranchState();
				return;
			}
		}
	}



	Branch.BranchState checkBranch(Branch branch, GlobalSolution globalSolution)
	{
		for (Guess guess : branch.guesses)
		{
			checkGuess(branch, guess, globalSolution);
		}
		return branch.calculateBranchState();
	}

	private void checkGuess(Branch branch, Guess guess, GlobalSolution globalSolution)
	{
		if (guess.getState() == Guess.State.OPEN)
		{
			Puzzle testPuzzle = new Puzzle(branch.puzzle);
			SingleField singleField = testPuzzle.getField(branch.guessLocation.col, branch.guessLocation.row);
			singleField.setValue(guess.getValue());
			ResolutionState.Resolution resolution = followHints(testPuzzle);
			switch (resolution)
			{
				case COMPLETE:
					guess.setState( Guess.State.COMLETELY_CHECKED );
					globalSolution.getSolutions().add(testPuzzle);
					break;
				case VALID:
					guess.setState( Guess.State.OPEN );
					guess.setChildBranch(createBranch(testPuzzle));
					break;
				case INVALID:
					guess.setState( Guess.State.COMLETELY_CHECKED );
					break;
			}
		}
	}

	Branch createBranch( Puzzle puzzle)
	{
		PotentialValuesCalculator potentialValuesCalculator = new PotentialValuesCalculator();
		GuessLocation location = getNextEmtpyLocation(puzzle);
		potentialValuesCalculator.calculate(puzzle);
		SingleField singleField = puzzle.getField(location.col, location.row);
		// clone the field
		Branch branch = new Branch(new Puzzle(puzzle));
		branch.guessLocation = location;

		List<Integer> potentialValuesList = singleField.getPotentialValuesList();
		for (Integer potval : potentialValuesList)
		{
			branch.guesses.add( new Guess(potval));
		}
		return branch;
	}
}
