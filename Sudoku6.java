package Sudoku;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
 * input14 is interesting problem. implement your new algorithm and if 14 and 14 they can only go there cancel the rest.
 */
public class Sudoku6
{
	static int dimension = 9;
	
	static class Square
	{
		char value = ' ';
		int posX = 0;
		int posY = 0;
		
		Box box = new Box();
		LineHori lineHori = new LineHori();
		LineVert lineVert = new LineVert();
	}
	
	static class Box
	{
		ArrayList<Square> squares = new ArrayList<Square>();
		HashMap<Integer, ArrayList<Integer>> sqr2int = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> int2sqr = new HashMap<Integer, ArrayList<Integer>>();
	
		void add(Square sqr)
		{
			squares.add(sqr);
			
			ArrayList<Integer> availInts = new ArrayList<Integer>();
			for (int i = 1; i < dimension+1; i++) 
			{
				availInts.add(i);
				
				if (int2sqr.containsKey(i)) int2sqr.get(i).add(sqr.posX*10+sqr.posY);
				else 
				{
					ArrayList<Integer> availSqrs = new ArrayList<Integer>();
					availSqrs.add(sqr.posX*10+sqr.posY);
					int2sqr.put(i, availSqrs);
				}
			}
			sqr2int.put(sqr.posX*10 + sqr.posY, availInts);
		}
	}
	//DONT FORGET ABOUT LINES
	static class LineHori
	{
		ArrayList<Square> squares = new ArrayList<Square>();
		HashMap<Integer, ArrayList<Integer>> sqr2int = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> int2sqr = new HashMap<Integer, ArrayList<Integer>>();
		
		void add(Square sqr)
		{
			squares.add(sqr);
			
			ArrayList<Integer> availInts = new ArrayList<Integer>();
			for (int i = 1; i < dimension+1; i++) 
			{
				availInts.add(i);
				
				if (int2sqr.containsKey(i)) int2sqr.get(i).add(sqr.posX*10+sqr.posY);
				else 
				{
					ArrayList<Integer> availSqrs = new ArrayList<Integer>();
					availSqrs.add(sqr.posX*10+sqr.posY);
					int2sqr.put(i, availSqrs);
				}
			}
			sqr2int.put(sqr.posX*10 + sqr.posY, availInts);
		}
	}
	
	static class LineVert
	{
		ArrayList<Square> squares = new ArrayList<Square>();
		HashMap<Integer, ArrayList<Integer>> sqr2int = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> int2sqr = new HashMap<Integer, ArrayList<Integer>>();
		
		void add(Square sqr)
		{
			squares.add(sqr);
			
			ArrayList<Integer> availInts = new ArrayList<Integer>();
			for (int i = 1; i < dimension+1; i++) 
			{
				availInts.add(i);
				
				if (int2sqr.containsKey(i)) int2sqr.get(i).add(sqr.posX*10+sqr.posY);
				else 
				{
					ArrayList<Integer> availSqrs = new ArrayList<Integer>();
					availSqrs.add(sqr.posX*10+sqr.posY);
					int2sqr.put(i, availSqrs);
				}
			}
			sqr2int.put(sqr.posX*10 + sqr.posY, availInts);
		}
	}
	
	static class Grid
	{
		ArrayList<ArrayList<Square>> grid = new ArrayList<ArrayList<Square>>();
		
		ArrayList<Box> boxs = new ArrayList<Box>();
		ArrayList<LineHori> lineHoris = new ArrayList<LineHori>();
		ArrayList<LineVert> lineVerts = new ArrayList<LineVert>();
		
		void init()
		{
			grid.add(null);
			boxs.add(null);
			lineHoris.add(null);
			lineVerts.add(null);
			
			for (int i = 1; i < dimension+1; i++)
			{
				Box box = new Box();
				LineHori lineHori = new LineHori();
				LineVert lineVert = new LineVert();
				ArrayList<Square> sectGrid = new ArrayList<Square>();
				
				box.squares.add(null);
				lineHori.squares.add(null);
				lineVert.squares.add(null);
				sectGrid.add(null);
				
				boxs.add(box); 
				lineHoris.add(lineHori);
				lineVerts.add(lineVert);
				grid.add(sectGrid);
			}
		}
		
