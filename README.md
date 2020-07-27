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
* One tables: Transaction.
### Program
* 新增transaction的服务需要确保在高并发情况下： 同一个TadeId，插入操作的版本号最小， 取消的版本号最大


### Exception
* RestException include the api exceptions.

### API
API document and test： Swagger. http://localhost:8082/swagger-ui.html



## 3. Test
Data initialized in launch, include one trade and five shipments (1 root shipment, 3 split shipments, 1 merged shipment).

TestNG, include: Please start the application in advanced.
* service test cases.
* controller test cases. 

## 4. Deployment
* JDK 1.8
* Maven 3.x.
* SpringBoot jar emmbeded tomcat.
