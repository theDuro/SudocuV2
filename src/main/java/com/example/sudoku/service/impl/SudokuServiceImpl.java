package com.example.sudoku.service.impl;

import com.example.sudoku.model.SudokuDto;
import com.example.sudoku.service.SudokuService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SudokuServiceImpl implements SudokuService {

    private int[][] field;
    private String fileName;

    public SudokuServiceImpl() {
            this.fileName = "a.csv";
    }

    @Override
    public SudokuDto checkArea() {

        List<Integer> lineList = new ArrayList<Integer>();
        List<Integer> columnList = new ArrayList<Integer>();
        List<Integer> areaList = new ArrayList<Integer>();
        this.field = readFile(this.fileName);
        int[][] areaTable = {
                {this.field[0][0],this.field[0][1],this.field[0][2],this.field[1][0],this.field[1][1],this.field[1][2],this.field[2][0],this.field[2][1],this.field[2][2]},
                {this.field[0][3],this.field[0][4],this.field[0][5],this.field[1][3],this.field[1][4],this.field[1][5],this.field[2][3],this.field[2][4],this.field[2][5]},
                {this.field[0][6],this.field[0][7],this.field[0][8],this.field[1][6],this.field[1][7],this.field[1][8],this.field[2][6],this.field[2][7],this.field[2][8]},
                {this.field[3][0],this.field[3][1],this.field[3][2],this.field[4][0],this.field[4][1],this.field[4][2],this.field[5][0],this.field[5][1],this.field[5][2]},
                {this.field[3][3],this.field[3][4],this.field[3][5],this.field[4][3],this.field[4][4],this.field[4][5],this.field[5][3],this.field[5][4],this.field[5][5]},
                {this.field[3][6],this.field[3][7],this.field[3][8],this.field[4][6],this.field[4][7],this.field[4][8],this.field[5][6],this.field[5][7],this.field[5][8]},
                {this.field[6][0],this.field[6][1],this.field[6][2],this.field[7][0],this.field[7][1],this.field[7][2],this.field[8][0],this.field[8][1],this.field[8][2]},
                {this.field[6][3],this.field[6][4],this.field[6][5],this.field[7][3],this.field[7][4],this.field[7][5],this.field[8][3],this.field[8][4],this.field[8][5]},
                {this.field[6][6],this.field[6][7],this.field[6][8],this.field[7][6],this.field[7][7],this.field[7][8],this.field[8][6],this.field[8][7],this.field[8][8]},
        };



        int numberLine;
        int numberColumn;
        int numberArea;

        for(int i=0; i<9;i++){
            for(int j=0; j<9; j++){
                numberArea = areaTable[i][j];
                for(int x=0; x<9; x++){
                    if(numberArea==areaTable[i][x] && x!=j){
                        areaList.add(i+1);
                    }
                }
            }
        }


        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                numberLine = this.field[i][j];
                numberColumn = this.field[j][i];
                for(int x=0; x<9; x++){
                    // Check Sudoku line
                    if(numberLine==this.field[i][x] && x!=j){
                        lineList.add(i+1);
                    }
                    // Check Sudoku  column
                    if(numberColumn==this.field[x][i] && x!=j){
                        columnList.add(i+1);
                    }
                }
            }
        }

        lineList = lineList.stream().distinct().collect(Collectors.toList());
        columnList = columnList.stream().distinct().collect(Collectors.toList());
        areaList = areaList.stream().distinct().collect(Collectors.toList());

        return new SudokuDto(lineList,columnList,areaList);
    }


    private static int[][] readFile(String fileName){

        Path path = Paths.get("a.csv");

        List<String> read = new ArrayList<>();

        try{
            read =  Files.readAllLines(path);
        }catch (IOException ex){
            System.out.println("Not find file");
        }

        int[][] dataRead = new int[read.size()][];
        int numberLine = 0;

        for(Object line : read){
            String[] lineDataString =  String.valueOf(line).split(",");

            int[] lineInt = new int[lineDataString.length];

            for(int i=0; i < lineInt.length; i++) {
                lineInt[i] = Integer.parseInt(lineDataString[i]);
            }

            dataRead[numberLine] = lineInt;

            numberLine++;

        }
        return dataRead;
    }

}
