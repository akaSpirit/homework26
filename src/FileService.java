import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = Paths.get("./test.json");

    public static Data[] readFile() {
        String json = "";
        try {
            json = Files.readString(PATH);
        } catch (IOException e) {
            System.out.println("\nFile not found " + e.getMessage());
        }
        return GSON.fromJson(json, Data[].class);
    }

    public static void writeFile(Data[] Data) {
        String json = GSON.toJson(Data);
        try {
            byte[] arr = json.getBytes();
            Files.write(PATH, arr);
        } catch (IOException e) {
            System.out.println("\nFile not found " + e.getMessage());
        }
    }


}
