package com.jnj.atm.services.utility;

import com.jnj.atm.services.exceptions.AmountNotValidException;

import java.util.HashMap;
import java.util.Map;

public class Utilities {

    /**
     * Method calculate 80% of given amount of total deposit to restrict full withdraw
     * @param amount
     * @return dobule
     */
    public static double calculateLimit(double amount) {
        return (amount * 80)/100;
    }

    /**
     * Method verify withdrawal amount does not go beyond limit
     * @param requestedAmount
     * @param balance
     * @return
     */
    public static boolean validateLimit(double requestedAmount, double balance) {
        if (requestedAmount <= calculateLimit(balance))
            return true;
        else
            return false;
    }

    /**
     * Method identify notes count
     * @param requestedAmount
     * @return Map
     * @throws AmountNotValidException
     */
    public static Map<String, Integer> dispencer(int requestedAmount) {

        Map<String, Integer> noteCountMap = new HashMap();

        String Euro[]={"50","20","10","5"};

        int totalNotes=0,count=0;

        for(int i=0;i<Euro.length;i++) {
            count= (int) (requestedAmount/Integer.parseInt(Euro[i]));
            if(count!=0) {
                noteCountMap.put(Euro[i], count);
            }
            totalNotes=totalNotes+count;
            requestedAmount=requestedAmount%Integer.parseInt(Euro[i]);
        }
        return noteCountMap;
    }

    /**
     * Method validate requested amount
     * @param amount
     * @return boolean
     */
    public static boolean isAmountValid(double amount) {
        if(amount < 5)
            return true;
        else
            return false;
    }
}
