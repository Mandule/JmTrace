package jmtrace;

/**
 * @author LiuJi
 */
public class TraceClass {
    private static boolean isJava(String object){
        return object.startsWith("java");
    }
    public static void traceFieldRead(Object owner, String name){
        if (isJava(owner.getClass().getCanonicalName())){
            return;
        }
        long threadID = Thread.currentThread().getId();
        long hashID = System.identityHashCode(owner) + name.hashCode();
        System.out.printf("R %d %x %s.%s\n", threadID, hashID, owner.getClass().getCanonicalName(), name);
    }
    public static void traceFieldWrite(Object owner, String name){
        if (isJava(owner.getClass().getCanonicalName())){
            return;
        }
        long threadID = Thread.currentThread().getId();
        long hashID = System.identityHashCode(owner) + name.hashCode();
        System.out.printf("W %d %x %s.%s\n", threadID, hashID, owner.getClass().getCanonicalName(), name);
    }
    public static void traceStaticFieldRead(String owner, String name){
        if (isJava(owner)){
            return;
        }
        long threadID = Thread.currentThread().getId();
        long hashID = System.identityHashCode(owner) + name.hashCode();;
        System.out.printf("R %d %x %s.%s\n", threadID, hashID, owner, name);
    }
    public static void traceStaticFieldWrite(String owner, String name){
        if (isJava(owner)){
            return;
        }
        long threadID = Thread.currentThread().getId();
        long hashID = System.identityHashCode(owner) + name.hashCode();
        System.out.printf("W %d %x %s.%s\n", threadID, hashID, owner, name);
    }
    public static void traceArrayRead(Object arr, int index) {
        long threadID = Thread.currentThread().getId();
        long hashID = System.identityHashCode(arr) + index;
        System.out.printf("R %d %x %s[%d]\n", threadID, hashID,
                arr.getClass().getCanonicalName().replace("[]", ""), index);
    }
    public static void traceArrayWrite(Object arr, int index) {
        long threadID = Thread.currentThread().getId();
        long hashID = System.identityHashCode(arr) + index;
        System.out.printf("W %d %x %s[%d]\n", threadID, hashID,
                arr.getClass().getCanonicalName().replace("[]", ""), index);
    }
}