package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
/**
 * movInstruction
 * subclass of the instruction class
 * created(instance off) by Translator Class
 *
 * takes single line from input file opcode is "mov"
 *
 * place the number(decimal) into a registers
 *
 * @author KA
 */
public class MovInstruction extends Instruction {

    private final RegisterName reg;
    private final int num;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, RegisterName reg, int num) {
        super(label, OP_CODE);
        this.reg = reg;
        this.num = num;
    }
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(reg, num);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }
    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + reg + " " + num;
    }

}
