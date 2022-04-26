package com.p.pc.coinbase;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class JavaHttpClient {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://api.pro.coinbase.com/products");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");

        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        List<Products> products = mapper.readValue(responseStream, new TypeReference<List<Products>>(){});
        System.out.println(products.size());
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Products {
        String id;

        @JsonCreator
        Products(@JsonProperty("id") String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return id;
        }
    }
}
