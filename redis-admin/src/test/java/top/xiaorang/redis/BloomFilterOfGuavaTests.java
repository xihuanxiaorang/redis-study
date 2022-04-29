package top.xiaorang.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BloomFilterOfGuavaTests {
    public static final int _1W = 10000;
    //布隆过滤器里预计要插入多少数据
    public static int size = 100 * _1W;
    //误判率,它越小误判的个数也就越少(思考，是不是可以设置的无限小，没有误判岂不更好)
    public static double fpp = 0.01;
    // 构建布隆过滤器
    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size, fpp);

    @Test
    public void test() {
        BloomFilter bloomFilter = BloomFilter.create(Funnels.integerFunnel(), 100);
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
        bloomFilter.put(1);
        bloomFilter.put(2);
        System.out.println(bloomFilter.mightContain(1));
        System.out.println(bloomFilter.mightContain(2));
    }

    public static void main(String[] args) {
        //1 先往布隆过滤器里面插入100万的样本数据
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        //故意取10万个不在过滤器里的值，看看有多少个会被认为在过滤器里
        List<Integer> list = new ArrayList<>(10 * _1W);

        for (int i = size; i < size + 10 * _1W; i++) {
            if (bloomFilter.mightContain(i)) {
                System.out.println(i + "\t" + "被误判了.");
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }
}
