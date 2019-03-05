--DROP TABLE BankUser;
--DROP TABLE BankAccount;

DELETE
FROM BankUser;

DELETE
FROM BankAccount;

DELETE
FROM UserAccount;

DELETE
FROM AccountTransaction;

commit;

CREATE TABLE BankUser (
    UserEmail VARCHAR2(50) NOT NULL,
    UserName VARCHAR2(25) NOT NULL,
    UserPassword VARCHAR2(25) NOT NULL,
    CONSTRAINT PK_BankUser PRIMARY KEY (UserEmail)
);

CREATE TABLE BankAccount (
    AccountName VARCHAR2(50) NOT NULL,
    AccountType VARCHAR2(15) NOT NULL,
    AccountBalance NUMBER(25,2) NOT NULL,
    CONSTRAINT PK_BankAccount PRIMARY KEY (AccountName)
);

CREATE TABLE UserAccount (
    UserEmail VARCHAR2(50) NOT NULL,
    AccountName VARCHAR2(50) NOT NULL
);

CREATE TABLE AccountTransaction (
    Message VARCHAR2(255),
    DateStamp TIMESTAMP,
    AccountName VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_MessageDate PRIMARY KEY (Message, DateStamp, AccountName)
);

drop table accounttransaction;

ALTER TABLE AccountTransaction ADD CONSTRAINT FK_ATAccount
    FOREIGN KEY (AccountName) REFERENCES BankAccount (AccountName);

ALTER TABLE UserAccount ADD CONSTRAINT FK_UAUser
    FOREIGN KEY (UserEmail) REFERENCES BankUser (UserEmail);
    
ALTER TABLE UserAccount ADD CONSTRAINT FK_UAAccount
    FOREIGN KEY (AccountName) REFERENCES BankAccount (AccountName);

INSERT INTO BankUser VALUES ('shimjay1@gmail.com', 'magikarp', '123');
INSERT INTO BankUser VALUES ('alfonzoshimothy@gmail.com', 'alfonzo', 'password1');

INSERT INTO BankAccount VALUES ('shimAccount', 'checking', 15.00);
INSERT INTO UserAccount VALUES ('shimjay1@gmail.com', 'shimAccount');

INSERT INTO BankAccount VALUES ('shimSavings', 'savings', 32.00);
INSERT INTO UserAccount VALUES ('shimjay1@gmail.com', 'shimSavings');

INSERT INTO BankAccount VALUES ('alfonzoChecking', 'checking', 44.00);
INSERT INTO UserAccount VALUES ('alfonzoshimothy@gmail.com', 'alfonzoChecking');

INSERT INTO BankAccount VALUES ('Alshimzo', 'checking', 999.00);
INSERT INTO UserAccount VALUES ('alfonzoshimothy@gmail.com', 'Alshimzo');
INSERT INTO UserAccount VALUES ('shimjay1@gmail.com', 'Alshimzo');

INSERT INTO AccountTransaction VALUES ('Things were added here first', timestamp '2018-08-21 09:25:50.00', 'shimChecking');
INSERT INTO AccountTransaction VALUES ('Things were added here second', timestamp '2018-08-21 09:26:50.00', 'shimChecking');
INSERT INTO AccountTransaction VALUES ('Things were added here third', timestamp '2018-08-21 09:27:50.00', 'shimChecking');
INSERT INTO AccountTransaction VALUES ('Things were added here fourth', timestamp '2018-09-21 09:22:50.00', 'shimChecking');
COMMIT;

--select all users associated with specific account--
SELECT u.*
FROM BankAccount a, BankUser u, UserAccount ua
WHERE a.accountname = ua.accountname AND ua.useremail = u.useremail AND a.accountname = 'shimChecking';

SELECT message
FROM AccountTransaction
WHERE AccountName = 'shimChecking'
ORDER BY datestamp ASC;

