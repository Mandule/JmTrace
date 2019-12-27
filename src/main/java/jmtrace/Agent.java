package jmtrace;
import java.lang.instrument.Instrumentation;

/**
 * @author LiuJi
 */
public class Agent {
    public static void premain(String agentArgs, Instrumentation inst){
        inst.addTransformer(new Transformer());
    }
}



