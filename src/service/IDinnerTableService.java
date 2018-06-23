package service;

import java.util.List;

import entity.DinnerTable;
import entity.TableStatus;

public interface IDinnerTableService {
	void add(DinnerTable table);
	void delete(int id);
	List<DinnerTable> getTables();
	List<DinnerTable> getTables(String tableName);
	void update(DinnerTable table);
	DinnerTable getTableBy_id(int id);
	List<DinnerTable> getTableBy_TableStatus();
}
