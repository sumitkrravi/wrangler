package io.cdap.wrangler.api.parser;

public class TimeDuration extends Token {
    private final double value;
    private final String unit;

    public TimeDuration(String raw) {
        super("TIME_DURATION", raw);
        String normalized = raw.trim().toLowerCase();
        if (normalized.endsWith("ms")) {
            unit = "ms";
            value = Double.parseDouble(normalized.replace("ms", ""));
        } else if (normalized.matches(".*(s|sec|seconds)$")) {
            unit = "s";
            value = Double.parseDouble(normalized.replaceAll("(s|sec|seconds)$", ""));
        } else if (normalized.matches(".*(m|min|minutes)$")) {
            unit = "m";
            value = Double.parseDouble(normalized.replaceAll("(m|min|minutes)$", ""));
        } else {
            throw new IllegalArgumentException("Unsupported time unit: " + raw);
        }
    }

    public long getMilliseconds() {
        switch (unit) {
            case "ms": return (long) value;
            case "s": return (long) (value * 1000);
            case "m": return (long) (value * 60 * 1000);
            default: throw new IllegalStateException("Unknown unit: " + unit);
        }
    }
}
