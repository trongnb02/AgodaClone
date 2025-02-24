# AgodaClone
Agoda Clone Microservices

## Auth Service

Get JWT token by credential:
```
POST: localhost:8222/api/v1/auth/login
{
    "username":"customer1-test12345",
    "email":"test12345@gmail.com",
    "password":"Test12345678"
}

Response:
{
    "message": "Login Success!",
    "data": {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjEtdGVzdDEyMzQ1IiwiaXNzIjoiUk9MRV9DVVNUT01FUiIsImlhdCI6MTc0MDM5ODk2NCwiZXhwIjoxNzQwNDcwOTY0fQ.HLnr1RQDEY_h0AunwNKFxm7Ly3wV3SBRStYYIwY1-k0"
    }
}
```

Validate token:
```
POST: localhost:8222/api/v1/auth/validate
{
    "token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjdXN0b21lcjEtdGVzdDEyMzQ1IiwiaXNzIjoiUk9MRV9DVVNUT01FUiIsImlhdCI6MTc0MDM4OTYzNiwiZXhwIjoxNzQwNDYxNjM2fQ.E9CHMR4ePvUBecEqHpxlNySDdwTGfxOFvvQx6BPg00"
}

Response:
{
    "message": "Successfully validated",
    "data": true
}
```

## User Service

Create customer:
```
POST: localhost:8222/api/v1/user/register
{
    "username":"customer12-test123456",
    "email":"test123456@gmail.com",
    "password":"Test12345678"
}

Response:
{
    "message": "Success!",
    "data": {
        "id": "c2a180a4-b524-4176-bc9a-ebff31b9260b",
        "username": "customer12-test123456",
        "email": "test123456@gmail.com",
        "password": "$2a$10$gZQQ/uu216d0oy7Iz7BGheBH0qv6bMQAC.U9kdbk1CGQOZfJ5B4qK",
        "role": "CUSTOMER"
    }
}
```

Update User by email:
```
POST: localhost:8222/api/v1/user/update
{
    "email":"test123@gmail.com",
    "userDetails":{
        "firstName": "customer fn",
        "lastName": "customer ln",
        "phoneNumber": "09999999",
        "country": "VietNam",
        "city": "HCM",
        "address": "addressdetails",
        "profilePicture":"test"
    }
}

Response:
{
    "message": "Success!",
    "data": {
        "id": "705fb64b-25df-4380-9e78-8dca7c449c75",
        "username": "customer1",
        "email": "test123@gmail.com",
        "userDetails": {
            "firstName": "customer fn",
            "lastName": "customer ln",
            "phoneNumber": "09999999",
            "country": "VietNam",
            "city": "HCM",
            "address": "addressdetails",
            "profilePicture": "test"
        }
    }
}
```

Get user information from email (internal):
```
GET: localhost:8222/api/v1/user/getUserByEmail/test1233@gmail.com

Response:
{
    "id": "42aed8a3-2400-4863-9be6-cff589637061",
    "username": "customer2",
    "email": "test1233@gmail.com",
    "password": "$2a$10$pHnl1QJ2TxQhwn1hVD8Nau/8f00bqGzPrxHGodXmYEfPT1WDkAd5.",
    "role": "CUSTOMER"
}
```
