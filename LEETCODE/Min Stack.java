class MinStack {
    Stack<int[]> stack,minStack;
    int idx;
    public MinStack() {
        idx=0;
        stack=new Stack<>();
        minStack=new Stack<>();
    }

    public void push(int x) {
        if(minStack.isEmpty()) minStack.push(new int[]{idx,x});
        else {
            if(minStack.peek()[1]>x) minStack.push(new int[]{idx,x});
        }
        stack.push(new int[]{idx++,x});
    }

    public void pop() {
        --idx;
        int i=stack.pop()[0];
        if(minStack.peek()[0]==i) minStack.pop();
    }

    public int top() {
        return stack.peek()[1];
    }

    public int getMin() {
        return minStack.peek()[1];
    }

}
