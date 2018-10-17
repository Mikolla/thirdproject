
import ru.secondproject.util.SingleDBHelper;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class hibertest {
    public static void main(String[] args) {

        SingleDBHelper.getInstance();
        SingleDBHelper.getInstance();
        SingleDBHelper.getInstance();
        SingleDBHelper.getInstance();
        SingleDBHelper.getInstance();

        System.out.println(SingleDBHelper.instanceCount);
    }
}
