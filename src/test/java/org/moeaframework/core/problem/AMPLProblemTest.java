/* Copyright 2009-2018 David Hadka
 *
 * This file is part of the MOEA Framework.
 *
 * The MOEA Framework is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * The MOEA Framework is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the MOEA Framework.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.moeaframework.core.problem;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.moeaframework.Executor;

/**
 * Tests the {@link AMPLProblem} class without the need for an external
 * executable.  No error conditions are tested.
 */
public class AMPLProblemTest {
	
	private AMPLProblem problem;	
	
	@Before
	public void setUp() throws IOException {
		problem = new AMPLProblem(302, 1, 407);
		Executor executor = new Executor().withProblem(problem).distributeOnAllCores()
				.withProperty("populationSize", 100000)
				.withProperty("operator", "sbx+hux+pm+bf").withMaxEvaluations(100000).withAlgorithm("GA");
		executor.run();		
	}
	
	@Test
	public void testNormalUse() throws Exception {
	}	
}
