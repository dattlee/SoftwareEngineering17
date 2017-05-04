package SimulationModel;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import SimulationModel.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lec35
 */
public class GUI extends javax.swing.JFrame {

    Simulation sim = new Simulation();
    
    //DefaultTableModel defSharesTable = new DefaultTableModel();   
    /**
     * Creates new form demo
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialise the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        runSimStepSelector = new javax.swing.JComboBox<>();
        tabMenu = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        portfolioTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sharesTable = new javax.swing.JTable();
        runSimButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        marketStatus = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();
        resetMenu = new javax.swing.JMenu();
        helpMenu = new javax.swing.JMenu();
        simulationHelp = new javax.swing.JMenu();
        dataHelp = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1387, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jInternalFrame1.setBackground(new java.awt.Color(255, 255, 255));
        jInternalFrame1.setForeground(new java.awt.Color(204, 255, 255));
        jInternalFrame1.setVisible(true);

        runSimStepSelector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "15 Minutes (1)", "1 Day (28)", "1 Week (196)", "1 Year (End)" }));
        runSimStepSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runSimStepSelectorActionPerformed(evt);
            }
        });

        tabMenu.setBackground(new java.awt.Color(255, 0, 0));
        tabMenu.setForeground(new java.awt.Color(51, 51, 51));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));

        portfolioTable.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        portfolioTable.setModel(new javax.swing.table.DefaultTableModel(
            getTraderTable(),
            new String [] {
                "Trader Name ", "Client Name", "Portfolio Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        }
    );
    jScrollPane6.setViewportView(portfolioTable);

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addContainerGap(206, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(171, 171, 171))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
            .addContainerGap(65, Short.MAX_VALUE)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(45, 45, 45))
    );

    tabMenu.addTab("Portfolios", jPanel4);

    jPanel5.setBackground(new java.awt.Color(0, 153, 153));

    sharesTable.setModel(new javax.swing.table.DefaultTableModel(
        getCompanyTable(),
        new String [] {
            "Company Name", "Share Value", "No of Shares"
        })
        {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        }
    );
    jScrollPane2.setViewportView(sharesTable);
    if (sharesTable.getColumnModel().getColumnCount() > 0) {
        sharesTable.getColumnModel().getColumn(0).setResizable(false);
        sharesTable.getColumnModel().getColumn(1).setResizable(false);
        sharesTable.getColumnModel().getColumn(2).setResizable(false);
        sharesTable.getColumnModel().getColumn(3).setResizable(false);
    }

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap(228, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(149, 149, 149))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap(60, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(50, 50, 50))
    );

    tabMenu.addTab("Shares", jPanel5);

    runSimButton.setText("Go");
    runSimButton.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            runSimButtonActionPerformed(evt);
        }
    });

    dateLabel.setText(sim.date);

    marketStatus.setFont(new java.awt.Font("Vijaya", 3, 48)); // NOI18N
    marketStatus.setForeground(chooseColor(marketStatus.getText()));
    marketStatus.setText(getMarketStatus());

    jLabel1.setIcon(new javax.swing.ImageIcon("smbhome.uscs.susx.ac.uk\\aw420\\Documents\\NetBeansProjects\\Wolfe&Gecko\\Wolfe&Gecko\\src\\SimulationModel\\logo.jpg")); // NOI18N

    jMenuBar2.setBackground(new java.awt.Color(153, 153, 255));

    fileMenu.setText("File");

    editMenu.setText("Edit");
    fileMenu.add(editMenu);

    resetMenu.setText("Reset");
    fileMenu.add(resetMenu);

    jMenuBar2.add(fileMenu);

    helpMenu.setText("Help");

    simulationHelp.setText("Simulation");
    helpMenu.add(simulationHelp);

    dataHelp.setText("Data");
    helpMenu.add(dataHelp);

    jMenuBar2.add(helpMenu);

    jInternalFrame1.setJMenuBar(jMenuBar2);

    javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
    jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
    jInternalFrame1Layout.setHorizontalGroup(
        jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jInternalFrame1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addComponent(tabMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addGap(25, 25, 25)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(46, 46, 46)
                            .addComponent(runSimStepSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(runSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20))
                        .addGroup(jInternalFrame1Layout.createSequentialGroup()
                            .addGap(269, 269, 269)
                            .addComponent(marketStatus))))))
    );
    jInternalFrame1Layout.setVerticalGroup(
        jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addGroup(jInternalFrame1Layout.createSequentialGroup()
                    .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(runSimButton, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                        .addComponent(runSimStepSelector)
                        .addComponent(dateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(4, 4, 4)
                    .addComponent(marketStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGap(18, 18, 18)
            .addComponent(tabMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(612, Short.MAX_VALUE))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jInternalFrame1))
            .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addGap(39, 39, 39)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
    );

    pack();
    }// </editor-fold>//GEN-END:initComponents

    /*private Color chooseColor(String n){
        if(n.equals("Bull Market")){
            return Color.GREEN;
        }
        else if(n.equals("Bear Market")){
            return Color.RED;
        }
        else{
            return Color.BLACK;
        }
    }*/
    
