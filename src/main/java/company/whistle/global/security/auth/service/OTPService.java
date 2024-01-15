package company.whistle.global.security.auth.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OTPService {
    public String createOTP() throws Exception {
        String otp = "";
        SecureRandom sr = new SecureRandom();

        for (int i = 0; i < 6; i++) {
            otp += sr.nextInt(9) + "";
        }
        return otp;
    }
}
