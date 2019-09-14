import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
//add next move promt to user
//add choose player function
//add ????
//Refactor in next life ...
// Documentation will be done in hell...

class TicTacToe extends Minimax {

    private JFrame f;
    private JButton jb1;
    private JLabel[] jl = new JLabel[13];

    private JPanel[] jp = new JPanel[3];

    private JRadioButton rb1;
    private JRadioButton rb2;
    private ButtonGroup bg;
    private Actions act;

    private String player = "X";
    private String computer = "O";
    private boolean over;
    private String[] values = new String[9];
    int[][] winningLine = new int[8][3];
    Minimax ai;
    private Timer t;

    void initGame() {

        over = false;
        for (int i = 0; i < values.length; i++) {
            values[i] = "";
        }
        ai = new Minimax(player, computer);
        for (int i = 0; i < 3; i++) {
            winningLine[i][0] = i * 3;
            winningLine[i][1] = i * 3 + 1;
            winningLine[i][2] = i * 3 + 2;
            winningLine[i + 3][0] = i;
            winningLine[i + 3][1] = i + 3;
            winningLine[i + 3][2] = i + 6;
        }
        winningLine[6][0] = 0;
        winningLine[6][1] = 4;
        winningLine[6][2] = 8;
        winningLine[7][0] = 2;
        winningLine[7][1] = 4;
        winningLine[7][2] = 6;
    }

    int[] winLine = new int[3];

    String checkStatus(String[] board) {
        String winner = "";
        for (int i = 0; i < winningLine.length; i++) {
            int[] line = winningLine[i];

            if (board[line[0]].equals(board[line[1]]) && board[line[1]].equals(board[line[2]])
                    && !board[line[0]].equals("")) {
                winner = board[line[0]];
                winLine = line;
                break;
            }
        }
        if (!winner.equals("")) {
            over = true;
        }
        if (winner.equals(player)) {
            return player;

        } else if (winner.equals(computer)) {
            return computer;

        } else {
            return "";
        }
    }

    boolean anyMoveLeft(String[] board) {
        for (int i = 0; i < 9; i++) {
            if (board[i].equals("")) {
                return true;
            }
        }
        return false;
    }

    void win() {
        String s = checkStatus(values);
        if (!s.equals("")) {
            t.stop();
            jl[12].setText("winner is " + s);
            for (int i = 0; i < values.length; i++) {
                if (i == winLine[0] || i == winLine[1] || i == winLine[2]) {
                    jl[i].setForeground(Color.GREEN);
                }
                jl[i].removeMouseListener(act);
            }
            jl[11].setText("");

        } else if (!anyMoveLeft(values)) {
            over = true;
            t.stop();
            jl[12].setText("draw");
            for (int i = 0; i < values.length; i++) {
                jl[i].removeMouseListener(act);
            }
            jl[11].setText("");
        }
    }

    void reset() {
        initGame();
        for (int i = 0; i < values.length; i++) {
            jl[i].setText("");
            jl[i].setForeground(Color.WHITE);
            jl[i].addMouseListener(act);
        }
        jl[11].setText("");
        jl[12].setText("");
        winLine = new int[3];

    }

