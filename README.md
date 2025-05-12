# Console App Java

![Java]


## Install

Console App developed in Java 17.

```bash
# 1. Clone repository
git clone https://github.com/pablosayc21/console-java-app

# 2. Access directory
cd console-java-app
```

## Aproach

### 1. FizzBuzz
Use the operator `%` to determine if it's a multiple of 3 and 5. I put the condition "if it's a multiple of 3 and 5" at the beginning because it must satisfy both conditions. For example, 15 is a multiple of 3 and is also a multiple of 5. If I put either of those comparators at the beginning, I wouldn't get to FizzBudd.
```java
for (int iterator = 1; iterator <= 100; iterator++) {

            if (iterator % 3 == 0 && iterator % 5 == 0) {

                System.out.println("FizzBuzz");

            } else if (iterator % 5 == 0) {

                System.out.println("Buzz");

            } else if (iterator % 3 == 0) {

                System.out.println("Fizz");

            } else {

                System.out.println(iterator);

            }

        }
```
### 2. Palindrome checker.
1. If it doesn't have characters automaticly returns false.
2. Ignores uppercase and eliminates every blank space using a regex.
3. Sets an iterator that goes from the start of the array to the middle of it. At the same time, the iterator is used to go from the end of the array to the middle.
4. If there is a missmatch between the characters on the iterators, then it isn't a palindrome and stops going through the array.
```java 
    public boolean isPalindrome(String text) {

        if (text.length() == 0) // 1
            return false;

        text = text.toLowerCase().replaceAll("\\s+", ""); // 2

        int endIndex = text.length();

        for (int i = 0; i < text.length() / 2; i++) { //3

            if (text.charAt(i) != text.charAt(endIndex - 1 - i)) //4
                return false;

        }

        return true;
    }
``` 
### 3. Sum of Unique Elements.
1. Used HashMap as I wanted to store the numbers as a key and the count of how many times it has appeared as the value.
2. If a number is not a key on the HashMap it will insert it with the value `1` as it is the first time it has been found.
3. If the number is already in the HashMap, it will increase its value. Meaning it has appeared more than one.
4. I get all the keys as Set, since I don't really care of the order.
5. Add the key to the sum if it only appeared once.
```java
public int sumOfUniqueElements(int[] nums) {
    int sum = 0;
    Map<Integer, Integer> numsCounter = new HashMap<Integer, Integer>(); //1
    for (int i = 0; i < nums.length; i++) {
        if (!numsCounter.containsKey(nums[i])) { //2
            numsCounter.put(nums[i], 1);
        } else {
            int actualCount = numsCounter.get(nums[i]);
            numsCounter.replace(nums[i], actualCount + 1); // 3
        }
    }

    Set<Integer> keys = numsCounter.keySet(); //4
    for (Integer key : keys) {
        if (numsCounter.get(key) == 1) //5
            sum += key;
    }
    return sum;
}
```

### 4. To-Do Application
Created the class `Task`. Which expects a description for its creation an sets the completed attribute as false since its been created. Archived its a attribute that will be explained later on the article.
```java
public class Task {

    private String description;
    private Boolean completed;
    private Boolean archived;

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.archived = false;
    }

    public Boolean isCompleted() {...} //Tells if the task is completed
    public Boolean isArchived() {...} //Tells if the task is archived
    public void markAsCompleted() {...} //Marks the task as completed
    public void restoreAsToDo() {...} //Marks the task back to To-Do
    public void markAsArchived() {...} //Marks the task as archived
    public void unArchiveTask() {...} //Marks the task as unarchived
    public String toString() { // Format for printing a Task
        return (this.completed ? "[X] " : "[ ] ") + this.description;
    }
}
```

