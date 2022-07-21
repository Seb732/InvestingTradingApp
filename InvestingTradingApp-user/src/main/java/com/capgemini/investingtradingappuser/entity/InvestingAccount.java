package com.capgemini.investingtradingappuser.entity;

import com.capgemini.investingtradingappposition.entity.Position;
import com.capgemini.investingtradingappposition.exception.InsufficientFoundsException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "investing_accounts")
public class InvestingAccount extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "investing_account_id")
    private long investingAccountID;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public InvestingAccount(double balance) {
        super(balance);
    }

    /**
     * Method enables user to buy stock, by adding such position to user's investing account's portfolio
     *
     * @param companyID - company's identifier
     * @param size      - number of shares to be bought
     * @param ticker    - unitary price
     * @throws InsufficientFoundsException - exception is thrown when overdraft
     */
    public void buy(long companyID, int size, double ticker, long investingAccountID) throws InsufficientFoundsException {
        if (this.balance < (double) size * ticker) {
            throw new InsufficientFoundsException();
        }
        this.balance -= (double) size * ticker;
        Position position = new Position();
        position.setCompanyID(companyID);
        position.setSize(size);
        position.setTicker(ticker);
        position.setInvestingAccountID(investingAccountID);
    }

    /**
     * Method enables user to sell position, by removing it from portfolio. Commission should be added in the near future.
     *
     * @param position - position which user want to sell
     */
    public void sell(Position position) {
        this.balance += (double) position.getSize() * position.getTicker();
    }


}
