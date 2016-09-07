
JobOffers App

Application uses jsoup to get all "java" (not java script) job offers from site "sql.ru" and add them to postgresql database.
This scheduled procedure runs each day by default.
At first start it will get all job offers by last year. After that, only fresh job offers since last run time until current date.
Duplicates will be ignored.

Steps:

1. Run "createAll.bat" to create database, database tables and qartz tables

2. Check "jobOffers.cfg" if you need it

3. Run App (StartUI() class)