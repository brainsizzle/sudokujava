package de.brainsizzle.sudokusolver.graphics;

import de.brainsizzle.sudokusolver.model.SingleField;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * renders one field and takes on the actions
 */
public class SingleFieldRenderer extends JComponent
{
	static int fontWidth = -1;
	static int fontHeight = -1;
	static Font largeFont = null;
	static Font smallFont = null;

	SingleField singleField;

	public SingleFieldRenderer(SingleField singleField)
	{
		this.singleField = singleField;
		setPreferredSize(new Dimension(10, 10));
		setMinimumSize(new Dimension(10, 10));
		setBorder(new LineBorder(Color.LIGHT_GRAY, 1 , false));

	}

	@Override
	protected void paintComponent(Graphics g)
	{
		System.out.print(".");

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2.setPaint(Color.BLACK);
		if (SingleField.ErrorState.WARNING == singleField.getErrorState())
		{
			g2.setPaint(Color.RED);
		}
		else if (SingleField.HighlightState.HIGHLIGHT == singleField.getHighlightState())
		{
			g2.setPaint(Color.BLUE);
		}

		if (fontHeight < 0)
		{
			initMetrics(g2);
		}
		if (singleField.isEmpty())
		{

			g2.setPaint(Color.GRAY);
			setFont(smallFont);

			boolean[] pots = singleField.getPotentialValues();
			for (int num = 1; num <= 9; num++)
			{
				if (pots[num])
				{

					int xstart = ((getWidth() - 10) - fontWidth / 3) / 2;
					xstart *= (num - 1) % 3;
					xstart += 5;

					int ystart = ((getHeight() - 10) - fontHeight /3)/2;
					ystart *= ((num -1) / 3) ;
					ystart += 5 + fontHeight / 3;
					g2.drawString("" + num, xstart, ystart);
				}
			}
		}
		else
		{
			setFont(largeFont);
			String val = singleField.getValue() == null ? "" : Integer.toString(singleField.getValue());
			int xstart = (getWidth() - fontWidth) / 2;
			int ystart = getHeight() / 2 + fontHeight / 2;
			g2.drawString(val, xstart, ystart);
		}
	}

	private void initMetrics(Graphics2D g2)
	{
		largeFont = new Font("SansSerif", Font.BOLD, 36);
		smallFont = new Font("SansSerif", Font.BOLD, 12);
		setFont(largeFont);
		FontMetrics metrics = g2.getFontMetrics(getFont());
		fontWidth = metrics.charWidth('0');
		fontHeight = metrics.getAscent() - metrics.getDescent() + 1;
	}

	public SingleField getSingleField()
	{
		return singleField;
	}
}
