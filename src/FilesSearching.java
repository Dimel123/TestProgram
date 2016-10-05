import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FilesSearching {
    private String rezult;

    public String printAllFilesContentWord(String path, String word) throws IOException {
        rezult = "Соответствия не установлено";
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files == null) throw new RuntimeException("Некорректная директория " + path + " либо произошла ошибка ввода/вывода");
        byte[] search_seq = word.getBytes();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isDirectory()) continue;
            BufferedInputStream fin = new BufferedInputStream(new FileInputStream(file));
            byte b;
            int c = 0;
            while ((b = (byte)fin.read()) != -1) {
                if (b == search_seq[c]){
                    c++;
                } else {
                    c = 0;
                    if(b == search_seq[c]) c++;
                }
                if(c == search_seq.length){
                    rezult = file.getName();
                    break;
                }
            }
            fin.close();
        } return rezult;
    }
}

