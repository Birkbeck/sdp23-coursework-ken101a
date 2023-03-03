package sml;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

/**
 * Represents the machine, the context in which programs run.
 * <p>
 * An instance contains 32 registers and methods to access and change them.
 *
 */
public final class Machine {
	private final List<String> labelpcstore = new ArrayList<>();
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
			//collects all label PC's addresses for use in jump (jnz) instructions
			//collects and stores label, and place its program counter addr in the next store
			// ie find the label in the list and the next is the PC for found label KISS
			if (ins.label != null ){
				labelpcstore.add(ins.label);
				labelpcstore.add(labelpcstore.size() , String.valueOf(programCounter));
			}
			//
			int programCounterUpdate = ins.execute(this);
			programCounter = (programCounterUpdate == NORMAL_PROGRAM_COUNTER_UPDATE)
				? programCounter + 1
				: programCounterUpdate;
		}
	}
	public int getProgramCounter(String label) {
		return Integer.parseInt(labelpcstore.get(labelpcstore.indexOf(label) +1));
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

}
