package com.example.sudoku.rest;

import com.example.sudoku.model.SudokuDto;
import com.example.sudoku.service.SudokuService;
import com.example.sudoku.service.impl.SudokuServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuApiController {

    private SudokuService sudokuService = new SudokuServiceImpl();

    @PostMapping("api/sudoku/verify")
    public ResponseEntity<SudokuDto> verifySudoku(){

        SudokuDto sudokuDto = sudokuService.checkArea();

        if(sudokuDto.getLineIds().isEmpty() && sudokuDto.getAreaIds().isEmpty() && sudokuDto.getColumnIds().isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(sudokuDto,HttpStatus.BAD_REQUEST);
        }

    }

}
