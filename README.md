# personal_diary_api
Restful Monolithic Design using Spring Boot

This maven project is first created from spring.io. Restful Monolithic Design using Spring Boot.

Postgres
---------------
* Postgres 11 is used as database.
* diarydb Database with user, role is kept under ./resources/db/diary_system.sql.
* neet to execute the diary_system.sql before running the project.
* User Info, Note Item, Category are recorded in the database.

Redis(Memory storage)
----------------------
1. Redis maintain key, value pair with TTL.
2. JWT token expirations customization is handled thourg Redis TTL feature.

Lombok Plugin
-------------------
* Need to Install lombok plugin.

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

