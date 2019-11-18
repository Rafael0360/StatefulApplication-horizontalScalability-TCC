package application.repository;

import application.model.PrimeNumberResult;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MockRepositoryStateful {

    private static int ID = 0;

    private List<PrimeNumberResult> listDate = new ArrayList<PrimeNumberResult>();

    public synchronized PrimeNumberResult add(PrimeNumberResult dataModel) {
        dataModel.setId(++ID);
        listDate.add(dataModel);
        return dataModel;
    }

    public List<PrimeNumberResult> getAll() {
        return this.listDate;
    }

    public PrimeNumberResult getAllById(int id){
        for (PrimeNumberResult d : listDate) {
            if(d.getId() == id){
                return d;
            }
        }
        return null;
    }

    public synchronized PrimeNumberResult getByPrime(double input) {
        for (PrimeNumberResult d : listDate) {
            if(d.getNumberOriginal() == input){
                return d;
            }
        }
        return null;
    }
}
