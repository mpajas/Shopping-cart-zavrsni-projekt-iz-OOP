package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    ArrayList<Product> products;
    ArrayList<Product> inCartProducts;

    public MyPanel(Store store)
    {
        products  = store.findProducts();
        inCartProducts = new ArrayList<Product>();

        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setSize(600,600);
        frame.setTitle(store.getStoreName());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setAutoscrolls(true);
        frame.add(itemsPanel,BorderLayout.CENTER);


        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 600, 300);

        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(600,300));
        contentPane.add(scrollPane);
        //---------------frame 2----------------------
        JFrame frame2 = new JFrame();
        frame2.setLayout(new BorderLayout());

        frame2.setSize(600,600);
        frame2.setTitle("Shopping cart");
        frame2.setVisible(true);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cartItemsPanel = new JPanel();
        cartItemsPanel.setLayout(new BoxLayout(cartItemsPanel, BoxLayout.Y_AXIS));
        cartItemsPanel.setAutoscrolls(true);
        frame2.add(cartItemsPanel,BorderLayout.CENTER);

        JScrollPane scrollPane2 = new JScrollPane(cartItemsPanel);
        scrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(0, 0, 600, 300);

        JPanel contentPane2 = new JPanel(null);
        contentPane2.setPreferredSize(new Dimension(600,300));
        contentPane2.add(scrollPane2);

        //napravi listu objekata za kupiti
        draw(itemsPanel, cartItemsPanel);
        drawCart(cartItemsPanel);

        frame.add(contentPane,BorderLayout.CENTER);
        frame2.add(contentPane2, BorderLayout.CENTER);
        frame.pack();
        frame2.pack();
    }

    private void getReceipt()
    {
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());

        frame.setSize(250,600);
        frame.setTitle("Receipt");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea("");
        StringBuffer display = new StringBuffer();
        String s = String.format("%-15s\t %5s\t %10s\n", "Item", "Qty", "Price");
        String s1 = String.format("%-15s\t %5s\t %10s\n", "----", "---", "-----");

        display.append(s);
        display.append(s1);
        double sum = 0;
        for (int i = 0; i< inCartProducts.size(); i++) {
            sum+=inCartProducts.get(i).getPrice();
            display.append(String.format("%.15s\t %5d\t %10.2f\n", inCartProducts.get(i).getName(), 1, inCartProducts.get(i).getPrice()));
        }
        display.append(String.format("%-15s\t %5s\t %10s\n", "", "", "-----"));
        display.append(String.format("%-15s\t %5s\t %10.2f\n", "Total", "", sum));
        textArea.setText(display.toString());
        try {
            PrintWriter writer = new PrintWriter("receipt.txt", "UTF-8");
        }catch (Exception e)
        {}

        try (PrintStream out = new PrintStream(new FileOutputStream("receipt.txt"))) {
            out.print(display.toString());
        }catch (Exception e)
        {
            System.out.println(e);
        }
        frame.getContentPane().add(textArea, BorderLayout.CENTER);

    }

    private void drawCart(JPanel cartItemsPanel) {

        for (int i = 0; i < inCartProducts.size(); i++) {
            int counter = i;
            JPanel panel1 = new JPanel();
            panel1.setLayout(new FlowLayout());
            panel1.setBackground(Color.white);
            panel1.setPreferredSize(new Dimension(250, 150));

            JPanel p2 = new JPanel();
            p2.setLayout(new FlowLayout());
            p2.setBackground(Color.white);
            p2.setPreferredSize(new Dimension(200, 140));
            //labels
            JLabel l1 = new JLabel("Name: ");
            l1.setForeground(Color.black);
            l1.setPreferredSize(new Dimension(80, 20));
            JLabel l2 = new JLabel("Price: ");
            l2.setForeground(Color.black);
            l2.setPreferredSize(new Dimension(80, 20));
            JLabel l3 = new JLabel("Origin: ");
            l3.setForeground(Color.black);
            l3.setPreferredSize(new Dimension(80, 20));
            JLabel l4 = new JLabel("Expiry date: ");
            l4.setForeground(Color.black);
            l4.setPreferredSize(new Dimension(80, 20));
            //texts
            JTextField t1 = new JTextField(inCartProducts.get(i).getName());
            t1.setPreferredSize(new Dimension(100, 20));
            JTextField t2 = new JTextField(inCartProducts.get(i).getPrice().toString());
            t2.setPreferredSize(new Dimension(100, 20));
            JTextField t3 = new JTextField(inCartProducts.get(i).getOrigin());
            t3.setPreferredSize(new Dimension(100, 20));
            JTextField t4 = new JTextField(inCartProducts.get(i).getExpiry());
            t4.setPreferredSize(new Dimension(100, 20));

            p2.add(l1);
            p2.add(t1);
            p2.add(l2);
            p2.add(t2);
            p2.add(l3);
            p2.add(t3);
            p2.add(l4);
            p2.add(t4);

            panel1.add(p2);
            cartItemsPanel.add(panel1);
        }
    }

    private void draw(JPanel itemsPanel, JPanel cartItemsPanel)
    {
        for (int i = 0; i<products.size(); i++){
            int counter = i;
            JPanel panel1 = new JPanel();
            panel1.setLayout(new FlowLayout());
            panel1.setBackground(Color.white);
            panel1.setPreferredSize(new Dimension(250,150));

            JPanel p2 = new JPanel();
            p2.setLayout(new FlowLayout());
            p2.setBackground(Color.white);
            p2.setPreferredSize(new Dimension(200,140));
            //labels
            JLabel l1 = new JLabel("Name: ");
            l1.setForeground(Color.black);
            l1.setPreferredSize(new Dimension(80,20));
            JLabel l2 = new JLabel("Price: ");
            l2.setForeground(Color.black);
            l2.setPreferredSize(new Dimension(80,20));
            JLabel l3 = new JLabel("Origin: ");
            l3.setForeground(Color.black);
            l3.setPreferredSize(new Dimension(80,20));
            JLabel l4 = new JLabel("Expiry date: ");
            l4.setForeground(Color.black);
            l4.setPreferredSize(new Dimension(80,20));
            //texts
            JTextField t1 = new JTextField(products.get(i).getName());
            t1.setPreferredSize(new Dimension(100, 20));
            JTextField t2 = new JTextField(products.get(i).getPrice().toString());
            t2.setPreferredSize(new Dimension(100, 20));
            JTextField t3 = new JTextField(products.get(i).getOrigin());
            t3.setPreferredSize(new Dimension(100, 20));
            JTextField t4 = new JTextField(products.get(i).getExpiry());
            t4.setPreferredSize(new Dimension(100, 20));

            JButton b1 = new JButton("Stavi u košaricu");
            JButton b2 = new JButton("Popust 20%");
            JButton b3 = new JButton("Popust 30%");
            JButton b4 = new JButton("Popust 50%");
            JPanel p3 = new JPanel();
            p3.setLayout(new GridLayout(2,2));
            p3.setBackground(Color.white);
            //p3.setPreferredSize(new Dimension(200,150));

            b2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    products.set(counter,new DiscountDecorator20(products.get(counter)));
                    itemsPanel.removeAll();
                    draw(itemsPanel, cartItemsPanel);
                }
            });

            b3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    products.set(counter,new DiscountDecorator30(products.get(counter)));
                    itemsPanel.removeAll();
                    draw(itemsPanel, cartItemsPanel);
                }
            });

            b4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    products.set(counter,new DiscountDecorator50(products.get(counter)));
                    itemsPanel.removeAll();
                    draw(itemsPanel, cartItemsPanel);
                }
            });

            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    inCartProducts.add(products.get(counter));
                    cartItemsPanel.removeAll();
                    JButton buyButton = new JButton("Buy");
                    JPanel menu = new JPanel();
                    menu.add(buyButton);
                    cartItemsPanel.add(menu, BorderLayout.NORTH);

                    buyButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            getReceipt();
                        }
                    });

                    drawCart(cartItemsPanel);
                }
            });

            p3.add(b1);
            p3.add(b2);
            p3.add(b3);
            p3.add(b4);
            p2.add(l1);
            p2.add(t1);
            p2.add(l2);
            p2.add(t2);
            p2.add(l3);
            p2.add(t3);
            p2.add(l4);
            p2.add(t4);

            panel1.add(p2);
            panel1.add(p3);
            itemsPanel.add(panel1);
        }
    }
}
