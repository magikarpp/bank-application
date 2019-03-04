--DROP TABLE BankUser;
--DROP TABLE BankAccount;

DELETE
FROM BankUser;

DELETE
FROM BankAccount;

DELETE
FROM UserAccount;

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
    AccountName VARCHAR2(50) NOT NULL,
    CONSTRAINT PK_UserAccount PRIMARY KEY (UserEmail, AccountName)
);

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
COMMIT;

--select all users associated with specific account--
SELECT u.*
FROM BankAccount a, BankUser u, UserAccount ua
WHERE a.accountname = ua.accountname AND ua.useremail = u.useremail AND a.accountname = 'Alshimzo';




