package com.laszlobogacsi.albimester.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLFileReaderTest {
    @Test
    void canReadAFile() {

        FileReader fileReader = new XMLFileReader();
        String filename = "testFile.xml";
        final URL filename1 = getClass().getClassLoader().getResource(filename);
        File file = fileReader.readFile(filename1);
        assertThat(file.exists()).isTrue();
    }
}
