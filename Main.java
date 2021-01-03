package com.thomas;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        InitiateData.readData();
        Menu.makeConnection();
        Menu.showMainPage();
    }
}
