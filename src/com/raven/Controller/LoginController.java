package com.raven.Controller;

import com.raven.util.XDialog;

public interface LoginController {
    void open(); 
    void login(); 
    default void exit(){ 
        if(XDialog.confirm("Bạn muốn kết thúc?")){ 
            System.exit(0); 
        } 
    }
}
