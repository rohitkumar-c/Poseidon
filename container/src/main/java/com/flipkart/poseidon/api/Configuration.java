/*
 * Copyright 2015 Flipkart Internet, pvt ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flipkart.poseidon.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.poseidon.helpers.ObjectMapperHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Configuration {

    int getPort();

    boolean sendServerVersion();

    String getRewriteFilePath();

    String getAccessLogConfigFilePath();

    boolean isAccessLogEnabled();

    String getRotationStatusFilePath();

    String getApiFilesPath();

    String[] getFilterIds();

    String getAppName();

    TracingConfiguration getTracingConfiguration();

    /**
     * @return true to have Poseidon throw actual exceptions instead of InterruptedException (Observable onError !! :( )
     */
    default boolean throwOriginalExceptionsForNonUpstreamFailures() {
        return false;
    }

    /*
     * Return null to use default values at framework
     */
    JettyConfiguration getJettyConfiguration();

    ExceptionMapper getExceptionMapper();

    Headers getHeadersConfiguration();

    default ObjectMapper getObjectMapper() {
        return ObjectMapperHelper.getMapper();
    }

    default boolean collectServiceClientCommandNames() {
        return false;
    }

    default List<String> getResponseHeadersToCollect() { return new ArrayList<>(); }

    default List<Pair<String, ServletHolder>> registerServlets() {
        return Collections.emptyList();
    }
}
