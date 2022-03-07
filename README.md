##To start SQL server: 

- `mysqld –console`
- In a new terminal, connect to mysql server with password. 
  - `mysql –u root -p`
- Can create new DB with: 
  - `CREATE DATABASE name`

<br />

### Repository package
- Deals with database management in Java
- `import org.springframework.data.jpa.repository.JpaRepository;`
- Built in CRUD operations in Jpa repository:
  - `.findAll()`
  - `.save()   - save the got Data`
  - `.delete()`

- Jpa repo **<>** takes in `Name of the entity we want to retrieve (Book)` and type of Primary key

<br />

### Controller 
Contains endpoints to access data in DB.


### application.properties

``` 
#Hibernate ddl auto (create, create-drop, validate, update) - all possible values for hibernate.hbm2ddl.auto
spring.jpa.hibernate.ddl-auto = create 
``` 
```
• validate: validate the schema, makes no changes to the database.
• update: update the schema.
• create: creates the schema, destroying previous data
• create-drop: drop the schema at the end of the session.
```
For the first time an app runs it is important to set **spring.jpa.hibernate.ddl-auto** to
**create**. For the next times, such property should be set to **update**

If it stays as create everytime, all previous existing data entries will be removed 
as we are creating a new table.

### Manually add a book entry:

**Note: All url are specified in Book controller.**

Need to escape double quotes with `/` and **change single quotes to double**.
```
curl -d '{"book_name": "Book1","author_name": "Author1","isbn":"ISBN1"}' -H 'Content-Type: application/json' http://localhost:8080/books

Change to:
curl -d "{/"book_name/": /"Book1/",/"author_name/": /"Author1/",/"isbn/":/"ISBN1/"}" -H "Content-Type: application/json" http://localhost:8080/books
```
### Modify Book:
Changes name to **New_Book**
```
curl -X PUT -H /"Content-Type: application/json/" -d "{/"book_name/": 
/"New_Book/",/"author_name/": /"Author2/",/"isbn/":/"ISBN2/"}" 
http://localhost:8080/books/2
```

### Delete Book:
`curl -X DELETE http://localhost:8080/books/1`


### JSP pages
JavaServer Pages (JSP) is a Java standard technology that enables you to write dynamic, 
data-driven pages for your Java web applications.

configure application.properties
```
spring.mvc.view.prefix: /WEB-INF/jsp/
spring.mvc.view.suffix: .jsp
```
Tells SpringBoot where the jsp pages are.


### Models 
Model represents an object or JAVA POJO carrying data. It can also have logic to update controller
if its data changes. <br />
View - View represents the visualization of the data that model contains. <br />
Controller - Controller acts on both model and view.
