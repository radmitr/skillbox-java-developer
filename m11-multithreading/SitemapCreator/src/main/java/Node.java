import java.util.ArrayList;

public class Node {

    /** URL of the node */
    private final String url;

    /** The depth from the root node*/
    private int depth;

    /** Parent of this node */
    private Node parent;

    /** Children of this node */
    private ArrayList<Node> children;

    public Node(String url) {
        this.url = url;
        children = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public Node getParent() {
        return parent;
    }

    public int getDepth() {
        return depth;
    }

    /**
     * Get the root node
     */
    public Node getRootNode() {
        return parent == null ? this : parent.getRootNode();
    }

    /**
     * Calculate the depth
     */
    private int calculateDepth() {
        int result = 0;
        if (parent == null) {
            return result;
        }
        result = 1 + parent.getDepth();
        return result;
    }

    /**
     * Set the parent and set calculated depth
     */
    private void setParentAndDepth(Node node) {
        this.parent = node;
        this.depth = calculateDepth();
    }

    /**
     * Add a child to the node and set the parent
     */
    public void addChild(Node child) {
        child.setParentAndDepth(this);
        children.add(child);
    }
}
