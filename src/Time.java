public class Time {
    public static double timestarted=System.nanoTime();

    public static double gettime(){
        return (System.nanoTime()-timestarted)*1E-9;
    }
}
