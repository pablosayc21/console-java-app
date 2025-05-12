import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ToDo {

    private int key;
    private Map<Integer, Task> tasks;
    private Scanner scanner;

    public ToDo(Scanner scanner) {

        this.key = 1;
        this.tasks = new HashMap<Integer, Task>();
        this.scanner = scanner;

    }

    public void createTask(String title) {

        if (title == null || title.length() == 0) {
            System.out.println("Couldn't add task. Tasks require a title.");
            return;
        }

        this.tasks.put(this.key, new Task(title));
        this.key++;
        System.out.println("Task added succesfully!");
    }

    public Boolean existingTask(int id) {
        if (!this.tasks.containsKey(id)) {
            System.out.println("Couldn't find task");
            return false;
        }
        return true;
    }

    public void markAsCompleted(int id) {

        if (!this.existingTask(id))
            return;
        this.tasks.get(id).markAsCompleted();

    }

    public void restoreAsToDo(int id) {

        if (!this.existingTask(id))
            return;
        this.tasks.get(id).restoreAsToDo();

    }

    public void deleteTask(int id) {

        if (!this.existingTask(id))
            return;

        this.tasks.remove(id);
        System.out.println("Task deteled permanently!");

    }

    public void archiveTask(int id) {

        if (!this.existingTask(id))
            return;

        this.tasks.get(id).markAsArchived();
    }

    public void unArchiveTask(int id) {

        if (!this.existingTask(id))
            return;

        this.tasks.get(id).unArchiveTask();

    }

    public void viewAllTasks() {

        String completedTasks = "";
        String toDoTasks = "";

        for (Map.Entry<Integer, Task> entry : this.tasks.entrySet()) {
            int id = entry.getKey();
            Task task = entry.getValue();

            if (!task.isArchived() && task.isCompleted()) {
                completedTasks += id + ". " + task.toString() + "\n";
            } else if (!task.isArchived() && !task.isCompleted()) {
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

            if (task.isArchived())
                tasksString += id + ". " + task.toString() + "\n";

        }

        if (tasksString.isEmpty())
            tasksString = "None";

        System.out.println("\nArchived tasks: \n" + tasksString);

    }

    public void printMenuOptions() {
        System.out.println("\nWelcome to the To-Do List App!");
        System.out.println("1. Add a Task.");
        System.out.println("2. View All Tasks.");
        System.out.println("3. Mark Task as Complete.");
        System.out.println("4. Delete Task.");
        System.out.println("5. Exit.");
        System.out.println("Suggested options: ");
        System.out.println("6. Restore Task as To-Do.");
        System.out.println("7. Archive Task.");
        System.out.println("8. View All Archived Tasks.");
        System.out.println("9. Unarchive Task.");
    }

    public int validInputNumber(String description) {

        System.out.println(description);

        while (!this.scanner.hasNextInt()) {

            System.out.println("Invalid input. Enter a number.");
            this.scanner.next();
            System.out.print(description);

        }

        return scanner.nextInt();
    }

    public void menu() {

        int option;

        do {

            this.printMenuOptions();

            option = this.validInputNumber(" \nSelect an option");
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter the task title: ");
                    String title = scanner.nextLine();
                    this.createTask(title);
                    break;
                case 2:
                    this.viewAllTasks();
                    break;
                case 3:
                    int idToMark = this.validInputNumber("Enter task ID.");
                    this.markAsCompleted(idToMark);
                    break;
                case 4:
                    int idTaskToDelete = this.validInputNumber("Enter task ID.");
                    this.deleteTask(idTaskToDelete);
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                case 6:
                    int idRestore = this.validInputNumber("Enter task ID.");
                    this.restoreAsToDo(idRestore);
                    break;
                case 7:
                    int idtoArchive = this.validInputNumber("Enter task ID.");
                    this.archiveTask(idtoArchive);
                    break;
                case 8:
                    this.viewArchivedTasks();
                    break;
                case 9:
                    int idtoUnArchive = this.validInputNumber("Enter task ID.");
                    this.unArchiveTask(idtoUnArchive);
                    break;
                default:
                    System.out.println("Choose an option from 1 to 9");
                    break;
            }

        } while (option != 5);
    }

}
