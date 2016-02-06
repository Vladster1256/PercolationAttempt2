package a01;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	private int size;
	private int gridsize;
	private int top;
	private int bottom;
	private boolean[][] grid1;
	private WeightedQuickUnionUF union;

	public Percolation(int N)
	{
		// create n­by­N grid, with all sites blocked
		size = N;

		gridsize = size * size;
		top = gridsize;
		bottom = gridsize + 1;
		union = new WeightedQuickUnionUF(gridsize + 2);
		// union.union(top, bottom);

		grid1 = new boolean[size][size];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				grid1[j][i] = false;
			}
		}

	}

	/**
	 * // open site (row i, column j) if it is not open already, validates that
	 * it is an actualy position and if it is blocked it opens it.
	 * 
	 * @param i
	 * @param j
	 */
	public void open(int row, int col)
	{ 
		try{
			validation(row,col);
		
			grid1[row][col] = true;
			if(row == 0 && isOpen(row,col))
			{
				union.union(calculate1DSpot(row,col), top);
			}
			if(row == size-1 && isOpen(row,col))
			{
				union.union(calculate1DSpot(row,col), bottom);
			}
			
			//check right neighbor
			if(col < size-1)
			{
				if(isOpen(row,col + 1))
				{
					union.union(calculate1DSpot(row,col), calculate1DSpot(row,col + 1));
				}
			}
			//check left neighbor
			if(col > 0)
			{
				if(isOpen(row,col - 1))
				{
					union.union(calculate1DSpot(row,col), calculate1DSpot(row,col - 1));
				}
			}
			//check up neighbor
			if(row > 0)
			{
				if(isOpen(row-1,col))
				{
				union.union(calculate1DSpot(row,col), calculate1DSpot(row-1,col));
				}
			}
			//check bottom
			if(row < size-1)
			{
				if(isOpen(row+1,col))
				{
					union.union(calculate1DSpot(row,col), calculate1DSpot(row+1,col));
				}
			}
		}catch(IndexOutOfBoundsException e)
		{
			System.out.println(e);
		}
		}

	/**
	 * // is site (row i, column j) open?
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int row, int col)
	{
		validation(row, col);
		return grid1[row][col];
	}

	/**
	 * // is site (row i, column j) full?
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int row, int col)
	{
		validation(row, col);
		if (isOpen(row, col))
		{
			return union.connected(calculate1DSpot(row, col), top);
		}
		return false;
	}

	/**
	 * // does the system percolate?
	 * 
	 * @return
	 */
	public boolean percolates()
	{ // got to check neighbors to see status of them, and then iterate from top
		// to bottom to see if they connect
		// May have to set top of grid [0][0] and then [Size][Size] to bottom to
		// see if they all connect. Or maybe you iterate through top row
		// and bottom row?

		if (union.connected(top, bottom))
		{
			return true;
		}

		return false;

	}

	/**
	 * This takes a 2d array and finds the 1d spot in the 2d array. Helper
	 * method for the Union find as union wont take a 2d array slot
	 * 
	 * @param x
	 *            is x cordinate
	 * @param y
	 *            is y cordinate
	 * @return return the 1d spot
	 */
	private int calculate1DSpot(int row, int col)
	{
		int col1 = col;
		int row1 = row;
		col1++;
		row1++;
		int returnable = (((size-1) * row1) - (size-1)) + col1;
		return returnable - 1;

	}

	private void validation(int row, int col)
	{
		if (row < 0 || row > size)
			throw new IndexOutOfBoundsException("row index " + row + " must be between 0 and " + (size));
		if (col < 0 || col > size)
			throw new IndexOutOfBoundsException("column index " + col + " must be between 0 and " + (size));
	}

}