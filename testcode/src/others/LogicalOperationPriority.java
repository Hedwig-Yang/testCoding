package others;

/**
 * @Auther:KUN
 * @Data:2021/2/20 10:25
 * @Description:
 * @Version:
 */
public class LogicalOperationPriority {
    public static void main(String[] args) {
        if(3>2 && 4>3 || 1<2 && 2>3){
            //and运算优先级高于or
            System.out.println("OK");
        }
    }
}
