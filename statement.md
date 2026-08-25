# Marks Manager System – Project Statement

## 1. Problem Statement
Managing student marks manually is time-consuming, error-prone, and inefficient.  
Teachers often need a simple tool to store marks, calculate results, generate grades, find toppers, check class performance, and maintain records.  
Existing solutions are either too complex or require internet access.

This project solves the problem by providing a lightweight **Java-based Marks Manager System** with both **Console** and **GUI** interfaces.

---

## 2. Objectives
- Provide an easy way to **add, update, delete, and search** student mark records.
- Automatically compute:
  - Total marks  
  - Percentage  
  - Grade  
  - Pass/Fail result  
- Provide class-level analytics:
  - Class average  
  - Topper  
  - Pass/Fail statistics
- Allow data to be **saved** and **loaded** from file (CSV format).
- Provide a **user-friendly graphical interface** using Java Swing.
- Ensure modularity using separate files/classes.

---

## 3. Project Scope
### In-Scope
- Store marks for **3 subjects** per student.
- CRUD operations (Create, Read, Update, Delete).
- Automated result calculation.
- Console application (text-based).
- Full GUI application (Java Swing).
- File saving and loading.
- Basic statistics and reporting.

### Out-of-Scope
- Database integration  
- Networking / online access  
- Multi-teacher login system  
- Large scale multi-class management  

---

## 4. Features (Functional Requirements)
1. Add student marks (3 subjects).
2. View all students and results.
3. Search a student by name.
4. Delete a student record.
5. Update student marks.
6. Show class average percentage.
7. Show topper of the class.
8. Show pass and fail count.
9. Save data to CSV.
10. Load data from CSV.
11. GUI interface with:
    - Input fields  
    - Buttons  
    - Student list  
    - Statistics display  

---

## 5. Non-Functional Requirements
- **Usability**: Simple and intuitive user interface.
- **Reliability**: Data validation for marks (0–100).
- **Maintainability**: Modular code (separate classes/files).
- **Portability**: Runs on any system with Java installed.
- **Performance**: Fast operations even with large inputs.

---

## 6. Tools & Technologies Used
- **Java 17/21 (JDK)**  
- **Java Swing**  
- **Java Collections Framework (ArrayList)**  
- **File I/O (CSV)**  
- **VS Code / IntelliJ / Eclipse** (any IDE)

---

## 7. System Architecture (High-Level)
### Components:
1. **Student.java**  
   - Holds marks, total, percentage, grade, pass/fail.

2. **StudentManager.java**  
   - Stores list of students  
   - Performs CRUD + analytics (topper, average, stats)

3. **MarksStorage.java**  
   - Handles file reading/writing in CSV format

4. **ConsoleApp.java**  
   - Text menu interface

5. **MarksManagerGUI.java**  
   - Swing GUI application

---

## 8. Use Case Overview
### Actors:
- **User** (Teacher / Student / Admin)

### Main Use Cases:
- Add student marks  
- View all records  
- Search student  
- Delete record  
- Show analytics (average, topper, pass/fail)  
- Save/Load data  

---

## 9. Test Cases Summary

| Test Case | Input | Expected Output |
|----------|--------|-----------------|
| Add Valid Student | Name + 3 valid marks | Student saved successfully |
| Add Invalid Marks | Marks <0 or >100 | Error message shown |
| Search Found | Existing name | Full details displayed |
| Search Not Found | Wrong name | "Student not found" |
| Class Average | Multiple students | Correct average displayed |
| Save To File | Click Save | File created |
| Load From File | Click Load | Students loaded |

---

## 10. Conclusion
The Marks Manager System successfully solves the problem of maintaining student marks and generating results efficiently.  
It includes both **console** and **GUI** interfaces, offers data persistence, and provides analytics such as topper and pass/fail count.  
The modular code structure makes it easy to maintain, extend, or integrate with advanced systems like databases or web apps in the future.

