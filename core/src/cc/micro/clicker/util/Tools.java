package cc.micro.clicker.util;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Tools {
    public static String _bi2shortStr(@NotNull final BigInteger number) {
        if(number.compareTo(BigInteger.valueOf(1_000_000L)) < 0) {
            return number.toString();
        }

        final Map<Long, String> suffixMap = new HashMap<Long, String>() {
            {
                put(1_000_000_000_000_000_000L, "E");
                put(1_000_000_000_000_000L, "P");
                put(1_000_000_000_000L, "T");
                put(1_000_000_000L, "G");
                put(1_000_000L, "M");
            }
        };

        for (final Long value : suffixMap.keySet()) {
            if(number.compareTo(BigInteger.valueOf(value)) > 0) {
                final DecimalFormat df = new DecimalFormat("#.###");
                return df.format(number.doubleValue() / value.doubleValue())
                        + " "
                        + suffixMap.get(value);
            }
        }

        return number.toString();
    }
}
