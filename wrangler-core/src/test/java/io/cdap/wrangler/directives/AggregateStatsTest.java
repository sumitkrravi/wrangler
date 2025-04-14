
package io.cdap.wrangler.directives;

import org.junit.Assert;
import org.junit.Test;

public class AggregateStatsTest {

    @Test
    public void testSum() {
        int[] numbers = {1, 2, 3, 4};
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        Assert.assertEquals(10, sum);
    }
}
