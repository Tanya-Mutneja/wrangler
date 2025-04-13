package io.cdap.wrangler.api.parser;

/**
 * Enum representing the different types of tokens that can be parsed
 * from a directive recipe in the Wrangler tool.
 */
public enum TokenType {
  STRING,
  INTEGER,
  FLOAT,
  BOOLEAN,
  IDENTIFIER,
  COLUMN,
  FUNCTION,

  // ✅ New token types added for the assignment
  BYTE_SIZE,
  TIME_DURATION
}
