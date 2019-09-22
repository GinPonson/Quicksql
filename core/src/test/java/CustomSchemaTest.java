import com.qihoo.qsql.api.SqlRunner;
import org.junit.Test;

/**
 * @author panyongjian
 * @since 2019/9/16.
 */
public class CustomSchemaTest {

    public static final String pg = "inline:{\"version\":\"1.0\",\"defaultSchema\":\"QSql\",\"schemas\":[{\"type\":\"custom\",\"name\":\"public\",\"factory\":\"org.apache.calcite.adapter.custom.JdbcSchemaFactory\",\"tables\":[{\"name\":\"test1\",\"factory\":\"org.apache.calcite.adapter.custom.JdbcTableFactory\",\"operand\":{\"dbName\":\"public\",\"tableName\":\"test1\",\"dbType\":\"postgresql\",\"jdbcDriver\":\"org.postgresql.Driver\",\"jdbcUrl\":\"jdbc:postgresql://localhost:5432/test\",\"jdbcUser\":\"postgres\",\"jdbcPassword\":\"root\"},\"columns\":[{\"name\":\"COL_1:string\"}]}]}]}";

    public static final String mysql = "inline:{\"version\":\"1.0\",\"defaultSchema\":\"QSql\",\"schemas\":[{\"type\":\"custom\",\"name\":\"foodmart\",\"factory\":\"org.apache.calcite.adapter.custom.JdbcSchemaFactory\",\"tables\":[{\"name\":\"account\",\"factory\":\"org.apache.calcite.adapter.custom.JdbcTableFactory\",\"operand\":{\"dbName\":\"foodmart\",\"tableName\":\"account\",\"dbType\":\"mysql\",\"jdbcDriver\":\"com.mysql.jdbc.Driver\",\"jdbcUrl\":\"jdbc:mysql://192.168.2.216:3306/foodmart\",\"jdbcUser\":\"root\",\"jdbcPassword\":\"ly-data@1234\"},\"columns\":[{\"name\":\"account_id:int\"}]}]}]}";

    public static final String pg1 = "inline:{\"version\":\"1.0\",\"defaultSchema\":\"QSql\",\"schemas\":[{\"type\":\"jdbc\",\"name\":\"public\",\"jdbcDriver\":\"org.postgresql.Driver\",\"jdbcUrl\":\"jdbc:postgresql://localhost:5432/test\",\"jdbcUser\":\"postgres\",\"jdbcPassword\":\"root\"}]}";

    @Test
    public void testPg() {
        String sql = "select COL_1 from public.test1";
        SqlRunner.Builder.RunnerType runnerType = SqlRunner.Builder.RunnerType.DEFAULT;
        SqlRunner runner = SqlRunner.builder()
                .setTransformRunner(runnerType)
                .setSchemaPath(pg)
                .setAppName("test virtual")
                .setAcceptedResultsNum(2000)
                .ok();

        runner.sql(sql).show().run();
    }

    @Test
    public void testMysql() {
        String sql = "select account_id,accoutn_type from foodmart.account";
        SqlRunner.Builder.RunnerType runnerType = SqlRunner.Builder.RunnerType.DEFAULT;
        SqlRunner runner = SqlRunner.builder()
                .setTransformRunner(runnerType)
                .setSchemaPath(mysql)
                .setAppName("test virtual")
                .setAcceptedResultsNum(2000)
                .ok();

        runner.sql(sql).show().run();
    }

}
