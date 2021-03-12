# short-url-app
Overview

The assignment covers the basic implementation of Url Shortening Service. The application has 3 functional part - 
1. Mysql database - for persisting data 
2. Spring - backend Api
3. Angular - front end 

Steps for Starting up application :- 

The 3 components are dockerized. The project can be cloned from github.

Git clone https://github.com/lasitpant/short-url-app.git
Cd urlapp/
Docker-compose up -d
Docker ps ( to see 3 containers)

Mysql database : port 3306
Backend : port 8080
frontend : port 4200

Data has been added using data.sql file (inside resources folder) hence, you would see the dashboard populated.

Frontend
1. Navigate to localhost:4200


The dashboard has 2 features -
a. Shortening a Url.
b. Generic Dashboard

A. Creating a Url is a simple process, enter the long url
Validation for valid url has been configured on the client side.
Expiry date a nullable field, hence you can submit it blank.
The short-link will be shown on highlighted in green once the creation is successful. 

The dashboard gives a basic representation of stats - devices the link is being accessed from, browser and Os. 

Backend Overview

Short url generation has a simple workflow. Url submitted is saved and mapped to a key which is picked from a key table. Approach used is similar to KGS (key generation Service),random short keys are generated and when a url is created one key from the key db is picked.
There are a number of benefits for using this approach from a scaling and concurrency point of view. With every short-url generation a random new key is added to the key table which is then made available. Used keys are marked as active.



Points covered for assessment -

1. Create a short URL
2. Use the short url to view the original link.
3. Capturing browser Statistics.
4. Unit Test cases for services and controllers


Future Improvements -
The code base is designed to incorporate features -
1. Adding a user module , so that each user has his own workspace to update, delete and get stats for specific urls.
2. The Keg Generation service can be easily scaled to a separate microservice.


References-
Spring.io, medium blogs and stackoverflow.
