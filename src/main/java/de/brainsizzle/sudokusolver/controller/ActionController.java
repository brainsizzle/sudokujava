package de.brainsizzle.sudokusolver.controller;

import de.brainsizzle.sudokusolver.SudokuSolver;
import de.brainsizzle.sudokusolver.calculators.FieldValidator;
import de.brainsizzle.sudokusolver.calculators.PotentialValuesCalculator;
import de.brainsizzle.sudokusolver.calculators.SimpleHintCalculator;
import de.brainsizzle.sudokusolver.model.Puzzle;
import de.brainsizzle.sudokusolver.model.ResolutionState;
import de.brainsizzle.sudokusolver.solver.GlobalSolution;
import de.brainsizzle.sudokusolver.solver.Solver;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

public class ActionController implements ActionListener
{
	SudokuSolver sudokuSolver;

	public ActionController(SudokuSolver sudokuSolver)
	{
		this.sudokuSolver = sudokuSolver;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand() == null) return;
		if ("fieldChanged".equals(e.getActionCommand()) ||
				"check".equals(e.getActionCommand()))
		{
			validate();
		}
		else if ("export".equals(e.getActionCommand()))
		{
			export();
		}
		else if ("import".equals(e.getActionCommand()))
		{
			importString();
		}
		else if ("hint".equals(e.getActionCommand()))
		{
			SimpleHintCalculator simpleHintCalculator = new SimpleHintCalculator();
			simpleHintCalculator.calculate(sudokuSolver.getPuzzle());
			validate();
		}
		else if ("clear".equals(e.getActionCommand()))
		{
			sudokuSolver.getPuzzle().reset();
			validate();
		}
		else if ("solve".equals(e.getActionCommand()))
		{
			Solver solver = new Solver();
			GlobalSolution globalSolution = solver.solve(sudokuSolver.getPuzzle());
			List<Puzzle> solutions = globalSolution.getSolutions();
			if (solutions.size() != 1)
			{
				JOptionPane.showConfirmDialog(null, "number of solutions: " + solutions.size());
			}
			if (!solutions.isEmpty())
			{
				sudokuSolver.getPuzzle().initWithStringRepresentation(solutions.get(0).getStringRepresentation());
			}
			validate();
		}
	}

	private void importString()
	{
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		String clipString = null;
		try
		{

			Object clip = clipboard.getData(DataFlavor.stringFlavor);
			if (clip != null)
			{
				clipString = clip.toString();
			}
		}
		catch (UnsupportedFlavorException | IOException e1)
		{
			// ignore
		}
		String errorMsg = sudokuSolver.getPuzzle().initWithStringRepresentation(clipString);
		if (errorMsg != null)
		{
			sudokuSolver.showErrorPopup(errorMsg);
		}
		else
		{
			validate();
		}
	}

	private void export()
	{
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		StringSelection stringSelection = new StringSelection(sudokuSolver.getPuzzle().getStringRepresentation());
		clipboard.setContents(stringSelection, stringSelection);
	}

	private void validate()
	{
		FieldValidator fieldValidator = new FieldValidator();
		ResolutionState.Resolution resolution = fieldValidator.validate(sudokuSolver.getPuzzle());
		sudokuSolver.setResolutionState(resolution);
		PotentialValuesCalculator potentialValuesCalculator = new PotentialValuesCalculator();
		potentialValuesCalculator.calculate(sudokuSolver.getPuzzle());
		sudokuSolver.repaint();
	}
}
