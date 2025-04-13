package io.cdap.wrangler.api.parser;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to parse time duration expressions like 500ms, 2s, 1.5min, 3h, etc.
 * Converts them into milliseconds.
 */
public class TimeDuration extends Token {
  private static final Pattern PATTERN = Pattern.compile("(?i)(\\d+(\\.\\d+)?)\\s*(MS|S|SEC|SECONDS|M|MIN|MINUTES|H|HR|HOURS)?");

  private final long milliseconds;

  public TimeDuration(String value) {
    super(value);
    Matcher matcher = PATTERN.matcher(value.trim());
    if (!matcher.matches()) {
      throw new IllegalArgumentException("Invalid time duration format: " + value);
    }

    double number = Double.parseDouble(matcher.group(1));
    String unit = matcher.group(3);
    if (unit == null) {
      unit = "MS"; // default to milliseconds
    }

    switch (unit.toUpperCase(Locale.ROOT)) {
      case "MS":
        this.milliseconds = (long) number;
        break;
      case "S":
      case "SEC":
      case "SECONDS":
        this.milliseconds = (long) (number * 1000);
        break;
      case "M":
      case "MIN":
      case "MINUTES":
        this.milliseconds = (long) (number * 60 * 1000);
        break;
      case "H":
      case "HR":
      case "HOURS":
        this.milliseconds = (long) (number * 60 * 60 * 1000);
        break;
      default:
        throw new IllegalArgumentException("Unknown time unit: " + unit);
    }
  }

  /**
   * Returns the time in milliseconds.
   */
  public long getMilliseconds() {
    return milliseconds;
  }

  @Override
  public String toString() {
    return milliseconds + " ms";
  }
}
