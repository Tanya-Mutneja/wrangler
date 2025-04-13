package io.cdap.wrangler.api.parser;

/**
 * This interface represents a generic token parsed from a directive argument.
 * All token types like String, Integer, Column, ByteSize, etc., implement this.
 */
public interface Token {
  /**
   * Returns the type of token.
   * @return TokenType enum representing the token type.
   */
  TokenType getType();

  /**
   * Returns the raw string value of the token (e.g., "10KB", "5ms").
   * @return the string representation of the token.
   */
  String getValue();
}
