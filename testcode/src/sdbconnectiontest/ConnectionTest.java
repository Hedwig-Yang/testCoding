package sdbconnectiontest;
import com.sequoiadb.base.DBCursor;
import com.sequoiadb.base.Sequoiadb;
import com.sequoiadb.exception.BaseException;
public class ConnectionTest {
    public static void main(String[] args) {
        String connString = "218.78.26.163:11121";
        try {
            // 建立 SequoiaDB 数据库连接
            Sequoiadb sdb = new Sequoiadb(connString, "admin", "admin");
            // 获取所有 Collection 信息，并打印出来
            DBCursor cursor = sdb.listCollections();
            while(cursor.hasNext()) {
                System.out.println(cursor.getNext());
            }
        } catch (BaseException e) {
            System.out.println("Sequoiadb driver error, error description:" + e.getErrorType());
        }
    }
}
