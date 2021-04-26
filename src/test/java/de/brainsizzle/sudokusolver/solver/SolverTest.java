package de.brainsizzle.sudokusolver.solver;

import de.brainsizzle.sudokusolver.model.Puzzle;

import org.junit.Assert;
import org.junit.Test;

public class SolverTest
{
	@Test
	public void testGetNextEmtpyLocation()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();
		GuessLocation loc;

		puzzle.initWithStringRepresentation("--1-9-45-#5-2----81#4--1-5--2#8---53---#-4-728--5#-53-61---#-7-5---16#--4-1-5--#--5--7-24#");
		loc = solver.getNextEmtpyLocation(puzzle);
		Assert.assertEquals(0, loc.row);
		Assert.assertEquals(0, loc.col);

		puzzle.initWithStringRepresentation("123456789#542----81#4--1-5--2#8---53---#-4-728--5#-53-61---#-7-5---16#--4-1-5--#--5--7-24#");
		loc = solver.getNextEmtpyLocation(puzzle);
		Assert.assertEquals(1, loc.row);
		Assert.assertEquals(3, loc.col);
	}

	@org.junit.Test
	public void testCreateBranch()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("--1-9-45-#5-2----81#4--1-5--2#8---53---#-4-728--5#-53-61---#-7-5---16#--4-1-5--#--5--7-24#");
		Branch branch = solver.createBranch(puzzle);

		System.out.println(branch.toString());
		Assert.assertEquals(3, branch.guesses.size());
	}

	@org.junit.Test
	public void testFollowHints()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("--7--89-4#-1-----26#--2--9---#---73546-#6459---37#2731-----#52189764-#734----98#896---57-#");
		solver.followHints(puzzle);
		System.out.println(puzzle);
		Assert.assertEquals("357268914#918574326#462319785#189735462#645982137#273146859#521897643#734651298#896423571#", puzzle.getStringRepresentation());
	}

	@org.junit.Test
	public void testCheckBranch()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("--1-9-45-#5-2----81#4--1-5--2#8---53---#-4-728--5#-53-61---#-7-5---16#--4-1-5--#--5--7-24#");
		Branch branch = solver.createBranch(puzzle);
		GlobalSolution globalSolution = new GlobalSolution();
		solver.checkBranch(branch, globalSolution);

		System.out.println(branch);
		Assert.assertEquals(Guess.State.COMLETELY_CHECKED, branch.guesses.get(0).getState());
		Assert.assertEquals(1, globalSolution.getSolutions().size());
		Assert.assertEquals(Guess.State.COMLETELY_CHECKED, branch.guesses.get(1).getState());
		Assert.assertEquals(Guess.State.COMLETELY_CHECKED, branch.guesses.get(2).getState());
	}

	@org.junit.Test
	public void testCheckBranch2()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("---------#---------#---------#---------#---------#---------#---------#---------#---------#");
		Branch branch = solver.createBranch(puzzle);
		GlobalSolution globalSolution = new GlobalSolution();
		solver.checkBranch(branch, globalSolution);

		System.out.println(branch);

		Assert.assertEquals(9, branch.guesses.size());
	}

	@Test
	public void testSolve()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("---------#---------#---------#---------#---------#---------#---------#---------#---------#");

		GlobalSolution resolution = solver.solve(puzzle);

		System.out.println(resolution);
	}

	@Test
	public void testSolve2()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("123456789#4561-----#---------#---------#---------#---------#---------#---------#---------#");

		GlobalSolution resolution = solver.solve(puzzle);

		System.out.println(resolution);
	}

	@Test
	public void testSolve3()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("123456789#456789123#789123456#214365897#365897214#897214365#531642978#---------#---------#");

		GlobalSolution resolution = solver.solve(puzzle);

		System.out.println(resolution);
	}

	@Test
	public void testSolve4()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("123456789#456789123#789123456#214365897#365897214#897214365#531642978#67-93-54-#94-57-63-#");

		GlobalSolution resolution = solver.solve(puzzle);

		System.out.println(resolution);
	}

	@Test
	public void testSolve5()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("123456789#456789123#789123456#214365897#365897214#897214365#531642978#64-97-53-#97-53-64-#");

		GlobalSolution resolution = solver.solve(puzzle);

		System.out.println(resolution);
	}

	@Test
	public void testSolve6()
	{
		Puzzle puzzle = new Puzzle();
		puzzle.init();
		Solver solver = new Solver();

		puzzle.initWithStringRepresentation("123456789#456789123#789123456#214365897#365897214#897214365#531642978#6--9--5--#9--5--6--#");

		GlobalSolution resolution = solver.solve(puzzle);

		System.out.println(resolution);
	}
}