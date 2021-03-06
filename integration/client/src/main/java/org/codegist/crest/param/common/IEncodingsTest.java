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

package org.codegist.crest.param.common;

import org.codegist.crest.BaseCRestTest;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.EnumSet;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.codegist.crest.param.common.IEncodingsTest.Tests.Defaults;
import static org.codegist.crest.param.common.IEncodingsTest.Tests.Encoded;
import static org.codegist.crest.util.ToStrings.string;
import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeTrue;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
public abstract class IEncodingsTest<T extends IEncodingsTest.IEncodings> extends BaseCRestTest<T> {

    public IEncodingsTest(CRestHolder crest, Class<T> service) {
        super(crest, service);
    }

    @Parameterized.Parameters
    public static Collection<CRestHolder[]> getData() {
        return crest(byRestServices());
    }

    public enum Tests {
        Defaults,Encoded
    }

    public EnumSet<Tests> ignores(){
        return EnumSet.noneOf(Tests.class);
    }

    public void assumeThatTestIsEnabled(Tests test){
        assumeTrue(!ignores().contains(test));
    }

    public static interface IEncodings {

        String defaults(String p1, Collection<String> p2);

        String encoded(String p1, Collection<String> p2);

    }

    private static final String NASTY = "£\"(')?\n &£d&f{/p3=}:,;£\"(')?\n &£d&f{/pp3=}";

    @Test
    public void testDefaults() throws UnsupportedEncodingException {
        assumeThatTestIsEnabled(Defaults);
        String actual = toTest.defaults(NASTY, asList(NASTY, NASTY));
        assertDefault(NASTY, NASTY, NASTY, actual);
    }

    public void assertDefault(String p1, String p21, String p22, String actual) throws UnsupportedEncodingException {
        String expected = format("default() p1=%s p2=%s", (p1), string((p21), (p22)));
        assertEquals(expected, actual);
    }

    @Test
    public void testEncoded() throws UnsupportedEncodingException {
        assumeThatTestIsEnabled(Encoded);
        String actual = toTest.encoded(url(NASTY), asList(url(NASTY), url(NASTY)));
        assertEncoded(NASTY, NASTY, NASTY, actual);
    }

    public void assertEncoded(String p1, String p21, String p22, String actual) throws UnsupportedEncodingException {
        String expected = format("encoded() p1=%s p2=%s", (p1), string(p21, p22));
        assertEquals(expected, actual);
    }
}
