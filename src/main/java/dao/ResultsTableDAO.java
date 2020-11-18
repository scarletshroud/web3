package dao;

import model.Result;

import java.util.ArrayList;
import java.util.List;

public interface ResultsTableDAO {
    public List<Result> getAll();
    public void save(Result result);
    public void update();
    public void delete();
}
