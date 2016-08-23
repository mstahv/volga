/*
 * Copyright 2016 Vaadin Community.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.it;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.vaadin.addonhelpers.automated.AbstractBaseWebDriverCase;
import org.vaadin.uiserver.Server;

/**
 *
 * @author mattitahvonenitmill
 */
public class AbstractWDIT extends AbstractBaseWebDriverCase {

    protected static final int TESTPORT = 5678;
    protected static final String BASEURL = "http://localhost:" + TESTPORT
            + "/";

    @BeforeClass
    public static void startServer() {
        try {
            server = new Server().startServer(TESTPORT);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void stopServer() {
        if (server != null) {
            try {
                server.stop();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
