Pre-requisite:
- MySQL Database Server
- Java
- Postman for testing

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
(A)Create Database & MySQL Users

1) Go to MySQL Server Directory (eg: C:\Program Files\MySQL\MySQL Server 8.0\bin) and open command line to log in to MySQL as the root user, Type the MySQL root password (default password if not changed before: root), and then press Enter:
mysql -u root -p

2) Create database:
mysql -> create database restservicedb;

3) Create user with password:
mysql -> create user 'restUser'@'localhost' identified by 'restPassword';

4) Grant privileges to user created:
mysql -> grant all privileges on restserviceDB.* to 'restUser'@'localhost';
mysql -> flush privileges;
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
(B)Extract the jar files into specific folder and execute bwith the following command in command line:
java -jar rest-service-0.0.1-SNAPSHOT.jar
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
(C)Insert sample data provided by execute the db script:
/dbscript/SSI_INSERT_SCRIPT.sql
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
(D)Testing of APIs with Postman or command line

GET : http://localhost:8080/api/v1/msm/{tradeId}
Sample response:
{
    "status": {
        "statusCode": 200,
        "statusText": "OK"
    },
    "data": {
        "tradeId": "1234567",
        "amount": 123.45,
        "currency": "USD",
        "valueDate": "20022020",
        "supportingInformation": "BNF:FFC-4697132",
        "payerParty": {
            "accountNumber": "438421",
            "bankCode": "OCBCSGSGXXX"
        },
        "receiverParty": {
            "accountNumber": "05461368",
            "bankCode": "DBSSGB2LXXX"
        }
    }
}

POST : http://localhost:8080/api/v1/msm
Sample response:
{
    "status": {
        "statusCode": 201,
        "statusText": "CREATED"
    },
    "data": {
        "tradeId": "123456",
        "amount": 123.45,
        "currency": "USD",
        "valueDate": "20022020",
        "supportingInformation": "BNF:FFC-482315",
        "payerParty": {
            "accountNumber": "438421",
            "bankCode": "OCBCSGSGXXX"
        },
        "receiverParty": {
            "accountNumber": "05461369",
            "bankCode": "DBSSSGSGXXX"
        }
    }
}
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
