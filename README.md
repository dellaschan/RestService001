Pre-requisite:
- MySQL Database Server
- MySQL Workbench
- Java
- Maven
- Postman for testing


(A) Create Database & MySQL Users

1) Go to MySQL Server Directory (eg: C:\Program Files\MySQL\MySQL Server 8.0\bin) and open command line to log in to MySQL as the root user, Type the MySQL root password (default password if not changed before: root), and then press Enter:
mysql -u root -p

2) Create database:
mysql -> create database restservicedb;

3) Create user with password:
mysql -> create user 'restUser'@'localhost' identified by 'restPassword';

4) Grant privileges to user created:
mysql -> grant all privileges on restserviceDB.* to 'restUser'@'localhost';
mysql -> flush privileges;



(B) Generating jar file from zip

1) Extract the zip files into specific project folder

2) Ensure there is .mvn folder in this project folder:
- inside the .mvn folder should have a wrapper folder which consist of maven-wrapper.jar (blocked by the email) & maven-wrapper.properties
- in this case need to download maven-wrapper.jar & maven-wrapper.properties from internet

3) Open command line in project folder and execute the following command to compile and generate the jar file:
mvn clean package



(C) Once project is compiled and jar is packaged, Go to /target folder, open command line and execute the following command to run the java program:
java -jar rest-service-0.0.1-SNAPSHOT.jar



(D) Insert sample data provided by executing the db script in MySQL Workbench:

1) Connect to localhost, port 3306
2) Connection name : restservicedb
3) Username: restUser
4) Password: restPassword
5) Execute below sql script:
/dbscript/SSI_INSERT_SCRIPT.sql



(E) Testing of APIs with Postman

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