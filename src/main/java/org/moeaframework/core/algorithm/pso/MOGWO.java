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
package org.moeaframework.core.algorithm.pso;

import org.moeaframework.core.population.EpsilonBoxDominanceArchive;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.math4.random.RandomUtils;
import org.apache.commons.math4.util.FastMath;
import org.moeaframework.core.PRNG;
import org.moeaframework.core.Problem;
import org.moeaframework.core.Solution;
import org.moeaframework.core.Variation;
import org.moeaframework.core.algorithm.AlgorithmInitializationException;
import org.moeaframework.core.comparator.CrowdingComparator;
import org.moeaframework.core.comparator.ParetoDominanceComparator;
import org.moeaframework.core.fitness.CrowdingDistanceFitnessEvaluator;
import org.moeaframework.core.fitness.FitnessBasedArchive;
import org.moeaframework.core.variable.RealVariable;
import org.moeaframework.util.SolutionUtils;

/**
 * Implementation of MOGWO, a multi-objective particle swarm optimizer (MOPSO), 
 * based on the Grey Wolf behavior
 * <p>
 * References:
 * <ol>
 *   <li>Seyedali Mirjalili, Shahrzad Saremi, Seyed Mohammad Mirjalili,
 *   	 Leandro dos S. Coelho (2016).  Multi-objective grey wolf optimizer: A novel algorithm for 
 *   	 multi-criterion optimization.  Expert Systems With Applications 47 (2016) 106–119.
 *	 </li>
 * </ol>
 */
public class MOGWO extends AbstractPSOAlgorithm {
	/**
	 * The uniform mutation operator.
	 */
	private Variation uniformMutation;
	
	/**
	 * The non-uniform mutation operator.
	 */
	private Variation nonUniformMutation;
	
	// MOGWO's additional fields
	private int        		 maxEvaluations; 
	private double    decreasingGlobalAccel; 
	private double[][] deltaPackNewVelocity;
	private double[][]  betaPackNewVelocity;
	private double[][] alphaPackNewVelocity;
	
	/**
	 * Constructs a new abstract MOGWO algorithm.
	 * 
	 * @param problem the problem
	 * @param swarmSize the number of particles
	 * @param leaderSize the number of leaders
	 * @param epsilons the &epsilon;-values used in the external archive
	 * @param variation the mutation operator
	 * 
	 */
	public MOGWO(Problem problem, int swarmSize, int leaderSize,
			double[] epsilons, double mutationProbability,
			double mutationPerturbation, int maxIterations) {
		super(problem, swarmSize, leaderSize, new CrowdingComparator(),
				new ParetoDominanceComparator(),
				new FitnessBasedArchive(new CrowdingDistanceFitnessEvaluator(), leaderSize),
				new EpsilonBoxDominanceArchive(epsilons),
				null);
		this.uniformMutation = new UniformMutation(mutationProbability,
				mutationPerturbation);
		this.nonUniformMutation = new NonUniformMutation(mutationProbability,
				mutationPerturbation, maxIterations);

		this.maxEvaluations = maxIterations;
		// a=2-it*((2)/MaxIt);
		this.decreasingGlobalAccel = 2.0 - ((double) this.getNumberOfEvaluations() * (2.0 / (double) this.maxEvaluations));
		// MOGWO uses three velocity-like arrays (alpha, beta, delta).
		this.deltaPackNewVelocity = new double[swarmSize][problem.getNumberOfVariables()];
		this.betaPackNewVelocity  = new double[swarmSize][problem.getNumberOfVariables()];
		this.alphaPackNewVelocity = new double[swarmSize][problem.getNumberOfVariables()];  
	}
	
