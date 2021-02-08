# SpringBootAPIMonoDB

#The Data Feeds

We have the two following data feeds:

Feed 1: The balances
This is a data feed, where each event represents a single balance update for a given account.

A single record looks like this

{"accountNumber": "abc", "lastUpdateTimestamp": "2020-01-01T01:02:03.8Z", "balance": 89.1}

Feed 2: The transactions
This is a data feed representing transactions that are occuring. Each record will be a single transaction at the bank. So there are two types of transactions: 1. DEPOSIT and 2. WITHDRAW.

A single record looks like this
For a DEPOSIT:

{"accountNumber": "abc", "transactionTs": "2020-01-03T01:02:03.8Z", "type": "DEPOSIT", "amount": 89.1}

Or a WITHDRAW

{"accountNumber": "abc", "transactionTs": "2020-01-03T01:02:03.8Z", "type": "WITHDRAW", "amount": 89.1}

The API [ the actual meat of the assignment ;) ]
There are 3 API that can serve the following queries:

1) Given an accountNumber, return the latest balance.
2) Given an accountNumber and a time range such as: Today, Last 7 days, last Month and the more general case of a range between date X and date Y. For example, I should be able to ask for all my transactions between January 8th, 2019 and November 28th, 2020.
3) Repeat 2, but filter for type. I.E. Shows transactions with type WITHDRAW.
