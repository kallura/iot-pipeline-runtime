## IoT Runtime

Loads the statistic data from HBase and aggregate average/median/max/min values for sensor array

#### Rest endpoint specification
Average:

````
URL: ${server_url}/sensor/average
Method: GET
Query params:
    sensor_list - the sensor list, devided by "," (example "power,energy")
    ime_start - the filter start date time (example "2018-05-18T13:13:42.000")
    time_end - the filter end date time (example "2018-05-21T13:13:42.000")
````
Median:

````
URL: ${server_url}/sensor/median
Method: GET
Query params:
    sensor_list - the sensor list, devided by "," (example "power,energy")
    ime_start - the filter start date time (example "2018-05-18T13:13:42.000")
    time_end - the filter end date time (example "2018-05-21T13:13:42.000")
````

Max:

````
URL: ${server_url}/sensor/max
Method: GET
Query params:
    sensor_list - the sensor list, devided by "," (example "power,energy")
    ime_start - the filter start date time (example "2018-05-18T13:13:42.000")
    time_end - the filter end date time (example "2018-05-21T13:13:42.000")
````

Min:

````
URL: ${server_url}/sensor/min
Method: GET
Query params:
    sensor_list - the sensor list, devided by "," (example "power,energy")
    ime_start - the filter start date time (example "2018-05-18T13:13:42.000")
    time_end - the filter end date time (example "2018-05-21T13:13:42.000")
````

#### Prerequisites
You will need to install next software, before you'll run app: 

1. java v1.8
2. git v2.15.1
3. maven v3.5.2

Please see bellow, the installation example for Linux OS 

```
//install java
sudo add-apt-repository ppa:webupd8team/java
sudo apt update
sudo apt install oracle-java8-installer
javac -version
//install maven
sudo apt-get install maven
//install git
apt-get install git

```
#### Installing
Before you will use the app you will need to execute next commands:

```
git clone ${repo_url}
cd {project_path}
mvn clean install
java -jar target/iot-pipeline-runtime.jar

```

