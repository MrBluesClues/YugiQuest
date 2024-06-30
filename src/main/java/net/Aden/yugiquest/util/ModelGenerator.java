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
                String filePath = OUTPUT_DIR + id + ".json";
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
                "  },\n" +
                "  \"display\": {\n" +
                "    \"thirdperson_righthand\": {\n" +
                "      \"rotation\": [0, 90, -35],\n" +
                "      \"translation\": [0, 1.25, -3.5],\n" +
                "      \"scale\": [0.2, 0.4, 0.4]\n" +
                "    },\n" +
                "    \"thirdperson_lefthand\": {\n" +
                "      \"rotation\": [0, 90, -35],\n" +
                "      \"translation\": [0, 1.25, -3.5],\n" +
                "      \"scale\": [0.2, 0.4, 0.4]\n" +
                "    },\n" +
                "    \"firstperson_righthand\": {\n" +
                "      \"rotation\": [0, -30, 20],\n" +
                "      \"translation\": [0, 4, 2],\n" +
                "      \"scale\": [0.2, 0.4, 0.41]\n" +
                "    },\n" +
                "    \"firstperson_lefthand\": {\n" +
                "      \"rotation\": [0, -30, 20],\n" +
                "      \"translation\": [0, 4, 2],\n" +
                "      \"scale\": [0.2, 0.4, 0.4]\n" +
                "    },\n" +
                "    \"gui\": {\n" +
                "      \"rotation\": [0, 0, 0],\n" +
                "      \"translation\": [0, 0, 0],\n" +
                "      \"scale\": [0.8, 1, 1]\n" +
                "    },\n" +
                "    \"ground\": {\n" +
                "      \"rotation\": [0, 0, 0],\n" +
                "      \"translation\": [0, 2, 0],\n" +
                "      \"scale\": [0.3, 0.5, 0.5]\n" +
                "    },\n" +
                "    \"fixed\": {\n" +
                "      \"rotation\": [0, 180, 0],\n" +
                "      \"translation\": [0, 0, 0],\n" +
                "      \"scale\": [0.8, 1, 1]\n" +
                "    }\n" +
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
