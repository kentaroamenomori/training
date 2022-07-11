package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class BoldKeyword implements ModelInterface {
    public static final String COMMAND = "boldkeyword";
    /**
     * 指定ファイルの指定キーワードを<b> </b>で囲ったファイルを指定ファイルと同じディレクトリに出力する。
     * キーワードが複数行にまたがっている場合は認識しない。
     * 出力ファイルの拡張子は .bold。
     * @param input ファイルのパスとキーワードを含んだ文字列。間に空白がない場合はエラー文を返す。ファイルは絶対パスで指定する。
     * @return 成功・エラー内容を記載した文字列。成功した場合はファイルのパスも返す。
     */
    @Override
    public String execute(String input) {
        int spaceIndex = input.indexOf(" ");
        if (spaceIndex == -1) {
            return "keyword not specified";
        }

        String filePath = input.substring(0, spaceIndex).trim();
        String keyword = input.substring(spaceIndex + 1).trim();

        Path path = Path.of(filePath);

        if (!Files.exists(path)) {
            return "file doesn't exist";
        }

        // 元ファイルの中身を格納する（本当はこの辺streamで処理した方が良さそう）
        List<String> content;
        try {
            content = Files.readAllLines(path);
        } catch (IOException e) {
            return "failed to read the file";
        }

        var replacedContent = new LinkedList<String>();
        String replacedKeyword = "<b>" + keyword + "</b>";

        // 新しいファイルの中身を生成
        for (String row : content) {
            if (row.contains(keyword)) {
                row = row.replaceAll(keyword, replacedKeyword);
            }
            replacedContent.add(row);
        }

        // 新しいファイルのパスを生成
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf(".");
        String newFileName = fileName.substring(0, dotIndex) + ".bold";

        String fileDirectory = path.getParent().toString();

        Path newPath = Paths.get(fileDirectory, newFileName);
        try {
            Files.createFile(newPath);
            Files.write(newPath, replacedContent);
        } catch (IOException e) {
            return "failed to create the file";
        }

        return String.format("file successfully created: %s", newPath);
    }
}
