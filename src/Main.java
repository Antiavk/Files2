import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Main  {

    private static final String filePathToSave = "C://Games/savegames/";
    private static List<String> FilesInDirectory = new ArrayList<String>();

    public static void main(String[] args) {

        GameProgress savegame0001 = new GameProgress(94, 20, 2, 30.55);
        GameProgress savegame0002 = new GameProgress(21, 10, 3, 155.32);
        GameProgress savegame0003 = new GameProgress(100, 30, 7, 254.32);

        saveGame("savegame0001.date", savegame0001);
        saveGame("savegame0002.date", savegame0002);
        saveGame("savegame0002.date", savegame0003);

    }
        public static void saveGame(String filePath, GameProgress gameProgress) {
            try (FileOutputStream fos = new FileOutputStream(filePathToSave)) {
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(gameProgress);

                FilesInDirectory.add(filePathToSave);
                System.out.println("Файл " + filePath + " добавлен в список сохранений.");

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
}