    private void runSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runSimButtonActionPerformed
        // TODO add your handling code here:
        String readSelection = (String) runSimStepSelector.getSelectedItem();
        if(readSelection.equals("15 Minutes (1)")){
            sim.runXSteps(1);
        }
        else if(readSelection.equals("1 Day (28)")){
            sim.runXSteps(28);
        }
        else if(readSelection.equals("1 Week (196)")){
            sim.runXSteps(196);
        }
        else{
            sim.runXSteps(20000);
        }

        updateAllData();
    }//GEN-LAST:event_runSimButtonActionPerformed

    private void runSimStepSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runSimStepSelectorActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_runSimStepSelectorActionPerformed
    
    public Object[][] getTraderTable(){
	int j = 0;
	int noOfTraders = sim.getAllPortfolios().size();
	Object[][]traderTable = new Object[noOfTraders][3];		
	for(Portfolio p: sim.getAllPortfolios()){  
                traderTable[j][0] = p.getTrader().getId();
		traderTable[j][1] = p.getId();
		traderTable[j][2] = p.getValue();
                
                j = j++;
	}

	return traderTable;
    }

    public Object[][] getCompanyTable(){
	int i = 0;
	int noOfComp = sim.getAllTradedCompanies().size();
	Object[][]companyTable = new Object[noOfComp][3];
	
	for(TradedCompany tc: sim.getAllTradedCompanies()){
		companyTable[i][0] = tc.getName();
		companyTable[i][1] = tc.getShareValue();
		companyTable[i][2] = tc.getSharesIssued();
		//companyTable[i][3] = tc.getChangeInValue();
		i = i++;
	}

	return companyTable;
    }
    
    //Updates data upon running the simulation for a number of steps
    private void updateAllData(){
        //Basically after the simulation finishes running get everything updated from here
        
        //Update market history display
        marketStatus.setText(sim.getMarketStatus());
        //marketStatus.setText("Stock Market Simulation");
        
        //Update date
        dateLabel.setText(sim.getDate());
        
        //update trader (portfolio) table
        portfolioTable.setModel(new javax.swing.table.DefaultTableModel(
                getTraderTable(),
                new String [] {
               "Trader Name", "Client Name", "Portfolio Value"
           }
            ) {
            Class[] types = new Class [] {
            java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
               false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
               return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(portfolioTable);

        if (portfolioTable.getColumnModel().getColumnCount() > 0) {
            portfolioTable.getColumnModel().getColumn(0).setResizable(false);
            portfolioTable.getColumnModel().getColumn(1).setResizable(false);
            portfolioTable.getColumnModel().getColumn(2).setResizable(false);
        }
       
        //update share table
        sharesTable.setModel(new javax.swing.table.DefaultTableModel(
               getCompanyTable(),
                new String [] {
                "Company Name", "Share Value", "No of Shares"
            })
             {
            Class[] types = new Class [] {
            java.lang.String.class, java.lang.Double.class, java.lang.Integer.class
        };
        boolean[] canEdit = new boolean [] {
           false, false, false
        };

        public boolean isCellEditable(int rowIndex, int columnIndex) {
           return canEdit [columnIndex];
        }
        }); 
        
        jScrollPane2.setViewportView(sharesTable);

        if (sharesTable.getColumnModel().getColumnCount() > 0) {
            sharesTable.getColumnModel().getColumn(0).setResizable(false);
            sharesTable.getColumnModel().getColumn(1).setResizable(false);
            sharesTable.getColumnModel().getColumn(2).setResizable(false);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu dataHelp;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel marketStatus;
    private javax.swing.JTable portfolioTable;
    private javax.swing.JMenu resetMenu;
    private javax.swing.JButton runSimButton;
    private javax.swing.JComboBox<String> runSimStepSelector;
    private javax.swing.JTable sharesTable;
    private javax.swing.JMenu simulationHelp;
    private javax.swing.JTabbedPane tabMenu;
    // End of variables declaration//GEN-END:variables
}
