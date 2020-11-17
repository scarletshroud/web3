package dao;

import model.Result;

import java.util.ArrayList;

public interface ResultDAO {
    public ArrayList<Result> getAllById();
    public void save();
    public void update();
    public void delete();
}
