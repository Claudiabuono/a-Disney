package coreModels.model;

public class Paginator <E> {
	private int numEl;
	private int page; 
	
	public Paginator () {
		numEl = 15;
		page = 1;
	}
	
	public Paginator (int numberElements) {
		page = 1;
		numEl = numberElements;
	}
	
	public Paginator (int numberElements, int pg) {
		numEl = numberElements;
		page = pg;
	}
	
	public Pair paginate (java.util.List<E> list){
		java.util.List<E> pagedList = null;
		int start = numEl * (page - 1);
		int size = list.size();
		int maxPg = (size/numEl) + 1;
		
		if(size > start && size - start > numEl)
			pagedList = list.subList(start, start + numEl);
		else if(size > start && size - start <= numEl) 
			pagedList = list.subList(start, size);
			
		return new Pair (pagedList, maxPg);
	}
	
	public class Pair {
		public java.util.List<E> pagedList;
		public int maxPg;
		
		Pair (java.util.List<E> list, int max){
			maxPg = max;
			pagedList = list;
		}
	}
}
