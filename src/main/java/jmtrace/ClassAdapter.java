package jmtrace;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * @author LiuJi
 */
public class ClassAdapter extends ClassVisitor {
    public ClassAdapter(ClassVisitor cv){
        super(Opcodes.ASM7, cv);
    }
    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String descriptor, String signature,
                                     String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);

        return mv == null ? null : new MethodAdapter(mv);
    }
}