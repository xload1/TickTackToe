package com.ticktacktoe.project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@org.springframework.stereotype.Service
public class Service {
    char [][] board;
    int turns;
    public Service() {
        board = new char[][]{{' ',' ',' ' },{' ',' ',' ' },{' ',' ',' ' }};
        turns = 0;
    }
    boolean isFull(){
        return turns>8;
    }
    String check(){
        for (int i = 0; i < 3; i++) {
            if(board[i][0]==board[i][1]&&board[i][1]==board[i][2]&&board[i][0]!=' ') return board[i][1] + " wins!";
            if(board[0][i]==board[1][i]&&board[1][i]==board[2][i]&&board[0][i]!=' ') return board[i][1] + " wins!";
        }
        if(board[0][0]==board[1][1]&&board[1][1]==board[2][2]&&board[0][0]!=' ') return board[0][0] + " wins!";
        if(board[0][2]==board[1][1]&&board[1][1]==board[2][0]&&board[0][2]!=' ') return board[0][2] + " wins!";
        return "";
    }
}
