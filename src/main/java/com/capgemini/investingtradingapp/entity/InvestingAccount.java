package com.capgemini.investingtradingapp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@ToString
@Getter
@Setter
public class InvestingAccount extends Account{

    HashMap<Integer, Position> portfolio;
    int positionID;
    /**
     * Class for user's investing account. Content development in a progress
     */
    public InvestingAccount() {
        this.balance = 0;
        portfolio = new HashMap<>();
    }

    /**
     * Method enables user to buy stock, by adding such position to user's investing account's portfolio
     * @param companyID - company's identifier
     * @param size - number of shares to be bought
     * @param ticker - unitary price
     * @throws Exception - exception is thrown when overdraft
     */
    public void buy(String companyID, int size, double ticker) throws Exception {
        if (this.balance < (double) size * ticker){
            throw new Exception("Insufficient founds");
        }
        this.balance -= (double) size * ticker;
        portfolio.put(positionID, new Position(companyID, size, ticker));
    }


    /**
     * Method enables user to sell position, by removing it from portfolio. Commission should be added in the near future.
     * @param positionID - position identifier in portfolio
     */
    public void sell(int positionID){
        this.balance += (double) portfolio.get(positionID).getSize() * portfolio.get(positionID).getTicker();
        portfolio.remove(positionID);
    }



}
