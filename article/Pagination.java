package article;

public class Pagination {

	private int currentPageNo; //현재 페이지 번호
	private int pageCountPerBlock; // 한 블럭당 페이지 개수
	private int itemCountPerPage; // 한 페이지당 게시물 개수
	private int totalCountOfItems; // 전체 게시물 개수
	
	public Pagination(int pageCountPerBlock, int itemCountPerPage, int totalCountOfItems) {
		this.currentPageNo = 1;
		this.pageCountPerBlock = pageCountPerBlock;
		this.itemCountPerPage = itemCountPerPage;
		this.totalCountOfItems = totalCountOfItems;
	}
	
	// 현재 페이지 블록 구하기
	public int getCurrentPageBlock() {
		return Util.getIntByCeil(currentPageNo, pageCountPerBlock); 
	}
	
	// 현재 페이지 블록의 시작 페이지 번호 구하기
	public int getStartNoInBlock() {
		int startNo = (getCurrentPageBlock() - 1) * pageCountPerBlock + 1;
		if(startNo < 1) {
			startNo = 1;
		}
		
		return startNo; 
	}
	
	// 현재 페이지 블록의 마지막 페이지 번호 구하기
	public int getEndNoInBlock() {
		int endNo = getStartNoInBlock() + pageCountPerBlock - 1;
		if(endNo > getLastNo()) {
			endNo = getLastNo();
		}
		return endNo;
	}
	
	// 마지막 페이지 번호 구하기
	public int getLastNo() {
		return Util.getIntByCeil(totalCountOfItems, itemCountPerPage);
	}
	
	// 마지막 페이지 블럭 구하기 => getLastNo / pageCountPerBlock
	public int getLastBlockNo() {
		return Util.getIntByCeil(getLastNo(), pageCountPerBlock);
	}
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public int getPageCountPerBlock() {
		return pageCountPerBlock;
	}

	public void setPageCountPerBlock(int pageCountPerBlock) {
		this.pageCountPerBlock = pageCountPerBlock;
	}

	public int getItemCountPerPage() {
		return itemCountPerPage;
	}

	public void setItemCountPerPage(int itemCountPerPage) {
		this.itemCountPerPage = itemCountPerPage;
	}

	public int getTotalCountOfItems() {
		return totalCountOfItems;
	}

	public void setTotalCountOfItems(int totalCountOfItems) {
		this.totalCountOfItems = totalCountOfItems;
	}
}
