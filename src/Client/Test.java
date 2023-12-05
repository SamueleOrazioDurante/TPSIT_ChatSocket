/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

/**
 *
 * @author lucad
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
public class Test {
    public static void main(final String[] args) {
        JFrame frame = new JFrame();
        final int FRAME_WIDTH = 1000;
        final int FRAME_HEIGHT = 1000;
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setTitle("Home Library");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        // Top Panel
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout());
        p1.setBackground(Color.LIGHT_GRAY);
        p1.setPreferredSize(new Dimension(950, 100));
        JLabel l1 = new JLabel("All Library Items");
        l1.setForeground(Color.BLACK);
        l1.setPreferredSize(new Dimension(900, 50));
        l1.setFont(l1.getFont().deriveFont(30.0f));
        p1.add(l1);
        // Content Panel
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(-1, 1));
        p2.setBackground(Color.LIGHT_GRAY);
        p2.setPreferredSize(new Dimension(950, 800));
        p2.setAutoscrolls(true);
        JScrollPane scrollPane = new JScrollPane(p2);
        scrollPane.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(0, 0, 950, 800);
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(950, 800));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        for (int i = 0; i < 20; i++) {
            JPanel sp1 = new JPanel();
            sp1.setLayout(new FlowLayout());
            sp1.setBackground(Color.WHITE);
            sp1.setPreferredSize(new Dimension(900, 180));
            sp1.setBorder(new LineBorder(Color.RED));
            p2.add(sp1);
        }
        // contentPane.add(p2);
        contentPane.add(p1, BorderLayout.NORTH);
        // frame.add(p2);
        // frame.setContentPane(contentPane);
        frame.add(contentPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
} 
