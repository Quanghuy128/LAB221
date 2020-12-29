/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khangtl.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 *
 * @author Peter
 */
public class Calculator extends javax.swing.JFrame {

    /**
     * Creates new form Calculator
     */
    ArrayList<BigDecimal> storeNum = new ArrayList<>();
    // Using BigDecimal for calculator
    BigDecimal firstNum, secondNum, percentNum, ans;
    boolean hasClickOperator = false;
    // Check add new number
    boolean hasAddNum = false;
    // Check has second number and save it
    boolean hasSecondNum = false;
    // Check click equal button
    boolean hasEqual = false;
    // Check do each calculate
    boolean hasCalculate = false;
    char operator = 'o';

    public Calculator() {
        initComponents();
        btnStoreClear.setEnabled(false);
        btnStoreResult.setEnabled(false);
    }

    private void doArithmeticOperator(BigDecimal startNum, BigDecimal endNum) {
        // Do operator per each operator's button
        switch (operator) {
            case '+':
                ans = startNum.add(endNum);
                break;

            case '-':
                ans = startNum.subtract(endNum);
                break;

            case '*':
                ans = startNum.multiply(endNum);
                break;

            case '/':
                try {
                    ans = startNum.divide(endNum, 24, BigDecimal.ROUND_HALF_EVEN);
                } catch (ArithmeticException ex) {
                    txtDisplay.setText("Cannot divide by zero");
                    enableButton(false);
                }
                break;
        }
        String strValue = formatDisplayString(ans);
        txtDisplay.setText(strValue);
    }

    private void doShowText(String text) {
        String finalText = "";
        // If screen are displaying text (not numeric), get new text
        if (!isNumeric()) {
            finalText = text;
            txtDisplay.setText(finalText);
            enableButton(true);
            return;
        }
        String currentText = txtDisplay.getText();
        // If current text is 0, just accept new text is a number different with 0
        if (currentText.equals("0")) {
            if (text.equals("0")) {
                finalText = "0";
            } else {
                finalText = text;
            }
        } else {
            // After click equal button, reset click operator, add number, equal and calculate
            if (hasEqual) {
                finalText = text;
                hasClickOperator = false;
                hasAddNum = false;
                hasEqual = false;
                hasCalculate = false;
            } else {
                // After click operator, change way to display text to the screen
                if (hasClickOperator) {
                    if (hasAddNum) {
                        finalText = currentText + text;
                    } else {
                        finalText = text;
                        hasAddNum = true;
                    }
                } else {
                    if (currentText.length() < 26) {
                        finalText = currentText + text;
                    } else {
                        finalText = currentText;
                    }
                }
            }
        }
        txtDisplay.setText(finalText);
    }

    private void doOperator(char op) {
        // Calculate when continue click operator
        BigDecimal currentNum = new BigDecimal(txtDisplay.getText());
        if (hasClickOperator) {
            if (hasAddNum) {
                System.out.println("A");
                secondNum = currentNum;
                doArithmeticOperator(firstNum, secondNum);
                firstNum = ans;
            }
        } else {
            firstNum = currentNum;
        }
        operator = op;
        hasClickOperator = true;
        // Reset check
        hasAddNum = false;
        hasSecondNum = false;
        hasEqual = false;
        hasCalculate = true;
    }

    private void enableButton(boolean status) {
        // Enable follow status given
        btnPlus.setEnabled(status);
        btnMinus.setEnabled(status);
        btnMultiply.setEnabled(status);
        btnDivide.setEnabled(status);
        btnOpposite.setEnabled(status);
        btnDot.setEnabled(status);
        btnStoreClear.setEnabled(status);
        btnStoreResult.setEnabled(status);
        btnStorePlus.setEnabled(status);
        btnStoreMinus.setEnabled(status);
        btnInverse.setEnabled(status);
        btnPercent.setEnabled(status);
        btnSquareRoot.setEnabled(status);
    }

