# Webshop in beta
## SUT:
In this project we are testing a legacy webshop that sells clothes and accessories. The main focus is to test essential features like: register, login, add items to cart and checkout (buy them).

### Webpage:
https://www.saucedemo.com/

### Test approach:
- High level UI test
- End to end
- Blackbox

### Project structure:
The project follows the page object model (POM).

### User storys:
- As a User, I want to log in so that I have access to features that require identification.
- As a User, I want to log out so that another user can use the application.
- As a User, I want to see an ordered list of the available products so that I can choose from them.
- As a User, I want to see every product on its own page so that I can get detailed information on the product.
- As a User, I want to put products into the cart so that I can buy them.
- As a User, I want to check out so that I can finish shopping.

## Installation guide:

### Prerequisits

- **JDK** 17 or higher
- **Maven** 3.9.6 or higher
- **Docker** 4.28.0 or higher

### Technologies used:
- ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)(https://www.java.com/en/)
- JUnit
- ![Selenium](https://img.shields.io/badge/-selenium-%43B02A?style=for-the-badge&logo=selenium&logoColor=white)(https://www.selenium.dev/)
- Selenium GRID
- ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)(https://www.docker.com/)
- ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)(https://maven.apache.org/)

### How to run:
1. Clone the Selenium GRID repository:
```sh
git clone https://github.com/SeleniumHQ/docker-selenium
```
2. Build 
```sh
mvn clean package
```
3. Run:
```sh
java -jar backend/target/webshop.jar
```
4. Clone project
5. Build project
6. Setup environmental variables

## Contact

- Fazekas Gergő - ímélcím
- Gömöri Dávid - gomori.david@gmail.com

Project - https://github.com/CodecoolGlobal/test-automation-webshop-in-beta-general-Intcognito