	/**
	 * Update the speed of an individual particle.
	 * 
	 * @param i the index of the particle
	 */
	@Override
	protected void updateVelocity(int i) {
		RandomUtils.DataGenerator rng = RandomUtils.createDataGenerator(PRNG.getRandom());
		
		/*
        clear rep2
        clear rep3
        */
		FitnessBasedArchive secondLeaders = new FitnessBasedArchive(new CrowdingDistanceFitnessEvaluator(), leaderSize);
		FitnessBasedArchive thirdLeaders  = new FitnessBasedArchive(new CrowdingDistanceFitnessEvaluator(), leaderSize);
        
        /*
        % Choose the alpha, beta, and delta grey wolves
        Delta=SelectLeader(rep,beta);
        Beta=SelectLeader(rep,beta);
        Alpha=SelectLeader(rep,beta);
        */
		Solution delta = selectLeader();
		Solution beta  = selectLeader();		
		Solution alpha = selectLeader();
        
        /*
        % If there are less than three solutions in the least crowded
        % hypercube, the second least crowded hypercube is also found
        % to choose other leaders from.
        if size(rep,1)>1
            counter=0;
            for newi=1:size(rep,1)
                if sum(Delta.Position~=rep(newi).Position)~=0
                    counter=counter+1;
                    rep2(counter,1)=rep(newi);
                end
            end
            Beta=SelectLeader(rep2,beta);
        end
        */
        if (leaders.size() > 1) {
			for (int r = 0; r < leaders.size(); r++) {
				Solution possiblePreyToHunt = leaders.get(r);
				for (int j = 0; j < problem.getNumberOfVariables(); j++) {					
					if (((RealVariable) delta.getVariable(j)).getValue() != ((RealVariable) possiblePreyToHunt.getVariable(j)).getValue())
						secondLeaders.add(possiblePreyToHunt);					
				}				
			}
			beta = selectLeader(secondLeaders);
		}
		
        /*
        % This scenario is the same if the second least crowded hypercube
        % has one solution, so the delta leader should be chosen from the
        % third least crowded hypercube.
        if size(rep,1)>2
            counter=0;
            for newi=1:size(rep2,1)
                if sum(Beta.Position~=rep2(newi).Position)~=0
                    counter=counter+1;
                    rep3(counter,1)=rep2(newi);
                end
            end
            Alpha=SelectLeader(rep3,beta);
        end
        */
        if (leaders.size() > 2) {
			for (int r = 0; r < secondLeaders.size(); r++) {
				Solution possiblePreyToHunt = secondLeaders.get(r);
				for (int j = 0; j < problem.getNumberOfVariables(); j++) {
					if(((RealVariable) beta.getVariable(j)).getValue() != ((RealVariable) possiblePreyToHunt.getVariable(j)).getValue())
						thirdLeaders.add(possiblePreyToHunt);					
				}				
			}
			alpha = selectLeader(thirdLeaders);
		}
		for (int j = 0; j < problem.getNumberOfVariables(); j++) {	
			/*
			% Eq.(3.4) in the paper
			c=2.*rand(1, nVar);
			% Eq.(3.1) in the paper
			D=abs(c.*Delta.Position-particle(i).Position);
			% Eq.(3.3) in the paper
			A=2.*a.*rand(1, nVar)-a;
			% Eq.(3.8) in the paper
			X1=Delta.Position-A.*abs(D);
			*/
			double impulseOfDeltaWolf = 2.0 * rng.nextUniform(0.0, 1.0);		
			double distanceBetweenDeltaWolfAndPrey = FastMath.abs((impulseOfDeltaWolf * ((RealVariable) delta.getVariable(j)).getValue()) - ((RealVariable) particles[i].getVariable(j)).getValue());
			double accelerationOfDeltaWolf = (2.0 * this.decreasingGlobalAccel * rng.nextUniform(0.0, 1.0)) - this.decreasingGlobalAccel;
			this.deltaPackNewVelocity[i][j] = ((RealVariable) delta.getVariable(j)).getValue() - (accelerationOfDeltaWolf * FastMath.abs(distanceBetweenDeltaWolfAndPrey));
									
			/*
			% Eq.(3.4) in the paper
			c=2.*rand(1, nVar);
			% Eq.(3.1) in the paper
			D=abs(c.*Beta.Position-particle(i).Position);
			% Eq.(3.3) in the paper
			A=2.*a.*rand()-a;
			% Eq.(3.9) in the paper
			X2=Beta.Position-A.*abs(D);
			*/
			double impulseOfBetaWolf  = 2.0 * rng.nextUniform(0.0, 1.0);
			double distanceBetweenBetaWolfAndPrey  = FastMath.abs((impulseOfBetaWolf * ((RealVariable) beta.getVariable(j)).getValue())  - ((RealVariable) particles[i].getVariable(j)).getValue());
			double accelerationOfBetaWolf  = (2.0 * this.decreasingGlobalAccel * rng.nextUniform(0.0, 1.0)) - this.decreasingGlobalAccel;
			this.betaPackNewVelocity[i][j] = ((RealVariable) beta.getVariable(j)).getValue()  - (accelerationOfBetaWolf  *  FastMath.abs(distanceBetweenBetaWolfAndPrey));						
			
			/*
			% Eq.(3.4) in the paper
			c=2.*rand(1, nVar);
			% Eq.(3.1) in the paper
			D=abs(c.*Alpha.Position-particle(i).Position);
			% Eq.(3.3) in the paper
			A=2.*a.*rand()-a;
			% Eq.(3.10) in the paper
			X3=Alpha.Position-A.*abs(D);
			*/
			double impulseOfAlphaWolf = 2.0 * rng.nextUniform(0.0, 1.0);
			double distanceBetweenAlphaWolfAndPrey = FastMath.abs((impulseOfAlphaWolf * ((RealVariable) alpha.getVariable(j)).getValue()) - ((RealVariable) particles[i].getVariable(j)).getValue());
			double accelerationOfAlphaWolf = (2.0 * this.decreasingGlobalAccel * rng.nextUniform(0.0, 1.0)) - this.decreasingGlobalAccel;
			this.alphaPackNewVelocity[i][j] = ((RealVariable) alpha.getVariable(j)).getValue() - (accelerationOfAlphaWolf * FastMath.abs(distanceBetweenAlphaWolfAndPrey));
			
			double w = PRNG.nextDouble(0.0, 1.0);
			velocities[i][j] = w * velocities[i][j] + (this.deltaPackNewVelocity[i][j] + this.betaPackNewVelocity[i][j] + this.alphaPackNewVelocity[i][j])/3.0;
		}
	}
	
