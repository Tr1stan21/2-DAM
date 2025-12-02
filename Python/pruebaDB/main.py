import sqlite3

if __name__ == '__main__':
    with sqlite3.connect("company.db") as connection:
        cursor = connection.cursor()
        cursor.execute("""DROP TABLE IF EXISTS employees""")
        cursor.execute("""
            CREATE TABLE IF NOT EXISTS employees (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                dept TEXT NOT NULL,
                salary INTEGER
            );
        """)

        cursor.execute("""
            INSERT INTO employees(name, dept, salary)
            VALUES ('Bob', 'Sales', 25000)
        """)
        connection.commit()

        newName = "John"
        newDept = "Manufacturing"
        newSalary = 22000
        cursor.execute("""INSERT INTO employees (name, dept, salary)
                          VALUES (?, ?, ?)""", (newName, newDept, newSalary))
        connection.commit()

        cursor.execute("""UPDATE employees
                          SET name = 'Tony'
                          WHERE id=1""")
        connection.commit()

        cursor.execute("SELECT * FROM employees ORDER BY name")
        for x in cursor.fetchall():
            print(x)

        whichDept = input("Enter a department: ")
        cursor.execute("SELECT * FROM employees WHERE dept = ?", (whichDept,))

        for x in cursor.fetchall():
            print(x)

        cursor.close()