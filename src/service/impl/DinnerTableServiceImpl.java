package service.impl;

import java.util.List;

import service.IDinnerTableService;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;
import factory.BeanFactory;

public class DinnerTableServiceImpl implements IDinnerTableService {
	private IDinnerTableDao tableDao = BeanFactory.getInstance("DinnerTableDao", IDinnerTableDao.class);
	public void add(DinnerTable table) {
		tableDao.add(table);
	}
	public void delete(int id) {
		tableDao.delete(id);
	}
	public List<DinnerTable> getTables() {
		return tableDao.getTables();
	}
	public void update(DinnerTable table) {
		 tableDao.update(table);
	}
	public List<DinnerTable> getTables(String tableName) {
		return tableDao.getTables(tableName);
	}
	public DinnerTable getTableBy_id(int id) {
		return tableDao.getTableBy_id(id);
	}
	//根据状态，查询没有被预定的菜桌
	public List<DinnerTable> getTableBy_TableStatus() {
		return tableDao.getTableBy_TableStatus(TableStatus.Free);
	}

}
