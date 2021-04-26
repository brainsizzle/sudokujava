package de.brainsizzle.sudokusolver.model;

/**
 * container for resolution errorState
 */
public class ResolutionState
{
	public enum Resolution
	{
		INVALID,
		VALID,
		COMPLETE
	}

	Resolution resolution;

	public ResolutionState(Resolution resolution)
	{
		this.resolution = resolution;
	}

	public Resolution getResolution()
	{
		return resolution;
	}

	public void setResolution(Resolution resolution)
	{
		this.resolution = resolution;
	}
}
