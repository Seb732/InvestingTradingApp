public class test {
    public static void main(String[] args) {
        System.out.println((4 / 2));
    }
    public static int divide(int a, int b){
        int result;
        try{
            result =  a / b;
        }
        catch (IllegalAccessError ex){
            throw new IllegalArgumentException("dasdas");
        }
        return result;
    }

}
