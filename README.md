# Library Management System

A console-based Java application built for the **Object-Oriented Programming (OOP) Lab**, Department of Software Engineering, University of Central Punjab. It manages library operations — books, members, and issue records — using core OOP principles instead of manual, paper-based tracking.

## Description

Traditional library record-keeping is slow, error-prone, and hard to search. This system replaces that with a menu-driven Java application that stores book and member records, tracks which books are issued to which members, and persists everything to disk so no data is lost between runs.

## OOP Concepts Used

- **Abstraction** — `Person` is an abstract base class defining common structure (`id`, `name`, `display()`) for any person in the system
- **Inheritance** — `Member` extends `Person`, reusing its fields and getters
- **Interface Implementation** — `BookManager`, `MemberManager`, and `IssueManager` all implement the generic `Manageable<T>` interface, guaranteeing a consistent set of operations (`add`, `view`, `update`, `delete`, `loadFromFile`, `saveToFile`) across every manager
- **Composition** — `LibraryManagementSystem` owns and coordinates one instance each of `BookManager`, `MemberManager`, and `IssueManager`
- **Association** — `Issue` links a book and a member together by their IDs

## Project Structure

```
.
├── Person.java                   
├── Member.java                   
├── Book.java                     
├── Issue.java                    
├── Manageable.java               
├── BookManager.java              
├── MemberManager.java            
├── IssueManager.java             
├── LibraryManagementSystem.java
└── README.md
```

## Features

- **Add Book** — stores a new book with an auto-generated ID, title, and author
- **View Books** — lists every book currently on record
- **Add Member** — registers a new member with an auto-generated ID and name
- **View Members** — lists every registered member
- **Issue Book** — issues a book to a member, **validating that both the book ID and member ID actually exist** before creating the record (prevents issuing nonexistent books to nonexistent members)
- **View Issues** — lists every issue record with its status
- **Exit** — saves all data to file and closes the program

## File Handling

Book, member, and issue records are saved to plain-text files (`books.txt`, `members.txt`, `issues.txt`) when the program exits, and automatically reloaded the next time it starts — so data persists across sessions.

## Requirements

- JDK 14 or later (the menu uses Java's arrow-style `switch` expressions)
- No external libraries — uses only the standard library (`java.util`, `java.io`)

## Installation & Usage

### 1. Clone the repository
```bash
git clone https://github.com/NuvairaKhan/Library-Management-System.git
cd Library-Management-System
```

### 2. Compile
```bash
javac *.java
```

### 3. Run
```bash
java LibraryManagementSystem
```

### 4. Use the menu
```
1. Add Book
2. View Books
3. Add Member
4. View Members
5. Issue Book
6. View Issues
7. Exit
```
Enter the number for the action you want, and follow the prompts.

## Validation

- **Issue Book** checks that both the entered book ID and member ID exist in the system before creating an issue record invalid IDs are rejected with a clear message instead of silently creating a broken record.

## Conclusion

This project applies core Object-Oriented Programming concepts abstraction, inheritance, interfaces, composition, and association to a practical problem: automating library record-keeping. Splitting responsibilities across dedicated manager classes keeps the system organized, and file-based persistence ensures records survive between runs.
