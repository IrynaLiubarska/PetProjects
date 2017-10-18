package ui;

import core.Hole;
import core.KalahEngine;
import core.Stones;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static core.KalahEngine.USER_1;
import static core.KalahEngine.USER_2;

public class Board implements ActionListener {

    private KalahEngine kalahEngine = new KalahEngine();
    private Stones stones = kalahEngine.getStones();
    private JButton[] holes1 = new JButton[7];
    private JButton[] holes2 = new JButton[7];
    private Map<JButton, Hole> buttonHoleMap = new HashMap<>();
    private JButton kalah1 = new JButton("kalah1");
    private JButton kalah2 = new JButton("kalah2");
    private JFrame frame;

    public Board() {
        JPanel p1 = new JPanel();
        BorderLayout bl = new BorderLayout();
        p1.setLayout(bl);
        p1.add("West", kalah1);
        p1.add("East", kalah2);
        JPanel windowContent = new JPanel();
        GridLayout gl = new GridLayout(2, 6);
        windowContent.setLayout(gl);
        addAllButtons(windowContent);
        p1.add("Center", windowContent);
        frame = new JFrame("Kalaha ui.Board");
        frame.setContentPane(p1);
        frame.pack();
        frame.setVisible(true);
        initGameConfig();
    }

    private void initGameConfig() { // TODO better name: initHolesState
        kalah1.setEnabled(false);
        kalah2.setEnabled(false);
        setButtonEnable(kalahEngine.getCurrentUser(), false);
        writeAmountOfStonesInEachHole();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        Hole buttonHole = buttonHoleMap.get(clickedButton);
        kalahEngine.move(buttonHole.getIndex());
        if (kalahEngine.isGameOver()) {
            checkWhoIsWinner(); // TODO promptWinner()
            setButtonEnable(kalahEngine.getCurrentUser(), true);
            // disableButtons(USER_1);
            // disableButtons(USER_2);
        } else {
            // enableButtons(currentUser);
            // disableButtons(!currentUser);
            setButtonEnable(kalahEngine.getCurrentUser(), false);
        }
        writeAmountOfStonesInEachHole();
    }

    private void checkWhoIsWinner() {
        if (kalahEngine.isNoWinner()) { // TODO better name (isTie())
            showMessage(frame, "There is no winner");
        } else if (kalahEngine.getWinner()) {
            showMessage(frame, "First user is winner");
        } else {
            showMessage(frame, "Second user is winner");
        }
    }

    private void showMessage(JFrame frame, String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    private void addAllButtons(JPanel windowContent) {
        for (int i = 5; i >= 0; i--) {
            generateNewButton(windowContent, i, USER_1, holes1);
        }
        for (int i = 0; i < 6; i++) {
            generateNewButton(windowContent, i, USER_2, holes2);
        }
        holes1[6] = kalah1;
        holes2[6] = kalah2;
        buttonHoleMap.put(kalah1, new Hole(6, USER_1));
        buttonHoleMap.put(kalah2, new Hole(6, USER_2));
    }

    private void generateNewButton(JPanel windowContent, int i, boolean user, JButton[] holes) {
        holes[i] = new JButton(String.valueOf(stones.getStones(i, user))); // TODO bad
        windowContent.add(holes[i]);
        holes[i].addActionListener(this);
        buttonHoleMap.put(holes[i], new Hole(i, user));
    }

    private void writeAmountOfStonesInEachHole() {
        for (int i = 6; i >= 0; i--) {
            holes1[i].setText(String.valueOf(stones.getStones(i, USER_1)));
            holes2[i].setText(String.valueOf(stones.getStones(i, USER_2)));
        }
    }

    private void setButtonEnable(boolean user, boolean finish) { // TODO what is finish?
        for (int i = 0; i < 6; i++) {
            if (finish) {
                holes1[i].setEnabled(false);
                holes2[i].setEnabled(false);
            } else {
                holes1[i].setEnabled(user);
                holes2[i].setEnabled(!user);
            }
        }
    }
}
