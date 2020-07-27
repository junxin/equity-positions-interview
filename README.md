# equity-positions
Equity positions project for interview.

## 1. Reqirement
### Post transaction: 
* 新增交易下单:
* 支持并发， 同一个TadeId，插入操作的版本号最小， 取消的版本号最大。

### list all Positions:
*   实时仓位展示



## 2. Design
### Datebase
* Using H2 Database in memory.
* Two tables: Transaction.


### Exception
* RestException include the api exceptions.

### API
API document and test： Swagger. http://localhost:8080/swagger-ui.html

API response class: ModelJsonRespone.
* code：0 = error, 1 = success, -11 = wrong param, -12 = access deney
* errorNo: exceptionNo in WebServiceException.
* errorDesc: i18n exception message.
* result: the api data according to the biz.

## 3. Test
Data initialized in launch, include one trade and five shipments (1 root shipment, 3 split shipments, 1 merged shipment).

JUnit test, include: Please start the application in advanced.
* service test cases.
* WebServiceException test cases.
* controller test cases. 

## 4. Deployment
* JDK 1.8
* Maven 3.x.
* SpringBoot jar emmbeded tomcat.
