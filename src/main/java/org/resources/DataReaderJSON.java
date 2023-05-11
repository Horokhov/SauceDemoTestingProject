package org.resources;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class DataReaderJSON {

    public void convertJSONtoMap() throws IOException {

        File json = new File(System.getProperty("user.dir")+"src/main/java/org/resources/PurchaseProduct.json");

        String jsonData = FileUtils.readFileToString(json);
    }
}
