package io.cdap.wrangler.api.parser;

public class ByteSize extends Token {
    private final double value;
    private final String unit;

    public ByteSize(String raw) {
        super("BYTE_SIZE", raw);
        String normalized = raw.trim().toLowerCase();
        if (normalized.endsWith("kb")) {
            unit = "kb";
            value = Double.parseDouble(normalized.replace("kb", ""));
        } else if (normalized.endsWith("mb")) {
            unit = "mb";
            value = Double.parseDouble(normalized.replace("mb", ""));
        } else if (normalized.endsWith("gb")) {
            unit = "gb";
            value = Double.parseDouble(normalized.replace("gb", ""));
        } else if (normalized.endsWith("tb")) {
            unit = "tb";
            value = Double.parseDouble(normalized.replace("tb", ""));
        } else {
            throw new IllegalArgumentException("Unsupported byte size unit: " + raw);
        }
    }

    public long getBytes() {
        switch (unit) {
            case "kb": return (long) (value * 1024);
            case "mb": return (long) (value * 1024 * 1024);
            case "gb": return (long) (value * 1024 * 1024 * 1024);
            case "tb": return (long) (value * 1024L * 1024 * 1024 * 1024);
            default: throw new IllegalStateException("Unknown unit: " + unit);
        }
    }
}
