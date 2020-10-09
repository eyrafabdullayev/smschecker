# smschecker

The function of the application is add a new sms to the db and constantly check unprocessed
sms and send these to the appropriate clients.

### Client side:
GET: /sms/send?msisdn={msisdn}&sender={senderName}&text={messageBody} 

Response: \
Success (200): 
{
“code”: 0 ,
“message”: “OK”
} \
Fail (500): 
{
“code”: 1,
“message”: “FAIL”
}

### Server side:
POST: /send \
Parameters: (They are same with db columns) \
msisdn : string. Validate this msisdn that fit with msisdn format. 
Msisdn format: start with 994 then(10 or 50 or 51 or 55 or 60 or 70 or 77 or 99 ) then 7 digits.\
senderName : string \
messageBody : string \

Response: \
HTTP response (success): 201 (Created) \
HTTP response (fail): 500 (Internal server error)

## Running

``$ docker run -i -p 8080:8080 eyrafabdullayev/smschecker:latest ``
