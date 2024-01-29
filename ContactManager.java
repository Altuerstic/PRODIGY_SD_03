import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

class Contact implements Serializable {
    private String name;
    private String phoneNo;
    private String emailAddress;

    public Contact(String name, String phoneNo, String emailAddress) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNo + ", Email: " + emailAddress;
    }
}

public class ContactManager extends JFrame {

    private ArrayList<Contact> contacts;
    private JTextField nameField, phoneField, emailField;
    private JTextArea displayArea;

    public ContactManager() {
        contacts = new ArrayList<>();

        // Set up the main frame
        setTitle("Contact Manager");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and place components in the frame
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        JButton viewButton = new JButton("View Contacts");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewContacts();
            }
        });

        JButton editButton = new JButton("Edit Contact");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editContact();
            }
        });

        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteContact();
            }
        });

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(phoneLabel);
        panel.add(phoneField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(addButton);
        panel.add(viewButton);
        panel.add(editButton);
        panel.add(deleteButton);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        setVisible(true);
    }

    private void addContact() {
        String name = nameField.getText();
        String phoneNo = phoneField.getText();
        String emailAddress = emailField.getText();

        Contact contact = new Contact(name, phoneNo, emailAddress);
        contacts.add(contact);

        displayArea.append("Contact added successfully!\n");
        clearFields();
    }

    private void viewContacts() {
        if (contacts.isEmpty()) {
            displayArea.setText("Contact list is empty.\n");
        } else {
            displayArea.setText("Contact List:\n");
            for (Contact contact : contacts) {
                displayArea.append(contact + "\n");
            }
        }
    }

    private void editContact() {
        String nameToEdit = JOptionPane.showInputDialog("Enter the name of the contact to edit:");

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(nameToEdit)) {
                String newPhoneNo = JOptionPane.showInputDialog("Enter new phone number:");
                String newEmailAddress = JOptionPane.showInputDialog("Enter new email address:");

                contact.setPhoneNo(newPhoneNo);
                contact.setEmailAddress(newEmailAddress);

                displayArea.append("Contact edited successfully!\n");
                clearFields();
                return;
            }
        }

        displayArea.append("Contact not found.\n");
    }

    private void deleteContact() {
        String nameToDelete = JOptionPane.showInputDialog("Enter the name of the contact to delete:");

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(nameToDelete)) {
                contacts.remove(contact);
                displayArea.append("Contact deleted successfully!\n");
                clearFields();
                return;
            }
        }

        displayArea.append("Contact not found.\n");
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ContactManager();
            }
        });
    }
}
