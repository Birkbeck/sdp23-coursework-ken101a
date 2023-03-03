package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String reflabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName result, String reflabel) {
        super(label, OP_CODE);
        this.result = result;
        this.reflabel = reflabel;
    }
   @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(result) != 0) {
            return m.getProgramCounter(reflabel);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + reflabel;
    }

}