		Grid(String[] input)
      {
			init();
			
			for (int x = 1; x < dimension+1; x++)
			{
				for (int y = 1; y < dimension+1; y++)
				{
					Square sqr = new Square();
					sqr.value = input[x].charAt(y);
					sqr.posX = x;
					sqr.posY = y;
					
					if (y <= 3)      
					{
						boxs.get(x*3-2 - 3*(x-1) + 3*(x/4) + 3*(x/7) - 3*(x/8)).add(sqr);//(y    + 3*(x%4-1) + 3*(x/4) - 9*(x/7) + 9*(x/8)), sqr);
						sqr.box = boxs.get(x*3-2 - 3*(x-1) + 3*(x/4) + 3*(x/7) - 3*(x/8));
					}
            	else if (y <= 6) 
            	{
            		boxs.get(x*3-1 - 3*(x-1) + 3*(x/4) + 3*(x/7) - 3*(x/8)).add(sqr);//(y -3 + 3*(x%4-1) + 3*(x/4) - 9*(x/7) + 9*(x/8)), sqr);
            		sqr.box = boxs.get(x*3-1 - 3*(x-1) + 3*(x/4) + 3*(x/7) - 3*(x/8));
            	}
            	else if (y <= 9) 
            	{
            		boxs.get(x*3   - 3*(x-1) + 3*(x/4) + 3*(x/7) - 3*(x/8)).add(sqr);//(y -6 + 3*(x%4-1) + 3*(x/4) - 9*(x/7) + 9*(x/8)), sqr);																				 
            		sqr.box = boxs.get(x*3   - 3*(x-1) + 3*(x/4) + 3*(x/7) - 3*(x/8));
            	}
					lineHoris.get(x).add(sqr);
					sqr.lineHori = lineHoris.get(x);
					lineVerts.get(y).add(sqr);
					sqr.lineVert = lineVerts.get(y);
					
					grid.get(x).add(sqr);
				}
			}
      }
		
		void execute()
		{
			printGrid();
			for (int x = 1; x < dimension+1; x++)
			{
				for (int y = 1; y < dimension+1; y++)
				{
					if (!(grid.get(x).get(y).value == ' ')) 
					{
						solveCase(grid.get(x).get(y), Character.getNumericValue(grid.get(x).get(y).value));
					}
				}
			}
		}
		
		void execute2()
		{
			printGrid();
			for (int x = 1; x < dimension+1; x++)
			{
				for (int y = 1; y < dimension+1; y++)
				{
					if (grid.get(x).get(y).value == ' ')
					{
						for (int testNum : grid.get(x).get(y).box.int2sqr.keySet())
						{
							if (grid.get(x).get(y).box.int2sqr.get((Object)testNum).size() == 2 ||
								(grid.get(x).get(y).lineHori.int2sqr.get((Object)testNum)!=null&&grid.get(x).get(y).lineHori.int2sqr.get((Object)testNum).size() == 2) ||
								(grid.get(x).get(y).lineVert.int2sqr.get((Object)testNum)!=null&&grid.get(x).get(y).lineVert.int2sqr.get((Object)testNum).size() == 2))
							{
								ArrayList<Integer> box = grid.get(x).get(y).box.int2sqr.get((Object)testNum);
								ArrayList<Integer> lineHori = grid.get(x).get(y).lineHori.int2sqr.get((Object)testNum);
								ArrayList<Integer> lineVert = grid.get(x).get(y).lineVert.int2sqr.get((Object)testNum);
								
								if (lineHori != null && (box.containsAll(lineHori) || lineHori.containsAll(box) || grid.get(x).get(y).lineHori.sqr2int.get((Object)lineHori.get(0)).containsAll(grid.get(x).get(y).lineHori.sqr2int.get((Object)lineHori.get(1)))))
								{
									newAlgoHori(x*10+y, testNum);
								}
								if (lineVert != null && (box.containsAll(lineVert) || lineVert.containsAll(box) || grid.get(x).get(y).lineVert.sqr2int.get((Object)lineVert.get(0)).containsAll(grid.get(x).get(y).lineVert.sqr2int.get((Object)lineVert.get(1)))))
								{
									newAlgoVert(x*10+y, testNum);
								}
							}
						}
						checkSolve(grid.get(x).get(y));
					}
				}
			}
		}
		
