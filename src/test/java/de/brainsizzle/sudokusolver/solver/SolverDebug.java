package de.brainsizzle.sudokusolver.solver;

import de.brainsizzle.sudokusolver.model.Puzzle;

import java.util.List;

public class SolverDebug
{
	public static void main(String[] args)
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		puzzle.initWithStringRepresentation("6--7-2-3-#---6--72-#-2---5689#5-2---91-#---9-48-2#-9--263-7#1-42--5--#286------#-5-4-12-8#");
		// field.initWithStringRepresentation("--1-9-45-#5-2----81#4--1-5--2#8---53---#-4-728--5#-53-61---#-7-5---16#--4-1-5--#--5--7-24#");


		Solver solver = new Solver();
		GlobalSolution globalSolution = solver.solve(puzzle);
		List<Puzzle> solutions = globalSolution.getSolutions();
		System.out.println("solutions#:" + solutions.size());
		for (Puzzle result : solutions)
		{
			System.out.println(result);
		}
		// Branch branch = solver.calculateNextBranch(field);

	}
}
