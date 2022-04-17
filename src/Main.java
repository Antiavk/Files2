import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main  {

      private static final String filePathToSave = "C://Games/savegames/";
      private static List<String> FilesInDirectory = new ArrayList<String>();

    public static void main(String[] args) {

        //Создать три экземпляра класса GameProgress
        GameProgress savegame0001 = new GameProgress(94, 20, 2, 30.55);
        GameProgress savegame0002 = new GameProgress(21, 10, 3, 155.32);
        GameProgress savegame0003 = new GameProgress(100, 30, 7, 254.32);

            saveGame("savegame0001.date", savegame0001);
            saveGame("savegame0002.date", savegame0002);
            saveGame("savegame0003.date", savegame0003);

        zipFiles(filePathToSave, (ArrayList<String>) FilesInDirectory);

        filesDelNoZip(FilesInDirectory);
    }

    public static void saveGame(String filePath, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(filePathToSave + filePath)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgress);

            FilesInDirectory.add(filePathToSave + filePath );
            System.out.println("Файл " + filePath + " добавлен в список сохранений.");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    static void zipFiles(String filePath, ArrayList<String> savesList) {
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(
                    new FileOutputStream(filePathToSave + "/zipFile.zip"));
            for (int i = 0; i < savesList.size(); i++) {
                FileInputStream fileInputStream = new FileInputStream(savesList.get(i));

                zipOutputStream.putNextEntry(
                        new ZipEntry(savesList.get(i).substring(filePath.length())));
                // считываем содержимое файла в массив byte
                byte[] boofer = new byte[1024];
                fileInputStream.read(boofer);
                // добавляем содержимое к архиву
                zipOutputStream.write(boofer);

                fileInputStream.close();
                zipOutputStream.flush();
            }
            // закрываем текущую запись для новой записи
            zipOutputStream.closeEntry();
            zipOutputStream.close();
            System.out.println("Файл zipFile.zip  добавлен в архив.");
          } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void filesDelNoZip(List<String> listFilesInDirectory) {
        for (String currentSave : listFilesInDirectory) {
            File fileToDel = new File(currentSave);
            if(fileToDel.delete()){
                System.out.println("Удален файл " + fileToDel + ".");
            } else {
                System.out.println("Не удалось удалить файл " + fileToDel + ".");
            }
        }
    }
}