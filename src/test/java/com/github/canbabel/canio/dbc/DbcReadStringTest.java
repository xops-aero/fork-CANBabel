/**
 *  CANBabel - Translator for Controller Area Network description formats
 *  Copyright (C) 2011-2025 julietkilo and Jan-Niklas Meier
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.github.canbabel.canio.dbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.github.canbabel.canio.ui.SchemaValidator;

public class DbcReadStringTest {
    
    private File testFile = null;

    @Before
    public void setUp() {
        URL url = Thread.currentThread().getContextClassLoader().getResource("read_in_test.dbc");
        testFile = new File(url.getPath());
    }

    @Test
    public void readAndValidateTest() throws IOException{
        DbcReader reader = new DbcReader();
        File readInTestKcdFile = new File("read_in_test.kcd");

        String testFileContent = FileUtils.readFileToString(testFile, StandardCharsets.UTF_8);

        if (reader.parseString(testFileContent)) {
            assertNotNull(reader.getBus());

            StreamSource source = new StreamSource(readInTestKcdFile);
            SchemaValidator validator = new SchemaValidator(System.out);

            assertTrue(validator.validate(source));
        }
    }

}
