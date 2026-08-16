package valio.booking_service.utils;

import javax.crypto.SecretKey;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class SignKey {
    static String secretKey = "mTc6e71Wchdr65tZai8c0FECTHVjHSU7e3QIXbkgzgjncRmakG/J8eI/MF0I+h5zeq+GqbWIphts55ya2j3djw==";
    public static SecretKey getSecretKey(){
        byte[] keyByte = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyByte);
    }
}

