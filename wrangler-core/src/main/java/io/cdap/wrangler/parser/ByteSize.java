package io.cdap.wrangler.api.parser;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to parse byte size expressions like 10KB, 2.5MB, 100B, etc.
 * Converts them into bytes.
 */
public class ByteSize extends Token {
  private static final Pattern PATTERN = Pattern.compile("(?i)(\\d+(\\.\\d+)?)\\s*(B|KB|MB|GB|TB)?");

  private final long bytes;

  public ByteSize(String value) {
    super(value);
    Matcher matcher = PATTERN.matcher(value.trim());
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid byte size format: " + value);
    }

    double number = Double.parseDouble(matcher.group(1));
    String unit = matcher.group(3);
    if (unit == null) {
      unit = "B"; // default to bytes
    }

    switch (unit.toUpperCase(Locale.ROOT)) {
      case "B":
        this.bytes = (long) number;
        break;
      case "KB":
        this.bytes = (long) (number * 1024);
        break;
      case "MB":
        this.bytes = (long) (number * 1024 * 1024);
        break;
      case "GB":
        this.bytes = (long) (number * 1024 * 1024 * 1024);
        break;
      case "TB":
        this.bytes = (long) (number * 1024L * 1024L * 1024L * 1024L);
        break;
      default:
        throw new IllegalArgumentException("Unknown byte size unit: " + unit);
    }
  }

  /**
   * Returns the size in bytes.
   */
  public long getBytes() {
    return bytes;
  }

  @Override
  public String toString() {
    return bytes + " bytes";
  }
}
