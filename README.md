# spring-data-jpa-join-table-example
* This is the sample backend microservice project for join table by using spring data jpa

<!-- TABLE OF CONTENTS -->
## Table of Contents
- [About The Project](#about-the-project)
    - [Built With](#built-with)
- [Getting Started](#getting-started)
    - [Clone Project](#clone-project)
    - [Prerequisites](#prerequisites)
    - [Instruction](#instruction)
- [Contact Me](#contact)
- [Contributing](#Contributing)


<!-- ABOUT THE PROJECT -->
## About The Project
This is the sample backend microservice project for join table by using spring data jpa.
You can learn how to connect and join two table by using spring data jpa.

### Built With

This project is build with
* [Java](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)
* [Maven](https://maven.apache.org/download.cgi)
* [Postgresql Database](https://www.postgresql.org/download/)


## Getting Started
This project is built with java, maven, postgresql and use project lombok as plugin.
So, please make sure all are installed in you machine.

### Clone Project

* Clone the repo
   ```sh
   git clone https://github.com/yewin-mm/spring-data-jpa-join-table-example.git

### Prerequisites
* Install Git in you machine. [Get Git](https://git-scm.com/downloads)
* Install Java and Maven in your machine. <br> [Get Java]((https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)) | [Get Maven](https://maven.apache.org/download.cgi)
* Install your favourite java IDE in your machine eg. IntelliJ or Eclipse
* Install Postgresql database in your machine [Get Postgresql](https://www.postgresql.org/download/
* Install project lombok in your IDE <br> (if you don't know how to install lombok in your IDE, please reference in google)
* Install Postman for testing api. [Get Postman](https://www.postman.com/)

### Instruction
* Go to your folder which you want to clone this project and <br>type below clone command in your command prompt (window) or in your terminal (MacOs and Linux).
    ```sh
   git clone https://github.com/yewin-mm/spring-data-jpa-join-table-example.git
* Open cloned project in you IDE.
* Change your postgresql username and password in spring datasource username and password fields in `application.properties` file. 
* Postgresql default username is postgres and password is no set and if you don't do update password, you can set just blank in password field.
* If you don't use postgresql database, please change your database driver in `pom.xml` file and change your database dialect in `application.properties` file. 
    * If you don't know your database driver and dialect, please reference in google. <br> eg. maven dependency for oracle database and spring jpa dialect properties for oracle driver.
* Insert some role into your database by querying in database console.
    * Here you can use database gui tools like [Dbeaver](https://dbeaver.io/), 
    * If so, firstly, you need to connect to your database with DBeaver 
    * after that you can add by clicking '+' sign in your role table or you can run query in DBeaver console like below.
  ```sh 
  insert into role (id, role_name) values (1, 'admin'); insert into role (id, role_name) values (2, 'normal user');
* Run the project in your IDE.
* Import `sample postman api request.json` file under project directory into your Postman and test it by click send button in your postman.


## Contact

Name - Ye Win <br> LinkedIn profile -  [Ye Win LinkedIn](linkedin.com/in/ye-win-1a33a292)  <br> Email Address - yewin.mmr@gmail.com

Project Link: [Spring Data JPA Join Table Example](https://github.com/yewin-mm/spring-data-jpa-join-table-example)

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.
<br>If you want to contribute....
1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request