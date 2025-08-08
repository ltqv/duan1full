package com.mycompany.model.student;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu {

    String icon;
    String name;
    MenuType type;
    
public Icon toIcon() {
    if (icon == null || icon.isEmpty()) {
        System.err.println("⚠️ Icon name is null or empty!");
        return null;
    }

    URL imageUrl = getClass().getResource("/com/mycompany/icon/student/" + icon + ".png");
    if (imageUrl == null) {
        System.err.println("❌ Không tìm thấy ảnh: " + icon + ".png");
        return null;
    }

    return new ImageIcon(imageUrl);
}



    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }

    public Model_Menu() {
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
