package xyz.damt.request;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Request {

    private final UUID uuid;
    private final String nameMCServerIP;

    @Getter private final Executor asyncThreadChecker = Executors.newFixedThreadPool(1);

    public Request(UUID uuid, String nameMCServerIP) {
        this.uuid = uuid;
        this.nameMCServerIP = nameMCServerIP;
    }

    public boolean hasLiked() {
        boolean value = false;
        try {
            URL requestHTTP = new URL("https://api.namemc.com/server/" + nameMCServerIP + "/likes?profile=" + uuid.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(requestHTTP.openStream()));
            value = Boolean.parseBoolean(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

}