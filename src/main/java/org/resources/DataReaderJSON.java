package org.resources;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReaderJSON {

    public List<HashMap<String, String>> convertJSONtoMap() throws IOException {

        File json = new File(System.getProperty("user.dir")+"src/main/java/org/resources/PurchaseProduct.json");

        String jsonData = FileUtils.readFileToString(json, StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String, String>>>() {

        });
        return data;


}}
