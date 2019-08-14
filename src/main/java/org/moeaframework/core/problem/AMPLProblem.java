package org.moeaframework.core.problem;

import java.io.File;

import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import com.ampl.AMPL;
import com.ampl.DataFrame;
import com.ampl.Environment;
import com.ampl.Parameter;
import com.ampl.ParameterMap;

public class AMPLProblem implements Problem {
	private AMPL ampl = null;
	
	private ParameterMap parameters = null;
	
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
	
	public AMPLProblem(File amplModel) {
		this(amplModel, null);
	}
	
	public AMPLProblem(File amplModel, File amplData) {
		ClassLoader loader = AMPLProblem.class.getClassLoader();
		File amplFile = new File(loader.getResource("natives/ampl/ampl.exe").getPath());
		Environment env = new Environment(amplFile.getParent());
		this.ampl = new AMPL(env);
		try {
			ampl.read(amplModel.getPath());
			if (amplData != null) {
				ampl.readData(amplData.getPath());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		this.parameters    = (ParameterMap) ampl.getParameters();
		for(Parameter var : this.parameters) {
			DataFrame df = var.getValues();
			System.out.println(df);
		}	
		ampl.solve();
		this.numberOfVariables   = this.parameters.size();
		this.numberOfObjectives  = 1;
		this.numberOfConstraints = 0;		
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
	@SuppressWarnings("deprecation")
	@Override
	protected void finalize() throws Throwable {
		if (!isClosed) {
			close();
		}
		this.ampl.close();
		super.finalize();
	}

	@Override
	public void close() {
		isClosed = true;
	}

}
