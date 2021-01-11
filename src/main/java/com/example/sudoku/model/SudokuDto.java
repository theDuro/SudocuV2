package com.example.sudoku.model;

import java.util.List ;

public class SudokuDto {

    private List lineIds;
    private List columnIds;
    private List areaIds;

    public SudokuDto(List lineIds, List columnIds, List areaIds) {
        this.lineIds = lineIds;
        this.columnIds = columnIds;
        this.areaIds = areaIds;
    }

    public List getLineIds() {
        return lineIds;
    }

    public List getColumnIds() {
        return columnIds;
    }

    public List getAreaIds() {
        return areaIds;
    }

    public void setLineIds(List lineIds) {
        this.lineIds = lineIds;
    }

    public void setColumnIds(List columnIds) {
        this.columnIds = columnIds;
    }

    public void setAreaIds(List areaIds) {
        this.areaIds = areaIds;
    }
}
