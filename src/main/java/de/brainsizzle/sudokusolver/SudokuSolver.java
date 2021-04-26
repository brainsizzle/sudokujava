package de.brainsizzle.sudokusolver;

import de.brainsizzle.sudokusolver.controller.FieldController;
import de.brainsizzle.sudokusolver.graphics.ResolutionRenderer;
import de.brainsizzle.sudokusolver.graphics.SingleFieldRenderer;
import de.brainsizzle.sudokusolver.controller.ActionController;
import de.brainsizzle.sudokusolver.model.Puzzle;
import de.brainsizzle.sudokusolver.model.ResolutionState;
import de.brainsizzle.sudokusolver.model.SingleField;

import java.awt.*;

import javax.swing.*;

/**
 * sudokuhelper startup
 */
public class SudokuSolver
{
	FieldController fieldController;
	Puzzle puzzle;
	ActionController actionController;
	ResolutionState resolutionState;
	private JFrame frame;
	private JPanel sudokuPanel;
	private JPanel actionPanel;

	public SudokuSolver()
	{
	}

	public FieldController getFieldController()
	{
		return fieldController;
	}

	public Puzzle getPuzzle()
	{
		return puzzle;
	}

	public ActionController getActionController()
	{
		return actionController;
	}

	public ResolutionState getResolutionState()
	{
		return resolutionState;
	}

	public static void main(String[] args)
	{
		SudokuSolver sudokuSolver = new SudokuSolver();
		sudokuSolver.init();
	}

	private void init()
	{
		frame = new JFrame("Sudokusolver");
		frame.setSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		sudokuPanel = new JPanel(new GridLayout(9,9));
		puzzle = new Puzzle();
		puzzle.init();

		resolutionState = new ResolutionState(ResolutionState.Resolution.VALID);
		fieldController = new FieldController(this);

		for (int row = 0; row < 9; row++)
		{
			for (int col = 0; col < 9; col++)
			{
				SingleField singleField = puzzle.getField(col, row);
				SingleFieldRenderer singleFieldRenderer = new SingleFieldRenderer(singleField);
				singleFieldRenderer.addMouseListener(fieldController);
				sudokuPanel.add(singleFieldRenderer);
			}
		}
		frame.getContentPane().add(sudokuPanel, BorderLayout.CENTER);

		actionController = new ActionController(this);
		fieldController.addActionListener(actionController);
		actionPanel = new JPanel();
		actionPanel.setLayout(new GridLayout(9, 1));

		addHintButton();
		addExportButton();
		addImportButton();
		addClearButton();
		addSolveButton();

		ResolutionRenderer resolutionRenderer = new ResolutionRenderer(resolutionState);
		actionPanel.add(resolutionRenderer);

		frame.getContentPane().add(actionPanel, BorderLayout.EAST);
		frame.setVisible(true);
	}

	private void addExportButton()
	{
		JButton exportButton = new JButton("copy");
		exportButton.setActionCommand("export");
		exportButton.addActionListener(actionController);
		actionPanel.add(exportButton);
	}

	private void addImportButton()
	{
		JButton importButton = new JButton("paste");
		importButton.setActionCommand("import");
		importButton.addActionListener(actionController);
		actionPanel.add(importButton);
	}

	private void addHintButton()
	{
		JButton hintButton = new JButton("hint");
		hintButton.setActionCommand("hint");
		hintButton.addActionListener(actionController);
		actionPanel.add(hintButton);
	}

	private void addClearButton()
	{
		JButton clearButton = new JButton("clear");
		clearButton.setActionCommand("clear");
		clearButton.addActionListener(actionController);
		actionPanel.add(clearButton);
	}

	private void addSolveButton()
	{
		JButton clearButton = new JButton("solve");
		clearButton.setActionCommand("solve");
		clearButton.addActionListener(actionController);
		actionPanel.add(clearButton);
	}



	public void repaint()
	{
		sudokuPanel.repaint();
		actionPanel.repaint();
	}

	public void setResolutionState(ResolutionState.Resolution resolution)
	{
		if (resolution != null && resolution != resolutionState.getResolution())
		{
			resolutionState.setResolution(resolution);
			repaint();
		}
	}

	public void showErrorPopup(String errorMsg)
	{
		JOptionPane.showMessageDialog(frame,
				errorMsg,
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	// --7--89-4#-1-----26#--2--9---#---735-6-#-4-9---37#2-31-----#5---9-6--#734-----8#8-----57-#
	// --9-7-8-6#--------2#5--1-----#-3-98-167#---726--5#-5-4--289#---89----#---3-----#98-5---4-#
	// 8-9--7413#2------58#36-18--2-#-----93--#-4---82--#5--46--71#---5-----#----7-9-6#-2---4---#

	// --1-9-45-#5-2----81#4--1-5--2#8---53---#-4-728--5#-53-61---#-7-5---16#--4-1-5--#--5--7-24#
	// 2----7---#1---4-3-8#-5-8-----#6---795--#----1-6-7#7----32--#-3-7-----#----2-4-6#9----5---#
}
