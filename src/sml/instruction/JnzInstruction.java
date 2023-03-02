package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String labelname;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String labelname) {
        super(label, OP_CODE);
        this.result = result;
        this.labelname = labelname;
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
