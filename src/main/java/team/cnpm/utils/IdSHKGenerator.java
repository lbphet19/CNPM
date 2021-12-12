package team.cnpm.utils;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class IdSHKGenerator {
	public String getSaltString() {
        String SALTCHARS = "1234567890";
        char[] random9DigitString = new char[9];
        Random rnd = new Random();
        for(int i = 0; i < 9; i++) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            random9DigitString[i] = SALTCHARS.charAt(index);
        }
        return String.copyValueOf(random9DigitString);
    }
}
