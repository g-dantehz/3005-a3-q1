# 3005-a1-q1
Student DB: PostgreSQL + Java (JDBC)

Description:
A Java application that connects to PostgreSQL database and performs the basic four database operations (CRUD) on a table students.

(C) Create: addStudent(first_name, last_name, email, enrollment_date) -> adds a new student

(R) Read: getAllStudents() -> prints every student in the table

(U) Update: updateStudentEmail(studentID, newEmail) -> changes a student's email 
  
(D) Delete: deleteStudent(studentID) -> removes a student

Setup and Run:

  Step 1: Get source code + Create database
  
    1. Clone the repository using "git clone <repo_url>" in IntelliJ, or preferred IDE.
    
    2. Open pgAdmin 4 and connect to your server.
    
    3. Right click Databases, then click Create, then click Database.
    
    4. Name it '3005_a3' and click save.

  Step 2: Create table with starting data
  
    1. Right click '3005_a3', then click Query Tool.
    
    2. Click the folder icon (open file) in the Query Tool toolbar and choose 'schema.sql'.
    
    3. Execute by clicking the play (execute) button.
    
    4. Check if it worked by running 'SELECT * FROM students;'. You should see 3 students in the 'Data Output' box.

  Step 3: Set up connection details
  
  In Main.java, at the top there is the connection details url, user, and password that you must change to be able to connect to PostgreSQL. 

    String url = "jdbc:postgresql://localhost:5432/3005_a3"; <- only change "3005_a3" if you renamed the project, otherwise leave it
  
    String user = "postgres"; <- replace "postgres" with your PostgreSQL login user here
  
    String password = "your_password_here"; <- replace with your own password for that user

  Step 4: Running the application

  Everything should be ready to go, so in IntelliJ press the play (run) button at the top. 
  
  Main runs each function in order, and prints the table after each step.
  
  The program will: list the initial table > add a student named Alice > change John Doe's email > removes Jim Beam

  Note: the program changes the table, so to run it again from the start you need to re-run schema.sql in pgAdmin. It drops and recreates the table with the three starting rows.



