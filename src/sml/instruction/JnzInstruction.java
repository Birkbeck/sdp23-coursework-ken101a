package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
/**
 * jnzInstruction
 * subclass of the instruction class
 * created(instance off) by Translator Class
 *
 * takes single line from input file opcode is "jnz"
 *
 * evaluates the if the register is zero
 * if not places the program counter at the 'count' held in ref label
 *
 * @author KA
 */
public class JnzInstruction extends Instruction {
    private final RegisterName reg;
    private final String reflabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, RegisterName reg, String reflabel) {
        super(label, OP_CODE);
        this.reg = reg;
        this.reflabel = reflabel;
    }
   @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(reg) != 0) {
            return m.getProgramCounter(reflabel);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + reg + " " + reflabel;
    }

}
