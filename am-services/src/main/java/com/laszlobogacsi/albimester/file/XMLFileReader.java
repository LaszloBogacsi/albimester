package com.laszlobogacsi.albimester.file;

import java.io.File;
import java.net.URL;

public class XMLFileReader implements FileReader {
    @Override
    public File readFile(URL filename) {
        return new File(filename.getPath());
    }
}
