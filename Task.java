public class Task {

    private String description;
    private Boolean completed;
    private Boolean archived;

    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.archived = false;
    }

    public Boolean isCompleted() {
        return this.completed;
    }

    public Boolean isArchived() {
        return this.archived;
    }

    public void markAsCompleted() {
        if (this.completed) {
            System.out.println("Task already completed!");
            return;
        }
        this.completed = true;
        System.out.println("Task marked as complete!");
    }

    public void restoreAsToDo() {
        if (!this.completed) {
            System.out.println("Task already in to do!");
            return;
        }
        this.completed = false;
        System.out.println("Task restored as to do!");
    }

    public void markAsArchived() {
        if (this.archived) {
            System.out.println("Task already archived!");
            return;
        }
        this.archived = true;
        System.out.println("Task archived! Check archived tasks to find it.");
    }

    public void unArchiveTask() {
        if (!this.archived) {
            System.out.println("Task already unarchived!");
            return;
        }
        System.out.println("Task unarchived! You can find it again in the to do list.");
        this.archived = false;
    }

    public String toString() {
        return (this.completed ? "[X] " : "[ ] ") + this.description;
    }

}
