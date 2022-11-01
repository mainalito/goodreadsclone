# Readbook-tracker web application
## Inspired by goodreads website. This is a spring mvc web application. An easy way to track your books 📚 
### Features
 1. Login with GitHub.
 2. Search for any book in the world 🌍
 3. Save a book in PostgreSQL database.
 4. Track your book by giving them stars 🌟, startDate, completedDate and current Reading Status.
 5. Sort books from the database by readingStatus.
### Lessons learnt
1. GitHub authentication with spring-security.
2. Spring webclient.
3. API for books was ``` private final String BOOK_URL = "https://openlibrary.org/works/";```
4. API for cover_urls = ``` private String COVER_URL = "https://covers.openlibrary.org/b/id/";```
5. Spring data jpa. Using two primary keys also called **composite keys** to fetch data.
6. Frontend was thymleaf template engine.
### Link for website
[readbook-tracker](https://readbook-tracker.herokuapp.com/)
### ![Login page)](https://user-images.githubusercontent.com/81359755/171620813-42419428-f652-4709-aef0-db2e27b1a05c.png)
### ![Home page](https://user-images.githubusercontent.com/81359755/171620927-b42e7fec-f934-4150-ad71-0ebc382e3300.png)
### ![single page](https://user-images.githubusercontent.com/81359755/197792144-287a6d36-182a-41c9-8d88-1dc069ec3bf1.png)


