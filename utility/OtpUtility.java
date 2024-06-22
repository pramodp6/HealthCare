package com.health.pramod.HealthCare.utility;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;



@Service
public class OtpUtility {

    public  String optverification(int length){
        Random random = new Random();
        int randomNumber = random.nextInt(999999);

        String output = Integer.toString(randomNumber);

        while (output.length()<6){
            output ="0" +output;
        }
        return output;

    }
}
