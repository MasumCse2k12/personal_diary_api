# personal_diary_api
Restful Monolithic Design using Spring Boot

This maven project is first created from spring.io.
Postgres
---------------
* Postgres 11 is used as database.
* diarydb Database with user, role is kept under ./resources/db/diary_system.sql.
* neet to execute the diary_system.sql before running the project.
* User Info, Note Item, Category are recorded in the database. (database credentials are in appliantion.properties file)

Redis(Memory storage)
----------------------
* Neet to install redis (Redis credentials are in appliantion.properties file)
* Redis maintain key, value pair with TTL.
* JWT token expirations customization is handled thourg Redis TTL feature.

Lombok Plugin
-------------------
* Need to Install lombok plugin.

Swagger-UI
---------------
* Added swagger to test REST API from browser.

Log4j
---------------
* Used Log4japi to write logs.

Functions
----------------
 * Implemented JWT authentication (Spring Security)
 * Managing Note (CRUD Operation)
 * Managing Note Categories (CRUD Operation)
 * Updating a notes category ie. switching from one category to another
 * Display notes according to category
 * Ability to View a single Note
 * User Identification
 * Note created by one user is only accessible for that user only

