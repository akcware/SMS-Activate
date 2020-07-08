# SMS-Activate
SMS Activate API Library for sms-activate.ru/ with Java Language.

# Usage
If you want to use this in your project just import the jar file that is in ```/dist/SMSActivate.jar``` to your project. Or If you want to edit the codes, edit the java file that 
is in ```/src/SMSActivate.java```.

## Codes 

### First create a class with your api key:
```
public class Main {
  static final String api_key = ""; // API Key Here
  
  public static void main(String[] args) {
    SMSActivate smsActivate = new SMSActivate(api_key);
  }
}
```

You can get all stocks with you selected country. Countries works with numbers of country that is in the documentation of sms-activate.ru api page. 0 is for Russia.
```Map<String, Object> numbers = smsActivate.getAllNumbers("0");``` 

### Get balance
```
String balance = smsActivate.getBalance();
```

### Order Number
Order Number with country and service. Services are social medias that you want to order a number for. Example for twitter service code is "tw". You will use ```accessID```
to getting status of delivery. (SMS received, SMS waiting, etc.)
```
String[] access = smsActivate.orderNumber("0", "tw");

if (access =! null) {
  String accessID = access[0]; // This is for getting status of messages
  String accessNumber = access[1]; // This is the phone number that you ordered.
} else System.err.println("Ordered number access is null!");
```

### Get Status of number
After you get the accessID, you can learn your sms status everytime.
```
String status = smsActivate.getStatus(accessID);


