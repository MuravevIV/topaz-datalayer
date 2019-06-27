package com.ilyamur.topaz.sqltool;

import java.util.List;

public class BatchUpdateResult {

    private List<Integer> updateCounts;

    public BatchUpdateResult(List<Integer> updateCounts) {
        this.updateCounts = updateCounts;
    }

    public List<Integer> getUpdateCounts() {
        return updateCounts;
    }
}