Created a class `ToDo` to simulate the list. Which expects a scanner to interact with the user. Used a single HashMap to save memory.
```java
public class ToDo {

    private int key;
    private Map<Integer, Task> tasks;
    private Scanner scanner;

    public ToDo(Scanner scanner) {
        this.key = 1; //Used to assign a single id to the tasks.
        this.tasks = new HashMap<Integer, Task>(); // HashMap (key: id, value: Task)
        this.scanner = scanner;
    }

    public void createTask(String title) {
        if (title == null || title.length() == 0) {
            System.out.println("Couldn't add task. Tasks require a title.");
            return;
        }
        this.tasks.put(this.key, new Task(title)); // Creates a new task in the HashMap
        this.key++;
        System.out.println("Task added succesfully!");
    }

    public Boolean existingTask(int id) {...} //Checks if a task ID its inside the HashMap
    public void markAsCompleted(int id) {...} //Expects an ID to mark the task as completed
    public void restoreAsToDo(int id) {...} //
    
    public void deleteTask(int id) {
        if (!this.existingTask(id))
            return;
        this.tasks.remove(id); //Removes the task permanently
        System.out.println("Task deteled permanently!");
    }

    public void archiveTask(int id) {...} //Expects an ID to mark the task as archived
    public void unArchiveTask(int id) {...} //Expects an ID to mark the task as unarchived

    public void viewArchivedTasks() {
        String completedTasks = "";
        String toDoTasks = "";
        for (Map.Entry<Integer, Task> entry : this.tasks.entrySet()) {
            int id = entry.getKey();
            Task task = entry.getValue();
            if (!task.isArchived() && task.isCompleted()) { //Concat the task to its corresponding category and ignoring archived ones
                completedTasks += id + ". " + task.toString() + "\n";
            } else if (!task.isArchived() && !task.isCompleted()) { //Concat the task to its corresponding category and ignoring archived ones
                toDoTasks += id + ". " + task.toString() + "\n";
            }
        }
        if (completedTasks.isEmpty())
            completedTasks = "None";
        if (toDoTasks.isEmpty())
            toDoTasks = "None";
        System.out.println("\n__Tasks________________________");
        System.out.println("\nTo Do: \n" + toDoTasks);
        System.out.println("\nCompleted: \n" + completedTasks);
        System.out.println("\n¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }

    public void viewArchivedTasks() {
        String tasksString = "";
        for (Map.Entry<Integer, Task> entry : this.tasks.entrySet()) {
            int id = entry.getKey();
            Task task = entry.getValue();
            if (task.isArchived()) //Filters just the archived tasks
                tasksString += id + ". " + task.toString() + "\n";
        }
        if (tasksString.isEmpty())
            tasksString = "None";
        System.out.println("\nArchived tasks: \n" + tasksString);
    }

}
```

## Execution

```bash
# 1. Compile the file
javac ConsoleApp.java

# 2. Execute the file
java ConsoleApp
```

Menu for running different apps.

```bash
Welcome to Console App!
1. FizzBuzz.
2. Palindrome Checker.
3. Sum of Unique Elements.
4. To Do App.
5. Exit.
```

### 1. FizzBuzz.
It will print all the numbers from 1 to 100 with the words Fizz, Buzz, FizzBuzz according to multiple of each number.
```Bash
1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz
```

### 2. Palindrome Checker.
There are two ways to check the function works.

```Bash
Select type of testing (1 as default):
1. Testcases.
2. Enter a string.
```

#### 2.1. Testcases.
Will print the deault test cases:
```
Radar: true
Hello: false
A man a plan a canal Panama: true
```
#### 2.2. Enter a string.
It will ask the user for a string to check if it is a palindrome.
```
Enter text: 
Delia saw i was ailed
Delia saw i was ailed: true
```
### 3. Sum of Unique Elements.
There are two ways to check the function works. 

```Bash
Select type of testing (1 as default):
1. Testcases.
2. Enter test manually (5 numbers)
```

#### 3.1. Testcases.
It will print the default test cases:
```
Select an option: 
1
[1,2,3,2,4]: 8
[5,5,5,5,5]: 0
```

