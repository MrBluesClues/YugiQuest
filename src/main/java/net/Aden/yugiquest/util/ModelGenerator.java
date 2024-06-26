package net.Aden.yugiquest.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class ModelGenerator {

    private static final String OUTPUT_DIR = "src/main/resources/assets/yugiquest/models/item/";

    public static void generateModelFiles(List<Map<String, Object>> cardData) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Files.createDirectories(Paths.get(OUTPUT_DIR));

            for (Map<String, Object> card : cardData) {
                int id = ((Number) card.get("id")).intValue();
                String modelJson = createModelJson(id);
                String filePath = OUTPUT_DIR + id + ".json"; // Updated to use just the ID
                try (FileWriter writer = new FileWriter(filePath)) {
                    writer.write(modelJson);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String createModelJson(int id) {
        return "{\n" +
                "  \"parent\": \"item/generated\",\n" +
                "  \"textures\": {\n" +
                "    \"layer0\": \"yugiquest:item/" + id + "\"\n" +
                "  }\n" +
                "}";
    }

    public static void main(String[] args) {
        List<Map<String, Object>> cardData = CardDataLoader.loadCardData();
        if (cardData != null) {
            generateModelFiles(cardData);
        }
    }
}
