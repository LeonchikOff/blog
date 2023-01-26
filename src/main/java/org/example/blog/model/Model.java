package org.example.blog.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class Model<DataTypeOfListInTheModel> {
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
    }
}
