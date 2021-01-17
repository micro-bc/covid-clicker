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

    @NotNull
    public static String _prettyTime(@NotNull final BigInteger milliseconds) {
        final StringBuilder prettyTime = new StringBuilder();

        final long day = 1000 * 60 * 60 * 24;
        final long hour = 1000 * 60 * 60;
        final long minute = 1000 * 60;
        final long second = 1000;

        final long days = milliseconds.longValue() / day;
        final long daysResidue = milliseconds.longValue() - days * day;
        final long hours = daysResidue / hour;
        final long hoursResidue = daysResidue - hours * hour;
        final long minutes = hoursResidue / minute;
        final long minutesResidue = hoursResidue - minutes * minute;
        final long seconds = minutesResidue / second;

        if(days > 0) prettyTime.append(days).append(" days ");
        if(hours > 0) prettyTime.append(hours).append("h ");
        if(minutes > 0) prettyTime.append(minutes).append("min ");
        prettyTime.append(seconds).append("s");

        return prettyTime.toString();
    }
}
