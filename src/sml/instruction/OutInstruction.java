package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
/**
 * outInstruction
 * subclass of the instruction class
 * created(instance off) by Translator Class
 *
 * takes single line from input file opcode is "out"
 *
 * prints on to console the contents of the registers
 *
 * @author KA
 */
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

