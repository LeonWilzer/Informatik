package lib;

import jdk.jfr.ContentType;

public class TreeHelper<ContentType> {

    private Queue<ContentType> treeQueue;
    private ContentType breakCondition;

    public TreeHelper(Queue<ContentType> pTreeQueue, ContentType pBreakCondition)
    {
        treeQueue = pTreeQueue;
        breakCondition = pBreakCondition;
    }

    public TreeHelper()
    {
        treeQueue = null;
        breakCondition = null;
    }

    public BinaryTree<ContentType> buildBinTree(ContentType pRootNode){
        
        ContentType leftNode = treeQueue.front();
        treeQueue.dequeue();
        ContentType rightNode = treeQueue.front();
        treeQueue.dequeue();
        
        if((leftNode == null || leftNode == breakCondition) && (rightNode == null || rightNode == breakCondition))
        {
            if(pRootNode == null || pRootNode == breakCondition)
                return null;
            else
                return new BinaryTree<ContentType>(pRootNode);
        }
        else
        {
            return new BinaryTree<ContentType>(pRootNode, buildBinTree(leftNode), buildBinTree(rightNode));
        }
    }

    // Setters
    public void setTreeQueue(Queue<ContentType> treeQueue) { this.treeQueue = treeQueue; }
    public void setBreakCondition(ContentType breakCondition) { this.breakCondition = breakCondition; }

    // Getters
    public Queue<ContentType> getTreeQueue() { return treeQueue; }
    public ContentType getBreakCondition() { return breakCondition; }
}