/*THE STATEMENTS ARE AT THE BOTTOM*/
create table theCarAttributes(
    fueltype char(25) not null, 
    doornumber char(25) not null,
    carbody char(25) not null, 
    drivewheel char(25) not null, 
    enginelocation char(25) not null, 
    wheelbase decimal(8,2) not null, 
    carlength decimal(8,2) not null, 
    carwidth decimal(8,2) not null, 
    carheight decimal(8,2) not null,
    curbweight decimal(8,0) not null, 
    enginetype char(25) not null, 
    cylindernumber char(25) not null, 
    enginesize decimal(8,0) not null, 
    horsepower decimal(8,0) not null, 
    citympg decimal(8,0) not null, 
    highwaympg decimal(8,0) not null
);

INSERT INTO thecarAttributes(fueltype, doornumber, carbody, drivewheel, enginelocation, wheelbase, carlength, carwidth, carheight, curbweight, enginetype, cylindernumber, enginesize, horsepower, citympg, highwaympg)
SELECT fueltype, doornumber, carbody, drivewheel, enginelocation, wheelbase, carlength, carwidth, carheight, curbweight, enginetype, cylindernumber, enginesize, horsepower, citympg, highwaympg
FROM carPrediction
GROUP BY fueltype,doornumber, carbody, drivewheel, enginelocation, wheelbase, carlength, carwidth, carheight, curbweight, enginetype, cylindernumber, enginesize, horsepower, citympg, highwaympg


SELECT *
FROM
( 
    SELECT condition, cylinders, fuel, title_status, transmission, drive, size, type, paint_color
    FROM usedCarPosting
    GROUP BY condition, cylinders, fuel, title_status, transmission, drive, size, type, paint_color
)tbl1,
(
    SELECT  Vehicle_type, Engine_size, Horsepower, Wheelbase, Width, Length, Curb_weight, Fuel_capacity, Fuel_efficiency
    FROM newCarsSold
    GROUP BY Vehicle_type, Engine_size, Horsepower, Wheelbase, Width, Length, Curb_weight, Fuel_capacity, Fuel_efficiency
)tbl2
/*(
    SELECT title_status, color
    FROM usCarData
    GROUP BY title_status, color
)tbl3,*/
(
    SELECT fueltype, doornumber, carbody, drivewheel, enginelocation, wheelbase, carlength, carwidth, carheight, curbweight, enginetype, cylindernumber, enginesize, horsepower, citympg, highwaympg
    FROM carPrediction
    GROUP BY fueltype,doornumber, carbody, drivewheel, enginelocation, wheelbase, carlength, carwidth, carheight, curbweight, enginetype, cylindernumber, enginesize, horsepower, citympg, highwaympg
)tbl4
/*(
    SELECT BodyStyle, Seats, PowerTrain
    FROM eletricVehicles
    GROUP BY BodyStyle, Seats, PowerTrain
)tbl5*/




SELECT price, year, manufacturer, model, condition, cylindernumber, fuel, odometer, title_status, transmission, drive, size, type, paint_color
FROM usedCarPosting



DROP TABLE theCarAttributes;

FROM carPrediction
CREATE TABLE theCarAndAttributes
AS (SELECT *
    FROM carPrediction);

Select * into theCarAndAttributes  from  carPrediction

CREATE TABLE suppliers
  AS (SELECT id, address, city, state, zip
      FROM companies
      WHERE id > 1000);

SELECT * 
INTO theCarAndAttributes 
FROM (SELECT fueltype
        FROM carPrediction)

SELECT manufacturer, model, type AS carBody, year
FROM usedCarPosting

SELECT Brand, model, TopSpeed, Range, BodyStyle, Seats, PriceEuro
FROM eletricVehicles

SELECT Manufacturer AS manufacturer, Model AS model,Vehicle_type, Price_in_thousands, enginesize, horsepower
FROM newCarsSold

SELECT fueltype, aspiration, doornumber, carbody, drivewheel, enginelocation, wheelbase, carlength, carwidth, carheight, curbweight, enginetype, cylindernumber, enginesize, horsepower, citympg, highwaympg
FROM carPrediction

SELECT *
FROM usCarData

DROP Table electricVehicles;