		void removeSqr(Square sqr)
		{
			sqr.box.sqr2int.remove((Object)(sqr.posX*10+sqr.posY));
			sqr.lineHori.sqr2int.remove((Object)(sqr.posX*10+sqr.posY));
			sqr.lineVert.sqr2int.remove((Object)(sqr.posX*10+sqr.posY));
		}
		
		void removeInt(Square sqr, int num)
		{
			sqr.box.int2sqr.remove((Object)num);
			sqr.lineHori.int2sqr.remove((Object)num);
			sqr.lineVert.int2sqr.remove((Object)num);
		}
		
		void removeCoorInt(Square sqr)
		{
			for (int intInBox : sqr.box.int2sqr.keySet()) sqr.box.int2sqr.get(intInBox).remove((Object)(sqr.posX*10+sqr.posY));
			for (int intInLineHori : sqr.lineHori.int2sqr.keySet()) sqr.lineHori.int2sqr.get(intInLineHori).remove((Object)(sqr.posX*10+sqr.posY));
			for (int intInLineVert : sqr.lineVert.int2sqr.keySet()) sqr.lineVert.int2sqr.get(intInLineVert).remove((Object)(sqr.posX*10+sqr.posY));
		}
		
		void removeCoorSqr(Square sqr, int num)
		{
			for (int sqrInBox : sqr.box.sqr2int.keySet())
			{
				grid.get(sqrInBox/10).get(sqrInBox%10).box.sqr2int.get((Object)sqrInBox).remove((Object)num);
				grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.sqr2int.get((Object)sqrInBox).remove((Object)num);
				grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.sqr2int.get((Object)sqrInBox).remove((Object)num);
				
				if (grid.get(sqrInBox/10).get(sqrInBox%10).box.int2sqr.containsKey((Object)num)) grid.get(sqrInBox/10).get(sqrInBox%10).box.int2sqr.get((Object)num).remove((Object)sqrInBox);
				if (grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.int2sqr.containsKey((Object)num)) grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.int2sqr.get((Object)num).remove((Object)sqrInBox);
				if (grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.int2sqr.containsKey((Object)num)) grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.int2sqr.get((Object)num).remove((Object)sqrInBox);
			}
			
			for (int sqrInLineHori : sqr.lineHori.sqr2int.keySet())
			{
				grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.sqr2int.get((Object)sqrInLineHori).remove((Object)num);
				grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.sqr2int.get((Object)sqrInLineHori).remove((Object)num);
				grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.sqr2int.get((Object)sqrInLineHori).remove((Object)num);
			
				if (grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.int2sqr.containsKey((Object)num)) grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.int2sqr.get((Object)num).remove((Object)sqrInLineHori);
				if (grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.int2sqr.containsKey((Object)num)) grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.int2sqr.get((Object)num).remove((Object)sqrInLineHori);
				if (grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.int2sqr.containsKey((Object)num)) grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.int2sqr.get((Object)num).remove((Object)sqrInLineHori);
			}
			
			for (int sqrInLineVert : sqr.lineVert.sqr2int.keySet())
			{
				grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.sqr2int.get((Object)sqrInLineVert).remove((Object)num);
				grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.sqr2int.get((Object)sqrInLineVert).remove((Object)num);
				grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.sqr2int.get((Object)sqrInLineVert).remove((Object)num);
			
				if (grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.int2sqr.containsKey((Object)num)) grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.int2sqr.get((Object)num).remove((Object)sqrInLineVert);
				if (grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.int2sqr.containsKey((Object)num)) grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.int2sqr.get((Object)num).remove((Object)sqrInLineVert);
				if (grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.int2sqr.containsKey((Object)num)) grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.int2sqr.get((Object)num).remove((Object)sqrInLineVert);
			}
		}
		
