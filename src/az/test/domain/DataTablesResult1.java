package az.test.domain;

import java.util.List;

public class DataTablesResult1 {
    private int draw;
    private int recordsTotal;
    private int recordsFiltered;
    private List<CustomerDto> data;

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public int getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(int recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<CustomerDto> getCustomerList() {
        return data;
    }

    public void setCustomerList(List<CustomerDto> data) {
        this.data = data;
    }
}
