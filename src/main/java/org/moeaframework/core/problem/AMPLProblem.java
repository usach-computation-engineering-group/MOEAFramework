package org.moeaframework.core.problem;

import org.moeaframework.core.Solution;
import org.moeaframework.core.variable.EncodingUtils;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.core.variable.BinaryIntegerVariable;

public class AMPLProblem extends AbstractProblem {
	
	private int[][][][]   v = new int[2][7][2][3];		// v. 84 variables
	private int[][][]     u = new int[7][3][2];			// u. 42 variables
	private int[][][] 	  x = new int[7][7][3]; 		// x (boolean). 147 variables
	private int[][]   	  y = new int[7][3]; 			// y (boolean). 21 variables
	private int[][]   	  f = new int[2][3]; 			// f (boolean). 6 variables
	private int[]     	  z = new int[2]; 				// z (boolean). 2 variables
	// 126 int variables
	// 176 boolean variables
	
	public AMPLProblem(int numberOfVariables, int numberOfObjectives, int numberOfConstraints) {
		super(numberOfVariables, numberOfObjectives, numberOfConstraints);
	}

	@Override
	public void evaluate(Solution solution) {
		double obj = 0.0;
		int countInt = 0;
		
		// Int variables setting
		for(int z = 0; z < 2; z++) {
			for(int y = 0; y < 7; y++) {
				for(int w = 0; w < 2; w++) {
					for(int g = 0; g < 3; g++) {
						v[z][y][w][g] = (int) ((RealVariable) solution.getVariable(countInt)).getValue();
						countInt++;
					}
				}
			}
		}
		

		for(int y = 0; y < 7; y++) {
			for(int w = 0; w < 3; w++) {
				for(int g = 0; g < 2; g++) {
					u[y][w][g] = (int) ((RealVariable) solution.getVariable(countInt)).getValue();
					countInt++;
				}
			}
		}
		
		// Bin variables setting
		for(int y = 0; y < 7; y++) {
			for(int w = 0; w < 7; w++) {
				for(int g = 0; g < 3; g++) {
					x[y][w][g] = ((BinaryIntegerVariable) solution.getVariable(countInt)).getValue();
					countInt++;
				}
			}
		}
		
		for(int w = 0; w < 7; w++) {
			for(int g = 0; g < 3; g++) {
				y[w][g] = ((BinaryIntegerVariable) solution.getVariable(countInt)).getValue();
				countInt++;
			}
		}
		
		for(int w = 0; w < 2; w++) {
			for(int g = 0; g < 3; g++) {
				f[w][g] = ((BinaryIntegerVariable) solution.getVariable(countInt)).getValue();
				countInt++;
			}
		}
		
		for(int g = 0; g < 2; g++) {
			z[g] = ((BinaryIntegerVariable) solution.getVariable(countInt)).getValue();
			countInt++;
		}
		
		//minimize CLT:
		obj = 30000*x[0][0][0] + 30000*x[0][0][1] + 1000*x[0][0][2] + 9000*x[0][1][0] + 9000*x[0][1][1] + 1000*x[0][1][2] + 259*x[0][2][0] + 259*x[0][2][1] + 9*x[0][2][2] + 25*x[0][3][0] + 25*x[0][3][1] + 11*x[0][3][2] + 210*x[0][4][0] + 210*x[0][4][1] + 10*x[0][4][2] + 84*x[0][5][0] + 84*x[0][5][1] + 12*x[0][5][2] + 38*x[0][6][0] + 38*x[0][6][1] + 8*x[0][6][2] + 1000*x[1][0][0] + 1000*x[1][0][1] + 1000*x[1][0][2] + 30000*x[1][1][0] + 30000*x[1][1][1] + 1000*x[1][1][2] + 74*x[1][2][0] + 74*x[1][2][1] + 3*x[1][2][2] + 75*x[1][3][0] + 75*x[1][3][1] + 4*x[1][3][2] + 168*x[1][4][0] + 168*x[1][4][1] + 2*x[1][4][2] + 189*x[1][5][0] + 189*x[1][5][1] + 5*x[1][5][2] + 95*x[1][6][0] + 95*x[1][6][1] + 3*x[1][6][2] + 296*x[2][0][0] + 296*x[2][0][1] + 2*x[2][0][2] + 150*x[2][1][0] + 150*x[2][1][1] + 4*x[2][1][2] + 1260*x[2][2][0] + 1260*x[2][2][1] + 7*x[2][2][2] + 168*x[2][3][0] + 168*x[2][3][1] + 11*x[2][3][2] + 190*x[2][4][0] + 190*x[2][4][1] + 9*x[2][4][2] + 168*x[2][5][0] + 168*x[2][5][1] + 7*x[2][5][2] + 114*x[2][6][0] + 114*x[2][6][1] + 5*x[2][6][2] + 230*x[3][0][0] + 230*x[3][0][1] + 11*x[3][0][2] + 72*x[3][1][0] + 72*x[3][1][1] + 10*x[3][1][2] + 184*x[3][2][0] + 184*x[3][2][1] + 12*x[3][2][2] + 360*x[3][3][0] + 360*x[3][3][1] + 8*x[3][3][2] + 60*x[3][4][0] + 60*x[3][4][1] + 11*x[3][4][2] + 85*x[3][5][0] + 85*x[3][5][1] + 10*x[3][5][2] + 154*x[3][6][0] + 154*x[3][6][1] + 12*x[3][6][2] + 60*x[4][0][0] + 60*x[4][0][1] + 7*x[4][0][2] + 135*x[4][1][0] + 135*x[4][1][1] + 11*x[4][1][2] + 136*x[4][2][0] + 136*x[4][2][1] + 9*x[4][2][2] + 175*x[4][3][0] + 175*x[4][3][1] + 7*x[4][3][2] + 1260*x[4][4][0] + 1260*x[4][4][1] + 5*x[4][4][2] + 168*x[4][5][0] + 168*x[4][5][1] + 2*x[4][5][2] + 117*x[4][6][0] + 117*x[4][6][1] + 4*x[4][6][2] + 250*x[5][0][0] + 250*x[5][0][1] + 2*x[5][0][2] + 126*x[5][1][0] + 126*x[5][1][1] + 4*x[5][1][2] + 105*x[5][2][0] + 105*x[5][2][1] + 7*x[5][2][2] + 72*x[5][3][0] + 72*x[5][3][1] + 11*x[5][3][2] + 30*x[5][4][0] + 30*x[5][4][1] + 2*x[5][4][2] + 510*x[5][5][0] + 510*x[5][5][1] + 4*x[5][5][2] + 189*x[5][6][0] + 189*x[5][6][1] + 7*x[5][6][2] + 138*x[6][0][0] + 138*x[6][0][1] + 11*x[6][0][2] + 108*x[6][1][0] + 108*x[6][1][1] + 12*x[6][1][2] + 230*x[6][2][0] + 230*x[6][2][1] + 7*x[6][2][2] + 296*x[6][3][0] + 296*x[6][3][1] + 11*x[6][3][2] + 50*x[6][4][0] + 50*x[6][4][1] + 9*x[6][4][2] + 336*x[6][5][0] + 336*x[6][5][1] + 7*x[6][5][2] + 630*x[6][6][0] + 630*x[6][6][1] + 5*x[6][6][2] - 10000*v[0][0][0][0] - 10000*v[0][0][0][1] - 10000*v[0][0][0][2] - 20000*v[0][0][1][0] - 20000*v[0][0][1][1] - 20000*v[0][0][1][2] - 10000*v[0][1][0][0] - 10000*v[0][1][0][1] - 10000*v[0][1][0][2] - 20000*v[0][1][1][0] - 20000*v[0][1][1][1] - 20000*v[0][1][1][2] - 10000*v[0][2][0][0] - 10000*v[0][2][0][1] - 10000*v[0][2][0][2] - 20000*v[0][2][1][0] - 20000*v[0][2][1][1] - 20000*v[0][2][1][2] - 10000*v[0][3][0][0] - 10000*v[0][3][0][1] - 10000*v[0][3][0][2] - 20000*v[0][3][1][0] - 20000*v[0][3][1][1] - 20000*v[0][3][1][2] - 10000*v[0][4][0][0] - 10000*v[0][4][0][1] - 10000*v[0][4][0][2] - 20000*v[0][4][1][0] - 20000*v[0][4][1][1] - 20000*v[0][4][1][2] - 10000*v[0][5][0][0] - 10000*v[0][5][0][1] - 10000*v[0][5][0][2] - 20000*v[0][5][1][0] - 20000*v[0][5][1][1] - 20000*v[0][5][1][2] - 10000*v[0][6][0][0] - 10000*v[0][6][0][1] - 10000*v[0][6][0][2] - 20000*v[0][6][1][0] - 20000*v[0][6][1][1] - 20000*v[0][6][1][2] - 10000*v[1][0][0][0] - 10000*v[1][0][0][1] - 10000*v[1][0][0][2] - 20000*v[1][0][1][0] - 20000*v[1][0][1][1] - 20000*v[1][0][1][2] - 10000*v[1][1][0][0] - 10000*v[1][1][0][1] - 10000*v[1][1][0][2] - 20000*v[1][1][1][0] - 20000*v[1][1][1][1] - 20000*v[1][1][1][2] - 10000*v[1][2][0][0] - 10000*v[1][2][0][1] - 10000*v[1][2][0][2] - 20000*v[1][2][1][0] - 20000*v[1][2][1][1] - 20000*v[1][2][1][2] - 10000*v[1][3][0][0] - 10000*v[1][3][0][1] - 10000*v[1][3][0][2] - 20000*v[1][3][1][0] - 20000*v[1][3][1][1] - 20000*v[1][3][1][2] - 10000*v[1][4][0][0] - 10000*v[1][4][0][1] - 10000*v[1][4][0][2] - 20000*v[1][4][1][0] - 20000*v[1][4][1][1] - 20000*v[1][4][1][2] - 10000*v[1][5][0][0] - 10000*v[1][5][0][1] - 10000*v[1][5][0][2] - 20000*v[1][5][1][0] - 20000*v[1][5][1][1] - 20000*v[1][5][1][2] - 10000*v[1][6][0][0] - 10000*v[1][6][0][1] - 10000*v[1][6][0][2] - 20000*v[1][6][1][0] - 20000*v[1][6][1][1] - 20000*v[1][6][1][2] + 1000*z[0] + 300*z[1] + 100*f[0][0] + 100*f[0][1] + 50*f[0][2] + 100*f[1][0] + 100*f[1][1] + 50*f[1][2] + 2350000;

		int i = 0;
		//subject to R1[0][0]:
		solution.setConstraint(i, v[0][0][0][0] + v[0][0][0][1] + v[0][0][0][2] + v[1][0][0][0] + v[1][0][0][1] + v[1][0][0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R1[0][1]:
		solution.setConstraint(i, v[0][0][1][0] + v[0][0][1][1] + v[0][0][1][2] + v[1][0][1][0] + v[1][0][1][1] + v[1][0][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R1[1][0]:
		solution.setConstraint(i, v[0][1][0][0] + v[0][1][0][1] + v[0][1][0][2] + v[1][1][0][0] + v[1][1][0][1] + v[1][1][0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R1[1][1]:
		solution.setConstraint(i, v[0][1][1][0] + v[0][1][1][1] + v[0][1][1][2] + v[1][1][1][0] + v[1][1][1][1] + v[1][1][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R1[2][0]:
		solution.setConstraint(i, v[0][2][0][0] + v[0][2][0][1] + v[0][2][0][2] + v[1][2][0][0] + v[1][2][0][1] + v[1][2][0][2] <= 12 ? 0.0 : 1.0);
		i++;
		//subject to R1[2][1]:
		solution.setConstraint(i, v[0][2][1][0] + v[0][2][1][1] + v[0][2][1][2] + v[1][2][1][0] + v[1][2][1][1] + v[1][2][1][2] <= 23 ? 0.0 : 1.0);
		i++;
		//subject to R1[3][0]:
		solution.setConstraint(i, v[0][3][0][0] + v[0][3][0][1] + v[0][3][0][2] + v[1][3][0][0] + v[1][3][0][1] + v[1][3][0][2] <= 17 ? 0.0 : 1.0);
		i++;
		//subject to R1[3][1]:
		solution.setConstraint(i, v[0][3][1][0] + v[0][3][1][1] + v[0][3][1][2] + v[1][3][1][0] + v[1][3][1][1] + v[1][3][1][2] <= 20 ? 0.0 : 1.0);
		i++;
		//subject to R1[4][0]:
		solution.setConstraint(i, v[0][4][0][0] + v[0][4][0][1] + v[0][4][0][2] + v[1][4][0][0] + v[1][4][0][1] + v[1][4][0][2] <= 11 ? 0.0 : 1.0);
		i++;
		//subject to R1[4][1]:
		solution.setConstraint(i, v[0][4][1][0] + v[0][4][1][1] + v[0][4][1][2] + v[1][4][1][0] + v[1][4][1][1] + v[1][4][1][2] <= 9 ? 0.0 : 1.0);
		i++;
		//subject to R1[5][0]:
		solution.setConstraint(i, v[0][5][0][0] + v[0][5][0][1] + v[0][5][0][2] + v[1][5][0][0] + v[1][5][0][1] + v[1][5][0][2] <= 23 ? 0.0 : 1.0);
		i++;
		//subject to R1[5][1]:
		solution.setConstraint(i, v[0][5][1][0] + v[0][5][1][1] + v[0][5][1][2] + v[1][5][1][0] + v[1][5][1][1] + v[1][5][1][2] <= 10 ? 0.0 : 1.0);
		i++;
		//subject to R1[6][0]:
		solution.setConstraint(i, v[0][6][0][0] + v[0][6][0][1] + v[0][6][0][2] + v[1][6][0][0] + v[1][6][0][1] + v[1][6][0][2] <= 14 ? 0.0 : 1.0);
		i++;
		//subject to R1[6][1]:
		solution.setConstraint(i, v[0][6][1][0] + v[0][6][1][1] + v[0][6][1][2] + v[1][6][1][0] + v[1][6][1][1] + v[1][6][1][2] <= 17 ? 0.0 : 1.0);
		i++;
		//subject to R2[0]:
		solution.setConstraint(i, v[0][0][0][0] + v[0][0][1][0] + v[0][1][0][0] + v[0][1][1][0] + v[0][2][0][0] + v[0][2][1][0] + v[0][3][0][0] + v[0][3][1][0] + v[0][4][0][0] + v[0][4][1][0] + v[0][5][0][0] + v[0][5][1][0] + v[0][6][0][0] + v[0][6][1][0] + v[1][0][0][0] + v[1][0][1][0] + v[1][1][0][0] + v[1][1][1][0] + v[1][2][0][0] + v[1][2][1][0] + v[1][3][0][0] + v[1][3][1][0] + v[1][4][0][0] + v[1][4][1][0] + v[1][5][0][0] + v[1][5][1][0] + v[1][6][0][0] + v[1][6][1][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R2[1]:
		solution.setConstraint(i, v[0][0][0][1] + v[0][0][1][1] + v[0][1][0][1] + v[0][1][1][1] + v[0][2][0][1] + v[0][2][1][1] + v[0][3][0][1] + v[0][3][1][1] + v[0][4][0][1] + v[0][4][1][1] + v[0][5][0][1] + v[0][5][1][1] + v[0][6][0][1] + v[0][6][1][1] + v[1][0][0][1] + v[1][0][1][1] + v[1][1][0][1] + v[1][1][1][1] + v[1][2][0][1] + v[1][2][1][1] + v[1][3][0][1] + v[1][3][1][1] + v[1][4][0][1] + v[1][4][1][1] + v[1][5][0][1] + v[1][5][1][1] + v[1][6][0][1] + v[1][6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R2[2]:
		solution.setConstraint(i, v[0][0][0][2] + v[0][0][1][2] + v[0][1][0][2] + v[0][1][1][2] + v[0][2][0][2] + v[0][2][1][2] + v[0][3][0][2] + v[0][3][1][2] + v[0][4][0][2] + v[0][4][1][2] + v[0][5][0][2] + v[0][5][1][2] + v[0][6][0][2] + v[0][6][1][2] + v[1][0][0][2] + v[1][0][1][2] + v[1][1][0][2] + v[1][1][1][2] + v[1][2][0][2] + v[1][2][1][2] + v[1][3][0][2] + v[1][3][1][2] + v[1][4][0][2] + v[1][4][1][2] + v[1][5][0][2] + v[1][5][1][2] + v[1][6][0][2] + v[1][6][1][2] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R3[0][0]:
		solution.setConstraint(i, v[0][2][0][0] + v[0][2][0][1] + v[0][2][0][2] + v[0][3][0][0] + v[0][3][0][1] + v[0][3][0][2] + v[0][4][0][0] + v[0][4][0][1] + v[0][4][0][2] + v[0][5][0][0] + v[0][5][0][1] + v[0][5][0][2] + v[0][6][0][0] + v[0][6][0][1] + v[0][6][0][2] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R3[0][1]:
		solution.setConstraint(i, v[0][2][1][0] + v[0][2][1][1] + v[0][2][1][2] + v[0][3][1][0] + v[0][3][1][1] + v[0][3][1][2] + v[0][4][1][0] + v[0][4][1][1] + v[0][4][1][2] + v[0][5][1][0] + v[0][5][1][1] + v[0][5][1][2] + v[0][6][1][0] + v[0][6][1][1] + v[0][6][1][2] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R3[1][0]:
		solution.setConstraint(i, v[1][2][0][0] + v[1][2][0][1] + v[1][2][0][2] + v[1][3][0][0] + v[1][3][0][1] + v[1][3][0][2] + v[1][4][0][0] + v[1][4][0][1] + v[1][4][0][2] + v[1][5][0][0] + v[1][5][0][1] + v[1][5][0][2] + v[1][6][0][0] + v[1][6][0][1] + v[1][6][0][2] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R3[1][1]:
		solution.setConstraint(i, v[1][2][1][0] + v[1][2][1][1] + v[1][2][1][2] + v[1][3][1][0] + v[1][3][1][1] + v[1][3][1][2] + v[1][4][1][0] + v[1][4][1][1] + v[1][4][1][2] + v[1][5][1][0] + v[1][5][1][1] + v[1][5][1][2] + v[1][6][1][0] + v[1][6][1][1] + v[1][6][1][2] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][2][0][0]:
		solution.setConstraint(i, 12*y[2][0] - v[0][2][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][2][0][1]:
		solution.setConstraint(i, 12*y[2][1] - v[0][2][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][2][0][2]:
		solution.setConstraint(i, 12*y[2][2] - v[0][2][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][2][1][0]:
		solution.setConstraint(i, 23*y[2][0] - v[0][2][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][2][1][1]:
		solution.setConstraint(i, 23*y[2][1] - v[0][2][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][2][1][2]:
		solution.setConstraint(i, 23*y[2][2] - v[0][2][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][3][0][0]:
		solution.setConstraint(i, 17*y[3][0] - v[0][3][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][3][0][1]:
		solution.setConstraint(i, 17*y[3][1] - v[0][3][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][3][0][2]:
		solution.setConstraint(i, 17*y[3][2] - v[0][3][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][3][1][0]:
		solution.setConstraint(i, 20*y[3][0] - v[0][3][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][3][1][1]:
		solution.setConstraint(i, 20*y[3][1] - v[0][3][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][3][1][2]:
		solution.setConstraint(i, 20*y[3][2] - v[0][3][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][4][0][0]:
		solution.setConstraint(i, 11*y[4][0] - v[0][4][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][4][0][1]:
		solution.setConstraint(i, 11*y[4][1] - v[0][4][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][4][0][2]:
		solution.setConstraint(i, 11*y[4][2] - v[0][4][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][4][1][0]:
		solution.setConstraint(i, 9*y[4][0] - v[0][4][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][4][1][1]:
		solution.setConstraint(i, 9*y[4][1] - v[0][4][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][4][1][2]:
		solution.setConstraint(i, 9*y[4][2] - v[0][4][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][5][0][0]:
		solution.setConstraint(i, 23*y[5][0] - v[0][5][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][5][0][1]:
		solution.setConstraint(i, 23*y[5][1] - v[0][5][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][5][0][2]:
		solution.setConstraint(i, 23*y[5][2] - v[0][5][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][5][1][0]:
		solution.setConstraint(i, 10*y[5][0] - v[0][5][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][5][1][1]:
		solution.setConstraint(i, 10*y[5][1] - v[0][5][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][5][1][2]:
		solution.setConstraint(i, 10*y[5][2] - v[0][5][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][6][0][0]:
		solution.setConstraint(i, 14*y[6][0] - v[0][6][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][6][0][1]:
		solution.setConstraint(i, 14*y[6][1] - v[0][6][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][6][0][2]:
		solution.setConstraint(i, 14*y[6][2] - v[0][6][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][6][1][0]:
		solution.setConstraint(i, 17*y[6][0] - v[0][6][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][6][1][1]:
		solution.setConstraint(i, 17*y[6][1] - v[0][6][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[0][6][1][2]:
		solution.setConstraint(i, 17*y[6][2] - v[0][6][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][2][0][0]:
		solution.setConstraint(i, 12*y[2][0] - v[1][2][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][2][0][1]:
		solution.setConstraint(i, 12*y[2][1] - v[1][2][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][2][0][2]:
		solution.setConstraint(i, 12*y[2][2] - v[1][2][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][2][1][0]:
		solution.setConstraint(i, 23*y[2][0] - v[1][2][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][2][1][1]:
		solution.setConstraint(i, 23*y[2][1] - v[1][2][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][2][1][2]:
		solution.setConstraint(i, 23*y[2][2] - v[1][2][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][3][0][0]:
		solution.setConstraint(i, 17*y[3][0] - v[1][3][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][3][0][1]:
		solution.setConstraint(i, 17*y[3][1] - v[1][3][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][3][0][2]:
		solution.setConstraint(i, 17*y[3][2] - v[1][3][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][3][1][0]:
		solution.setConstraint(i, 20*y[3][0] - v[1][3][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][3][1][1]:
		solution.setConstraint(i, 20*y[3][1] - v[1][3][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][3][1][2]:
		solution.setConstraint(i, 20*y[3][2] - v[1][3][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][4][0][0]:
		solution.setConstraint(i, 11*y[4][0] - v[1][4][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][4][0][1]:
		solution.setConstraint(i, 11*y[4][1] - v[1][4][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][4][0][2]:
		solution.setConstraint(i, 11*y[4][2] - v[1][4][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][4][1][0]:
		solution.setConstraint(i, 9*y[4][0] - v[1][4][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][4][1][1]:
		solution.setConstraint(i, 9*y[4][1] - v[1][4][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][4][1][2]:
		solution.setConstraint(i, 9*y[4][2] - v[1][4][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][5][0][0]:
		solution.setConstraint(i, 23*y[5][0] - v[1][5][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][5][0][1]:
		solution.setConstraint(i, 23*y[5][1] - v[1][5][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][5][0][2]:
		solution.setConstraint(i, 23*y[5][2] - v[1][5][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][5][1][0]:
		solution.setConstraint(i, 10*y[5][0] - v[1][5][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][5][1][1]:
		solution.setConstraint(i, 10*y[5][1] - v[1][5][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][5][1][2]:
		solution.setConstraint(i, 10*y[5][2] - v[1][5][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][6][0][0]:
		solution.setConstraint(i, 14*y[6][0] - v[1][6][0][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][6][0][1]:
		solution.setConstraint(i, 14*y[6][1] - v[1][6][0][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][6][0][2]:
		solution.setConstraint(i, 14*y[6][2] - v[1][6][0][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][6][1][0]:
		solution.setConstraint(i, 17*y[6][0] - v[1][6][1][0] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][6][1][1]:
		solution.setConstraint(i, 17*y[6][1] - v[1][6][1][1] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R4[1][6][1][2]:
		solution.setConstraint(i, 17*y[6][2] - v[1][6][1][2] >= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][2][0][0]:
		solution.setConstraint(i, v[0][2][0][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][2][0][1]:
		solution.setConstraint(i, v[0][2][0][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][2][0][2]:
		solution.setConstraint(i, v[0][2][0][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][2][1][0]:
		solution.setConstraint(i, v[0][2][1][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][2][1][1]:
		solution.setConstraint(i, v[0][2][1][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][2][1][2]:
		solution.setConstraint(i, v[0][2][1][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][3][0][0]:
		solution.setConstraint(i, v[0][3][0][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][3][0][1]:
		solution.setConstraint(i, v[0][3][0][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][3][0][2]:
		solution.setConstraint(i, v[0][3][0][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][3][1][0]:
		solution.setConstraint(i, v[0][3][1][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][3][1][1]:
		solution.setConstraint(i, v[0][3][1][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][3][1][2]:
		solution.setConstraint(i, v[0][3][1][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][4][0][0]:
		solution.setConstraint(i, v[0][4][0][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][4][0][1]:
		solution.setConstraint(i, v[0][4][0][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][4][0][2]:
		solution.setConstraint(i, v[0][4][0][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][4][1][0]:
		solution.setConstraint(i, v[0][4][1][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][4][1][1]:
		solution.setConstraint(i, v[0][4][1][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][4][1][2]:
		solution.setConstraint(i, v[0][4][1][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][5][0][0]:
		solution.setConstraint(i, v[0][5][0][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][5][0][1]:
		solution.setConstraint(i, v[0][5][0][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][5][0][2]:
		solution.setConstraint(i, v[0][5][0][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][5][1][0]:
		solution.setConstraint(i, v[0][5][1][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][5][1][1]:
		solution.setConstraint(i, v[0][5][1][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][5][1][2]:
		solution.setConstraint(i, v[0][5][1][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][6][0][0]:
		solution.setConstraint(i, v[0][6][0][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][6][0][1]:
		solution.setConstraint(i, v[0][6][0][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][6][0][2]:
		solution.setConstraint(i, v[0][6][0][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][6][1][0]:
		solution.setConstraint(i, v[0][6][1][0] - 100*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][6][1][1]:
		solution.setConstraint(i, v[0][6][1][1] - 50*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[0][6][1][2]:
		solution.setConstraint(i, v[0][6][1][2] - 48*z[0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][2][0][0]:
		solution.setConstraint(i, v[1][2][0][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][2][0][1]:
		solution.setConstraint(i, v[1][2][0][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][2][0][2]:
		solution.setConstraint(i, v[1][2][0][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][2][1][0]:
		solution.setConstraint(i, v[1][2][1][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][2][1][1]:
		solution.setConstraint(i, v[1][2][1][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][2][1][2]:
		solution.setConstraint(i, v[1][2][1][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][3][0][0]:
		solution.setConstraint(i, v[1][3][0][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][3][0][1]:
		solution.setConstraint(i, v[1][3][0][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][3][0][2]:
		solution.setConstraint(i, v[1][3][0][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][3][1][0]:
		solution.setConstraint(i, v[1][3][1][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][3][1][1]:
		solution.setConstraint(i, v[1][3][1][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][3][1][2]:
		solution.setConstraint(i, v[1][3][1][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][4][0][0]:
		solution.setConstraint(i, v[1][4][0][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][4][0][1]:
		solution.setConstraint(i, v[1][4][0][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][4][0][2]:
		solution.setConstraint(i, v[1][4][0][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][4][1][0]:
		solution.setConstraint(i, v[1][4][1][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][4][1][1]:
		solution.setConstraint(i, v[1][4][1][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][4][1][2]:
		solution.setConstraint(i, v[1][4][1][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][5][0][0]:
		solution.setConstraint(i, v[1][5][0][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][5][0][1]:
		solution.setConstraint(i, v[1][5][0][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][5][0][2]:
		solution.setConstraint(i, v[1][5][0][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][5][1][0]:
		solution.setConstraint(i, v[1][5][1][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][5][1][1]:
		solution.setConstraint(i, v[1][5][1][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][5][1][2]:
		solution.setConstraint(i, v[1][5][1][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][6][0][0]:
		solution.setConstraint(i, v[1][6][0][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][6][0][1]:
		solution.setConstraint(i, v[1][6][0][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][6][0][2]:
		solution.setConstraint(i, v[1][6][0][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][6][1][0]:
		solution.setConstraint(i, v[1][6][1][0] - 100*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][6][1][1]:
		solution.setConstraint(i, v[1][6][1][1] - 50*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R5[1][6][1][2]:
		solution.setConstraint(i, v[1][6][1][2] - 48*z[1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][2][0][0]:
		solution.setConstraint(i, v[0][2][0][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][2][0][1]:
		solution.setConstraint(i, v[0][2][0][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][2][0][2]:
		solution.setConstraint(i, v[0][2][0][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][2][1][0]:
		solution.setConstraint(i, v[0][2][1][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][2][1][1]:
		solution.setConstraint(i, v[0][2][1][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][2][1][2]:
		solution.setConstraint(i, v[0][2][1][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][3][0][0]:
		solution.setConstraint(i, v[0][3][0][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][3][0][1]:
		solution.setConstraint(i, v[0][3][0][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][3][0][2]:
		solution.setConstraint(i, v[0][3][0][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][3][1][0]:
		solution.setConstraint(i, v[0][3][1][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][3][1][1]:
		solution.setConstraint(i, v[0][3][1][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][3][1][2]:
		solution.setConstraint(i, v[0][3][1][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][4][0][0]:
		solution.setConstraint(i, v[0][4][0][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][4][0][1]:
		solution.setConstraint(i, v[0][4][0][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][4][0][2]:
		solution.setConstraint(i, v[0][4][0][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][4][1][0]:
		solution.setConstraint(i, v[0][4][1][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][4][1][1]:
		solution.setConstraint(i, v[0][4][1][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][4][1][2]:
		solution.setConstraint(i, v[0][4][1][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][5][0][0]:
		solution.setConstraint(i, v[0][5][0][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][5][0][1]:
		solution.setConstraint(i, v[0][5][0][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][5][0][2]:
		solution.setConstraint(i, v[0][5][0][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][5][1][0]:
		solution.setConstraint(i, v[0][5][1][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][5][1][1]:
		solution.setConstraint(i, v[0][5][1][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][5][1][2]:
		solution.setConstraint(i, v[0][5][1][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][6][0][0]:
		solution.setConstraint(i, v[0][6][0][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][6][0][1]:
		solution.setConstraint(i, v[0][6][0][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][6][0][2]:
		solution.setConstraint(i, v[0][6][0][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][6][1][0]:
		solution.setConstraint(i, v[0][6][1][0] - 100*f[0][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][6][1][1]:
		solution.setConstraint(i, v[0][6][1][1] - 50*f[0][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[0][6][1][2]:
		solution.setConstraint(i, v[0][6][1][2] - 48*f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][2][0][0]:
		solution.setConstraint(i, v[1][2][0][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][2][0][1]:
		solution.setConstraint(i, v[1][2][0][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][2][0][2]:
		solution.setConstraint(i, v[1][2][0][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][2][1][0]:
		solution.setConstraint(i, v[1][2][1][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][2][1][1]:
		solution.setConstraint(i, v[1][2][1][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][2][1][2]:
		solution.setConstraint(i, v[1][2][1][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][3][0][0]:
		solution.setConstraint(i, v[1][3][0][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][3][0][1]:
		solution.setConstraint(i, v[1][3][0][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][3][0][2]:
		solution.setConstraint(i, v[1][3][0][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][3][1][0]:
		solution.setConstraint(i, v[1][3][1][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][3][1][1]:
		solution.setConstraint(i, v[1][3][1][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][3][1][2]:
		solution.setConstraint(i, v[1][3][1][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][4][0][0]:
		solution.setConstraint(i, v[1][4][0][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][4][0][1]:
		solution.setConstraint(i, v[1][4][0][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][4][0][2]:
		solution.setConstraint(i, v[1][4][0][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][4][1][0]:
		solution.setConstraint(i, v[1][4][1][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][4][1][1]:
		solution.setConstraint(i, v[1][4][1][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][4][1][2]:
		solution.setConstraint(i, v[1][4][1][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][5][0][0]:
		solution.setConstraint(i, v[1][5][0][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][5][0][1]:
		solution.setConstraint(i, v[1][5][0][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][5][0][2]:
		solution.setConstraint(i, v[1][5][0][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][5][1][0]:
		solution.setConstraint(i, v[1][5][1][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][5][1][1]:
		solution.setConstraint(i, v[1][5][1][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][5][1][2]:
		solution.setConstraint(i, v[1][5][1][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][6][0][0]:
		solution.setConstraint(i, v[1][6][0][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][6][0][1]:
		solution.setConstraint(i, v[1][6][0][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][6][0][2]:
		solution.setConstraint(i, v[1][6][0][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][6][1][0]:
		solution.setConstraint(i, v[1][6][1][0] - 100*f[1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][6][1][1]:
		solution.setConstraint(i, v[1][6][1][1] - 50*f[1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R6[1][6][1][2]:
		solution.setConstraint(i, v[1][6][1][2] - 48*f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][2][0]:
		solution.setConstraint(i, -99999*x[0][2][0] - 99999*x[1][2][0] - 99999*x[2][2][0] - 99999*x[3][2][0] - 99999*x[4][2][0] - 99999*x[5][2][0] - 99999*x[6][2][0] + v[0][2][0][0] + v[0][2][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][2][1]:
		solution.setConstraint(i, -99999*x[0][2][1] - 99999*x[1][2][1] - 99999*x[2][2][1] - 99999*x[3][2][1] - 99999*x[4][2][1] - 99999*x[5][2][1] - 99999*x[6][2][1] + v[0][2][0][1] + v[0][2][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][2][2]:
		solution.setConstraint(i, -99999*x[0][2][2] - 99999*x[1][2][2] - 99999*x[2][2][2] - 99999*x[3][2][2] - 99999*x[4][2][2] - 99999*x[5][2][2] - 99999*x[6][2][2] + v[0][2][0][2] + v[0][2][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][3][0]:
		solution.setConstraint(i, -99999*x[0][3][0] - 99999*x[1][3][0] - 99999*x[2][3][0] - 99999*x[3][3][0] - 99999*x[4][3][0] - 99999*x[5][3][0] - 99999*x[6][3][0] + v[0][3][0][0] + v[0][3][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][3][1]:
		solution.setConstraint(i, -99999*x[0][3][1] - 99999*x[1][3][1] - 99999*x[2][3][1] - 99999*x[3][3][1] - 99999*x[4][3][1] - 99999*x[5][3][1] - 99999*x[6][3][1] + v[0][3][0][1] + v[0][3][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][3][2]:
		solution.setConstraint(i, -99999*x[0][3][2] - 99999*x[1][3][2] - 99999*x[2][3][2] - 99999*x[3][3][2] - 99999*x[4][3][2] - 99999*x[5][3][2] - 99999*x[6][3][2] + v[0][3][0][2] + v[0][3][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][4][0]:
		solution.setConstraint(i, -99999*x[0][4][0] - 99999*x[1][4][0] - 99999*x[2][4][0] - 99999*x[3][4][0] - 99999*x[4][4][0] - 99999*x[5][4][0] - 99999*x[6][4][0] + v[0][4][0][0] + v[0][4][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][4][1]:
		solution.setConstraint(i, -99999*x[0][4][1] - 99999*x[1][4][1] - 99999*x[2][4][1] - 99999*x[3][4][1] - 99999*x[4][4][1] - 99999*x[5][4][1] - 99999*x[6][4][1] + v[0][4][0][1] + v[0][4][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][4][2]:
		solution.setConstraint(i, -99999*x[0][4][2] - 99999*x[1][4][2] - 99999*x[2][4][2] - 99999*x[3][4][2] - 99999*x[4][4][2] - 99999*x[5][4][2] - 99999*x[6][4][2] + v[0][4][0][2] + v[0][4][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][5][0]:
		solution.setConstraint(i, -99999*x[0][5][0] - 99999*x[1][5][0] - 99999*x[2][5][0] - 99999*x[3][5][0] - 99999*x[4][5][0] - 99999*x[5][5][0] - 99999*x[6][5][0] + v[0][5][0][0] + v[0][5][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][5][1]:
		solution.setConstraint(i, -99999*x[0][5][1] - 99999*x[1][5][1] - 99999*x[2][5][1] - 99999*x[3][5][1] - 99999*x[4][5][1] - 99999*x[5][5][1] - 99999*x[6][5][1] + v[0][5][0][1] + v[0][5][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][5][2]:
		solution.setConstraint(i, -99999*x[0][5][2] - 99999*x[1][5][2] - 99999*x[2][5][2] - 99999*x[3][5][2] - 99999*x[4][5][2] - 99999*x[5][5][2] - 99999*x[6][5][2] + v[0][5][0][2] + v[0][5][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][6][0]:
		solution.setConstraint(i, -99999*x[0][6][0] - 99999*x[1][6][0] - 99999*x[2][6][0] - 99999*x[3][6][0] - 99999*x[4][6][0] - 99999*x[5][6][0] - 99999*x[6][6][0] + v[0][6][0][0] + v[0][6][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][6][1]:
		solution.setConstraint(i, -99999*x[0][6][1] - 99999*x[1][6][1] - 99999*x[2][6][1] - 99999*x[3][6][1] - 99999*x[4][6][1] - 99999*x[5][6][1] - 99999*x[6][6][1] + v[0][6][0][1] + v[0][6][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[0][6][2]:
		solution.setConstraint(i, -99999*x[0][6][2] - 99999*x[1][6][2] - 99999*x[2][6][2] - 99999*x[3][6][2] - 99999*x[4][6][2] - 99999*x[5][6][2] - 99999*x[6][6][2] + v[0][6][0][2] + v[0][6][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][2][0]:
		solution.setConstraint(i, -99999*x[0][2][0] - 99999*x[1][2][0] - 99999*x[2][2][0] - 99999*x[3][2][0] - 99999*x[4][2][0] - 99999*x[5][2][0] - 99999*x[6][2][0] + v[1][2][0][0] + v[1][2][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][2][1]:
		solution.setConstraint(i, -99999*x[0][2][1] - 99999*x[1][2][1] - 99999*x[2][2][1] - 99999*x[3][2][1] - 99999*x[4][2][1] - 99999*x[5][2][1] - 99999*x[6][2][1] + v[1][2][0][1] + v[1][2][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][2][2]:
		solution.setConstraint(i, -99999*x[0][2][2] - 99999*x[1][2][2] - 99999*x[2][2][2] - 99999*x[3][2][2] - 99999*x[4][2][2] - 99999*x[5][2][2] - 99999*x[6][2][2] + v[1][2][0][2] + v[1][2][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][3][0]:
		solution.setConstraint(i, -99999*x[0][3][0] - 99999*x[1][3][0] - 99999*x[2][3][0] - 99999*x[3][3][0] - 99999*x[4][3][0] - 99999*x[5][3][0] - 99999*x[6][3][0] + v[1][3][0][0] + v[1][3][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][3][1]:
		solution.setConstraint(i, -99999*x[0][3][1] - 99999*x[1][3][1] - 99999*x[2][3][1] - 99999*x[3][3][1] - 99999*x[4][3][1] - 99999*x[5][3][1] - 99999*x[6][3][1] + v[1][3][0][1] + v[1][3][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][3][2]:
		solution.setConstraint(i, -99999*x[0][3][2] - 99999*x[1][3][2] - 99999*x[2][3][2] - 99999*x[3][3][2] - 99999*x[4][3][2] - 99999*x[5][3][2] - 99999*x[6][3][2] + v[1][3][0][2] + v[1][3][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][4][0]:
		solution.setConstraint(i, -99999*x[0][4][0] - 99999*x[1][4][0] - 99999*x[2][4][0] - 99999*x[3][4][0] - 99999*x[4][4][0] - 99999*x[5][4][0] - 99999*x[6][4][0] + v[1][4][0][0] + v[1][4][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][4][1]:
		solution.setConstraint(i, -99999*x[0][4][1] - 99999*x[1][4][1] - 99999*x[2][4][1] - 99999*x[3][4][1] - 99999*x[4][4][1] - 99999*x[5][4][1] - 99999*x[6][4][1] + v[1][4][0][1] + v[1][4][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][4][2]:
		solution.setConstraint(i, -99999*x[0][4][2] - 99999*x[1][4][2] - 99999*x[2][4][2] - 99999*x[3][4][2] - 99999*x[4][4][2] - 99999*x[5][4][2] - 99999*x[6][4][2] + v[1][4][0][2] + v[1][4][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][5][0]:
		solution.setConstraint(i, -99999*x[0][5][0] - 99999*x[1][5][0] - 99999*x[2][5][0] - 99999*x[3][5][0] - 99999*x[4][5][0] - 99999*x[5][5][0] - 99999*x[6][5][0] + v[1][5][0][0] + v[1][5][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][5][1]:
		solution.setConstraint(i, -99999*x[0][5][1] - 99999*x[1][5][1] - 99999*x[2][5][1] - 99999*x[3][5][1] - 99999*x[4][5][1] - 99999*x[5][5][1] - 99999*x[6][5][1] + v[1][5][0][1] + v[1][5][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][5][2]:
		solution.setConstraint(i, -99999*x[0][5][2] - 99999*x[1][5][2] - 99999*x[2][5][2] - 99999*x[3][5][2] - 99999*x[4][5][2] - 99999*x[5][5][2] - 99999*x[6][5][2] + v[1][5][0][2] + v[1][5][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][6][0]:
		solution.setConstraint(i, -99999*x[0][6][0] - 99999*x[1][6][0] - 99999*x[2][6][0] - 99999*x[3][6][0] - 99999*x[4][6][0] - 99999*x[5][6][0] - 99999*x[6][6][0] + v[1][6][0][0] + v[1][6][1][0] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][6][1]:
		solution.setConstraint(i, -99999*x[0][6][1] - 99999*x[1][6][1] - 99999*x[2][6][1] - 99999*x[3][6][1] - 99999*x[4][6][1] - 99999*x[5][6][1] - 99999*x[6][6][1] + v[1][6][0][1] + v[1][6][1][1] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R7[1][6][2]:
		solution.setConstraint(i, -99999*x[0][6][2] - 99999*x[1][6][2] - 99999*x[2][6][2] - 99999*x[3][6][2] - 99999*x[4][6][2] - 99999*x[5][6][2] - 99999*x[6][6][2] + v[1][6][0][2] + v[1][6][1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[0][0]:
		solution.setConstraint(i, -x[0][1][0] - x[0][2][0] - x[0][3][0] - x[0][4][0] - x[0][5][0] - x[0][6][0] + x[1][0][0] + x[2][0][0] + x[3][0][0] + x[4][0][0] + x[5][0][0] + x[6][0][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[0][1]:
		solution.setConstraint(i, x[0][1][0] - x[1][0][0] - x[1][2][0] - x[1][3][0] - x[1][4][0] - x[1][5][0] - x[1][6][0] + x[2][1][0] + x[3][1][0] + x[4][1][0] + x[5][1][0] + x[6][1][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[0][2]:
		solution.setConstraint(i, x[0][2][0] + x[1][2][0] - x[2][0][0] - x[2][1][0] - x[2][3][0] - x[2][4][0] - x[2][5][0] - x[2][6][0] + x[3][2][0] + x[4][2][0] + x[5][2][0] + x[6][2][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[0][3]:
		solution.setConstraint(i, x[0][3][0] + x[1][3][0] + x[2][3][0] - x[3][0][0] - x[3][1][0] - x[3][2][0] - x[3][4][0] - x[3][5][0] - x[3][6][0] + x[4][3][0] + x[5][3][0] + x[6][3][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[0][4]:
		solution.setConstraint(i, x[0][4][0] + x[1][4][0] + x[2][4][0] + x[3][4][0] - x[4][0][0] - x[4][1][0] - x[4][2][0] - x[4][3][0] - x[4][5][0] - x[4][6][0] + x[5][4][0] + x[6][4][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[0][5]:
		solution.setConstraint(i, x[0][5][0] + x[1][5][0] + x[2][5][0] + x[3][5][0] + x[4][5][0] - x[5][0][0] - x[5][1][0] - x[5][2][0] - x[5][3][0] - x[5][4][0] - x[5][6][0] + x[6][5][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[0][6]:
		solution.setConstraint(i, x[0][6][0] + x[1][6][0] + x[2][6][0] + x[3][6][0] + x[4][6][0] + x[5][6][0] - x[6][0][0] - x[6][1][0] - x[6][2][0] - x[6][3][0] - x[6][4][0] - x[6][5][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[1][0]:
		solution.setConstraint(i, -x[0][1][1] - x[0][2][1] - x[0][3][1] - x[0][4][1] - x[0][5][1] - x[0][6][1] + x[1][0][1] + x[2][0][1] + x[3][0][1] + x[4][0][1] + x[5][0][1] + x[6][0][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[1][1]:
		solution.setConstraint(i, x[0][1][1] - x[1][0][1] - x[1][2][1] - x[1][3][1] - x[1][4][1] - x[1][5][1] - x[1][6][1] + x[2][1][1] + x[3][1][1] + x[4][1][1] + x[5][1][1] + x[6][1][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[1][2]:
		solution.setConstraint(i, x[0][2][1] + x[1][2][1] - x[2][0][1] - x[2][1][1] - x[2][3][1] - x[2][4][1] - x[2][5][1] - x[2][6][1] + x[3][2][1] + x[4][2][1] + x[5][2][1] + x[6][2][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[1][3]:
		solution.setConstraint(i, x[0][3][1] + x[1][3][1] + x[2][3][1] - x[3][0][1] - x[3][1][1] - x[3][2][1] - x[3][4][1] - x[3][5][1] - x[3][6][1] + x[4][3][1] + x[5][3][1] + x[6][3][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[1][4]:
		solution.setConstraint(i, x[0][4][1] + x[1][4][1] + x[2][4][1] + x[3][4][1] - x[4][0][1] - x[4][1][1] - x[4][2][1] - x[4][3][1] - x[4][5][1] - x[4][6][1] + x[5][4][1] + x[6][4][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[1][5]:
		solution.setConstraint(i, x[0][5][1] + x[1][5][1] + x[2][5][1] + x[3][5][1] + x[4][5][1] - x[5][0][1] - x[5][1][1] - x[5][2][1] - x[5][3][1] - x[5][4][1] - x[5][6][1] + x[6][5][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[1][6]:
		solution.setConstraint(i, x[0][6][1] + x[1][6][1] + x[2][6][1] + x[3][6][1] + x[4][6][1] + x[5][6][1] - x[6][0][1] - x[6][1][1] - x[6][2][1] - x[6][3][1] - x[6][4][1] - x[6][5][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[2][0]:
		solution.setConstraint(i, -x[0][1][2] - x[0][2][2] - x[0][3][2] - x[0][4][2] - x[0][5][2] - x[0][6][2] + x[1][0][2] + x[2][0][2] + x[3][0][2] + x[4][0][2] + x[5][0][2] + x[6][0][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[2][1]:
		solution.setConstraint(i, x[0][1][2] - x[1][0][2] - x[1][2][2] - x[1][3][2] - x[1][4][2] - x[1][5][2] - x[1][6][2] + x[2][1][2] + x[3][1][2] + x[4][1][2] + x[5][1][2] + x[6][1][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[2][2]:
		solution.setConstraint(i, x[0][2][2] + x[1][2][2] - x[2][0][2] - x[2][1][2] - x[2][3][2] - x[2][4][2] - x[2][5][2] - x[2][6][2] + x[3][2][2] + x[4][2][2] + x[5][2][2] + x[6][2][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[2][3]:
		solution.setConstraint(i, x[0][3][2] + x[1][3][2] + x[2][3][2] - x[3][0][2] - x[3][1][2] - x[3][2][2] - x[3][4][2] - x[3][5][2] - x[3][6][2] + x[4][3][2] + x[5][3][2] + x[6][3][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[2][4]:
		solution.setConstraint(i, x[0][4][2] + x[1][4][2] + x[2][4][2] + x[3][4][2] - x[4][0][2] - x[4][1][2] - x[4][2][2] - x[4][3][2] - x[4][5][2] - x[4][6][2] + x[5][4][2] + x[6][4][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[2][5]:
		solution.setConstraint(i, x[0][5][2] + x[1][5][2] + x[2][5][2] + x[3][5][2] + x[4][5][2] - x[5][0][2] - x[5][1][2] - x[5][2][2] - x[5][3][2] - x[5][4][2] - x[5][6][2] + x[6][5][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R8[2][6]:
		solution.setConstraint(i, x[0][6][2] + x[1][6][2] + x[2][6][2] + x[3][6][2] + x[4][6][2] + x[5][6][2] - x[6][0][2] - x[6][1][2] - x[6][2][2] - x[6][3][2] - x[6][4][2] - x[6][5][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][3][0][0]:
		solution.setConstraint(i, 100*x[2][3][0] + v[0][2][0][0] + v[1][2][0][0] + u[2][0][0] - u[3][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][3][0][1]:
		solution.setConstraint(i, 100*x[2][3][0] + v[0][2][1][0] + v[1][2][1][0] + u[2][0][1] - u[3][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][3][1][0]:
		solution.setConstraint(i, 50*x[2][3][1] + v[0][2][0][1] + v[1][2][0][1] + u[2][1][0] - u[3][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][3][1][1]:
		solution.setConstraint(i, 50*x[2][3][1] + v[0][2][1][1] + v[1][2][1][1] + u[2][1][1] - u[3][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][3][2][0]:
		solution.setConstraint(i, 48*x[2][3][2] + v[0][2][0][2] + v[1][2][0][2] + u[2][2][0] - u[3][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][3][2][1]:
		solution.setConstraint(i, 48*x[2][3][2] + v[0][2][1][2] + v[1][2][1][2] + u[2][2][1] - u[3][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][4][0][0]:
		solution.setConstraint(i, 100*x[2][4][0] + v[0][2][0][0] + v[1][2][0][0] + u[2][0][0] - u[4][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][4][0][1]:
		solution.setConstraint(i, 100*x[2][4][0] + v[0][2][1][0] + v[1][2][1][0] + u[2][0][1] - u[4][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][4][1][0]:
		solution.setConstraint(i, 50*x[2][4][1] + v[0][2][0][1] + v[1][2][0][1] + u[2][1][0] - u[4][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][4][1][1]:
		solution.setConstraint(i, 50*x[2][4][1] + v[0][2][1][1] + v[1][2][1][1] + u[2][1][1] - u[4][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][4][2][0]:
		solution.setConstraint(i, 48*x[2][4][2] + v[0][2][0][2] + v[1][2][0][2] + u[2][2][0] - u[4][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][4][2][1]:
		solution.setConstraint(i, 48*x[2][4][2] + v[0][2][1][2] + v[1][2][1][2] + u[2][2][1] - u[4][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][5][0][0]:
		solution.setConstraint(i, 100*x[2][5][0] + v[0][2][0][0] + v[1][2][0][0] + u[2][0][0] - u[5][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][5][0][1]:
		solution.setConstraint(i, 100*x[2][5][0] + v[0][2][1][0] + v[1][2][1][0] + u[2][0][1] - u[5][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][5][1][0]:
		solution.setConstraint(i, 50*x[2][5][1] + v[0][2][0][1] + v[1][2][0][1] + u[2][1][0] - u[5][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][5][1][1]:
		solution.setConstraint(i, 50*x[2][5][1] + v[0][2][1][1] + v[1][2][1][1] + u[2][1][1] - u[5][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][5][2][0]:
		solution.setConstraint(i, 48*x[2][5][2] + v[0][2][0][2] + v[1][2][0][2] + u[2][2][0] - u[5][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][5][2][1]:
		solution.setConstraint(i, 48*x[2][5][2] + v[0][2][1][2] + v[1][2][1][2] + u[2][2][1] - u[5][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][6][0][0]:
		solution.setConstraint(i, 100*x[2][6][0] + v[0][2][0][0] + v[1][2][0][0] + u[2][0][0] - u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][6][0][1]:
		solution.setConstraint(i, 100*x[2][6][0] + v[0][2][1][0] + v[1][2][1][0] + u[2][0][1] - u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][6][1][0]:
		solution.setConstraint(i, 50*x[2][6][1] + v[0][2][0][1] + v[1][2][0][1] + u[2][1][0] - u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][6][1][1]:
		solution.setConstraint(i, 50*x[2][6][1] + v[0][2][1][1] + v[1][2][1][1] + u[2][1][1] - u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][6][2][0]:
		solution.setConstraint(i, 48*x[2][6][2] + v[0][2][0][2] + v[1][2][0][2] + u[2][2][0] - u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[2][6][2][1]:
		solution.setConstraint(i, 48*x[2][6][2] + v[0][2][1][2] + v[1][2][1][2] + u[2][2][1] - u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][2][0][0]:
		solution.setConstraint(i, 100*x[3][2][0] + v[0][3][0][0] + v[1][3][0][0] - u[2][0][0] + u[3][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][2][0][1]:
		solution.setConstraint(i, 100*x[3][2][0] + v[0][3][1][0] + v[1][3][1][0] - u[2][0][1] + u[3][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][2][1][0]:
		solution.setConstraint(i, 50*x[3][2][1] + v[0][3][0][1] + v[1][3][0][1] - u[2][1][0] + u[3][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][2][1][1]:
		solution.setConstraint(i, 50*x[3][2][1] + v[0][3][1][1] + v[1][3][1][1] - u[2][1][1] + u[3][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][2][2][0]:
		solution.setConstraint(i, 48*x[3][2][2] + v[0][3][0][2] + v[1][3][0][2] - u[2][2][0] + u[3][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][2][2][1]:
		solution.setConstraint(i, 48*x[3][2][2] + v[0][3][1][2] + v[1][3][1][2] - u[2][2][1] + u[3][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][4][0][0]:
		solution.setConstraint(i, 100*x[3][4][0] + v[0][3][0][0] + v[1][3][0][0] + u[3][0][0] - u[4][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][4][0][1]:
		solution.setConstraint(i, 100*x[3][4][0] + v[0][3][1][0] + v[1][3][1][0] + u[3][0][1] - u[4][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][4][1][0]:
		solution.setConstraint(i, 50*x[3][4][1] + v[0][3][0][1] + v[1][3][0][1] + u[3][1][0] - u[4][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][4][1][1]:
		solution.setConstraint(i, 50*x[3][4][1] + v[0][3][1][1] + v[1][3][1][1] + u[3][1][1] - u[4][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][4][2][0]:
		solution.setConstraint(i, 48*x[3][4][2] + v[0][3][0][2] + v[1][3][0][2] + u[3][2][0] - u[4][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][4][2][1]:
		solution.setConstraint(i, 48*x[3][4][2] + v[0][3][1][2] + v[1][3][1][2] + u[3][2][1] - u[4][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][5][0][0]:
		solution.setConstraint(i, 100*x[3][5][0] + v[0][3][0][0] + v[1][3][0][0] + u[3][0][0] - u[5][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][5][0][1]:
		solution.setConstraint(i, 100*x[3][5][0] + v[0][3][1][0] + v[1][3][1][0] + u[3][0][1] - u[5][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][5][1][0]:
		solution.setConstraint(i, 50*x[3][5][1] + v[0][3][0][1] + v[1][3][0][1] + u[3][1][0] - u[5][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][5][1][1]:
		solution.setConstraint(i, 50*x[3][5][1] + v[0][3][1][1] + v[1][3][1][1] + u[3][1][1] - u[5][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][5][2][0]:
		solution.setConstraint(i, 48*x[3][5][2] + v[0][3][0][2] + v[1][3][0][2] + u[3][2][0] - u[5][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][5][2][1]:
		solution.setConstraint(i, 48*x[3][5][2] + v[0][3][1][2] + v[1][3][1][2] + u[3][2][1] - u[5][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][6][0][0]:
		solution.setConstraint(i, 100*x[3][6][0] + v[0][3][0][0] + v[1][3][0][0] + u[3][0][0] - u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][6][0][1]:
		solution.setConstraint(i, 100*x[3][6][0] + v[0][3][1][0] + v[1][3][1][0] + u[3][0][1] - u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][6][1][0]:
		solution.setConstraint(i, 50*x[3][6][1] + v[0][3][0][1] + v[1][3][0][1] + u[3][1][0] - u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][6][1][1]:
		solution.setConstraint(i, 50*x[3][6][1] + v[0][3][1][1] + v[1][3][1][1] + u[3][1][1] - u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][6][2][0]:
		solution.setConstraint(i, 48*x[3][6][2] + v[0][3][0][2] + v[1][3][0][2] + u[3][2][0] - u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[3][6][2][1]:
		solution.setConstraint(i, 48*x[3][6][2] + v[0][3][1][2] + v[1][3][1][2] + u[3][2][1] - u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][2][0][0]:
		solution.setConstraint(i, 100*x[4][2][0] + v[0][4][0][0] + v[1][4][0][0] - u[2][0][0] + u[4][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][2][0][1]:
		solution.setConstraint(i, 100*x[4][2][0] + v[0][4][1][0] + v[1][4][1][0] - u[2][0][1] + u[4][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][2][1][0]:
		solution.setConstraint(i, 50*x[4][2][1] + v[0][4][0][1] + v[1][4][0][1] - u[2][1][0] + u[4][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][2][1][1]:
		solution.setConstraint(i, 50*x[4][2][1] + v[0][4][1][1] + v[1][4][1][1] - u[2][1][1] + u[4][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][2][2][0]:
		solution.setConstraint(i, 48*x[4][2][2] + v[0][4][0][2] + v[1][4][0][2] - u[2][2][0] + u[4][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][2][2][1]:
		solution.setConstraint(i, 48*x[4][2][2] + v[0][4][1][2] + v[1][4][1][2] - u[2][2][1] + u[4][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][3][0][0]:
		solution.setConstraint(i, 100*x[4][3][0] + v[0][4][0][0] + v[1][4][0][0] - u[3][0][0] + u[4][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][3][0][1]:
		solution.setConstraint(i, 100*x[4][3][0] + v[0][4][1][0] + v[1][4][1][0] - u[3][0][1] + u[4][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][3][1][0]:
		solution.setConstraint(i, 50*x[4][3][1] + v[0][4][0][1] + v[1][4][0][1] - u[3][1][0] + u[4][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][3][1][1]:
		solution.setConstraint(i, 50*x[4][3][1] + v[0][4][1][1] + v[1][4][1][1] - u[3][1][1] + u[4][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][3][2][0]:
		solution.setConstraint(i, 48*x[4][3][2] + v[0][4][0][2] + v[1][4][0][2] - u[3][2][0] + u[4][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][3][2][1]:
		solution.setConstraint(i, 48*x[4][3][2] + v[0][4][1][2] + v[1][4][1][2] - u[3][2][1] + u[4][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][5][0][0]:
		solution.setConstraint(i, 100*x[4][5][0] + v[0][4][0][0] + v[1][4][0][0] + u[4][0][0] - u[5][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][5][0][1]:
		solution.setConstraint(i, 100*x[4][5][0] + v[0][4][1][0] + v[1][4][1][0] + u[4][0][1] - u[5][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][5][1][0]:
		solution.setConstraint(i, 50*x[4][5][1] + v[0][4][0][1] + v[1][4][0][1] + u[4][1][0] - u[5][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][5][1][1]:
		solution.setConstraint(i, 50*x[4][5][1] + v[0][4][1][1] + v[1][4][1][1] + u[4][1][1] - u[5][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][5][2][0]:
		solution.setConstraint(i, 48*x[4][5][2] + v[0][4][0][2] + v[1][4][0][2] + u[4][2][0] - u[5][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][5][2][1]:
		solution.setConstraint(i, 48*x[4][5][2] + v[0][4][1][2] + v[1][4][1][2] + u[4][2][1] - u[5][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][6][0][0]:
		solution.setConstraint(i, 100*x[4][6][0] + v[0][4][0][0] + v[1][4][0][0] + u[4][0][0] - u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][6][0][1]:
		solution.setConstraint(i, 100*x[4][6][0] + v[0][4][1][0] + v[1][4][1][0] + u[4][0][1] - u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][6][1][0]:
		solution.setConstraint(i, 50*x[4][6][1] + v[0][4][0][1] + v[1][4][0][1] + u[4][1][0] - u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][6][1][1]:
		solution.setConstraint(i, 50*x[4][6][1] + v[0][4][1][1] + v[1][4][1][1] + u[4][1][1] - u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][6][2][0]:
		solution.setConstraint(i, 48*x[4][6][2] + v[0][4][0][2] + v[1][4][0][2] + u[4][2][0] - u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[4][6][2][1]:
		solution.setConstraint(i, 48*x[4][6][2] + v[0][4][1][2] + v[1][4][1][2] + u[4][2][1] - u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][2][0][0]:
		solution.setConstraint(i, 100*x[5][2][0] + v[0][5][0][0] + v[1][5][0][0] - u[2][0][0] + u[5][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][2][0][1]:
		solution.setConstraint(i, 100*x[5][2][0] + v[0][5][1][0] + v[1][5][1][0] - u[2][0][1] + u[5][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][2][1][0]:
		solution.setConstraint(i, 50*x[5][2][1] + v[0][5][0][1] + v[1][5][0][1] - u[2][1][0] + u[5][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][2][1][1]:
		solution.setConstraint(i, 50*x[5][2][1] + v[0][5][1][1] + v[1][5][1][1] - u[2][1][1] + u[5][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][2][2][0]:
		solution.setConstraint(i, 48*x[5][2][2] + v[0][5][0][2] + v[1][5][0][2] - u[2][2][0] + u[5][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][2][2][1]:
		solution.setConstraint(i, 48*x[5][2][2] + v[0][5][1][2] + v[1][5][1][2] - u[2][2][1] + u[5][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][3][0][0]:
		solution.setConstraint(i, 100*x[5][3][0] + v[0][5][0][0] + v[1][5][0][0] - u[3][0][0] + u[5][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][3][0][1]:
		solution.setConstraint(i, 100*x[5][3][0] + v[0][5][1][0] + v[1][5][1][0] - u[3][0][1] + u[5][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][3][1][0]:
		solution.setConstraint(i, 50*x[5][3][1] + v[0][5][0][1] + v[1][5][0][1] - u[3][1][0] + u[5][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][3][1][1]:
		solution.setConstraint(i, 50*x[5][3][1] + v[0][5][1][1] + v[1][5][1][1] - u[3][1][1] + u[5][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][3][2][0]:
		solution.setConstraint(i, 48*x[5][3][2] + v[0][5][0][2] + v[1][5][0][2] - u[3][2][0] + u[5][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][3][2][1]:
		solution.setConstraint(i, 48*x[5][3][2] + v[0][5][1][2] + v[1][5][1][2] - u[3][2][1] + u[5][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][4][0][0]:
		solution.setConstraint(i, 100*x[5][4][0] + v[0][5][0][0] + v[1][5][0][0] - u[4][0][0] + u[5][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][4][0][1]:
		solution.setConstraint(i, 100*x[5][4][0] + v[0][5][1][0] + v[1][5][1][0] - u[4][0][1] + u[5][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][4][1][0]:
		solution.setConstraint(i, 50*x[5][4][1] + v[0][5][0][1] + v[1][5][0][1] - u[4][1][0] + u[5][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][4][1][1]:
		solution.setConstraint(i, 50*x[5][4][1] + v[0][5][1][1] + v[1][5][1][1] - u[4][1][1] + u[5][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][4][2][0]:
		solution.setConstraint(i, 48*x[5][4][2] + v[0][5][0][2] + v[1][5][0][2] - u[4][2][0] + u[5][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][4][2][1]:
		solution.setConstraint(i, 48*x[5][4][2] + v[0][5][1][2] + v[1][5][1][2] - u[4][2][1] + u[5][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][6][0][0]:
		solution.setConstraint(i, 100*x[5][6][0] + v[0][5][0][0] + v[1][5][0][0] + u[5][0][0] - u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][6][0][1]:
		solution.setConstraint(i, 100*x[5][6][0] + v[0][5][1][0] + v[1][5][1][0] + u[5][0][1] - u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][6][1][0]:
		solution.setConstraint(i, 50*x[5][6][1] + v[0][5][0][1] + v[1][5][0][1] + u[5][1][0] - u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][6][1][1]:
		solution.setConstraint(i, 50*x[5][6][1] + v[0][5][1][1] + v[1][5][1][1] + u[5][1][1] - u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][6][2][0]:
		solution.setConstraint(i, 48*x[5][6][2] + v[0][5][0][2] + v[1][5][0][2] + u[5][2][0] - u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[5][6][2][1]:
		solution.setConstraint(i, 48*x[5][6][2] + v[0][5][1][2] + v[1][5][1][2] + u[5][2][1] - u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][2][0][0]:
		solution.setConstraint(i, 100*x[6][2][0] + v[0][6][0][0] + v[1][6][0][0] - u[2][0][0] + u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][2][0][1]:
		solution.setConstraint(i, 100*x[6][2][0] + v[0][6][1][0] + v[1][6][1][0] - u[2][0][1] + u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][2][1][0]:
		solution.setConstraint(i, 50*x[6][2][1] + v[0][6][0][1] + v[1][6][0][1] - u[2][1][0] + u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][2][1][1]:
		solution.setConstraint(i, 50*x[6][2][1] + v[0][6][1][1] + v[1][6][1][1] - u[2][1][1] + u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][2][2][0]:
		solution.setConstraint(i, 48*x[6][2][2] + v[0][6][0][2] + v[1][6][0][2] - u[2][2][0] + u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][2][2][1]:
		solution.setConstraint(i, 48*x[6][2][2] + v[0][6][1][2] + v[1][6][1][2] - u[2][2][1] + u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][3][0][0]:
		solution.setConstraint(i, 100*x[6][3][0] + v[0][6][0][0] + v[1][6][0][0] - u[3][0][0] + u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][3][0][1]:
		solution.setConstraint(i, 100*x[6][3][0] + v[0][6][1][0] + v[1][6][1][0] - u[3][0][1] + u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][3][1][0]:
		solution.setConstraint(i, 50*x[6][3][1] + v[0][6][0][1] + v[1][6][0][1] - u[3][1][0] + u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][3][1][1]:
		solution.setConstraint(i, 50*x[6][3][1] + v[0][6][1][1] + v[1][6][1][1] - u[3][1][1] + u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][3][2][0]:
		solution.setConstraint(i, 48*x[6][3][2] + v[0][6][0][2] + v[1][6][0][2] - u[3][2][0] + u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][3][2][1]:
		solution.setConstraint(i, 48*x[6][3][2] + v[0][6][1][2] + v[1][6][1][2] - u[3][2][1] + u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][4][0][0]:
		solution.setConstraint(i, 100*x[6][4][0] + v[0][6][0][0] + v[1][6][0][0] - u[4][0][0] + u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][4][0][1]:
		solution.setConstraint(i, 100*x[6][4][0] + v[0][6][1][0] + v[1][6][1][0] - u[4][0][1] + u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][4][1][0]:
		solution.setConstraint(i, 50*x[6][4][1] + v[0][6][0][1] + v[1][6][0][1] - u[4][1][0] + u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][4][1][1]:
		solution.setConstraint(i, 50*x[6][4][1] + v[0][6][1][1] + v[1][6][1][1] - u[4][1][1] + u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][4][2][0]:
		solution.setConstraint(i, 48*x[6][4][2] + v[0][6][0][2] + v[1][6][0][2] - u[4][2][0] + u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][4][2][1]:
		solution.setConstraint(i, 48*x[6][4][2] + v[0][6][1][2] + v[1][6][1][2] - u[4][2][1] + u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][5][0][0]:
		solution.setConstraint(i, 100*x[6][5][0] + v[0][6][0][0] + v[1][6][0][0] - u[5][0][0] + u[6][0][0] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][5][0][1]:
		solution.setConstraint(i, 100*x[6][5][0] + v[0][6][1][0] + v[1][6][1][0] - u[5][0][1] + u[6][0][1] <= 100 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][5][1][0]:
		solution.setConstraint(i, 50*x[6][5][1] + v[0][6][0][1] + v[1][6][0][1] - u[5][1][0] + u[6][1][0] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][5][1][1]:
		solution.setConstraint(i, 50*x[6][5][1] + v[0][6][1][1] + v[1][6][1][1] - u[5][1][1] + u[6][1][1] <= 50 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][5][2][0]:
		solution.setConstraint(i, 48*x[6][5][2] + v[0][6][0][2] + v[1][6][0][2] - u[5][2][0] + u[6][2][0] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R9[6][5][2][1]:
		solution.setConstraint(i, 48*x[6][5][2] + v[0][6][1][2] + v[1][6][1][2] - u[5][2][1] + u[6][2][1] <= 48 ? 0.0 : 1.0);
		i++;
		//subject to R10[0][0]:
		solution.setConstraint(i, x[0][2][0] + x[0][3][0] + x[0][4][0] + x[0][5][0] + x[0][6][0] + x[2][0][0] + x[3][0][0] + x[4][0][0] + x[5][0][0] + x[6][0][0] <= 2 ? 0.0 : 1.0);
		i++;
		//subject to R10[0][1]:
		solution.setConstraint(i, x[0][2][1] + x[0][3][1] + x[0][4][1] + x[0][5][1] + x[0][6][1] + x[2][0][1] + x[3][0][1] + x[4][0][1] + x[5][0][1] + x[6][0][1] <= 2 ? 0.0 : 1.0);
		i++;
		//subject to R10[0][2]:
		solution.setConstraint(i, x[0][2][2] + x[0][3][2] + x[0][4][2] + x[0][5][2] + x[0][6][2] + x[2][0][2] + x[3][0][2] + x[4][0][2] + x[5][0][2] + x[6][0][2] <= 2 ? 0.0 : 1.0);
		i++;
		//subject to R10[1][0]:
		solution.setConstraint(i, x[1][2][0] + x[1][3][0] + x[1][4][0] + x[1][5][0] + x[1][6][0] + x[2][1][0] + x[3][1][0] + x[4][1][0] + x[5][1][0] + x[6][1][0] <= 2 ? 0.0 : 1.0);
		i++;
		//subject to R10[1][1]:
		solution.setConstraint(i, x[1][2][1] + x[1][3][1] + x[1][4][1] + x[1][5][1] + x[1][6][1] + x[2][1][1] + x[3][1][1] + x[4][1][1] + x[5][1][1] + x[6][1][1] <= 2 ? 0.0 : 1.0);
		i++;
		//subject to R10[1][2]:
		solution.setConstraint(i, x[1][2][2] + x[1][3][2] + x[1][4][2] + x[1][5][2] + x[1][6][2] + x[2][1][2] + x[3][1][2] + x[4][1][2] + x[5][1][2] + x[6][1][2] <= 2 ? 0.0 : 1.0);
		i++;
		//subject to R11[0]:
		solution.setConstraint(i, x[0][2][0] + x[0][3][0] + x[0][4][0] + x[0][5][0] + x[0][6][0] + x[1][2][0] + x[1][3][0] + x[1][4][0] + x[1][5][0] + x[1][6][0] <= 1 ? 0.0 : 1.0);
		i++;
		//subject to R11[1]:
		solution.setConstraint(i, x[0][2][1] + x[0][3][1] + x[0][4][1] + x[0][5][1] + x[0][6][1] + x[1][2][1] + x[1][3][1] + x[1][4][1] + x[1][5][1] + x[1][6][1] <= 1 ? 0.0 : 1.0);
		i++;
		//subject to R11[2]:
		solution.setConstraint(i, x[0][2][2] + x[0][3][2] + x[0][4][2] + x[0][5][2] + x[0][6][2] + x[1][2][2] + x[1][3][2] + x[1][4][2] + x[1][5][2] + x[1][6][2] <= 1 ? 0.0 : 1.0);
		i++;
		//subject to R12[0][2]:
		solution.setConstraint(i, x[2][0][0] + x[2][1][0] + x[2][3][0] + x[2][4][0] + x[2][5][0] + x[2][6][0] - y[2][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[0][3]:
		solution.setConstraint(i, x[3][0][0] + x[3][1][0] + x[3][2][0] + x[3][4][0] + x[3][5][0] + x[3][6][0] - y[3][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[0][4]:
		solution.setConstraint(i, x[4][0][0] + x[4][1][0] + x[4][2][0] + x[4][3][0] + x[4][5][0] + x[4][6][0] - y[4][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[0][5]:
		solution.setConstraint(i, x[5][0][0] + x[5][1][0] + x[5][2][0] + x[5][3][0] + x[5][4][0] + x[5][6][0] - y[5][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[0][6]:
		solution.setConstraint(i, x[6][0][0] + x[6][1][0] + x[6][2][0] + x[6][3][0] + x[6][4][0] + x[6][5][0] - y[6][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[1][2]:
		solution.setConstraint(i, x[2][0][1] + x[2][1][1] + x[2][3][1] + x[2][4][1] + x[2][5][1] + x[2][6][1] - y[2][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[1][3]:
		solution.setConstraint(i, x[3][0][1] + x[3][1][1] + x[3][2][1] + x[3][4][1] + x[3][5][1] + x[3][6][1] - y[3][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[1][4]:
		solution.setConstraint(i, x[4][0][1] + x[4][1][1] + x[4][2][1] + x[4][3][1] + x[4][5][1] + x[4][6][1] - y[4][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[1][5]:
		solution.setConstraint(i, x[5][0][1] + x[5][1][1] + x[5][2][1] + x[5][3][1] + x[5][4][1] + x[5][6][1] - y[5][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[1][6]:
		solution.setConstraint(i, x[6][0][1] + x[6][1][1] + x[6][2][1] + x[6][3][1] + x[6][4][1] + x[6][5][1] - y[6][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[2][2]:
		solution.setConstraint(i, x[2][0][2] + x[2][1][2] + x[2][3][2] + x[2][4][2] + x[2][5][2] + x[2][6][2] - y[2][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[2][3]:
		solution.setConstraint(i, x[3][0][2] + x[3][1][2] + x[3][2][2] + x[3][4][2] + x[3][5][2] + x[3][6][2] - y[3][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[2][4]:
		solution.setConstraint(i, x[4][0][2] + x[4][1][2] + x[4][2][2] + x[4][3][2] + x[4][5][2] + x[4][6][2] - y[4][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[2][5]:
		solution.setConstraint(i, x[5][0][2] + x[5][1][2] + x[5][2][2] + x[5][3][2] + x[5][4][2] + x[5][6][2] - y[5][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R12[2][6]:
		solution.setConstraint(i, x[6][0][2] + x[6][1][2] + x[6][2][2] + x[6][3][2] + x[6][4][2] + x[6][5][2] - y[6][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R13[0]:
		solution.setConstraint(i, -3*z[0] + f[0][0] + f[0][1] + f[0][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R13[1]:
		solution.setConstraint(i, -3*z[1] + f[1][0] + f[1][1] + f[1][2] <= 0 ? 0.0 : 1.0);
		i++;
		//subject to R14[0][0]:
		solution.setConstraint(i, x[0][2][0] + x[0][3][0] + x[0][4][0] + x[0][5][0] + x[0][6][0] - f[0][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R14[0][1]:
		solution.setConstraint(i, x[0][2][1] + x[0][3][1] + x[0][4][1] + x[0][5][1] + x[0][6][1] - f[0][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R14[0][2]:
		solution.setConstraint(i, x[0][2][2] + x[0][3][2] + x[0][4][2] + x[0][5][2] + x[0][6][2] - f[0][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R14[1][0]:
		solution.setConstraint(i, x[1][2][0] + x[1][3][0] + x[1][4][0] + x[1][5][0] + x[1][6][0] - f[1][0] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R14[1][1]:
		solution.setConstraint(i, x[1][2][1] + x[1][3][1] + x[1][4][1] + x[1][5][1] + x[1][6][1] - f[1][1] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R14[1][2]:
		solution.setConstraint(i, x[1][2][2] + x[1][3][2] + x[1][4][2] + x[1][5][2] + x[1][6][2] - f[1][2] == 0 ? 0.0 : 1.0);
		i++;
		//subject to R15[0]:
		solution.setConstraint(i, 12.5*x[0][0][0] + 14.2*x[0][1][0] + 11.3*x[0][2][0] + 10.12*x[0][3][0] + 10.98*x[0][4][0] + 15.03*x[0][5][0] + 9.8*x[0][6][0] + 12.5*x[1][0][0] + 14.2*x[1][1][0] + 11.3*x[1][2][0] + 10.12*x[1][3][0] + 10.98*x[1][4][0] + 15.03*x[1][5][0] + 9.8*x[1][6][0] + 12.5*x[2][0][0] + 14.2*x[2][1][0] + 11.3*x[2][2][0] + 10.12*x[2][3][0] + 10.98*x[2][4][0] + 15.03*x[2][5][0] + 9.8*x[2][6][0] + 12.5*x[3][0][0] + 14.2*x[3][1][0] + 11.3*x[3][2][0] + 10.12*x[3][3][0] + 10.98*x[3][4][0] + 15.03*x[3][5][0] + 9.8*x[3][6][0] + 12.5*x[4][0][0] + 14.2*x[4][1][0] + 11.3*x[4][2][0] + 10.12*x[4][3][0] + 10.98*x[4][4][0] + 15.03*x[4][5][0] + 9.8*x[4][6][0] + 12.5*x[5][0][0] + 14.2*x[5][1][0] + 11.3*x[5][2][0] + 10.12*x[5][3][0] + 10.98*x[5][4][0] + 15.03*x[5][5][0] + 9.8*x[5][6][0] + 12.5*x[6][0][0] + 14.2*x[6][1][0] + 11.3*x[6][2][0] + 10.12*x[6][3][0] + 10.98*x[6][4][0] + 15.03*x[6][5][0] + 9.8*x[6][6][0] <= 72 ? 0.0 : 1.0);
		i++;
		//subject to R15[1]:
		solution.setConstraint(i, 12.5*x[0][0][1] + 14.2*x[0][1][1] + 11.3*x[0][2][1] + 10.12*x[0][3][1] + 10.98*x[0][4][1] + 15.03*x[0][5][1] + 9.8*x[0][6][1] + 12.5*x[1][0][1] + 14.2*x[1][1][1] + 11.3*x[1][2][1] + 10.12*x[1][3][1] + 10.98*x[1][4][1] + 15.03*x[1][5][1] + 9.8*x[1][6][1] + 12.5*x[2][0][1] + 14.2*x[2][1][1] + 11.3*x[2][2][1] + 10.12*x[2][3][1] + 10.98*x[2][4][1] + 15.03*x[2][5][1] + 9.8*x[2][6][1] + 12.5*x[3][0][1] + 14.2*x[3][1][1] + 11.3*x[3][2][1] + 10.12*x[3][3][1] + 10.98*x[3][4][1] + 15.03*x[3][5][1] + 9.8*x[3][6][1] + 12.5*x[4][0][1] + 14.2*x[4][1][1] + 11.3*x[4][2][1] + 10.12*x[4][3][1] + 10.98*x[4][4][1] + 15.03*x[4][5][1] + 9.8*x[4][6][1] + 12.5*x[5][0][1] + 14.2*x[5][1][1] + 11.3*x[5][2][1] + 10.12*x[5][3][1] + 10.98*x[5][4][1] + 15.03*x[5][5][1] + 9.8*x[5][6][1] + 12.5*x[6][0][1] + 14.2*x[6][1][1] + 11.3*x[6][2][1] + 10.12*x[6][3][1] + 10.98*x[6][4][1] + 15.03*x[6][5][1] + 9.8*x[6][6][1] <= 72 ? 0.0 : 1.0);
		i++;
		//subject to R16[2]:
		solution.setConstraint(i, 9.3*x[0][0][2] + 3.8*x[0][1][2] + 8.6*x[0][2][2] + 5.9*x[0][3][2] + 8.5*x[0][4][2] + 4.8*x[0][5][2] + 6.02*x[0][6][2] + 9.3*x[1][0][2] + 3.8*x[1][1][2] + 8.6*x[1][2][2] + 5.9*x[1][3][2] + 8.5*x[1][4][2] + 4.8*x[1][5][2] + 6.02*x[1][6][2] + 9.3*x[2][0][2] + 3.8*x[2][1][2] + 8.6*x[2][2][2] + 5.9*x[2][3][2] + 8.5*x[2][4][2] + 4.8*x[2][5][2] + 6.02*x[2][6][2] + 9.3*x[3][0][2] + 3.8*x[3][1][2] + 8.6*x[3][2][2] + 5.9*x[3][3][2] + 8.5*x[3][4][2] + 4.8*x[3][5][2] + 6.02*x[3][6][2] + 9.3*x[4][0][2] + 3.8*x[4][1][2] + 8.6*x[4][2][2] + 5.9*x[4][3][2] + 8.5*x[4][4][2] + 4.8*x[4][5][2] + 6.02*x[4][6][2] + 9.3*x[5][0][2] + 3.8*x[5][1][2] + 8.6*x[5][2][2] + 5.9*x[5][3][2] + 8.5*x[5][4][2] + 4.8*x[5][5][2] + 6.02*x[5][6][2] + 9.3*x[6][0][2] + 3.8*x[6][1][2] + 8.6*x[6][2][2] + 5.9*x[6][3][2] + 8.5*x[6][4][2] + 4.8*x[6][5][2] + 6.02*x[6][6][2] <= 72 ? 0.0 : 1.0);
		
		solution.setObjective(0, obj);
	}

	@Override
	public Solution newSolution() {
		Solution solution = new Solution(this.numberOfVariables, this.numberOfObjectives, this.numberOfConstraints);
		for(int i = 0; i < 126; i++)
			solution.setVariable(i, EncodingUtils.newInt(0, 100000));
		for(int i = 126; i < 302; i++)
			solution.setVariable(i, EncodingUtils.newBinaryInt(0, 1));
		return solution;
	}
}
