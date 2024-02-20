package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private int taskCounter;

    /**
     * Constructor for TaskList.
     * Initializes an empty list of tasks and sets the task counter to zero.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.taskCounter = 0;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
        taskCounter++;
    }

    /**
     * Deletes a task from the list at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @return A string representing the result of the deletion.
     * @throws DukeException If the index is invalid.
     */
    public String deleteTask(int index) throws DukeException {
        validateIndex(index);
        Task removedTask = tasks.remove(index);
        String result = "Noted. I've removed this task:\n" +
                removedTask.getStatusIcon() + "\nNow you have " +
                tasks.size() + " " + (tasks.size() <= 1 ? "task" : "tasks") + " in the list.";
        System.out.println(result);
        return result;
    }

    /**
     * Gets a task from the list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws DukeException If the index is invalid.
     */
    public Task getTask(int index) throws DukeException {
        validateIndex(index);
        return tasks.get(index);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void validateIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Task not found. Please provide a valid task number.");
        }
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index The index of the task to be marked as done.
     * @return A string representing the result of marking the task as done.
     * @throws DukeException If the index is invalid or the task is already marked as done.
     */
    public String markTaskAsDone(int index) throws DukeException {
        validateIndex(index);
        Task task = tasks.get(index);
        if (task.isDone()) {
            throw new DukeException("Oops! This task is already marked as done.");
        }
        task.markAsDone();
        return "Nice! I've marked this task as done:\n" + task.getStatusIcon();
    }

    /**
     * Marks a task in the list as not done.
     *
     * @param index The index of the task to be marked as not done.
     * @return A string representing the result of marking the task as not done.
     * @throws DukeException If the index is invalid or the task is not marked as done.
     */
    public String markTaskAsNotDone(int index) throws DukeException {
        validateIndex(index);
        Task task = tasks.get(index);
        if (!task.isDone()) {
            throw new DukeException("Oops! This task is not marked as done yet.");
        }
        task.markAsNotDone();
        return "OK, I've marked this task as not done yet:\n" + task.getStatusIcon();
    }

    /**
     * Searches for tasks in the task list that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the search criteria.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
