# webCountries06092021
this repo suppose to have countries project. SpringBoot web service to get Countries and States from mySQL.


Java coding challenge
 
Create a database of your desired flavor that can store simple data based on the following fictional country and state information:
 
Country
Country Code
Currency
State
State Code
State Population
Big Land
BLD
CHK
Left Province
LEF
10,100
 
 
 
Right Province
RIG
778,030
 
 
 
Topside
TSD
2,200,340
 
 
 
Center Province
CTR
1,340,922
Mordor
MDR
GUL
Udun
UDN
2,000,110
 
 
 
Gorgoroth
GOR
3,120,900
 
 
 
Nurn
NRN
1,100,000
 
 
 
Kand
KND
2,500,000
Numberland
NUM
DIG
One
ONE
1,1500,00
 
 
 
Two
TWO
25,320,000
 
 
 
Three
TRE
3,100,00
 
 
 
Four
FOR
400,000
 
Create the following web services to access this data:
 
    1. getAllCountryPopulations()
 
GET method that returns all of the countries with the total populations of each country as a JSON object keyed on country code
 
    2. getAllCountryCurrencies()
 
GET method that returns all of the countries with the currency of each country as a JSON object keyed on country code
 
    3. validateState(country_code, state_code)
 
GET method that returns a boolean indicating whether or not the supplied county code and state code combination are valid
 
    4. addState(country_cd, state_cd, state_name, state_population)
 
PUT method that will add the supplied state information to the database above
 
 
Please implement your solution in Java and send us the following:
 
    1. Source code for your solution to the above requests
    2. Schema of the database defined to hold the above data
    3. The mechanism used to populate the initial values in the database (hint: don’t hard code these entries)
    4. WAR file or Spring Boot Jar we can run in tomcat to test your code
 
If you have any questions, please use your best judgment and document any assumptions you have made.
 

I created "countries" and "states" tables in mySQL db. 
Web service able to run all commands above and population by country.

I filled tables using curl:
adding countries:
curl -X POST -d '{"countryCode":"BLD","countryName":"Big Land","currency":"CHK"}' -H 'Content-Type: application/json' http://localhost:8080/user/country
curl -X POST -d '{"countryCode":"MDR","countryName":"Mordor","currency":"GUL"}' -H 'Content-Type: application/json' http://localhost:8080/user/country
curl -X POST -d '{"countryCode":"NUM","countryName":"Numberland","currency":"DIG"}' -H 'Content-Type: application/json' http://localhost:8080/user/country
curl -X POST -d '{"countryCode":"BL2","countryName":"Big2Land","currency":"CH2"}' -H 'Content-Type: application/json' http://localhost:8080/user/country
curl -X POST -d '{"countryCode":"BL3","countryName":"Big3Land","currency":"CH3"}' -H 'Content-Type: application/json' http://localhost:8080/user/country
curl -X POST -d '{"countryCode":"BL4","countryName":"Big4Land","currency":"CH4"}' -H 'Content-Type: application/json' http://localhost:8080/user/country

update country:
curl -X PUT -d '{"countryCode":"BL4","countryName":"Bigger XXX Land","currency":"CHX"}' -H 'Content-Type: application/json' http://localhost:8080/user/country
delete country:
curl -X DELETE http://localhost:8080/user/country/BL2
curl -X DELETE http://localhost:8080/user/country/BL3

add state:
curl -X POST -d '{"stateCode":"LEF", "stateName":"Left Province", "countryCode":"BLD","population":10100}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"RIG", "stateName":"Right Province", "countryCode":"BLD","population":778030}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"TSD", "stateName":"Topside", "countryCode":"BLD","population":2200340}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"CTR", "stateName":"Center Province", "countryCode":"BLD","population":1340922}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"UDN", "stateName":"Udun", "countryCode":"MDR","population":2000110}' -H 'Content-Type: application/json' http://localhost:8080/user/state

curl -X POST -d '{"stateCode":"GOR", "stateName":"Gorgoroth", "countryCode":"MDR","population":3120900}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"NRN", "stateName":"Nurn", "countryCode":"MDR","population":1100000}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"KND", "stateName":"Kand", "countryCode":"MDR","population":2500000}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"ONE", "stateName":"One", "countryCode":"NUM","population":1150000}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"TWO", "stateName":"Two", "countryCode":"NUM","population":25320000}' -H 'Content-Type: application/json' http://localhost:8080/user/state

curl -X POST -d '{"stateCode":"TRE", "stateName":"Three", "countryCode":"NUM","population":310000}' -H 'Content-Type: application/json' http://localhost:8080/user/state
curl -X POST -d '{"stateCode":"FOR", "stateName":"Four", "countryCode":"NUM","population":400000}' -H 'Content-Type: application/json' http://localhost:8080/user/state

curl http://localhost:8080/user/addstate?country_code=BLD&state_code=RIG&state_name=Right_Province&state_population=778030

http://localhost:8080/user/totalpopulation
40230402

http://localhost:8080/user/countrypopulation?country_code=BLD
4329392

validate:
http://localhost:8080/user/validatestate?country_code=BLD&state_code=TWO
false

print country and currency:
http://localhost:8080/user/currencies
[{"countryCode":"BLD","countryCurrency":"CHK"},{"countryCode":"MDR","countryCurrency":"GUL"},{"countryCode":"NUM","countryCurrency":"DIG"}]


What is missing: 
No UI
No parameter validation
No Exception handling
No Unit Tests.

