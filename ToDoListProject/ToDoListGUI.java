import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * ToDoListGUI - a Swing GUI version of your console ToDolist.
 * - Uses a Task object (description + isCompleted) instead of plain strings (cleaner).
 * - Keeps an ArrayList<Task> for business logic and a DefaultListModel<Task> for the JList view.
 */
public class ToDoListGUI {

    // Business data (same role as your console 'tasks' ArrayList)
    private final ArrayList<Task> tasks = new ArrayList<>();

    // Model for the visible list; JList is bound to this model
    private final DefaultListModel<Task> listModel = new DefaultListModel<>();

    // Swing components
    private final JFrame frame = new JFrame("To-Do List");
    private final JList<Task> taskJList = new JList<>(listModel);
    private final JTextField taskField = new JTextField(20);
    private final JButton addButton = new JButton("Add Task");
    private final JButton completeButton = new JButton("Mark Complete");
    private final JButton deleteButton = new JButton("Delete Task");

    public ToDoListGUI() {
        buildUI();
        attachListeners();
    }

    private void buildUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 350);
        frame.setLocationRelativeTo(null); // center on screen

        // Use BorderLayout: input at top, list center, buttons bottom
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("New task:"));
        topPanel.add(taskField);
        topPanel.add(addButton);

        taskJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskJList.setVisibleRowCount(10);
        JScrollPane listScroll = new JScrollPane(taskJList);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(completeButton);
        bottomPanel.add(deleteButton);

        frame.setLayout(new BorderLayout(8, 8));
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(listScroll, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void attachListeners() {
        // Add task when Add button pressed (maps to your addtask())
        addButton.addActionListener(e -> addTaskFromField());

        // Also add when Enter pressed inside the text field
        taskField.addActionListener(e -> addTaskFromField());

        // Mark selected task complete (maps to marktaskcomplete())
        completeButton.addActionListener(e -> markSelectedTaskComplete());

        // Delete selected task (maps to deletetask())
        deleteButton.addActionListener(e -> deleteSelectedTask());

        // Double-click a task to toggle completion (nice UX)
        taskJList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    markSelectedTaskComplete();
                }
            }
        });

        // Keyboard Delete key to remove a task
        taskJList.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteTask");
        taskJList.getActionMap().put("deleteTask", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedTask();
            }
        });
    }

    /** ---------- Business methods (mirror your console logic) ---------- */

    // Add a task from the text field (validates input)
    private void addTaskFromField() {
        String desc = taskField.getText().trim();
        if (desc.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a task description.", "Input required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Task t = new Task(desc);
        tasks.add(t);               // update business data (like tasks.add(task) in your console version)
        listModel.addElement(t);    // update UI model (keeps view in sync)
        taskField.setText("");
    }

    // Mark the currently selected task as completed
    private void markSelectedTaskComplete() {
        int idx = taskJList.getSelectedIndex();
        if (idx == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to mark complete.", "No task selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Task t = listModel.getElementAt(idx);
        if (t.isCompleted()) {
            JOptionPane.showMessageDialog(frame, "Task is already marked completed.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        t.markCompleted();              // update business object
        listModel.setElementAt(t, idx); // notify the list model so the JList repaints (toString changed)
    }

    // Delete the selected task
    private void deleteSelectedTask() {
        int idx = taskJList.getSelectedIndex();
        if (idx == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a task to delete.", "No task selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(frame, "Delete selected task?", "Confirm delete", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        tasks.remove(idx);          // update business data
        listModel.remove(idx);      // update UI model
    }

    /** ---------- Small Task class (clearer than raw String use) ---------- */
    private static class Task {
        private final String description;
        private boolean completed;

        Task(String description) {
            this.description = description;
            this.completed = false;
        }

        void markCompleted() { this.completed = true; }
        boolean isCompleted() { return completed; }

        // toString controls how the task appears in the JList.
        @Override
        public String toString() {
            return (completed ? "[✔] " : "[ ] ") + description;
        }
    }

    /** ---------- Main ---------- */
    public static void main(String[] args) {
        // Swing components must be constructed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(ToDoListGUI::new);
    }
}
