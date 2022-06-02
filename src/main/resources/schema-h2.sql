CREATE TABLE Trade(tradeId varchar(255) PRIMARY KEY ,version INT,  counterPartyId varchar(255),bookId varchar(255),
maturityDate date,createdDate timestamp,expired char(1));