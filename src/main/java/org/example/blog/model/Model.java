package org.example.blog.model;

import java.util.List;

public class Model<DataTypeOfListInTheModel> extends AbstractModel {
    private List<DataTypeOfListInTheModel> currentDataList;
    private Integer totalAmountOfData;

    public List<DataTypeOfListInTheModel> getCurrentDataList() {
        return currentDataList;
    }

    public void setCurrentDataList(List<DataTypeOfListInTheModel> currentDataList) {
        this.currentDataList = currentDataList;
    }

    public Integer getTotalAmountOfData() {
        return totalAmountOfData;
    }

    public void setTotalAmountOfData(Integer totalAmountOfData) {
        this.totalAmountOfData = totalAmountOfData;
    }
}
