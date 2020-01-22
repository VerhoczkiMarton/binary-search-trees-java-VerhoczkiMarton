package javabst;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {
    private Integer value;
    private Node right;
    private Node left;

    public Node(Integer value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        return new ArrayList<Node>() {
            {
                add(left);
                add(right);
            }
        };
    }

    public boolean remove(Integer value, Node parent) {
        if (value < this.value) {
            if (left != null) return left.remove(value, this);
            else return false;
        } else if (value > this.value) {
            if (right != null) return right.remove(value, this);
            else return false;
        } else {
            if (left != null && right != null) {
                this.value = right.minValue();
                right.remove(this.value, this);
            } else if (parent.left == this) {
                parent.left = (left != null) ? left : right;
            } else if (parent.right == this) {
                parent.right = (left != null) ? left : right;
            }
            return true;
        }
    }

    public int minValue() {
        if (left == null)
            return value;
        else
            return left.minValue();
    }
}
