package com.example.demo.Password;

import lombok.SneakyThrows;
import org.bouncycastle.util.encoders.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Password {
    @SneakyThrows
    public static String hashSHA256(String originalString){
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hash));
    }

}
