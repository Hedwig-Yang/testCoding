package others;

public class EqualsNoPointExceptionTest {
    public static void main(String[] args) {
        System.out.println("a".equals(null));  //false
        String a = null;
        System.out.println(a.equals("a"));  //NullPointerException
    }
}