	/**
	 * Update the position of an individual particle.
	 * 
	 * @param i the index of the particle
	 */
	@Override
	protected void updatePosition(int i) {
		Solution parent = particles[i];
		Solution offspring = parent.copy();
		
		for (int j = 0; j < problem.getNumberOfVariables(); j++) {
			RealVariable variable = (RealVariable)offspring.getVariable(j);
			/*
	        % Eq.(3.11) in the paper
	        particle(i).Position=(X1+X2+X3)./3;
	        */
			double position = (this.deltaPackNewVelocity[i][j] + this.betaPackNewVelocity[i][j] + this.alphaPackNewVelocity[i][j])/3.0;
			
			if (position < variable.getLowerBound()) {
				position = variable.getLowerBound();
				velocities[i][j] *= -1;
			} else if (position > variable.getUpperBound()) {
				position = variable.getUpperBound();
				velocities[i][j] *= -1;
			}
			
			variable.setValue(position);
		}
		
		particles[i] = offspring;
	}
	
	/**
	 * Randomly select a leader.
	 * 
	 * @return the selected leader
	 */
	protected Solution selectLeader(FitnessBasedArchive rep) {
		Solution leader1 = rep.get(PRNG.nextInt(rep.size()));
		Solution leader2 = rep.get(PRNG.nextInt(rep.size()));
		int flag = leaderComparator.compare(leader1, leader2);
		
		if (flag < 0) {
			return leader1;
		} else if (flag > 0) {
			return leader2;
		} else if (PRNG.nextBoolean()) {
			return leader1;
		} else {
			return leader2;
		}
	}