/*THE QUIRES FROM UML*/
-- CREATE A ATTRIBUTE TABLE SO 1 CAR CAN HAVE MANY ATRIBUTE AND 1 ATTRIBUTE CAN HAVE MANY CARS
CREATE TABLE theCarAttributes(
    condition char(25), 
    cylinders char(25),
    fuel char(25), 
    title_status char(25), 
    transmission char(25), 
    drive char(25), 
    size char(25), 
    type char(25), 
    paint_color char(25) 
);

INSERT INTO thecarAttributes(condition, cylinders, fuel, title_status, transmission, drive, size, type, paint_color)
SELECT condition, cylinders, fuel, title_status, transmission, drive, size, type, paint_color
FROM usedCarPosting
GROUP BY condition, cylinders, fuel, title_status, transmission, drive, size, type, paint_color

DROP TABLE theCarAttributes
-- BUYER BUYS VEHICLE DELETEING A TUPLE --DO NOT RUN  
DELETE FROM usedCarPosting
WHERE manufacturer = 'ford' AND -- ford and f-f150 and selected ID is user input
    model = 'f-150' AND
    year  = 2012 AND
    id = 7184711787
-- SELLER WANTING TO SELL VEHICLE INSERTING A TUPLE --DO NOT RUN 
INSERT INTO usedCarPosting 
VALUES(id, url, region, region_url, price, year, manufacturer, model, condition, cylinders, fuel, odometer, title_status, transmission, vin, drive, size, type, paint_color, image_url, county, state, lat, long) 
--WANT TO ADD A COLUMN CALLED VIEWS TO THE USED CAR TABLE, DID NOT IMPLEMENT IN OTHER TABLES
--BUT YOU ADD ONE EVERY TIME A SELECT CAR IS USED
ALTER TABLE usedCarPosting
ADD views DECIMAL(8,0)
-- SELLER WANTS TO KNOW HOW MANY PEOPLE HAVE SEEN THEIR POSTINGS
SELECT views
FROM usedCarPosting
WHERE id = 7184711787
-- BUYER CAN LIKE A USED CAR POSTING AND SAVE IT IN THE LIKE TABLE
CREATE TABLE liked(
    id DECIMAL(15,0), 
    url char(500), 
    region char(25), 
    region_url char(10),
    price DECIMAL(8,0), 
    year DECIMAL(4,0),
    manufacturer char(25), 
    model char(25), 
    condition char(25), 
    cylinders char(25), 
    fuel char(25), 
    odometer DECIMAL(15,0), 
    title_status char(25), 
    transmission char(25), 
    vin char(25), 
    drive char(25),
    size char(25), 
    type char(25), 
    paint_color char(25), 
    image_url char(500),
    description char(5000), 
    county char(25), 
    state char(25), 
    lat DECIMAL(8,8), 
    long DECIMAL(8,8),
    views DECIMAL(8,0)
);
DROP TABLE liked;
INSERT INTO liked(id, url, region, region_url,price, year,manufacturer, model, condition, cylinderS, fuel, odometer , title_status, transmission, vin, drive,size, type, paint_color, image_url,description, county, state, lat, long, views )
SELECT *
FROM usedCarPosting
WHERE id = 7184711787
-- BUYER SELECT MANUFACTURER AND LIST USED CARS AND PRICE
SELECT manufacturer, model, price, year
FROM usedCarPosting
WHERE manufacturer = 'ford' --ford is user input

--BUYER SELECT SPECFIC USED CARS BY SELECTING MANUFACTURER AND MODEL
SELECT /*manufacturer, model, price, year*/ *
FROM usedCarPosting
WHERE manufacturer = 'ford' AND -- ford and f-f150 is user input
    model = 'f-150'

--BUYER VIEW THE POPULARITY OF CAR BY VIEWING THE NUMBER OF NEW CARS SOLD 
SELECT Sales_in_thousands
FROM newCarsSold
WHERE Manufacturer = 'ford' AND --Input is ford and F-Series
    Model = 'F-Series'
--BUYER/SELLER SELECT ATTRIBUTE TO DETERMINE THE RIGHT PRICE FOR CAR
SELECT AVG(Distinct(price))
FROM theCarAttributes, usedCarPosting
WHERE theCarAttributes.type = 'pickup' AND
    theCarAttributes.paint_color = 'blue' AND
    usedCarPosting.type = theCarAttributes.type AND
    usedCarPosting.paint_color = theCarAttributes.paint_color
