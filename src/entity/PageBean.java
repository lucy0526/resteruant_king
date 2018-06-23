package entity;

import java.util.List;

public class PageBean<T> {
	private int currentPage;
	private int totalPage;//��ҳ��
	
	private int pageCount = 6;//ÿҳ��ʾ������
	private int totalCount;//��������
	private List<T> pageData;//����
	
	private Condition condition;
	/**
	 * �����ҳ��
	 * @return
	 */
	public int getTotalPage() {
		return ((totalCount%pageCount)==0)?(totalCount/pageCount):(totalCount/pageCount+1);
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> pageData) {
		this.pageData = pageData;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
}
