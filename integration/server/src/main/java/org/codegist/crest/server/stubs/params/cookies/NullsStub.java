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

package org.codegist.crest.server.stubs.params.cookies;

import javax.ws.rs.*;
import java.util.List;

import static org.codegist.crest.server.utils.ToStrings.stringCookie;

/**
 * @author laurent.gilles@codegist.org
 */
@Produces("text/html;charset=UTF-8")
@Path("params/cookie/null")
public class NullsStub {

    @GET
    public String nulls(
            @HeaderParam("Cookie") List<String> cookies,
            @CookieParam("p1") String p1,
            @CookieParam("p2") String p2,
            @CookieParam("p3") String p3) {
        return String.format("null(%s) p1=%s p2=%s p3=%s", stringCookie(cookies, 3), p1, p2, p3);
    }

    @GET
    @Path("merging")
    public String merging(
            @HeaderParam("Cookie") List<String> cookies,
            @CookieParam("p1") String p1,
            @CookieParam("p2") String p2,
            @CookieParam("p3") String p3) {
        return String.format("merging(%s) p1=%s p2=%s p3=%s", stringCookie(cookies, 3), p1, p2, p3);
    }
}
