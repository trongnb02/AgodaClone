# AgodaClone
Agoda Clone Microservices

## Service Architecture Diagram
![service-architecture](./Diagram.jpg)

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
Authorization: Bearer ...

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

Delete user:
```
DELETE: localhost:8222/api/v1/user/delete
{
    "email":"test12345@gmail.com"
}
Authorization: Bearer ...

Respone:
{
    "message": "Delete User Successfully!",
    "data": "test12345@gmail.com"
}
```

## Hotel Service

Create new hotel (without rooms):
```
POST: localhost:8222/api/v1/hotel/new
{
    "name":"hotel name test 3",
    "address": "hotel address 3",
    "city": "hotel city test 3",
    "vendorId": "vendoridtest3",
    "phone":"0192387761253",
    "rooms":[]
}

Response:
{
    "message": "Create Hotel Successfully!",
    "data": {
        "id": "a036a43c-7a94-4eff-8d4f-b52f4be1f846",
        "name": "hotel name test 3",
        "address": "hotel address 3",
        "city": "hotel city test 3",
        "phone": "0192387761253",
        "vendorId": "vendoridtest3",
        "roomsId": []
    }
}
```

Create new hotel (with new rooms):
```
POST: localhost:8222/api/v1/hotel/new
{
    "name":"hotel name test 4",
    "address": "hotel address 4",
    "city": "hotel city test 4",
    "vendorId": "vendoridtest4",
    "phone":"0192387761253",
    "rooms":[
        {
            "roomType": "Type 1",
            "price": 35,
            "capacity": 2,
            "availability": true
        },
        {
            "roomType": "Type 12",
            "price": 23,
            "capacity": 4,
            "availability": true
        }
    ]
}

Response:
{
    "message": "Create Hotel Successfully!",
    "data": {
        "id": "5951c136-5d38-4fc3-bf36-ae649dcb7a14",
        "name": "hotel name test 4",
        "address": "hotel address 4",
        "city": "hotel city test 4",
        "phone": "0192387761253",
        "vendorId": "vendoridtest4",
        "roomsId": [
            "4b92efe1-a1f7-4048-aade-92df10d11d68",
            "6ee57fa4-d178-4b96-8441-25f116aa7bcc"
        ]
    }
}
```

Get all hotels (FOR ADMIN ONLY):
```
GET: localhost:8222/api/v1/hotel/list

Response:
{
    "message": "All Hotels",
    "data": [
        {
            "id": "2acaf534-4550-4bfe-8e58-5607bbfcf298",
            "creationTimestamp": "2025-02-28T13:31:53.113639",
            "createdBy": null,
            "updateTimestamp": "2025-02-28T13:31:53.113639",
            "lastModifiedBy": null,
            "name": "hotel name test 5",
            "address": "hotel address 5",
            "city": "hotel city test 5",
            "phone": "0192387761253",
            "vendorId": "vendoridtest5",
            "rooms": []
        },
        {
            "id": "5951c136-5d38-4fc3-bf36-ae649dcb7a14",
            "creationTimestamp": "2025-02-28T13:30:49.278884",
            "createdBy": null,
            "updateTimestamp": "2025-02-28T13:30:49.278884",
            "lastModifiedBy": null,
            "name": "hotel name test 4",
            "address": "hotel address 4",
            "city": "hotel city test 4",
            "phone": "0192387761253",
            "vendorId": "vendoridtest4",
            "rooms": [
                {
                    "id": "6ee57fa4-d178-4b96-8441-25f116aa7bcc",
                    "creationTimestamp": "2025-02-28T13:30:49.301821",
                    "createdBy": null,
                    "updateTimestamp": "2025-02-28T13:30:49.301821",
                    "lastModifiedBy": null,
                    "roomType": "Type 1",
                    "price": 35.0,
                    "capacity": 2,
                    "availability": true
                },
                {
                    "id": "4b92efe1-a1f7-4048-aade-92df10d11d68",
                    "creationTimestamp": "2025-02-28T13:30:49.308462",
                    "createdBy": null,
                    "updateTimestamp": "2025-02-28T13:30:49.308462",
                    "lastModifiedBy": null,
                    "roomType": "Type 12",
                    "price": 23.0,
                    "capacity": 4,
                    "availability": true
                }
            ]
        }
    ]
}
```

Get hotel by id:
```
GET: localhost:8222/api/v1/hotel/2acaf534-4550-4bfe-8e58-5607bbfcf298/detail

Response:
{
    "message": "Hotel Detail",
    "data": {
        "id": "2acaf534-4550-4bfe-8e58-5607bbfcf298",
        "name": "hotel name test 5",
        "address": "hotel address 5",
        "city": "hotel city test 5",
        "phone": "0192387761253",
        "vendorId": "vendoridtest5",
        "roomsId": [
            "852f161e-ba76-4b1c-aa0a-3a6654df0349",
            "56f2afa9-fa14-4294-83b2-130cb56ea39e"
        ]
    }
}
```

Add new room to hotel:
```
POST: localhost:8892/api/v1/hotel/2acaf534-4550-4bfe-8e58-5607bbfcf298/addroom
{
    "roomType": "Type 8 test",
    "price": 22,
    "capacity": 8,
    "availability": true
}

Response:
{
    "message": "Add new room to hotel successfully!",
    "data": {
        "id": "2acaf534-4550-4bfe-8e58-5607bbfcf298",
        "name": "hotel name test 5",
        "address": "hotel address 5",
        "city": "hotel city test 5",
        "phone": "0192387761253",
        "vendorId": "vendoridtest5",
        "roomsId": [
            "852f161e-ba76-4b1c-aa0a-3a6654df0349",
            "56f2afa9-fa14-4294-83b2-130cb56ea39e"
        ]
    }
}
```

Get room detail:
```
GET: localhost:8892/api/v1/hotel/2acaf534-4550-4bfe-8e58-5607bbfcf298/detail/852f161e-ba76-4b1c-aa0a-3a6654df0349

Response:
{
    "message": "Room Detail",
    "data": {
        "id": "852f161e-ba76-4b1c-aa0a-3a6654df0349",
        "roomType": "Type 6",
        "price": 67.0,
        "capacity": 1,
        "availability": true,
        "hotelId": "2acaf534-4550-4bfe-8e58-5607bbfcf298"
    }
}
```

List All Rooms Of hotel:
```
GET: localhost:8892/api/v1/hotel/2acaf534-4550-4bfe-8e58-5607bbfcf298/roomlist

Response:
{
    "message": "List Room Of Hotel",
    "data": [
        {
            "id": "56f2afa9-fa14-4294-83b2-130cb56ea39e",
            "roomType": "Type 8 test",
            "price": 22.0,
            "capacity": 8,
            "availability": true,
            "hotelId": "2acaf534-4550-4bfe-8e58-5607bbfcf298"
        },
        {
            "id": "852f161e-ba76-4b1c-aa0a-3a6654df0349",
            "roomType": "Type 6",
            "price": 67.0,
            "capacity": 1,
            "availability": true,
            "hotelId": "2acaf534-4550-4bfe-8e58-5607bbfcf298"
        }
    ]
}
```

Delete hotel and all its rooms:
```
DELETE: localhost:8892/api/v1/hotel/b128bbd2-a4ec-45ba-a1d7-d2380ecb05aa/delete

Response:
{
    "message": "Delete hotel and all its rooms successfully!",
    "data": null
}
```