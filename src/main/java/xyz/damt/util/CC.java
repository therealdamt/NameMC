package xyz.damt.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CC {

    public static String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public static List<String> translate(List<String> input) {
        List<String> translatedList = new ArrayList<>();
        input.forEach(s -> translatedList.add(ChatColor.translateAlternateColorCodes('&', s)));
        return translatedList;
    }

}