		void newAlgoHori(int sqr, int num)
		{
			ArrayList<Square> boxAll = (ArrayList<Square>) grid.get(sqr/10).get(sqr%10).box.squares.clone();
			ArrayList<Integer> box = new ArrayList<Integer>();
			for (Square b : boxAll) {if (b != null && b.value == ' ') {box.add(b.posX*10+b.posY);}}
			ArrayList<Integer> backup = (ArrayList<Integer>) box.clone();
			ArrayList<Integer> lineHori = (ArrayList<Integer>) grid.get(sqr/10).get(sqr%10).lineHori.int2sqr.get((Object)num).clone();
			boolean algo3 = false; if (!lineHori.containsAll(box)) algo3 = true;
			box.retainAll(lineHori);
			if (box.size() == 0) return;
			ArrayList<Integer> intersect = (ArrayList<Integer>) box.clone();
			if (algo3) intersect = (ArrayList<Integer>) lineHori.clone();
			ArrayList<Integer> intersectInt = ((ArrayList<Integer>)grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(0)).clone());
			if (algo3) intersectInt = (ArrayList<Integer>) grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineVert.sqr2int.get(intersect.get(0)).clone();
			if (!algo3)intersectInt.retainAll(((ArrayList<Integer>)grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(1)).clone()));
			for (int i = 0; i < intersectInt.size(); i++) {if (!(grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineHori.int2sqr.get(intersectInt.get(i)).size()==2)){intersectInt.remove(intersectInt.get(i)); i--;}}
			box = backup;
			
