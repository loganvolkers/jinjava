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

import static com.hubspot.jinjava.parse.ParserConstants.TOKEN_TAG;

import com.hubspot.jinjava.util.WhitespaceUtils;

public class TagToken extends Token {

  private static final long serialVersionUID = 2766011408032384360L;

  private String tagName;
  private String helpers;
  
  public TagToken(String image, int lineNumber) throws ParseException {
    super(image, lineNumber);
  }

  @Override
  public int getType() {
    return TOKEN_TAG;
  }
  
  /**
   * Get tag name
   */
  @Override
  protected void parse() {
    content = image.substring(2, image.length() - 2);
    
    if(WhitespaceUtils.startsWith(content, "-")) {
      setLeftTrim(true);
      content = WhitespaceUtils.unwrap(content, "-", "");
    }
    if(WhitespaceUtils.endsWith(content, "-")) {
      setRightTrim(true);
      content = WhitespaceUtils.unwrap(content, "", "-");
    }
    
    int nameStart = -1, pos = 0, len = content.length();

    for(; pos < len; pos++) {
      char c = content.charAt(pos);
      if(nameStart == -1 && Character.isJavaIdentifierStart(c)) {
        nameStart = pos;
      }
      else if(nameStart != -1 && !Character.isJavaIdentifierPart(c)) {
        break;
      }
    }
    
    if(pos < content.length()) {
      tagName = content.substring(nameStart, pos).toLowerCase();
      helpers = content.substring(pos);
    } else {
      tagName = content.toLowerCase().trim();
      helpers = "";
    }
  }

  public String getTagName() {
    return tagName;
  }

  public String getHelpers() {
    return helpers;
  }

  @Override
  public String toString() {
    if (helpers.length() == 0) {
      return "{% " + tagName + " %}";
    }
    return "{% " + tagName + " " + helpers + " %}";
  }

}