	@Override
	protected void iterate() {
		// % Eq.(3.3) in the paper
		// Linearly decrease Global Acceleration from 2 to zero over the course of iterations
		this.decreasingGlobalAccel = 2.0 - ((double) this.getNumberOfEvaluations() * (2.0 / (double) this.maxEvaluations));		
		
		updateVelocities();
		updatePositions();
		mutate();
		
		evaluateAll(particles);
		
		updateLocalBest();
		leaders.addAll(particles);
		leaders.update();
		
		if (archive != null) {
			archive.addAll(particles);
		}
	}	
	
	@Override
	protected void mutate(int i) {
		if (i % 3 == 0) {
			particles[i] = nonUniformMutation.evolve(new Solution[] {
					particles[i] })[0];
		} else if (i % 3 == 1) {
			particles[i] = uniformMutation.evolve(new Solution[] {
					particles[i] })[0];
		}
	}
	
	/**
	 * The non-uniform mutation operator.
	 */
	private class NonUniformMutation implements Variation {
		
		private final double probability;
		
		private final double perturbation;
		
		private final int maxIterations;
		
		public NonUniformMutation(double probability, double perturbation,
				int maxIterations) {
			super();
			this.probability = probability;
			this.perturbation = perturbation;
			this.maxIterations = maxIterations;
		}

		@Override
		public int getArity() {
			return 1;
		}

		@Override
		public Solution[] evolve(Solution[] parents) {
			Solution offspring = parents[0].copy();
			
			for (int i = 0; i < offspring.getNumberOfVariables(); i++) {
				if (PRNG.nextDouble() < getProbability()) {
					RealVariable variable = (RealVariable)offspring.getVariable(i);
					double value = variable.getValue();
					
					if (PRNG.nextBoolean()) {
						value += getDelta(variable.getUpperBound() - value);
					} else {
						value += getDelta(variable.getLowerBound() - value);
					}

					if (value < variable.getLowerBound()) {
						value = variable.getLowerBound();
					} else if (value > variable.getUpperBound()) {
						value = variable.getUpperBound();
					}
					
					variable.setValue(value);
				}
			}
			
			return new Solution[] { offspring };
		}
		
		public double getDelta(double difference) {
			int currentIteration = getNumberOfEvaluations() / swarmSize;
			double fraction = currentIteration / (double)getMaxIterations();
			
			return difference * (1.0 - Math.pow(PRNG.nextDouble(), 
					Math.pow(1.0 - fraction, getPerturbation())));
		}

		public double getProbability() {
			return probability;
		}

		public double getPerturbation() {
			return perturbation;
		}

		public int getMaxIterations() {
			return maxIterations;
		}
	}
	
	/**
	 * The uniform mutation operator.
	 */
	private class UniformMutation implements Variation {
		
		private final double probability;
		
		private final double perturbation;
		
		public UniformMutation(double probability, double perturbation) {
			super();
			this.probability = probability;
			this.perturbation = perturbation;
		}

		@Override
		public int getArity() {
			return 1;
		}

		@Override
		public Solution[] evolve(Solution[] parents) {
			Solution offspring = parents[0].copy();
			
			for (int i = 0; i < offspring.getNumberOfVariables(); i++) {
				if (PRNG.nextDouble() < getProbability()) {
					RealVariable variable = (RealVariable)offspring.getVariable(i);
					double value = variable.getValue();
					
					value += (PRNG.nextDouble() - 0.5) * getPerturbation();
					
					if (value < variable.getLowerBound()) {
						value = variable.getLowerBound();
					} else if (value > variable.getUpperBound()) {
						value = variable.getUpperBound();
					}
					
					variable.setValue(value);
				}
			}
			
			return new Solution[] { offspring };
		}

		public double getProbability() {
			return probability;
		}

		public double getPerturbation() {
			return perturbation;
		}		
	}

	public Variation getUniformMutation() {
		return uniformMutation;
	}

	public Variation getNonUniformMutation() {
		return nonUniformMutation;
	}
	
