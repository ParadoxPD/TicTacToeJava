import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class TicTacToe {

    private JFrame f;

    void gui() {

    }

    private class Action implements ActionListener {

        public void actionPerformed(ActionEvent e) {

        }
    }

    // private class Mouse implements MouseListener {

    // public void mouseClicked(MouseEvent e) {

    // }

    // public void mousePressed(MouseEvent e) {

    // }

    // public void mouseExited(MouseEvent e) {
    // }

    // public void mouseReleased(MouseEvent e) {
    // }

    // public void mouseEntered(MouseEvent e) {
    // }

    // }

    class ResizeListener implements ComponentListener {

        public void componentHidden(ComponentEvent e) {
        }

        public void componentMoved(ComponentEvent e) {
        }

        public void componentShown(ComponentEvent e) {
        }

        public void componentResized(ComponentEvent e) {
            if (e.getSource() == f) {
                handleWindowChange(f.getWidth(), f.getHeight());
            }
        }

        void handleWindowChange(int windowWidth, int windowHeight) {
        }
    }

    public static void main(String[] agrs) {
        new TicTacToe().gui();
    }

}