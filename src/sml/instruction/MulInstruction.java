package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
/**
 * mulInstruction
 * subclass of the instruction class
 * created(instance off) by Translator Class
 *
 * takes single line from input file opcode is "mul"
 *
 * multiply two registers the 2nd result in 1st register
 *
 *
 * @author KA
 */
public class MulInstruction extends Instruction {
    private final RegisterName resultReg1;
    private final RegisterName sourceReg2;

    public static final String OP_CODE = "mul";

    public MulInstruction(String label, RegisterName resultReg1, RegisterName sourceReg2) {
        super(label, OP_CODE);
        this.resultReg1 = resultReg1;
        this.sourceReg2 = sourceReg2;
    }
    @Override
    public int execute(Machine m) {
       // System.out.println("mull f3...");
        int value1 = m.getRegisters().get(resultReg1);
        int value2 = m.getRegisters().get(sourceReg2);
        m.getRegisters().set(resultReg1, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + resultReg1 + " " + sourceReg2;
    }
}
