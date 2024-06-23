package net.Aden.yugiquest.util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class CardDataLoader {
    private static final String JSON_FILE_PATH = "D:\\stuff\\New folder\\Forge-Yugi-1.20.X\\src\\main\\resources\\cardDatabase.json"; // Path to JSON Card database

    //This does some random thing to load the card data???
    public static List<Map<String, Object>> loadCardData() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(JSON_FILE_PATH)) {
            Type listType = new TypeToken<List<Map<String, Object>>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}