#### 3.2. Enter test manually.
It will ask the user for 5 integers to test the function.
```
Select an option: 
2
Enter an element: a
Invalid input. Enter a number.
Enter an element: b
Invalid input. Enter a number.
Enter an element: 1
Enter an element: 2
Enter an element: 1
Enter an element: 3
Enter an element: 4
[1,2,1,3,4]: 9
```
There are validations for invalid input.

### 4. To Do App
It will show the menu for the required options and suggested ones.

```bash
Welcome to the To-Do List App!
1. Add a Task.
2. View All Tasks.
3. Mark Task as Complete.
4. Delete Task.
5. Exit.
Suggested options:
6. Restore Task as To-Do.
7. Archive Task.
8. View All Archived Tasks.
9. Unarchive Task.
```
#### 4.1. Add task.
It will ask for the title of the task.
```
Enter the task title: Clean up my room
Task added succesfully!
```
#### 4.2. Add task.
It will display all of the tasks ordered by completed and to do.
```
__Tasks________________________

To Do: 
1. [ ] Buy groceries
2. [ ] Clean up my room


Completed: 
None

¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
```

#### 4.3. Mark Task as Complete.
It will ask the ID of the task to mark it as completed.
```
Enter task ID.
1
Task marked as complete!

__Tasks________________________

To Do: 
2. [ ] Clean up my room


Completed: 
1. [X] Buy groceries

¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
```
#### 4.4. Delete Task.
Exits the To-Do list.
```
Goodbye!
```

#### 4.4. Delete Task.
It will ask the ID of the task to delete it permanently.
```
Enter task ID.
2
Task deteled permanently!

__Tasks________________________

To Do: 
None

Completed: 
1. [X] Buy groceries


¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
```

#### 4.6. Restore as To-Do Task.
If an user marked a task as complete by mistake, this option will give the ability to restore it as To-Do.
```
__Tasks________________________

To Do: 
None

Completed: 
1. [X] Buy groceries


¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯

Welcome to the To-Do List App!
1. Add a Task.
2. View All Tasks.
3. Mark Task as Complete.
4. Delete Task.
5. Exit.
Suggested options: 
6. Restore Task as To-Do.
7. View All Archived Tasks.
8. Archive Task.
9. Unarchive Task.
 
Select an option
6
Enter task ID.
1
Task restored as to do!

__Tasks________________________

To Do: 
1. [ ] Buy groceries


Completed: 
None

¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
```

#### 4.7. Archive Task.
This gives the abilty to remove taks from the list without deleting them permanently.
```
__Tasks________________________

To Do: 
1. [ ] Buy groceries


Completed: 
None

¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯

Welcome to the To-Do List App!
1. Add a Task.
2. View All Tasks.
3. Mark Task as Complete.
4. Delete Task.
5. Exit.
Suggested options: 
6. Restore Task as To-Do.
7. Archive Task.
8. View All Archived Tasks.
9. Unarchive Task.

Select an option
7
Enter task ID.
1
Task archived! Check archived tasks to find it.
```
#### 4.8. View All Archived Tasks.
Views all the archived tasks.
```
Archived tasks: 
1. [ ] Buy groceries
```

#### 4.9. Unarchive Task.
Returns the task from the archived tasks to the To-Do list.
```
Archived tasks: 
1. [ ] Buy groceries

Enter task ID.
1
Task unarchived! You can find it again in the to do list.

Welcome to the To-Do List App!
1. Add a Task.
2. View All Tasks.
3. Mark Task as Complete.
4. Delete Task.
5. Exit.
Suggested options: 
6. Restore Task as To-Do.
7. Archive Task.
8. View All Archived Tasks.
9. Unarchive Task.
 
Select an option
2

__Tasks________________________

To Do: 
1. [ ] Buy groceries

Completed: 
None

¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
```



## Authors
* [Pablo Say](https://github.com/pablosayc21)

## Contact

[Instagram](https://www.instagram.com/pablosc_21/) 

[LinkedIn](https://www.linkedin.com/in/PabloSay21/)

[Java]:https://img.shields.io/badge/Java-17-blue?logo=OpenJDK&logoColor=white

