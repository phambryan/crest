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

package org.codegist.crest.config.annotate;

import org.codegist.crest.annotate.CookieParam;
import org.codegist.crest.annotate.CookieParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

/**
 * @author Laurent Gilles (laurent.gilles@codegist.org)
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({CookieParamsAnnotationHandler.class, CookieParamAnnotationHandler.class})
public class CookieParamsAnnotationHandlerTest extends DownToMethodAnnotationBaseTest<CookieParams> {


    private final CookieParamAnnotationHandler mockCookieParamAnnotationHandler = mock(CookieParamAnnotationHandler.class);
    private final CookieParamsAnnotationHandler toTest = new CookieParamsAnnotationHandler(mockCookieParamAnnotationHandler);
    private final CookieParam[] mockAnnotations = { mock(CookieParam.class), mock(CookieParam.class)};

    public CookieParamsAnnotationHandlerTest() {
        super(CookieParams.class);
    }

    @Test
    public void defaultContructorShouldUseDefaultInnerHandler() throws Exception {
        whenNew(CookieParamAnnotationHandler.class).withArguments(crestConfig).thenReturn(mockCookieParamAnnotationHandler);
        new CookieParamsAnnotationHandler(crestConfig);
        verifyNew(CookieParamAnnotationHandler.class).withArguments(crestConfig);
    }

    @Test
    public void handleInterfaceAnnotationShouldDelegateToInnerHandlerForEachAnnotations() throws Exception {
        when(mockAnnotation.value()).thenReturn(mockAnnotations);

        toTest.handleInterfaceAnnotation(mockAnnotation, mockInterfaceConfigBuilder);

        verify(mockCookieParamAnnotationHandler).handleInterfaceAnnotation(mockAnnotations[0], mockInterfaceConfigBuilder);
        verify(mockCookieParamAnnotationHandler).handleInterfaceAnnotation(mockAnnotations[1], mockInterfaceConfigBuilder);
        verify(mockAnnotation).value();
    }


    @Test
    public void handleMethodAnnotationShouldDelegateToInnerHandlerForEachAnnotations() throws Exception {
        when(mockAnnotation.value()).thenReturn(mockAnnotations);

        toTest.handleMethodAnnotation(mockAnnotation, mockMethodConfigBuilder);

        verify(mockCookieParamAnnotationHandler).handleMethodAnnotation(mockAnnotations[0], mockMethodConfigBuilder);
        verify(mockCookieParamAnnotationHandler).handleMethodAnnotation(mockAnnotations[1], mockMethodConfigBuilder);
        verify(mockAnnotation).value();
    }


    @Override
    public AnnotationHandler<CookieParams> getToTest() {
        return toTest;
    }

}
