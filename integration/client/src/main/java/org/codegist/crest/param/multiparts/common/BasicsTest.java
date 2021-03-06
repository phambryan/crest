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

package org.codegist.crest.param.multiparts.common;

import org.codegist.crest.annotate.EndPoint;
import org.codegist.crest.annotate.MultiPartParam;
import org.codegist.crest.annotate.POST;
import org.codegist.crest.annotate.Path;
import org.codegist.crest.param.common.IBasicsTest;

import java.util.EnumSet;

/**
 * @author laurent.gilles@codegist.org
 */
public class BasicsTest extends IBasicsTest<BasicsTest.Basics> {

    public BasicsTest(CRestHolder crest) {
        super(crest, Basics.class);
    }

    @EndPoint("{crest.server.end-point}")
    @Path("params/multipart/basic")
    @POST
    public static interface Basics extends IBasicsTest.IBasics {

        String send(
                @MultiPartParam("p1") String p1,
                @MultiPartParam("p2") int p2);

    }

    @Override
    public EnumSet<Tests> ignores() {
        return EnumSet.of(Tests.Send);
    }
}
