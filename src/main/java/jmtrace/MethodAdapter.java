package jmtrace;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author LiuJi
 */
class MethodAdapter extends MethodVisitor implements Opcodes {
    public MethodAdapter(final MethodVisitor mv) {
        super(ASM7, mv);
    }
    @Override
    public void visitInsn(int opcode) {
        if(opcode >= IALOAD && opcode <= SALOAD) {
            mv.visitInsn(DUP2);
            mv.visitMethodInsn(INVOKESTATIC, "jmtrace/TraceClass",
                    "traceArrayRead", "(Ljava/lang/Object;I)V", false);
        }
        if (opcode >= IASTORE && opcode <= SASTORE){
            mv.visitInsn(DUP_X2);
            mv.visitInsn(POP);
            mv.visitInsn(DUP2);
            mv.visitMethodInsn(INVOKESTATIC, "jmtrace/TraceClass",
                    "traceArrayWrite", "(Ljava/lang/Object;I)V", false);
            mv.visitInsn(DUP2_X1);
            mv.visitInsn(POP2);
        }
        mv.visitInsn(opcode);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
        if (opcode == GETFIELD){
            mv.visitInsn(DUP);
            mv.visitLdcInsn(name);
            mv.visitMethodInsn(INVOKESTATIC, "jmtrace/TraceClass",
                    "traceFieldRead","(Ljava/lang/Object;Ljava/lang/String;)V",false);
        }
        if (opcode == PUTFIELD){
            mv.visitInsn(DUP2);
            mv.visitInsn(POP);
            mv.visitLdcInsn(name);
            mv.visitMethodInsn(INVOKESTATIC, "jmtrace/TraceClass",
                    "traceFieldWrite","(Ljava/lang/Object;Ljava/lang/String;)V",false);
        }
        if (opcode == GETSTATIC){
            mv.visitLdcInsn(owner);
            mv.visitLdcInsn(name);
            mv.visitMethodInsn(INVOKESTATIC, "jmtrace/TraceClass",
                    "traceStaticFieldRead", "(Ljava/lang/String;Ljava/lang/String;)V", false);
        }
        if (opcode == PUTSTATIC){
            mv.visitLdcInsn(owner);
            mv.visitLdcInsn(name);
            mv.visitMethodInsn(INVOKESTATIC, "jmtrace/TraceClass",
                    "traceStaticFieldWrite", "(Ljava/lang/String;Ljava/lang/String;)V", false);
        }
        mv.visitFieldInsn(opcode, owner, name, desc);
    }
}