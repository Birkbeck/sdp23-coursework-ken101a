package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int source;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName result, int source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, source);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

}
