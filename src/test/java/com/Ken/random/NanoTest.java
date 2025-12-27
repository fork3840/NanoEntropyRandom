package com.Ken.random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NanoTest {
    @Test
    public void testRandomDistribution() {
        NanoEntropyRandom rand = new NanoEntropyRandom();
        int sampleSize = 1000000;
        int heads = 0;
        for (int i = 0; i < sampleSize; i++) {
            if (rand.weightRandom(1.0)) heads++;
        }
        // 验证布尔分布是否接近 50%
        double ratio = (double) heads / sampleSize;
        System.out.println("正面概率: " + ratio);
        assertTrue(ratio == 1.0);
    }
}