    void gui() {
        f = new JFrame("TicTacToe");
        initGame();
        jp[0] = new JPanel();
        jp[1] = new JPanel();
        jp[2] = new JPanel();

        jl[0] = new JLabel("X");

        jl[1] = new JLabel("X");
        jl[2] = new JLabel("X");
        jl[3] = new JLabel("X");
        jl[4] = new JLabel("X");
        jl[5] = new JLabel("X");
        jl[6] = new JLabel("X");
        jl[7] = new JLabel("X");
        jl[8] = new JLabel("X");
        jl[9] = new JLabel();
        jl[10] = new JLabel();
        jl[11] = new JLabel();
        jl[12] = new JLabel();

        jb1 = new JButton();
        jb1.setBackground(new Color(0x37B8F9));
        bg = new ButtonGroup();
        rb1 = new JRadioButton();
        rb1.setSelected(true);
        rb2 = new JRadioButton();

        jb1.setText("New Game");

        rb1.setText("X Player");

        rb2.setText("O Player");

        jl[9].setText("Choose Player:-");

        jl[10].setText("Current Player :-");

        jl[11].setText("");

        bg.add(rb1);
        bg.add(rb2);

        for (JPanel j : jp) {
            j.setBackground(new Color(0x282C34));
        }
        for (JLabel j : jl) {
            j.setForeground(new java.awt.Color(255, 255, 255));
        }
        rb1.setForeground(new java.awt.Color(255, 255, 255));
        rb2.setForeground(new java.awt.Color(255, 255, 255));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jp[2].setLayout(new AbsoluteLayout());

        jl[1].setBackground(new java.awt.Color(60, 84, 108));
        jl[1].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[1].setForeground(new java.awt.Color(255, 255, 255));
        jl[1].setHorizontalAlignment(SwingConstants.CENTER);
        jl[1].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[1].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[1].setOpaque(true);
        jp[2].add(jl[1], new AbsoluteConstraints(280, 90, 100, 100));

        jl[2].setBackground(new java.awt.Color(60, 84, 108));
        jl[2].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[2].setForeground(new java.awt.Color(255, 255, 255));
        jl[2].setHorizontalAlignment(SwingConstants.CENTER);
        jl[2].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[2].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[2].setOpaque(true);
        jp[2].add(jl[2], new AbsoluteConstraints(380, 90, 100, 100));

        jl[3].setBackground(new java.awt.Color(60, 84, 108));
        jl[3].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[3].setForeground(new java.awt.Color(255, 255, 255));
        jl[3].setHorizontalAlignment(SwingConstants.CENTER);
        jl[3].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[3].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[3].setOpaque(true);
        jp[2].add(jl[3], new AbsoluteConstraints(180, 190, 100, 100));

        jl[4].setBackground(new java.awt.Color(60, 84, 108));
        jl[4].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[4].setForeground(new java.awt.Color(255, 255, 255));
        jl[4].setHorizontalAlignment(SwingConstants.CENTER);
        jl[4].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[4].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[4].setOpaque(true);
        jp[2].add(jl[4], new AbsoluteConstraints(280, 190, 100, 100));

        jl[5].setBackground(new java.awt.Color(60, 84, 108));
        jl[5].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[5].setForeground(new java.awt.Color(255, 255, 255));
        jl[5].setHorizontalAlignment(SwingConstants.CENTER);
        jl[5].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[5].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[5].setOpaque(true);
        jp[2].add(jl[5], new AbsoluteConstraints(380, 190, 100, 100));

        jl[6].setBackground(new java.awt.Color(60, 84, 108));
        jl[6].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[6].setForeground(new java.awt.Color(255, 255, 255));
        jl[6].setHorizontalAlignment(SwingConstants.CENTER);
        jl[6].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[6].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[6].setOpaque(true);
        jp[2].add(jl[6], new AbsoluteConstraints(180, 290, 100, 100));

        jl[7].setBackground(new java.awt.Color(60, 84, 108));
        jl[7].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[7].setForeground(new java.awt.Color(255, 255, 255));
        jl[7].setHorizontalAlignment(SwingConstants.CENTER);
        jl[7].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[7].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[7].setOpaque(true);
        jp[2].add(jl[7], new AbsoluteConstraints(280, 290, 100, 100));

        jl[8].setBackground(new java.awt.Color(60, 84, 108));
        jl[8].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[8].setForeground(new java.awt.Color(255, 255, 255));
        jl[8].setHorizontalAlignment(SwingConstants.CENTER);
        jl[8].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[8].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[8].setOpaque(true);
        jp[2].add(jl[8], new AbsoluteConstraints(380, 290, 100, 100));

        jl[12].setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jl[12].setHorizontalAlignment(SwingConstants.CENTER);
        jp[2].add(jl[12], new AbsoluteConstraints(-2, 400, 670, 80));

        jl[0].setBackground(new java.awt.Color(60, 84, 108));
        jl[0].setFont(new java.awt.Font("Lucida Bright", 1, 42)); // NOI18N
        jl[0].setForeground(new java.awt.Color(255, 255, 255));
        jl[0].setHorizontalAlignment(SwingConstants.CENTER);
        jl[0].setText("X");
        jl[0].setBorder(
                BorderFactory.createEtchedBorder(new java.awt.Color(79, 112, 144), new java.awt.Color(79, 112, 144)));
        jl[0].setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jl[0].setOpaque(true);
        jp[2].add(jl[0], new AbsoluteConstraints(180, 90, 100, 100));

        GroupLayout jPanel1Layout = new GroupLayout(jp[0]);
        jp[0].setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(jp[2], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        jPanel1Layout
                .setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout
                                .createSequentialGroup().addComponent(jp[2], GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 3, Short.MAX_VALUE)));