	@Override
	public Serializable getState() throws NotSerializableException {
		if (!isInitialized()) {
			throw new AlgorithmInitializationException(this, 
					"algorithm not initialized");
		}

		List<Solution> particlesList = SolutionUtils.toList(particles);
		List<Solution> localBestParticlesList = SolutionUtils.toList(localBestParticles);
		List<Solution> leadersList = SolutionUtils.toList(leaders);
		List<Solution> archiveList = archive == null ? null : SolutionUtils.toList(archive);
		double[][] velocitiesClone = new double[velocities.length][];
		NonUniformMutation nonUniformMutation = new NonUniformMutation(((NonUniformMutation)getNonUniformMutation()).getProbability(), ((NonUniformMutation)getNonUniformMutation()).getPerturbation(), 
				((NonUniformMutation)getNonUniformMutation()).getMaxIterations());
		UniformMutation uniformMutation = new UniformMutation(((UniformMutation)getUniformMutation()).getProbability(), ((UniformMutation)getUniformMutation()).getPerturbation());
		
		for (int i = 0; i < velocities.length; i++) {
			velocitiesClone[i] = velocities[i].clone();
		}

		double[][] x1 = new double[this.deltaPackNewVelocity.length][];

		for (int i = 0; i < this.deltaPackNewVelocity.length; i++) {
			x1[i] = this.deltaPackNewVelocity[i].clone();
		}

		double[][] x2 = new double[this.betaPackNewVelocity.length][];

		for (int i = 0; i < this.betaPackNewVelocity.length; i++) {
			x2[i] = this.betaPackNewVelocity[i].clone();
		}

		double[][] x3 = new double[this.alphaPackNewVelocity.length][];

		for (int i = 0; i < this.alphaPackNewVelocity.length; i++) {
			x3[i] = this.alphaPackNewVelocity[i].clone();
		}
		
		return new MOGWOAlgorithmState(getNumberOfEvaluations(),
				particlesList, localBestParticlesList, leadersList,
				archiveList, velocitiesClone, this.maxEvaluations, this.decreasingGlobalAccel, 
				x1, x2, x3, nonUniformMutation, uniformMutation);
	}

	@Override
	public void setState(Object objState) throws NotSerializableException {
		super.initialize();

		MOGWOAlgorithmState state = (MOGWOAlgorithmState)objState;

		numberOfEvaluations = state.getNumberOfEvaluations();
		
		if (state.getParticles().size() != swarmSize) {
			throw new NotSerializableException(
					"swarmSize does not match serialized state");
		}

		for (int i = 0; i < swarmSize; i++) {
			particles[i] = state.getParticles().get(i);
		}
		
		for (int i = 0; i < swarmSize; i++) {
			localBestParticles[i] = state.getLocalBestParticles().get(i);
		}
		
		leaders.addAll(state.getLeaders());
		leaders.update();

		if (archive != null) {
			archive.addAll(state.getArchive());
		}
		
		
		for (int i = 0; i < swarmSize; i++) {
			for (int j = 0; j < problem.getNumberOfVariables(); j++) {
				velocities[i][j] = state.getVelocities()[i][j];
				this.deltaPackNewVelocity[i][j] = state.getX1()[i][j];
				this.betaPackNewVelocity[i][j]  = state.getX2()[i][j];
				this.alphaPackNewVelocity[i][j] = state.getX3()[i][j];
			}
		}
		this.nonUniformMutation = new NonUniformMutation(((NonUniformMutation)state.getNonUniformMutation()).getProbability(), ((NonUniformMutation)state.getNonUniformMutation()).getPerturbation(), 
				((NonUniformMutation)state.getNonUniformMutation()).getMaxIterations());
		this.uniformMutation = new UniformMutation(((UniformMutation)state.getUniformMutation()).getProbability(), ((UniformMutation)state.getUniformMutation()).getPerturbation());
		this.maxEvaluations = state.getMaxEvaluations();
		this.decreasingGlobalAccel = state.getA();		
	}
	
	/**
	 * Proxy for serializing and deserializing the state of an
	 * {@code MOGWO}. This proxy supports saving
	 * the {@code numberOfEvaluations}, {@code population} and {@code archive}.
	 */
	private static class MOGWOAlgorithmState implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2793926626233655363L;

