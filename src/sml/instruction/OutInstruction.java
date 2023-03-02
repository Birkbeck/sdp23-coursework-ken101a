package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class OutInstruction extends Instruction {
    private final RegisterName result;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }
    @Override
    public int execute(Machine m) {
        System.out.println(result + " = " + m.getRegisters().get(result));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }

}

