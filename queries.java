import java.sql.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.File;
import java.io.*;
import java.util.Scanner;

import org.sqlite.util.StringUtils;

import java.util.*;

public class queries {
    private Connection c = null;
    private String dbName;
    private boolean isConnected = false;
    public static Scanner scan = new Scanner(System.in);

    private void openConnection(String _dbName) {
        dbName = _dbName;

        if (false == isConnected) {
            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println("Open database: " + _dbName);

            try {
                String connStr = new String("jdbc:sqlite:");
                connStr = connStr + _dbName;

                // STEP: Register JDBC driver
                Class.forName("org.sqlite.JDBC");

                // STEP: Open a connection
                c = DriverManager.getConnection(connStr);

                // STEP: Diable auto transactions
                c.setAutoCommit(false);

                isConnected = true;
                System.out.println("success");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }

    private void closeConnection() {
        if (true == isConnected) {
            System.out.println("++++++++++++++++++++++++++++++++++");
            System.out.println("Close database: " + dbName);

            try {
                // STEP: Close connection
                c.close();

                isConnected = false;
                dbName = "";
                System.out.println("success");
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("++++++++++++++++++++++++++++++++++");
        }
    }
    private void Q1(String ID) {
        try {
            Statement stmt = c.createStatement();
            String sql1 = "SELECT year, manufacturer, model, price "+
                            "FROM usedCarPosting "+
                            "WHERE id = "+ ID;
            String sql = "DELETE FROM usedCarPosting " +
                            "WHERE id = " + ID;
            PreparedStatement stmt1 = c.prepareStatement(sql1);
            ResultSet rs = stmt1.executeQuery();

            while(rs.next()){
                int year = rs.getInt("year");
                float price = rs.getFloat("price");
                String manufacturer = rs.getString("manufacturer");
                String model = rs.getString("model");
                if (manufacturer == " " && model == " "){
                    System.out.println("You entered a invalid ID");    
                }else{
                System.out.println("You bought a " + year + " " + manufacturer + " " + model + " for " + price);
                stmt.execute(sql);
                c.commit();
                stmt.close();  
                stmt1.close(); 
            }  
        }  

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

    }
    private void Q2(String ID, String url, String region, String regionUrl, Float price, Integer year, String manufacturer, String model, String condition, String cylinders, String fuel, Integer odometer, String titleStatus, String transmission, String vin, String drive, String size, String type, String color, String imageUrl, String county, String state, Float latitude, Float longitue) {
        try {
            Statement stmt = c.createStatement();
            String sql = "INSERT INTO usedCarPosting " +
            "VALUES( " + ID + " , " + url + ", " + region + ", " + regionUrl + ", " + price + ", " + year + ", " + manufacturer + ", " + model + ", " + condition + ", " + cylinders + ",  "+ fuel + ",  " + odometer + ", " + titleStatus + ", " + transmission + ", " + vin + ", " + drive + ", " + size + ", " + type + ","+ color + ","+ imageUrl + ", " + county + ","+ state +","+ latitude + "," + longitue + ", NULL) ";

                            
            stmt.execute(sql);
            c.commit();
            stmt.close();

        System.out.println("You posted your used vehicle, your ID is " + ID);    

            // rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

    }
    private void Q3(String ID) {
        try {
            Statement stmt = c.createStatement();
            Statement stmt1 = c.createStatement();
            String sql = "INSERT INTO liked(id, url, region, region_url, price, year, manufacturer, model, condition, cylinderS, fuel, odometer , title_status, transmission, vin, drive,size, type, paint_color, image_url,description, county, state, lat, long, views ) " +
                            "SELECT * " +
                            "FROM usedCarPosting " +
                            "WHERE id = " + ID;
            String sql1 = "UPDATE usedCarPosting " +
                            "set views = views + 1 " +
                            "WHERE id = "+ ID;
            // PreparedStatement stmt1 = c.prepareStatement(sql1);                 
            stmt1.execute(sql1);
            stmt.execute(sql);
            
            c.commit();
            stmt.close();
            stmt1.close();

        System.out.println("You Liked a vehicle with ID " + ID);    

            // rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

    }
    private void Q4(String ID) {
        try {
            Statement stmt = c.createStatement();
            String sql = "DELETE FROM liked " +
                            "WHERE id =  " + ID;
                            
            stmt.execute(sql);
            c.commit();
            stmt.close();

        System.out.println("You unliked a vehicle with ID " + ID);    

            // rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

    }
    private void Q5() {
        try {

            String sql = "SELECT id ,url ,region ,region_url, price, year, manufacturer, model, condition, cylinders, fuel, odometer, title_status, transmission, vin, drive, size, type, paint_color, image_url, description, county, state, lat, long, views "+
                            "FROM liked ";
            PreparedStatement stmt = c.prepareStatement(sql);  
            ResultSet rs = stmt.executeQuery(); 
            while(rs.next()){
                String id = rs.getString("id");
                String url  = rs.getString("url");
                String region  = rs.getString("region");
                String region_url = rs.getString("region_url");
                String price = rs.getString("price");
                String year = rs.getString("year");
                String manufacturer = rs.getString("manufacturer");
                String model = rs.getString("model");
                String condition = rs.getString("condition");
                String cylinders = rs.getString("cylinders");
                String fuel = rs.getString("fuel");
                String odometer = rs.getString("odometer");
                String title_status = rs.getString("title_status");
                String transmission = rs.getString("transmission");
                String vin = rs.getString("vin");
                String drive = rs.getString("drive");
                String size = rs.getString("size");
                String type = rs.getString("type");
                String paint_color = rs.getString("paint_color");
                String image_url = rs.getString("image_url");
                String description = rs.getString("description");
                String county = rs.getString("county");
                String state = rs.getString("state");
                String lat = rs.getString("lat");
                String longitute = rs.getString("long");
                String views = rs.getString("views");
                System.out.println( id +"|"+ url +"|"+ region +"|"+ region_url +"|"+ price +"|"+ year +"|"+ manufacturer +"|"+ model +"|"+ condition +"|"+ cylinders +"|"+ fuel +"|"+ odometer +"|"+ title_status +"|"+ transmission +"|"+ vin +"|"+ drive +"|"+ size +"|"+ type +"|"+ paint_color +"|"+ image_url +"|"+ description +"|"+ county +"|"+ state +"|"+ lat +"|"+ longitute +"|"+ views );
                System.out.println(" ");

            }
            c.commit();
            stmt.close();               
            rs.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

    }
    private void Q6(String ID) {
        try {
            Statement stmt = c.createStatement();
            String sql = "SELECT views " +
                            "FROM usedCarPosting " +
                            "WHERE id = " + ID;
            ResultSet rs =stmt.executeQuery(sql);              
            while(rs.next()){
                Integer views = rs.getInt("views");
                System.out.println("your posting has "+ views + " views");
             }
             
             
             rs.close();
             stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

    }
    private void Q7(String ID) {
        try {
            Statement stmt = c.createStatement();
            String sql = "SELECT url " +
                            "FROM usedCarPosting " +
                            "WHERE id = " + ID;
            ResultSet rs =stmt.executeQuery(sql);              
            while(rs.next()){
                String url = rs.getString("url");
                System.out.println("Go to this website "+ url);
             }

             rs.close();
             stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }

    }

//works
    private void evStats(String brand){
        try{
            Statement stmt;
            stmt = c.createStatement();
            String sql = "SELECT Brand, model, TopSpeed, Range, BodyStyle, Seats, PriceEuro FROM eletricVehicles WHERE Brand LIKE '%" + brand +"%'";
            ResultSet rs =stmt.executeQuery(sql);


            // PreparedStatement stmt = c.prepareStatement(sql);
            // ResultSet rs = stmt.executeQuery();

            while(rs.next()){
               brand = rs.getString("Brand");
                String model = rs.getString ("model");
                String topSpeed = rs.getString("TopSpeed");
                String Range = rs.getString("Range");
                String BodyStyle = rs.getString("BodyStyle");
                String Seats= rs.getString("Seats");
                String Price= rs.getString("PriceEuro");
                System.out.println("The Brand "+ brand + "has these stats:");
                System.out.println("model " + model);
                System.out.println("TopSpeed " + topSpeed);

            }
            //stmt.execute(sql);
            
            rs.close();
			stmt.close();

                }

                catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    try {
                        c.rollback();
                    } catch (Exception e1) {
                        System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                    }
                }

    }

    private void priceOfNewCar(String manufacturer,String model,String price){ //uses new Cars Sold data
        try{
            Statement stmt;
            stmt = c.createStatement();
            String sql = "SELECT DISTINCT Manufacturer AS manufacturer, Model AS model,Vehicle_type, Price_in_thousands as price, Engine_size, Horsepower "
             +" FROM newCarsSold WHERE manufacturer LIKE '%"+ manufacturer+"%' AND model LIKE '%" + model + "%' AND price < " + price + "";
            ;
            ResultSet rs =stmt.executeQuery(sql);

            while(rs.next()){
                manufacturer = rs.getString("manufacturer");
                 model = rs.getString ("model");
                String vType = rs.getString("Vehicle_type");
                 price= rs.getString("price");
                String eSize = rs.getString("Engine_size");
                String hpower= rs.getString("HorsePower");
                
                //System.out.println();
                System.out.print("Manufacturer " +manufacturer);
                System.out.print("|Model " + model);
                System.out.print("| TYPE " + vType);
                System.out.print("| PRICE " + price);
                System.out.print("| ENGINE " + eSize);
                System.out.print("| POWER " + hpower);
                System.out.println();

            }
            //stmt.execute(sql);
            
            rs.close();
			stmt.close();

                }

                catch (Exception e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    try {
                        c.rollback();
                    } catch (Exception e1) {
                        System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                    }
                }

    }

    //not tested yet
    private void popularityOfCar(String Manufacturer,String Model){
        try{
            Statement stmt;
            stmt = c.createStatement();
            String sql = "SELECT Sales_in_thousands " + 
            " FROM newCarsSold WHERE Manufacturer LIKE '%" + Manufacturer + "%' AND Model LIKE '%" + Model +"%'";
            ResultSet rs =stmt.executeQuery(sql);

            while(rs.next()){
               String sales= rs.getString("Sales_in_thousands");
               //Manufacturer = rs.getString("Manufacturer");
               // Model = rs.getString("Model");
                System.out.println("Times this MODEL:  has been sold: " + sales);
                System.out.println();
            }

        }

        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }
    }

        //not tested yet 
        private void comparingOldNew(String manufacturer,String model){
            try{
                Statement stmt;
                stmt = c.createStatement();
                String sql = "SELECT *  " + 
                " FROM (SELECT (newCarsSold.Price_in_thousands * 1000) AS priceOfNewCar " + 
                " FROM  newCarsSold WHERE LOWER(newCarsSold.Manufacturer) LIKE '%"+ manufacturer+"%' " +
                " AND LOWER(newCarsSold.Model) LIKE '%" + model +"%' )tbl1, " + 
                " (SELECT AVG(price) AS priceOfUsedCar FROM usedCarPosting " + 
                "  WHERE manufacturer LIKE '%"+ manufacturer+ "%' AND model LIKE '%" + model +"%')tbl2 "; 
                ResultSet rs =stmt.executeQuery(sql);
    
                while(rs.next()){
                    String newCar = rs.getString("priceOfNewCar");
                    String oldCar = rs.getString("priceOfUsedCar");
                    
                    //System.out.println();
                    System.out.println("--Here is the comparison of OLD VS NEW--");
                    System.out.print("NEW CAR COSTS  " + newCar + " | ");
                    System.out.print("| OLD CAR COSTS " + oldCar + " | ");
                    System.out.println();
    
                }
    
            }
            catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                try {
                    c.rollback();
                } catch (Exception e1) {
                    System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
                }
            }
        }

           //not tested yet 
    private void minPriceForBody(String carBody){ //test using carBody = convertible
        try{
            Statement stmt;
            stmt = c.createStatement();
            String sql = " SELECT CarName , price " +
            " FROM carPrediction WHERE carbody = '" + carBody + "' GROUP BY CarName HAVING MIN(price)";
            ResultSet rs =stmt.executeQuery(sql);
            
            while(rs.next()){
                String con= rs.getString("CarName");
                String price = rs.getString("price");
                
                 System.out.println("--Cheapest cars of certain body type--");
                 System.out.println("BODY: " + con + "MIN PRICE: " + price);
                 System.out.println();
             }


        }

        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            try {
                c.rollback();
            } catch (Exception e1) {
                System.err.println(e1.getClass().getName() + ": " + e1.getMessage());
            }
        }
    }



    


    
    public static void main(String args[]) {
        
        queries sj = new queries();
        sj.openConnection("data/data.sqlite");
        

        System.out.println("What would you like to do?");		
        System.out.println("1. A Buyer who wants to buy a used vehicle");
        System.out.println("2. A Seller who wants to sell a used vehicle");
        System.out.println("3. Like a vehcile");
        System.out.println("4. Unlike a vehcile");
        System.out.println("5. View all your liked cars");
        System.out.println("6. Check how many people have viewed your posting");
        System.out.println("7. Go to the webPage of the car you have selected");
        System.out.println("10. SEE Electric Cars");
        System.out.println("11. SEE NEW Cars");
        System.out.println("12. Find cheapest car given type");
        System.out.println("0. To quit");
        int userInput = scan.nextInt();

        while(userInput != 0){
            if(userInput == 1){
                System.out.println("Enter ID of the vehicle you which to buy");
                String var = scan.next();
                sj.Q1(var);
                System.out.println("Select another option or press 0 to quit");
            }else if (userInput == 2){
                // id, url, region, region_url, price, year, manufacturer, model, condition, cylinders, fuel, odometer, title_status, transmission, vin, drive, size, type, paint_color, image_url, county, state, lat, long
                System.out.println("Enter the following information or enter NULL to skip");
                System.out.println("Enter a unique ID, cannot enter NULL");
                String ID = scan.next();
                System.out.println("Enter the URl of the your posting ");
                String url = scan.next();
                System.out.println("Enter the region you are from ");
                String region = scan.next();
                System.out.println("Enter the region URL ");
                String regionUrl = scan.next();
                System.out.println("Enter the price for the used vehicle ");
                Float price = scan.nextFloat();
                System.out.println("Enter the year of the vehicle ");
                Integer year = scan.nextInt();
                System.out.println("Enter the manufacturer of the vehicle ");
                String manufacturer = scan.next();
                System.out.println("Enter the model of the vehilce  ");
                String model = scan.next();
                System.out.println("Enter the condition of the vehicle ");
                String condition = scan.next();
                System.out.println("Enter the number cylinders of the vehicle ");
                String cylinders = scan.next();
                System.out.println("Enter power/fuel for the vehicle, ex Gas, Hybrid, Diesel ");
                String fuel = scan.next();
                System.out.println("Enter the odometer for the vehicle ");
                Integer odometer = scan.nextInt();
                System.out.println("Enter the title_status ");
                String titleStatus = scan.next();
                System.out.println("Enter the transmission ");
                String transmission = scan.next();
                System.out.println("Enter the vin ");
                String vin = scan.next();
                System.out.println("Enter the drive ");
                String drive = scan.next();
                System.out.println("Enter the size ");
                String size = scan.next();
                System.out.println("Enter the type ");
                String type = scan.next();
                System.out.println("Enter the color ");
                String color = scan.next();
                System.out.println("Enter the image url ");
                String imageUrl = scan.next();
                System.out.println("Enter the county ");
                String county = scan.next();
                System.out.println("Enter the state ");
                String state = scan.next();
                System.out.println("Enter the long ");
                Float latitude = scan.nextFloat();
                System.out.println("Enter the lat ");
                Float longitue = scan.nextFloat();
                sj.Q2(ID, url, region, regionUrl, price, year, manufacturer, model, condition, cylinders, fuel, odometer, titleStatus, transmission, vin, drive, size, type, color, imageUrl, county, state, latitude, longitue);
                System.out.println("Select another option or press 0 to quit");
            }else if(userInput == 3){
                System.out.println("Enter ID of the vehicle you wish to like and save");
                String var1 = scan.next();
                sj.Q3(var1);
                System.out.println("Select another option or press 0 to quit");
            }else if(userInput == 4){
                System.out.println("Enter ID of the vehicle you wish to unlike and unsave");
                String var2 = scan.next();
                sj.Q4(var2);
                System.out.println("Select another option or press 0 to quit");
            }else if(userInput == 5){
                sj.Q5();
                System.out.println("Select another option or press 0 to quit");
            }else if (userInput == 6){
                System.out.println("Enter ID of your posting to see how many people have viewed your post");
                String var1 = scan.next();
                sj.Q6(var1);
            }else if (userInput == 7){
                System.out.println("Enter ID of the posting to go to the website");
                String var = scan.next();
                sj.Q7(var);
                System.out.println("Select another option or press 0 to quit");
            }else if(userInput == 10){
                System.out.println("What brand do you want");
                String var = scan.next();
                sj.evStats(var);
            }else if(userInput == 11){
                System.out.println("ENTER MANUFACTURER: ");
                String man = scan.next();
                System.out.println("ENTER MODEL: ");
                String mod = scan.next();
                System.out.println("ENTER BUDGET: ");
                String bud = scan.next();

                sj.priceOfNewCar(man,mod,bud);

                System.out.println(" 1 = To see popularity of car and compare price of old vs new car");
                System.out.println("0 . To exit");
                int temp = scan.nextInt();

                if(temp == 1){
                    sj.popularityOfCar(man,mod);
                    sj.comparingOldNew(man,mod);

                }
            System.out.println("Select another option or press 0 to quit");

            }else if(userInput ==12){
                System.out.println("What type do u want i.e seda,hatchback,wagon");
                String type = scan.next();

                sj.minPriceForBody(type);
            System.out.println("Select another option or press 0 to quit");
            }


            userInput = scan.nextInt();
        }

        sj.closeConnection();

    }

}