--BUYER WANTS SEPCIFIC ATTRIBUTE IN USED CARS AND COMPARE IT TO NEW CARS
/*SELECT newCarsSold.Manufacturer, newCarsSold.Model, newCarsSold.Price_in_thousands, tbl1.manufacturer, tbl1.model, tbl1.year, tbl1.price
FROM(
    SELECT LOWER(manufacturer) AS manufacturer, LOWER(model) AS model,(year), price
    FROM usedCarPosting
    WHERE condition = 'excellent'
)tbl1,
newCarsSold
WHERE tbl1.manufacturer = newCarsSold.Manufacturer AND
    tbl1.model = newCarsSold.Model*/
-- BUYER WANTS TO COMPARE THE USED CAR WITH THE NEW CAR PRICE
SELECT *
FROM
    (SELECT (newCarsSold.Price_in_thousands * 1000) AS priceOfNewCar
    FROM  newCarsSold
    WHERE LOWER(newCarsSold.Manufacturer) = 'bmw' AND
        LOWER(newCarsSold.Model) = '323i')tbl1, 
    (SELECT AVG(price) AS priceOfUsedCar
    FROM usedCarPosting
    WHERE manufacturer = 'bmw' AND model = '323i')tbl2
--BUYER WHO WANTS TO BUY USED ELETRIC CAR
SELECT id, manufacturer, model, year, price
FROM usedCarPosting
WHERE fuel = 'electric'
--BUYER WHO WANTS A SPECFIC ATTRIBUTE IN THEIR ELETRIC CAR
SELECT Brand, Model
FROM eletricVehicles
WHERE CAST(SUBSTR(Range,1,3) AS INT) < 400



--view cars sold > 10k and in 2011
SELECT Manufacturer,Model,Sales_in_thousands
FROM newCarsSold
WHERE Sales_in_thousands > 10.00
AND Latest_Launch LIKE '%2011%'
--get avg price of all cars in ca that are dodge and newer than 2014
SELECT AVG(price), brand, model
FROM usCarData
WHERE year >= 2014
AND brand LIKE '%dodge%'
AND state LIKE'%california%'
GROUP BY model
--get the min price for every convertible
SELECT CarName as convertibles, price
FROM carPrediction
WHERE carbody = 'convertible'
GROUP BY CarName HAVING MIN(price)
--get all the models that are in excellent condition and are red
SELECT manufacturer,model
FROM usedCarPosting
WHERE condition LIKE '%excellent%'
AND paint_color LIKE '%red%'
AND region = 'albuquerque'
GROUP BY manufacturer
--get the type of fuel used by a certain brand car
SELECT*
FROM(SELECT DISTINCT fueltype,aspiration
FROM carPrediction
WHERE CarName LIKE '%NISSAN%')tlb1,
(SELECT Brand,Model,TOPSpeed,Range
FROM eletricVehicles
WHERE Brand LIKE '%NISSAN%'
GROUP BY Model
)tbl2
 
-- get all the cars in usa and the model/price
SELECT newCarsSold.Model as model, usCarData.price as price
FROM newCarsSold, usCarData
WHERE newCarsSold.Manufacturer LIKE usCarData.brand
AND usCarData.country LIKE '%usa%'
AND newCarsSold.Horsepower > 175
 
--get the fuel type of the car and yr and the resell value
SELECT SUBSTR("Fuel Type",0,10) as fuelType, Year, vehiclefuelEconomy.Make,vehiclefuelEconomy.Model, newCarsSold.__year_resale_value
FROM vehiclefuelEconomy,newCarsSold
WHERE newCarsSold.Manufacturer = vehiclefuelEconomy.Make
AND newCarsSold.Fuel_capacity > 15.0
GROUP BY vehiclefuelEconomy.Make
 
--all the cars in ca, get the actual price and the predicted price to compare
SELECT COUNT(usedCarPosting.state) as carsInCA,AVG(usedCarPosting.price) as avgPrice,AVG(carPrediction.price) as avgPredictionPrice
FROM usedCarPosting,carPrediction
WHERE usedCarPosting.model = carPrediction.CarName
AND usedCarPosting.state LIKE '%ca%'