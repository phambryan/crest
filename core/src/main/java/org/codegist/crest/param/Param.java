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

package org.codegist.crest.param;

import org.codegist.crest.config.ParamConfig;

import java.util.Collection;

/**
 * <p>A Param is the concretization of a {@link org.codegist.crest.config.ParamConfig}.</p>
 * <p>While a {@link org.codegist.crest.config.ParamConfig} only describe a parameter, a Param is a real instance of a parameter, holding the value(s) associated with it's config.</p>
 * @author laurent.gilles@codegist.org
 */
public interface Param {

    /**
     * Value(s) of the param. For single valued parameter, returns a one-element collection.
     * @return value(s) of the param
     */
    Collection<Object> getValue();

    /**
     * The parameter config that describes the param and its value.
     * @return param config
     */
    ParamConfig getParamConfig();
    
}