			if (algo2)
			{
			if (intersectInt.size() == 2)//grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(0)).containsAll(grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.sqr2int.get(intersect.get(1))))
			{
				grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(0)).retainAll(intersectInt);
				grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.sqr2int.get(intersect.get(1)).retainAll(intersectInt);
				grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineHori.sqr2int.get(intersect.get(0)).retainAll(intersectInt);
				grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineHori.sqr2int.get(intersect.get(1)).retainAll(intersectInt);
				
				for (int i : grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.int2sqr.keySet())
				{
					if (!(intersectInt.contains(i)))
					{
						grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.int2sqr.get((Object)i).removeAll(intersect);
					}
				}
				for (int i : grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.int2sqr.keySet())
				{
					if (!(intersectInt.contains(i)))
					{
						grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.int2sqr.get((Object)i).removeAll(intersect);
					}
				}
				for (int i : grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineHori.int2sqr.keySet())
				{
					if (!(intersectInt.contains(i)))
					{
						grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineHori.int2sqr.get((Object)i).removeAll(intersect);
					}
				}
			}
			}
			
			for (int intLH : lineHori)
			{
				Square revLineHori = grid.get(intLH/10).get(intLH%10);
				if (!intersect.contains(intLH))
				{
					revLineHori.lineHori.int2sqr.get((Object)num).remove((Object)revLineHori);
					revLineHori.lineVert.int2sqr.get(num).remove((Object)revLineHori);
					revLineHori.box.int2sqr.get((Object)num).remove((Object)revLineHori);
					
					revLineHori.lineHori.sqr2int.get((Object)intLH).remove((Object)num);
					revLineHori.lineHori.sqr2int.get((Object)intLH).remove((Object)num);
					revLineHori.box.sqr2int.get((Object)intLH).remove((Object)num);
				}
			}
			
			box = backup;
			for (int intB : box)
			{
				Square revBox = grid.get(intB/10).get(intB%10);
				if (!intersect.contains(intB) && revBox.box.int2sqr.get((Object)num).contains((Object)intB))
				{
					revBox.box.int2sqr.get((Object)num).remove((Object)intB);
					revBox.lineHori.int2sqr.get((Object)num).remove((Object)intB);
					revBox.lineVert.int2sqr.get((Object)num).remove((Object)intB);
					
					revBox.box.sqr2int.get((Object)intB).remove((Object)num);
					revBox.lineHori.sqr2int.get((Object)intB).remove((Object)num);
					revBox.lineVert.sqr2int.get((Object)intB).remove((Object)num);
				}
			}
			
			ArrayList<Square> boxAll2 = (ArrayList<Square>) grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.squares.clone();
			ArrayList<Integer> box2 = new ArrayList<Integer>();
			for (Square b : boxAll2) {if (b != null && b.value == ' ') {box2.add(b.posX*10+b.posY);}}
			for (int intB : box2)
			{
				Square revBox = grid.get(intB/10).get(intB%10);
				if (!intersect.contains(intB) && revBox.box.int2sqr.get((Object)num).contains((Object)intB))
				{
					revBox.box.int2sqr.get((Object)num).remove((Object)intB);
					revBox.lineHori.int2sqr.get((Object)num).remove((Object)intB);
					revBox.lineVert.int2sqr.get((Object)num).remove((Object)intB);
					
					revBox.box.sqr2int.get((Object)intB).remove((Object)num);
					revBox.lineHori.sqr2int.get((Object)intB).remove((Object)num);
					revBox.lineVert.sqr2int.get((Object)intB).remove((Object)num);
				}
			}
		}
		
		void newAlgoVert(int sqr, int num)
		{
			ArrayList<Square> boxAll = (ArrayList<Square>) grid.get(sqr/10).get(sqr%10).box.squares.clone();
			ArrayList<Integer> box = new ArrayList<Integer>();
			for (Square b : boxAll) {if (b != null && b.value == ' ') {box.add(b.posX*10+b.posY);}}
			ArrayList<Integer> backup = (ArrayList<Integer>) box.clone();
			ArrayList<Integer> lineVert = (ArrayList<Integer>) grid.get(sqr/10).get(sqr%10).lineVert.int2sqr.get((Object)num).clone();
			boolean algo3 = false; if (!lineVert.containsAll(box) && grid.get(sqr/10).get(sqr%10).lineVert.sqr2int.get((Object)lineVert.get(0)).containsAll(grid.get(sqr/10).get(sqr%10).lineVert.sqr2int.get((Object)lineVert.get(1)))) algo3 = true;
			//^fix this
			box.retainAll(lineVert);
			if (box.size() == 0) return;
			ArrayList<Integer> intersect = (ArrayList<Integer>) box.clone();
			if (algo3) intersect = (ArrayList<Integer>) lineVert.clone();
			ArrayList<Integer> intersectInt = ((ArrayList<Integer>)grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(0)).clone());
			if (algo3) intersectInt = (ArrayList<Integer>) grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineVert.sqr2int.get(intersect.get(0)).clone();
			if (!algo3)intersectInt.retainAll(((ArrayList<Integer>)grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(1)).clone()));
			for (int i = 0; i < intersectInt.size(); i++) {if (!(grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineVert.int2sqr.get(intersectInt.get(i)).size()==2)) {intersectInt.remove(intersectInt.get(i)); i--;}}
			box = backup;
			
			if (algo2)
			{
			if (intersectInt.size() == 2)//grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(0)).containsAll(grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.sqr2int.get(intersect.get(1))))
			{
				grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.sqr2int.get(intersect.get(0)).retainAll(intersectInt);
				grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.sqr2int.get(intersect.get(1)).retainAll(intersectInt);
				grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineVert.sqr2int.get(intersect.get(0)).retainAll(intersectInt);
				grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineVert.sqr2int.get(intersect.get(1)).retainAll(intersectInt);
				
				for (int i : grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.int2sqr.keySet())
				{
					if (!(intersectInt.contains(i)))
					{
						grid.get(intersect.get(0)/10).get(intersect.get(0)%10).box.int2sqr.get((Object)i).removeAll(intersect);
					}
				}
				for (int i : grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.int2sqr.keySet())
				{
					if (!(intersectInt.contains(i)))
					{
						grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.int2sqr.get((Object)i).removeAll(intersect);
					}
				}
				for (int i : grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineVert.int2sqr.keySet())
				{
					if (!(intersectInt.contains(i)))
					{
						grid.get(intersect.get(0)/10).get(intersect.get(0)%10).lineVert.int2sqr.get((Object)i).removeAll(intersect);
					}
				}
			}
			}
				
			for (int intLV : lineVert)
			{
				Square revLineVert = grid.get(intLV/10).get(intLV%10);
				if (!intersect.contains(intLV))
				{
					revLineVert.lineHori.int2sqr.get((Object)num).remove((Object)revLineVert);
					revLineVert.lineVert.int2sqr.get((Object)num).remove((Object)revLineVert);
					revLineVert.box.int2sqr.get((Object)num).remove((Object)revLineVert);
					
					revLineVert.lineHori.sqr2int.get((Object)intLV).remove((Object)num);
					revLineVert.lineVert.sqr2int.get((Object)intLV).remove((Object)num);
					revLineVert.box.sqr2int.get((Object)intLV).remove((Object)num);
				}
			}
			
			box = backup;
			for (int intB : box)
			{
				Square revBox = grid.get(intB/10).get(intB%10);
				if (!intersect.contains(intB) && revBox.box.int2sqr.get((Object)num).contains((Object)intB))
				{
					if (!algo3)
					{
					revBox.box.int2sqr.get((Object)num).remove((Object)intB);
					revBox.lineHori.int2sqr.get((Object)num).remove((Object)intB);
					revBox.lineVert.int2sqr.get((Object)num).remove((Object)intB);
					}
					
					revBox.box.sqr2int.get((Object)intB).remove((Object)num);
					revBox.lineHori.sqr2int.get((Object)intB).remove((Object)num);
					revBox.lineVert.sqr2int.get((Object)intB).remove((Object)num);
				}
			}
			
			if (algo3)
			{
			int num2 = intersectInt.get(1);
			ArrayList<Square> boxAll2 = (ArrayList<Square>) grid.get(intersect.get(1)/10).get(intersect.get(1)%10).box.squares.clone();
			ArrayList<Integer> box2 = new ArrayList<Integer>();
			for (Square b : boxAll2) {if (b != null && b.value == ' ') {box2.add(b.posX*10+b.posY);}}
			for (int intB : box2)
			{
				Square revBox = grid.get(intB/10).get(intB%10);
				if (!intersect.contains(intB) && revBox.box.int2sqr.get((Object)num2).contains((Object)intB))
				{	
					revBox.box.sqr2int.get((Object)intB).remove((Object)num2);
					revBox.lineHori.sqr2int.get((Object)intB).remove((Object)num2);
					revBox.lineVert.sqr2int.get((Object)intB).remove((Object)num2);
				}
			}
			}
		}
		
		void solveCase(Square sqr, int num)
		{
			removeSqr(sqr);
			
			removeInt(sqr, num);
			
			removeCoorInt(sqr);
			
			removeCoorSqr(sqr, num);
			
			checkSolve(sqr);
		}
		
		void checkSolve(Square sqr)
		{
			ArrayList<Square> toSolve = new ArrayList<Square>();
			ArrayList<Square> toSolve1 = new ArrayList<Square>();
			ArrayList<Integer> toSolveNum = new ArrayList<Integer>();
			
			for (int sqrInBox : sqr.box.sqr2int.keySet())
			{
				if (grid.get(sqrInBox/10).get(sqrInBox%10).box.sqr2int.get((Object)sqrInBox).size() == 1 ||
					 grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.sqr2int.get((Object)sqrInBox).size() == 1 ||
					 grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.sqr2int.get((Object)sqrInBox).size() == 1)  
				{if (!toSolve.contains((Object)grid.get(sqrInBox/10).get(sqrInBox%10))) toSolve.add(grid.get(sqrInBox/10).get(sqrInBox%10));}
				
				for (int testNum: grid.get(sqrInBox/10).get(sqrInBox%10).box.int2sqr.keySet())
				{
					if (grid.get(sqrInBox/10).get(sqrInBox%10).box.int2sqr.get((Object)testNum).size() == 1)
					{if (!toSolve1.contains((Object)grid.get(sqrInBox/10).get(sqrInBox%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInBox/10).get(sqrInBox%10).box.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInBox/10).get(sqrInBox%10).box.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
				}
				for (int testNum: grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.int2sqr.keySet())
				{
					if (grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.int2sqr.get((Object)testNum).size() == 1) 
					{if (!toSolve1.contains((Object)grid.get(sqrInBox/10).get(sqrInBox%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInBox/10).get(sqrInBox%10).lineHori.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
			   }
				for (int testNum: grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.int2sqr.keySet())
				{
					if (grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.int2sqr.get((Object)testNum).size() == 1)
					{if (!toSolve1.contains((Object)grid.get(sqrInBox/10).get(sqrInBox%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInBox/10).get(sqrInBox%10).lineVert.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
				}
			}
			
			for (int sqrInLineHori : sqr.lineHori.sqr2int.keySet())
			{
				if (grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.sqr2int.get((Object)sqrInLineHori).size() == 1 ||
					 grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.sqr2int.get((Object)sqrInLineHori).size() == 1 ||
					 grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.sqr2int.get((Object)sqrInLineHori).size() == 1)  
				{if (!toSolve.contains((Object)grid.get(sqrInLineHori/10).get(sqrInLineHori%10))) toSolve.add(grid.get(sqrInLineHori/10).get(sqrInLineHori%10));}
			
				for (int testNum: grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.int2sqr.keySet())
				{
					if (grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.int2sqr.get((Object)testNum).size() == 1)
					{if (!toSolve1.contains((Object)grid.get(sqrInLineHori/10).get(sqrInLineHori%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInLineHori/10).get(sqrInLineHori%10).box.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
				}
				for (int testNum: grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.int2sqr.keySet())
				{
					if (grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.int2sqr.get((Object)testNum).size() == 1) 
					{if (!toSolve1.contains((Object)grid.get(sqrInLineHori/10).get(sqrInLineHori%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineHori.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
			   }
				for (int testNum: grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.int2sqr.keySet())
				{
					if (grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.int2sqr.get((Object)testNum).size() == 1)
					{if (!toSolve1.contains((Object)grid.get(sqrInLineHori/10).get(sqrInLineHori%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInLineHori/10).get(sqrInLineHori%10).lineVert.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
				}
			}
			
			for (int sqrInLineVert : sqr.lineVert.sqr2int.keySet())
			{
				if (grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.sqr2int.get((Object)sqrInLineVert).size() == 1 ||
					 grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.sqr2int.get((Object)sqrInLineVert).size() == 1 ||
					 grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.sqr2int.get((Object)sqrInLineVert).size() == 1)  
				{if (!toSolve.contains((Object)grid.get(sqrInLineVert/10).get(sqrInLineVert%10))) toSolve.add(grid.get(sqrInLineVert/10).get(sqrInLineVert%10));}
			
				for (int testNum: grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.int2sqr.keySet())
				{
					if (grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.int2sqr.get((Object)testNum).size() == 1)
					{if (!toSolve1.contains((Object)grid.get(sqrInLineVert/10).get(sqrInLineVert%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInLineVert/10).get(sqrInLineVert%10).box.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
				}
				for (int testNum: grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.int2sqr.keySet())
				{
					if (grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.int2sqr.get((Object)testNum).size() == 1) 
					{if (!toSolve1.contains((Object)grid.get(sqrInLineVert/10).get(sqrInLineVert%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineHori.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
			   }
				for (int testNum: grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.int2sqr.keySet())
				{
					if (grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.int2sqr.get((Object)testNum).size() == 1)
					{if (!toSolve1.contains((Object)grid.get(sqrInLineVert/10).get(sqrInLineVert%10))) 
					{toSolve1.add(grid.get(grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.int2sqr.get((Object)testNum).get(0)/10).get(grid.get(sqrInLineVert/10).get(sqrInLineVert%10).lineVert.int2sqr.get((Object)testNum).get(0)%10));toSolveNum.add(testNum);}}
				}
			}
		
			if (toSolve.size() != 0)
			{
				for (Square solve : toSolve)
				{//WHY DO YOU NEED THE CONTAINS KEY IN THE FIRST PLACE?
					if (solve.box.sqr2int.containsKey((Object)(solve.posX*10+solve.posY)) && solve.box.sqr2int.get((Object)(solve.posX*10+solve.posY)).size() == 1)
					{solve(solve, solve.box.sqr2int.get((Object)(solve.posX*10+solve.posY)).get(0));}
					else if (solve.lineHori.sqr2int.containsKey((Object)(solve.posX*10+solve.posY)) && solve.lineHori.sqr2int.get((Object)(solve.posX*10+solve.posY)).size() == 1)
					{solve(solve, solve.lineHori.sqr2int.get((Object)(solve.posX*10+solve.posY)).get(0));}
					else if (solve.lineVert.sqr2int.containsKey((Object)(solve.posX*10+solve.posY)) && solve.lineVert.sqr2int.get((Object)(solve.posX*10+solve.posY)).size() == 1)
					{solve(solve, solve.lineVert.sqr2int.get((Object)(solve.posX*10+solve.posY)).get(0));}
				}
			}
			
			if (toSolve1.size() != 0)
			{
				for (int i = 0; i < toSolve1.size(); i++)
				{//WHY DO YOU NEED THE CONTAINS KEY IN THE FIRST PLACE?
					if (toSolve1.get(i).box.sqr2int.containsKey((Object)(toSolve1.get(i).posX*10+toSolve1.get(i).posY)))
					{solve(toSolve1.get(i), toSolveNum.get(i));}
					else if (toSolve1.get(i).lineHori.sqr2int.containsKey((Object)(toSolve1.get(i).posX*10+toSolve1.get(i).posY)))
					{solve(toSolve1.get(i), toSolveNum.get(i));}
					else if (toSolve1.get(i).lineVert.sqr2int.containsKey((Object)(toSolve1.get(i).posX*10+toSolve1.get(i).posY)))
					{solve(toSolve1.get(i), toSolveNum.get(i));}
				}
			}
		}
		
		int count = 0;
		void solve(Square sqr, int num)
		{
			grid.get(sqr.posX).get(sqr.posY).value = Character.forDigit(num, 10);
			System.out.println("SOLVING: " + (sqr.posX*10+sqr.posY) + " " + num); count++;
			printGrid(); System.out.println(count);
			solveCase(sqr, num);
		}
		
		void printGrid()//fix this
      {
      	System.out.print("-------------------\n");
      	for (int n = 1; n <= 9; n++)
      	{
      		for (int i = 1; i <= 9; i++)
      		{
      			System.out.print("|" + grid.get(n).get(i).value);
      		}
      		System.out.print("|\n");
      	}
      	System.out.print("-------------------\n");
      }
	}
	
	static boolean algo1 = true;
	static boolean algo2 = true;
	public static void main(String[] args) throws FileNotFoundException
	{
		/*
		Scanner inputFile = new Scanner(new FileReader("src/Sudoku/input/input24.txt"));
		String input[] = new String[dimension+1];
		for (int i = 1; i <= dimension; i++)
		{
			input[i] = " " + inputFile.nextLine();
		}
		*/
		Scanner inputFile = new Scanner(new FileReader("src/Sudoku/input/micro.txt"));
		String input[] = new String[dimension+1];
		for (int i = 1; i <= dimension; i++)
		{
			input[i] = " " + inputFile.nextLine().replaceAll("\\s+","").replaceAll("x"," ");
		}
		
		long start = System.currentTimeMillis();
		Grid grid = new Grid(input);
		grid.execute();
		grid.execute2();
		long end = System.currentTimeMillis();
		long elapsed = end - start;
		System.out.println("Duration: "+ elapsed);
	}
}