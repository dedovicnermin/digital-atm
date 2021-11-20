# DigitalATM

### Running the application
1. navigate to directory `digital-atm/docker` from command line

2. run the following : `docker compose up` 
   1. installation of Docker required. 
   2. Schema/Data SQL scripts are run on startup. This can be copied and ran inside a local DB if necessary.
3. Run `DigitalAtmApplication.main()` 
   1. from IDE
   2. Or, in command line: 
      1. execute the following to build app artifact (jar) - `mvn clean install`
      2. navigate to target folder `digital-atm/target`
      3. execute the following to run the application - `java -jar digital-atm-0.0.1-SNAPSHOT.jar`
4. In your web browser, navigate to `http://localhost:8080` to be directed to the login page
5. Select a username from below (password is `password`) 
   > *ALL USERS HAVE A BALANCE OF $100.00*



## USERNAME OPTIONS

| **username** |
|--------------|
| heisenberg   |
| skylerwhite  |
| hankschrader |
| capncook     |
| gusfring     |


