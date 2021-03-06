/**********************************************************************
Copyright (c) 2014 HubSpot Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 **********************************************************************/
package com.hubspot.jinjava.parse;

import static com.hubspot.jinjava.parse.ParserConstants.TOKEN_ECHO;
import static com.hubspot.jinjava.parse.ParserConstants.TOKEN_FIXED;
import static com.hubspot.jinjava.parse.ParserConstants.TOKEN_NOTE;
import static com.hubspot.jinjava.parse.ParserConstants.TOKEN_TAG;

import java.io.Serializable;

public abstract class Token implements Serializable {

  private static final long serialVersionUID = -7513379852268838992L;

  protected String image;
  // useful for some token type
  protected String content;

  protected int lineNumber;

  private boolean leftTrim;
  private boolean rightTrim;

  public Token(String image, int lineNumber) throws ParseException {
    this.image = image;
    this.lineNumber = lineNumber;
    parse();
  }

  public String getImage() {
    return image;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public boolean isLeftTrim() {
    return leftTrim;
  }
  
  public boolean isRightTrim() {
    return rightTrim;
  }
  
  public void setLeftTrim(boolean leftTrim) {
    this.leftTrim = leftTrim;
  }
  
  public void setRightTrim(boolean rightTrim) {
    this.rightTrim = rightTrim;
  }
  
  @Override
  public String toString() {
    return image;
  }

  protected abstract void parse() throws ParseException;

  public abstract int getType();

  static Token newToken(int tokenKind, String image, int lineNumber) throws ParseException {
    switch (tokenKind) {
    case TOKEN_FIXED:
      return new FixedToken(image, lineNumber);
    case TOKEN_NOTE:
      return new NoteToken(image, lineNumber);
    case TOKEN_ECHO:
      return new EchoToken(image, lineNumber);
    case TOKEN_TAG:
      return new TagToken(image, lineNumber);
    default:
      throw new ParseException("Creating a token with unknown type >>> " + (char) tokenKind);
    }
  }

}
