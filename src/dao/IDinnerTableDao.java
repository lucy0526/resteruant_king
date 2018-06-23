package dao;

import java.util.List;

import entity.DinnerTable;
import entity.TableStatus;

public interface IDinnerTableDao {
	void add(DinnerTable table);
	void delete(int id);
	List<DinnerTable> getTables();
	void update(DinnerTable table);
	List<DinnerTable> getTables(String tableName);
	DinnerTable getTableBy_id(int id);
	List<DinnerTable> getTableBy_TableStatus(TableStatus ts);
	
}
