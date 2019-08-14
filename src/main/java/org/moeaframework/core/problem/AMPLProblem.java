package org.moeaframework.core.problem;

import java.io.File;

import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import com.ampl.AMPL;
import com.ampl.Constraint;
import com.ampl.EntityMap;
import com.ampl.Environment;
import com.ampl.Objective;
import com.ampl.Variable;

public class AMPLProblem implements Problem {
	private AMPL ampl = null;
	
	private int numberOfVariables   = -1;
	private int numberOfObjectives  = -1;
	private int numberOfConstraints = -1;
	/**
	 * {@code true} if the {@code close()} method has been invoked; {@code 
	 * false} otherwise.
	 */
	private boolean isClosed;
	
	@Override
	public String getName() {
		return AMPLProblem.class.getName();
	}

	@Override
	public int getNumberOfVariables() {
		return this.numberOfVariables;
	}

	@Override
	public int getNumberOfObjectives() {
		return this.numberOfObjectives;
	}

	@Override
	public int getNumberOfConstraints() {
		return this.numberOfConstraints;
	}
	
	public AMPLProblem(File amplModel, File amplData) {
		ClassLoader loader = AMPLProblem.class.getClassLoader();
		File amplFile = new File(loader.getResource("natives/ampl/ampl.exe").getPath());
		Environment env = new Environment(amplFile.getParent());
		this.ampl = new AMPL(env);
		try {
			ampl.read(amplModel.getPath());
			ampl.readData(amplData.getPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		EntityMap<Variable> amplVariables      = ampl.getVariables();
		EntityMap<Objective> amplObjectives    = ampl.getObjectives();
		EntityMap<Constraint> amplConstraints  = ampl.getConstraints();
		
		this.numberOfVariables   = amplVariables.size();
		this.numberOfObjectives  = amplObjectives.size();
		this.numberOfConstraints = amplConstraints.size();
	}
	
	@Override
	public void evaluate(Solution solution) {
		
	}

	@Override
	public Solution newSolution() {
		return null;
	}

	/**
	 * Calls {@code close()} if this problem has not yet been closed prior to
	 * finalization.
	 */
	@Override
	protected void finalize() throws Throwable {
		if (!isClosed) {
			close();
		}
		
		super.finalize();
	}

	@Override
	public void close() {
		isClosed = true;
	}

}
