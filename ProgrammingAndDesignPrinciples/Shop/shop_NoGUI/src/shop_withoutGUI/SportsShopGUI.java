package shop_withoutGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class SportsShopGUI extends JFrame {
    private static final String FILE_NAME = "sports_shop_inventory.txt";

    private JTextArea displayArea;
    private JTextField itemNameField, itemCategoryField, itemPriceField, searchField;

    public SportsShopGUI() {
        setTitle("Sports Shop Inventory Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // North Panel
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(4, 2, 5, 5));

        northPanel.add(new JLabel("Item Name:"));
        itemNameField = new JTextField();
        northPanel.add(itemNameField);

        northPanel.add(new JLabel("Item Category:"));
        itemCategoryField = new JTextField();
        northPanel.add(itemCategoryField);

        northPanel.add(new JLabel("Item Price:"));
        itemPriceField = new JTextField();
        northPanel.add(itemPriceField);

        JButton addButton = new JButton("Add Item");
        addButton.addActionListener(new AddItemListener());
        northPanel.add(addButton);

        JButton resetButton = new JButton("Reset File");
        resetButton.addActionListener(new ResetFileListener());
        northPanel.add(resetButton);
        
        
        
   
        
       

        add(northPanel, BorderLayout.NORTH);
        
        // Center Panel
        
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);
        
        
        

        // South Panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());
        
        JButton listButton = new JButton("Show List?");
        listButton.addActionListener(new ShowListListener());
        southPanel.add(listButton).setSize(50,50);;
        
        searchField = new JTextField(20);
        southPanel.add(searchField);
        
        JButton searchButton = new JButton("Search Item");
        searchButton.addActionListener(new SearchItemListener());
        southPanel.add(searchButton);
        
        JButton removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener(new RemoveItemListener());
        southPanel.add(removeItemButton);
        
        

        add(southPanel, BorderLayout.SOUTH);
    }

    private class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String itemName = itemNameField.getText().trim();
            String itemCategory = itemCategoryField.getText().trim();
            String itemPrice = itemPriceField.getText().trim();

            if (itemName.isEmpty() || itemCategory.isEmpty() || itemPrice.isEmpty()) {
                JOptionPane.showMessageDialog(SportsShopGUI.this,
                        "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
                writer.println(itemName + "," + itemCategory + "," + itemPrice);
                displayArea.append("Added Item: " + itemName + " (" + itemCategory + ") - $" + itemPrice + "\n");
                clearInputFields();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(SportsShopGUI.this,
                        "Error adding item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        private void clearInputFields() {
            itemNameField.setText("");
            itemCategoryField.setText("");
            itemPriceField.setText("");
        }
    }

    private class ResetFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, false))) {
                displayArea.setText("File reset successfully.\n");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(SportsShopGUI.this,
                        "Error resetting file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    

    private class ShowListListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	
        	
             try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                 String line;
                
                 while ((line = reader.readLine()) != null) {
                         displayArea.append("Item: " + line + "\n");
                     }
                 }

             catch (IOException ex) {
                 JOptionPane.showMessageDialog(SportsShopGUI.this,
                         "Error searching file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             }
          
             
         }
        
     }

    private class SearchItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String searchName = searchField.getText().trim();

            if (searchName.isEmpty()) {
                JOptionPane.showMessageDialog(SportsShopGUI.this,
                        "Search field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                boolean found = false;

                while ((line = reader.readLine()) != null) {
                    String[] itemDetails = line.split(",");
                    if (itemDetails[0].equalsIgnoreCase(searchName)) {
                        displayArea.append("Item Found: " + line + "\n");
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    displayArea.append("Item not found: " + searchName + "\n");
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(SportsShopGUI.this,
                        "Error searching file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private class RemoveItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        	 String tempfile = "tempfile.txt";
        	 String searchName = searchField.getText().trim();
        	 
        	 File oldFile = new File(FILE_NAME);
        	 File tempFiles = new File(tempfile);
        	 
        	 if (searchName.isEmpty()) {
                 JOptionPane.showMessageDialog(SportsShopGUI.this,
                         "Search field cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                 return;
             }
        	 
        	 try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));) {
                 String line;
                

                 while ((line = reader.readLine()) != null) {
                     String[] itemDetails = line.split(",");
                     	
                     	if (itemDetails[0].equalsIgnoreCase(searchName)){
                     		displayArea.append("Removed Item: " + line + "\n");
                     	}
                     	if (!itemDetails[0].equalsIgnoreCase(searchName)) {
                     	   try (PrintWriter writer = new PrintWriter(new FileWriter(tempFiles, true))) {
                               writer.println(line);
                            
                           } catch (IOException ex) {
                               JOptionPane.showMessageDialog(SportsShopGUI.this,
                                       "Error Removing item: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                           }
                     	  
                     	
                       } 
                     }
                

             } catch (IOException ex) {
                 JOptionPane.showMessageDialog(SportsShopGUI.this,
                         "Error searching file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
             }
        	   oldFile.delete();
               File dump = new File(FILE_NAME);
               tempFiles.renameTo(dump);
        	
         }
     
    	
        
     }
    

           

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SportsShopGUI gui = new SportsShopGUI();
            gui.setVisible(true);
        });
    }
}
