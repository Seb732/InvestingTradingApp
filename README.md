#### Api usage example: 
```yaml
{
    "userID": 0,
    "firstName": "Jan",
    "lastName": "Duda",
    "teleNumb": "507-321-312",
    "email": "sebasd@gmail.com",
    "personalAccount": {
        "balance": 5000.0
    },
    "investingAccount": {
        "balance": 3400.0,
        "investingAccountID": 0,
        "portfolio": [
            {
                "positionID": 0,
                "companyID": "TESLA",
                "size": 4,
                "ticker": 400.0,
                "positionStatus": "OPEN",
                "openDate": "2022-06-26T22:00:34.9624596",
                "closeDate": null,
                "investingAccount": null
            }
        ]
    }
}
```


### Functionalities
___
Connected with:

#### User

<ul>
    <li>CRUD interface for user's account</li>
    <li>Login to personal account</li>
    <li>User can see the balance, income, expenses via dashboard</li>
    <li>User can invest in company by transferring money to assigned investing account</li>
    <li>User's able to see history of investments</li>
</ul>

#### Investing 
<ul>
    <li>See the charts of stock prices etc.</li>
    <li>*experimental* User can see stock price prediction</li>
</ul>
.


