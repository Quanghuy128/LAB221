/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.ui;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import khangtl.daos.ItemsDAO;
import khangtl.daos.SuppliersDAO;
import khangtl.dtos.ItemsDTO;
import khangtl.dtos.SuppliersDTO;

/**
 *
 * @author Khang Tran
 */
public class Main extends javax.swing.JFrame {

    // Initialize global variables
    Object itemHeader[] = new Object[6];
    Object supplierHeader[] = new Object[3];
    Object itemData[] = new Object[6];
    Object supplierData[] = new Object[3];
    Vector<String> supCodeList = new Vector<>();
    ArrayList<ItemsDTO> itemList = new ArrayList<>();
    ArrayList<SuppliersDTO> supplierList = new ArrayList<>();

    int itemIndex = -1;
    int supplierIndex = -1;

    /**
     * Creates new form Main
     *
     * @throws java.sql.SQLException
     * @throws com.microsoft.sqlserver.jdbc.SQLServerException
     * @throws javax.naming.NamingException
     */
    public Main() throws SQLException, SQLServerException, NamingException {
        initComponents();
        // Popup confirm dialog before exit program
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int close = JOptionPane.showConfirmDialog(null, "Do you want to close program?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (close == JOptionPane.YES_OPTION) {
                    e.getWindow().dispose();
                }
            }
        });
        // Add header of item
        itemHeader[0] = "Code";
        itemHeader[1] = "Name";
        itemHeader[2] = "Supplier";
        itemHeader[3] = "Unit";
        itemHeader[4] = "Price";
        itemHeader[5] = "Supplying";
        // Add header of supplier
        supplierHeader[0] = "Code";
        supplierHeader[1] = "Name";
        supplierHeader[2] = "Address";
        updateData();
    }

    public void updateData() throws SQLException, NamingException {
        supCodeList = new Vector<>();
        ItemsDAO itemDAO = new ItemsDAO();
        // Fetch item first
        itemDAO.fetchItem();
        itemList = itemDAO.getItems();
        DefaultTableModel itemModel;
        itemModel = (DefaultTableModel) this.itemTable.getModel();
        itemModel.setRowCount(0);
        itemModel.setColumnIdentifiers(itemHeader);
        // If list item is not null, input data into table
        if (itemList != null) {
            for (int i = 0; i < itemList.size(); i++) {
                itemData[0] = itemList.get(i).getCode();
                itemData[1] = itemList.get(i).getName();
                itemData[2] = itemList.get(i).getSupCode();
                itemData[3] = itemList.get(i).getUnit();
                itemData[4] = itemList.get(i).getPrice();
                itemData[5] = itemList.get(i).isSupplying();
                itemModel.addRow(itemData);
                itemModel.getDataVector().elementAt(i);
            }
        }
        // Update UI of table
        itemTable.updateUI();
        SuppliersDAO supplierDAO = new SuppliersDAO();
        // Fetch supplier first
        supplierDAO.fetchSupplier();
        supplierList = supplierDAO.getSuppliers();
        DefaultTableModel supplierModel;
        supplierModel = (DefaultTableModel) this.supTable.getModel();
        supplierModel.setRowCount(0);
        supplierModel.setColumnIdentifiers(supplierHeader);
        // If list supplier is not null, input data into table
        if (supplierList != null) {
            for (int j = 0; j < supplierList.size(); j++) {
                supplierData[0] = supplierList.get(j).getCode();
                supplierData[1] = supplierList.get(j).getName();
                supCodeList.add(supplierList.get(j).getCode());
                supplierData[2] = supplierList.get(j).getAddress();
                supplierModel.addRow(supplierData);
            }
        }
        // Update UI of table
        supTable.updateUI();
        itemSupplierComboBox.setModel(new DefaultComboBoxModel<>(supCodeList));

        // Reset all data of text field, not in table
        resetItemData();
        resetSupplierData();
        txtItemCode.setEditable(false);
        txtSupCode.setEditable(false);

        // Check empty supplier and item
        if (itemList == null && supplierList == null) {
            JOptionPane.showMessageDialog(this, "Your data is empty");
        }
    }

    private boolean checkValidCode(String code) {
        // Check valid code using regex
        String regex = "[A-Z]{2}\\d{3}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(code);
        return m.matches();
    }

    private void resetSupplierData() {
        // Reset supplier data in text field
        txtSupCode.requestFocus();
        txtSupCode.setText("");
        txtSupName.setText("");
        txtSupAddress.setText("");
        supCollaboratingCheckBox.setSelected(false);
    }

    private void resetItemData() {
        // Reset item data in text field
        txtItemCode.setText("");
        txtItemCode.requestFocus();
        txtItemName.setText("");
        txtItemUnit.setText("");
        txtItemPrice.setText("");
        itemSupplyCheckBox.setSelected(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadDataBtn = new javax.swing.JButton();
        tabPane = new javax.swing.JTabbedPane();
        supPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        supTable = new javax.swing.JTable();
        rightSupPanel = new javax.swing.JPanel();
        supCodeLbl = new javax.swing.JLabel();
        supNameLbl = new javax.swing.JLabel();
        supAddressLbl = new javax.swing.JLabel();
        supCollaboratingLbl = new javax.swing.JLabel();
        supCollaboratingCheckBox = new javax.swing.JCheckBox();
        txtSupCode = new javax.swing.JTextField();
        txtSupName = new javax.swing.JTextField();
        txtSupAddress = new javax.swing.JTextField();
        addSupplier = new javax.swing.JButton();
        saveSupplier = new javax.swing.JButton();
        deleteSupplier = new javax.swing.JButton();
        itemPanel = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        rightItemPanel = new javax.swing.JPanel();
        itemCode = new javax.swing.JLabel();
        itemName = new javax.swing.JLabel();
        supplierCodeLbl = new javax.swing.JLabel();
        itemUnit = new javax.swing.JLabel();
        itemPrice = new javax.swing.JLabel();
        itemSupply = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        txtItemUnit = new javax.swing.JTextField();
        txtItemPrice = new javax.swing.JTextField();
        itemSupplyCheckBox = new javax.swing.JCheckBox();
        itemSupplierComboBox = new javax.swing.JComboBox<>();
        addItem = new javax.swing.JButton();
        saveItem = new javax.swing.JButton();
        deleteItem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Item Supplier Management");

        loadDataBtn.setBackground(new java.awt.Color(204, 255, 255));
        loadDataBtn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        loadDataBtn.setText("Load data");
        loadDataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadDataBtnActionPerformed(evt);
            }
        });

        tabPane.setName(""); // NOI18N

        supTable.setBackground(javax.swing.UIManager.getDefaults().getColor("CheckBox.focus"));
        supTable.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        supTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        supTable.setGridColor(new java.awt.Color(51, 153, 255));
        supTable.setSelectionBackground(new java.awt.Color(255, 153, 102));
        supTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(supTable);

        supCodeLbl.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        supCodeLbl.setText("Code");

        supNameLbl.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        supNameLbl.setText("Name");

        supAddressLbl.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        supAddressLbl.setText("Address");

        supCollaboratingLbl.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        supCollaboratingLbl.setText("Collaborating");

        supCollaboratingCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supCollaboratingCheckBoxActionPerformed(evt);
            }
        });

        addSupplier.setBackground(new java.awt.Color(0, 153, 255));
        addSupplier.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        addSupplier.setText("Add New");
        addSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierActionPerformed(evt);
            }
        });

        saveSupplier.setBackground(new java.awt.Color(153, 255, 102));
        saveSupplier.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        saveSupplier.setText("Save");
        saveSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSupplierActionPerformed(evt);
            }
        });

        deleteSupplier.setBackground(new java.awt.Color(255, 51, 51));
        deleteSupplier.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        deleteSupplier.setText("Delete");
        deleteSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSupplierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightSupPanelLayout = new javax.swing.GroupLayout(rightSupPanel);
        rightSupPanel.setLayout(rightSupPanelLayout);
        rightSupPanelLayout.setHorizontalGroup(
            rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightSupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(supAddressLbl)
                    .addComponent(supCollaboratingLbl)
                    .addComponent(supCodeLbl)
                    .addComponent(supNameLbl)
                    .addComponent(addSupplier))
                .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(rightSupPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSupAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supCollaboratingCheckBox)
                            .addComponent(txtSupName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(rightSupPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(saveSupplier)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deleteSupplier)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        rightSupPanelLayout.setVerticalGroup(
            rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightSupPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supCodeLbl)
                    .addComponent(txtSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supNameLbl)
                    .addComponent(txtSupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supAddressLbl)
                    .addComponent(txtSupAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(supCollaboratingCheckBox)
                    .addComponent(supCollaboratingLbl))
                .addGap(18, 18, 18)
                .addGroup(rightSupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplier)
                    .addComponent(saveSupplier)
                    .addComponent(deleteSupplier))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout supPanelLayout = new javax.swing.GroupLayout(supPanel);
        supPanel.setLayout(supPanelLayout);
        supPanelLayout.setHorizontalGroup(
            supPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightSupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        supPanelLayout.setVerticalGroup(
            supPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rightSupPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        tabPane.addTab("Supplier", supPanel);

        itemTable.setFont(new java.awt.Font("Trebuchet MS", 0, 13)); // NOI18N
        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Name", "Supplier", "Unit", "Price", "Supply"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        itemTable.setGridColor(new java.awt.Color(53, 153, 255));
        itemTable.setSelectionBackground(new java.awt.Color(255, 153, 102));
        itemTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                itemTableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(itemTable);

        itemCode.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        itemCode.setText("Code");

        itemName.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        itemName.setText("Name");

        supplierCodeLbl.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        supplierCodeLbl.setText("Supplier");

        itemUnit.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        itemUnit.setText("Unit");

        itemPrice.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        itemPrice.setText("Price");

        itemSupply.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        itemSupply.setText("Supply");
        itemSupply.setToolTipText("");

        itemSupplierComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        addItem.setBackground(new java.awt.Color(0, 153, 255));
        addItem.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        addItem.setText("Add New");
        addItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemActionPerformed(evt);
            }
        });

        saveItem.setBackground(new java.awt.Color(153, 255, 102));
        saveItem.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        saveItem.setText("Save");
        saveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveItemActionPerformed(evt);
            }
        });

        deleteItem.setBackground(new java.awt.Color(255, 51, 51));
        deleteItem.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        deleteItem.setText("Delete");
        deleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightItemPanelLayout = new javax.swing.GroupLayout(rightItemPanel);
        rightItemPanel.setLayout(rightItemPanelLayout);
        rightItemPanelLayout.setHorizontalGroup(
            rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addItem)
                    .addGroup(rightItemPanelLayout.createSequentialGroup()
                        .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemCode)
                            .addComponent(itemName)
                            .addComponent(supplierCodeLbl)
                            .addComponent(itemUnit)
                            .addComponent(itemPrice)
                            .addComponent(itemSupply))
                        .addGap(28, 28, 28)
                        .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(itemSupplierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtItemUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtItemPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(itemSupplyCheckBox))
                            .addGroup(rightItemPanelLayout.createSequentialGroup()
                                .addComponent(saveItem)
                                .addGap(18, 18, 18)
                                .addComponent(deleteItem)))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        rightItemPanelLayout.setVerticalGroup(
            rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(itemCode))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightItemPanelLayout.createSequentialGroup()
                        .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemName)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierCodeLbl)
                            .addComponent(itemSupplierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemUnit)
                            .addComponent(txtItemUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtItemPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemPrice))
                        .addGap(18, 18, 18)
                        .addComponent(itemSupply))
                    .addComponent(itemSupplyCheckBox, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(rightItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItem)
                    .addComponent(saveItem)
                    .addComponent(deleteItem))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout itemPanelLayout = new javax.swing.GroupLayout(itemPanel);
        itemPanel.setLayout(itemPanelLayout);
        itemPanelLayout.setHorizontalGroup(
            itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rightItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );
        itemPanelLayout.setVerticalGroup(
            itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemPanelLayout.createSequentialGroup()
                .addGroup(itemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rightItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabPane.addTab("Item", itemPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPane)
            .addGroup(layout.createSequentialGroup()
                .addGap(407, 407, 407)
                .addComponent(loadDataBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loadDataBtn)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        tabPane.getAccessibleContext().setAccessibleName("Supplier");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void supCollaboratingCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supCollaboratingCheckBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supCollaboratingCheckBoxActionPerformed

    private void itemTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_itemTableMouseClicked
        // TODO add your handling code here:
        txtItemCode.setEditable(false);
        itemIndex = itemTable.getSelectedRow();
        ItemsDTO itemDataTmp = itemList.get(itemIndex);
        txtItemCode.setText(itemDataTmp.getCode());
        txtItemName.setText(itemDataTmp.getName());
        String supName = supCodeList.get(itemIndex);
        // Get exact supCode with item
        int index = -1;
        if (supName != null) {
            for (int i = 0; i < supCodeList.size(); i++) {
                // Compare supCode in supCodeList
                if (supCodeList.get(i).equalsIgnoreCase(supName)) {
                    index = i;
                }
            }
        }
        // Get selected supplier's code
        itemSupplierComboBox.setSelectedIndex(index);
        txtItemUnit.setText(itemDataTmp.getUnit());
        txtItemPrice.setText((int) itemDataTmp.getPrice() + "");
        itemSupplyCheckBox.setSelected(itemDataTmp.isSupplying());
    }//GEN-LAST:event_itemTableMouseClicked

    private void supTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supTableMouseClicked
        // TODO add your handling code here:
        txtSupCode.setEditable(false);
        supplierIndex = supTable.getSelectedRow();
        SuppliersDTO supplierDataTmp = supplierList.get(supplierIndex);
        txtSupCode.setText(supplierDataTmp.getCode());
        txtSupName.setText(supplierDataTmp.getName());
        txtSupAddress.setText(supplierDataTmp.getAddress());
        supCollaboratingCheckBox.setSelected(supplierDataTmp.isCollaborating());
    }//GEN-LAST:event_supTableMouseClicked

    private void loadDataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadDataBtnActionPerformed
        // TODO add your handling code here:
        try {
            // Click button to load data again
            updateData();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_loadDataBtnActionPerformed

    private void addSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierActionPerformed
        // TODO add your handling code here:
        // Clear all data before insert
        txtSupCode.setEditable(true);
        resetSupplierData();
    }//GEN-LAST:event_addSupplierActionPerformed

    private void saveSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSupplierActionPerformed
        // TODO add your handling code here:
        // User haven't chosen supplier yet
        String code = txtSupCode.getText();
        if (!txtSupCode.isEditable() && code.isEmpty()) {
            resetSupplierData();
            JOptionPane.showMessageDialog(this, "Please choose supplier you want to save");
            return;
        }
        // Check valid code
        if (!checkValidCode(code)) {
            JOptionPane.showMessageDialog(this, "Code: please input code follow this format: xxyyy (x is a character from A-Z, y is a digit)");
            return;
        }
        String name = txtSupName.getText();
        String address = txtSupAddress.getText();
        boolean collaborating = supCollaboratingCheckBox.isSelected();
        // Check empty string
        if (name.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please do not input an empty string");
            return;
        }
        SuppliersDAO supplier = new SuppliersDAO();
        if (txtSupCode.isEditable()) {
            try {
                boolean checkInsert = supplier.insertNewSupplier(code, name, address, collaborating);
                if (checkInsert) {
                    JOptionPane.showMessageDialog(this, "New supplier added successful");
                    addSupplierActionPerformed(evt);
                    updateData();
                }
            } // Catch input string too long of user or duplicate code
            catch (SQLServerException ex) {
                if (ex.getMessage().contains("truncated")) {
                    JOptionPane.showMessageDialog(this, "Your input string is too long");
                } else if (ex.getMessage().contains("duplicate")) {
                    JOptionPane.showMessageDialog(this, "Supplier Code is already existed");
                }
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                boolean checkUpdate = supplier.updateSupplier(code, name, address, collaborating);
                if (checkUpdate) {
                    JOptionPane.showMessageDialog(this, "Supplier updated successful");
                    addSupplierActionPerformed(evt);
                    updateData();
                }
            } // Catch input string too long of user
            catch (SQLServerException ex) {
                if (ex.getMessage().contains("truncated")) {
                    JOptionPane.showMessageDialog(this, "Your input string is too long");
                }
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveSupplierActionPerformed

    private void deleteSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSupplierActionPerformed
        // TODO add your handling code here:
        // User haven't chosen supplier yet
        if (!txtSupCode.isEditable() && txtSupCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose supplier you want to delete");
            resetSupplierData();
            return;
        }

        String textCode = txtSupCode.getText();
        // User haven't chosen supplier yet
        if (textCode.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please choose supplier you want to delete");
        } else {
            SuppliersDAO supplier = new SuppliersDAO();
            try {
                // Show dialog to confirm delete supplier
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Do you want to delete this supplier?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    // Execute delete supplier
                    boolean checkDelete = supplier.deleteSupplier(textCode);
                    if (checkDelete) {
                        JOptionPane.showMessageDialog(this, "Supplier deleted successful");
                        updateData();
                    }
                }
            } // Check delete constraint between item and supplier 
            catch (SQLServerException ex) {
                if (ex.getMessage().contains("constraint")) {
                    JOptionPane.showMessageDialog(this, "Cannot delete this supplier, you have one item contain this supplier's code");
                }
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteSupplierActionPerformed

    private void deleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteItemActionPerformed
        // TODO add your handling code here:
        // User haven't chosen item yet
        if (!txtItemCode.isEditable() && txtItemCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose item you want to delete");
            resetItemData();
            return;
        }
        String textCode = txtItemCode.getText();
        // User haven't chosen item yet
        if (textCode.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(this, "Please choose item you want to delete");
        } else {
            ItemsDAO item = new ItemsDAO();
            try {
                // Show dialog to confirm delete item
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Do you want to delete this item?", "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    // Execute delete item
                    boolean checkDelete = item.deleteItem(textCode);
                    if (checkDelete) {
                        JOptionPane.showMessageDialog(this, "Item deleted succesful");
                        updateData();
                    }
                }
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteItemActionPerformed

    private void saveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveItemActionPerformed
        // TODO add your handling code here:
        // User haven't chosen item yet
        if (!txtItemCode.isEditable() && txtItemCode.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please choose item you want to save");
            resetItemData();
            return;
        }
        // Check valid code
        String code = txtItemCode.getText();
        if (!checkValidCode(code)) {
            JOptionPane.showMessageDialog(this, "Code: please input code follow this format: xxyyy (x is a character from A-Z, y is a digit)");
            return;
        }
        String name = txtItemName.getText();
        String supCode = (String) itemSupplierComboBox.getSelectedItem();
        String unit = txtItemUnit.getText();
        // Check valid unit
        if (unit.length() > 10) {
            JOptionPane.showMessageDialog(this, "Unit: maximum 10 characters");
            return;
        }

        int price;
        // Check valid price
        try {
            price = Integer.parseInt(txtItemPrice.getText());
            if (price <= 0) {
                JOptionPane.showMessageDialog(this, "Price: please set price greater than zero");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Price: please enter a digit");
            return;
        }
        boolean supplying = itemSupplyCheckBox.isSelected();
        // Check empty string
        if (code.isEmpty() || name.isEmpty() || unit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please do not input an empty string");
            return;
        }
        ItemsDAO item = new ItemsDAO();
        if (txtItemCode.isEditable()) {
            try {
                boolean checkInsert = item.insertNewItem(code, name, supCode, unit, price, supplying);
                if (checkInsert) {
                    JOptionPane.showMessageDialog(this, "New item added successful");
                    addItemActionPerformed(evt);
                    updateData();
                }
            } // Catch input string too long of user or duplicate code
            catch (SQLServerException ex) {
                if (ex.getMessage().contains("truncated")) {
                    JOptionPane.showMessageDialog(this, "Your input string is too long");
                } else if (ex.getMessage().contains("duplicate")) {
                    JOptionPane.showMessageDialog(this, "Item Code is already existed");
                }
            } catch (NamingException | SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                boolean checkUpdate = item.updateNewItem(code, name, supCode, unit, price, supplying);
                if (checkUpdate) {
                    JOptionPane.showMessageDialog(this, "Item update successful");
                    addItemActionPerformed(evt);
                    updateData();
                }
            } // Catch input string too long of user
            catch (SQLServerException ex) {
                if (ex.getMessage().contains("truncated")) {
                    JOptionPane.showMessageDialog(this, "Your input string is too long");
                }
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_saveItemActionPerformed

    private void addItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemActionPerformed
        // TODO add your handling code here:
        // Clear all data before insert
        txtItemCode.setEditable(true);
        resetItemData();
    }//GEN-LAST:event_addItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        // Setup look and feel
        String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            UIManager.setLookAndFeel(lookAndFeel);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        }
        //</editor-fold>

        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Main().setVisible(true);
            } catch (SQLException | NamingException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItem;
    private javax.swing.JButton addSupplier;
    private javax.swing.JButton deleteItem;
    private javax.swing.JButton deleteSupplier;
    private javax.swing.JLabel itemCode;
    private javax.swing.JLabel itemName;
    private javax.swing.JPanel itemPanel;
    private javax.swing.JLabel itemPrice;
    private javax.swing.JComboBox<String> itemSupplierComboBox;
    private javax.swing.JLabel itemSupply;
    private javax.swing.JCheckBox itemSupplyCheckBox;
    private javax.swing.JTable itemTable;
    private javax.swing.JLabel itemUnit;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton loadDataBtn;
    private javax.swing.JPanel rightItemPanel;
    private javax.swing.JPanel rightSupPanel;
    private javax.swing.JButton saveItem;
    private javax.swing.JButton saveSupplier;
    private javax.swing.JLabel supAddressLbl;
    private javax.swing.JLabel supCodeLbl;
    private javax.swing.JCheckBox supCollaboratingCheckBox;
    private javax.swing.JLabel supCollaboratingLbl;
    private javax.swing.JLabel supNameLbl;
    private javax.swing.JPanel supPanel;
    private javax.swing.JTable supTable;
    private javax.swing.JLabel supplierCodeLbl;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtItemPrice;
    private javax.swing.JTextField txtItemUnit;
    private javax.swing.JTextField txtSupAddress;
    private javax.swing.JTextField txtSupCode;
    private javax.swing.JTextField txtSupName;
    // End of variables declaration//GEN-END:variables
}
