class Minimax {

    String player = "O";
    String opponent = "X";
    int[][] winningLine = new int[8][3];

    Minimax(String x, String y) {
        opponent = x;
        player = y;

        init();
    }

    Minimax() {
        init();
    }

    void init() {

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

    int findBestMove(String[] currentBoardState) {
        int bestMovePos = -1, bestMoveVal = -1000;
        for (int i = 0; i < currentBoardState.length; i++) {
            int moveVal = 0;
            if (currentBoardState[i].equals("")) {
                currentBoardState[i] = player;
                moveVal = minimax(currentBoardState, 0, false);
                currentBoardState[i] = "";
                if (moveVal > bestMoveVal) {
                    bestMoveVal = moveVal;
                    bestMovePos = i;
                }
            }
        }

        return bestMovePos;
    }

    int minimax(String[] currentBoardState, int depth, boolean isMaximizer) {

        int score = checkVictory(currentBoardState);
        if (score == 10) {
            return score - depth;
        } else if (score == -10) {
            return score + depth;
        } else if (!moveLeft(currentBoardState)) {
            return 0;
        }

        else {
            if (isMaximizer) {
                int bestMoveVal = -1000;
                for (int i = 0; i < currentBoardState.length; i++) {
                    int moveVal = 0;
                    if (currentBoardState[i].equals("")) {
                        currentBoardState[i] = player;
                        moveVal = minimax(currentBoardState, depth + 1, !isMaximizer);
                        currentBoardState[i] = "";
                        bestMoveVal = Math.max(moveVal, bestMoveVal);

                    }
                }
                return bestMoveVal;
            } else {
                int bestMoveVal = 1000;
                for (int i = 0; i < currentBoardState.length; i++) {
                    int moveVal = 0;
                    if (currentBoardState[i].equals("")) {
                        currentBoardState[i] = opponent;
                        moveVal = minimax(currentBoardState, depth + 1, !isMaximizer);
                        currentBoardState[i] = "";
                        bestMoveVal = Math.min(moveVal, bestMoveVal);

                    }
                }
                return bestMoveVal;

            }
        }
    }

    int checkVictory(String[] boardState) {
        String winner = "";
        for (int i = 0; i < winningLine.length; i++) {
            int[] line = winningLine[i];

            if (boardState[line[0]].equals(boardState[line[1]]) && boardState[line[1]].equals(boardState[line[2]])
                    && !boardState[line[0]].equals("")) {
                winner = boardState[line[0]];
                break;
            }
        }
        if (winner.equals(player)) {
            return 10;
        } else if (winner.equals(opponent)) {
            return -10;
        } else {
            return 0;
        }
    }

    boolean moveLeft(String[] boardState) {
        for (int i = 0; i < 9; i++) {
            if (boardState[i].equals("")) {
                return true;
            }
        }
        return false;
    }

    // public static void main(String[] args) {
    // String[] a = { "X", "", "", "", "", "", "", "", "" };
    // System.out.println(new Minimax("O", "X").findBestMove(a));
    // }
}