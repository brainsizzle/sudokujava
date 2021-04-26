package de.brainsizzle.sudokusolver.solver;

import de.brainsizzle.sudokusolver.model.Puzzle;

import java.util.ArrayList;
import java.util.List;

/**
 * overall state for the whole resolver
 */
public class GlobalSolution
{

	List<Puzzle> solutions = new ArrayList<>();

	public GlobalSolution()
	{
	}

	public List<Puzzle> getSolutions()
	{
		return solutions;
	}

	public void setSolutions(List<Puzzle> solutions)
	{
		this.solutions = solutions;
	}

	@Override
	public String toString()
	{
		return "GlobalSolution{" +
				" solutions=" + solutions +
				'}';
	}
}
