package coreModels.model;

public class Pair<E> {
    public java.util.List<E> pagedList;
    public int maxPg;

    public Pair(java.util.List<E> list, int max){
        maxPg = max;
        pagedList = list;
    }
}
