/*
 * Copyright 2011 CodeGist.org
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 *  ===================================================================
 *
 *  More information at http://www.codegist.org.
 */

package org.codegist.crest.util;

import org.codegist.common.lang.Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Collections.unmodifiableMap;

/**
 * @author laurent.gilles@codegist.org
 */
public final class Placeholders {

    private static final Pattern ESCAPED_OPEN_CURLY_BRACKET = Pattern.compile(Pattern.quote("\\{"));
    private static final Pattern ESCAPED_CLOSE_CURLY_BRACKET = Pattern.compile(Pattern.quote("\\}"));

    private Placeholders() {
        throw new IllegalStateException();
    }

    /**
     * <p>Compiles the given placeholder map.</p>
     * <p>Placeholder keys reflects the placeholder name, and the value the value to use as replacement when placeholder are merged.</p>
     * <p>Returns a map where keys are Patterns of the following form: {placeholdername}</p>
     * @param placeholders placeholder map to compile
     * @return compiled placeholder map
     */
    public static Map<Pattern,String> compile(Map<String,String> placeholders) {
        Map<Pattern,String> compiled = new HashMap<Pattern, String>();
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String placeholder = entry.getKey();
            String value = entry.getValue();
            compiled.put(Pattern.compile("\\{" + Pattern.quote(placeholder) + "\\}"), value);
        }
        return unmodifiableMap(compiled);
    }

    /**
     * <p>Merges the placeholder into the given string with the values present in the placeholder map.</p>
     * <p>For given string:</p>
     * <code><pre>
     * this is {my.placeholder}'s {my.placeholder2} with {some.placeholder}. Signed {my.placeholder} ok ? \{my.placeholder\}</pre></code>
     * <p/>
     * <p>And for the following placeholder map:</p>
     * <code><pre>
     * {my.placeholder} = Laurent
     * {my.placeholder2} = String</pre></code>
     * <p/>
     * <p>Then the resulting string will be:</p>
     * <code><pre>
     * this is Laurent's string with {some.placeholder}. Signed Laurent ok ? {my.placeholder}</pre></code>
     * @param placeholders compiled placeholder map to use for merging placeholders in given string
     * @param str string potentially containing placeholders
     * @return merged string
     */
    public static String merge(Map<Pattern,String> placeholders, String str) {
        if (Strings.isBlank(str)) {
            return str;
        }
        String replaced = str;
        for (Map.Entry<Pattern, String> entry : placeholders.entrySet()) {
            Pattern placeholder = entry.getKey();
            String value = entry.getValue();
            replaced = placeholder.matcher(replaced).replaceAll(value);
        }
        replaced = ESCAPED_OPEN_CURLY_BRACKET.matcher(replaced).replaceAll("{");// replace escaped with non escaped
        replaced = ESCAPED_CLOSE_CURLY_BRACKET.matcher(replaced).replaceAll("}");// replace escaped with non escaped
        return replaced;
    }
}
