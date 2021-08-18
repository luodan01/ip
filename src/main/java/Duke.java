import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> taskList = new ArrayList<>();
    private enum TaskType { ToDo, Event, Deadline, None }
    private static Scanner sc = new Scanner(System.in);

    private static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskList.size() + 1; i++) {
            System.out.println(i + "." + taskList.get(i - 1));
        }
    }

    private static String formatNumTasks() {
        int size = taskList.size();
        return size == 0 ? "no tasks"
                : size == 1 ? "1 task"
                : size + " tasks";
    }

    private static void doTask(String idx) {
        Task t = taskList.get(Integer.parseInt(idx) - 1);
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done: \n\t" + t);
    }

    private static void addTask(TaskType type, String taskInfo) {
        Task t;
        switch (type) {
            case Event:
                String[] eventInfo = taskInfo.split(" /at ");
                t = new Event(eventInfo[0], eventInfo[1]);
                break;
            case Deadline:
                String[] deadlineInfo = taskInfo.split(" /by ");
                t = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                break;
            case ToDo:
                t = new ToDo(taskInfo);
                break;
            default:
                return;
        }
        taskList.add(t);
        System.out.println("Got it. I've added this task: \n\t" + t);
        System.out.println("Now you have " + formatNumTasks() + " in the list.");
    }

    private static TaskType getTaskType(String taskInput) {
        return taskInput.equals("event") ? TaskType.Event
                : taskInput.equals("deadline") ? TaskType.Deadline
                : taskInput.equals("todo") ? TaskType.ToDo
                : TaskType.None;
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String[] params = sc.nextLine().split(" ", 2);
        String firstParam = params[0];

        while (!firstParam.equals("bye")) {
            if (firstParam.equals("list")) {
                displayTasks();
            } else if (firstParam.equals("done")) {
                doTask(params[1]);
            }  else {
               addTask(getTaskType(firstParam), params[1]);
            }
            params = sc.nextLine().split(" ", 2);
            firstParam = params[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}