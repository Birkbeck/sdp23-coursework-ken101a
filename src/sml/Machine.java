package sml;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 *
 */
public final class Machine {


	private final List<String> ls = new ArrayList<>();
	private final Labels labels = new Labels();

	private final List<Instruction> program = new ArrayList<>();

	private final Registers registers;

	// The program counter; it contains the index (in program)
	// of the next instruction to be executed.
	private int programCounter = 0;

	public Machine(Registers registers) {
		this.registers = registers;
	}

	/**
	 * Execute the program in program, beginning at instruction 0.
	 * Precondition: the program and its labels have been stored properly.
	 */
	public void execute() {
		programCounter = 0;
		registers.clear();
		while (programCounter < program.size()) {
			Instruction ins = program.get(programCounter);
			//
			if (ins.label != null ){
				ls.add(ins.label);
				ls.add(ls.size() , String.valueOf(programCounter));
				//System.out.println(" label:    " + ls.get(ls.indexOf(ins.label)));
				//System.out.println(" label pc: " + ls.get(ls.indexOf(ins.label) +1));
			}
			//
			int programCounterUpdate = ins.execute(this);
			programCounter = (programCounterUpdate == NORMAL_PROGRAM_COUNTER_UPDATE)
				? programCounter + 1
				: programCounterUpdate;
		}
	}
	public int getProgramCounter(String label) {
		return Integer.parseInt(ls.get(ls.indexOf(label) +1));
	}

	public Labels getLabels() {
		return this.labels;
	}

	public List<Instruction> getProgram() {
		return this.program;
	}

	public Registers getRegisters() {
		return this.registers;
	}


	/**
	 * String representation of the program under execution.
	 *
	 * @return pretty formatted version of the code.
	 */
	@Override
	public String toString() {
		return program.stream()
				.map(Instruction::toString)
				.collect(Collectors.joining("\n"));
	}


	// TODO: use pattern matching for instanceof
	// https://docs.oracle.com/en/java/javase/14/language/pattern-matching-instanceof-operator.html
	@Override
	public boolean equals(Object o) {
		//System.out.println("equals 1: " + Integer.parseInt(ls.get(ls.indexOf(o) + 1)));
		return false ;
	}

/*
@Override
	public boolean equals(Object o) {
		if (o instanceof Machine) {
			//
			Machine other = (Machine) o;
			return Objects.equals(this.labels, other.labels)
					&& Objects.equals(this.program, other.program)
					&& Objects.equals(this.registers, other.registers)
					&& this.programCounter == other.programCounter;
		}
		return false;
	}

 */


	@Override
	public int hashCode() {
		return Objects.hash(labels, program, registers, programCounter);
	}
}
