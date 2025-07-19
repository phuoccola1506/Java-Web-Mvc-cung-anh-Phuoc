/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phuoc.dev.util;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base32;

/**
 *
 * @author Admin
 */
public class OTPUtil {

    public static String generateSecretKey() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[10];
        random.nextBytes(bytes);
        return new Base32().encodeToString(bytes);
    }

    public static String getTOTPAuthURL(String issuer, String accountName, String secretKey) {
        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s",
                issuer, accountName, secretKey, issuer);
    }
    
    public static boolean verifyCode(String secretKey, String code) {
        try {
            int codeInt = Integer.parseInt(code);
            GoogleAuthenticator gAuth = new GoogleAuthenticator();
            return gAuth.authorize(secretKey, codeInt);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
