public class NestedIterator implements Iterator<Integer> {
    List<Integer> list=new ArrayList<>();
    int idx=0;

    public NestedIterator(List<NestedInteger> nestedList) {
        go(nestedList);
    }

    public void go(List<NestedInteger> nestedList){
        for(NestedInteger ni : nestedList){
            if(ni.isInteger()) list.add(ni.getInteger());
            else go(ni.getList());
        }
    }

    @Override
    public Integer next() {
        return list.get(idx++);
    }

    @Override
    public boolean hasNext() {
        return idx<list.size();
    }
}