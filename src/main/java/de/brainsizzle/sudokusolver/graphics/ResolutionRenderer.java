package de.brainsizzle.sudokusolver.graphics;

import de.brainsizzle.sudokusolver.model.ResolutionState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.border.LineBorder;

public class ResolutionRenderer extends JComponent
{
	ResolutionState resolutionState;

	public ResolutionRenderer(ResolutionState resolutionState)
	{
		this.resolutionState = resolutionState;
		setPreferredSize(new Dimension(10, 10));
		setMinimumSize(new Dimension(10, 10));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1 , false));
		setFont(new Font("SansSerif", Font.BOLD, 36));
	}



	@Override
	protected void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		if (ResolutionState.Resolution.VALID == resolutionState.getResolution())
		{
			g2.setColor(Color.green);
		}
		else if (ResolutionState.Resolution.INVALID == resolutionState.getResolution())
		{
			g2.setColor(Color.yellow);
		}
		g2.fill(new Rectangle(0, 0, getWidth(), getHeight()));
	}

}
