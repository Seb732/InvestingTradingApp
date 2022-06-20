package com.capgemini.investingtradingapp.entity;

import com.capgemini.investingtradingapp.exception.InsufficientFoundsException;
import com.capgemini.investingtradingapp.exception.PositionNotFoundException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class InvestingAccount extends Account{
    private static long id = 1;
    List<Position> portfolio;
    /**
     * Class for user's investing account. Content development in a progress
     */
    public InvestingAccount() {
        this.balance = 0;
        portfolio = new ArrayList<>();
    }

    /**
     * Method enables user to buy stock, by adding such position to user's investing account's portfolio
     * @param companyID - company's identifier
     * @param size - number of shares to be bought
     * @param ticker - unitary price
     * @throws InsufficientFoundsException - exception is thrown when overdraft
     */
    public void buy(String companyID, int size, double ticker) throws InsufficientFoundsException {
        if (this.balance < (double) size * ticker){
            throw new InsufficientFoundsException();
        }
        this.balance -= (double) size * ticker;
        portfolio.add(new Position(companyID, size, ticker));
    }


    /**
     * Method enables user to sell position, by removing it from portfolio. Commission should be added in the near future.
     * @param positionID - position identifier in portfolio
     */
    public void sell(int positionID) throws PositionNotFoundException {
        if (this.portfolio.stream().noneMatch(x -> x.getPositionID() == positionID)){
            throw new PositionNotFoundException();
        }
            this.balance += (double) portfolio.get(positionID).getSize() * portfolio.get(positionID).getTicker();
            portfolio.remove(positionID);

    }



}