        f.getContentPane().add(jp[0], java.awt.BorderLayout.CENTER);

        GroupLayout jPanel2Layout = new GroupLayout(jp[1]);
        jp[1].setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addGap(64, 64, 64)
                        .addComponent(jb1, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jl[9], GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rb1, GroupLayout.Alignment.LEADING))
                                .addComponent(rb2))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(jl[10], GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE).addComponent(
                                        jl[11], GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(37, Short.MAX_VALUE)));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup().addGap(35, 35, 35)
                        .addComponent(jb1, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(23, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jl[9], GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(rb1)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(rb2))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jl[10], GroupLayout.PREFERRED_SIZE, 44,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jl[11],
                                                GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)));

        f.getContentPane().add(jp[1], java.awt.BorderLayout.PAGE_START);

        for (int i = 0; i < 9; i++) {
            jl[i].setText("");
        }
        f.setVisible(true);

        f.pack();

        act = new Actions();
        for (int i = 0; i < values.length; i++) {

            jl[i].addMouseListener(act);
            jl[i].addMouseMotionListener(act);
        }
        jb1.addActionListener(act);
        rb1.addActionListener(act);
        rb2.addActionListener(act);

    }

    private class Actions implements ActionListener, MouseListener, MouseMotionListener {

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == jb1) {
                reset();
            }

            if (e.getSource() == rb1) {
                player = "X";
                computer = "O";
                reset();

            }
            if (e.getSource() == rb2) {

                player = "O";
                computer = "X";
                reset();

            }

        }

        public void mouseClicked(MouseEvent e) {

        }

        public void mousePressed(MouseEvent e) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == jl[i] && jl[i].getText().equals("")) {

                    nextMove(i, player);
                    win();
                    if (!over) {
                        int move = ai.findBestMove(values);
                        t = new Timer(200, new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                nextMove(move, computer);
                                win();
                            }
                        });
                        t.setRepeats(false);
                        t.start();

                    }

                }
            }
        }

        public void mouseExited(MouseEvent e) {

        }

        public void mouseReleased(MouseEvent e) {

        }

        public void mouseEntered(MouseEvent e) {

        }

        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            for (int i = 0; i < 9; i++) {
                if (e.getSource() == jl[i]) {
                    jl[i].setBackground(new Color(40, 42, 54));
                } else {
                    jl[i].setBackground(new Color(60, 84, 108));
                }
            }
        }

        void nextMove(int i, String current) {

            jl[i].setText(current);
            values[i] = current;
            jl[11].setText(!current.equals(player) ? "Next Move is : " + "player" + " \'" + player + "\'"
                    : "Next Move is : " + "computer" + " \'" + computer + "\'");

        }

    }

    // class ResizeListener implements ComponentListener {

    // public void componentHidden(ComponentEvent e) {
    // }

    // public void componentMoved(ComponentEvent e) {
    // }

    // public void componentShown(ComponentEvent e) {
    // }

    // public void componentResized(ComponentEvent e) {
    // if (e.getSource() == f) {
    // handleWindowChange(f.getWidth(), f.getHeight());
    // }
    // }

    // void handleWindowChange(int windowWidth, int windowHeight) {
    // }

    // }

    public static void main(String[] agrs) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TicTacToe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TicTacToe().gui();
            }
        });

    }

}