    private boolean isNumeric() {
        // Check is numeric
        try {
            Double num = Double.parseDouble(txtDisplay.getText());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private String formatDisplayString(BigDecimal value) {
        // Format display string with mathematic convention
        int scale = 17;
        value = value.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        NumberFormat formatter = new DecimalFormat("0.0E0");
        formatter.setRoundingMode(RoundingMode.HALF_EVEN);
        formatter.setMinimumFractionDigits(scale);
        String strValue = value.stripTrailingZeros().toPlainString();
        if (strValue.length() > 24) {
            strValue = formatter.format(value);
            if (Math.abs(Integer.parseInt(strValue.split("E")[1])) >= 10000) {
                return "Overflow";
            }
        }
        return strValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDisplay = new javax.swing.JTextField();
        btnStoreClear = new javax.swing.JButton();
        btnStoreResult = new javax.swing.JButton();
        btnStorePlus = new javax.swing.JButton();
        btnStoreMinus = new javax.swing.JButton();
        btnSquareRoot = new javax.swing.JButton();
        btnSeven = new javax.swing.JButton();
        btnEight = new javax.swing.JButton();
        btnNine = new javax.swing.JButton();
        btnDivide = new javax.swing.JButton();
        btnPercent = new javax.swing.JButton();
        btnFour = new javax.swing.JButton();
        btnFive = new javax.swing.JButton();
        btnSix = new javax.swing.JButton();
        btnMultiply = new javax.swing.JButton();
        btnInverse = new javax.swing.JButton();
        btnOne = new javax.swing.JButton();
        btnTwo = new javax.swing.JButton();
        btnThree = new javax.swing.JButton();
        btnMinus = new javax.swing.JButton();
        btnZero = new javax.swing.JButton();
        btnDot = new javax.swing.JButton();
        btnOpposite = new javax.swing.JButton();
        btnPlus = new javax.swing.JButton();
        btnEqual = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnClearAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Calculator");
        setLocation(new java.awt.Point(800, 300));
        setResizable(false);

        txtDisplay.setEditable(false);
        txtDisplay.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        txtDisplay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDisplay.setText("0");
        txtDisplay.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));

        btnStoreClear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnStoreClear.setText("MC");
        btnStoreClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStoreClearActionPerformed(evt);
            }
        });

        btnStoreResult.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnStoreResult.setText("MR");
        btnStoreResult.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStoreResultActionPerformed(evt);
            }
        });

        btnStorePlus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnStorePlus.setText("M+");
        btnStorePlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStorePlusActionPerformed(evt);
            }
        });

        btnStoreMinus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnStoreMinus.setText("M-");
        btnStoreMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStoreMinusActionPerformed(evt);
            }
        });

        btnSquareRoot.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSquareRoot.setText("sqrt");
        btnSquareRoot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSquareRootActionPerformed(evt);
            }
        });

        btnSeven.setBackground(new java.awt.Color(102, 153, 255));
        btnSeven.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSeven.setText("7");
        btnSeven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSevenActionPerformed(evt);
            }
        });

        btnEight.setBackground(new java.awt.Color(102, 153, 255));
        btnEight.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEight.setText("8");
        btnEight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEightActionPerformed(evt);
            }
        });

        btnNine.setBackground(new java.awt.Color(102, 153, 255));
        btnNine.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNine.setText("9");
        btnNine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNineActionPerformed(evt);
            }
        });

        btnDivide.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDivide.setText("/");
        btnDivide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDivideActionPerformed(evt);
            }
        });

        btnPercent.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPercent.setText("%");
        btnPercent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPercentActionPerformed(evt);
            }
        });

        btnFour.setBackground(new java.awt.Color(102, 153, 255));
        btnFour.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFour.setText("4");
        btnFour.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFourActionPerformed(evt);
            }
        });

        btnFive.setBackground(new java.awt.Color(102, 153, 255));
        btnFive.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnFive.setText("5");
        btnFive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiveActionPerformed(evt);
            }
        });

        btnSix.setBackground(new java.awt.Color(102, 153, 255));
        btnSix.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSix.setText("6");
        btnSix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSixActionPerformed(evt);
            }
        });

        btnMultiply.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnMultiply.setText("x");
        btnMultiply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMultiplyActionPerformed(evt);
            }
        });

        btnInverse.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnInverse.setText("1/X");
        btnInverse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInverseActionPerformed(evt);
            }
        });

        btnOne.setBackground(new java.awt.Color(102, 153, 255));
        btnOne.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnOne.setText("1");
        btnOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOneActionPerformed(evt);
            }
        });

        btnTwo.setBackground(new java.awt.Color(102, 153, 255));
        btnTwo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTwo.setText("2");
        btnTwo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTwoActionPerformed(evt);
            }
        });

        btnThree.setBackground(new java.awt.Color(102, 153, 255));
        btnThree.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThree.setText("3");
        btnThree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThreeActionPerformed(evt);
            }
        });

        btnMinus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnMinus.setText("_");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        btnZero.setBackground(new java.awt.Color(102, 153, 255));
        btnZero.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnZero.setText("0");
        btnZero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZeroActionPerformed(evt);
            }
        });

        btnDot.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDot.setText(".");
        btnDot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotActionPerformed(evt);
            }
        });

        btnOpposite.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnOpposite.setText("+/_");
        btnOpposite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOppositeActionPerformed(evt);
            }
        });

        btnPlus.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnPlus.setText("+");
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        btnEqual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnEqual.setText("=");
        btnEqual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEqualActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(255, 102, 102));
        btnClear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClear.setText("<");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnClearAll.setBackground(new java.awt.Color(255, 102, 102));
        btnClearAll.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnClearAll.setText("C");
        btnClearAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDisplay)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnStoreClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStoreResult, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnStorePlus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStoreMinus, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSquareRoot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSeven, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEight, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNine, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDivide, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnOpposite, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnZero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnDot, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnOne, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnThree, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEqual, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFour, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFive, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSix, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnMultiply, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnInverse, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearAll, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSquareRoot, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnStoreClear, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStorePlus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStoreResult, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnStoreMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEight, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSeven, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDivide, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPercent, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnNine, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFive, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnFour, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnMultiply, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnInverse, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSix, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThree, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOne, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnZero, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOpposite, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDot, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnEqual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOneActionPerformed
        // TODO add your handling code here:
        doShowText("1");
    }//GEN-LAST:event_btnOneActionPerformed

    private void btnTwoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTwoActionPerformed
        // TODO add your handling code here:
        doShowText("2");
    }//GEN-LAST:event_btnTwoActionPerformed

    private void btnThreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThreeActionPerformed
        // TODO add your handling code here:
        doShowText("3");
    }//GEN-LAST:event_btnThreeActionPerformed

    private void btnFourActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFourActionPerformed
        // TODO add your handling code here:
        doShowText("4");
    }//GEN-LAST:event_btnFourActionPerformed

    private void btnFiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiveActionPerformed
        // TODO add your handling code here:
        doShowText("5");
    }//GEN-LAST:event_btnFiveActionPerformed

    private void btnSixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSixActionPerformed
        // TODO add your handling code here:
        doShowText("6");
    }//GEN-LAST:event_btnSixActionPerformed

    private void btnSevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSevenActionPerformed
        // TODO add your handling code here:
        doShowText("7");
    }//GEN-LAST:event_btnSevenActionPerformed

    private void btnEightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEightActionPerformed
        // TODO add your handling code here:
        doShowText("8");
    }//GEN-LAST:event_btnEightActionPerformed

    private void btnNineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNineActionPerformed
        // TODO add your handling code here:
        doShowText("9");
    }//GEN-LAST:event_btnNineActionPerformed

    private void btnClearAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearAllActionPerformed
        // TODO add your handling code here:
        // Reset all button to default new
        hasEqual = false;
        hasCalculate = false;
        hasClickOperator = false;
        hasAddNum = false;
        hasSecondNum = false;
        firstNum = new BigDecimal("0");
        secondNum = new BigDecimal("0");
        ans = new BigDecimal("0");
        operator = 'o';
        txtDisplay.setText("0");
        enableButton(true);
    }//GEN-LAST:event_btnClearAllActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
        // Clear text
        if (!hasCalculate) {
            int length = txtDisplay.getText().length();
            int lastPosition = txtDisplay.getText().length() - 1;
            String storeString = txtDisplay.getText();
            if (length > 1) {
                txtDisplay.setText(storeString.substring(0, lastPosition));
            } else {
                txtDisplay.setText("0");
            }
        }
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnDotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotActionPerformed
        // TODO add your handling code here:
        if (!txtDisplay.getText().contains(".")) {
            txtDisplay.setText(txtDisplay.getText() + ".");
        }
    }//GEN-LAST:event_btnDotActionPerformed

    private void btnZeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZeroActionPerformed
        // TODO add your handling code here:
        doShowText("0");
    }//GEN-LAST:event_btnZeroActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        // TODO add your handling code here:
        doOperator('+');
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        // TODO add your handling code here:
        doOperator('-');
    }//GEN-LAST:event_btnMinusActionPerformed

    private void btnMultiplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMultiplyActionPerformed
        // TODO add your handling code here:
        doOperator('*');
    }//GEN-LAST:event_btnMultiplyActionPerformed

    private void btnDivideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDivideActionPerformed
        // TODO add your handling code here:
        doOperator('/');
    }//GEN-LAST:event_btnDivideActionPerformed

    private void btnEqualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEqualActionPerformed
        // TODO add your handling code here:
        if (!isNumeric()) {
            txtDisplay.setText("0");
            enableButton(true);
            btnClearAllActionPerformed(evt);
            return;
        }
        BigDecimal currentNum = new BigDecimal(txtDisplay.getText());
        // Check logic when click equal after click operator
        if (hasClickOperator) {
            if (hasAddNum) {
                secondNum = currentNum;
                doArithmeticOperator(firstNum, secondNum);
                hasSecondNum = true;
            } else {
                if (hasSecondNum) {
                    doArithmeticOperator(firstNum, secondNum);
                } else {
                    secondNum = currentNum;
                    doArithmeticOperator(firstNum, secondNum);
                    hasSecondNum = true;
                }
            }
            firstNum = ans;
        // If not click operator, do another logic
        } else {
            if (hasSecondNum) {
                doArithmeticOperator(currentNum, secondNum);
            }
        }
        // Add animation for equal button
        Timer blinkTimer = new Timer(70, new ActionListener() {
            private int count = 0;
            private final int maxCount = 2;
            private boolean on = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (count >= maxCount) {
                    btnEqual.setBackground(null);
                    ((Timer) e.getSource()).stop();
                } else {
                    btnEqual.setBackground(on ? Color.YELLOW : null);
                    on = !on;
                    count++;
                }
            }
        });
        blinkTimer.start();
        hasEqual = true;
        hasCalculate = true;
        hasAddNum = false;
        enableButton(true);
    }//GEN-LAST:event_btnEqualActionPerformed

    private void btnInverseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInverseActionPerformed
        // TODO add your handling code here:
        // Check divide by 0
        if (txtDisplay.getText().equals("0")) {
            txtDisplay.setText("Cannot divide by zero");
            enableButton(false);
            return;
        }
        // Do inverse
        double inverseAns = 1 / Double.parseDouble(txtDisplay.getText());
        BigDecimal value = new BigDecimal(Double.toString(inverseAns));
        String strValue = formatDisplayString(value);
        txtDisplay.setText(strValue);
        // Reset after click equal button
        if (hasEqual) {
            hasClickOperator = false;
            hasAddNum = false;
            hasEqual = false;
        }
        hasCalculate = true;
    }//GEN-LAST:event_btnInverseActionPerformed

    private void btnOppositeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOppositeActionPerformed
        // TODO add your handling code here:
        // Get opposite number of current number
        BigDecimal value = new BigDecimal(txtDisplay.getText());
        txtDisplay.setText(value.negate().toString());
    }//GEN-LAST:event_btnOppositeActionPerformed

    private void btnStoreClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStoreClearActionPerformed
        // TODO add your handling code here:
        // Clear store number
        storeNum.clear();
        btnStoreClear.setEnabled(false);
        btnStoreResult.setEnabled(false);
        hasCalculate = true;
    }//GEN-LAST:event_btnStoreClearActionPerformed

    private void btnStoreResultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStoreResultActionPerformed
        // TODO add your handling code here:
        // Get store result
        BigDecimal sum = new BigDecimal("0");
        for (int i = 0; i < storeNum.size(); i++) {
            sum = sum.add(storeNum.get(i));
        }
        txtDisplay.setText(sum.toString());
        hasCalculate = true;
    }//GEN-LAST:event_btnStoreResultActionPerformed

    private void btnStorePlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStorePlusActionPerformed
        // TODO add your handling code here:
        // Store number in plus version
        BigDecimal value = new BigDecimal(txtDisplay.getText());
        storeNum.add(value);
        btnStoreClear.setEnabled(true);
        btnStoreResult.setEnabled(true);
        hasCalculate = true;
    }//GEN-LAST:event_btnStorePlusActionPerformed

    private void btnStoreMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStoreMinusActionPerformed
        // TODO add your handling code here:
        // Store number in minus version
        BigDecimal value = new BigDecimal(txtDisplay.getText());
        storeNum.add(value.negate());
        btnStoreClear.setEnabled(true);
        btnStoreResult.setEnabled(true);
        hasCalculate = true;
    }//GEN-LAST:event_btnStoreMinusActionPerformed

    private void btnSquareRootActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSquareRootActionPerformed
        // TODO add your handling code here:
        // Check do not square root with negative number
        if (Double.parseDouble(txtDisplay.getText()) >= 0) {
            BigDecimal value = new BigDecimal(Math.sqrt(Double.parseDouble(txtDisplay.getText())));
            String strValue = formatDisplayString(value);
            txtDisplay.setText(strValue);
        } else {
            txtDisplay.setText("Invalid input");
            enableButton(false);
        }
        // Reset after click equal button
        if (hasEqual) {
            hasClickOperator = false;
            hasAddNum = false;
            hasEqual = false;
        }
        hasCalculate = true;
    }//GEN-LAST:event_btnSquareRootActionPerformed

    private void btnPercentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPercentActionPerformed
        // TODO add your handling code here:
        BigDecimal currentNum = new BigDecimal(txtDisplay.getText());
        BigDecimal value;
        String strValue;
        // Do logic percent after click equal button
        if (hasEqual) {
            value = currentNum.multiply(ans.divide(new BigDecimal(100)));
            strValue = formatDisplayString(value);
        // Do another logic when unclick equal button
        } else {
            if (hasClickOperator) {
                value = firstNum.multiply(currentNum.divide(new BigDecimal(100)));
                strValue = formatDisplayString(value);
            } else {
                strValue = "0";
            }
        }
        // Reset after click equal button
        if (hasEqual) {
            hasClickOperator = false;
            hasAddNum = false;
            hasEqual = false;
        }
        txtDisplay.setText(strValue);
        hasCalculate = true;
    }//GEN-LAST:event_btnPercentActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnClearAll;
    private javax.swing.JButton btnDivide;
    private javax.swing.JButton btnDot;
    private javax.swing.JButton btnEight;
    private javax.swing.JButton btnEqual;
    private javax.swing.JButton btnFive;
    private javax.swing.JButton btnFour;
    private javax.swing.JButton btnInverse;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton btnMultiply;
    private javax.swing.JButton btnNine;
    private javax.swing.JButton btnOne;
    private javax.swing.JButton btnOpposite;
    private javax.swing.JButton btnPercent;
    private javax.swing.JButton btnPlus;
    private javax.swing.JButton btnSeven;
    private javax.swing.JButton btnSix;
    private javax.swing.JButton btnSquareRoot;
    private javax.swing.JButton btnStoreClear;
    private javax.swing.JButton btnStoreMinus;
    private javax.swing.JButton btnStorePlus;
    private javax.swing.JButton btnStoreResult;
    private javax.swing.JButton btnThree;
    private javax.swing.JButton btnTwo;
    private javax.swing.JButton btnZero;
    private javax.swing.JTextField txtDisplay;
    // End of variables declaration//GEN-END:variables
}
