package net.Aden.yugiquest.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CardLangUpdater {

    private static final String CARD_DATABASE_PATH = "D:\\stuff\\New folder\\Forge-Yugi-1.20.X\\cardDatabase.json";
    private static final String LANG_FILE_PATH = "D:\\stuff\\New folder\\Forge-Yugi-1.20.X\\src\\main\\resources\\assets\\yugiquest\\lang\\en_us.json";

    public static void main(String[] args) {
        updateLangFileWithCardNames();
    }

    public static void updateLangFileWithCardNames() {
        try {
            // Read the card database JSON file
            Gson gson = new Gson();
            List<Map<String, Object>> cardList;
            try (FileReader reader = new FileReader(CARD_DATABASE_PATH)) {
                cardList = gson.fromJson(reader, List.class);
            }

            // Read the existing lang file
            JsonObject langJson;
            if (Files.exists(Paths.get(LANG_FILE_PATH))) {
                try (FileReader reader = new FileReader(LANG_FILE_PATH)) {
                    langJson = JsonParser.parseReader(reader).getAsJsonObject();
                }
            } else {
                langJson = new JsonObject();
            }

            // Update the lang file with card names
            for (Map<String, Object> card : cardList) {
                int id = ((Number) card.get("id")).intValue();
                String name = (String) card.get("name");
                String langKey = "item.yugiquest." + id;
                langJson.addProperty(langKey, name);
            }

            // Write the updated lang file back to disk
            try (FileWriter writer = new FileWriter(LANG_FILE_PATH)) {
                Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                writer.write(prettyGson.toJson(langJson));
            }

            System.out.println("Lang file updated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
