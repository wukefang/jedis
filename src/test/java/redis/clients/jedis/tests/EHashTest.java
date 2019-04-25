package redis.clients.jedis.tests;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * created by bert on 2019/4/25.
 */
public class EHashTest {

    private Jedis jedis;

    @Before
    public void setup() {
        jedis = new Jedis("192.168.60.48", 19016);
        jedis.auth("xmly123456");
    }

    @Test
    public void testEhset() {

        long ret = jedis.ehset("hello", "world", "very good");
        System.out.println(ret);
    }

    @Test
    public void testEhsetex() throws InterruptedException {
        String ret = jedis.ehsetex("hello", "lqk", "good", 1);
        System.out.println(ret);

        System.out.println(jedis.ehget("hello", "lqk"));
        Thread.sleep(2000);
        System.out.println(jedis.ehget("hello", "lqk"));
    }

    @Test
    public void testEhget() {
        String value = jedis.ehget("hello", "world");
        System.out.println(value);
    }

    @Test
    public void testEhincrBy() {

        System.out.println(jedis.ehset("hello", "int", "1"));
        System.out.println(jedis.ehincrBy("hello", "int", 1));

    }

    @Test
    public void testEhincrByFloat() {

        System.out.println(jedis.ehset("hello", "float", "1.1"));
        System.out.println(jedis.ehincrByFloat("hello", "float", 1.1));
    }

    @Test
    public void testEhvals() {

        System.out.println(jedis.ehvals("hello"));
    }

    @Test
    public void testEhgetAll() {
        System.out.println(jedis.ehgetAll("hello"));
    }

    @Test
    public void testEhsetnx() {
        System.out.println(jedis.ehset("hello", "3ehsetnx", "ok"));
        System.out.println(jedis.ehsetnx("hello", "3ehsetnx", "ok2"));
        System.out.println(jedis.ehsetnx("hello", "4ehsetnx2", "ok"));
    }

    @Test
    public void ehexpire() {
        System.out.println(jedis.ehset("hello", "ehexpire", "very good"));
        System.out.println(jedis.ehttl("hello", "ehexpire"));
        System.out.println(jedis.ehexpire("hello", "ehexpire", 100000));
        System.out.println(jedis.ehttl("hello", "ehexpire"));
    }

    @Test
    public void ehexpireAt() {
        System.out.println(jedis.ehset("hello", "ehexpireAt", "very good"));
        System.out.println(jedis.ehttl("hello", "ehexpireAt"));
        System.out.println(jedis.ehexpireAt("hello", "ehexpireAt", System.currentTimeMillis() / 1000 + 1000000));
        System.out.println(jedis.ehttl("hello", "ehexpireAt"));
    }

    @Test
    public void ehexists() {
        System.out.println(jedis.ehset("hello", "ehexists", "very good"));
        System.out.println(jedis.ehexists("hello", "ehexists"));
        System.out.println(jedis.ehexists("hello", "ehexists_not"));

    }

    @Test
    public void ehdel() {
        System.out.println(jedis.ehset("hello", "ehdel1", "very good"));
        System.out.println(jedis.ehset("hello", "ehdel2", "very good"));
        System.out.println(jedis.ehdel("hello", "ehdel1", "ehdel3"));
    }

    @Test
    public void ehlen() {
        System.out.println(jedis.ehset("hello", "ehlen1", "very good"));
        System.out.println(jedis.ehset("hello", "ehlen2", "very good"));
        System.out.println(jedis.ehlen("hello"));
    }

    @Test
    public void ehstrlen() {
        System.out.println(jedis.ehset("hello", "ehstrlen", "very good"));
        System.out.println(jedis.ehstrlen("hello", "ehstrlen"));
    }

    @Test
    public void ehmset() {
        Map<String, String> map = new HashMap<>();
        map.put("ehmset1", "good1");
        map.put("ehmset2", "good2");
        map.put("ehmset3", "good3");
        System.out.println(jedis.ehmset("hello", map));
        System.out.println(jedis.ehmget("hello", "ehmset2", "ehmset3"));
    }

    @Test
    public void ehmsetex() {
        Jedis.TemporaryEntry entry1 = new Jedis.TemporaryEntry("ehmsetex1", "good1", 1000);
        Jedis.TemporaryEntry entry2 = new Jedis.TemporaryEntry("ehmsetex2", "good2", 1000);
        Jedis.TemporaryEntry entry3 = new Jedis.TemporaryEntry("ehmsetex3", "good3", 1000);

        System.out.println(jedis.ehmsetex("hello", entry1,entry2,entry3));


        System.out.println(jedis.ehmget("hello", "ehmsetex1", "ehmsetex2","ehmsetex3"));
        System.out.println(jedis.ehttl("hello", "ehmsetex1"));
        System.out.println(jedis.ehttl("hello", "ehmsetex2"));
        System.out.println(jedis.ehttl("hello", "ehmsetex3"));
    }

    @Test
    public void ehkeys() {
        Jedis.TemporaryEntry entry1 = new Jedis.TemporaryEntry("ehkeys1", "good1", 1000);
        Jedis.TemporaryEntry entry2 = new Jedis.TemporaryEntry("ehkeys2", "good2", 1000);
        Jedis.TemporaryEntry entry3 = new Jedis.TemporaryEntry("ehkeys3", "good3", 1000);

        System.out.println(jedis.ehmsetex("hello", entry1,entry2,entry3));
        System.out.println(jedis.ehkeys("hello"));
    }
}
