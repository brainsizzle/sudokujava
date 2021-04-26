package de.brainsizzle.sudokusolver.controller;

import de.brainsizzle.sudokusolver.SudokuSolver;
import de.brainsizzle.sudokusolver.graphics.SingleFieldRenderer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.event.EventListenerList;

public class FieldController implements MouseListener
{
	SudokuSolver sudokuSolver;

	protected EventListenerList listenerList = new EventListenerList();

	public FieldController(SudokuSolver sudokuSolver)
	{
		this.sudokuSolver = sudokuSolver;
	}

	public void addActionListener(ActionListener actionListener)
	{
		listenerList.add(ActionListener.class, actionListener);
	}

	public void removeActionListener(ActionListener l)
	{
		listenerList.remove(ActionListener.class, l);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		changeFieldValue(e);
		fireActionPerformed("fieldChanged");
	}

	protected void fireActionPerformed(String event) {
		// Guaranteed to return a non-null array
		Object[] listeners = listenerList.getListenerList();
		ActionEvent e = null;
		// Process the listeners last to first, notifying
		// those that are interested in this event
		for (int i = listeners.length-2; i>=0; i-=2) {
			if (listeners[i]==ActionListener.class) {
				// Lazily create the event:
				if (e == null) {

					e = new ActionEvent(this,
							ActionEvent.ACTION_PERFORMED,
							event,
							new Date().getTime(),
							0);
				}
				((ActionListener)listeners[i+1]).actionPerformed(e);
			}
		}
	}

	private void changeFieldValue(MouseEvent e)
	{
		SingleFieldRenderer renderer = (SingleFieldRenderer) e.getSource();
		renderer.getSingleField().incrementValue();
		sudokuSolver.repaint();
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
