## Api usage example:

### User:

<b>POST</b> - create new user

```yaml
/user
Request Body:
  {
    "firstName": "John",
    "lastName": "Smith",
    "teleNumb": "777-333-888",
    "email": "jsmith@gmail.com"
  }
```

<b>GET</b> - get user by params

```yaml
/user - get all users

Request Param:

  firstName && lastName - Get user by first name and last name
  user?firstName=John&lastName=Smith

  teleNumb && email - Get user by phone number and email
  user?teleNumb=777-333-888&email=jsmith@gmail.com
```

<b>PUT</b> - update user details

```yaml
/user/{userID}
Path variable: userID

Request Body:
  {
    "firstName": "John",
    "lastName": "Smith",
    "teleNumb": "777-333-888",
    "email": "jsmith@gmail.com"
  }
```

<b>DELETE</b> - delete user by ID

```yaml
/user
Request Param: userID
  user?userID=50
```

### Personal Account

<b>PUT</b> - deposit/withdraw money to personal account
| transfer money to investing account

```yaml
Path variable: personalAccountID

Request Param:
  amount:
  operationType: deposit/withdrawal/transferIN

  deposit:
  user/personalAccount/{personalAccountID}/balance?operationType=deposit&amount=9999
  withdrawal:
  user/personalAccount/{personalAccountID}/balance?operationType=withdrawal&amount=999
  transferIN:
  user/personalAccount/{personalAccountID}/balance?operationType=transferIN&amount=1000
```

### Investing Account

<b>POST</b> - buy position

```yaml
user/investingAccount/{investingAccountID}/position

Path variable: investingAccountID

Request body:
  {
    "companyID": 3,
    "size": 2,
    "ticker": 55,
    "positionStatus": "OPEN"
  }
```

<b>PUT</b> - sell position

```yaml
user/investingAccount/{investingAccountID}/position/{positionID}

Path variable: investingAccountID, positionID
```

<b>PUT</b> - transfer money back to personal account

```yaml
/user/investingAccount/{investingAccountID}/balance

Path variable: investingAccountID
Request param: amount
  /user/investingAccount/{investingAccountID}/balance?amount1=00
```

### Position

<b>POST</b> - create position

```yaml
/position?investingAccountID=43

Request Param: investingAccountID

Request Body:
  {
    "companyID": 3,
    "size": 2,
    "ticker": 55,
    "positionStatus": "OPEN"
  }
```

<b>GET</b> - get position by param

```yaml
/position - get all positions

Request Param:
  companyID - get positions by company ID
  /position?companyID=1

  openDate - get positions after given open date
  /position?openDate=06-07-2022 00:00

  ticker - get positions with ticker greater than given
  /position?ticker=98
```

<b>PUT</b> - update position by position ID

```yaml
/position/{positionID}

Path variable: positionID

Request Body:
  {
    "companyID": 3,
    "size": 2,
    "ticker": 55,
    "positionStatus": "OPEN"
  }
```

<b>DELETE</b> - delete position by position ID

```yaml
/position

Request param: positionID
  /position?positionID=26
```

### Company

<b>POST</b> - create new company

```yaml
/company

Request Body:
  {
    "companyName": "Tesla Inc.",
    "tickerSymbol": "TSLA"
  }
```

<b>GET</b> - get company by param

```yaml
/company - get all companies

Request Param:

  companyName - get company by name
  /company?companyName=Tesla Inc.

  tickerSymbol - get company by ticker symbol
  /company?tickerSymbol=TSLA
```

<b>POST</b> - update company by id

```yaml
/company/{companyID}

Path variable: companyID
  /company?companyID=4

Request Body:
  {
    "companyName": "Tesla Inc.",
    "tickerSymbol": "TSLA"
  }
```

<b>DELETE</b> - delete company by id

```yaml
/company

Request param: companyID
  company?companyID=4
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