		/**
		 * The number of objective function evaluations.
		 */
		private final int numberOfEvaluations;

		/**
		 * The particles stored in a serializable list.
		 */
		private final List<Solution> particles;

		/**
		 * The local best particles stored in a serializable list.
		 */
		private final List<Solution> localBestParticles;
		
		/**
		 * The leaders stored in a serializable list.
		 */
		private final List<Solution> leaders;
		
		/**
		 * The archive stored in a serializable list.
		 */
		private final List<Solution> archive;
		
		/**
		 * The velocities.
		 */
		private final double[][] velocities;
		
		/**
		* The uniform mutation operator, whose parameters remain unchanged.
		*/
		private final Variation uniformMutation;
	
		/**
		* The non-uniform mutation operator, whose parameters change during a run.
		*/
		private final Variation nonUniformMutation;
		
		private int        maxEvaluations; 
		private double     decreasingGlobalAccel; 
		private double[][] deltaPackNewVelocity;
		private double[][]  betaPackNewVelocity;
		private double[][] alphaPackNewVelocity;

		/**
		 * Constructs a proxy to serialize and deserialize the state of an 
		 * {@code AbstractPSOAlgorithm}.
		 * 
		 * @param numberOfEvaluations the number of objective function
		 *        evaluations
		 * @param population the population stored in a serializable list
		 * @param archive the archive stored in a serializable list
		 */
		public MOGWOAlgorithmState(int numberOfEvaluations,
				List<Solution> particles,
				List<Solution> localBestParticles,
				List<Solution> leaders,
				List<Solution> archive,
				double[][] velocities, 
				int maxEvaluations, 
				double a, 
				double[][] x1, 
				double[][] x2, 
				double[][] x3, 
				Variation uniformMutation, 
				Variation nonUniformMutation) {
			super();
			this.numberOfEvaluations = numberOfEvaluations;
			this.particles = particles;
			this.localBestParticles = localBestParticles;
			this.leaders = leaders;
			this.archive = archive;
			this.velocities = velocities;
			this.maxEvaluations = maxEvaluations;
			this.decreasingGlobalAccel = a;
			this.deltaPackNewVelocity = x1;
			this.betaPackNewVelocity  = x2;
			this.alphaPackNewVelocity = x3;
			this.uniformMutation = uniformMutation;
			this.nonUniformMutation = nonUniformMutation;
		}

		/**
		 * Returns the number of objective function evaluations.
		 * 
		 * @return the number of objective function evaluations
		 */
		public int getNumberOfEvaluations() {
			return numberOfEvaluations;
		}

		/**
		 * Returns the particles stored in a serializable list.
		 * 
		 * @return the particles stored in a serializable list
		 */
		public List<Solution> getParticles() {
			return particles;
		}

		/**
		 * Returns the local best particles stored in a serializable list.
		 * 
		 * @return the local best particles stored in a serializable list
		 */
		public List<Solution> getLocalBestParticles() {
			return localBestParticles;
		}

		/**
		 * Returns the leaders stored in a serializable list.
		 * 
		 * @return the leaders stored in a serializable list
		 */
		public List<Solution> getLeaders() {
			return leaders;
		}

		/**
		 * Returns the velocities.
		 * 
		 * @return the velocities
		 */
		public double[][] getVelocities() {
			return velocities;
		}

		/**
		 * Returns the archive stored in a serializable list.
		 * 
		 * @return the archive stored in a serializable list
		 */
		public List<Solution> getArchive() {
			return archive;
		}

		public int getMaxEvaluations() {
			return maxEvaluations;
		}

		public double getA() {
			return decreasingGlobalAccel;
		}

		public double[][] getX1() {
			return deltaPackNewVelocity;
		}

		public double[][] getX2() {
			return  betaPackNewVelocity;
		}

		public double[][] getX3() {
			return alphaPackNewVelocity;
		}
		
		public Variation getUniformMutation() {
			return uniformMutation;
		}

		public Variation getNonUniformMutation() {
			return nonUniformMutation;
		}
	}
}
