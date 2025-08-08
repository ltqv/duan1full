package login_form;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * Quản lý tính năng "Remember me".
 * *Lưu ý*: chỉ demo – muốn an toàn hơn hãy hash mật khẩu.
 */
public class Security {

    private static final String FILE_NAME = "remember.dat";

    /** Lưu user + password (đã base64 để tránh nhìn rõ plain-text). */
    public static void save(String username, String password) {
        try (BufferedWriter bw = Files.newBufferedWriter(getPath())) {
            bw.write(Base64.getEncoder().encodeToString(username.getBytes()));
            bw.newLine();
            bw.write(Base64.getEncoder().encodeToString(password.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Đọc thông tin nếu có; trả về mảng {username, password} hoặc null. */
    public static String[] load() {
        Path p = getPath();
        if (Files.exists(p)) {
            try (BufferedReader br = Files.newBufferedReader(p)) {
                String u = br.readLine();
                String pw = br.readLine();
                if (u != null && pw != null) {
                    return new String[] {
                        new String(Base64.getDecoder().decode(u)),
                        new String(Base64.getDecoder().decode(pw))
                    };
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /** Xoá file remember khi người dùng logout. */
    public static void clear() {
        try {
            Files.deleteIfExists(getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Path getPath() {
        return Paths.get(System.getProperty("user.dir")).resolve(FILE_NAME);
    }
}